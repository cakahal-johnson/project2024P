package com.example.childabuseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen_logins extends AppCompatActivity {

    private Button buttonLoginAdmin;
    private Button buttonLoginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_logins);

        buttonLoginAdmin = findViewById(R.id.buttonLoginAdmin);
        buttonLoginUser = findViewById(R.id.buttonLoginUser);

        buttonLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_logins.this, UserLogin.class);
                startActivity(intent);
            }
        });

        buttonLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_logins.this, UserRegistration.class);
                startActivity(intent);
            }
        });
    }
}