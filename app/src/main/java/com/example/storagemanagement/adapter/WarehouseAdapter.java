package com.example.storagemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.model.Warehouse;

import java.util.List;

public class WarehouseAdapter extends BaseAdapter {
    private List<Warehouse> warehouses;
    private Context context;

    public WarehouseAdapter(List<Warehouse> warehouses, Context context) {
        this.warehouses = warehouses;
        this.context = context;
    }

    @Override
    public int getCount() {
        return warehouses.size();
    }

    @Override
    public Object getItem(int position) {
        return warehouses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.warehouse_row_layout, null);
        }
        Warehouse warehouse = (Warehouse) getItem(position);
        if (warehouse != null) {
            TextView textViewWarehouseId = convertView.findViewById(R.id.textViewWarehouseId);
            TextView textViewWarehouseName = convertView.findViewById(R.id.textViewWarehouseName);
            TextView textViewWarehouseAddress = convertView.findViewById(R.id.textViewWarehouseAddress);
            textViewWarehouseId.setText(warehouse.getWarehouseId());
            textViewWarehouseName.setText(warehouse.getName());
            textViewWarehouseAddress.setText(warehouse.getAddress());
        }
        return convertView;
    }
}
