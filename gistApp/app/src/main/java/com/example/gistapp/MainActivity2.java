package com.example.gistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    Button loginBtn;
    TextView forgotPassword, needAnAccount;

    FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        forgotPassword = findViewById(R.id.forgot_password);

        needAnAccount = findViewById(R.id.need_an_account);
        loginBtn = findViewById(R.id.login_btn);

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();

        //here is forgotten password section
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, ForgotPasswordActivity.class));
            }
        });

        needAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, RegisterActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    loginEmail.setError("Email is required");
                }else if (TextUtils.isEmpty(password)){
                    loginPassword.setError("Password is required");
                }else{
                    Login(email, password);
                }

            }
        });

    }

    private void Login(String email, String password) {
        progressDialog.setTitle("Please wait...");
        progressDialog.show();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity2.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity2.this,HomeActivity.class));
                }else{
                    Toast.makeText(MainActivity2.this, "Login failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity2.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

    //here we will check if the user is already login take to the home activity
    @Override
    protected void onStart() {
        super.onStart();
    }
    //
}