 package com.example.splashprog;

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
                        wait(3000);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent i = new Intent(MainActivity.this,Home.class);
                    startActivity(i);
                    finish();
                }
            }

        };
        timer.start();
    }
}