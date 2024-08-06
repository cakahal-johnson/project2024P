package com.example.firebaseblog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

public class PostActivity extends AppCompatActivity {
    EditText titleEd, bodyEd;
    Button submit;
    ImageView imageView;

    Uri imageUri = null;
    int SELECT_IMAGE = 200;
    TextView msg;
    ProgressDialog progressDialog;
    String titleText, bodyText;
    BlogUser blogUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_activity);

        imageView = findViewById(R.id.imageView);
        msg = findViewById(R.id.msg);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        submit = findViewById(R.id.submit);
        titleEd = findViewById(R.id.title);
        bodyEd = findViewById(R.id.body);

        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (null == imageUri){
                    setMSG("Please select an Image");
                    return;
                }
                titleText = titleEd.getText().toString();
                bodyText = bodyEd.getText().toString();
                if (titleText.trim().isEmpty()){
//                    msg.setTextColor(R.color.teal_200);
                    setMSG("Please Type the Title of the Blog");
                    return;
                }else if (bodyText.trim().isEmpty()){
//                    msg.setTextColor(R.color.teal_200);
                    setMSG("Please Type the Title of the Blog");
                    return;

                }

                uploadImage();
            }
        });


    }



    private void setMSG(String message) {
        progressDialog.dismiss();
        msg.setText(message);
    }
    public void clearMSG(){
        progressDialog.show();
        msg.setText("");
    }

    public void selectimage(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"select Picture"),SELECT_IMAGE);
    }

    private void uploadImage() {
        clearMSG();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        StorageReference ref = storageRef.child("blog/"+ UUID.randomUUID().toString());

        ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imageUri = uri;
                        getUserDetails();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == SELECT_IMAGE){
                imageUri = data.getData();

                if(null != imageUri){
                    imageView.setImageURI(imageUri);
                }
            }
        }
    }

    private void getUserDetails() {

        BlogUser.getInstance(FirebaseAuth.getInstance().getCurrentUser().getUid());

        FirebaseAuth.getInstance().getCurrentUser().getUid();
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("blog_users")
                .document(user_id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                blogUser = documentSnapshot.toObject(BlogUser.class);
                createPost(blogUser);
            }
        });
    }

    private void createPost(BlogUser blogUser) {

        HashMap<String, Object> post = new HashMap<>();
        post.put("user_id", blogUser.user_id);
        post.put("username", blogUser.username);
        post.put("email",blogUser.email);
        post.put("image",blogUser.image);
        post.put("blog_image",imageUri);
        post.put("title",titleText);
        post.put("body",bodyText);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("blogs").document(UUID.randomUUID().toString()).set(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onSuccess(Void unused) {
                        msg.setTextColor(R.color.teal_200);
                        setMSG("Successfully submitted");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        msg.setTextColor(R.color.teal_200);
                        setMSG(e.getMessage().toString());
                    }
                });
    }


}