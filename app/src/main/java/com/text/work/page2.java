package com.text.work;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class page2 extends AppCompatActivity {
    private static final String TAG = "page2";
    private EditText input;
    String a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        input = (EditText) findViewById(R.id.input1);

        Intent intent=getIntent();
        a=intent.getStringExtra("biaoti");
        b=intent.getStringExtra("lianjie");

        onload();

        input.setOnClickListener(new View.OnClickListener() {//备忘录内容输入与保存清空 参考于https://www.jianshu.com/p/f4874b43036b
            @Override
            public void onClick(View view) {
                input.setCursorVisible(true);//使光标可见
            }
        });

    }
    public void click3(View btn) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("text", Context.MODE_PRIVATE);
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
    public void click4(View btn) {
        FileOutputStream fos = null;
        input.setText("");
        try {
            fos = openFileOutput("text", Context.MODE_PRIVATE);
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
    public void click5(View btn) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("text", Context.MODE_PRIVATE);
            if(a==null){
                Toast.makeText(page2.this, "无数据，添加null", Toast.LENGTH_SHORT).show();
                String text = input.getText().toString()+a+b;
                fos.write(text.getBytes());
            }else{
            String text = input.getText().toString()+a+"https://s.weibo.com/"+b;
            fos.write(text.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    Toast.makeText(page2.this, "添加成功！", Toast.LENGTH_SHORT).show();
                    Intent config4 = new Intent(page2.this, page2.class);//打开本页面实现刷新 显示添加内容
                    startActivityForResult(config4, 4);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void onload(){
        FileInputStream fis = null;
        try{
            fis = openFileInput("text");
            if(fis.available()==0){
                return;
            }else{
                byte[] con = new byte[fis.available()];
                while(fis.read(con)!=-1){

                }
                input.setText(new String(con));
                input.setCursorVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    long time;//双击退出
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





}