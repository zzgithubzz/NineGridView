package com.example.administrator.ninegridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = ((WebView) findViewById(R.id.webView));

        url = getIntent().getStringExtra("url");

        webView.loadUrl(url);
    }
}
