package com.example.storagemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.storagemanagement.R;
import com.example.storagemanagement.model.GoodsReceivedNote;

import java.util.List;

public class GoodsReceivedNoteAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsReceivedNote> goodsReceivedNotes;

    public GoodsReceivedNoteAdapter(Context context, List<GoodsReceivedNote> goodsReceivedNotes) {
        this.context = context;
        this.goodsReceivedNotes = goodsReceivedNotes;
    }

    @Override
    public int getCount() {
        return goodsReceivedNotes.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsReceivedNotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.goods_received_note_row_layout, null);
        }
        GoodsReceivedNote goodsReceivedNote = (GoodsReceivedNote) getItem(position);
        if (goodsReceivedNote != null) {
            TextView textViewGoodsReceivedNoteId = convertView.findViewById(R.id.textViewGoodsReceivedNoteId);
            TextView textViewDate = convertView.findViewById(R.id.textViewDate);
            TextView textViewNotice = convertView.findViewById(R.id.textViewNotice);
            textViewGoodsReceivedNoteId.setText(goodsReceivedNote.getGoodsReceivedNoteId());
            textViewDate.setText(goodsReceivedNote.getDate());
            textViewNotice.setText(goodsReceivedNote.getNotice());
        }
        return convertView;
    }
}
