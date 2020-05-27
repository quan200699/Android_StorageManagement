package com.example.storagemanagement.activity.employee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storagemanagement.R;
import com.example.storagemanagement.dao.employee.EmployeeDao;
import com.example.storagemanagement.dao.employee.IEmployeeDao;
import com.example.storagemanagement.model.Employee;

import static com.example.storagemanagement.config.StaticVariable.MESSAGE_CREATE_SUCCESS;
import static com.example.storagemanagement.config.StaticVariable.MESSAGE_FAIL;

public class AddEmployeeActivity extends AppCompatActivity {
    private EditText editTextEmployeeId;
    private EditText editTextEmployeeName;
    private EditText editTextBirthday;
    private EditText editTextSex;
    private EditText editTextAddress;
    private Button buttonSave;
    private IEmployeeDao employeeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee = createNewEmployee();
                showMessage(employee);
                resetForm();
            }
        });
    }

    private void resetForm() {
        editTextEmployeeId.setText("");
        editTextEmployeeName.setText("");
        editTextBirthday.setText("");
        editTextSex.setText("");
        editTextAddress.setText("");
    }

    private void showMessage(Employee employee) {
        if (employee != null) {
            Toast.makeText(this, MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

    private Employee createNewEmployee() {
        String employeeId = editTextEmployeeId.getText().toString();
        String employeeName = editTextEmployeeName.getText().toString();
        String birthday = editTextBirthday.getText().toString();
        String sex = editTextSex.getText().toString();
        String address = editTextAddress.getText().toString();
        return employeeDao.insert(new Employee(employeeId, employeeName, birthday, sex, address));
    }

    private void init() {
        editTextEmployeeId = findViewById(R.id.editTextEmployeeId);
        editTextEmployeeName = findViewById(R.id.editTextEmployeeName);
        editTextBirthday = findViewById(R.id.editTextEmployeeBirthDay);
        editTextSex = findViewById(R.id.editTextEmployeeSex);
        editTextAddress = findViewById(R.id.editTextEmployeeAddress);
        buttonSave = findViewById(R.id.buttonAdd);
        employeeDao = new EmployeeDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddEmployeeActivity.this, EmployeeActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
