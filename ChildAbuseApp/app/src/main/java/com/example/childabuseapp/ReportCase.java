package com.example.childabuseapp;

import android.Manifest;  // Ensure this is imported
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportCase extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 100;

    private EditText editTextCaseDetails;
    private Button buttonSelectImage;
    private Button buttonSubmitCase;
    private ImageView imageViewSelected;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_case);


        editTextCaseDetails = findViewById(R.id.editTextCaseDetails);
        buttonSelectImage = findViewById(R.id.buttonSelectImage);
        buttonSubmitCase = findViewById(R.id.buttonSubmitCase);
        imageViewSelected = findViewById(R.id.imageViewSelected);

        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(ReportCase.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ReportCase.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    } else {
                        openImageSelector();
                    }
                } else {
                    openImageSelector();
                }
            }
        });

        buttonSubmitCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitCase();
            }
        });
    }

    private void openImageSelector() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imageViewSelected.setImageBitmap(bitmap);
                imageViewSelected.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void submitCase() {
        String caseDetails = editTextCaseDetails.getText().toString().trim();

        if (TextUtils.isEmpty(caseDetails)) {
            Toast.makeText(this, "Please enter case details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simulate a case reporting process (replace this with actual submission logic)
        Toast.makeText(this, "Case reported successfully", Toast.LENGTH_SHORT).show();

        // Prepare data to send back to the dashboard
        Intent resultIntent = new Intent();
        resultIntent.putExtra("reportedCase", caseDetails);
        resultIntent.putExtra("caseImageUri", selectedImageUri != null ? selectedImageUri.toString() : null);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImageSelector();
            } else {
                Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
            }
        }
    }
}