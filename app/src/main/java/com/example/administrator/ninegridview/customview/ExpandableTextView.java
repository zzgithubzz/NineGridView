package com.example.administrator.ninegridview.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableTextView extends LinearLayout {
    private TextView mTextView;
    private TextView mOpenBtn;
    private boolean isOpen = false;
    private int foldLines = 3; //大于3行的时候折叠
    private int lineCounts;

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public ExpandableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void initView() {

        lineCounts = mTextView.getLineCount();
        if (lineCounts < foldLines) {
            mOpenBtn.setVisibility(GONE);
        } else {
            if (isOpen && mTextView.getHeight() != lineCounts * mTextView.getLineHeight()) {
                mTextView.setHeight(mTextView.getLineHeight() * mTextView.getLineCount());
            } else if (!isOpen && mTextView.getHeight() != foldLines * mTextView.getLineHeight()) {
                mTextView.setHeight(mTextView.getLineHeight() * foldLines);
            }
        }

        mOpenBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    //to close
                    mTextView.setHeight(mTextView.getLineHeight() * foldLines);
                    mOpenBtn.setText("全文");
//                    mOpenBtn.setImageResource(R.drawable.icon_up_arrow);
                    isOpen = false;

                } else {
                    //to open
                    mTextView.setHeight(mTextView.getLineHeight() * mTextView.getLineCount());
//                    mOpenBtn.setImageResource(R.drawable.icon_down_arrow);
                    mOpenBtn.setText("收起");
                    isOpen = true;

                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("zyr", "onMeasure");
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("zyr", "onFinishInflate");
        if (mTextView == null || mOpenBtn == null) {
            mTextView = (TextView) getChildAt(0);
            mOpenBtn = (TextView) getChildAt(1);
        }

    }
}
