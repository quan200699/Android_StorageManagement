package com.example.storagemanagement.activity.goodsReceivedNote;

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
import com.example.storagemanagement.dao.goodsReceivedNote.GoodsReceivedNoteDao;
import com.example.storagemanagement.dao.goodsReceivedNote.IGoodsReceivedNoteDao;
import com.example.storagemanagement.dao.warehouse.IWarehouseDao;
import com.example.storagemanagement.dao.warehouse.WarehouseDao;
import com.example.storagemanagement.model.GoodsReceivedNote;
import com.example.storagemanagement.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.MESSAGE_CREATE_SUCCESS;
import static com.example.storagemanagement.config.StaticVariable.MESSAGE_FAIL;

public class AddGoodsReceivedNoteActivity extends AppCompatActivity {
    private EditText editTextGoodsReceivedNoteId;
    private EditText editTextDate;
    private EditText editTextNotice;
    private Spinner spinnerSupplier;
    private Spinner spinnerWarehouse;
    private Spinner spinnerEmployee;
    private Button buttonSave;
    private IGoodsReceivedNoteDao goodsReceivedNoteDao;
    private IWarehouseDao warehouseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods_received_note);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        List<Warehouse> warehouses = getAllWarehouse();
        List<String> warehouseNames = addWarehouseNameToList(warehouses);
        ArrayAdapter<String> adapterWarehouse = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, warehouseNames);
        spinnerWarehouse.setAdapter(adapterWarehouse);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsReceivedNote goodsReceivedNote = createGoodsReceivedNote();
                showMessage(goodsReceivedNote);
                resetForm();
            }
        });

    }

    private void resetForm() {
        editTextGoodsReceivedNoteId.setText("");
        editTextDate.setText("");
        editTextNotice.setText("");
        spinnerWarehouse.setSelection(0);
    }

    private void showMessage(GoodsReceivedNote goodsReceivedNote) {
        if (goodsReceivedNote != null) {
            Toast.makeText(this, MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

    private GoodsReceivedNote createGoodsReceivedNote() {
        String goodsReceivedNoteId = editTextGoodsReceivedNoteId.getText().toString();
        String date = editTextDate.getText().toString();
        String notice = editTextNotice.getText().toString();
        String warehouseName = spinnerWarehouse.getSelectedItem().toString();
        GoodsReceivedNote goodsReceivedNote = new GoodsReceivedNote(goodsReceivedNoteId, date, notice);
        Warehouse warehouse = warehouseDao.findByName(warehouseName);
        if (warehouse != null) {
            goodsReceivedNote.setWarehouseId(warehouse.getWarehouseId());
        }
        return goodsReceivedNoteDao.insert(goodsReceivedNote);
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
        editTextGoodsReceivedNoteId = findViewById(R.id.editTextGoodsReceivedNoteId);
        editTextDate = findViewById(R.id.editTextDate);
        editTextNotice = findViewById(R.id.editTextNotice);
        spinnerSupplier = findViewById(R.id.spinnerSupplier);
        spinnerWarehouse = findViewById(R.id.spinnerWarehouse);
        spinnerEmployee = findViewById(R.id.spinnerEmployee);
        buttonSave = findViewById(R.id.buttonSave);
        goodsReceivedNoteDao = new GoodsReceivedNoteDao(this);
        warehouseDao = new WarehouseDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddGoodsReceivedNoteActivity.this, GoodsReceivedNoteActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
