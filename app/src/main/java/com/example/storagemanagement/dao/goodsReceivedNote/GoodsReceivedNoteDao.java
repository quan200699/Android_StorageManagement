package com.example.storagemanagement.dao.goodsReceivedNote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storagemanagement.helper.DBHelper;
import com.example.storagemanagement.model.GoodsReceivedNote;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class GoodsReceivedNoteDao implements IGoodsReceivedNoteDao {
    private DBHelper dbHelper;

    public GoodsReceivedNoteDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public GoodsReceivedNote insert(GoodsReceivedNote goodsReceivedNote) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GOODS_RECEIVED_NOTE_ID, goodsReceivedNote.getGoodsReceivedNoteId());
        contentValues.put(DATE, goodsReceivedNote.getDate());
        contentValues.put(SUPPLIER_ID, goodsReceivedNote.getSupplierId());
        contentValues.put(WAREHOUSE_ID, goodsReceivedNote.getWarehouseId());
        contentValues.put(EMPLOYEE_ID, goodsReceivedNote.getEmployeeId());
        contentValues.put(NOTICE, goodsReceivedNote.getNotice());
        long result = sqLiteDatabase.insert(TABLE_GOODS_RECEIVED_NOTE, null, contentValues);
        return result != -1 ? goodsReceivedNote : null;
    }

    @Override
    public GoodsReceivedNote findById(int id) {
        GoodsReceivedNote goodsReceivedNote = new GoodsReceivedNote();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_GOODS_RECEIVED_NOTE).append(" ").append(WHERE).append(" ").append(ID).append("=").append(id);
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String goodsReceivedNoteId = cursor.getString(cursor.getColumnIndex(GOODS_DELIVERY_NOTE_ID));
            String date = cursor.getString(cursor.getColumnIndex(DATE));
            String supplierId = cursor.getString(cursor.getColumnIndex(SUPPLIER_ID));
            String warehouseId = cursor.getString(cursor.getColumnIndex(WAREHOUSE_ID));
            String employeeId = cursor.getString(cursor.getColumnIndex(EMPLOYEE_ID));
            String notice = cursor.getString(cursor.getColumnIndex(NOTICE));
            goodsReceivedNote = new GoodsReceivedNote(goodsReceivedNoteId, date, supplierId, warehouseId, employeeId, notice);
            cursor.moveToNext();
        }
        cursor.close();
        return goodsReceivedNote;
    }

    @Override
    public List<GoodsReceivedNote> findAll() {
        List<GoodsReceivedNote> goodsReceivedNotes = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_GOODS_RECEIVED_NOTE);
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String goodsReceivedNoteId = cursor.getString(cursor.getColumnIndex(GOODS_RECEIVED_NOTE_ID));
            String date = cursor.getString(cursor.getColumnIndex(DATE));
            String supplierId = cursor.getString(cursor.getColumnIndex(SUPPLIER_ID));
            String warehouseId = cursor.getString(cursor.getColumnIndex(WAREHOUSE_ID));
            String employeeId = cursor.getString(cursor.getColumnIndex(EMPLOYEE_ID));
            String notice = cursor.getString(cursor.getColumnIndex(NOTICE));
            goodsReceivedNotes.add(new GoodsReceivedNote(id, goodsReceivedNoteId, date,
                    supplierId, warehouseId, employeeId, notice));
            cursor.moveToNext();
        }
        cursor.close();
        return goodsReceivedNotes;
    }

    @Override
    public boolean removeById(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        int result = sqLiteDatabase.delete(TABLE_GOODS_RECEIVED_NOTE, whereClause.toString(), arguments);
        return result != 0;
    }

    @Override
    public boolean updateById(int id, GoodsReceivedNote goodsReceivedNote) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        ContentValues contentValues = new ContentValues();
        contentValues.put(GOODS_RECEIVED_NOTE_ID, goodsReceivedNote.getGoodsReceivedNoteId());
        contentValues.put(DATE, goodsReceivedNote.getDate());
        contentValues.put(SUPPLIER_ID, goodsReceivedNote.getSupplierId());
        contentValues.put(WAREHOUSE_ID, goodsReceivedNote.getWarehouseId());
        contentValues.put(EMPLOYEE_ID, goodsReceivedNote.getEmployeeId());
        contentValues.put(NOTICE, goodsReceivedNote.getNotice());
        int result = sqLiteDatabase.update(TABLE_GOODS_RECEIVED_NOTE, contentValues,
                whereClause.toString(), arguments);
        return result != 0;
    }
}
