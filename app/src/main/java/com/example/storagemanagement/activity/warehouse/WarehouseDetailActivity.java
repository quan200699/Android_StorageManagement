package com.example.storagemanagement.activity.warehouse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.storagemanagement.R;
import com.example.storagemanagement.dao.warehouse.IWarehouseDao;
import com.example.storagemanagement.dao.warehouse.WarehouseDao;
import com.example.storagemanagement.model.Warehouse;

import static com.example.storagemanagement.config.StaticVariable.*;

public class WarehouseDetailActivity extends AppCompatActivity {
    private EditText editTextWarehouseId;
    private EditText editTextWarehouseName;
    private EditText editTextWarehouseAddress;
    private Button buttonEdit;
    private Button buttonDelete;
    private IWarehouseDao warehouseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final Warehouse warehouseInfoFromBundle = getWarehouseInfoFromBundle(bundle);
            setEditTextDefaultValue(warehouseInfoFromBundle);
        }
    }

    private void setEditTextDefaultValue(Warehouse warehouse) {
        editTextWarehouseId.setText(warehouse.getWarehouseId());
        editTextWarehouseName.setText(warehouse.getName());
        editTextWarehouseAddress.setText(warehouse.getAddress());
    }

    private Warehouse getWarehouseInfoFromBundle(Bundle bundle) {
        String wareHouseId = bundle.getString(WAREHOUSE_ID);
        int id = bundle.getInt(ID);
        String warehouseName = bundle.getString(NAME);
        String warehouseAddress = bundle.getString(ADDRESS);
        return new Warehouse(id, wareHouseId, warehouseName, warehouseAddress);
    }

    private void init() {
        editTextWarehouseId = findViewById(R.id.editTextWarehouseId);
        editTextWarehouseName = findViewById(R.id.editTextWarehouseName);
        editTextWarehouseAddress = findViewById(R.id.editTextWarehouseAddress);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);
        warehouseDao = new WarehouseDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(WarehouseDetailActivity.this, WarehouseActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
