package com.example.storagemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.storagemanagement.R;
import com.example.storagemanagement.model.Supplier;

import java.util.List;

public class SupplierAdapter extends BaseAdapter {
    private Context context;
    private List<Supplier> sup;
    public SupplierAdapter(Context context, List<Supplier> suppliers){
        this.context=context;
        this.sup = suppliers;
    }
    @Override
    public int getCount() {
        return sup.size();
    }

    @Override
    public Object getItem(int position) {
        return sup.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.supplier_row_layout,null);
        }
        Supplier supplier = (Supplier) getItem(position);
        if (supplier!=null){
            TextView textViewSupplierId = convertView.findViewById(R.id.textViewSupplierId);
            TextView textViewSupplierName = convertView.findViewById(R.id.textViewSupplierName);
            TextView textViewSupplierEmail = convertView.findViewById(R.id.textViewSupplierEmail);
            TextView textViewSupplierAddress = convertView.findViewById(R.id.textViewSupplierAddress);
            textViewSupplierId.setText(supplier.getId()+"");
            textViewSupplierName.setText(supplier.getName());
            textViewSupplierEmail.setText(supplier.getEmail());
            textViewSupplierAddress.setText(supplier.getAddress());

        }
        return convertView;
    }
}
