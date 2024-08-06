package com.example.firebaseblog;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

public class BlogUser {
    String user_id, image,email,username,password;

    public static BlogUser getInstance(String user_id){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        final BlogUser[] blogUser = new BlogUser[1];

       DocumentReference docRef = firestore.collection("blog_users").document(user_id);
       docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
           @Override
           public void onSuccess(DocumentSnapshot documentSnapshot) {
               Log.i("blog_user", String.valueOf(documentSnapshot));

               blogUser[0] = documentSnapshot.toObject(BlogUser.class);

           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               System.out.println("User is null :"+e.toString());
               e.printStackTrace();
           }
       });
       return blogUser[0];
    }

    public BlogUser(String image, String email, String username, String password, String user_id) {
        this.user_id = user_id;
        this.image = image;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BlogUser(){}
}
