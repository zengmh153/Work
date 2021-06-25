package com.text.work;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class page1 extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    private static final String TAG = "page1";
    ListView listView;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        listView = findViewById(R.id.mylist1);
        ProgressBar progressBar = findViewById(R.id.progressBar1);

        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==9){
                    ArrayList<HashMap<String,String>> retList=(ArrayList<HashMap<String,String>>)msg.obj;

                    adapter = new MyAdapter(page1.this,R.layout.list_item1,retList);
                    listView.setAdapter(adapter);
                    //切换显示
                    progressBar.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    Toast.makeText(page1.this,"已获取最新微博热搜",Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        Resou Resou=new Resou();
        Resou.setHandler(handler);
        Thread t =new Thread(Resou);
        t.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object itemAtPosition=listView.getItemAtPosition(position);
        HashMap<String,String> map = (HashMap<String,String>) itemAtPosition;
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("是否转入备忘录粘贴链接")
                .setNegativeButton("否",null)
                .setPositiveButton("是",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Object itemAtPosition=listView.getItemAtPosition(position);
                        HashMap<String,String> map = (HashMap<String,String>) itemAtPosition;
                        String titleStr = map.get("ItemTitle");
                        String detailStr = map.get("ItemDetail");
                        /* Intent config3 = new Intent(page1.this, page2.class);
                        config3.putExtra("biaoti",titleStr);
                        config3.putExtra("lianjie",detailStr);
                        startActivityForResult(config3, 3);*/
                        Log.i(TAG, "onClick: ");
                        Uri uri = Uri.parse("https://s.weibo.com/"+detailStr);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
        builder.create().show();
        return true;
    }
}