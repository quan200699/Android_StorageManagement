package com.example.storagemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.model.Employee;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {
    private Context context;
    private List<Employee> employees;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.employee_row_layout, null);
        }
        Employee employee = (Employee) getItem(position);
        if (employee != null) {
            TextView textViewEmployeeId = convertView.findViewById(R.id.textViewEmployeeId);
            TextView textViewEmployeeName = convertView.findViewById(R.id.textViewEmployeeName);
            TextView textViewBirthday = convertView.findViewById(R.id.textViewBirthday);
            TextView textViewSex = convertView.findViewById(R.id.textViewSex);
            TextView textViewAddress = convertView.findViewById(R.id.textViewAddress);
            textViewEmployeeId.setText(employee.getEmployeeId());
            textViewEmployeeName.setText(employee.getName());
            textViewBirthday.setText(employee.getBirthday());
            textViewSex.setText(employee.getSex());
            textViewAddress.setText(employee.getAddress());
        }
        return convertView;
    }
}
