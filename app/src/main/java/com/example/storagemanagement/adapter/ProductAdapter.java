package com.example.storagemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.model.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.product_row_layout, null);
        }
        Product product = (Product) getItem(position);
        if (product != null) {
            TextView textViewProductId = convertView.findViewById(R.id.textViewProductId);
            TextView textViewProductName = convertView.findViewById(R.id.textViewProductName);
            TextView textViewProductDescription = convertView.findViewById(R.id.textViewDescription);
            TextView textViewProductGuarantee = convertView.findViewById(R.id.textViewGuarantee);
            textViewProductId.setText(product.getProductId());
            textViewProductName.setText(product.getName());
            textViewProductDescription.setText(product.getDescription());
            textViewProductGuarantee.setText(product.getGuarantee() + "");
        }
        return convertView;
    }
}
