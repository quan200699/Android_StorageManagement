package com.example.storagemanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.customer.CustomerActivity;
import com.example.storagemanagement.activity.goodsDeliveryNote.GoodsDeliveryNoteActivity;
import com.example.storagemanagement.activity.goodsReceivedNote.GoodsReceivedNoteActivity;
import com.example.storagemanagement.activity.product.ProductActivity;
import com.example.storagemanagement.activity.warehouse.WarehouseActivity;

public class MainActivity extends AppCompatActivity {
    private Button buttonProduct;
    private Button buttonCustomer;
    private Button buttonWarehouse;
    private Button buttonGoodsDeliveryNote;
    private Button buttonGoodsReceivedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onClickEvent(buttonProduct, ProductActivity.class);
        onClickEvent(buttonCustomer, CustomerActivity.class);
        onClickEvent(buttonWarehouse, WarehouseActivity.class);
        onClickEvent(buttonGoodsDeliveryNote, GoodsDeliveryNoteActivity.class);
        onClickEvent(buttonGoodsReceivedNote, GoodsReceivedNoteActivity.class);
    }

    private void onClickEvent(Button button, final Class<?> activity) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity);
                startActivity(intent);
            }
        });
    }

    private void init() {
        buttonProduct = findViewById(R.id.buttonProduct);
        buttonCustomer = findViewById(R.id.buttonCustomer);
        buttonWarehouse = findViewById(R.id.buttonWarehouse);
        buttonGoodsDeliveryNote = findViewById(R.id.buttonGoodsDeliveryNote);
        buttonGoodsReceivedNote = findViewById(R.id.buttonGoodsReceivedNote);
    }
}
