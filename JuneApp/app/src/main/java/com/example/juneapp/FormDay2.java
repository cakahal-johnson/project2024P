package com.example.juneapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormDay2 extends AppCompatActivity {
    EditText email, username,password;
    Button button;

    AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_day2);

        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button = findViewById(R.id.submit);
        alertBuilder = new AlertDialog.Builder(FormDay2.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();
                String emailTxt = email.getText().toString();

                //validation section
                if (usernameTxt.trim().isEmpty()){
                    username.setError("Please fill filed");
                    return;
                }

                if(passwordTxt.trim().length() < 5){
                    password.setError("Password must be 5 character and above");
                    return;
                }

                if(!emailTxt.trim().contains("@") && emailTxt.trim().length() < 3){
                    email.setError("Invalid Email");
                    return;
                }

                alertBuilder.setTitle("Success");
                alertBuilder.setMessage("You have successfully registered");

                alertBuilder.setPositiveButton("Go to Dashboard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Bundle bundle = new Bundle();
                        bundle.putString("username", usernameTxt);
                        bundle.putString("password", passwordTxt);
                        bundle.putString("email", emailTxt);

                        Intent intent = new Intent(FormDay2.this, Dashborad.class);
                        intent.putExtras(bundle); // fix on Array section
                        startActivity(intent);


                    }
                });


            }
        });
    }
}