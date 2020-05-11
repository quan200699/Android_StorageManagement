package com.example.storagemanagement.activity.supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.MainActivity;
import com.example.storagemanagement.adapter.SupplierAdapter;
import com.example.storagemanagement.dao.supplier.ISupplierDao;
import com.example.storagemanagement.dao.supplier.SupplierDao;
import com.example.storagemanagement.model.Supplier;
import static com.example.storagemanagement.config.StaticVariable.*;
import java.util.List;

public class SupplierActivity extends AppCompatActivity {
    private ListView ListViewSupplier;
    private Button buttonAdd;
    private ISupplierDao supplierDao;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        final List<Supplier> listSup = supplierDao.findAll();
        SupplierAdapter supplierAdapter = new SupplierAdapter(SupplierActivity.this,listSup);
        ListViewSupplier.setAdapter(supplierAdapter);
        ListViewSupplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SupplierActivity.this,SupplierDetailActivity.class);
                intent.putExtra(ID,listSup.get(position).getId());
                intent.putExtra(SUPPLIER_ID,listSup.get(position).getSupplierId());
                intent.putExtra(NAME,listSup.get(position).getName());
                intent.putExtra(EMAIL,listSup.get(position).getEmail());
                intent.putExtra(ADDRESS,listSup.get(position).getEmail());
                startActivity(intent);
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupplierActivity.this,AddSupplierActivity.class);
                startActivity(intent);
            }
        });

    }
    private void init(){
        ListViewSupplier = findViewById(R.id.listViewSupplier);
        buttonAdd = findViewById(R.id.buttonAdd);
        supplierDao = new SupplierDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(SupplierActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
