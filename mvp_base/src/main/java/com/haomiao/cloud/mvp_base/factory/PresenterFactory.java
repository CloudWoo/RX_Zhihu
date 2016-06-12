package com.haomiao.cloud.mvp_base.factory;


import com.haomiao.cloud.mvp_base.presenter.Presenter;

public interface PresenterFactory<P extends Presenter> {
    P createPresenter();
}
