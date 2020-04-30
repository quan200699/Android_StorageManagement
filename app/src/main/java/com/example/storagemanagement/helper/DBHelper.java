package com.example.storagemanagement.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.storagemanagement.config.StaticVariable.*;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_PRODUCT);
            db.execSQL(CREATE_TABLE_CUSTOMER);
            db.execSQL(CREATE_TABLE_SUPPLIER);
            db.execSQL(CREATE_TABLE_EMPLOYEE);
            db.execSQL(CREATE_TABLE_WAREHOUSE);
            db.execSQL(CREATE_TABLE_GOODS_DELIVERY_NOTE);
            db.execSQL(CREATE_TABLE_GOODS_DELIVERY_NOTE_DETAIL);
            db.execSQL(CREATE_TABLE_GOODS_RECEIVED_NOTE);
        } catch (Exception e) {
            Log.e("TEXT", "" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE_PRODUCT);
            db.execSQL(DROP_TABLE_CUSTOMER);
            db.execSQL(DROP_TABLE_SUPPLIER);
            db.execSQL(DROP_TABLE_EMPLOYEE);
            db.execSQL(DROP_TABLE_WAREHOUSE);
            db.execSQL(DROP_TABLE_GOODS_DELIVERY_NOTE);
            db.execSQL(DROP_TABLE_GOODS_DELIVERY_NOTE_DETAIL);
            db.execSQL(DROP_TABLE_GOODS_RECEIVED_NOTE);
            onCreate(db);
        } catch (Exception e) {
            Log.e("TEXT", "" + e);
        }
    }
}
