package com.abhishesh.projects.medicine.db;

import android.provider.BaseColumns;

/**
 * Created by Abhishesh on 16/10/15.
 */

/**
 * Class which lists all table details like column names and table name
 */
public final class DatabaseContracts {

    public DatabaseContracts() {}

    public static abstract class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "items";
        public static final String COLUMN_NAME_ITEM_ID = "id";
        public static final String COLUMN_NAME_LABEL = "label";
        public static final String COLUMN_NAME_BRAND = "brand";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_DRUGS_PACK_SIZE = "drugspacksize";
        public static final String COLUMN_NAME_MANUFACTURER = "manufacturer";
        public static final String COLUMN_NAME_MRP = "mrp";
        public static final String COLUMN_NAME_PACK_FORM = "packform";
        public static final String COLUMN_NAME_P_FORM = "pform";
    }
}
