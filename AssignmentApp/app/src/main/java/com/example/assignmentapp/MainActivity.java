package com.example.assignmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button user_detailBTN, cl_detailBTN, smsBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        user_detailBTN = findViewById(R.id.user_detail);
        cl_detailBTN = findViewById(R.id.user_detail2);
        smsBTN = findViewById(R.id.sms1);

        smsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SmsClass.class);
                startActivity(intent);
            }
        });



        user_detailBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UserInfo.class);
                startActivity(intent);
            }
        });

        cl_detailBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AsstNum1.class);
                startActivity(intent);
            }
        });


    }
}