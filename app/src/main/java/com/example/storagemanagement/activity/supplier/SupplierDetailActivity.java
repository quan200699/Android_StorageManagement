package com.example.storagemanagement.activity.supplier;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.MainActivity;
import com.example.storagemanagement.dao.supplier.ISupplierDao;
import com.example.storagemanagement.dao.supplier.SupplierDao;
import com.example.storagemanagement.model.Supplier;

import static com.example.storagemanagement.config.StaticVariable.ADDRESS;
import static com.example.storagemanagement.config.StaticVariable.ARE_YOU_SURE;
import static com.example.storagemanagement.config.StaticVariable.CANCEL;
import static com.example.storagemanagement.config.StaticVariable.EMAIL;
import static com.example.storagemanagement.config.StaticVariable.ID;
import static com.example.storagemanagement.config.StaticVariable.MESSAGE_DELETE_SUCCESS;
import static com.example.storagemanagement.config.StaticVariable.MESSAGE_FAIL;
import static com.example.storagemanagement.config.StaticVariable.MESSAGE_UPDATE_SUCCESS;
import static com.example.storagemanagement.config.StaticVariable.NAME;
import static com.example.storagemanagement.config.StaticVariable.NO;
import static com.example.storagemanagement.config.StaticVariable.SUPPLIER_ID;
import static com.example.storagemanagement.config.StaticVariable.YES;

public class SupplierDetailActivity extends AppCompatActivity {
    private EditText editTextSupplierId;
    private EditText editTextSupplierName;
    private EditText editTextSupplierAddress;
    private EditText editTextSupplierEmail;
    private Button buttonEdit;
    private Button buttonDelete;
    private ISupplierDao supplierDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            final Supplier supplier = getSupplierFromBundle(bundle);
            setDefaultEditTextValue(supplier);
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Supplier newSup = getSupplierFromLayout();
                    newSup.setId(supplier.getId());
                    Boolean isUpdated = supplierDao.updateById(supplier.getId(),newSup);
                    showMessage(isUpdated,MESSAGE_UPDATE_SUCCESS);
                }
            });
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopup(supplier.getId());
                }
            });
        }
    }
    private void showPopup(final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(SupplierDetailActivity.this);
        final boolean isDeleted = supplierDao.removeById(id);
        builder.setTitle("Thông tin nhà cung cấp");
        builder.setMessage(ARE_YOU_SURE);
        builder.setPositiveButton(YES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage(isDeleted,MESSAGE_DELETE_SUCCESS);
                Intent intent = new Intent(SupplierDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(NO, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),CANCEL,Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void init(){
        editTextSupplierAddress = findViewById(R.id.editTextSupplierAddress);
        editTextSupplierEmail = findViewById(R.id.editTextSupplierEmail);
        editTextSupplierId = findViewById(R.id.editTextSupplierId);
        editTextSupplierName = findViewById(R.id.editTextSupplierName);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);
        supplierDao = new SupplierDao(this);
    }
    private void setDefaultEditTextValue(Supplier supplier){
        editTextSupplierName.setText(supplier.getName());
        editTextSupplierId.setText(supplier.getSupplierId());
        editTextSupplierEmail.setText(supplier.getEmail());
        editTextSupplierAddress.setText(supplier.getAddress());
    }
    private void showMessage(boolean isSuccess , String message){
        if(isSuccess){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),MESSAGE_FAIL,Toast.LENGTH_SHORT).show();
        }
    }
    private Supplier getSupplierFromBundle(Bundle bundle){
        int id = bundle.getInt(ID);
        String supplierID = bundle.getString(SUPPLIER_ID);
        String supplierName = bundle.getString(NAME);
        String supplierEmail = bundle.getString(EMAIL);
        String supplierAddress = bundle.getString(ADDRESS);
        return new Supplier(id,supplierID,supplierName,supplierAddress,supplierEmail);
    }
    private Supplier getSupplierFromLayout(){
        String supplierId = editTextSupplierId.getText().toString();
        String supplierName = editTextSupplierName.getText().toString();
        String supplierEmail = editTextSupplierEmail.getText().toString();
        String supplierAddress = editTextSupplierAddress.getText().toString();
        return new Supplier(supplierId,supplierName,supplierAddress,supplierEmail);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(SupplierDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
