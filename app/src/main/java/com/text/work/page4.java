package com.text.work;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class page4 extends AppCompatActivity {
    private DBHelper helper;
    private EditText cost_title;
    private EditText cost_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        initView();
    }
    private void initView() {
        helper = new DBHelper(page4.this);
        cost_title = findViewById(R.id.cost_title);
        cost_money = findViewById(R.id.cost_money);
    }
    public void click6(View view) {
        String titleStr = cost_title.getText().toString().trim();
        String moneyStr = cost_money.getText().toString().trim();
        if ("".equals(moneyStr)) {//可以不填写Title但是不能不填金额
             Toast.makeText(this, "请填写金额", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            Date date = new Date(System.currentTimeMillis());
            String dateStr=simpleDateFormat.format(date);
            values.put("Title", titleStr);
            values.put("Money", moneyStr);
            values.put("Date", dateStr);
            long account = db.insert("account", null, values);
            if (account > 0) {
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                setResult(1);
                finish();
            } else {
                db.close();
            }
            setResult(1);
            finish();
        }

    }
}
