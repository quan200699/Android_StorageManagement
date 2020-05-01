package com.example.storagemanagement.activity.warehouse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Warehouse warehouse = getWarehouseInfoFromLayout();
                    warehouse.setId(warehouseInfoFromBundle.getId());
                    boolean isUpdated = warehouseDao.updateById(warehouseInfoFromBundle.getId(), warehouse);
                    showMessage(isUpdated, MESSAGE_UPDATE_SUCCESS);
                }
            });
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopup(warehouseInfoFromBundle.getId());
                }
            });
        }
    }

    private void showPopup(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(WarehouseDetailActivity.this);
        final boolean isDeleted = warehouseDao.removeById(id);
        builder.setTitle(DELETE_PRODUCT);
        builder.setMessage(ARE_YOU_SURE);
        builder.setPositiveButton(YES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage(isDeleted, MESSAGE_DELETE_SUCCESS);
                Intent intent = new Intent(WarehouseDetailActivity.this, WarehouseActivity.class);
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

    private Warehouse getWarehouseInfoFromLayout() {
        String wareHouseId = editTextWarehouseId.getText().toString();
        String warehouseName = editTextWarehouseName.getText().toString();
        String warehouseAddress = editTextWarehouseAddress.getText().toString();
        return new Warehouse(wareHouseId, warehouseName, warehouseAddress);
    }

    private void setEditTextDefaultValue(Warehouse warehouse) {
        editTextWarehouseId.setText(warehouse.getWarehouseId());
        editTextWarehouseName.setText(warehouse.getName());
        editTextWarehouseAddress.setText(warehouse.getAddress());
    }

    private Warehouse getWarehouseInfoFromBundle(Bundle bundle) {
        int id = bundle.getInt(ID);
        String wareHouseId = bundle.getString(WAREHOUSE_ID);
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
