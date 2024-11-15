package com.example.android_quanlychitieu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String databaseName = "SignLog.db";

    public Database(@Nullable Context context ) {
        super(context, "SignLog.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(name TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users"); // Xóa bảng
        onCreate(db); // Tạo lại bảng
    }

    public Boolean insertData(String name, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkName(String name) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase(); // Dùng getReadableDatabase thay vì writable
        Cursor cursor = MyDatabase.rawQuery("Select * from users where name = ?", new String[]{name});
        boolean exists = cursor.getCount() > 0; // Kiểm tra số lượng bản ghi
        cursor.close(); // Đóng cursor
        return exists;
    }

    public Boolean checUser(String name, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where name = ? and password = ?", new String[]{name, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
