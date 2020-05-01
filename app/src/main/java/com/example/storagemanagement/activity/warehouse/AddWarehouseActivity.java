package com.example.storagemanagement.activity.warehouse;

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
import com.example.storagemanagement.activity.MainActivity;
import com.example.storagemanagement.dao.warehouse.IWarehouseDao;
import com.example.storagemanagement.dao.warehouse.WarehouseDao;
import com.example.storagemanagement.model.Warehouse;

import static com.example.storagemanagement.config.StaticVariable.MESSAGE_CREATE_SUCCESS;
import static com.example.storagemanagement.config.StaticVariable.MESSAGE_FAIL;

public class AddWarehouseActivity extends AppCompatActivity {
    private EditText editTextWarehouseId;
    private EditText editTextWarehouseName;
    private EditText editTextWarehouseAddress;
    private Button buttonSave;
    private IWarehouseDao warehouseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_warehouse);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Warehouse warehouse = createNewWarehouse();
                showMessage(warehouse);
                resetForm();
            }
        });
    }

    private void resetForm() {
        editTextWarehouseId.setText("");
        editTextWarehouseName.setText("");
        editTextWarehouseAddress.setText("");
    }

    private void showMessage(Warehouse warehouse) {
        if (warehouse != null) {
            Toast.makeText(this, MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

    private Warehouse createNewWarehouse() {
        String warehouseId = editTextWarehouseId.getText().toString();
        String warehouseName = editTextWarehouseName.getText().toString();
        String warehouseAddress = editTextWarehouseAddress.getText().toString();
        return warehouseDao.insert(new Warehouse(warehouseId, warehouseName, warehouseAddress));
    }

    private void init() {
        editTextWarehouseId = findViewById(R.id.editTextWarehouseId);
        editTextWarehouseName = findViewById(R.id.editTextWarehouseName);
        editTextWarehouseAddress = findViewById(R.id.editTextWarehouseAddress);
        buttonSave = findViewById(R.id.buttonSave);
        warehouseDao = new WarehouseDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddWarehouseActivity.this, WarehouseActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
