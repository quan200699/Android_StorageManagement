package com.example.storagemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.model.Customer;

import java.util.List;

public class CustomerAdapter extends BaseAdapter {
    private Context context;
    private List<Customer> customers;

    public CustomerAdapter(Context context, List<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Object getItem(int position) {
        return customers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.customer_row_layout, null);
        }
        Customer customer = (Customer) getItem(position);
        if (customer != null) {
            TextView textViewCustomerId = convertView.findViewById(R.id.textViewCustomerId);
            TextView textViewCustomerName = convertView.findViewById(R.id.textViewCustomerName);
            TextView textViewCustomerAddress = convertView.findViewById(R.id.textViewAddress);
            TextView textViewPhoneNumber = convertView.findViewById(R.id.textViewPhoneNumber);
            textViewCustomerId.setText(customer.getCustomerId());
            textViewCustomerName.setText(customer.getName());
            textViewCustomerAddress.setText(customer.getAddress());
            textViewPhoneNumber.setText(customer.getPhoneNumber());
        }
        return convertView;
    }
}
