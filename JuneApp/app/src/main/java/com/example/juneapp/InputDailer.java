package com.example.juneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class InputDailer extends AppCompatActivity {

    EditText etNumber;
    ImageButton btCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dailer);

        etNumber = findViewById(R.id.et_number);
        btCall = findViewById(R.id.bt_call);

        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etNumber.getText().toString();
                if (phone.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter a Number", Toast.LENGTH_SHORT).show();
                }else{
                    String s = "tel:" + phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
//                    finish();
                }
            }
        });
    }
}