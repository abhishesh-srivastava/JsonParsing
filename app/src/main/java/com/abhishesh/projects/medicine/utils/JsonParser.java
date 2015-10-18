package com.abhishesh.projects.medicine.utils;

import com.abhishesh.projects.medicine.db.DatabaseContracts;
import com.abhishesh.projects.medicine.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishesh on 17/10/15.
 */

/**
 * Class used for parsing response from server(json format data)
 */
public class JsonParser {

    public static List<Item> parse(String data) {
        List<Item> itemList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray array = jsonObject.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                String id = null;
                String label = null;
                String brand = null;
                String type = null;
                String drugsPackSize = null;
                String manufacturer = null;
                float mrp = 0.0f;
                String packForm = null;
                String pForm = null;
                id = itemObject.getString(DatabaseContracts.ItemEntry.COLUMN_NAME_ITEM_ID);
                label = itemObject.getString(DatabaseContracts.ItemEntry.COLUMN_NAME_LABEL);
                brand = itemObject.getString(DatabaseContracts.ItemEntry.COLUMN_NAME_LABEL);
                type = itemObject.getString(DatabaseContracts.ItemEntry.COLUMN_NAME_TYPE);
                drugsPackSize = itemObject.getString(DatabaseContracts.ItemEntry.COLUMN_NAME_DRUGS_PACK_SIZE);
                manufacturer = itemObject.getString(DatabaseContracts.ItemEntry.COLUMN_NAME_MANUFACTURER);
                mrp = Float.valueOf(itemObject.getString(DatabaseContracts.ItemEntry.COLUMN_NAME_MRP));
                packForm = itemObject.getString(DatabaseContracts.ItemEntry.COLUMN_NAME_PACK_FORM);
                pForm = itemObject.getString(DatabaseContracts.ItemEntry.COLUMN_NAME_P_FORM);
                itemList.add(new Item(id, label, brand, type, drugsPackSize, manufacturer, mrp, packForm, pForm));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
