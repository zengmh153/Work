package com.text.work;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class page4 extends AppCompatActivity {
    private DBHelper helper;
    private EditText et_cost_title;
    private EditText et_cost_money;
    private DatePicker dp_cost_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        initView();
    }
    private void initView() {
        helper = new DBHelper(page4.this);
        et_cost_title = findViewById(R.id.et_cost_title);
        et_cost_money = findViewById(R.id.et_cost_money);
    }


    public void click6(View view) {
        String titleStr = et_cost_title.getText().toString().trim();
        String moneyStr = et_cost_money.getText().toString().trim();
        if ("".equals(moneyStr)) {//可以不填写Title但是不能不填金额
            Toast toast = Toast.makeText(this, "请填写金额", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            Date date = new Date(System.currentTimeMillis());
            String dateStr=simpleDateFormat.format(date);
            values.put("Title", titleStr);
            values.put("Money", moneyStr);
            values.put("Date", dateStr);
            long account = db.insert("account", null, values);
            if (account > 0) {
                Toast toast = Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                setResult(1);
                finish();
            } else {
                Toast toast = Toast.makeText(this, "请重试", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                db.close();
            }
            setResult(1);
            finish();
        }

    }
}
