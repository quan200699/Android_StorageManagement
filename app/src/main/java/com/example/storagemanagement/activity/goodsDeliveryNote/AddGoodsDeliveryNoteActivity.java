package com.example.storagemanagement.activity.goodsDeliveryNote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.storagemanagement.R;
import com.example.storagemanagement.dao.goodsDeliveryNote.GoodsDeliveryNoteDao;
import com.example.storagemanagement.dao.goodsDeliveryNote.IGoodsDeliveryNoteDao;

public class AddGoodsDeliveryNoteActivity extends AppCompatActivity {
    private EditText editTextGoodsDeliveryNoteId;
    private EditText editTextDate;
    private EditText editTextNotice;
    private Spinner spinnerCustomer;
    private Spinner spinnerWarehouse;
    private Spinner spinnerEmployee;
    private IGoodsDeliveryNoteDao goodsDeliveryNoteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods_delivery_note);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init() {
        editTextGoodsDeliveryNoteId = findViewById(R.id.editTextGoodsDeliveryNoteId);
        editTextDate = findViewById(R.id.editTextDate);
        editTextNotice = findViewById(R.id.editTextNotice);
        spinnerCustomer = findViewById(R.id.spinnerCustomer);
        spinnerWarehouse = findViewById(R.id.spinnerWarehouse);
        spinnerEmployee = findViewById(R.id.spinnerEmployee);
        goodsDeliveryNoteDao = new GoodsDeliveryNoteDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddGoodsDeliveryNoteActivity.this, GoodsDeliveryNoteActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
