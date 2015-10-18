package com.abhishesh.projects.medicine.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.abhishesh.projects.medicine.R;

/**
 * Created by Abhishesh on 18/10/15.
 */
public class Utils {

    public static int[] icons = {
            R.drawable.icon1,
            R.drawable.icon3,
            R.drawable.icon4,
            R.drawable.icon5,
            R.drawable.icon6
    };
    //check if working internet is available or not
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
