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

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LoaithuFragment extends Fragment {
    private FloatingActionButton fab;
    private Database db;
    private List<LoaiThu> loaithuList;
    private LoaiThuAdapter loaithuAdapter;
    private int userId;

    public LoaithuFragment() {
    }

    public static LoaithuFragment newInstance(int userId) {
        LoaithuFragment fragment = new LoaithuFragment();
        Bundle args = new Bundle();
        args.putInt("user_id", userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt("user_id", -1);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (loaithuAdapter != null) {
            loadLoaithu(userId);
            loaithuAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loaithu, container, false);

        db = new Database(requireContext());

        if (getArguments() != null) {
            userId = getArguments().getInt("user_id", -1);
        }

        Log.d("TAG", "user_id in LoaithuFragment: " + userId);

        if (userId == -1) {
            Toast.makeText(getContext(), "Lỗi: Không có user_id", Toast.LENGTH_SHORT).show();
            return view;
        }

        ListView listView = view.findViewById(R.id.listViewLoaiThu);


        ///duc dduc

        loaithuList = new ArrayList<>();
        loaithuAdapter = new LoaiThuAdapter(requireContext(), loaithuList);
        listView.setAdapter(loaithuAdapter);

        loadLoaithu(userId);

        fab = view.findViewById(R.id.fab_page);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddLoaiThu.class);
            intent.putExtra("user_id", userId);
            ((Activity) getContext()).startActivityForResult(intent, 1);
        });



        return view;
    }



    public void loadLoaithu(int userId) {
        Log.d("loaiThu", "Loading loai thu for userId: " + userId);
        SQLiteDatabase dbsqlt = this.db.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + Database.TABLE_LOAITHU +
                    " WHERE " + Database.COLUMN_USER_ID_FK + " = ?";
            cursor = dbsqlt.rawQuery(query, new String[]{String.valueOf(userId)});

            loaithuList.clear();

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(Database.COLUMN_LOAITHU_ID));
                    String name = cursor.getString(cursor.getColumnIndex(Database.COLUMN_LOAITHU_NAME));
                    int userIdFromDb = cursor.getInt(cursor.getColumnIndex(Database.COLUMN_USER_ID_FK));
                    loaithuList.add(new LoaiThu(id, name, userIdFromDb));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("LoaithuFragment", "Error loading Loaithu", e);
        } finally {
            if (cursor != null) cursor.close();
        }

        if (loaithuAdapter != null) {
            loaithuAdapter.notifyDataSetChanged();
        }
    }



}
