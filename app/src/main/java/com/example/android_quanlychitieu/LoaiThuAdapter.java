package com.example.android_quanlychitieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.List;

public class LoaiThuAdapter extends BaseAdapter {
    private Context context;
    private List<LoaiThu> loaiThuList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public LoaiThuAdapter(Context context, List<LoaiThu> loaiThuList, OnItemClickListener listener) {
        this.context = context;
        this.loaiThuList = loaiThuList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return loaiThuList.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiThuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_loaithu, parent, false);
        }

        LoaiThu loaiThu = loaiThuList.get(position);

        TextView tvLoaiThuId = convertView.findViewById(R.id.tvLoaiThuId);
        TextView tvLoaiThuName = convertView.findViewById(R.id.tvLoaiThuName);
        ImageButton btnEdit = convertView.findViewById(R.id.btn_edit);
        ImageButton btnDelete = convertView.findViewById(R.id.btn_delete);

        tvLoaiThuId.setText(String.valueOf(loaiThu.getId()));
        tvLoaiThuName.setText(loaiThu.getName());

        btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(position);
            }
        });

        btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(position);
            }
        });

        return convertView;
    }
}
