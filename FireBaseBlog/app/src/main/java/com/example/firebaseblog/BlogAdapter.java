package com.example.firebaseblog;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Map;

public class BlogAdapter extends ArrayAdapter {
    ArrayList<Map> posts;
    Context context;


    public BlogAdapter(@NonNull Context context, ArrayList<Map> posts) {
        super(context, 0);
        this.posts = posts;
        this.context = context;
    }



    @Override
    public int getCount() {
        Log.i("keyyy", String.valueOf(posts.size()));
        return posts.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.single_post, parent,false);
        TextView title = convertView.findViewById(R.id.title);
        TextView author = convertView.findViewById(R.id.author);
        TextView content = convertView.findViewById(R.id.content);
        ImageView image = convertView.findViewById(R.id.image);
        ImageView profile = convertView.findViewById(R.id.profile);

        /////////GETTING VALUES***************
        Map blog = posts.get(position);

        title.setText(blog.get("title").toString());
        author.setText(blog.get("username").toString());
        content.setText(blog.get("body").toString());

        return convertView;
    }

}
