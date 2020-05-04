package com.example.storagemanagement.activity.goodsReceivedNoteDetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.goodsReceivedNote.InfoGoodsReceivedNoteActivity;
import com.example.storagemanagement.adapter.GoodsReceivedNoteDetailAdapter;
import com.example.storagemanagement.dao.goodsReceivedNoteDetail.GoodsReceivedNoteDetailDao;
import com.example.storagemanagement.dao.goodsReceivedNoteDetail.IGoodsReceivedNoteDetailDao;
import com.example.storagemanagement.model.GoodsReceivedNoteDetail;

import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

public class GoodsReceivedNoteDetailActivity extends AppCompatActivity {
    private ListView listViewGoodsReceivedNote;
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
        if (bundle != null) {
            final String goodsReceivedNoteId = bundle.getString(GOODS_RECEIVED_NOTE_ID);
            final List<GoodsReceivedNoteDetail> goodsDeliveryNoteDetails = goodsReceivedNoteDetailDao.findAllByGoodsReceivedNoteId(goodsReceivedNoteId);
            GoodsReceivedNoteDetailAdapter goodsReceivedNoteDetailAdapter = new GoodsReceivedNoteDetailAdapter(GoodsReceivedNoteDetailActivity.this, goodsDeliveryNoteDetails);
            listViewGoodsReceivedNote.setAdapter(goodsReceivedNoteDetailAdapter);
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GoodsReceivedNoteDetailActivity.this, AddGoodsReceivedNoteDetailActivity.class);
                    intent.putExtra(GOODS_RECEIVED_NOTE_ID, goodsReceivedNoteId);
                    startActivity(intent);
                }
            });
        }
    }

    private void init() {
        listViewGoodsReceivedNote = findViewById(R.id.listViewGoodsReceivedNoteDetail);
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
