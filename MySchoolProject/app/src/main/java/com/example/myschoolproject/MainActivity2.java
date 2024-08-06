package com.example.myschoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private String selectTopicName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final LinearLayout phys = findViewById(R.id.phyLayout);
        final LinearLayout chem = findViewById(R.id.chemLayout);
        final LinearLayout maths = findViewById(R.id.mathsLayout);
        final LinearLayout eng = findViewById(R.id.engLayout);

        final Button startBtn = findViewById(R.id.startQuizBtn);

        final Button exitQuizBtn = findViewById(R.id.exitQuizBtn);

        exitQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivityMenu.class);
                startActivity(intent);
            }
        });

        phys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTopicName = "phys";

                phys.setBackgroundResource(R.drawable.round_back_white_stroke10);

                chem.setBackgroundResource(R.drawable.round_back_white10);
                maths.setBackgroundResource(R.drawable.round_back_white10);
                eng.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTopicName = "chem";

                chem.setBackgroundResource(R.drawable.round_back_white_stroke10);

                phys.setBackgroundResource(R.drawable.round_back_white10);
                maths.setBackgroundResource(R.drawable.round_back_white10);
                eng.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTopicName = "maths";

                maths.setBackgroundResource(R.drawable.round_back_white_stroke10);

                chem.setBackgroundResource(R.drawable.round_back_white10);
                phys.setBackgroundResource(R.drawable.round_back_white10);
                eng.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTopicName = "eng";

                eng.setBackgroundResource(R.drawable.round_back_white_stroke10);

                chem.setBackgroundResource(R.drawable.round_back_white10);
                maths.setBackgroundResource(R.drawable.round_back_white10);
                phys.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectTopicName.isEmpty()){
                    Toast.makeText(MainActivity2.this, "Please select the Topic", Toast.LENGTH_SHORT).show();

                }else{

                    Intent intent = new Intent(MainActivity2.this, QuizActivity.class);
                    intent.putExtra("selectedTopic", selectTopicName);
                    startActivity(intent);
                }
            }
        });
    }
}