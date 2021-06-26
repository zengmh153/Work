package com.text.work;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        Date date = new Date(System.currentTimeMillis());
        String dateStr=simpleDateFormat.format(date);
        textView1.setText("现在是"+dateStr);
    }

    public void click1(View btn) {
        Intent config = new Intent(this, page1.class);
        startActivityForResult(config, 0);
    }

    public void click2(View btn) {
        Intent config1 = new Intent(this, page2.class);
        startActivityForResult(config1, 1);
    }

    public void click3(View btn) {
        Intent config2 = new Intent(this, page3.class);
        startActivityForResult(config2, 2);
    }

}