package com.example.storagemanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.storagemanagement.R;
import com.example.storagemanagement.activity.product.ProductActivity;

public class MainActivity extends AppCompatActivity {
    private Button buttonProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onClickEvent(buttonProduct, ProductActivity.class);
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
    }
}
