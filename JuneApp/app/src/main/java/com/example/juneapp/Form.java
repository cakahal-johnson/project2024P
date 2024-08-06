package com.example.juneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Form extends AppCompatActivity {

    Button submit;
    EditText username, password;
    Spinner spinner;
    CheckBox eating, sleeping, coding;
    RadioGroup radio_button_group;
    SeekBar seekBar;
    TextView range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //Button section
        submit = findViewById(R.id.submit);

        // Edit Text section
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        spinner = findViewById(R.id.spinner);

        // Checkbox section
        eating = findViewById(R.id.eating);
        sleeping = findViewById(R.id.sleeping);
        coding = findViewById(R.id.coding);

        // Radio Group Section
        radio_button_group = findViewById(R.id.radio_btn);

        //SeeBar
        seekBar = findViewById(R.id.seekbar);
        range = findViewById(R.id.range);


        //Spinner Option selection
        String[] options = {"Html", "css", "bootstrap", "javascript"};
        ArrayAdapter adapter = new ArrayAdapter(Form.this, android.R.layout.simple_spinner_item, options);

        spinner.setAdapter(adapter);

        // here we work on text section
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();
                if (usernameTxt.trim().length() < 3) {
                    Toast.makeText(Form.this,
                            "Username must be greater than 4 character", Toast.LENGTH_SHORT).show();

                }else if (passwordTxt.trim().length() < 6) {
                    Toast.makeText(Form.this, "Opps! not upto 6 characters", Toast.LENGTH_SHORT).show();
                }else{
                    String result = "Success! \n";
                    result += "Username "+usernameTxt+ " \n";
                    result += "password "+passwordTxt+ " \n";

                    Toast.makeText(Form.this, result, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Radio Group
        radio_button_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.male) {
                    Toast.makeText(getApplicationContext(), "You are a male", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(), "You are female", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // To console log
        Log.i("myId", String.valueOf(R.id.male));

        // Checkbox Section
        eating.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    Toast.makeText(getApplicationContext(), "You checked eating", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(), "You checked eating", Toast.LENGTH_SHORT).show();
                }

            }
        });

        sleeping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    Toast.makeText(getApplicationContext(), "You checked sleeping", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(), "You checked sleeping", Toast.LENGTH_SHORT).show();
                }
            }
        });

        coding.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    Toast.makeText(getApplicationContext(), "You checked coding", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(), "You checked coding", Toast.LENGTH_SHORT).show();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                range.setText(String.valueOf(i));
            }



            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}