package com.example.storagemanagement.dao.product;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.storagemanagement.helper.DBHelper;
import com.example.storagemanagement.model.Product;

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
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    @Override
    public boolean updateById(int id, Product product) {
        return false;
    }
}
