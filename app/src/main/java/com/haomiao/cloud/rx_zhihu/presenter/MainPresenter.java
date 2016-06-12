package com.haomiao.cloud.rx_zhihu.presenter;

import android.os.Bundle;

import com.haomiao.cloud.rx_zhihu.model.ZhiHuBanner;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuDailyItem;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuLatest;
import com.haomiao.cloud.rx_zhihu.service.ServiceAPI;
import com.haomiao.cloud.rx_zhihu.service.ServiceFactory;
import com.haomiao.cloud.rx_zhihu.service.ServiceManger;
import com.haomiao.cloud.rx_zhihu.utils.DateUtil;
import com.haomiao.cloud.rx_zhihu.utils.data.DataCache;
import com.haomiao.cloud.rx_zhihu.view.activity.Main2Activity;

import java.util.ArrayList;
import java.util.List;

import icepick.State;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.Presenter.Imp.
 * Created by Cloud on 16/5/18.
 * Instruction
 */
public class MainPresenter extends BasePresenter<Main2Activity> {


    public final static int STATE_DEFAULT = 0;
    public final static int STATE_REFRESH = 1;
    public final static int STATE_LOADMORE = 2;

    private int mState = STATE_DEFAULT;

    public void request(int pageNum, int mState) {
        this.pageNum = pageNum;
        this.mState = mState;
        start(REQUEST_ITEMS);
    }

    public void requestBanner() {
        start(REQUEST_BANNER);
    }


    @Override
    protected void onTakeView(Main2Activity view) {
        super.onTakeView(view);
        mState = STATE_DEFAULT;
        new DataCache().getFirstCacheFile()
                .flatMap(new Func1<Integer, Observable<ArrayList<ZhiHuDailyItem>>>() {
                    @Override
                    public Observable<ArrayList<ZhiHuDailyItem>> call(Integer cacheNum) {
                        firstCache = cacheNum;
                        return new DataCache().getCacheFile(DateUtil.getInstance().getDate(cacheNum), mState);
                    }
                })
                .filter(new Func1<ArrayList<ZhiHuDailyItem>, Boolean>() {
                    @Override
                    public Boolean call(ArrayList<ZhiHuDailyItem> zhiHuDailyItems) {
                        return zhiHuDailyItems != null;
                    }
                })
                .compose(MainPresenter.this.<ArrayList<ZhiHuDailyItem>>deliverLatestCache())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(MainPresenter.this.split(new Action2<Main2Activity, ArrayList<ZhiHuDailyItem>>() {
                    @Override
                    public void call(Main2Activity view, ArrayList<ZhiHuDailyItem> data) {
                        view.setData(data);
                    }
                }, new Action2<Main2Activity, Throwable>() {
                    @Override
                    public void call(Main2Activity view, Throwable throwable) {
                        view.onError(mState);
                    }
                }));
    }

    private static final int REQUEST_ITEMS = 1;
    private static final int REQUEST_BANNER = 2;

    //  Fllowing will be automatically saved and restored
    @State
    int pageNum = 0;
    @State
    int firstCache = 0;

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        restartable(REQUEST_ITEMS, new Func0<Subscription>() {
            @Override
            public Subscription call() {
                return Observable.concat(new DataCache().<ArrayList<ZhiHuDailyItem>>getCacheFile(DateUtil.getInstance().getDate(pageNum + firstCache), mState), new ServiceAPI().getZhiHuDaliyData(pageNum))
                        .first(new Func1<ArrayList<ZhiHuDailyItem>, Boolean>() {
                            @Override
                            public Boolean call(ArrayList<ZhiHuDailyItem> zhiHuDailyItems) {
                                return zhiHuDailyItems != null;
                            }
                        })
                        .compose(MainPresenter.this.<ArrayList<ZhiHuDailyItem>>deliverLatestCache())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(MainPresenter.this.split(new Action2<Main2Activity, ArrayList<ZhiHuDailyItem>>() {
                            @Override
                            public void call(Main2Activity view, ArrayList<ZhiHuDailyItem> data) {
                                view.setData(data);
                            }
                        }, new Action2<Main2Activity, Throwable>() {
                            @Override
                            public void call(Main2Activity view, Throwable throwable) {
                                view.onError(mState);
                            }
                        }));
            }
        });

        restartable(REQUEST_BANNER, new Func0<Subscription>() {
            @Override
            public Subscription call() {
                return
//                        Observable.concat(new DataCache().<ArrayList<ZhiHuBanner>>getCacheFile("banner"), new ServiceAPI().getBannerData())
                new ServiceAPI().getBannerData()
                        .first(new Func1<ArrayList<ZhiHuBanner>, Boolean>() {
                            @Override
                            public Boolean call(ArrayList<ZhiHuBanner> zhiHuBanners) {
                                return zhiHuBanners != null;
                            }
                        })
                        .compose(MainPresenter.this.<ArrayList<ZhiHuBanner>>deliverLatestCache())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(MainPresenter.this.split(new Action2<Main2Activity, ArrayList<ZhiHuBanner>>() {
                            @Override
                            public void call(Main2Activity view, ArrayList<ZhiHuBanner> zhiHuBanners) {
                                view.initBanner(zhiHuBanners);
                            }
                        }, new Action2<Main2Activity, Throwable>() {
                            @Override
                            public void call(Main2Activity view, Throwable throwable) {
                                view.onError(mState);
                            }
                        }));
            }
        });


    }

}
