package com.example.recyclerviewapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private TextView name, slogan;
    private ImageView logo;
    private View topView1, topView2, getTopView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}