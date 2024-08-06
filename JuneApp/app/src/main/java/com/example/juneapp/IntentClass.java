package com.example.juneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentClass extends AppCompatActivity {

    Button btn, btnweb, btndail, btnsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_class2);

        btn = findViewById(R.id.btn);
        btnweb = findViewById(R.id.btnweb);
        btndail = findViewById(R.id.btndail);
        btnsms = findViewById(R.id.btnsms);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntentClass.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btndail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:07062119521"));
                startActivity(intent);
            }
        });


        btnweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"));
                startActivity(intent);
            }
    });

        btnsms.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setType("vnd.android-dir/mms-sms");
//                intent.putExtra("address", "07062119521");
            intent.putExtra("sms_body", "Hello world");
            intent.setData(Uri.parse("sms:07062119521"));

            startActivity(intent);
        }
    });


    }
}