package com.example.myschoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    Button loginBtn;
    EditText username, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        loginBtn = findViewById(R.id.submit);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();
                if (usernameTxt.trim().length() < 1){
                    Toast.makeText(login.this,
                            "Username must be greater than 3 character", Toast.LENGTH_LONG).show();
                }else if (passwordTxt.trim().length() < 1){
                    Toast.makeText(login.this,
                            "Password must be greater than 3 character", Toast.LENGTH_LONG).show();
                }else if (usernameTxt.equals("student") && passwordTxt.equals("123456")){
                    Toast.makeText(login.this,
                            "Login Successful Welcome Back!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(login.this,MainActivityMenu.class);
                    startActivity(intent);

                    finish();

                }else{

                    Toast.makeText(login.this, "unauthorized access contact the Admin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    public void login(){
//
//    }
}