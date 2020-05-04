package com.example.storagemanagement.activity.goodsReceivedNoteDetail;

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
import com.example.storagemanagement.dao.goodsReceivedNoteDetail.GoodsReceivedNoteDetailDao;
import com.example.storagemanagement.dao.goodsReceivedNoteDetail.IGoodsReceivedNoteDetailDao;
import com.example.storagemanagement.dao.product.IProductDao;
import com.example.storagemanagement.dao.product.ProductDao;
import com.example.storagemanagement.model.GoodsReceivedNoteDetail;
import com.example.storagemanagement.model.Product;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class AddGoodsReceivedNoteDetailActivity extends AppCompatActivity {
    private EditText editTextGoodsReceivedNoteId;
    private EditText editTextQuantity;
    private EditText editTextPrice;
    private Spinner spinnerProduct;
    private Button buttonSave;
    private IGoodsReceivedNoteDetailDao goodsReceivedNoteDetailDao;
    private IProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods_received_note_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        List<Product> products = getAllProduct();
        List<String> productNames = addProductNameToList(products);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, productNames);
        spinnerProduct.setAdapter(adapter);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final String goodsReceivedNoteId = bundle.getString(GOODS_RECEIVED_NOTE_ID);
            editTextGoodsReceivedNoteId.setText(goodsReceivedNoteId);
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoodsReceivedNoteDetail goodsReceivedNoteDetail = createNewGoodsReceivedNoteDetail(goodsReceivedNoteId);
                    showMessage(goodsReceivedNoteDetail);
                    resetForm();
                }
            });
        }
    }

    private void resetForm() {
        editTextGoodsReceivedNoteId.setText("");
        editTextPrice.setText("");
        editTextQuantity.setText("");
        spinnerProduct.setSelection(0);
    }

    private void showMessage(GoodsReceivedNoteDetail goodsReceivedNoteDetail) {
        if (goodsReceivedNoteDetail != null) {
            Toast.makeText(this, MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

    private GoodsReceivedNoteDetail createNewGoodsReceivedNoteDetail(String goodsReceivedNoteId) {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString());
        double price = Double.parseDouble(editTextPrice.getText().toString());
        String productName = spinnerProduct.getSelectedItem().toString();
        GoodsReceivedNoteDetail goodsReceivedNoteDetail = new GoodsReceivedNoteDetail(goodsReceivedNoteId, quantity, price);
        Product product = productDao.findByName(productName);
        if (productName != null) {
            goodsReceivedNoteDetail.setProductId(product.getProductId());
        }
        return goodsReceivedNoteDetailDao.insert(goodsReceivedNoteDetail);
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
        editTextGoodsReceivedNoteId = findViewById(R.id.editTextGoodsReceivedNoteId);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextPrice = findViewById(R.id.editTextPrice);
        spinnerProduct = findViewById(R.id.spinnerProduct);
        buttonSave = findViewById(R.id.buttonSave);
        goodsReceivedNoteDetailDao = new GoodsReceivedNoteDetailDao(this);
        productDao = new ProductDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddGoodsReceivedNoteDetailActivity.this, GoodsReceivedNoteDetailActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
