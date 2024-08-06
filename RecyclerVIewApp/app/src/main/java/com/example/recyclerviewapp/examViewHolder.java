package com.example.recyclerviewapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class examViewHolder extends RecyclerView.ViewHolder {
    TextView examName, examMessage, examDate;
    View view;
    public examViewHolder(@NonNull View itemView) {
        super(itemView);

        examName
                = (TextView)itemView
                .findViewById(R.id.examName);
        examDate
                = (TextView)itemView
                .findViewById(R.id.examDate);
        examMessage
                = (TextView)itemView
                .findViewById(R.id.examMessage);
        view  = itemView;
    }
}
