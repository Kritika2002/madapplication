package com.example.madapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(fullname Text primary key, password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop Table if exists users");

    }

    public boolean insertData(String fullname, String password)
    {
        SQLiteDatabase myDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("password",password);
        long result= myDB.insert("users", null, contentValues);

        if(result==-1)
        {
            return false;
        }
        else {
            return true;
        }

    }
    public boolean checkfullname(String fullname)
    {
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("select * from users where fullname=?", new String[] {fullname});
        if (cursor.getCount()>0)
        {
            return  true;

        }
        else
        {
            return false;
        }
    }

    public boolean checkdata(String fullname, String password)
    {
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("select * from users where fullname=? and password=?", new String[] {fullname, password});
        if (cursor.getCount()>0)
        {
            return  true;
        }
        else
        {
            return false;
        }
    }


}
