package com.example.storagemanagement.activity.product;

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
import com.example.storagemanagement.dao.product.IProductDao;
import com.example.storagemanagement.dao.product.ProductDao;
import com.example.storagemanagement.model.Product;

import static com.example.storagemanagement.config.StaticVariable.*;

public class AddProductActivity extends AppCompatActivity {
    private EditText editTextProductId;
    private EditText editTextProductName;
    private EditText editTextProductDescription;
    private EditText editTextProductGuarantee;
    private Button buttonSave;
    private IProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = createNewProduct();
                showMessage(product);
                resetForm();
            }
        });
    }

    private void showMessage(Product product) {
        if (product != null) {
            Toast.makeText(this, MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

    private void resetForm() {
        editTextProductId.setText("");
        editTextProductName.setText("");
        editTextProductGuarantee.setText("");
        editTextProductDescription.setText("");
    }

    private Product createNewProduct() {
        String productId = editTextProductId.getText().toString();
        String productName = editTextProductName.getText().toString();
        int productGuarantee = Integer.parseInt(editTextProductGuarantee.getText().toString());
        String productDescription = editTextProductDescription.getText().toString();
        Product product = new Product(productId, productName, productDescription, productGuarantee);
        product = productDao.insert(product);
        return product;
    }

    private void init() {
        editTextProductId = findViewById(R.id.editTextProductId);
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextProductGuarantee = findViewById(R.id.editTextGuarantee);
        editTextProductDescription = findViewById(R.id.editTextProductDescription);
        buttonSave = findViewById(R.id.buttonSave);
        productDao = new ProductDao(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddProductActivity.this, ProductActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
