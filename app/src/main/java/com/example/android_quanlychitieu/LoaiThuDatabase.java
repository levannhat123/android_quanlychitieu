package com.example.android_quanlychitieu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoaiThuDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LoaiThu.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "loaithu";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    public LoaiThuDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Thêm loại thu
    public boolean insertLoaiThu(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    // Lấy danh sách loại thu
    public Cursor getAllLoaiThu() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Lấy ID của loại thu mới chèn vào
    public int getLastInsertedId() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(id) FROM " + TABLE_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return -1; // Nếu không có dữ liệu
    }


    // Cập nhật loại thu
    public boolean updateLoaiThu(int id, String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newName);
        int result = db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // Xóa loại thu
    public boolean deleteLoaiThu(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
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

