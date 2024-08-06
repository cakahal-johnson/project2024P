package com.example.android.wastemanagement;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.wastemanagement.Models.Bandwidth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scan extends AppCompatActivity {

    public static TextView tvresult;
    private  Button btn;

    private IntentIntegrator qrScan;
    DatabaseReference reference;
    private FirebaseAuth auth;
    List<Long> al = new ArrayList<Long>();
    long sum=0;
    EditText editText;
    String donorKey, userType;
    int isSell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        reference = FirebaseDatabase.getInstance().getReference().child("tracker");
        auth = FirebaseAuth.getInstance();

        qrScan = new IntentIntegrator(this);

        tvresult = (TextView) findViewById(R.id.tvresult);

        btn = (Button) findViewById(R.id.btn);
        editText = findViewById(R.id.addDonationContent);
        Bundle bd = getIntent().getExtras();

        if(bd!=null) {
            donorKey = bd.getString("donorKey");
            userType = bd.getString("type");
            isSell = bd.getInt("isSell");
            Toast.makeText(Scan.this,userType,Toast.LENGTH_SHORT).show();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.initiateScan();
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    tvresult.setText(obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    final String id = result.getContents();
                    Log.d("ID",id);

                    if(donorKey.equals(id)){
                        if(userType.equals("donor")){
                            DatabaseReference dbTracker = FirebaseDatabase.getInstance().getReference().child("tracker")
                                    .child(auth.getUid()).child(donorKey).child("donorLat");
                            dbTracker.setValue("no");
                            reference.child(auth.getUid()).child(id).child("toCollect")
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Bandwidth bandwidth = dataSnapshot.getValue(Bandwidth.class);
                                    if (bandwidth != null) {
                                        final long sum = bandwidth.getClothes()*5 + bandwidth.getElectronics()*10+bandwidth.getFurniture()*5+
                                                bandwidth.getGrains()*5+bandwidth.getHouseholdProduct()*5+bandwidth.getPackedFood()*5+
                                                bandwidth.getStationary()*5;
                                        Log.d("SUM", String.valueOf(sum));
                                        final DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("donor").child(id);
                                        db.child("donation_status").setValue((long)0);
                                        db.child("userPoints").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                Long temppoints=dataSnapshot.getValue(Long.class);
                                                db.child("userPoints").setValue(temppoints+sum);
                                                startActivity(new Intent(Scan.this,Home.class));
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }else{
                                        reference.child(auth.getUid()).child(id).child("industryItem")
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(final DataSnapshot dataSnapshot) {
                                                        final String type = dataSnapshot.getValue(String.class);
                                                        DatabaseReference dbx=FirebaseDatabase.getInstance().getReference()
                                                                .child("donor").child(id);
                                                        dbx.child("donation_status").setValue((long)0);
                                                        final DatabaseReference dby = FirebaseDatabase.getInstance().getReference()
                                                                .child("society").child("userEcoCash");
                                                        dby.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                if(!dataSnapshot.exists()){
                                                                    if(type.equals("E-waste")) {
                                                                        dby.setValue((double)(Double.valueOf(editText.getText().toString())*1.25*10));
                                                                    }else if(type.equals("Plastic")){
                                                                        dby.setValue((double)((Double.valueOf(editText.getText().toString())*1.25*10)));
                                                                    }else if(type.equals("Paper")){
                                                                        dby.setValue((double)((Double.valueOf(editText.getText().toString())*1.25*12)));
                                                                    }else if(type.equals("Organic")){
                                                                        dby.setValue((double)((Double.valueOf(editText.getText().toString())*1.25*2)));
                                                                    }else if(type.equals("Glass")){
                                                                        dby.setValue((double)((Double.valueOf(editText.getText().toString())*1.25*2)));
                                                                    }
                                                                }else{
                                                                    if(type.equals("E-waste")) {
                                                                        dby.setValue(dataSnapshot.getValue(Double.class)+(double)((Double.valueOf(editText.getText().toString())*1.25*10)));
                                                                    }else if(type.equals("Plastic")){
                                                                        dby.setValue(dataSnapshot.getValue(Double.class)+(double)((Double.valueOf(editText.getText().toString())*1.25*10)));
                                                                    }else if(type.equals("Paper")){
                                                                        dby.setValue(dataSnapshot.getValue(Double.class)+(double)((Double.valueOf(editText.getText().toString())*1.25*12)));
                                                                    }else if(type.equals("Organic")){
                                                                        dby.setValue(dataSnapshot.getValue(Double.class)+(double)((Double.valueOf(editText.getText().toString())*1.25*2)));
                                                                    }else if(type.equals("Glass")){
                                                                        dby.setValue(dataSnapshot.getValue(Double.class)+(double)((Double.valueOf(editText.getText().toString())*1.25*2)));
                                                                    }
                                                                }
                                                                startActivity(new Intent(Scan.this,Home.class));
                                                            }

                                                            @Override
                                                            public void onCancelled(DatabaseError databaseError) {

                                                            }
                                                        });
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }else{
                            Toast.makeText(Scan.this, "Successfully authenticated", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Scan.this, "QR code cannot be authenticated, Please check the user",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

