package com.example.storagemanagement.activity.supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.MainActivity;
import com.example.storagemanagement.dao.supplier.ISupplierDao;
import com.example.storagemanagement.dao.supplier.SupplierDao;
import com.example.storagemanagement.model.Supplier;

import static com.example.storagemanagement.config.StaticVariable.MESSAGE_CREATE_SUCCESS;
import static com.example.storagemanagement.config.StaticVariable.MESSAGE_FAIL;

public class AddSupplierActivity extends AppCompatActivity {
    private EditText editTextSupplierId;
    private EditText editTextSupplierName;
    private EditText editTextSupplierEmail;
    private EditText editTextSupplierAddress;
    private Button buttonSave;
    private ISupplierDao supplierDao;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Supplier sup = newSupplier();
                ShowMessage(sup);
                resetValue();
            }
        });
    }
    private void init(){
        editTextSupplierId = findViewById(R.id.editTextSupplierId);
        editTextSupplierName = findViewById(R.id.editTextSupplierName);
        editTextSupplierEmail = findViewById(R.id.editTextSupplierEmail);
        editTextSupplierAddress = findViewById(R.id.editTextSupplierAddress);
        buttonSave = findViewById(R.id.buttonEdit);
        supplierDao = new SupplierDao(this);
    }
    private void ShowMessage(Supplier supplier){
        if(supplier!=null){
            Toast.makeText(this,MESSAGE_CREATE_SUCCESS,Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,MESSAGE_FAIL,Toast.LENGTH_SHORT).show();
        }
    }
    private Supplier newSupplier(){
        String SupplierId = editTextSupplierId.getText().toString();
        String SupplierName = editTextSupplierName.getText().toString();
        String SupplierAddress = editTextSupplierAddress.getText().toString();
        String SupplierEmail = editTextSupplierEmail.getText().toString();
        Supplier sup = new Supplier(SupplierId,SupplierName,SupplierAddress,SupplierEmail);
        sup = supplierDao.insert(sup);
        return sup;
    }
    private void resetValue(){
        editTextSupplierAddress.setText("");
        editTextSupplierEmail.setText("");
        editTextSupplierName.setText("");
        editTextSupplierId.setText("");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(AddSupplierActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
