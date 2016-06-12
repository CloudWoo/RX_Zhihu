package com.haomiao.cloud.rx_zhihu.view.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;

import com.haomiao.cloud.mvp_base.presenter.Presenter;
import com.haomiao.cloud.mvp_base.view.NucleusAppCompatActivity;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuDaily;
import com.haomiao.cloud.rx_zhihu.event.Events;
import com.haomiao.cloud.rx_zhihu.event.RxBus;
import com.haomiao.cloud.rx_zhihu.utils.SPUtils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Cloud on 16/5/18.
 */
public class BaseActivity<P extends Presenter> extends NucleusAppCompatActivity<P> {
    private boolean mAutoBindView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void setContentView(int layoutResID) {
        setTheme(SPUtils.getInstance().getTheme());
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mAutoBindView = true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAutoBindView) {
            ButterKnife.unbind(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        RxBus.getInstance().send(Events.BANNER_ISALIVE, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RxBus.getInstance().send(Events.BANNER_ISALIVE, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }




}

