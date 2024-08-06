package com.example.gistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gistapp.Adapter.PostAdapter;
import com.example.gistapp.Model.PostModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth auth;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    List<PostModel> postModelList;

    //here is for checking the onBackpressed function
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true); //here will load from end and will show latest post first

        recyclerView.setLayoutManager(layoutManager);

//        getting the array post
        postModelList = new ArrayList<>();

//        now we retrieve data from data from firebase
        loadPosts();



    }

    private void loadPosts() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postModelList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    PostModel postModel = ds.getValue(PostModel.class);
                    postModelList.add(postModel);
                    postAdapter = new PostAdapter(HomeActivity.this, postModelList);
                    recyclerView.setAdapter(postAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this, ""+databaseError, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //8 of 15


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_logout){
            auth.signOut();
            startActivity(new Intent(HomeActivity.this, MainActivity2.class));
        }
        if (item.getItemId() == R.id.action_add_post){
            startActivity(new Intent(HomeActivity.this, AddPostActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finishAffinity(); //means when user is on main screen and press the back button then shutdown the app

        //here when click on the back button
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

}