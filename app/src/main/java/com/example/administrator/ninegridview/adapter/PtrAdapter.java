package com.example.administrator.ninegridview.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ninegridview.R;
import com.example.administrator.ninegridview.WebActivity;
import com.example.administrator.ninegridview.bean.InfoBean;
import com.example.administrator.ninegridview.customview.MyGridView;
import com.example.administrator.ninegridview.customview.SpecialArticle;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PtrAdapter extends BaseAdapter {

    ViewHolder viewHolder = null;
    private List<InfoBean> list;
    private Context context;
    private LayoutInflater inflater;
    private SharedPreferences sharedPreferences;
    //    private boolean isPraise1 = false,isPraise2 = false;
    private boolean[] isPraise;
    private PopupWindow popupWindow;
    private MyGridView gvPop;

    public PtrAdapter(List<InfoBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE);

        isPraise = new boolean[list.size()];
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType() == 0) {
            return 0;  //表示类型一(上传的为图片)
        } else if (list.get(position).getType() == 1) {
            return 1;//表示类型二(上传的为视频)
        }
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);

        if (convertView == null) {
            switch (type) {
                case 0:
                    convertView = inflater.inflate(R.layout.ptr_item, parent, false);
                    viewHolder = new ViewHolder();
                    viewHolder.imgHeader = (ImageView) convertView.findViewById(R.id.img_header);
                    viewHolder.userName = (TextView) convertView.findViewById(R.id.name);
                    viewHolder.tvX = (TextView) convertView.findViewById(R.id.tv_X_item);
                    viewHolder.contact = (TextView) convertView.findViewById(R.id.largeTitle);
                    viewHolder.work = (TextView) convertView.findViewById(R.id.smallTitle);
                    viewHolder.content = (TextView) convertView.findViewById(R.id.text_content);
                    viewHolder.praise = (ImageView) convertView.findViewById(R.id.praise);
                    viewHolder.praise.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isPraise[position] = sharedPreferences.getBoolean("isPraise1" + position, false);

                            praise(isPraise[position], "isPraise1" + position);

                        }
                    });
                    viewHolder.tvX.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showPopupWindow(view);
                        }
                    });
                    viewHolder.nineGridView = (NineGridView) convertView.findViewById(R.id.nineGridView);

                    convertView.setTag(viewHolder);
                    break;
                case 1:
                    convertView = inflater.inflate(R.layout.ptr_item2, parent, false);
                    viewHolder = new ViewHolder();
                    viewHolder.imgHeader = (ImageView) convertView.findViewById(R.id.img_header2);
                    viewHolder.userName = (TextView) convertView.findViewById(R.id.name2);
                    viewHolder.tvX = (TextView) convertView.findViewById(R.id.tv_X_item2);
                    viewHolder.contact = (TextView) convertView.findViewById(R.id.largeTitle2);
                    viewHolder.work = (TextView) convertView.findViewById(R.id.smallTitle2);
                    viewHolder.content = (TextView) convertView.findViewById(R.id.text_content2);
                    viewHolder.specialArticle = (SpecialArticle) convertView.findViewById(R.id.specialArticle);
                    viewHolder.praise = (ImageView) convertView.findViewById(R.id.praise);
                    viewHolder.praise.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isPraise[position] = sharedPreferences.getBoolean("isPraise2" + position, false);

                            praise(isPraise[position], "isPraise2" + position);

                        }
                    });
                    viewHolder.tvX.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showPopupWindow(view);
                        }
                    });
                    viewHolder.specialArticle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, WebActivity.class);
                            intent.putExtra("url", list.get(position).getWebUrl());
                            context.startActivity(intent);

                        }
                    });
                    convertView.setTag(viewHolder);
                    break;
                default:
                    break;
            }

        } else {
            switch (getItemViewType(position)) {
                case 0:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;
                case 1:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;
                default:
                    break;
            }
        }


        viewHolder.userName.setText(list.get(position).getUserName());
        viewHolder.contact.setText(list.get(position).getContact());
        viewHolder.work.setText(list.get(position).getWork());
        viewHolder.content.setText(list.get(position).getContent());


        Picasso.with(context).load(list.get(position).getUserImage()).placeholder(R.mipmap.ic_launcher).into(viewHolder.imgHeader);


        switch (getItemViewType(position)) {
            case 0:
                Log.e("AAA", "getView: " + list.get(position).getList().size());
                viewHolder.nineGridView.setAdapter(new NineGridViewClickAdapter(context, list.get(position).getList()));
                break;
            case 1:
                viewHolder.specialArticle.setText(list.get(position).getWebContent());
                viewHolder.specialArticle.setImage(context, list.get(position).getWebImage());
                break;
            default:
                break;
        }
        return convertView;
    }

    //点赞的方法
    private void praise(boolean isPraise, String str) {
        if (isPraise) {
            Toast.makeText(context, "取消点赞", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(str, false);
            editor.commit();
        } else {
            Toast.makeText(context, "点赞", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(str, true);
            editor.commit();
        }
    }

    private void showPopupWindow(View view) {
        if (popupWindow == null) {
            View v = LayoutInflater.from(context).inflate(R.layout.pop_layout, null, false);
            gvPop = ((MyGridView) v.findViewById(R.id.gv_pop));

            List<String> list = new ArrayList<>();
            list.add("广告/低俗");
            list.add("鸡汤/段子");
            list.add("作者:XX");
            gvPop.setAdapter(new MyGridViewAdapter(list, context));
            popupWindow = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
        }

        popupWindow.showAsDropDown(view);

    }

    class ViewHolder {
        TextView userName, contact, work, content, tvX;
        ImageView imgHeader, praise;
        NineGridView nineGridView;
        SpecialArticle specialArticle;
    }

}
