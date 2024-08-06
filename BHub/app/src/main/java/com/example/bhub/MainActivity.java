package com.example.bhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this){
                        wait(6000);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent i = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(i);
                    finish();
                }
            }

        };
        timer.start();
    }
}