package com.example.storagemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.model.GoodsReceivedNoteDetail;

import java.util.List;

public class GoodsReceivedNoteDetailAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsReceivedNoteDetail> goodsReceivedNoteDetails;

    public GoodsReceivedNoteDetailAdapter(Context context, List<GoodsReceivedNoteDetail> goodsReceivedNoteDetails) {
        this.context = context;
        this.goodsReceivedNoteDetails = goodsReceivedNoteDetails;
    }

    @Override
    public int getCount() {
        return goodsReceivedNoteDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsReceivedNoteDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.goods_received_note_detail_row_layout, null);
        }
        GoodsReceivedNoteDetail goodsDeliveryNoteDetail = (GoodsReceivedNoteDetail) getItem(position);
        if (goodsDeliveryNoteDetail != null) {
            TextView textViewGoodsDeliveryNoteId = convertView.findViewById(R.id.textViewGoodsReceivedNoteId);
            TextView textViewProductId = convertView.findViewById(R.id.textViewProductId);
            TextView textViewQuantity = convertView.findViewById(R.id.textViewQuantity);
            TextView textViewPrice = convertView.findViewById(R.id.textViewPrice);
            textViewGoodsDeliveryNoteId.setText(goodsDeliveryNoteDetail.getGoodsReceivedNoteId());
            textViewProductId.setText(goodsDeliveryNoteDetail.getProductId());
            textViewQuantity.setText(goodsDeliveryNoteDetail.getQuantity() + "");
            textViewPrice.setText(goodsDeliveryNoteDetail.getPrice() + "");
        }
        return convertView;
    }
}
