package com.abhishesh.projects.medicine.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.abhishesh.projects.medicine.model.Item;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Abhishesh on 17/10/15.
 */
public class DatabaseController {

    private DatabaseHelper mHelper;
    private SQLiteDatabase db;
    private static DatabaseController sInstance;

    private DatabaseController(Context context) {
        mHelper = DatabaseHelper.getInstance(context);
    }

    public static DatabaseController getInstance(Context context) {
        if(sInstance == null) {
            sInstance = new DatabaseController(context);
        }
        return sInstance;
    }
    /**
     * Method to insert item in item db
     * @param item object which is to be inserted in db
     * @return object id of inserted item(inserted row number), -1 if error in insertion operation
     */
    public long insertItem(Item item) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContracts.ItemEntry.COLUMN_NAME_BRAND, item.getBrand());
        values.put(DatabaseContracts.ItemEntry.COLUMN_NAME_DRUGS_PACK_SIZE, item.getDrugsPackSize());
        values.put(DatabaseContracts.ItemEntry.COLUMN_NAME_ITEM_ID, item.getId());
        values.put(DatabaseContracts.ItemEntry.COLUMN_NAME_LABEL, item.getLabel());
        values.put(DatabaseContracts.ItemEntry.COLUMN_NAME_MANUFACTURER, item.getManufacturer());
        values.put(DatabaseContracts.ItemEntry.COLUMN_NAME_MRP, item.getMrp());
        values.put(DatabaseContracts.ItemEntry.COLUMN_NAME_PACK_FORM, item.getPackForm());
        values.put(DatabaseContracts.ItemEntry.COLUMN_NAME_P_FORM, item.getpForm());
        values.put(DatabaseContracts.ItemEntry.COLUMN_NAME_TYPE, item.getType());
        db = mHelper.getWritableDatabase();
        return db.insert(DatabaseContracts.ItemEntry.TABLE_NAME, null, values);
    }

    /**
     * Retrives item corresponding to given id
     * Note : id refer heres inserted row number
     */
    public Item getItem(long _id) {
        db = mHelper.getReadableDatabase();
        String[] selectClause = null;
        String selection = DatabaseContracts.ItemEntry._ID + " =? ";
        String[] args = {String.valueOf(_id)};
        Cursor cursor = db.query(DatabaseContracts.ItemEntry.TABLE_NAME, selectClause, selection, args, null, null, null);
        Item item = null;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_ITEM_ID));
                String label = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_LABEL));
                String brand = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_BRAND));
                String type = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_TYPE));
                String drugsPackSize = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_DRUGS_PACK_SIZE));
                String manufacturer = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_MANUFACTURER));
                float mrp = cursor.getFloat(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_MRP));
                String packForm = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_PACK_FORM));
                String pForm = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_P_FORM));
                item = new Item(id, label, brand, type, drugsPackSize, manufacturer, mrp, packForm, pForm);
            }
            cursor.close();
        }
        return item;
    }

    /**
     * Lists all items from database
     * @return
     */
    public List<Item> getAllItems() {
        List<Item> itemList = null;
        db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseContracts.ItemEntry.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            itemList = fetchItem(cursor);
            cursor.close();
        }
        return itemList;
    }

    /**
     * helper method to retrieve item from database
     */
    private List<Item> fetchItem(Cursor cursor) {
        List<Item> itemList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_ITEM_ID));
                String label = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_LABEL));
                String brand = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_BRAND));
                String type = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_TYPE));
                String drugsPackSize = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_DRUGS_PACK_SIZE));
                String manufacturer = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_MANUFACTURER));
                float mrp = cursor.getFloat(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_MRP));
                String packForm = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_PACK_FORM));
                String pForm = cursor.getString(cursor.getColumnIndex(DatabaseContracts.ItemEntry.COLUMN_NAME_P_FORM));
                Item item = new Item(id, label, brand, type, drugsPackSize, manufacturer, mrp, packForm, pForm);
                itemList.add(item);
            }
        }
        return itemList;
    }
}
