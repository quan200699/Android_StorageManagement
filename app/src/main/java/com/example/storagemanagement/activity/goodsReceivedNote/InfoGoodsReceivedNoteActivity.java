package com.example.storagemanagement.activity.goodsReceivedNote;

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
import com.example.storagemanagement.dao.goodsReceivedNote.GoodsReceivedNoteDao;
import com.example.storagemanagement.dao.goodsReceivedNote.IGoodsReceivedNoteDao;
import com.example.storagemanagement.dao.warehouse.IWarehouseDao;
import com.example.storagemanagement.dao.warehouse.WarehouseDao;
import com.example.storagemanagement.model.GoodsReceivedNote;
import com.example.storagemanagement.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class InfoGoodsReceivedNoteActivity extends AppCompatActivity {
    private EditText editTextGoodsDeliveryNoteId;
    private EditText editTextDate;
    private EditText editTextNotice;
    private Spinner spinnerCustomer;
    private Spinner spinnerWarehouse;
    private Spinner spinnerEmployee;
    private Button buttonEdit;
    private Button buttonDelete;
    private Button buttonGoodsDeliveryNoteDetail;
    private IGoodsReceivedNoteDao goodsDeliveryNoteDao;
    private IWarehouseDao warehouseDao;
    private List<Warehouse> warehouses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_goods_received_note);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        warehouses = getAllWarehouse();
        List<String> warehouseNames = addWarehouseNameToList(warehouses);
        ArrayAdapter<String> adapterWarehouse = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, warehouseNames);
        spinnerWarehouse.setAdapter(adapterWarehouse);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            GoodsReceivedNote goodsReceivedNoteFromBundle = getGoodsReceivedNoteFromBundle(bundle);
            setDefaultValueForLayout(goodsReceivedNoteFromBundle);
        }
    }

    private void setDefaultValueForLayout(GoodsReceivedNote goodsReceivedNoteFromBundle) {
        editTextGoodsDeliveryNoteId.setText(goodsReceivedNoteFromBundle.getGoodsReceivedNoteId());
        editTextDate.setText(goodsReceivedNoteFromBundle.getDate());
        editTextNotice.setText(goodsReceivedNoteFromBundle.getNotice());
        spinnerWarehouse.setSelection(getDefaultWarehouseSpinnerPosition(warehouses, goodsReceivedNoteFromBundle.getWarehouseId()));
    }

    private int getDefaultWarehouseSpinnerPosition(List<Warehouse> warehouses, String warehouseId) {
        int position = 0;
        if (warehouseId != null) {
            for (int i = 0; i < warehouses.size(); i++) {
                if (warehouseId.equals(warehouses.get(i).getWarehouseId())) {
                    position = i + 1;
                    break;
                }
            }
        }
        return position;
    }

    private GoodsReceivedNote getGoodsReceivedNoteFromBundle(Bundle bundle) {
        int id = bundle.getInt(ID);
        String goodsReceivedNoteId = bundle.getString(GOODS_RECEIVED_NOTE_ID);
        String date = bundle.getString(DATE);
        String warehouseId = bundle.getString(WAREHOUSE_ID);
        String notice = bundle.getString(NOTICE);
        return new GoodsReceivedNote(id, goodsReceivedNoteId, date, warehouseId, notice);
    }

    private List<String> addWarehouseNameToList(List<Warehouse> warehouses) {
        List<String> warehouseNames = new ArrayList<>();
        warehouseNames.add("");
        for (Warehouse warehouse : warehouses) {
            warehouseNames.add(warehouse.getName());
        }
        return warehouseNames;
    }

    private List<Warehouse> getAllWarehouse() {
        return warehouseDao.findAll();
    }


    private void init() {
        editTextGoodsDeliveryNoteId = findViewById(R.id.editTextGoodsReceivedNoteId);
        editTextDate = findViewById(R.id.editTextDate);
        editTextNotice = findViewById(R.id.editTextNotice);
        spinnerCustomer = findViewById(R.id.spinnerSupplier);
        spinnerWarehouse = findViewById(R.id.spinnerWarehouse);
        spinnerEmployee = findViewById(R.id.spinnerEmployee);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonGoodsDeliveryNoteDetail = findViewById(R.id.buttonGoodsReceivedNote);
        goodsDeliveryNoteDao = new GoodsReceivedNoteDao(this);
        warehouseDao = new WarehouseDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(InfoGoodsReceivedNoteActivity.this, GoodsReceivedNoteActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
