package com.haomiao.cloud.rx_zhihu.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.haomiao.cloud.rx_zhihu.model.webBody;
import com.haomiao.cloud.rx_zhihu.R;
import com.haomiao.cloud.rx_zhihu.service.ServiceFactory;
import com.haomiao.cloud.rx_zhihu.service.ServiceManger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Main4Activity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        webView = (WebView) findViewById(R.id.webView2);
        webView.getSettings().setDefaultTextEncodingName("utf-8") ;
        final ServiceManger service = ServiceFactory.createServiceFrom(ServiceManger.class, ServiceManger.ENDPOINT);
        service.getXml()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<webBody>() {
            @Override
            public void call(webBody webBody) {
                Log.d("Main4Activity", webBody.getBody());
//                webView.loadData(fmtString(webBody.getBody()), "text/html", "utf-8");
//                webView.loadDataWithBaseURL(null, webBody.getBody(), "text/html",  "utf-8", null);
            }
        });

        webView.loadUrl("http://daily.zhihu.com/story/3892357");


        WebSettings webSettings = webView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
    }


    private String fmtString(String str){
        String notice = "";
        try{
            notice = URLEncoder.encode(str, "utf-8");
        }catch(UnsupportedEncodingException ex){
        }
        return notice;
    }
}
