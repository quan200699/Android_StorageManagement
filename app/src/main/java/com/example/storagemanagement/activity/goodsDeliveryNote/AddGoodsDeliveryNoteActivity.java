package com.example.storagemanagement.activity.goodsDeliveryNote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.storagemanagement.R;
import com.example.storagemanagement.dao.customer.CustomerDao;
import com.example.storagemanagement.dao.customer.ICustomerDao;
import com.example.storagemanagement.dao.goodsDeliveryNote.GoodsDeliveryNoteDao;
import com.example.storagemanagement.dao.goodsDeliveryNote.IGoodsDeliveryNoteDao;
import com.example.storagemanagement.dao.warehouse.IWarehouseDao;
import com.example.storagemanagement.dao.warehouse.WarehouseDao;
import com.example.storagemanagement.model.Customer;
import com.example.storagemanagement.model.GoodsDeliveryNote;
import com.example.storagemanagement.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.MESSAGE_CREATE_SUCCESS;
import static com.example.storagemanagement.config.StaticVariable.MESSAGE_FAIL;

public class AddGoodsDeliveryNoteActivity extends AppCompatActivity {
    private EditText editTextGoodsDeliveryNoteId;
    private EditText editTextDate;
    private EditText editTextNotice;
    private Spinner spinnerCustomer;
    private Spinner spinnerWarehouse;
    private Spinner spinnerEmployee;
    private Button buttonSave;
    private IGoodsDeliveryNoteDao goodsDeliveryNoteDao;
    private ICustomerDao customerDao;
    private IWarehouseDao warehouseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods_delivery_note);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        List<Customer> customers = getAllCustomer();
        List<String> customerNames = addCustomerNameToList(customers);
        List<Warehouse> warehouses = getAllWarehouse();
        List<String> warehouseNames = addWarehouseNameToList(warehouses);
        ArrayAdapter<String> adapterCustomer = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, customerNames);
        ArrayAdapter<String> adapterWarehouse = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, warehouseNames);
        spinnerCustomer.setAdapter(adapterCustomer);
        spinnerWarehouse.setAdapter(adapterWarehouse);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsDeliveryNote goodsDeliveryNote = createGoodsDeliveryNote();
                showMessage(goodsDeliveryNote);
                resetForm();
            }
        });
    }

    private void resetForm() {
        editTextGoodsDeliveryNoteId.setText("");
        editTextDate.setText("");
        editTextNotice.setText("");
        spinnerWarehouse.setSelection(0);
        spinnerCustomer.setSelection(0);
    }

    private void showMessage(GoodsDeliveryNote goodsDeliveryNote) {
        if (goodsDeliveryNote != null) {
            Toast.makeText(this, MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

    private GoodsDeliveryNote createGoodsDeliveryNote() {
        String goodsDeliveryNoteId = editTextGoodsDeliveryNoteId.getText().toString();
        String date = editTextDate.getText().toString();
        String notice = editTextNotice.getText().toString();
        String customerName = spinnerCustomer.getSelectedItem().toString();
        String warehouseName = spinnerWarehouse.getSelectedItem().toString();
        GoodsDeliveryNote goodsDeliveryNote = new GoodsDeliveryNote(goodsDeliveryNoteId, date, notice);
        Customer customer = customerDao.findByName(customerName);
        Warehouse warehouse = warehouseDao.findByName(warehouseName);
        if (customer != null) {
            goodsDeliveryNote.setCustomerId(customer.getCustomerId());
        }
        if (warehouse != null) {
            goodsDeliveryNote.setWareHouseId(warehouse.getWarehouseId());
        }
        return goodsDeliveryNoteDao.insert(goodsDeliveryNote);
    }

    private List<String> addWarehouseNameToList(List<Warehouse> warehouses) {
        List<String> warehouseNames = new ArrayList<>();
        warehouseNames.add("");
        for (Warehouse warehouse : warehouses) {
            warehouseNames.add(warehouse.getName());
        }
        return warehouseNames;
    }

    private List<String> addCustomerNameToList(List<Customer> customers) {
        List<String> customerNames = new ArrayList<>();
        customerNames.add("");
        for (Customer customer : customers) {
            customerNames.add(customer.getName());
        }
        return customerNames;
    }

    private List<Warehouse> getAllWarehouse() {
        return warehouseDao.findAll();
    }

    private List<Customer> getAllCustomer() {
        return customerDao.findAll();
    }

    private void init() {
        editTextGoodsDeliveryNoteId = findViewById(R.id.editTextGoodsDeliveryNoteId);
        editTextDate = findViewById(R.id.editTextDate);
        editTextNotice = findViewById(R.id.editTextNotice);
        spinnerCustomer = findViewById(R.id.spinnerCustomer);
        spinnerWarehouse = findViewById(R.id.spinnerWarehouse);
        spinnerEmployee = findViewById(R.id.spinnerEmployee);
        buttonSave = findViewById(R.id.buttonSave);
        goodsDeliveryNoteDao = new GoodsDeliveryNoteDao(this);
        customerDao = new CustomerDao(this);
        warehouseDao = new WarehouseDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddGoodsDeliveryNoteActivity.this, GoodsDeliveryNoteActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
