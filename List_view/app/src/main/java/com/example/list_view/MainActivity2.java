package com.example.list_view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;

    // Using ArrayList to store images data
    ArrayList courseImg = new ArrayList<>(Arrays.asList(R.drawable.data_structure, R.drawable.c_plus_plus,
            R.drawable.c_hash, R.drawable.java_script,
            R.drawable.java, R.drawable.c,
            R.drawable.html, R.drawable.css));
    ArrayList courseName = new ArrayList<>(Arrays.asList("Data Structure", "C++", "C#", "JavaScript", "Java",
            "C-Language", "HTML 5", "CSS"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting reference of recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Setting the layout as linear
        // layout for vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // Sending reference and data to Adapter
        Adapter adapter = new Adapter(MainActivity2.this, courseImg, courseName);

        // Setting Adapter to RecyclerView
        recyclerView.setAdapter(adapter);
    }
}
