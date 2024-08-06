package com.example.firebaseblog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    ImageView avatar;
    Button registerBTN;
    EditText emailBTN, usernameBTN, passwordBTN;
    CardView avatar_card;
    Uri profileUri = null;
    int SELECT_IMAGE = 200;
    TextView msg;

    String emailTxt;
    String passwordTxt;
    String usernameTxt;
    String imageUrli;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        avatar = findViewById(R.id.avatar);
        emailBTN = findViewById(R.id.email);
        usernameBTN = findViewById(R.id.username);
        passwordBTN = findViewById(R.id.password);
        registerBTN = findViewById(R.id.register);
        avatar_card = findViewById(R.id.avatar_card);
        msg = findViewById(R.id.msg);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing");


        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearMSG();
                passwordTxt = passwordBTN.getText().toString();
                usernameTxt = usernameBTN.getText().toString();
                emailTxt = emailBTN.getText().toString();
//                String regex =
//                        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


                if (null == profileUri) {
                    setMSG("please select a profile image");
                    return;
                } else if (usernameTxt.isEmpty()) {
                    setMSG("Opps! Username is empty");
                    return;
                } else if (emailTxt.isEmpty()) {
                    setMSG("Invalid email");
                    return;
                } else if (passwordTxt.length() < 6) {
                    setMSG("password must be greater than 6 xters");
                    return;
                }
                uploadImage();

            }
        });


        avatar_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

    }

    public void uploadImage(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();


        // Create a reference to "mountains.jpg"
        StorageReference ref = storageRef.child("profilePics/"+ UUID.randomUUID().toString());


        ref.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imageUrli = uri.toString();
                        createAccount();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                setMSG("Image failed to upload: "+e.getMessage().toString());
                return;
            }
        });

    }

    //HERE IS OUTSIDE BOX

    public void registerToFirestore(String user_id){

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Map<String, Object> user_details = new HashMap<>();
        user_details.put("username", usernameTxt);
        user_details.put("image", imageUrli);
        user_details.put("email", emailTxt);
        user_details.put("password", passwordTxt);
        user_details.put("user_id", user_id);

        firestore.collection("blog_users").document(user_id).set(user_details).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                setMSG("Successful");

                Intent i = new Intent(Register.this,Dashboard.class);
                startActivity(i);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                setMSG(e.getMessage().toString());
            }
        });
    }

    public void createAccount(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(emailTxt,passwordTxt).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String id = authResult.getUser().getUid();
                registerToFirestore(id);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                setMSG("Failed to create "+e.getMessage().toString());
            }
        });
    }

    public void setMSG(String message) {
        progressDialog.dismiss();
        msg.setText(message);
    }

    public void clearMSG() {
        progressDialog.show();
        msg.setText("");
    }


    public void selectImage() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                profileUri = data.getData();

                if (null != profileUri) {
                    avatar.setImageURI(profileUri);
                }
            }
        }
    }
}