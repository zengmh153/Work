package com.text.work;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
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
            list=new ArrayList<>();
            SQLiteDatabase db=helper.getReadableDatabase();
            Cursor cursor=db.query("account",null,null,null,null,
                    null,null);
            while (cursor.moveToNext()){
                costList clist=new costList();//构造实例
                clist.setID(cursor.getString(cursor.getColumnIndex("ID")));
                clist.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
                clist.setDate(cursor.getString(cursor.getColumnIndex("Date")));
                clist.setMoney(cursor.getString(cursor.getColumnIndex("Money")));
                list.add(clist);
            }
            adapter = new MyAdapter2(page3.this,R.layout.list_item2,list);
            listView.setAdapter(adapter);
            db.close();
        }

        private void initView() {
            helper=new DBHelper(page3.this);
            listView = findViewById(R.id.list_view);

        }

        //事件：添加
        public void click5(View view){//跳转
            Intent intent=new Intent(page3.this,page4.class);
            startActivityForResult(intent,1);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==1&&resultCode==1)
            {
                this.initData();
            }
        }
    public void click7(View view){
        list1=new ArrayList<>();
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor1=db.query("account",null,null,null,null,
                null,null);
        Log.i(TAG, "click7: "+cursor1);

    }
    }


