package com.example.storagemanagement.activity.goodsDeliveryNote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

import static com.example.storagemanagement.config.StaticVariable.*;

public class GoodsDeliveryNoteDetailActivity extends AppCompatActivity {
    private EditText editTextGoodsDeliveryNoteId;
    private EditText editTextDate;
    private EditText editTextNotice;
    private Spinner spinnerCustomer;
    private Spinner spinnerWarehouse;
    private Spinner spinnerEmployee;
    private Button buttonEdit;
    private Button buttonDelete;
    private IGoodsDeliveryNoteDao goodsDeliveryNoteDao;
    private ICustomerDao customerDao;
    private IWarehouseDao warehouseDao;
    private List<Customer> customers;
    private List<Warehouse> warehouses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_delivery_note_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        customers = getAllCustomer();
        List<String> customerNames = addCustomerNameToList(customers);
        warehouses = getAllWarehouse();
        List<String> warehouseNames = addWarehouseNameToList(warehouses);
        ArrayAdapter<String> adapterCustomer = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, customerNames);
        ArrayAdapter<String> adapterWarehouse = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, warehouseNames);
        spinnerCustomer.setAdapter(adapterCustomer);
        spinnerWarehouse.setAdapter(adapterWarehouse);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final GoodsDeliveryNote goodsDeliveryNoteFromBundle = getGoodsDeliveryNoteFromBundle(bundle);
            setDefaultValueForLayout(goodsDeliveryNoteFromBundle);
        }
    }

    private void setDefaultValueForLayout(GoodsDeliveryNote goodsDeliveryNoteFromBundle) {
        editTextGoodsDeliveryNoteId.setText(goodsDeliveryNoteFromBundle.getGoodsDeliveryNoteId());
        editTextDate.setText(goodsDeliveryNoteFromBundle.getDate());
        editTextNotice.setText(goodsDeliveryNoteFromBundle.getNotice());
        spinnerCustomer.setSelection(getDefaultCustomerSpinnerPosition(customers, goodsDeliveryNoteFromBundle.getCustomerId()));
        spinnerWarehouse.setSelection(getDefaultWarehouseSpinnerPosition(warehouses, goodsDeliveryNoteFromBundle.getWareHouseId()));
    }

    private int getDefaultWarehouseSpinnerPosition(List<Warehouse> warehouses, String wareHouseId) {
        int position = 0;
        if (wareHouseId != null) {
            for (int i = 0; i < warehouses.size(); i++) {
                if (wareHouseId.equals(warehouses.get(i).getWarehouseId())) {
                    position = i + 1;
                    break;
                }
            }
        }
        return position;
    }

    private int getDefaultCustomerSpinnerPosition(List<Customer> customers, String customerId) {
        int position = 0;
        if (customerId != null) {
            for (int i = 0; i < customers.size(); i++) {
                if (customerId.equals(customers.get(i).getCustomerId())) {
                    position = i + 1;
                    break;
                }
            }
        }
        return position;
    }

    private GoodsDeliveryNote getGoodsDeliveryNoteFromBundle(Bundle bundle) {
        int id = bundle.getInt(ID);
        String goodsDeliveryNoteId = bundle.getString(GOODS_DELIVERY_NOTE_ID);
        String date = bundle.getString(DATE);
        String customerId = bundle.getString(CUSTOMER_ID);
        String warehouseId = bundle.getString(WAREHOUSE_ID);
        String notice = bundle.getString(NOTICE);
        return new GoodsDeliveryNote(id, goodsDeliveryNoteId, date, customerId, warehouseId, notice);
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
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);
        goodsDeliveryNoteDao = new GoodsDeliveryNoteDao(this);
        customerDao = new CustomerDao(this);
        warehouseDao = new WarehouseDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(GoodsDeliveryNoteDetailActivity.this, GoodsDeliveryNoteActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
