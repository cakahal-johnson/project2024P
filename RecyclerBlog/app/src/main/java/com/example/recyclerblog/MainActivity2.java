package com.example.recyclerblog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity2.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        androidData = new DataClass("camera", R.string.camera, "Java", R.drawable.java);
        dataList.add(androidData);

        androidData = new DataClass("recyclerView", R.string.recyclerView, "Html", R.drawable.html);
        dataList.add(androidData);

        androidData = new DataClass("RView", R.string.camera, "Javascript", R.drawable.java_script);
        dataList.add(androidData);

        androidData = new DataClass("edit", R.string.edit, "C#", R.drawable.c_hash);
        dataList.add(androidData);

        androidData = new DataClass("rating", R.string.edit, "C", R.drawable.c);
        dataList.add(androidData);

        androidData = new DataClass("data", R.string.edit, "C++", R.drawable.c_plus_plus);
        dataList.add(androidData);

        adapter = new MyAdapter(MainActivity2.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private void searchList(String text){
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList){
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())){
                dataSearchList.add(data);
            }
        }

        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        }else {
            adapter.setSearchList(dataSearchList);
        }
    }
}