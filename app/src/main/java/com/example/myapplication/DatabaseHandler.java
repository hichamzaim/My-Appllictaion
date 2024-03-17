package com.example.myapplication;

//here we start

import static java.security.AccessController.getContext;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user_account.db";

    private static final String TABLE_USER = "user";
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_USER + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fullName TEXT," +
                "username TEXT," +
                "email TEXT," +
                "phone TEXT," +
                "password TEXT," +
                "image BLOB," +
                "occupation TEXT," +
                "nationality TEXT," +
                "address TEXT," +
                "zipcode TEXT," +
                "country TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;
        cursor = db.query(TABLE_USER,
                new String[] {"id", "fullName","username", "email", "phone", "password", "image", "occupation", "nationality", "address", "zipcode", "country"},
                "username=?", new String[]{ username },
                null, null,null,null );
        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            byte[] imageByte = cursor.getBlob(6);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
            user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), bitmap, cursor.getString(7), cursor.getString(8)
                    , cursor.getString(9), cursor.getString(10), cursor.getString(11));
        }

        if (cursor != null) {
            cursor.close();
        }

        return user;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_USER;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                byte[] imageByte = cursor.getBlob(6);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), bitmap, cursor.getString(7), cursor.getString(8)
                        , cursor.getString(9), cursor.getString(10), cursor.getString(11));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("fullName", user.getFullName());
        values.put("username", user.getUsername());
        values.put("email", user.getEmail());
        values.put("phone", user.getPhone());
        values.put("password", user.getPassword());

        byteArrayOutputStream = new ByteArrayOutputStream();
        user.getImage().compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();
        values.put("image", imageInBytes);

        values.put("occupation", "");
        values.put("nationality", "");
        values.put("address", "");
        values.put("zipcode", "");
        values.put("country", "");


        db.insert(TABLE_USER, null, values);
        db.close();
    }
    public void updatePic(String username, Bitmap profileImg){
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        byteArrayOutputStream = new ByteArrayOutputStream();
        profileImg.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();
        values.put("image", imageInBytes);
        long re = sq.update(TABLE_USER, values, "username=?", new String[]{username});

    }

    public void updateInfo(String username, User user){
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("fullName", user.getFullName());
        values.put("username", user.getUsername());
        values.put("email", user.getEmail());
        values.put("phone", user.getPhone());
        values.put("occupation", user.getOccupation());
        values.put("nationality", user.getNationality());
        values.put("address", user.getAddress());
        values.put("zipcode", user.getZipcode());
        values.put("country", user.getCountry());

        long re = sq.update(TABLE_USER, values, "username=?", new String[]{username});

    }

}
