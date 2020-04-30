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
        } catch (Exception e) {
            Log.e("TEXT", "" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE_PRODUCT);
            db.execSQL(DROP_TABLE_PRODUCT);
            onCreate(db);
        } catch (Exception e) {
            Log.e("TEXT", "" + e);
        }
    }
}
