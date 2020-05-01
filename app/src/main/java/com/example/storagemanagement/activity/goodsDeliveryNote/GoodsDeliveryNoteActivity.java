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

public class GoodsDeliveryNoteActivity extends AppCompatActivity {
    private ListView listViewGoodsDeliveryNote;
    private Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_delivery_note);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init() {
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
