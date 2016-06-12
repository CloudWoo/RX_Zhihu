package com.haomiao.cloud.rx_zhihu.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.haomiao.cloud.rx_zhihu.R;

import butterknife.ButterKnife;

public class DailyItemActivity extends AppCompatActivity {

//    @Bind(R.id.webView)
//    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_item);
        ButterKnife.bind(this);
//        String id = getIntent().getStringExtra("id");
//        webView.loadUrl("http://daily.zhihu.com/story/" + id);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webView.setWebChromeClient(new WebChromeClient());
//        webView.setWebViewClient(new WebViewClient());

    }
}
