 package com.example.assignmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

 public class UserInfo extends AppCompatActivity {
    EditText etName, etEmail, etContact;
    Button btSave;
    TextView tv_initial, tv_name, tv_email, tv_contact;
    ArrayList<ModelClass> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        etContact = findViewById(R.id.contact);
        btSave = findViewById(R.id.submit);
        tv_initial = findViewById(R.id.Dinitial);
        tv_name = findViewById(R.id.Dname);
        tv_email = findViewById(R.id.Demail);
        tv_contact = findViewById(R.id.Dcontact);

        loadData();

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(etName.getText().toString(), etEmail.getText().toString(), etContact.getText().toString());
            }
        });
    }

     private void loadData() {
         SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);

         Gson gson = new Gson();
         String json = sharedPreferences.getString("student_data", null);
         Type type = new TypeToken<ArrayList<ModelClass>>(){
         }.getType();

         arrayList = gson.fromJson(json,type);

         if(arrayList==null){
             arrayList=new ArrayList<>();
             tv_name.setText(" "+0);
             tv_initial.setText(" "+0);
             tv_email.setText(" "+0);
             tv_contact.setText(" "+0);
         }else {
             for (int i = 0; i < arrayList.size(); i++){
//                 tv_initial.setText(tv_initial.getText().toString()+"\n"+arrayList.get(i).initial+"\n");
                 tv_name.setText(tv_name.getText().toString()+"\n"+arrayList.get(i).name+"\n");
                 tv_email.setText(tv_email.getText().toString()+"\n"+arrayList.get(i).email+"\n");
                 tv_contact.setText(tv_contact.getText().toString()+"\n"+arrayList.get(i).contact+"\n");
             }
         }



     }

     private void saveData(String name, String email, String contact) {
         SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPreferences.edit();

         Gson gson = new Gson();
         arrayList.add(new ModelClass(name, email, Integer.parseInt(contact)));
         String json = gson.toJson(arrayList);
         editor.putString("student_data", json);
         editor.apply();

         tv_name.setText(" \n");
//         tv_initial.setText(" \n");
         tv_email.setText(" \n");
         tv_contact.setText(" \n");

         loadData();

     }
 }