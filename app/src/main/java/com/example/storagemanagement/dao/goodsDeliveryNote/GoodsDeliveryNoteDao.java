package com.example.storagemanagement.dao.goodsDeliveryNote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storagemanagement.helper.DBHelper;
import com.example.storagemanagement.model.GoodsDeliveryNote;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class GoodsDeliveryNoteDao implements IGoodsDeliveryNoteDao {
    private DBHelper dbHelper;

    public GoodsDeliveryNoteDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public GoodsDeliveryNote insert(GoodsDeliveryNote goodsDeliveryNote) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GOODS_DELIVERY_NOTE_ID, goodsDeliveryNote.getGoodsDeliveryNoteId());
        contentValues.put(DATE, goodsDeliveryNote.getDate());
        contentValues.put(CUSTOMER_ID, goodsDeliveryNote.getCustomerId());
        contentValues.put(WAREHOUSE_ID, goodsDeliveryNote.getWareHouseId());
        contentValues.put(EMPLOYEE_ID, goodsDeliveryNote.getEmployeeId());
        contentValues.put(NOTICE, goodsDeliveryNote.getNotice());
        long result = sqLiteDatabase.insert(TABLE_GOODS_DELIVERY_NOTE, null, contentValues);
        return result != -1 ? goodsDeliveryNote : null;
    }

    @Override
    public GoodsDeliveryNote findById(int id) {
        GoodsDeliveryNote goodsDeliveryNote = new GoodsDeliveryNote();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_GOODS_DELIVERY_NOTE).append(" ").append(WHERE).append(" ").append(ID).append("=").append(id);
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String goodsDeliveryNoteId = cursor.getString(cursor.getColumnIndex(GOODS_DELIVERY_NOTE_ID));
            String date = cursor.getString(cursor.getColumnIndex(DATE));
            String customerId = cursor.getString(cursor.getColumnIndex(CUSTOMER_ID));
            String warehouseId = cursor.getString(cursor.getColumnIndex(WAREHOUSE_ID));
            String employeeId = cursor.getString(cursor.getColumnIndex(EMPLOYEE_ID));
            String notice = cursor.getString(cursor.getColumnIndex(NOTICE));
            goodsDeliveryNote = new GoodsDeliveryNote(goodsDeliveryNoteId, date, customerId, warehouseId, employeeId, notice);
            cursor.moveToNext();
        }
        cursor.close();
        return goodsDeliveryNote;
    }

    @Override
    public List<GoodsDeliveryNote> findAll() {
        List<GoodsDeliveryNote> goodsDeliveryNotes = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_GOODS_DELIVERY_NOTE);
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String goodsDeliveryNoteId = cursor.getString(cursor.getColumnIndex(GOODS_DELIVERY_NOTE_ID));
            String date = cursor.getString(cursor.getColumnIndex(DATE));
            String customerId = cursor.getString(cursor.getColumnIndex(CUSTOMER_ID));
            String warehouseId = cursor.getString(cursor.getColumnIndex(WAREHOUSE_ID));
            String employeeId = cursor.getString(cursor.getColumnIndex(EMPLOYEE_ID));
            String notice = cursor.getString(cursor.getColumnIndex(NOTICE));
            goodsDeliveryNotes.add(new GoodsDeliveryNote(id, goodsDeliveryNoteId, date,
                    customerId, warehouseId, employeeId, notice));
            cursor.moveToNext();
        }
        cursor.close();
        return goodsDeliveryNotes;
    }

    @Override
    public boolean removeById(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        int result = sqLiteDatabase.delete(TABLE_GOODS_DELIVERY_NOTE, whereClause.toString(), arguments);
        return result != 0;
    }

    @Override
    public boolean updateById(int id, GoodsDeliveryNote goodsDeliveryNote) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        ContentValues contentValues = new ContentValues();
        contentValues.put(GOODS_DELIVERY_NOTE_ID, goodsDeliveryNote.getGoodsDeliveryNoteId());
        contentValues.put(DATE, goodsDeliveryNote.getDate());
        contentValues.put(CUSTOMER_ID, goodsDeliveryNote.getCustomerId());
        contentValues.put(WAREHOUSE_ID, goodsDeliveryNote.getWareHouseId());
        contentValues.put(EMPLOYEE_ID, goodsDeliveryNote.getEmployeeId());
        contentValues.put(NOTICE, goodsDeliveryNote.getNotice());
        int result = sqLiteDatabase.update(TABLE_GOODS_DELIVERY_NOTE, contentValues,
                whereClause.toString(), arguments);
        return result != 0;
    }
}
