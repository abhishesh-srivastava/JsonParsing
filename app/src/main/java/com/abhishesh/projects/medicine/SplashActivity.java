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
    private String DATABASE_PATH ="/data/com.abhishesh.projects.medicine/databases";
    public void copy() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = DATABASE_PATH;
                String backupDBPath = "BiaApp/";
                File currentDB = new File(data,currentDBPath);
                File backupDB = new File(sd, backupDBPath);
                copyDirectoryOneLocationToAnotherLocation(currentDB,backupDB);
            }
        } catch (Exception e) {
        }
    }
    public static void copyDirectoryOneLocationToAnotherLocation(File sourceLocation, File targetLocation)
            throws IOException {
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            String[] children = sourceLocation.list();
            for (int i = 0; i < sourceLocation.listFiles().length; i++) {

                copyDirectoryOneLocationToAnotherLocation(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {
            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);
            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_layout);
        // Declare a progressbar that appears in the middle of the splash screen.
        progressBar = (ProgressBar) findViewById(R.id.progress);
        copy();
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