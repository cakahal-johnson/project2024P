package com.example.verificationsys;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private WebView webView;
    private ValueCallback<Uri[]> uploadMessage;
    private final static int FILE_CHOOSER_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            // For 5.0+ devices
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                Log.d(TAG, "onShowFileChooser: Called");
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }
                uploadMessage = filePathCallback;

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(Intent.createChooser(intent, "File Chooser"), FILE_CHOOSER_RESULT_CODE);

                return true;
            }
        });

        webView.setDownloadListener((url, userAgent, contentDisposition, mimeType, contentLength) -> {
            Log.d(TAG, "onDownloadStart: URL = " + url);
            if (url.startsWith("http://") || url.startsWith("https://")) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setMimeType(mimeType);
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading file...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));

                try {
                    DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    dm.enqueue(request);
                    Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.e(TAG, "onDownloadStart: Error downloading file", e);
                    Toast.makeText(getApplicationContext(), "Error downloading file", Toast.LENGTH_LONG).show();
                }
            } else if (url.startsWith("data:")) {
                try {
                    String base64Data = url.split(",")[1];
                    byte[] fileData = Base64.decode(base64Data, Base64.DEFAULT);

//                    String fileName = URLUtil.guessFileName(url, contentDisposition, mimeType);
                    //String fileName = "downloaded_file.png"; // Set appropriate file name
                    String fileName = "CCU_Certificate.png"; // Set appropriate file name
                    File downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File file = new File(downloadFolder, fileName);

                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(fileData);
                    fos.flush();
                    fos.close();

                    // Make file visible in Downloads app
                    MediaScannerConnection.scanFile(getApplicationContext(), new String[]{file.getAbsolutePath()}, null, null);

                    Toast.makeText(getApplicationContext(), "File saved: " + fileName, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.e(TAG, "onDownloadStart: Error saving file", e);
                    Toast.makeText(getApplicationContext(), "Error saving file", Toast.LENGTH_LONG).show();
                }
            } else {
                Log.e(TAG, "onDownloadStart: Unsupported URL scheme");
                Toast.makeText(getApplicationContext(), "Cannot download this file type", Toast.LENGTH_LONG).show();
            }
        });



        Button generateButton = findViewById(R.id.generateButton);
        Button verifyButton = findViewById(R.id.verifyButton);

        generateButton.setOnClickListener(v -> {
            Log.d(TAG, "Generate button clicked");
            webView.loadUrl("file:///android_asset/index.html");
        });
        verifyButton.setOnClickListener(v -> {
            Log.d(TAG, "Verify button clicked");
            webView.loadUrl("file:///android_asset/verify.html");
        });

        // Load the default page
        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            Log.d(TAG, "onActivityResult: Called with requestCode = " + requestCode + ", resultCode = " + resultCode);
            if (uploadMessage == null) return;
            uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
            uploadMessage = null;
        }
    }
}
// till 4:21Am 9th july 2024 Log