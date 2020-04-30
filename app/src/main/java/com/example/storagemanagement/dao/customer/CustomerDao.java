package com.example.storagemanagement.dao.customer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storagemanagement.helper.DBHelper;
import com.example.storagemanagement.model.Customer;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class CustomerDao implements ICustomerDao {
    private DBHelper dbHelper;

    public CustomerDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public Customer insert(Customer customer) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_ID, customer.getCustomerId());
        contentValues.put(NAME, customer.getName());
        contentValues.put(ADDRESS, customer.getAddress());
        contentValues.put(PHONE_NUMBER, customer.getPhoneNumber());
        long result = sqLiteDatabase.insert(TABLE_CUSTOMER, null, contentValues);
        return result != -1 ? customer : null;
    }

    @Override
    public Customer findById(int id) {
        Customer customer = new Customer();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_CUSTOMER).append(" ").append(WHERE).append(" ").append(ID).append("=").append(id);
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String customerId = cursor.getString(cursor.getColumnIndex(CUSTOMER_ID));
            String customerName = cursor.getString(cursor.getColumnIndex(NAME));
            String customerAddress = cursor.getString(cursor.getColumnIndex(ADDRESS));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(PHONE_NUMBER));
            customer = new Customer(customerId, customerName, customerAddress, phoneNumber);
            cursor.moveToNext();
        }
        cursor.close();
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_CUSTOMER);
        Cursor res = sqLiteDatabase.rawQuery(query.toString(), null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex(ID));
            String customerId = res.getString(res.getColumnIndex(PRODUCT_ID));
            String name = res.getString(res.getColumnIndex(NAME));
            String address = res.getString(res.getColumnIndex(DESCRIPTION));
            String phoneNumber = res.getString(res.getColumnIndex(PHONE_NUMBER));
            customers.add(new Customer(id, customerId, name, address, phoneNumber));
            res.moveToNext();
        }
        res.close();
        return customers;
    }

    @Override
    public boolean removeById(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        int result = sqLiteDatabase.delete(TABLE_CUSTOMER, whereClause.toString(), arguments);
        return result != 0;
    }

    @Override
    public boolean updateById(int id, Customer customer) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_ID, customer.getCustomerId());
        contentValues.put(NAME, customer.getName());
        contentValues.put(ADDRESS, customer.getAddress());
        contentValues.put(PHONE_NUMBER, customer.getPhoneNumber());
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        int result = sqLiteDatabase.update(TABLE_CUSTOMER, contentValues, whereClause.toString(), arguments);
        return result != 0;
    }
}
