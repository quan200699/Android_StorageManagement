package com.example.storagemanagement.activity.goodsReceivedNoteDetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class InfoGoodsReceivedNoteDetailActivity extends AppCompatActivity {
    private EditText editTextGoodsReceivedNoteId;
    private EditText editTextQuantity;
    private EditText editTextPrice;
    private Spinner spinnerProduct;
    private Button buttonEdit;
    private Button buttonDelete;
    private IGoodsReceivedNoteDetailDao goodsReceivedNoteDetailDao;
    private IProductDao productDao;
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_goods_received_note_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        products = getAllProduct();
        List<String> productNames = addProductNameToList(products);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, productNames);
        spinnerProduct.setAdapter(adapter);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final GoodsReceivedNoteDetail goodsReceivedNoteDetailFromBundle = getGoodsReceivedNoteDetailFromBundle(bundle);
            setDefaultValueForLayout(goodsReceivedNoteDetailFromBundle);
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoodsReceivedNoteDetail goodsReceivedNoteDetail = getGoodsReceivedNoteDetailFromLayout();
                    goodsReceivedNoteDetail.setId(goodsReceivedNoteDetailFromBundle.getId());
                    boolean isUpdated = goodsReceivedNoteDetailDao.updateById(goodsReceivedNoteDetailFromBundle.getId(), goodsReceivedNoteDetail);
                    showMessage(isUpdated, MESSAGE_UPDATE_SUCCESS);
                }
            });
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopup(goodsReceivedNoteDetailFromBundle.getId());
                }
            });
        }
    }

    private void showPopup(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(InfoGoodsReceivedNoteDetailActivity.this);
        final boolean isDeleted = goodsReceivedNoteDetailDao.removeById(id);
        builder.setTitle(DELETE + GOODS_RECEIVED_NOTE_DETAIL);
        builder.setMessage(ARE_YOU_SURE);
        builder.setPositiveButton(YES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage(isDeleted, MESSAGE_DELETE_SUCCESS);
                Intent intent = new Intent(InfoGoodsReceivedNoteDetailActivity.this, GoodsReceivedNoteDetailActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(NO, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), CANCEL, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showMessage(boolean isSuccess, String message) {
        if (isSuccess) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

    private GoodsReceivedNoteDetail getGoodsReceivedNoteDetailFromLayout() {
        String goodsReceivedNoteId = editTextGoodsReceivedNoteId.getText().toString();
        String productId = spinnerProduct.getSelectedItem().toString();
        int quantity = Integer.parseInt(editTextQuantity.getText().toString());
        double price = Double.parseDouble(editTextPrice.getText().toString());
        return new GoodsReceivedNoteDetail(goodsReceivedNoteId, productId, quantity, price);
    }

    private void setDefaultValueForLayout(GoodsReceivedNoteDetail goodsReceivedNoteDetail) {
        editTextGoodsReceivedNoteId.setText(goodsReceivedNoteDetail.getGoodsReceivedNoteId());
        editTextPrice.setText(goodsReceivedNoteDetail.getPrice() + "");
        editTextQuantity.setText(goodsReceivedNoteDetail.getQuantity() + "");
        spinnerProduct.setSelection(getDefaultPositionForSpinner(products, goodsReceivedNoteDetail.getProductId()));
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

    private GoodsReceivedNoteDetail getGoodsReceivedNoteDetailFromBundle(Bundle bundle) {
        String goodsReceivedNoteId = bundle.getString(GOODS_RECEIVED_NOTE_ID);
        int id = bundle.getInt(ID);
        String productId = bundle.getString(PRODUCT_ID);
        int quantity = bundle.getInt(QUANTITY);
        double price = bundle.getDouble(PRICE);
        return new GoodsReceivedNoteDetail(id, goodsReceivedNoteId, productId, quantity, price);
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
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);
        goodsReceivedNoteDetailDao = new GoodsReceivedNoteDetailDao(this);
        productDao = new ProductDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(InfoGoodsReceivedNoteDetailActivity.this, GoodsReceivedNoteDetailActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
