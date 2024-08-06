package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.realestate.adapter.RecentsAdapter;
import com.example.realestate.adapter.TopPlacesAdapter;
import com.example.realestate.model.RecentsData;
import com.example.realestate.model.TopPlacesData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //here after setting the adapter and the model
    // so after the topPlaces setting up we add the recycler as well
    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // here we add some dummy data in our model class
        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("Abakpa Nike", "Enugu", "From: $2000", R.drawable.house1));
        recentsDataList.add(new RecentsData("Boro Town", "Bayelsa", "From: $5000", R.drawable.house));
        recentsDataList.add(new RecentsData("Zuba", "Abuja", "From: $3000", R.drawable.h1));
        recentsDataList.add(new RecentsData("GRA", "Enugu", "From: $6000", R.drawable.h7));

        recentsDataList.add(new RecentsData("Abakpa Nike", "Enugu", "From: $2000", R.drawable.h2));
        recentsDataList.add(new RecentsData("Boro Town", "Bayelsa", "From: $5000", R.drawable.h3));
        recentsDataList.add(new RecentsData("Zuba", "Abuja", "From: $3000", R.drawable.h4));
        recentsDataList.add(new RecentsData("GRA", "Enugu", "From: $6000", R.drawable.h5));

//        calling the class we created below 23:   set the res values style xml to NoActionBar
        setRecentRecycler(recentsDataList);

        // here we add some dummy data in out model class
        List<TopPlacesData> topPlacesDataList = new ArrayList<>();

        topPlacesDataList.add(new TopPlacesData("Obiagu", "Enugu", "$200 - $500", R.drawable.h7));
        topPlacesDataList.add(new TopPlacesData("New Heaven", "Enugu", "$800 - $1500", R.drawable.h7));
        topPlacesDataList.add(new TopPlacesData("Emene", "Enugu", "$220 - $500", R.drawable.h7));
        topPlacesDataList.add(new TopPlacesData("Abakpa", "Enugu", "$400 - $700", R.drawable.h7));
        topPlacesDataList.add(new TopPlacesData("Trans-Ekulu", "Enugu", "$700 - $1000", R.drawable.h7));

        // calling the class we created for Top Place
        setTopPlacesRecycler(topPlacesDataList);


    }

    // creating a class for the Recent Row recycler view
    private void setRecentRecycler(List<RecentsData> recentsDataList){

        recentRecycler = findViewById(R.id.recent_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);
    }

    // creating a class fo the TopPlaces Row recycler view
    private void setTopPlacesRecycler(List<TopPlacesData>topPlacesDataList){

        topPlacesRecycler = findViewById(R.id.top_places_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);
    }
}