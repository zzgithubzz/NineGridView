package com.example.administrator.ninegridview.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.ninegridview.R;
import com.squareup.picasso.Picasso;

public class SpecialArticle extends RelativeLayout {

    private ImageView mImageView;
    private TextView mTextView;

    public SpecialArticle(Context context) {
        super(context);
    }

    public SpecialArticle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    private void initViews(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.specialarticlelayout, this, true);
        mImageView = (ImageView) view.findViewById(R.id.image_specialArticle);
        mTextView = (TextView) view.findViewById(R.id.text_specialArticle);
    }

    public void setText(String text) {
        if (mTextView != null) {
            mTextView.setText(text);
        }
    }

    public void setImage(Context context, String url) {
        if (mImageView != null && url != null)
            Picasso.with(context).load(url).into(mImageView);
//            mImageView.setImageBitmap(bitmap);
    }
}
