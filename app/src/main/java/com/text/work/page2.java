package com.text.work;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class page2 extends AppCompatActivity {
    private static final String TAG = "page2";
    private EditText input;
    private Button save;
    private Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        input=(EditText)findViewById(R.id.input1) ;

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setFullScreen();
        hideBar();

        inputInfo = (EditText) findViewById(R.id.editText3);
        save = (Button) findViewById(R.id.button4);
        reset = (Button) findViewById(R.id.button5);
        count = (TextView)findViewById(R.id.textView4);


    }

    private void setFullScreen() {
    }

    public void save(View btn){
        Log.i(TAG, "save:  ");
        String newbeiwang=input.getText().toString();
        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("newbeiwang",newbeiwang);
        Log.i(TAG, "save: sp="+sp);
        editor.apply();

    }
    public void reset(View btn){
        }


}