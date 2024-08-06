package com.example.firebaseblog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FloatingActionButton fab;
    ArrayList postsLists;
    BlogAdapter blogAdapter;
    ListView listview;

    public HomeFragment(){
        //Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postsLists = new ArrayList();
        blogAdapter = new BlogAdapter(getContext(),postsLists);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);
        fab = view.findViewById(R.id.fab);
        listview = view.findViewById(R.id.listview);
        listview.setAdapter(blogAdapter);
        getPosts();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PostActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getPosts() {
        // Log.i("Keyyy", "00000");
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("blogs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    //Log.i("Keyyy", "111111");
                    for (QueryDocumentSnapshot document : task.getResult()){
                        Log.i("Key", document.getId() + " => " + document.getData());
                        postsLists.add(document.getData());
                    }
                }else{
                    //Log.w(TAG, "Error getting documents.", task.getException());
                }
                blogAdapter.notifyDataSetChanged();

            }
        });
    }
}
