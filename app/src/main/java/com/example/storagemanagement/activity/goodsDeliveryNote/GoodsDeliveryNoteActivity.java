package com.example.storagemanagement.activity.goodsDeliveryNote;

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
import com.example.storagemanagement.activity.MainActivity;
import com.example.storagemanagement.adapter.GoodsDeliveryNoteAdapter;
import com.example.storagemanagement.dao.goodsDeliveryNote.GoodsDeliveryNoteDao;
import com.example.storagemanagement.dao.goodsDeliveryNote.IGoodsDeliveryNoteDao;
import com.example.storagemanagement.model.GoodsDeliveryNote;

import java.util.List;

import static com.example.storagemanagement.config.StaticVariable.*;

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
        final List<GoodsDeliveryNote> goodsDeliveryNotes = goodsDeliveryNoteDao.findAll();
        GoodsDeliveryNoteAdapter goodsDeliveryNoteAdapter = new GoodsDeliveryNoteAdapter(GoodsDeliveryNoteActivity.this, goodsDeliveryNotes);
        listViewGoodsDeliveryNote.setAdapter(goodsDeliveryNoteAdapter);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsDeliveryNoteActivity.this, AddGoodsDeliveryNoteActivity.class);
                startActivity(intent);
            }
        });
        listViewGoodsDeliveryNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GoodsDeliveryNoteActivity.this, GoodsDeliveryNoteDetailActivity.class);
                intent.putExtra(ID, goodsDeliveryNotes.get(position).getId());
                intent.putExtra(GOODS_DELIVERY_NOTE_ID, goodsDeliveryNotes.get(position).getGoodsDeliveryNoteId());
                intent.putExtra(DATE, goodsDeliveryNotes.get(position).getDate());
                intent.putExtra(CUSTOMER_ID, goodsDeliveryNotes.get(position).getCustomerId());
                intent.putExtra(WAREHOUSE_ID, goodsDeliveryNotes.get(position).getWareHouseId());
                intent.putExtra(NOTICE, goodsDeliveryNotes.get(position).getNotice());
                startActivity(intent);
            }
        });
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
