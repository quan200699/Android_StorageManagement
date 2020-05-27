package com.example.storagemanagement.activity.employee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.MainActivity;
import com.example.storagemanagement.adapter.EmployeeAdapter;
import com.example.storagemanagement.dao.employee.EmployeeDao;
import com.example.storagemanagement.dao.employee.IEmployeeDao;
import com.example.storagemanagement.model.Employee;

import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class EmployeeActivity extends AppCompatActivity {
    private ListView listViewEmployee;
    private Button buttonAdd;
    private IEmployeeDao employeeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        final List<Employee> employees = employeeDao.findAll();
        EmployeeAdapter customerAdapter = new EmployeeAdapter(EmployeeActivity.this, employees);
        listViewEmployee.setAdapter(customerAdapter);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeActivity.this, AddEmployeeActivity.class);
                startActivity(intent);
            }
        });
        listViewEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EmployeeActivity.this, EmployeeDetailActivity.class);
                intent.putExtra(ID, employees.get(position).getId());
                intent.putExtra(EMPLOYEE_ID, employees.get(position).getEmployeeId());
                intent.putExtra(NAME, employees.get(position).getName());
                intent.putExtra(BIRTHDAY, employees.get(position).getBirthday());
                intent.putExtra(SEX, employees.get(position).getSex());
                intent.putExtra(ADDRESS, employees.get(position).getAddress());
                startActivity(intent);
            }
        });
    }

    private void init() {
        listViewEmployee = findViewById(R.id.listViewEmployee);
        buttonAdd = findViewById(R.id.buttonAdd);
        employeeDao = new EmployeeDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(EmployeeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}