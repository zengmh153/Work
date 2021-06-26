package com.text.work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class MyAdapter2 extends ArrayAdapter {
    List<costList> mList;
    public MyAdapter2(@NonNull Context context, int resource, @NonNull List<costList> list) {
        super(context, resource, list);
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private LayoutInflater mLayoutInflater;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.list_item2, null);
        costList item = mList.get(position);
        TextView tv_title = view.findViewById(R.id.title1);
        TextView tv_date = view.findViewById(R.id.date1);
        TextView tv_moneyin = view.findViewById(R.id.moneyin);
        TextView tv_moneyout = view.findViewById(R.id.moneyout);
        tv_title.setText(mList.get(position).getTitle());
        tv_date.setText(mList.get(position).getDate());
        tv_moneyin.setText(mList.get(position).getMoneyin());
        tv_moneyout.setText(mList.get(position).getMoneyout());

        return view;

    }


}








