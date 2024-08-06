package com.example.myschoolproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        Button ExBtn = (Button) context.findViewById(R.id.startExamBtn);

        ExBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity2.class);
                startActivity(intent);
            }
        });

    }
}