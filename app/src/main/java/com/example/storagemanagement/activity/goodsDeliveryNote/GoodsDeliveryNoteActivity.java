package com.example.storagemanagement.activity.goodsDeliveryNote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.MainActivity;
import com.example.storagemanagement.dao.goodsDeliveryNote.GoodsDeliveryNoteDao;
import com.example.storagemanagement.dao.goodsDeliveryNote.IGoodsDeliveryNoteDao;
import com.example.storagemanagement.model.GoodsDeliveryNote;

import java.util.List;

public class GoodsDeliveryNoteActivity extends AppCompatActivity {
    private ListView listViewGoodsDeliveryNote;
    private Button buttonAdd;
    private IGoodsDeliveryNoteDao goodsDeliveryNoteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_delivery_note);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init() {
        listViewGoodsDeliveryNote = findViewById(R.id.listViewGoodsDeliveryNote);
        buttonAdd = findViewById(R.id.buttonAdd);
        goodsDeliveryNoteDao = new GoodsDeliveryNoteDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(GoodsDeliveryNoteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
