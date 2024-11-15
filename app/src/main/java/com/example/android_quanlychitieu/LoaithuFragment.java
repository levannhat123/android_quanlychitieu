package com.example.android_quanlychitieu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoaithuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoaithuFragment extends Fragment {
    private FloatingActionButton fab;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoaithuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoaithuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoaithuFragment newInstance(String param1, String param2) {
        LoaithuFragment fragment = new LoaithuFragment();
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
        View view= inflater.inflate(R.layout.fragment_loaithu, container, false);

        fab = view.findViewById(R.id.fab_page);
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

        //nhật
        // Tạo AlertDialog.Builder với context từ Fragment
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();

        // Inflate layout tùy chỉnh cho Dialog
        View dialogView = inflater.inflate(R.layout.custom_dialog_loaithu, null);
        builder.setView(dialogView);

        // Tìm các thành phần trong layout tùy chỉnh
        EditText etLoaiThu = dialogView.findViewById(R.id.etLoaiThu);






        // Thiết lập các nút cho Dialog
        builder.setPositiveButton("THÊM MỚI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy dữ liệu từ các trường và xử lý logic
                String loaiThu= etLoaiThu.getText().toString().trim();


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