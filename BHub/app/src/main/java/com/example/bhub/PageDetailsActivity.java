package com.example.bhub;

import static javax.xml.transform.OutputKeys.ENCODING;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PageDetailsActivity extends AppCompatActivity {

    private TextView titleTV, publishInfoTv;
    private WebView webView;

    //that we passed in intent from adapterPage
    private String pageId;

    //actionBar
    private ActionBar actionBar;

    private static final String TAG = "PageDetails_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_details);

        //init actionbar
        actionBar = getSupportActionBar();
        actionBar.setTitle("Cakahal Blog");
        actionBar.setSubtitle("Page Details");
        //back button
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        //init UI Views
        titleTV = findViewById(R.id.titleTv);
        publishInfoTv = findViewById(R.id.publishInfoTv);
        webView = findViewById(R.id.webView);


        //get page id that we passed in intent from adapterPage
        pageId = getIntent().getStringExtra("pageId");
        Log.d(TAG, "onCreate: PageId: "+pageId);

        loadPageDetails();

    }

    private void loadPageDetails() {
        String url = "https://www.googleapis.com/blogger/v3/blogs/"+Constants.BLOG_ID
                +"/posts/"+pageId
                +"?key="+Constants.API_KEY;

        Log.d(TAG, "loadPostDetails: URL"+url);

        //API request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //got api response
                Log.d(TAG, "onResponse: "+response);

                //response is in JSON Object form
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    //get data
                    String title = jsonObject.getString("title");
                    String published = jsonObject.getString("published");
                    String content = jsonObject.getString("content");
                    String url = jsonObject.getString("url");
                    String id = jsonObject.getString("id");
                    String displayName = jsonObject.getJSONObject("author").getString("displayName");

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

                    //set data
                    titleTV.setText(title);
                    publishInfoTv.setText("By "+displayName+" "+formattedDate); //by cakahal johnson 6/1/2023 03:09 AM
                    //load content in webview as it is in web/html form
                    webView.loadDataWithBaseURL(null, content, "text/html", ENCODING, null);


                }catch (Exception e){
                    Log.d(TAG, "onResponse: "+e.getMessage());
                    Toast.makeText(PageDetailsActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //failed getting api response
                Log.d(TAG, "onResponse: "+error.getMessage());
                Toast.makeText(PageDetailsActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        //add request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); //goto previous activity, when back button of actionBar clicked
        return super.onSupportNavigateUp();
    }
}