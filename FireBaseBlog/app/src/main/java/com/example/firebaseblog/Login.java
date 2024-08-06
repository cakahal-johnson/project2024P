package com.example.firebaseblog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button submitBtn;
    EditText emailEd, passwordEd;
    String user_id = null;
    String emailTxt;
    String passwordTxt;
    TextView msg;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEd = findViewById(R.id.email);
        passwordEd = findViewById(R.id.password);
        submitBtn = findViewById(R.id.login);
        msg = findViewById(R.id.msg);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing");


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordTxt = passwordEd.getText().toString();
                emailTxt = emailEd.getText().toString();

                if (emailTxt.isEmpty()){
                    setMSG("Email is empty");
                    return;
                }

                if (passwordTxt.isEmpty()){
                    setMSG("Email is empty");
                    return;
                }

                signIn();
            }
        });
    }

    public void signIn(){
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(emailTxt, passwordTxt).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                if (authResult.getUser() != null){
                    Intent i = new Intent(Login.this, Dashboard.class);
                    startActivity(i);
                }else{
                    setMSG("Error Occurred");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                setMSG(e.toString());
            }
        });
    }

    public void setMSG(String message){
        progressDialog.dismiss();
        msg.setText(message);
    }

    public void clearMSG(){
        progressDialog.show();
        msg.setText("");
    }

}