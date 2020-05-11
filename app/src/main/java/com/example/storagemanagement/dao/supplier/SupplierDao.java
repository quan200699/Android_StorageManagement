package com.example.storagemanagement.dao.supplier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storagemanagement.config.StaticVariable;
import com.example.storagemanagement.helper.DBHelper;
import com.example.storagemanagement.model.Supplier;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class SupplierDao implements ISupplierDao {
    private DBHelper dbHelper;

    public SupplierDao(Context context){this.dbHelper = new DBHelper(context);}

    @Override
    public Supplier insert(Supplier supplier) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUPPLIER_ID,supplier.getSupplierId());
        contentValues.put(NAME,supplier.getName());
        contentValues.put(ADDRESS,supplier.getAddress());
        contentValues.put(EMAIL,supplier.getEmail());
        long result =sqLiteDatabase.insert(TABLE_SUPPLIER,null,contentValues);
        return result !=-1 ? supplier : null;
    }

    @Override
    public Supplier findById(int id) {
        Supplier sup = new Supplier();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_SUPPLIER).append(" ").append("WHERE").append(" ").append(ID).append("=").append(id);
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(),null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String SupplierId = cursor.getString(cursor.getColumnIndex(SUPPLIER_ID));
            String SupplierName = cursor.getString(cursor.getColumnIndex(NAME));
            String SupplierAddress = cursor.getString(cursor.getColumnIndex(ADDRESS));
            String SupplierEmail = cursor.getString(cursor.getColumnIndex(EMAIL));
            sup = new Supplier(SupplierId,SupplierName,SupplierAddress,SupplierEmail);
            cursor.moveToNext();
        }
        cursor.close();
        return sup;
    }

    @Override
    public List<Supplier> findAll() {
       List<Supplier> sup = new ArrayList<>();
       SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
       StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
       query.append(TABLE_SUPPLIER);
       Cursor cursor = sqLiteDatabase.rawQuery(query.toString(),null);
       cursor.moveToFirst();
       while (!cursor.isAfterLast()){
           int id = cursor.getInt(cursor.getColumnIndex(ID));
           String SupplierId = cursor.getString(cursor.getColumnIndex(SUPPLIER_ID));
           String SupplierName = cursor.getString(cursor.getColumnIndex(NAME));
           String SupplierAddress = cursor.getString(cursor.getColumnIndex(ADDRESS));
           String SupplierEmail = cursor.getString(cursor.getColumnIndex(EMAIL));
           sup.add(new Supplier(id,SupplierId,SupplierName,SupplierAddress,SupplierEmail));
           cursor.moveToNext();
       }
       cursor.close();
       return sup;
    }

    @Override
    public boolean removeById(int id) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        String[] agruments = new String[]{id+""};
        StringBuilder WhereClause = new StringBuilder(ID);
        WhereClause.append("=").append("?");
        int result = sqLiteDatabase.delete(TABLE_SUPPLIER,WhereClause.toString(),agruments);
        return result !=0;
    }

    @Override
    public boolean updateById(int id, Supplier supplier) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUPPLIER_ID,supplier.getSupplierId());
        contentValues.put(NAME,supplier.getName());
        contentValues.put(ADDRESS,supplier.getAddress());
        contentValues.put(EMAIL,supplier.getEmail());
        String [] arguments = new String[]{id+""};
        StringBuilder WhereClause = new  StringBuilder(ID);
        WhereClause.append("=").append("?");
        int result = sqLiteDatabase.update(TABLE_SUPPLIER,contentValues,WhereClause.toString(),arguments);
        return result !=0;
    }
}
