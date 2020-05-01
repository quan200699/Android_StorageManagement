package com.example.storagemanagement.dao.warehouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storagemanagement.helper.DBHelper;
import com.example.storagemanagement.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class WarehouseDao implements IWarehouseDao {
    private DBHelper dbHelper;

    public WarehouseDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public Warehouse insert(Warehouse warehouse) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WAREHOUSE_ID, warehouse.getWarehouseId());
        contentValues.put(NAME, warehouse.getName());
        contentValues.put(ADDRESS, warehouse.getAddress());
        long result = sqLiteDatabase.insert(TABLE_WAREHOUSE, null, contentValues);
        return result != -1 ? warehouse : null;
    }

    @Override
    public Warehouse findById(int id) {
        Warehouse warehouse = new Warehouse();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_WAREHOUSE).append(" ").append(WHERE).append(" ").append(ID).append("=").append(id);
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String warehouseId = cursor.getString(cursor.getColumnIndex(WAREHOUSE_ID));
            String warehouseName = cursor.getString(cursor.getColumnIndex(NAME));
            String address = cursor.getString(cursor.getColumnIndex(ADDRESS));
            warehouse = new Warehouse(warehouseId, warehouseName, address);
            cursor.moveToNext();
        }
        cursor.close();
        return warehouse;
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> warehouses = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_WAREHOUSE);
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String warehouseId = cursor.getString(cursor.getColumnIndex(WAREHOUSE_ID));
            String warehouseName = cursor.getString(cursor.getColumnIndex(NAME));
            String address = cursor.getString(cursor.getColumnIndex(ADDRESS));
            warehouses.add(new Warehouse(id, warehouseId, warehouseName, address));
            cursor.moveToNext();
        }
        cursor.close();
        return warehouses;
    }

    @Override
    public boolean removeById(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        int result = sqLiteDatabase.delete(TABLE_WAREHOUSE, whereClause.toString(), arguments);
        return result != 0;
    }

    @Override
    public boolean updateById(int id, Warehouse warehouse) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        ContentValues contentValues = new ContentValues();
        contentValues.put(WAREHOUSE_ID, warehouse.getWarehouseId());
        contentValues.put(NAME, warehouse.getName());
        contentValues.put(ADDRESS, warehouse.getAddress());
        whereClause.append("=").append("?");
        int result = sqLiteDatabase.update(TABLE_WAREHOUSE, contentValues, whereClause.toString(), arguments);
        return result != 0;
    }
}
