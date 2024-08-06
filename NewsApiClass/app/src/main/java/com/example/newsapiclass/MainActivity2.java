package com.example.newsapiclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;

    Button b1, b2, b3, b4, b5, b6, b7;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching News articles of " + query);
                dialog.show();

                RequestManager manager = new RequestManager(MainActivity2.this);
                manager.getNewsHeadlines(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching News articles...");
        dialog.show();

        b1 = findViewById(R.id.btn_1);
        b1.setOnClickListener((View.OnClickListener) this);


        b2 = findViewById(R.id.btn_2);
        b2.setOnClickListener((View.OnClickListener) this);


        b3 = findViewById(R.id.btn_3);
        b3.setOnClickListener((View.OnClickListener) this);


        b4 = findViewById(R.id.btn_4);
        b4.setOnClickListener((View.OnClickListener) this);


        b5 = findViewById(R.id.btn_5);
        b5.setOnClickListener((View.OnClickListener) this);


        b6 = findViewById(R.id.btn_6);
        b6.setOnClickListener((View.OnClickListener) this);


        b7 = findViewById(R.id.btn_7);
        b7.setOnClickListener((View.OnClickListener) this);

        RequestManager manager = new RequestManager(this);

    }
}