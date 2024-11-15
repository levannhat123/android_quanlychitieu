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
public class LoaichiFragment extends Fragment {

    private FloatingActionButton fab;

    public LoaichiFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_loaichi, container, false);

        fab = view.findViewById(R.id.fab_page);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị hộp thoại thêm mới khi nhấn vào FAB
                showAddDialog();

                ///addd
            }
        });

        return view;
    }
    private void showAddDialog() {
        // Tạo AlertDialog.Builder với context từ Fragment
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();

        // Inflate layout tùy chỉnh cho Dialog
        View dialogView = inflater.inflate(R.layout.custom_dialog_loaichi, null);
        builder.setView(dialogView);

        // Tìm các thành phần trong layout tùy chỉnh
        EditText etLoaiChi = dialogView.findViewById(R.id.etLoaiChi);






        // Thiết lập các nút cho Dialog
        builder.setPositiveButton("THÊM MỚI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy dữ liệu từ các trường và xử lý logic
                String loaiChi = etLoaiChi.getText().toString().trim();


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