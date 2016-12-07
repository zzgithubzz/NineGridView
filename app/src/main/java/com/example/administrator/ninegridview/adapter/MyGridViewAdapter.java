package com.example.administrator.ninegridview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.ninegridview.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */

public class MyGridViewAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private LayoutInflater inflater;

    public MyGridViewAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.popup_gv_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) view.findViewById(R.id.tv_pop_gv);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv.setText(list.get(i));

        return view;
    }

    class ViewHolder {
        TextView tv;
    }
}
