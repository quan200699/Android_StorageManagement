package com.example.storagemanagement.activity.warehouse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.MainActivity;
import com.example.storagemanagement.adapter.WarehouseAdapter;
import com.example.storagemanagement.dao.warehouse.IWarehouseDao;
import com.example.storagemanagement.dao.warehouse.WarehouseDao;
import com.example.storagemanagement.model.Warehouse;

import java.util.List;

public class WarehouseActivity extends AppCompatActivity {
    private ListView listViewWarehouse;
    private Button buttonAdd;
    private IWarehouseDao warehouseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        final List<Warehouse> warehouses = warehouseDao.findAll();
        WarehouseAdapter warehouseAdapter = new WarehouseAdapter(WarehouseActivity.this, warehouses);
        listViewWarehouse.setAdapter(warehouseAdapter);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WarehouseActivity.this, AddWarehouseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        listViewWarehouse = findViewById(R.id.listViewWarehouse);
        buttonAdd = findViewById(R.id.buttonAdd);
        warehouseDao = new WarehouseDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(WarehouseActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
