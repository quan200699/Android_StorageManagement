package com.example.storagemanagement.activity.goodsDeliveryNoteDetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.storagemanagement.R;
import com.example.storagemanagement.dao.goodsDeliveryNoteDetail.GoodsDeliveryNoteDetailDao;
import com.example.storagemanagement.dao.goodsDeliveryNoteDetail.IGoodsDeliveryNoteDetailDao;

import static com.example.storagemanagement.config.StaticVariable.*;

public class AddGoodsDeliveryNoteDetailActivity extends AppCompatActivity {
    private EditText editTextGoodsDeliveryNoteId;
    private EditText editTextQuantity;
    private EditText editTextPrice;
    private Spinner spinnerProduct;
    private Button buttonSave;
    private IGoodsDeliveryNoteDetailDao goodsDeliveryNoteDetailDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods_delivery_note_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String goodsDeliveryNoteId = bundle.getString(GOODS_DELIVERY_NOTE_ID);
            editTextGoodsDeliveryNoteId.setText(goodsDeliveryNoteId);

        }
    }

    private void init() {
        editTextGoodsDeliveryNoteId = findViewById(R.id.editTextGoodsDeliveryNoteId);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextPrice = findViewById(R.id.editTextPrice);
        spinnerProduct = findViewById(R.id.spinnerProduct);
        buttonSave = findViewById(R.id.buttonSave);
        goodsDeliveryNoteDetailDao = new GoodsDeliveryNoteDetailDao(this);
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
