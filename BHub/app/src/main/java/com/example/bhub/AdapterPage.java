package com.example.bhub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class AdapterPage extends RecyclerView.Adapter<AdapterPage.HolderPage> {

    private Context context;
    private ArrayList<ModelPage> pageArrayList;

    //constructor
    public AdapterPage(Context context, ArrayList<ModelPage> pageArrayList) {
        this.context = context;
        this.pageArrayList = pageArrayList;
    }

    @NonNull
    @Override
    public HolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout row_page xml
        View view = LayoutInflater.from(context).inflate(R.layout.row_page, parent, false);

        return new HolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPage holder, int position) {
        //Get data
        ModelPage model = pageArrayList.get(position);
        String authorName = model.getAuthorName();
        String content = model.getContent();
        String id = model.getId();
        String published = model.getPublished();
        String selfLink = model.getSelfLink();
        String title = model.getTitle();
        String updated = model.getUpdated();
        String url = model.getUrl();

        //description/content is in html/web form, format it
        Document document = Jsoup.parse(content);
        try {
            //get thumbnail from page, if page contains multiple images, it will get first image as thumbnail
            Elements elements = document.select("img");
            String image = elements.get(0).attr("src");
            //set the image, if there is any
            Picasso.get().load(image).placeholder(R.drawable.ic_image).into(holder.imageIv);
        }
        catch (Exception e){
            //if exception occurs due to no image or any other reason set default image
            holder.imageIv.setImageResource(R.drawable.ic_image);
        }

        //format data from GMT to dd/MM/yyyy
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

        //set data
        holder.titleTv.setText(title);
        holder.descriptionTv.setText(document.text());
        holder.publishInfoTv.setText("By "+authorName+" "+formattedDate); //By Cakahal Johnson 30/12/2022

        //handle page click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start page details activity, pass id of the page, whose details wanna show
                Intent intent = new Intent(context, PostDetailsActivity.class);
                intent.putExtra("pageId", id);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return pageArrayList.size(); //return list size | number of records
    }

    //View holder class for row_page xml
    class HolderPage extends RecyclerView.ViewHolder{

        //UI views of row_page xml
        private TextView titleTv, publishInfoTv, descriptionTv;
        private ImageView imageIv;

        public HolderPage(@NonNull View itemView) {
            super(itemView);

            //init UI Views
            titleTv = itemView.findViewById(R.id.titleTv);
            publishInfoTv = itemView.findViewById(R.id.publishInfoTv);
            imageIv = itemView.findViewById(R.id.imageIv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);

        }
    }
}
