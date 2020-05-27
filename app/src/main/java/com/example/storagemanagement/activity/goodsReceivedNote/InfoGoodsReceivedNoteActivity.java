package com.example.storagemanagement.activity.goodsReceivedNote;

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
import com.example.storagemanagement.activity.goodsReceivedNoteDetail.GoodsReceivedNoteDetailActivity;
import com.example.storagemanagement.dao.employee.EmployeeDao;
import com.example.storagemanagement.dao.employee.IEmployeeDao;
import com.example.storagemanagement.dao.goodsReceivedNote.GoodsReceivedNoteDao;
import com.example.storagemanagement.dao.goodsReceivedNote.IGoodsReceivedNoteDao;
import com.example.storagemanagement.dao.supplier.ISupplierDao;
import com.example.storagemanagement.dao.supplier.SupplierDao;
import com.example.storagemanagement.dao.warehouse.IWarehouseDao;
import com.example.storagemanagement.dao.warehouse.WarehouseDao;
import com.example.storagemanagement.model.Employee;
import com.example.storagemanagement.model.GoodsReceivedNote;
import com.example.storagemanagement.model.Supplier;
import com.example.storagemanagement.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class InfoGoodsReceivedNoteActivity extends AppCompatActivity {
    private EditText editTextGoodsReceivedNoteId;
    private EditText editTextDate;
    private EditText editTextNotice;
    private Spinner spinnerSupplier;
    private Spinner spinnerWarehouse;
    private Spinner spinnerEmployee;
    private Button buttonEdit;
    private Button buttonDelete;
    private Button buttonGoodsReceivedNoteDetail;
    private IGoodsReceivedNoteDao goodsReceivedNoteDao;
    private IWarehouseDao warehouseDao;
    private IEmployeeDao employeeDao;
    private ISupplierDao supplierDao;
    private List<Warehouse> warehouses;
    private List<Employee> employees;
    private List<Supplier> suppliers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_goods_received_note);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        warehouses = getAllWarehouse();
        employees = getAllEmployees();
        suppliers = getAllSuppliers();
        List<String> warehouseNames = addWarehouseNameToList(warehouses);
        ArrayAdapter<String> adapterWarehouse = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, warehouseNames);
        spinnerWarehouse.setAdapter(adapterWarehouse);
        List<String> employeeNames = addEmployeeNameToList(employees);
        ArrayAdapter<String> adapterEmployee = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, employeeNames);
        spinnerEmployee.setAdapter(adapterEmployee);
        List<String> supplierNames = addSupplierNameToList(suppliers);
        ArrayAdapter<String> adapterSupplier = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, supplierNames);
        spinnerSupplier.setAdapter(adapterSupplier);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final GoodsReceivedNote goodsReceivedNoteFromBundle = getGoodsReceivedNoteFromBundle(bundle);
            setDefaultValueForLayout(goodsReceivedNoteFromBundle);
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoodsReceivedNote goodsReceivedNote = getGoodsReceivedNoteFromLayout();
                    goodsReceivedNote.setId(goodsReceivedNoteFromBundle.getId());
                    boolean isUpdated = goodsReceivedNoteDao.updateById(goodsReceivedNote.getId(), goodsReceivedNote);
                    showMessage(isUpdated, MESSAGE_UPDATE_SUCCESS);
                }
            });
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopup(goodsReceivedNoteFromBundle.getId());
                }
            });
            buttonGoodsReceivedNoteDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(InfoGoodsReceivedNoteActivity.this, GoodsReceivedNoteDetailActivity.class);
                    intent.putExtra(GOODS_RECEIVED_NOTE_ID, goodsReceivedNoteFromBundle.getGoodsReceivedNoteId());
                    startActivity(intent);
                }
            });
        }
    }

    private void showPopup(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(InfoGoodsReceivedNoteActivity.this);
        final boolean isDeleted = goodsReceivedNoteDao.removeById(id);
        builder.setTitle(DELETE + GOODS_RECEIVED_NOTE);
        builder.setMessage(ARE_YOU_SURE);
        builder.setPositiveButton(YES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage(isDeleted, MESSAGE_DELETE_SUCCESS);
                Intent intent = new Intent(InfoGoodsReceivedNoteActivity.this, GoodsReceivedNoteActivity.class);
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

    private GoodsReceivedNote getGoodsReceivedNoteFromLayout() {
        String goodsReceivedNoteId = editTextGoodsReceivedNoteId.getText().toString();
        String date = editTextDate.getText().toString();
        String notice = editTextNotice.getText().toString();
        String warehouseName = spinnerWarehouse.getSelectedItem().toString();
        String supplierName = spinnerSupplier.getSelectedItem().toString();
        String employeeName = spinnerEmployee.getSelectedItem().toString();
        GoodsReceivedNote goodsReceivedNote = new GoodsReceivedNote(goodsReceivedNoteId, date, notice);
        Warehouse warehouse = warehouseDao.findByName(warehouseName);
        if (warehouse != null) {
            goodsReceivedNote.setWarehouseId(warehouse.getWarehouseId());
        }
        Supplier supplier = supplierDao.findByName(supplierName);
        if (supplier != null) {
            goodsReceivedNote.setSupplierId(supplier.getSupplierId());
        }
        Employee employee = employeeDao.findByName(employeeName);
        if (warehouse != null) {
            goodsReceivedNote.setEmployeeId(employee.getEmployeeId());
        }
        return goodsReceivedNote;
    }

    private void setDefaultValueForLayout(GoodsReceivedNote goodsReceivedNoteFromBundle) {
        editTextGoodsReceivedNoteId.setText(goodsReceivedNoteFromBundle.getGoodsReceivedNoteId());
        editTextDate.setText(goodsReceivedNoteFromBundle.getDate());
        editTextNotice.setText(goodsReceivedNoteFromBundle.getNotice());
        spinnerWarehouse.setSelection(getDefaultWarehouseSpinnerPosition(warehouses, goodsReceivedNoteFromBundle.getWarehouseId()));
        spinnerEmployee.setSelection(getDefaultEmployeeSpinnerPosition(employees, goodsReceivedNoteFromBundle.getEmployeeId()));
        spinnerSupplier.setSelection(getDefaultSupplierSpinnerPosition(suppliers, goodsReceivedNoteFromBundle.getSupplierId()));
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

    private int getDefaultEmployeeSpinnerPosition(List<Employee> employees, String employeeId) {
        int position = 0;
        if (employeeId != null) {
            for (int i = 0; i < employees.size(); i++) {
                if (employeeId.equals(employees.get(i).getEmployeeId())) {
                    position = i + 1;
                    break;
                }
            }
        }
        return position;
    }

    private int getDefaultSupplierSpinnerPosition(List<Supplier> suppliers, String supplierId) {
        int position = 0;
        if (supplierId != null) {
            for (int i = 0; i < suppliers.size(); i++) {
                if (supplierId.equals(suppliers.get(i).getSupplierId())) {
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
        String supplierId = bundle.getString(SUPPLIER_ID);
        String employeeId = bundle.getString(EMPLOYEE_ID);
        String notice = bundle.getString(NOTICE);
        return new GoodsReceivedNote(id, goodsReceivedNoteId, date, supplierId, warehouseId, employeeId, notice);
    }

    private List<String> addWarehouseNameToList(List<Warehouse> warehouses) {
        List<String> warehouseNames = new ArrayList<>();
        warehouseNames.add("");
        for (Warehouse warehouse : warehouses) {
            warehouseNames.add(warehouse.getName());
        }
        return warehouseNames;
    }


    private List<String> addEmployeeNameToList(List<Employee> employees) {
        List<String> employeeNames = new ArrayList<>();
        employeeNames.add("");
        for (Employee employee : employees) {
            employeeNames.add(employee.getName());
        }
        return employeeNames;
    }

    private List<String> addSupplierNameToList(List<Supplier> suppliers) {
        List<String> supplierNames = new ArrayList<>();
        supplierNames.add("");
        for (Supplier supplier : suppliers) {
            supplierNames.add(supplier.getName());
        }
        return supplierNames;
    }

    private List<Warehouse> getAllWarehouse() {
        return warehouseDao.findAll();
    }

    private List<Supplier> getAllSuppliers() {
        return supplierDao.findAll();
    }

    private List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }


    private void init() {
        editTextGoodsReceivedNoteId = findViewById(R.id.editTextGoodsReceivedNoteId);
        editTextDate = findViewById(R.id.editTextDate);
        editTextNotice = findViewById(R.id.editTextNotice);
        spinnerSupplier = findViewById(R.id.spinnerSupplier);
        spinnerWarehouse = findViewById(R.id.spinnerWarehouse);
        spinnerEmployee = findViewById(R.id.spinnerEmployee);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonGoodsReceivedNoteDetail = findViewById(R.id.buttonGoodsReceivedNoteDetail);
        goodsReceivedNoteDao = new GoodsReceivedNoteDao(this);
        warehouseDao = new WarehouseDao(this);
        employeeDao = new EmployeeDao(this);
        supplierDao = new SupplierDao(this);
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
