package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declare the UI elements
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private RadioGroup genderRadioGroup;
    private Spinner courseSpinner;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI elements
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        genderRadioGroup = findViewById(R.id.gender_radio_group);
        courseSpinner = findViewById(R.id.course_spinner);
        submitButton = findViewById(R.id.submit_button);

        // Set a click listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values from the UI elements
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                int genderId = genderRadioGroup.getCheckedRadioButtonId();
                String gender = "";
                if (genderId != -1) {
                    // Find the selected radio button and get its text
                    RadioButton genderRadioButton = findViewById(genderId);
                    gender = genderRadioButton.getText().toString();
                }
                String course = courseSpinner.getSelectedItem().toString();

                // Validate the input values
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || gender.isEmpty()) {
                    // Show a toast message if any field is empty
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Perform the CRUD operation here
                    // For example, insert the data into a database or send it to a server
//                    ========================= Dashboard ============================
                    // Get input values from UI elements
                    // For example:
                    String nameD = nameEditText.getText().toString();
                    String courseD = courseSpinner.getSelectedItem().toString();

                    // Create intent to start dashboard activity
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, DashboardActivity.class);

                    // Pass data to intent
                    intent.putExtra("name", nameD);
                    intent.putExtra("course", courseD);

                    // Start dashboard activity with intent
                    startActivity(intent);
//                    ==========================DashBoard ============================
                    // Show a toast message to confirm the operation
                    Toast.makeText(MainActivity.this, "Data submitted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}