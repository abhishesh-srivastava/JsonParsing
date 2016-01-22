package com.abhishesh.projects.medicine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.abhishesh.projects.medicine.ui.GridFragment;
import com.abhishesh.projects.medicine.ui.MainActivity;
import com.abhishesh.projects.medicine.utils.GlobalConstants;
import com.abhishesh.projects.medicine.utils.Utils;

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
	//Test
    private ProgressBar progressBar;
    private Handler mHandler = new Handler();
    private boolean isDataAvailable = false;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_layout);
        // Declare a progressbar that appears in the middle of the splash screen.
        progressBar = (ProgressBar) findViewById(R.id.progress);
        isDataAvailable = getSharedPreferences(GlobalConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getBoolean(GlobalConstants.SHARED_PREF_FIELD_LOADED, false);
    }

    @Override
    public void onStart() {
        // When the aplication starts, show the progressbar for 500 milliseconds. After that, execute loadHomeActivity runnable.
        long mStartTime = 0;
        if (mStartTime == 0L) {
            mStartTime = System.currentTimeMillis();
            mHandler.removeCallbacks(loadHomeActivity);
            progressBar.setVisibility(ProgressBar.VISIBLE);
            progressBar.setProgress(0);
            mHandler.postDelayed(loadHomeActivity, 500);
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
            if(!Utils.isNetworkAvailable(SplashActivity.this) && !isDataAvailable) {
                Toast.makeText(SplashActivity.this,"Internet connection not found",Toast.LENGTH_SHORT).show();
            } else {
                Intent intenthome = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intenthome);
            }
            finish();
        }
    };
}