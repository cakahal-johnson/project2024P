package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Get references to UI elements
        TextView nameTextView = findViewById(R.id.name_text_view);
        TextView courseTextView = findViewById(R.id.course_text_view);

        // Get intent and data from previous activity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String course = intent.getStringExtra("course");

        // Update UI elements with data
        nameTextView.setText(name);
        courseTextView.setText(course);
    }

}