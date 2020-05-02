package com.example.storagemanagement.activity.goodsDeliveryNoteDetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.goodsDeliveryNote.InfoGoodsDeliveryNoteActivity;
import com.example.storagemanagement.adapter.GoodsDeliveryNoteDetailAdapter;
import com.example.storagemanagement.dao.goodsDeliveryNoteDetail.GoodsDeliveryNoteDetailDao;
import com.example.storagemanagement.dao.goodsDeliveryNoteDetail.IGoodsDeliveryNoteDetailDao;
import com.example.storagemanagement.model.GoodsDeliveryNoteDetail;

import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class GoodsDeliveryNoteDetailActivity extends AppCompatActivity {
    private ListView listViewGoodsDeliveryNote;
    private Button buttonAdd;
    private IGoodsDeliveryNoteDetailDao goodsDeliveryNoteDetailDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_delivery_note_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String goodsDeliveryNoteId = bundle.getString(GOODS_DELIVERY_NOTE_ID);
            List<GoodsDeliveryNoteDetail> goodsDeliveryNoteDetails = goodsDeliveryNoteDetailDao.findAllByGoodsDeliveryNoteId(goodsDeliveryNoteId);
            GoodsDeliveryNoteDetailAdapter goodsDeliveryNoteDetailAdapter = new GoodsDeliveryNoteDetailAdapter(GoodsDeliveryNoteDetailActivity.this, goodsDeliveryNoteDetails);
            listViewGoodsDeliveryNote.setAdapter(goodsDeliveryNoteDetailAdapter);
        }
    }

    private void init() {
        listViewGoodsDeliveryNote = findViewById(R.id.listViewGoodsDeliveryNoteDetail);
        buttonAdd = findViewById(R.id.buttonAdd);
        goodsDeliveryNoteDetailDao = new GoodsDeliveryNoteDetailDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(GoodsDeliveryNoteDetailActivity.this, InfoGoodsDeliveryNoteActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
