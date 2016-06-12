package com.haomiao.cloud.rx_zhihu.presenter;


import android.os.Bundle;
import android.support.annotation.NonNull;

import com.haomiao.cloud.mvp_base.presenter.RxPresenter;
import com.haomiao.cloud.mvp_base.view.ViewWithPresenter;

import icepick.Icepick;


public class BasePresenter <V extends ViewWithPresenter> extends RxPresenter<V> {
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Icepick.restoreInstanceState(this, savedState);

    }


    @Override
    protected void onSave(@NonNull Bundle state) {
        super.onSave(state);
        Icepick.saveInstanceState(this, state);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



}
