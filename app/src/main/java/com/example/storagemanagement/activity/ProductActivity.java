package com.example.storagemanagement.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.adapter.ProductAdapter;
import com.example.storagemanagement.dao.product.IProductDao;
import com.example.storagemanagement.dao.product.ProductDao;
import com.example.storagemanagement.model.Product;

import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private ListView listViewProduct;
    private Button buttonAdd;
    private IProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        List<Product> products = productDao.findAll();
        ProductAdapter productAdapter = new ProductAdapter(ProductActivity.this, products);
        listViewProduct.setAdapter(productAdapter);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void init() {
        listViewProduct = findViewById(R.id.listViewProduct);
        buttonAdd = findViewById(R.id.buttonAdd);
        productDao = new ProductDao(this);
    }
}
