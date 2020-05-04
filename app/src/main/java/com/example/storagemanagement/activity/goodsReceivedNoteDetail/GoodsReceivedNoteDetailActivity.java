package com.example.storagemanagement.activity.goodsReceivedNoteDetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.goodsReceivedNote.InfoGoodsReceivedNoteActivity;
import com.example.storagemanagement.dao.goodsReceivedNoteDetail.GoodsReceivedNoteDetailDao;
import com.example.storagemanagement.dao.goodsReceivedNoteDetail.IGoodsReceivedNoteDetailDao;

public class GoodsReceivedNoteDetailActivity extends AppCompatActivity {
    private ListView listViewGoodsDeliveryNote;
    private Button buttonAdd;
    private IGoodsReceivedNoteDetailDao goodsReceivedNoteDetailDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_received_note_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        Bundle bundle = getIntent().getExtras();
    }

    private void init() {
        listViewGoodsDeliveryNote = findViewById(R.id.listViewGoodsDeliveryNoteDetail);
        buttonAdd = findViewById(R.id.buttonAdd);
        goodsReceivedNoteDetailDao = new GoodsReceivedNoteDetailDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(GoodsReceivedNoteDetailActivity.this, InfoGoodsReceivedNoteActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
