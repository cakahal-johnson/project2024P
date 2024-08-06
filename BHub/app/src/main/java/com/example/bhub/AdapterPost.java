package com.example.bhub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.HolderPost>  {

    private Context context;
    private ArrayList<ModelPost> postArrayList;

    //constructor
    public AdapterPost(Context context, ArrayList<ModelPost> postArrayList) {
        this.context = context;
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public HolderPost onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout row_post.xml
        View view = LayoutInflater.from(context).inflate(R.layout.row_post, parent, false);

        return new HolderPost(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPost holder, int position) {
        //get data, set data, handle click etc...
        ModelPost model = postArrayList.get(position); //get data at specific position

        //get data
        String authorName = model.getAuthorName();
        String content = model.getContent();
        String id = model.getId(); //post id
        String published = model.getPublished();
        String selfLink = model.getSelfLink();
        String title = model.getTitle();
        String updated = model.getUpdated();
        String url = model.getUrl();

        //content/description is in HTML/web form, we need to convert it to simple text using jsoup library
        Document document = Jsoup.parse(content);

        try{
            //there may multiple images, get first image from the post
            Elements elements = document.select("img");
            String image = elements.get(0).attr("src");
            Picasso.get().load(image).placeholder(R.drawable.ic_image).into(holder.imageIv);
//                28:05 get()
        }catch (Exception e){
            //exception occur while collecting image may be due to no image in post, set default
            holder.imageIv.setImageResource(R.drawable.ic_image);

        }

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

        holder.titleTv.setText(title);
        holder.descriptionTv.setText(document.text());
        holder.publishInfoTv.setText("By " + authorName + " "+ formattedDate); //e.g By Cakahal Johnson 25/10/2022 02:12 am

        //handle click, start activity with post id
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent to start activity
                Intent intent = new Intent(context, PostDetailsActivity.class);
                intent.putExtra("postId", id); // key, value: pass post id will be used tol get post details in PostDetailsActivity
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return postArrayList.size(); //returns number of records, list size
    }

    /* ViewHolder class that holds Ui Views of row_post.xml*/
    class HolderPost extends RecyclerView.ViewHolder{

        // Ui Views of row_post.xml
        ImageButton moreBtn;
        TextView titleTv, publishInfoTv, descriptionTv;
        ImageView imageIv;

        public HolderPost(@NonNull View itemView) {
            super(itemView);

            //initializing UI views
            moreBtn = itemView.findViewById(R.id.moreBtn);
            titleTv = itemView.findViewById(R.id.titleTv);
            publishInfoTv = itemView.findViewById(R.id.publishInfoTv);
            imageIv = itemView.findViewById(R.id.imageIv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
        }
    }
}
