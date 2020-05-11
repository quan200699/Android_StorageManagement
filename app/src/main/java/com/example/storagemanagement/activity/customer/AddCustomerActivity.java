package com.example.storagemanagement.activity.customer;

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
import com.example.storagemanagement.dao.customer.CustomerDao;
import com.example.storagemanagement.dao.customer.ICustomerDao;
import com.example.storagemanagement.model.Customer;

import static com.example.storagemanagement.config.StaticVariable.MESSAGE_CREATE_SUCCESS;
import static com.example.storagemanagement.config.StaticVariable.MESSAGE_FAIL;

public class AddCustomerActivity extends AppCompatActivity {
    private EditText editTextCustomerId;
    private EditText editTextCustomerName;
    private EditText editTextCustomerAddress;
    private EditText editTextPhoneNumber;
    private Button buttonSave;
    private ICustomerDao customerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = createNewCustomer();
                showMessage(customer);
                resetForm();
            }
        });
    }

    private void resetForm() {
        editTextCustomerId.setText("");
        editTextCustomerName.setText("");
        editTextCustomerAddress.setText("");
        editTextPhoneNumber.setText("");
    }

    private void showMessage(Customer customer) {
        if (customer != null) {
            Toast.makeText(this, MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

    private Customer createNewCustomer() {
        String customerId = editTextCustomerId.getText().toString();
        String customerName = editTextCustomerName.getText().toString();
        String customerAddress = editTextCustomerAddress.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        return customerDao.insert(new Customer(customerId, customerName, customerAddress, phoneNumber));
    }

    private void init() {
        editTextCustomerId = findViewById(R.id.editTextCustomerId);
        editTextCustomerName = findViewById(R.id.editTextCustomerName);
        editTextCustomerAddress = findViewById(R.id.editTextCustomerAddress);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonSave = findViewById(R.id.buttonEdit);
        customerDao = new CustomerDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddCustomerActivity.this, CustomerActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
