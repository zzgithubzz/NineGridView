package com.example.administrator.ninegridview.myApp;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.administrator.ninegridview.R;
import com.lzy.ninegrid.NineGridView;
import com.squareup.picasso.Picasso;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NineGridView.setImageLoader(new PicassoImageLoader());
    }

    /**
     * Picasso 加载
     */
    private class PicassoImageLoader implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Picasso.with(context).load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.drawable.ic_default_image)
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }
}
