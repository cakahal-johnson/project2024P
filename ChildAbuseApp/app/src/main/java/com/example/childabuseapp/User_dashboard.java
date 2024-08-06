package com.example.childabuseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class User_dashboard extends AppCompatActivity {

    private Button buttonReportNewCase;
    private ListView listViewCases;
    private SimpleAdapter casesAdapter;
    private ArrayList<HashMap<String, String>> casesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        buttonReportNewCase = findViewById(R.id.buttonReportNewCase);
        listViewCases = findViewById(R.id.listViewCases);

        // Initialize the list and adapter
        casesList = new ArrayList<>();
        casesAdapter = new SimpleAdapter(this, casesList, R.layout.list_item_case,
                new String[]{"caseDetails", "caseImageUri"},
                new int[]{R.id.textViewCaseDetails, R.id.imageViewCase});
        listViewCases.setAdapter(casesAdapter);

        buttonReportNewCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Report Case Activity
                Intent intent = new Intent(User_dashboard.this, ReportCase.class);
                startActivityForResult(intent, 1);
            }
        });

        // Load existing cases (this would normally be retrieved from a database or backend service)
        loadReportedCases();
    }

    private void loadReportedCases() {
        // This is just a placeholder. In a real application, you would fetch this data from a server or database.
        HashMap<String, String> case1 = new HashMap<>();
        case1.put("caseDetails", "Case 1: Details of the first reported case.");
        case1.put("caseImageUri", null);
        casesList.add(case1);

        HashMap<String, String> case2 = new HashMap<>();
        case2.put("caseDetails", "Case 2: Details of the second reported case.");
        case2.put("caseImageUri", null);
        casesList.add(case2);

        casesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String newCase = data.getStringExtra("reportedCase");
            String caseImageUri = data.getStringExtra("caseImageUri");

            if (newCase != null) {
                HashMap<String, String> newReportedCase = new HashMap<>();
                newReportedCase.put("caseDetails", newCase);
                newReportedCase.put("caseImageUri", caseImageUri);
                casesList.add(newReportedCase);
                casesAdapter.notifyDataSetChanged();
            }
        }
    }
}