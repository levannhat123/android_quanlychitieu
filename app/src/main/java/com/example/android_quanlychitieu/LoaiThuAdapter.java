package com.example.android_quanlychitieu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LoaiThuAdapter extends ArrayAdapter<LoaiThu> {

    public LoaiThuAdapter(@NonNull Context context, @NonNull List<LoaiThu> loaithu) {
        super(context, 0, loaithu);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_loaithu, parent, false);
        }

        LoaiThu loaiThu = getItem(position);

        TextView tvLoaiThuId = convertView.findViewById(R.id.tvLoaiThuId);
        TextView tvLoaiThuName = convertView.findViewById(R.id.tvLoaiThuName);
        ImageButton btnEdit = convertView.findViewById(R.id.btn_edit);
        ImageButton btnDelete = convertView.findViewById(R.id.btn_delete);

        if (loaiThu != null) {
            tvLoaiThuId.setText(String.valueOf(loaiThu.getId()));
            tvLoaiThuName.setText(loaiThu.getName());

            btnEdit.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), EditLoaithu.class);
                intent.putExtra("user_id", loaiThu.getUserId());
                intent.putExtra("loaithu_id", loaiThu.getId());
                intent.putExtra("loaithu_name", loaiThu.getName());
                ((Activity) getContext()).startActivityForResult(intent, 1);
            });
            btnDelete.setOnClickListener(v -> {
                new AlertDialog.Builder(getContext())
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn có chắc chắn muốn xóa loại thu này?")
                        .setPositiveButton("Xóa", (dialog, which) -> {

                            Database db = new Database(getContext());
                            boolean isDeleted = db.deleteLoaithu(loaiThu.getId(), loaiThu.getUserId());
                            if (isDeleted) {
                                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                                remove(loaiThu);
                                notifyDataSetChanged(); // Cập nhật giao diện
                            } else {
                                Toast.makeText(getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }

                        })
                        .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
                        .show();

            });



        }
        return convertView;
    }


}
