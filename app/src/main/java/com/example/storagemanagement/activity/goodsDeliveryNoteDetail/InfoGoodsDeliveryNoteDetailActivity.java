package com.example.storagemanagement.activity.goodsDeliveryNoteDetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.storagemanagement.R;
import com.example.storagemanagement.dao.goodsDeliveryNoteDetail.GoodsDeliveryNoteDetailDao;
import com.example.storagemanagement.dao.goodsDeliveryNoteDetail.IGoodsDeliveryNoteDetailDao;
import com.example.storagemanagement.dao.product.IProductDao;
import com.example.storagemanagement.dao.product.ProductDao;
import com.example.storagemanagement.model.GoodsDeliveryNote;
import com.example.storagemanagement.model.GoodsDeliveryNoteDetail;
import com.example.storagemanagement.model.Product;

import java.util.ArrayList;
import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class InfoGoodsDeliveryNoteDetailActivity extends AppCompatActivity {
    private EditText editTextGoodsDeliveryNoteId;
    private EditText editTextQuantity;
    private EditText editTextPrice;
    private Spinner spinnerProduct;
    private Button buttonEdit;
    private Button buttonDelete;
    private IGoodsDeliveryNoteDetailDao goodsDeliveryNoteDetailDao;
    private IProductDao productDao;
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_goods_delivery_note_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        products = getAllProduct();
        List<String> productNames = addProductNameToList(products);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, productNames);
        spinnerProduct.setAdapter(adapter);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            GoodsDeliveryNoteDetail goodsDeliveryNoteDetailFromBundle = getGoodsDeliveryNoteDetailFromBundle(bundle);
            setDefaultValueForLayout(goodsDeliveryNoteDetailFromBundle);
        }
    }

    private void setDefaultValueForLayout(GoodsDeliveryNoteDetail goodsDeliveryNoteDetail) {
        editTextGoodsDeliveryNoteId.setText(goodsDeliveryNoteDetail.getGoodsDeliveryNoteId());
        editTextPrice.setText(goodsDeliveryNoteDetail.getPrice() + "");
        editTextQuantity.setText(goodsDeliveryNoteDetail.getQuantity() + "");
        spinnerProduct.setSelection(getDefaultPositionForSpinner(products, goodsDeliveryNoteDetail.getProductId()));
    }

    private int getDefaultPositionForSpinner(List<Product> products, String productId) {
        int position = 0;
        if (productId != null) {
            for (int i = 0; i < products.size(); i++) {
                if (productId.equals(products.get(i).getProductId())) {
                    position = i + 1;
                    break;
                }
            }
        }
        return position;
    }

    private GoodsDeliveryNoteDetail getGoodsDeliveryNoteDetailFromBundle(Bundle bundle) {
        String goodsDeliveryNoteId = bundle.getString(GOODS_DELIVERY_NOTE_ID);
        int id = bundle.getInt(ID);
        String productId = bundle.getString(PRODUCT_ID);
        int quantity = bundle.getInt(QUANTITY);
        double price = bundle.getDouble(PRICE);
        return new GoodsDeliveryNoteDetail(id, goodsDeliveryNoteId, productId, quantity, price);
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
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);
        goodsDeliveryNoteDetailDao = new GoodsDeliveryNoteDetailDao(this);
        productDao = new ProductDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(InfoGoodsDeliveryNoteDetailActivity.this, GoodsDeliveryNoteDetailActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
