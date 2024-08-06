package com.example.cakahalblog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

public class blog extends AppCompatActivity {

    //UI Views
    private RecyclerView postsRv;
    private Button loadMoreBtn;

    private String url = "https://www.googleapis.com/blogger/v3/blogs/929219335939963238/posts?key=AIzaSyAt0yZIpUc-Z8TjfkdT_Rg17_4k44Vami0";  //complete url for retrieving posts
    private String nextToken = "CgkIChiAkceVjiYQ0b2SAQ"; //next page token to load more posts

    private ArrayList<ModelPost> postArrayList;
    private AdapterPost adapterPost;

    private ProgressDialog progressDialog;

    private static final String TAG = "MAIN_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        //initializing UI Views
        postsRv = findViewById(R.id.postRv);
        loadMoreBtn = findViewById(R.id.loadMoreBtn);

        //setting progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");

        //initializing and clear list before adding data into it
        postArrayList = new ArrayList<>();
        postArrayList.clear();

        loadPosts();

        //load more button click
        loadMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPosts();
            }
        });

    }

    private void loadPosts() {
        progressDialog.show();

        if (nextToken.equals("")){
            Log.d(TAG, "loadPosts: Next Page token is empty, no more posts");
            url = "https://www.googleapis.com/blogger/v3/blogs/"
                    +Constants.Blog_ID
                    +"/posts?maxResults="+Constants.Max_POST_RESULTS
                    +" key="+Constants.Api_Key;
        }else if (nextToken.equals("end")){
            Log.d(TAG, "loadPosts: Next Token is empty/end, no more posts");
            Toast.makeText(this,"No more posts...", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }else {
            Log.d(TAG, "loadPosts: Next token: "+nextToken);
            url = "https://www.googleapis.com/blogger/v3/blogs/"
                    +Constants.Blog_ID
                    +"/posts?maxResults="+Constants.Max_POST_RESULTS
                    +"&pageToken=" +nextToken
                    +" key="+Constants.Api_Key;
        }
        Log.d(TAG, "loadPosts: URL: "+url);

        //Request data, Method is Get not PoSt
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //we got response, so dismiss dialog first
                progressDialog.dismiss();
                Log.d(TAG, "onResponse: " + response);

                /*json data is in response parameter of this function, it may cause exception while parsing / formatting so do it in a try / catch*/
                try {
                    //response is in json object
                    JSONObject jsonObject = new JSONObject(response);

                    try {
                        nextToken = jsonObject.getString("nextPageToken");
                        Log.d(TAG, "onResponse: NextPageToken: " + nextToken);
                    } catch (Exception e) {
                        Toast.makeText(blog.this, "Reached end of page...", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse: Reached end of page...: " + e.getMessage());
                        nextToken = "end";

                    }
                    //get json array data from json
                    JSONArray jsonArray = jsonObject.getJSONArray("items");

                    //continue getting data while its completed
                    for (int i = 0; i <= jsonArray.length(); i++){
                        try {
                            //get data
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String id = jsonObject1.getString("id");
                            String title = jsonObject1.getString("title");
                            String content = jsonObject1.getString("content");
                            String published = jsonObject1.getString("published");
                            String updated = jsonObject1.getString("updated");
                            String url = jsonObject1.getString("url");
                            String selfLink = jsonObject1.getString("selfLink");
                            String authorName = jsonObject1.getJSONObject("author").getString("displayName");
                            String image = jsonObject1.getJSONObject("author").getString("image");

                            //set data
                            ModelPost modelPost = new ModelPost(""+authorName,
                                    ""+content,
                                    ""+id,
                                    ""+published,
                                    ""+selfLink,
                                    ""+title,
                                    ""+updated,
                                    ""+url
                                    );

                            //add data/model to list
                            postArrayList.add(modelPost);

                        }catch (Exception e){
                            Log.d(TAG, "onResponse: " +e.getMessage());
                            Toast.makeText(blog.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    //setup adapter
                    adapterPost = new AdapterPost(blog.this, postArrayList);
                    //set adapter to recyclerview
                    postsRv.setAdapter(adapterPost);
                    progressDialog.dismiss();

                }catch (Exception e){
                    Log.d(TAG, "onResponse: 2: " +e.getMessage());
                    Toast.makeText(blog.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.getMessage());
                Toast.makeText(blog.this,""+error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        //add request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}