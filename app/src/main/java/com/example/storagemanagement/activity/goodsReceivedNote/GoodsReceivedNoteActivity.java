package com.example.storagemanagement.activity.goodsReceivedNote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.MainActivity;
import com.example.storagemanagement.adapter.GoodsReceivedNoteAdapter;
import com.example.storagemanagement.dao.goodsReceivedNote.GoodsReceivedNoteDao;
import com.example.storagemanagement.dao.goodsReceivedNote.IGoodsReceivedNoteDao;
import com.example.storagemanagement.model.GoodsReceivedNote;

import java.util.List;

public class GoodsReceivedNoteActivity extends AppCompatActivity {
    private ListView listViewGoodsReceivedNote;
    private Button buttonAdd;
    private IGoodsReceivedNoteDao goodsReceivedNoteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_received_note);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        final List<GoodsReceivedNote> goodsReceivedNotes = goodsReceivedNoteDao.findAll();
        GoodsReceivedNoteAdapter goodsReceivedNoteAdapter = new GoodsReceivedNoteAdapter(GoodsReceivedNoteActivity.this, goodsReceivedNotes);
        listViewGoodsReceivedNote.setAdapter(goodsReceivedNoteAdapter);

    }

    private void init() {
        listViewGoodsReceivedNote = findViewById(R.id.listViewGoodsReceivedNote);
        buttonAdd = findViewById(R.id.buttonAdd);
        goodsReceivedNoteDao = new GoodsReceivedNoteDao(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(GoodsReceivedNoteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
