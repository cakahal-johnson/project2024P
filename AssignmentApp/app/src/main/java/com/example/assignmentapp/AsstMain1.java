//package com.example.assignmentapp;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class AsstMain1 extends AppCompatActivity {
//
//    LinearLayout container;
//    LayoutInflater layoutInflater;
//    EditText name, email, phone;
//    Button BTN;
//    AlertDialog alertDialog;
//    AlertDialog.Builder alertBuilder;
//    ArrayList contacts = new ArrayList();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_asst_main1);
//
//        name = findViewById(R.id.name);
//        email = findViewById(R.id.email);
//        phone = findViewById(R.id.phone);
//        BTN = findViewById(R.id.submit);
//        layoutInflater = layoutInflater.from(this);
//
//        alertBuilder = new AlertDialog.Builder(AsstMain1.this);
//
//        BTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view1) {
//                String nameTxt = name.getText().toString();
//                String emailTxt = email.getText().toString();
//                String phoneTxt = phone.getText().toString();
//                String[] contact = {nameTxt, emailTxt, phoneTxt};
//                contacts.add(contact);
//
//                for(int i = 0; i < contacts.size(); i++){
//                    String[] single_person = (String[]) contacts.get(i);
//                    String nameV = single_person[0];
//                    String emailV = single_person[1];
//                    String phoneV = single_person[2];
//
//                    String initial = nameV.trim().substring(0,1);
//
//                    View view = layoutInflater.inflate(R.layout.single_user,container, false);
//                    TextView email_textview = view.findViewById(R.id.email);
//                    TextView name_textview = view.findViewById(R.id.name);
//                    TextView phone_textview = view.findViewById(R.id.phone);
//                    TextView initial_textview = view.findViewById(R.id.initial);
//                    TextView delete_textview = view.findViewById(R.id.delelete);
//
//
//                    name_textview.setText(nameV);
//                    email_textview.setText(emailV);
//                    phone_textview.setText(phoneV);
//                    initial_textview.setText(initial);
//
//                    container.addView(view);
//
//                    delete_textview.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view1) {
//                            alertBuilder.setTitle("DELETE");
//                            alertBuilder.setMessage("Are you sure!");
//
//                            alertBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    container.removeView(view);
//                                }
//                            });
//
//                            alertBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    finish();
//                                }
//                            });
//
//                            AlertDialog alertDialog = alertBuilder.create();
//                            alertDialog.show();
//
//                        }
//                    });
//                }
//            }
//        });
//    }
//}