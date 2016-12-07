package com.example.administrator.ninegridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.ninegridview.adapter.PtrAdapter;
import com.example.administrator.ninegridview.bean.InfoBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzy.ninegrid.ImageInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView ptrListView;

    private List<InfoBean> list = new ArrayList<>();

    private PtrAdapter ptrAdapter;

    private String url = "http://f.hiphotos.baidu.com/zhidao/pic/item/d62a6059252dd42a9592a7e2013b5bb5c8eab8e4.jpg";

    private String content = "我曾经跨过山和大海\n也穿过人山人海\n我曾经拥有过着的一切\n转眼都飘散如烟";
    private List<ImageInfo> listImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ptrListView = ((PullToRefreshListView) findViewById(R.id.ptr_listview));

        ptrListView.setMode(PullToRefreshBase.Mode.BOTH);

        View view = LayoutInflater.from(this).inflate(R.layout.list_empty, null, false);
        ptrListView.setEmptyView(view);

        for (int i = 0; i < 100; i++) {

            int number = (int) (1 + Math.random() * 9);

            listImg = new ArrayList<>();

            for (int num = 0; num < number; num++) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(url);
                info.setBigImageUrl(url);
                listImg.add(info);
            }

            list.add(new InfoBean(0, url, "萤火之森", "动漫", "宫崎骏", content, listImg, null, null, null));

            list.add(new InfoBean(1, url, "萤火之森", "动漫", "宫崎骏", content, null, url, "平凡之路", "http://www.51cto.com/"));
        }

        ptrAdapter = new PtrAdapter(list, this);
        ptrListView.setAdapter(ptrAdapter);

    }
}
