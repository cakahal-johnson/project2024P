package com.example.newfeed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntertainmentFragment extends Fragment {

    String api = "c0b4db7fd8394e1bbd1ac6dfbefa0e49";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter   adapter;
    String country = "us";
    private RecyclerView recyclerViewofentertainment;
    private String category = "entertainment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.entertainmentfragment, null);


        recyclerViewofentertainment = v.findViewById(R.id.recyclerviewofenterainment);
        modelClassArrayList = new ArrayList<>();
        recyclerViewofentertainment.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        recyclerViewofentertainment.setAdapter(adapter);

        findNews();


        return v;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful())
                {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });


    }
}
