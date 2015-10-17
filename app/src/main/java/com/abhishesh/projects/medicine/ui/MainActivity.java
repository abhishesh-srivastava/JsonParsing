package com.abhishesh.projects.medicine.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.abhishesh.projects.medicine.utils.LogUtils;

/**
 * Created by Abhishesh on 15/10/15.
 */
public class MainActivity extends FragmentActivity {

    public static final String TAG = LogUtils.makeLogTag(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, new GridFragment(), TAG);
            ft.commit();
        }
    }
}
