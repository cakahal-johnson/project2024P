package com.example.firebaseblog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {
    HomeFragment homeFragment = new HomeFragment();
    MyPostFragment myPostFragment = new MyPostFragment();
    PersonFragment personFragment = new PersonFragment();
    FrameLayout frameLayout;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        frameLayout = findViewById(R.id.container);
        navigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = new HomeFragment();

                if (item.getItemId() == R.id.home){
                    fragment = new HomeFragment();
                    item.setChecked(true);
                }else if (item.getItemId() == R.id.list){
                    fragment = new ListFragment();

                    item.setChecked(true);
                }else if (item.getItemId() == R.id.me){
                    fragment = new MyPostFragment();
                    item.setChecked(true);

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                return false;
            }
        });
    }
}