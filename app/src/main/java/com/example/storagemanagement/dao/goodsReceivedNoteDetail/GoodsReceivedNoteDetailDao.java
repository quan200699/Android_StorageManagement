package com.example.storagemanagement.dao.goodsReceivedNoteDetail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storagemanagement.helper.DBHelper;
import com.example.storagemanagement.model.GoodsReceivedNoteDetail;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class GoodsReceivedNoteDetailDao implements IGoodsReceivedNoteDetailDao {
    private DBHelper dbHelper;

    public GoodsReceivedNoteDetailDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public GoodsReceivedNoteDetail insert(GoodsReceivedNoteDetail goodsReceivedNoteDetail) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GOODS_RECEIVED_NOTE_ID, goodsReceivedNoteDetail.getGoodsReceivedNoteId());
        contentValues.put(PRODUCT_ID, goodsReceivedNoteDetail.getProductId());
        contentValues.put(QUANTITY, goodsReceivedNoteDetail.getQuantity());
        contentValues.put(PRICE, goodsReceivedNoteDetail.getPrice());
        long result = sqLiteDatabase.insert(TABLE_GOODS_RECEIVED_NOTE_DETAIL, null, contentValues);
        return result != -1 ? goodsReceivedNoteDetail : null;
    }

    @Override
    public GoodsReceivedNoteDetail findById(int id) {
        return null;
    }

    @Override
    public List<GoodsReceivedNoteDetail> findAll() {
        return null;
    }

    @Override
    public boolean removeById(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        int result = sqLiteDatabase.delete(TABLE_GOODS_RECEIVED_NOTE_DETAIL, whereClause.toString(), arguments);
        return result != 0;
    }

    @Override
    public boolean updateById(int id, GoodsReceivedNoteDetail goodsReceivedNoteDetail) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        ContentValues contentValues = new ContentValues();
        contentValues.put(GOODS_RECEIVED_NOTE_ID, goodsReceivedNoteDetail.getGoodsReceivedNoteId());
        contentValues.put(PRODUCT_ID, goodsReceivedNoteDetail.getProductId());
        contentValues.put(QUANTITY, goodsReceivedNoteDetail.getQuantity());
        contentValues.put(PRICE, goodsReceivedNoteDetail.getPrice());
        int result = sqLiteDatabase.update(TABLE_GOODS_RECEIVED_NOTE_DETAIL, contentValues,
                whereClause.toString(), arguments);
        return result != 0;
    }

    @Override
    public List<GoodsReceivedNoteDetail> findAllByGoodsReceivedNoteId(String goodsReceivedNoteId) {
        List<GoodsReceivedNoteDetail> goodsReceivedNoteDetails = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_GOODS_RECEIVED_NOTE_DETAIL).append(" ").append(WHERE).append(" ").append(GOODS_RECEIVED_NOTE_ID)
                .append("=").append("\'").append(goodsReceivedNoteId).append("\'");
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String productId = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
            int quantity = cursor.getInt(cursor.getColumnIndex(QUANTITY));
            double price = cursor.getDouble(cursor.getColumnIndex(PRICE));
            goodsReceivedNoteDetails.add(new GoodsReceivedNoteDetail(id, goodsReceivedNoteId, productId, quantity, price));
            cursor.moveToNext();
        }
        cursor.close();
        return goodsReceivedNoteDetails;
    }
}
