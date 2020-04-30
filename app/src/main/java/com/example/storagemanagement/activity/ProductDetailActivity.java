package com.example.storagemanagement.activity;

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

public class ProductDetailActivity extends AppCompatActivity {
    private EditText editTextProductId;
    private EditText editTextProductName;
    private EditText editTextProductDescription;
    private EditText editTextProductGuarantee;
    private Button buttonEdit;
    private Button buttonDelete;
    private IProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final Product productInfoFromBundle = getProductInfoFromBundle(bundle);
            setEditTextDefaultValue(productInfoFromBundle);
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product newProduct = getProductInfoFromLayout();
                    newProduct.setId(productInfoFromBundle.getId());
                    boolean isUpdated = productDao.updateById(productInfoFromBundle.getId(), newProduct);
                    showMessage(isUpdated);
                }
            });
        }
    }

    private void showMessage(boolean isSuccess) {
        if (isSuccess) {
            Toast.makeText(getApplicationContext(), MESSAGE_UPDATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

    private Product getProductInfoFromLayout() {
        String productId = editTextProductId.getText().toString();
        String productName = editTextProductName.getText().toString();
        String productDescription = editTextProductDescription.getText().toString();
        int productGuarantee = Integer.parseInt(editTextProductGuarantee.getText().toString());
        return new Product(productId, productName, productDescription, productGuarantee);
    }

    private void setEditTextDefaultValue(Product product) {
        editTextProductId.setText(product.getProductId());
        editTextProductName.setText(product.getName());
        editTextProductDescription.setText(product.getDescription());
        editTextProductGuarantee.setText(product.getGuarantee() + "");
    }

    private Product getProductInfoFromBundle(Bundle bundle) {
        int id = bundle.getInt(ID);
        String productId = bundle.getString(PRODUCT_ID);
        String productName = bundle.getString(NAME);
        String productDescription = bundle.getString(DESCRIPTION);
        int productGuarantee = bundle.getInt(GUARANTEE);
        return new Product(id, productId, productName, productDescription, productGuarantee);
    }

    private void init() {
        editTextProductId = findViewById(R.id.editTextProductId);
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextProductDescription = findViewById(R.id.editTextProductDescription);
        editTextProductGuarantee = findViewById(R.id.editTextGuarantee);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);
        productDao = new ProductDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ProductDetailActivity.this, ProductActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
