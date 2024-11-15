package com.example.android_quanlychitieu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class KhoanchiFragment extends Fragment {
    private FloatingActionButton fab;

    public KhoanchiFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view= inflater.inflate(R.layout.fragment_khoanchi, container, false);

        fab = view.findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị hộp thoại thêm mới khi nhấn vào FAB
                showAddDialog();
            }
        });

        return view;
    }
    private void showAddDialog() {
        // Tạo AlertDialog.Builder với context từ Fragment
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();

        // Inflate layout tùy chỉnh cho Dialog
        View dialogView = inflater.inflate(R.layout.custom_dialog_khoanchi, null);
        builder.setView(dialogView);

        // Tìm các thành phần trong layout tùy chỉnh
        EditText etTenKhoanThu = dialogView.findViewById(R.id.etTenKhoanChi);
        EditText etNgayThu = dialogView.findViewById(R.id.etNgayChi);
        EditText etTien = dialogView.findViewById(R.id.etTien_chi);
        EditText etHoTen = dialogView.findViewById(R.id.etHoTenChi);
        EditText etGhiChu = dialogView.findViewById(R.id.etGhiChu_chi);
        // Inside your Fragment or Activity
        Spinner spLoaiThu = dialogView.findViewById(R.id.spLoaiThu);

// Dữ liệu cho Spinner
        List<String> loaiThuList = new ArrayList<>();
        loaiThuList.add("Loại 1");
        loaiThuList.add("Loại 2");
        loaiThuList.add("Loại 3");


        // Inside your fragment
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), // context (can also use getActivity() or requireContext())
                android.R.layout.simple_spinner_item, // layout for the spinner items
                loaiThuList // data list
        );


// Thiết lập layout dropdown cho Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLoaiThu.setAdapter(adapter);


        // Thiết lập các nút cho Dialog
        builder.setPositiveButton("THÊM MỚI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy dữ liệu từ các trường và xử lý logic
                String tenKhoanThu = etTenKhoanThu.getText().toString().trim();
                String ngayThu = etNgayThu.getText().toString().trim();
                String tien = etTien.getText().toString().trim();
                String hoTen = etHoTen.getText().toString().trim();
                String ghiChu = etGhiChu.getText().toString().trim();

                // Xử lý logic thêm mới
            }
        });


        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Hiển thị AlertDialog
        builder.create().show();
    }
}
