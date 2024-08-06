//package com.example.android.wastemanagement;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.android.wastemanagement.Models.Bandwidth;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class SocietyScan extends AppCompatActivity {
//
//    private FirebaseAuth auth;
//    private IntentIntegrator qrScan;
//    public static TextView tvresult;
//    private  Button btn;
//    double input;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_society_scan);
//
//        auth = FirebaseAuth.getInstance();
//        qrScan = new IntentIntegrator(this);
//        tvresult = (TextView) findViewById(R.id.tvresult);
//        btn = (Button) findViewById(R.id.btn);
//
//        Bundle bd = getIntent().getExtras();
//        if(bd!=null) {
//            input = (double)(bd.getInt("input"));
//        }
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                qrScan.initiateScan();
//            }
//
//        });
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null) {
//            //if qrcode has nothing in it
//            if (result.getContents() == null) {
//                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
//            } else {
//                //if qr contains data
//                try {
//                    //converting the data to json
//                    JSONObject obj = new JSONObject(result.getContents());
//                    //setting values to textviews
//                    tvresult.setText(obj.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    //if control comes here
//                    //that means the encoded format not matches
//                    //in this case you can display whatever data is available on the qrcode
//                    //to a toast
//                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
//                    final String id = result.getContents();
//                    Log.d("ID",id);
//                    final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("society")
//                            .child(id).child("userEcoCash");
//                    db.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            if(!dataSnapshot.exists()){
//                                if(type.equals("E-waste")) {
//                                    finalDb.setValue((double)(input*1.25*10));
//                                }else if(type.equals("Plastic")){
//                                    finalDb.setValue((double)(input*1.25*10));
//                                }else if(type.equals("Paper")){
//                                    finalDb.setValue((double)(input*1.25*12));
//                                }else if(type.equals("Organic")){
//                                    finalDb.setValue((double)(input*1.25*2));
//                                }else if(type.equals("Glass")){
//                                    finalDb.setValue((double)(input*1.25*2));
//                                }
//                            }else{
//                                if(type.equals("E-waste")) {
//                                    finalDb.setValue(dataSnapshot.getValue(Double.class)+(double)(input*1.25*10));
//                                }else if(type.equals("Plastic")){
//                                    finalDb.setValue(dataSnapshot.getValue(Double.class)+(double)(input*1.25*10));
//                                }else if(type.equals("Paper")){
//                                    finalDb.setValue(dataSnapshot.getValue(Double.class)+(double)(input*1.25*12));
//                                }else if(type.equals("Organic")){
//                                    finalDb.setValue(dataSnapshot.getValue(Double.class)+(double)(input*1.25*2));
//                                }else if(type.equals("Glass")){
//                                    finalDb.setValue(dataSnapshot.getValue(Double.class)+(double)(input*1.25*2));
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//
//                            DatabaseReference dbTracker = FirebaseDatabase.getInstance().getReference().child("tracker")
//                                    .child(auth.getUid()).child(donorKey).child("donorLat");
//                            dbTracker.setValue("no");
//                            reference.child(auth.getUid()).child(id).child("toCollect")
//                                    .addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(DataSnapshot dataSnapshot) {
//                                            Bandwidth bandwidth = dataSnapshot.getValue(Bandwidth.class);
//                                            if (bandwidth != null) {
//                                                final long sum = bandwidth.getClothes()*5 + bandwidth.getElectronics()*10+bandwidth.getFurniture()*5+
//                                                        bandwidth.getGrains()*5+bandwidth.getHouseholdProduct()*5+bandwidth.getPackedFood()*5+
//                                                        bandwidth.getStationary()*5;
//                                                Log.d("SUM", String.valueOf(sum));
//                                                final DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("donor").child(id);
//                                                db.child("donation_status").setValue((long)0);
//                                                db.child("userPoints").addListenerForSingleValueEvent(new ValueEventListener() {
//                                                    @Override
//                                                    public void onDataChange(DataSnapshot dataSnapshot) {
//                                                        Long temppoints=dataSnapshot.getValue(Long.class);
//                                                        db.child("userPoints").setValue(temppoints+sum);
//                                                        startActivity(new Intent(Scan.this,Home.class));
//                                                    }
//
//                                                    @Override
//                                                    public void onCancelled(DatabaseError databaseError) {
//
//                                                    }
//                                                });
//                                            }else{
//                                                reference.child(auth.getUid()).child(id).child("industryItem")
//                                                        .addListenerForSingleValueEvent(new ValueEventListener() {
//                                                            @Override
//                                                            public void onDataChange(final DataSnapshot dataSnapshot) {
//                                                                final String type = dataSnapshot.getValue(String.class);
//                                                                DatabaseReference db=FirebaseDatabase.getInstance().getReference()
//                                                                        .child("donor").child(id);
//                                                                db.child("donation_status").setValue((long)0);
//                                                                db = db.child("userEcoCash");
//                                                                final DatabaseReference finalDb = db;
//                                                                db.addListenerForSingleValueEvent(new ValueEventListener() {
//                                                                    @Override
//                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
//                                                                        if(!dataSnapshot.exists()){
//                                                                            if(type.equals("E-waste")) {
//                                                                                finalDb.setValue((double)(input*1.25*10));
//                                                                            }else if(type.equals("Plastic")){
//                                                                                finalDb.setValue((double)(input*1.25*10));
//                                                                            }else if(type.equals("Paper")){
//                                                                                finalDb.setValue((double)(input*1.25*12));
//                                                                            }else if(type.equals("Organic")){
//                                                                                finalDb.setValue((double)(input*1.25*2));
//                                                                            }else if(type.equals("Glass")){
//                                                                                finalDb.setValue((double)(input*1.25*2));
//                                                                            }
//                                                                        }else{
//                                                                            if(type.equals("E-waste")) {
//                                                                                finalDb.setValue(dataSnapshot.getValue(Double.class)+(double)(input*1.25*10));
//                                                                            }else if(type.equals("Plastic")){
//                                                                                finalDb.setValue(dataSnapshot.getValue(Double.class)+(double)(input*1.25*10));
//                                                                            }else if(type.equals("Paper")){
//                                                                                finalDb.setValue(dataSnapshot.getValue(Double.class)+(double)(input*1.25*12));
//                                                                            }else if(type.equals("Organic")){
//                                                                                finalDb.setValue(dataSnapshot.getValue(Double.class)+(double)(input*1.25*2));
//                                                                            }else if(type.equals("Glass")){
//                                                                                finalDb.setValue(dataSnapshot.getValue(Double.class)+(double)(input*1.25*2));
//                                                                            }
//                                                                        }
//                                                                        Long temppoints=dataSnapshot.getValue(Long.class);
//                                                                        finalDb.child("userPoints").setValue(temppoints+sum);
//                                                                        startActivity(new Intent(Scan.this,Home.class));
//                                                                    }
//
//                                                                    @Override
//                                                                    public void onCancelled(DatabaseError databaseError) {
//
//                                                                    }
//                                                                });
//                                                            }
//
//                                                            @Override
//                                                            public void onCancelled(DatabaseError databaseError) {
//
//                                                            }
//                                                        });
//                                            }
//                                        }
//                                        @Override
//                                        public void onCancelled(DatabaseError databaseError) {
//
//                                        }
//                                    });
//                            }
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
//}
