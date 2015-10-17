package com.abhishesh.projects.medicine.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Abhishesh on 17/10/15.
 */
public class NetworkRequestHandler {

    public static String getRequest(String endPoint) {
        String response = "";
        try {
            URL obj = new URL(endPoint);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if(responseCode == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder builder = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    builder.append(inputLine);
                }
                response = builder.toString();
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
