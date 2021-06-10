package com.text.work;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);

    }

    public void click1(View btn) {
        Intent config = new Intent(this, page1.class);
        startActivityForResult(config, 1);
    }

    public void click2(View btn) {
        Intent config1 = new Intent(this, page2.class);
        startActivityForResult(config1, 2);
    }
}