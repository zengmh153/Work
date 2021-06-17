package com.text.work;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class page2 extends AppCompatActivity {
    private static final String TAG = "page2";
    private EditText input;
    private Button save;
    private Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setFullScreen();


        input = (EditText) findViewById(R.id.input1);
        save = (Button) findViewById(R.id.button4);
        reset = (Button) findViewById(R.id.button5);


        input.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged (CharSequence charSequence,int i, int i1, int i2){

            }

            @Override
            public void onTextChanged (CharSequence charSequence,int i, int i1, int i2){

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        onload();
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setCursorVisible(true);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("txt", Context.MODE_PRIVATE);
                    String text = input.getText().toString();
                    fos.write(text.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fos != null) {
                            fos.flush();
                            Toast.makeText(page2.this, "保存成功！", Toast.LENGTH_SHORT).show();
                            fos.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fos = null;
                input.setText("");
                try {
                    fos = openFileOutput("txt", Context.MODE_PRIVATE);
                    String text = "";
                    fos.write(text.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fos != null) {
                            fos.flush();
                            Toast.makeText(page2.this, "清空成功！", Toast.LENGTH_SHORT).show();
                            fos.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void onload(){
        FileInputStream fis = null;
        try{
            fis = openFileInput("txt");
            if(fis.available()==0){
                return;
            }else{
                byte[] con = new byte[fis.available()];
                while(fis.read(con)!=-1){

                }
                input.setText(new String(con));
                input.setSelection(input.getText().length());
                input.setCursorVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    long time;
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if(System.currentTimeMillis()-time>2000){
                Toast.makeText(page2.this,"再次点击返回键，程序退出",Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            }else{
                page2.this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
    public void reset(View btn){
        }


    private void setFullScreen(){
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


}