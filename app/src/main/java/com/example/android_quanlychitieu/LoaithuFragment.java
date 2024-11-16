package com.example.android_quanlychitieu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

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
    private List<LoaiThu> loaiThuList = new ArrayList<>();
    private LoaiThuAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loaithu, container, false);

        ListView listView = view.findViewById(R.id.listViewLoaiThu);

        fab = view.findViewById(R.id.fab_page);
        fab.setOnClickListener(v -> showAddDialog());

        // Lấy dữ liệu từ database và hiển thị
        LoaiThuDatabase db = new LoaiThuDatabase(getActivity());
        Cursor cursor = db.getAllLoaiThu();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                loaiThuList.add(new LoaiThu(id, name));
            } while (cursor.moveToNext());
            cursor.close();
        }

        adapter = new LoaiThuAdapter(getActivity(), loaiThuList, new LoaiThuAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                LoaiThu loaiThu = loaiThuList.get(position);
                showEditDialog(loaiThu);
            }

            @Override
            public void onDeleteClick(int position) {
                LoaiThu loaiThu = loaiThuList.get(position);
                deleteLoaiThu(loaiThu);
            }
        });
        listView.setAdapter(adapter);

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
                String loaiThu = etLoaiThu.getText().toString().trim();

                if (loaiThu.isEmpty()) {
                    Toast.makeText(getActivity(), "Tên loại thu không được để trống!", Toast.LENGTH_SHORT).show();
                    return;
                }

                LoaiThuDatabase db = new LoaiThuDatabase(getActivity());
                boolean isInserted = db.insertLoaiThu(loaiThu);

                if (isInserted) {
                    Toast.makeText(getActivity(), "Thêm mới thành công!", Toast.LENGTH_SHORT).show();

                    // Lấy ID vừa được thêm
                    int lastInsertedId = db.getLastInsertedId();

                    // Thêm vào danh sách
                    loaiThuList.add(new LoaiThu(lastInsertedId, loaiThu));

                    // Cập nhật Adapter
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "Thêm mới thất bại!", Toast.LENGTH_SHORT).show();
                }
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
    private void showEditDialog(LoaiThu loaiThu) {
        // Tạo AlertDialog để chỉnh sửa loại thu
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog_loaithu, null);
        builder.setView(dialogView);

        EditText etLoaiThu = dialogView.findViewById(R.id.etLoaiThu);
        etLoaiThu.setText(loaiThu.getName());

        builder.setPositiveButton("CẬP NHẬT", (dialog, which) -> {
            String newName = etLoaiThu.getText().toString().trim();
            if (newName.isEmpty()) {
                Toast.makeText(getActivity(), "Tên loại thu không được để trống!", Toast.LENGTH_SHORT).show();
                return;
            }

            LoaiThuDatabase db = new LoaiThuDatabase(getActivity());
            boolean isUpdated = db.updateLoaiThu(loaiThu.getId(), newName);

            if (isUpdated) {
                Toast.makeText(getActivity(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                loaiThu.setName(newName);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(), "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.cancel());
        builder.create().show();
    }
    private void deleteLoaiThu(LoaiThu loaiThu) {
        LoaiThuDatabase db = new LoaiThuDatabase(getActivity());
        boolean isDeleted = db.deleteLoaiThu(loaiThu.getId());

        if (isDeleted) {
            Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            loaiThuList.remove(loaiThu);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getActivity(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }

//    public boolean deleteLoaiThu(int id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        int result = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
//        db.close();
//
//        // Nếu xóa thành công, cập nhật lại ID cho các mục còn lại
//        if (result > 0) {
//            updateIdsAfterDelete();
//        }
//
//        return result > 0;
//    }
//
//    // Phương thức cập nhật lại ID
//    public void updateIdsAfterDelete() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//        int index = 1; // Bắt đầu lại từ ID 1
//
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                int currentId = cursor.getInt(cursor.getColumnIndex("id"));
//                String name = cursor.getString(cursor.getColumnIndex("name"));
//
//                // Nếu ID hiện tại không khớp với index, cập nhật lại ID
//                if (currentId != index) {
//                    ContentValues values = new ContentValues();
//                    values.put("id", index);
//                    db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(currentId)});
//                }
//                index++; // Tăng ID tiếp theo
//            } while (cursor.moveToNext());
//            cursor.close();
//        }
//        db.close();
//    }
}