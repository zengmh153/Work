package com.text.work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DB_NAME = "mydb.db";

    public DBHelper(Context context) {
        super(context, DB_NAME ,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table account(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Title varchar(20)," +
                "Date varchar(20)," +
                "Moneyin vaechar(20),"+
                "Moneyout vaechar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int arg1, int arg2) {
    }
}

