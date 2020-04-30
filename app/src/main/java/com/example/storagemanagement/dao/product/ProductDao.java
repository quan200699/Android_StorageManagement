package com.example.storagemanagement.dao.product;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storagemanagement.helper.DBHelper;
import com.example.storagemanagement.model.Product;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class ProductDao implements IProductDao {
    private DBHelper dbHelper;

    public ProductDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public Product insert(Product product) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_ID, product.getProductId());
        contentValues.put(NAME, product.getName());
        contentValues.put(DESCRIPTION, product.getDescription());
        contentValues.put(GUARANTEE, product.getGuarantee());
        long result = sqLiteDatabase.insert(TABLE_PRODUCT, null, contentValues);
        return result == -1 ? product : null;
    }

    @Override
    public Product findById(int id) {
        Product product = new Product();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_PRODUCT).append(" ").append(WHERE).append(" ").append(ID).append("=").append(id);
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String productId = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
            String productName = cursor.getString(cursor.getColumnIndex(NAME));
            String productDescription = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
            int guarantee = cursor.getInt(cursor.getColumnIndex(GUARANTEE));
            product = new Product(productId, productName, productDescription, guarantee);
            cursor.moveToNext();
        }
        cursor.close();
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_PRODUCT);
        Cursor res = sqLiteDatabase.rawQuery(query.toString(), null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex(ID));
            String productId = res.getString(res.getColumnIndex(PRODUCT_ID));
            String name = res.getString(res.getColumnIndex(NAME));
            String description = res.getString(res.getColumnIndex(DESCRIPTION));
            int guarantee = res.getInt(res.getColumnIndex(GUARANTEE));
            products.add(new Product(id, productId, name, description, guarantee));
            res.moveToNext();
        }
        res.close();
        return products;
    }

    @Override
    public boolean removeById(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        int result = sqLiteDatabase.delete(TABLE_PRODUCT, ID + " = ? ", arguments);
        return result != 0;
    }

    @Override
    public boolean updateById(int id, Product product) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_ID, product.getProductId());
        contentValues.put(NAME, product.getName());
        contentValues.put(DESCRIPTION, product.getDescription());
        contentValues.put(GUARANTEE, product.getGuarantee());
        String[] arguments = new String[]{id + ""};
        int result = sqLiteDatabase.update(TABLE_PRODUCT, contentValues, ID + "= ?", arguments);
        return result != 0;
    }
}
