package com.example.storagemanagement.activity.goodsDeliveryNoteDetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
            final String goodsDeliveryNoteId = bundle.getString(GOODS_DELIVERY_NOTE_ID);
            final List<GoodsDeliveryNoteDetail> goodsDeliveryNoteDetails = goodsDeliveryNoteDetailDao.findAllByGoodsDeliveryNoteId(goodsDeliveryNoteId);
            GoodsDeliveryNoteDetailAdapter goodsDeliveryNoteDetailAdapter = new GoodsDeliveryNoteDetailAdapter(GoodsDeliveryNoteDetailActivity.this, goodsDeliveryNoteDetails);
            listViewGoodsDeliveryNote.setAdapter(goodsDeliveryNoteDetailAdapter);
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GoodsDeliveryNoteDetailActivity.this, AddGoodsDeliveryNoteDetailActivity.class);
                    intent.putExtra(GOODS_DELIVERY_NOTE_ID, goodsDeliveryNoteId);
                    startActivity(intent);
                }
            });
            listViewGoodsDeliveryNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(GoodsDeliveryNoteDetailActivity.this, InfoGoodsDeliveryNoteDetailActivity.class);
                    intent.putExtra(GOODS_DELIVERY_NOTE_ID, goodsDeliveryNoteId);
                    intent.putExtra(ID, goodsDeliveryNoteDetails.get(position).getId());
                    intent.putExtra(PRODUCT_ID, goodsDeliveryNoteDetails.get(position).getProductId());
                    intent.putExtra(QUANTITY, goodsDeliveryNoteDetails.get(position).getQuantity());
                    intent.putExtra(PRICE, goodsDeliveryNoteDetails.get(position).getPrice());
                    startActivity(intent);
                }
            });
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
