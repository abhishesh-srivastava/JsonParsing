package com.abhishesh.projects.medicine.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abhishesh on 16/10/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "OfflineStore";
    public static int DATABASE_VERSION = 1;
    private static DatabaseHelper sInstance;

    /**
     * Helper needs to be synchronized as multiple threads may access same instance at a time
     */
    public static synchronized DatabaseHelper getInstance(Context context)
    {
        if (sInstance == null)
            sInstance = new DatabaseHelper(context);
        return sInstance;
    }

    // Private constructor So that database instance can be obtained only via getInstance method
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        // create table
        sql = "CREATE TABLE IF NOT EXISTS " + DatabaseContracts.ItemEntry.TABLE_NAME + " (" +
                DatabaseContracts.ItemEntry.COLUMN_NAME_ITEM_ID + " INTEGER PRIMARY KEY DEFAULT 0, " +
                DatabaseContracts.ItemEntry.COLUMN_NAME_BRAND + " TEXT, " +
                DatabaseContracts.ItemEntry.COLUMN_NAME_DRUGS_PACK_SIZE + " TEXT, " +
                DatabaseContracts.ItemEntry.COLUMN_NAME_LABEL + " TEXT, " +
                DatabaseContracts.ItemEntry.COLUMN_NAME_MANUFACTURER + " TEXT, " +
                DatabaseContracts.ItemEntry.COLUMN_NAME_MRP + " REAL, " +
                DatabaseContracts.ItemEntry.COLUMN_NAME_PACK_FORM + " TEXT, " +
                DatabaseContracts.ItemEntry.COLUMN_NAME_P_FORM + " TEXT " +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion) {
            String sql;
            sql = "DROP TABLE " + DatabaseContracts.ItemEntry.TABLE_NAME + ";";
            db.execSQL(sql);
            onCreate(db);
        }
    }
}
