package com.example.storagemanagement.activity.customer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.storagemanagement.R;
import com.example.storagemanagement.dao.customer.CustomerDao;
import com.example.storagemanagement.dao.customer.ICustomerDao;
import com.example.storagemanagement.model.Customer;

import static com.example.storagemanagement.config.StaticVariable.*;

public class CustomerDetailActivity extends AppCompatActivity {
    private EditText editTextCustomerId;
    private EditText editTextCustomerName;
    private EditText editTextCustomerAddress;
    private EditText editTextPhoneNumber;
    private Button buttonEdit;
    private Button buttonDelete;
    private ICustomerDao customerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final Customer customerInfoFromBundle = getCustomerInfoFromBundle(bundle);
            setEditTextDefaultValue(customerInfoFromBundle);
        }
    }

    private void setEditTextDefaultValue(Customer customerInfoFromBundle) {
        editTextCustomerId.setText(customerInfoFromBundle.getCustomerId());
        editTextCustomerName.setText(customerInfoFromBundle.getName());
        editTextCustomerAddress.setText(customerInfoFromBundle.getAddress());
        editTextPhoneNumber.setText(customerInfoFromBundle.getPhoneNumber());
    }

    private Customer getCustomerInfoFromBundle(Bundle bundle) {
        int id = bundle.getInt(ID);
        String customerId = bundle.getString(CUSTOMER_ID);
        String customerName = bundle.getString(NAME);
        String customerAddress = bundle.getString(ADDRESS);
        String phoneNumber = bundle.getString(PHONE_NUMBER);
        return new Customer(id, customerId, customerName, customerAddress, phoneNumber);
    }

    private void init() {
        editTextCustomerId = findViewById(R.id.editTextCustomerId);
        editTextCustomerName = findViewById(R.id.editTextCustomerName);
        editTextCustomerAddress = findViewById(R.id.editTextCustomerAddress);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonDelete = findViewById(R.id.buttonDelete);
        customerDao = new CustomerDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(CustomerDetailActivity.this, CustomerActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
