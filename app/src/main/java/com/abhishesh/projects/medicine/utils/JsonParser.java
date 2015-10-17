package com.abhishesh.projects.medicine.utils;

import com.abhishesh.projects.medicine.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishesh on 17/10/15.
 */
public class JsonParser {

    public static List<Item> parse(String data) {
        List<Item> itemList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray array = jsonObject.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                String temp = null;
                String id = null;
                String label = null;
                String brand = null;
                String type = null;
                String drugsPackSize = null;
                String manufacturer = null;
                float mrp = 0.0f;
                String packForm = null;
                String pForm = null;
                /*{"id":"000010","label":"1 2 3 50MG TAB","brand":"1 2 3 50MG TAB","type":"MEDICINE",
                "drugspacksize":"STRIPS","manufacturer":"AGLOWMED LIMITED","mrp":"0.00",
                "packform":"STRIPS","pform":"STRIPS"}*/
                id = itemObject.getString("id");
                label = itemObject.getString("label");
                brand = itemObject.getString("brand");
                type = itemObject.getString("type");
                drugsPackSize = itemObject.getString("drugspacksize");
                manufacturer = itemObject.getString("manufacturer");
                mrp = Float.valueOf(itemObject.getString("mrp"));
                packForm = itemObject.getString("packform");
                pForm = itemObject.getString("pform");
                itemList.add(new Item(id, label, brand, type, drugsPackSize, manufacturer, mrp, packForm, pForm));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
