package com.example.storagemanagement.activity.goodsDeliveryNote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.example.storagemanagement.activity.goodsDeliveryNoteDetail.GoodsDeliveryNoteDetailActivity;
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

public class InfoGoodsDeliveryNoteActivity extends AppCompatActivity {
    private EditText editTextGoodsDeliveryNoteId;
    private EditText editTextDate;
    private EditText editTextNotice;
    private Spinner spinnerCustomer;
    private Spinner spinnerWarehouse;
    private Spinner spinnerEmployee;
    private Button buttonEdit;
    private Button buttonDelete;
    private Button buttonGoodsDeliveryNoteDetail;
    private IGoodsDeliveryNoteDao goodsDeliveryNoteDao;
    private ICustomerDao customerDao;
    private IWarehouseDao warehouseDao;
    private List<Customer> customers;
    private List<Warehouse> warehouses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_goods_delivery_note);
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
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoodsDeliveryNote goodsDeliveryNote = getGoodsDeliveryNoteFromLayout();
                    goodsDeliveryNote.setId(goodsDeliveryNoteFromBundle.getId());
                    boolean isUpdated = goodsDeliveryNoteDao.updateById(goodsDeliveryNote.getId(), goodsDeliveryNote);
                    showMessage(isUpdated, MESSAGE_UPDATE_SUCCESS);
                }
            });
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopup(goodsDeliveryNoteFromBundle.getId());
                }
            });
            buttonGoodsDeliveryNoteDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(InfoGoodsDeliveryNoteActivity.this, GoodsDeliveryNoteDetailActivity.class);
                    intent.putExtra(GOODS_DELIVERY_NOTE_ID, goodsDeliveryNoteFromBundle.getGoodsDeliveryNoteId());
                    startActivity(intent);
                }
            });
        }
    }

    private void showPopup(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(InfoGoodsDeliveryNoteActivity.this);
        final boolean isDeleted = goodsDeliveryNoteDao.removeById(id);
        builder.setTitle(DELETE + GOODS_DELIVERY_NOTE);
        builder.setMessage(ARE_YOU_SURE);
        builder.setPositiveButton(YES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage(isDeleted, MESSAGE_DELETE_SUCCESS);
                Intent intent = new Intent(InfoGoodsDeliveryNoteActivity.this, GoodsDeliveryNoteActivity.class);
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

    private GoodsDeliveryNote getGoodsDeliveryNoteFromLayout() {
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
        return goodsDeliveryNote;
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
        buttonGoodsDeliveryNoteDetail = findViewById(R.id.buttonGoodsDeliveryNoteDetail);
        goodsDeliveryNoteDao = new GoodsDeliveryNoteDao(this);
        customerDao = new CustomerDao(this);
        warehouseDao = new WarehouseDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(InfoGoodsDeliveryNoteActivity.this, GoodsDeliveryNoteActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
