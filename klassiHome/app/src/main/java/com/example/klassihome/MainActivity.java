package com.example.klassihome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

/*
*  first create a new android studio project
* change Java version to 11 or newer (from build gradle)
* from file click project structure -> SDK location -> gradle setting -> 11_version
*
* Enable view Binding() in the gradle add above buildType
* buildFeatures{ viewBinding = true}
*
* Implement ExoPlayer (implementation 'com.google.android.exoplayer:exoplayer:2.17.1')
*
* Add internet permission in manifest file
* set android:usesCleartextTraffic="true" to true, in manifest inside <application below android:supportsRtl="true"
*
* Remove AppBar(themes) "Theme.MaterialComponents.Light.NoActionBar" and theme2 "Theme.MaterialComponents.NoActionBar"
*
* Add ViewPager2 in xml layout
*
* create xml for video view item (RelativeLayout)
*
* create video kotlin class
*
* create videoAdapter kotlin class
*
* ============================ video fetching ===============
* "Big Buck Bunny",
* "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
*"Elephant Dream",
*  "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
*"For Bigger Blazes",
*"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
*
 * */