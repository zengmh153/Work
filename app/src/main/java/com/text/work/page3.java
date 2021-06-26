package com.text.work;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class page3 extends AppCompatActivity {
    private static final String TAG = "page3";

    private DBHelper helper;
    private ListView listView;
    private List<costList> list;
    private List<costList> list1;
    MyAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        initView();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("account", null, null, null, null,
                null, null);
        while (cursor.moveToNext()) {
            costList clist = new costList();
            clist.setID(cursor.getString(cursor.getColumnIndex("ID")));
            clist.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
            clist.setDate(cursor.getString(cursor.getColumnIndex("Date")));
            clist.setMoneyin(cursor.getString(cursor.getColumnIndex("Moneyin")));
            clist.setMoneyout(cursor.getString(cursor.getColumnIndex("Moneyout")));
            list.add(clist);
        }
        adapter = new MyAdapter2(page3.this, R.layout.list_item2, list);
        listView.setAdapter(adapter);
        db.close();
    }

    private void initView() {
        helper = new DBHelper(page3.this);
        listView = findViewById(R.id.list_view);

    }

    //事件：添加
    public void click5(View view) {
        Intent intent = new Intent(page3.this, page4.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            this.initData();
        }
    }

    public void click7(View view) {
        list1 = new ArrayList<>();
        float a = 0, b = 0;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor1 = db.query("account", null, null, null, null,
                null, null);
        while (cursor1.moveToNext()) {
            for (int i = 0; i < cursor1.getCount(); i++) {
                cursor1.moveToPosition(i);
                float moneyout = Float.parseFloat(cursor1.getString(4));
                float moneyin = Float.parseFloat(cursor1.getString(3));
                a = a + moneyin;
                b = b + moneyout;
            }
            a = a - b;
            Log.i(TAG, "click7: a " + a);
        }
        SQLiteDatabase db1 = helper.getWritableDatabase();
        db.delete("account", null, null);
        SQLiteDatabase db2 = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date(System.currentTimeMillis());
        String dateStr=simpleDateFormat.format(date);
        values.put("Title", "总计");
        if(a>0){
            values.put("Moneyin", a);
            values.put("Moneyout" ,0);
        }else{
            values.put("Moneyin", 0);
            values.put("Moneyout" ,0-a);
        }
        values.put("Date", dateStr);
        long account = db1.insert("account", null, values);
        Intent intent = new Intent(page3.this, page3.class);
        startActivityForResult(intent, 1);
    }

    }


