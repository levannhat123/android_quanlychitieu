package com.example.android_quanlychitieu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuanLiChiTieu.db";
    private static final int DATABASE_VERSION = 1;

    // Table: users
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Table: loaithu
    public static final String TABLE_LOAITHU = "loaithu";
    public static final String COLUMN_LOAITHU_ID = "loaithu_id";
    public static final String COLUMN_LOAITHU_NAME = "loaithu_name";
    public static final String COLUMN_USER_ID_FK = "user_id_fk";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table users
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT NOT NULL, "
                + COLUMN_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(CREATE_USERS_TABLE);

        // Create table loaithu
        String CREATE_LOAITHU_TABLE = "CREATE TABLE " + TABLE_LOAITHU + " ("
                + COLUMN_LOAITHU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_LOAITHU_NAME + " TEXT NOT NULL, "
                + COLUMN_USER_ID_FK + " INTEGER NOT NULL, "
                + "FOREIGN KEY(" + COLUMN_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "))";
        db.execSQL(CREATE_LOAITHU_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOAITHU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Register user and auto-create loaithu entries
    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);
        if (result != -1) {
            // Insert default loaithu entries for new user
            int userId = getLastInsertedId();
            return true;
        }
        return false;
    }

    // Insert default loaithu entries for a user


    // Insert loaithu
    public boolean insertLoaiThu(String name, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOAITHU_NAME, name);
        values.put(COLUMN_USER_ID_FK, userId);

        long result = db.insert(TABLE_LOAITHU, null, values);
        return result != -1;
    }

    // Update loaithu
    public boolean updateLoaiThu(int loaithuId, String name, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOAITHU_NAME, name);
        values.put(COLUMN_USER_ID_FK, userId);

        // Cập nhật dòng trong bảng loaithu
        int result = db.update(TABLE_LOAITHU, values, COLUMN_LOAITHU_ID + "=?",
                new String[]{String.valueOf(loaithuId)});

        Log.d("Database", "Update result: " + result); // Ghi log số dòng bị ảnh hưởng
        return result > 0; // Trả về true nếu ít nhất 1 dòng được cập nhật
    }



    // User login
    public int login(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USERS +
                        " WHERE " + COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{username, password});

        if (cursor != null && cursor.moveToFirst()) {
            int userId = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID));
            cursor.close();
            return userId;
        }
        if (cursor != null) cursor.close();
        return -1;
    }

    // Get last inserted ID
    private int getLastInsertedId() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        }
        if (cursor != null) cursor.close();
        return -1;
    }
    public Boolean checkName(String name) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where username = ?", new String[]{name});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public boolean deleteLoaithu(int loaithuId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_LOAITHU, COLUMN_LOAITHU_ID + "=?",
                new String[]{String.valueOf(loaithuId)});
        return result > 0;
    }
    public Cursor getAllLoaiThu(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_LOAITHU, null, COLUMN_USER_ID_FK + "=?",
                new String[]{String.valueOf(userId)}, null, null, null);
    }
}
