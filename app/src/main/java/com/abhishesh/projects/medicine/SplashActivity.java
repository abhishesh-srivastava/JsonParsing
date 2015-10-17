package com.abhishesh.projects.medicine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.ProgressBar;

import com.abhishesh.projects.medicine.ui.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Abhishesh on 15/10/15.
 */
public class SplashActivity extends Activity {

    private ProgressBar progressBar;
    private Handler mHandler = new Handler();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_layout);
        // Declare a progressbar that appears in the middle of the splash screen.
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    public void onStart() {
        // When the aplication starts, show the progressbar for 1 seconds. After that, execute loadHomeActivity runnable.
        long mStartTime = 0;
        if (mStartTime == 0L) {
            mStartTime = System.currentTimeMillis();
            mHandler.removeCallbacks(loadHomeActivity);
            progressBar.setVisibility(ProgressBar.VISIBLE);
            progressBar.setProgress(0);
            mHandler.postDelayed(loadHomeActivity, 1000);
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(loadHomeActivity);
    }

    // A runnable executed when the progressbar finishes which starts the HomeActivity.
    private Runnable loadHomeActivity = new Runnable() {
        public void run() {
            Intent intenthome = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intenthome);
            finish();
        }
    };
}
