package com.example.storagemanagement.activity.customer;

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
import com.example.storagemanagement.adapter.CustomerAdapter;
import com.example.storagemanagement.dao.customer.CustomerDao;
import com.example.storagemanagement.dao.customer.ICustomerDao;
import com.example.storagemanagement.model.Customer;

import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class CustomerActivity extends AppCompatActivity {
    private ListView listViewCustomer;
    private Button buttonAdd;
    private ICustomerDao customerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        final List<Customer> customers = customerDao.findAll();
        CustomerAdapter customerAdapter = new CustomerAdapter(CustomerActivity.this, customers);
        listViewCustomer.setAdapter(customerAdapter);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this, AddCustomerActivity.class);
                startActivity(intent);
            }
        });
        listViewCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomerActivity.this, CustomerDetailActivity.class);
                intent.putExtra(ID, customers.get(position).getId());
                intent.putExtra(CUSTOMER_ID, customers.get(position).getCustomerId());
                intent.putExtra(NAME, customers.get(position).getName());
                intent.putExtra(ADDRESS, customers.get(position).getAddress());
                intent.putExtra(PHONE_NUMBER, customers.get(position).getPhoneNumber());
                startActivity(intent);
            }
        });
    }

    private void init() {
        listViewCustomer = findViewById(R.id.listViewCustomer);
        buttonAdd = findViewById(R.id.buttonAdd);
        customerDao = new CustomerDao(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(CustomerActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
