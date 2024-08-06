package com.example.juneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewClass2 extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_class2);

        webView = findViewById(R.id.webView);
        String url = "https://www.facebook.com";

        //pre-loader setting section
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading content from "+url);

        webView.loadUrl(url);

        //TO ADD A SCRIPT
        WebSettings seetings = webView.getSettings();
        seetings.setJavaScriptEnabled(true);
        seetings.setDisplayZoomControls(true);

        // rep-loading section
        webView.setWebViewClient(new WebViewClient(){
            // crt o key to override


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                pd.show();

                if (!url.startsWith(url)){
                    return;
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
    }
}