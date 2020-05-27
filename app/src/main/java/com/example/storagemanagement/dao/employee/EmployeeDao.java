package com.example.storagemanagement.dao.employee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storagemanagement.helper.DBHelper;
import com.example.storagemanagement.model.Employee;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class EmployeeDao implements IEmployeeDao {
    private DBHelper dbHelper;

    public EmployeeDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public Employee findByName(String name) {
        Employee employee = new Employee();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_EMPLOYEE).append(" ").append(WHERE).append(" ").append(NAME).append("=").append("'").append(name).append("'");
        Cursor cursor = sqLiteDatabase.rawQuery(String.valueOf(query), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String employeeId = cursor.getString(cursor.getColumnIndex(EMPLOYEE_ID));
            String birthday = cursor.getString(cursor.getColumnIndex(BIRTHDAY));
            String sex = cursor.getString(cursor.getColumnIndex(SEX));
            String address = cursor.getString(cursor.getColumnIndex(ADDRESS));
            employee = new Employee(id, employeeId, name, birthday, sex, address);
            cursor.moveToNext();
        }
        cursor.close();
        return employee;
    }

    @Override
    public Employee insert(Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_ID, employee.getEmployeeId());
        contentValues.put(NAME, employee.getName());
        contentValues.put(BIRTHDAY, employee.getBirthday());
        contentValues.put(SEX, employee.getSex());
        contentValues.put(ADDRESS, employee.getAddress());
        long result = sqLiteDatabase.insert(TABLE_EMPLOYEE, null, contentValues);
        return result != -1 ? employee : null;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = new Employee();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_EMPLOYEE).append(" ").append(WHERE).append(" ").append(ID).append("=").append(id);
        Cursor cursor = sqLiteDatabase.rawQuery(query.toString(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String employeeId = cursor.getString(cursor.getColumnIndex(EMPLOYEE_ID));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String birthday = cursor.getString(cursor.getColumnIndex(BIRTHDAY));
            String customerAddress = cursor.getString(cursor.getColumnIndex(ADDRESS));
            String sex = cursor.getString(cursor.getColumnIndex(SEX));
            employee = new Employee(employeeId, name, birthday, sex, customerAddress);
            cursor.moveToNext();
        }
        cursor.close();
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_ATTRIBUTE);
        query.append(TABLE_CUSTOMER);
        Cursor res = sqLiteDatabase.rawQuery(query.toString(), null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex(ID));
            String employeeId = res.getString(res.getColumnIndex(CUSTOMER_ID));
            String name = res.getString(res.getColumnIndex(NAME));
            String birthday = res.getString(res.getColumnIndex(BIRTHDAY));
            String sex = res.getString(res.getColumnIndex(SEX));
            String address = res.getString(res.getColumnIndex(ADDRESS));
            employees.add(new Employee(id, employeeId, name, birthday, sex, address));
            res.moveToNext();
        }
        res.close();
        return employees;
    }

    @Override
    public boolean removeById(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        int result = sqLiteDatabase.delete(TABLE_EMPLOYEE, whereClause.toString(), arguments);
        return result != 0;
    }

    @Override
    public boolean updateById(int id, Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_ID, employee.getEmployeeId());
        contentValues.put(NAME, employee.getName());
        contentValues.put(BIRTHDAY, employee.getBirthday());
        contentValues.put(SEX, employee.getSex());
        contentValues.put(ADDRESS, employee.getAddress());
        String[] arguments = new String[]{id + ""};
        StringBuilder whereClause = new StringBuilder(ID);
        whereClause.append("=").append("?");
        int result = sqLiteDatabase.update(TABLE_EMPLOYEE, contentValues, whereClause.toString(), arguments);
        return result != 0;
    }
}
