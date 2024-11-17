package com.example.android_quanlychitieu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhoanthuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhoanthuFragment extends Fragment {
    private FloatingActionButton fab;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KhoanthuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KhoanthuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KhoanthuFragment newInstance(String param1, String param2) {
        KhoanthuFragment fragment = new KhoanthuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_khoanthu, container, false);

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
//        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
//        builder.setView(dialogView);
//
//        // Tìm các thành phần trong layout tùy chỉnh
//        EditText etTenKhoanThu = dialogView.findViewById(R.id.etTenKhoanThu);
//        EditText etNgayThu = dialogView.findViewById(R.id.etNgayThu);
//        EditText etTien = dialogView.findViewById(R.id.etTien);
//        EditText etHoTen = dialogView.findViewById(R.id.etHoTen);
//        EditText etGhiChu = dialogView.findViewById(R.id.etGhiChu);
        // Inside your Fragment or Activity
//        Spinner spLoaiThu = dialogView.findViewById(R.id.spLoaiThu);

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
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spLoaiThu.setAdapter(adapter);


        // Thiết lập các nút cho Dialog
//        builder.setPositiveButton("THÊM MỚI", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Lấy dữ liệu từ các trường và xử lý logic
//                String tenKhoanThu = etTenKhoanThu.getText().toString().trim();
//                String ngayThu = etNgayThu.getText().toString().trim();
//                String tien = etTien.getText().toString().trim();
//                String hoTen = etHoTen.getText().toString().trim();
//                String ghiChu = etGhiChu.getText().toString().trim();
//
//                // Xử lý logic thêm mới
//            }
//        });

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