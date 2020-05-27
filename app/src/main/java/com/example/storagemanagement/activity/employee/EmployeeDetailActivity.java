package com.example.storagemanagement.activity.employee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

import static com.example.storagemanagement.config.StaticVariable.*;

public class EmployeeDetailActivity extends AppCompatActivity {
    private EditText editTextEmployeeId;
    private EditText editTextEmployeeName;
    private EditText editTextAddress;
    private EditText editTextSex;
    private EditText editTextBirthDay;
    private Button buttonEdit;
    private Button buttonDelete;
    private IEmployeeDao employeeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final Employee employeeInfoFromBundle = getEmployeeInfoFromBundle(bundle);
            setEditTextDefaultValue(employeeInfoFromBundle);
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Employee employee = getEmployeeInfoFromLayout();
                    employee.setId(employeeInfoFromBundle.getId());
                    boolean isUpdated = employeeDao.updateById(employeeInfoFromBundle.getId(), employee);
                    showMessage(isUpdated, MESSAGE_UPDATE_SUCCESS);
                }
            });
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopup(employeeInfoFromBundle.getId());
                }
            });
        }
    }

    private void showPopup(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeDetailActivity.this);
        final boolean isDeleted = employeeDao.removeById(id);
        builder.setTitle(DELETE + CUSTOMER);
        builder.setMessage(ARE_YOU_SURE);
        builder.setPositiveButton(YES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage(isDeleted, MESSAGE_DELETE_SUCCESS);
                Intent intent = new Intent(EmployeeDetailActivity.this, EmployeeActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(NO, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), CANCEL, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showMessage(boolean isSuccess, String message) {
        if (isSuccess) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

    private Employee getEmployeeInfoFromLayout() {
        String employeeId = editTextEmployeeId.getText().toString();
        String name = editTextEmployeeName.getText().toString();
        String birthday = editTextBirthDay.getText().toString();
        String sex = editTextSex.getText().toString();
        String address = editTextAddress.getText().toString();
        return new Employee(employeeId, name, birthday, sex, address);
    }

    private void setEditTextDefaultValue(Employee employee) {
        editTextEmployeeId.setText(employee.getEmployeeId());
        editTextEmployeeName.setText(employee.getName());
        editTextBirthDay.setText(employee.getBirthday());
        editTextSex.setText(employee.getSex());
        editTextAddress.setText(employee.getAddress());
    }

    private Employee getEmployeeInfoFromBundle(Bundle bundle) {
        int id = bundle.getInt(ID);
        String employeeId = bundle.getString(EMPLOYEE_ID);
        String name = bundle.getString(NAME);
        String birthday = bundle.getString(BIRTHDAY);
        String sex = bundle.getString(SEX);
        String address = bundle.getString(ADDRESS);
        return new Employee(id, employeeId, name, birthday, sex, address);
    }

    private void init() {
        editTextEmployeeId = findViewById(R.id.editTextEmployeeId);
        editTextEmployeeName = findViewById(R.id.editTextEmployeeName);
        editTextAddress = findViewById(R.id.editTextEmployeeAddress);
        editTextSex = findViewById(R.id.editTextEmployeeSex);
        editTextBirthDay = findViewById(R.id.editTextEmployeeBirthDay);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonDelete = findViewById(R.id.buttonDelete);
        employeeDao = new EmployeeDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(EmployeeDetailActivity.this, EmployeeActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
