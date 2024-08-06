package com.example.bhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.HolderComment> {

    private Context context;
    private ArrayList<ModelComment> commentArrayList;

    //constructor
    public AdapterComment(Context context, ArrayList<ModelComment> commentArrayList) {
        this.context = context;
        this.commentArrayList = commentArrayList;
    }

    @NonNull
    @Override
    public HolderComment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout row_comment xml
        View view = LayoutInflater.from(context).inflate(R.layout.row_comment, parent, false);
        return new HolderComment(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderComment holder, int position) {
        //Get data
        ModelComment modelComment = commentArrayList.get(position);
        String id = modelComment.getId();
        String name = modelComment.getName();
        String published = modelComment.getPublished();
        String comment = modelComment.getComment();
        String image = modelComment.getProfileImage();

        //format GMT date formate to proper format dd/MM/yyyy
        //format date
        String gmtDate = published;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //convert from e.g 2020-10-25T14:12:00-07:00
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy k:mm a"); //convert to e.g 25/10/2020 02:12 pm
        String formattedDate = "";
        try{
            Date date = dateFormat.parse(gmtDate);
            formattedDate = dateFormat2.format(date);
        }catch (Exception e){
            // if there is any exceptions while formatting the date, show the same we got from API
            formattedDate = published;
            e.printStackTrace();
        }

        //Set data
        holder.nameTv.setText(name);
        holder.dateTv.setText(formattedDate);
        holder.commentTv.setText(comment);
        try {
            Picasso.get().load(image).placeholder(R.drawable.ic_person_gray).into(holder.profileTv);
        }
        catch (Exception e){
            holder.profileTv.setImageResource(R.drawable.ic_person_gray);
        }
    }

    @Override
    public int getItemCount() {
        return commentArrayList.size(); //return size of list / number of records
    }

    /*View holder class from the row_comment xml */
    class HolderComment extends RecyclerView.ViewHolder{


        //UI Views of row_comment xml
        ImageView profileTv;
        TextView nameTv, dateTv, commentTv;


        public HolderComment(@NonNull View itemView) {
            super(itemView);

            //init UI Views
            profileTv = itemView.findViewById(R.id.profileTv);
            nameTv = itemView.findViewById(R.id.nameTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            commentTv = itemView.findViewById(R.id.commentTv);


        }
    }
}
