package com.example.firebaseblog;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);
//        zoomIn();

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                Intent intent;
                if (auth.getCurrentUser() == null){
                    intent = new Intent(MainActivity.this, Login.class);
                }else{
                    intent = new Intent(MainActivity.this,Dashboard.class);
                }

                startActivity(intent);

                Intent i = new Intent(MainActivity.this,Register.class);
                startActivity(i);

            }
        }, 2000);
    }

//    public void zoomIn(){
//        final ValueAnimator anim = ValueAnimator.ofFloat(1f, 1.5f);
//        anim.setDuration(1000);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                image.setScaleX((Float) animation.getAnimatedValue());
//                image.setScaleY((Float) animation.getAnimatedValue());
//
//            }
//        });
//        anim.setRepeatCount(2);
//        anim.setRepeatMode(ValueAnimator.REVERSE);
//        anim.start();
//    }

}