package com.example.storagemanagement.dao.goodsDeliveryNoteDetail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storagemanagement.helper.DBHelper;
import com.example.storagemanagement.model.GoodsDeliveryNoteDetail;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class GoodsDeliveryNoteDetailDao implements IGoodsDeliveryNoteDetailDao {
    private DBHelper dbHelper;

    public GoodsDeliveryNoteDetailDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public GoodsDeliveryNoteDetail insert(GoodsDeliveryNoteDetail goodsDeliveryNoteDetail) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GOODS_DELIVERY_NOTE_ID, goodsDeliveryNoteDetail.getGoodsDeliveryNoteId());
        contentValues.put(PRODUCT_ID, goodsDeliveryNoteDetail.getProductId());
        contentValues.put(QUANTITY, goodsDeliveryNoteDetail.getQuantity());
        contentValues.put(PRICE, goodsDeliveryNoteDetail.getPrice());
        long result = sqLiteDatabase.insert(TABLE_GOODS_DELIVERY_NOTE_DETAIL, null, contentValues);
        return result != -1 ? goodsDeliveryNoteDetail : null;
    }

    @Override
    public GoodsDeliveryNoteDetail findById(int id) {
        return null;
    }

    @Override
    public List<GoodsDeliveryNoteDetail> findAll() {
        return null;
    }

    @Override
    public boolean removeById(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        int result = sqLiteDatabase.delete(TABLE_GOODS_DELIVERY_NOTE_DETAIL, whereClause.toString(), arguments);
        return result != 0;
    }

    @Override
    public boolean updateById(int id, GoodsDeliveryNoteDetail goodsDeliveryNoteDetail) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        ContentValues contentValues = new ContentValues();
        contentValues.put(GOODS_DELIVERY_NOTE_ID, goodsDeliveryNoteDetail.getGoodsDeliveryNoteId());
        contentValues.put(PRODUCT_ID, goodsDeliveryNoteDetail.getProductId());
        contentValues.put(QUANTITY, goodsDeliveryNoteDetail.getQuantity());
        contentValues.put(PRICE, goodsDeliveryNoteDetail.getPrice());
        int result = sqLiteDatabase.update(TABLE_GOODS_DELIVERY_NOTE_DETAIL, contentValues,
                whereClause.toString(), arguments);
        return result != 0;
    }

    @Override
    public List<GoodsDeliveryNoteDetail> findAllByGoodsDeliveryNoteId(String goodsDeliveryNoteId) {
        List<GoodsDeliveryNoteDetail> goodsDeliveryNoteDetails = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_GOODS_DELIVERY_NOTE_DETAIL).append(" ").append(WHERE).append(" ").append(GOODS_DELIVERY_NOTE_ID)
                .append("=").append(goodsDeliveryNoteId);
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String productId = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
            int quantity = cursor.getInt(cursor.getColumnIndex(QUANTITY));
            double price = cursor.getDouble(cursor.getColumnIndex(PRICE));
            goodsDeliveryNoteDetails.add(new GoodsDeliveryNoteDetail(id, goodsDeliveryNoteId, productId, quantity, price));
            cursor.moveToNext();
        }
        cursor.close();
        return goodsDeliveryNoteDetails;
    }
}
