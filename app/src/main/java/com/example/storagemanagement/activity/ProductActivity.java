package com.example.storagemanagement.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.adapter.ProductAdapter;
import com.example.storagemanagement.dao.product.IProductDao;
import com.example.storagemanagement.dao.product.ProductDao;
import com.example.storagemanagement.model.Product;

import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

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
        final List<Product> products = productDao.findAll();
        ProductAdapter productAdapter = new ProductAdapter(ProductActivity.this, products);
        listViewProduct.setAdapter(productAdapter);
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductActivity.this, ProductDetailActivity.class);
                intent.putExtra(PRODUCT_ID, products.get(position).getProductId());
                intent.putExtra(NAME, products.get(position).getName());
                intent.putExtra(DESCRIPTION, products.get(position).getDescription());
                intent.putExtra(GUARANTEE, products.get(position).getGuarantee() + "");
                startActivity(intent);
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        listViewProduct = findViewById(R.id.listViewProduct);
        buttonAdd = findViewById(R.id.buttonAdd);
        productDao = new ProductDao(this);
    }
}
