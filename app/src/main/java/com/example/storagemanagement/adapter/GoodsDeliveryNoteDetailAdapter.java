package com.example.storagemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.model.GoodsDeliveryNoteDetail;

import java.util.List;

public class GoodsDeliveryNoteDetailAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsDeliveryNoteDetail> goodsDeliveryNoteDetails;

    public GoodsDeliveryNoteDetailAdapter(Context context, List<GoodsDeliveryNoteDetail> goodsDeliveryNoteDetails) {
        this.context = context;
        this.goodsDeliveryNoteDetails = goodsDeliveryNoteDetails;
    }

    @Override
    public int getCount() {
        return goodsDeliveryNoteDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsDeliveryNoteDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.goods_delivery_note_detail_row_layout, null);
        }
        GoodsDeliveryNoteDetail goodsDeliveryNoteDetail = (GoodsDeliveryNoteDetail) getItem(position);
        if (goodsDeliveryNoteDetail != null) {
            TextView textViewGoodsDeliveryNoteId = convertView.findViewById(R.id.textViewGoodsDeliveryNoteId);
            TextView textViewProductId = convertView.findViewById(R.id.textViewProductId);
            TextView textViewQuantity = convertView.findViewById(R.id.textViewQuantity);
            TextView textViewPrice = convertView.findViewById(R.id.textViewPrice);
            textViewGoodsDeliveryNoteId.setText(goodsDeliveryNoteDetail.getGoodsDeliveryNoteId());
            textViewProductId.setText(goodsDeliveryNoteDetail.getProductId());
            textViewQuantity.setText(goodsDeliveryNoteDetail.getQuantity() + "");
            textViewPrice.setText(goodsDeliveryNoteDetail.getPrice() + "");
        }
        return convertView;
    }
}
