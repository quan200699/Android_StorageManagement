package com.example.storagemanagement.activity.goodsDeliveryNoteDetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.storagemanagement.dao.goodsDeliveryNoteDetail.GoodsDeliveryNoteDetailDao;
import com.example.storagemanagement.dao.goodsDeliveryNoteDetail.IGoodsDeliveryNoteDetailDao;
import com.example.storagemanagement.dao.product.IProductDao;
import com.example.storagemanagement.dao.product.ProductDao;
import com.example.storagemanagement.model.GoodsDeliveryNoteDetail;
import com.example.storagemanagement.model.Product;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class AddGoodsDeliveryNoteDetailActivity extends AppCompatActivity {
    private EditText editTextGoodsDeliveryNoteId;
    private EditText editTextQuantity;
    private EditText editTextPrice;
    private Spinner spinnerProduct;
    private Button buttonSave;
    private IGoodsDeliveryNoteDetailDao goodsDeliveryNoteDetailDao;
    private IProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods_delivery_note_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        List<Product> products = getAllProduct();
        List<String> productNames = addProductNameToList(products);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, productNames);
        spinnerProduct.setAdapter(adapter);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final String goodsDeliveryNoteId = bundle.getString(GOODS_DELIVERY_NOTE_ID);
            editTextGoodsDeliveryNoteId.setText(goodsDeliveryNoteId);
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoodsDeliveryNoteDetail goodsDeliveryNoteDetail = createNewGoodsDeliveryNoteDetail(goodsDeliveryNoteId);
                    showMessage(goodsDeliveryNoteDetail);
                    resetForm();
                }
            });
        }
    }

    private void resetForm() {
        editTextGoodsDeliveryNoteId.setText("");
        editTextPrice.setText("");
        editTextQuantity.setText("");
        spinnerProduct.setSelection(0);
    }

    private void showMessage(GoodsDeliveryNoteDetail goodsDeliveryNoteDetail) {
        if (goodsDeliveryNoteDetail != null) {
            Toast.makeText(this, MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

    private GoodsDeliveryNoteDetail createNewGoodsDeliveryNoteDetail(String goodsDeliveryNoteId) {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString());
        double price = Double.parseDouble(editTextPrice.getText().toString());
        String productName = spinnerProduct.getSelectedItem().toString();
        GoodsDeliveryNoteDetail goodsDeliveryNoteDetail = new GoodsDeliveryNoteDetail(goodsDeliveryNoteId, quantity, price);
        Product product = productDao.findByName(productName);
        if (productName != null) {
            goodsDeliveryNoteDetail.setProductId(product.getProductId());
        }
        return goodsDeliveryNoteDetailDao.insert(goodsDeliveryNoteDetail);
    }

    private List<String> addProductNameToList(List<Product> products) {
        List<String> productNames = new ArrayList<>();
        productNames.add("");
        for (Product product : products) {
            productNames.add(product.getName());
        }
        return productNames;
    }

    private List<Product> getAllProduct() {
        return productDao.findAll();
    }

    private void init() {
        editTextGoodsDeliveryNoteId = findViewById(R.id.editTextGoodsDeliveryNoteId);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextPrice = findViewById(R.id.editTextPrice);
        spinnerProduct = findViewById(R.id.spinnerProduct);
        buttonSave = findViewById(R.id.buttonSave);
        goodsDeliveryNoteDetailDao = new GoodsDeliveryNoteDetailDao(this);
        productDao = new ProductDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddGoodsDeliveryNoteDetailActivity.this, GoodsDeliveryNoteDetailActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
