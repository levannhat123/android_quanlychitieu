package com.example.android_quanlychitieu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class KhoanthuFragment extends Fragment {
    private FloatingActionButton fab;
    private Database db;
    private List<KhoanThu> khoanthuList;
    private KhoanThuAdapter khoanthuAdapter;
    //
    private int userId;

    public KhoanthuFragment() {
        // Required empty public constructor
    }

    public static KhoanthuFragment newInstance(int userId) {
        KhoanthuFragment fragment = new KhoanthuFragment();
        Bundle args = new Bundle();
        args.putInt("user_id", userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt("user_id", -1);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (khoanthuAdapter != null) {
            loadLoaithu(userId);
        }
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoanthu, container, false);

        // Khởi tạo Database
        db = new Database(requireContext());
        if (getArguments() != null) {
            userId = getArguments().getInt("user_id", -1);
        }
        Log.d("TAG", "user_id in khoanthuFragment: " + userId);
        // Kiểm tra user_id
        if (userId == -1) {
            Toast.makeText(getContext(), "Lỗi: Không có user_id", Toast.LENGTH_SHORT).show();
            return view;
        }
        ListView listView = view.findViewById(R.id.listViewKhoanThu);
        khoanthuList = new ArrayList<>();
        khoanthuAdapter = new KhoanThuAdapter(requireContext(), khoanthuList, db, userId);

        listView.setAdapter(khoanthuAdapter);

        loadLoaithu(userId);

        fab = view.findViewById(R.id.fab_khoanthu);
        fab.setOnClickListener(v -> {
            if (userId == -1) {
                Toast.makeText(getContext(), "Lỗi: Không xác định được người dùng", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(getContext(), AddKhoanThu.class);
            intent.putExtra("user_id", userId);
            ((Activity) getContext()).startActivityForResult(intent, 1);
        });

        return view;
    }



    private void loadLoaithu(int userId) {
        Log.d("loaiThu", "Loading khoan thu for userId: " + userId);
        SQLiteDatabase dbsqlt = this.db.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + Database.TABLE_KHOANTHU +
                    " WHERE " + Database.COLUMN_USER_ID_FK + " = ?";
            cursor = dbsqlt.rawQuery(query, new String[]{String.valueOf(userId)});

            khoanthuList.clear();

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(Database.COLUMN_KHOANTHU_ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_KHOANTHU_NAME));
                    String ngaythu = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_NGAYTHU));
                    int tien = cursor.getInt(cursor.getColumnIndexOrThrow(Database.COLUMN_TIEN));
                    String ghichu = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_GHICHU));
                    int loathu = cursor.getInt(cursor.getColumnIndexOrThrow(Database.COLUMN_LOAITHU_ID_FK));
                    int userIdFromDb = cursor.getInt(cursor.getColumnIndexOrThrow(Database.COLUMN_USER_ID_FK));
                    khoanthuList.add(new KhoanThu(id,name,ngaythu,tien,ghichu,loathu,userIdFromDb));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("KhoanthuFragment", "Error loading Loaithu", e);
        } finally {
            if (cursor != null) cursor.close();
        }

        if (khoanthuAdapter != null) {
            khoanthuAdapter.notifyDataSetChanged();
        }
    }
}
