package com.example.storagemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.model.GoodsDeliveryNote;

import java.util.List;

public class GoodsDeliveryNoteAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsDeliveryNote> goodsDeliveryNotes;

    public GoodsDeliveryNoteAdapter(Context context, List<GoodsDeliveryNote> goodsDeliveryNotes) {
        this.context = context;
        this.goodsDeliveryNotes = goodsDeliveryNotes;
    }

    @Override
    public int getCount() {
        return goodsDeliveryNotes.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsDeliveryNotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.goods_delivery_note_row_layout, null);
        }
        GoodsDeliveryNote goodsDeliveryNote = (GoodsDeliveryNote) getItem(position);
        if (goodsDeliveryNote != null) {
            TextView textViewGoodsDeliveryNoteId = convertView.findViewById(R.id.textViewGoodsDeliveryNoteId);
            TextView textViewDate = convertView.findViewById(R.id.textViewDate);
            TextView textViewNotice = convertView.findViewById(R.id.textViewNotice);
            textViewGoodsDeliveryNoteId.setText(goodsDeliveryNote.getGoodsDeliveryNoteId());
            textViewDate.setText(goodsDeliveryNote.getDate());
            textViewNotice.setText(goodsDeliveryNote.getNotice());
        }
        return convertView;
    }
}
