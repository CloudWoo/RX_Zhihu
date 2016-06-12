package com.haomiao.cloud.rx_zhihu.presenter;

import android.os.Bundle;
import android.util.Log;

import com.haomiao.cloud.rx_zhihu.model.ZhiHuBanner;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuDailyItem;
import com.haomiao.cloud.rx_zhihu.service.ServiceAPI;
import com.haomiao.cloud.rx_zhihu.utils.DateUtil;
import com.haomiao.cloud.rx_zhihu.utils.DataCache;
import com.haomiao.cloud.rx_zhihu.utils.SPUtils;
import com.haomiao.cloud.rx_zhihu.view.activity.MainActivity;

import java.util.ArrayList;

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
public class MainPresenter extends BasePresenter<MainActivity> {

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
    protected void onTakeView(MainActivity view) {
        super.onTakeView(view);
        mState = STATE_DEFAULT;
        // 读取缓存
        if(!SPUtils.getInstance().isFirstIn()){
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
                    .subscribe(MainPresenter.this.split(new Action2<MainActivity, ArrayList<ZhiHuDailyItem>>() {
                        @Override
                        public void call(MainActivity view, ArrayList<ZhiHuDailyItem> data) {
                            view.setData(data);
                        }
                    }, new Action2<MainActivity, Throwable>() {
                        @Override
                        public void call(MainActivity view, Throwable throwable) {
                            view.onError(mState);
                        }
                    }));
        }else {
            SPUtils.getInstance().setFirstIn(false);
        }

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
                                if(zhiHuDailyItems !=null && zhiHuDailyItems.get(0).getDate().equals(DateUtil.getInstance().getDate(0))){
                                    firstCache = 0;
                                }
                                return zhiHuDailyItems != null;
                            }
                        })
                        .compose(MainPresenter.this.<ArrayList<ZhiHuDailyItem>>deliverLatestCache())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(MainPresenter.this.split(new Action2<MainActivity, ArrayList<ZhiHuDailyItem>>() {
                            @Override
                            public void call(MainActivity view, ArrayList<ZhiHuDailyItem> data) {
                                if(pageNum == 0){
                                    data.set(0, new ZhiHuDailyItem(
                                            data.get(0).getImages(),
                                            data.get(0).getId(),
                                            "今日要闻",
                                            data.get(0).getTitle(),
                                            0));
                                }
                                view.setData(data);
                            }
                        }, new Action2<MainActivity, Throwable>() {
                            @Override
                            public void call(MainActivity view, Throwable throwable) {
                                view.onError(mState);
                            }
                        }));
            }
        });

        restartable(REQUEST_BANNER, new Func0<Subscription>() {
            @Override
            public Subscription call() {
                return Observable.concat(new DataCache().<ArrayList<ZhiHuBanner>>getCacheFile("banner"), new ServiceAPI().getBannerData())
                        .filter(new Func1<ArrayList<ZhiHuBanner>, Boolean>() {
                            @Override
                            public Boolean call(ArrayList<ZhiHuBanner> zhiHuBanners) {
                                return zhiHuBanners != null;
                            }
                        })
                        .compose(MainPresenter.this.<ArrayList<ZhiHuBanner>>deliverLatestCache())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(MainPresenter.this.split(new Action2<MainActivity, ArrayList<ZhiHuBanner>>() {
                            @Override
                            public void call(MainActivity view, ArrayList<ZhiHuBanner> zhiHuBanners) {
                                view.initBanner(zhiHuBanners);
                            }
                        }, new Action2<MainActivity, Throwable>() {
                            @Override
                            public void call(MainActivity view, Throwable throwable) {
                                view.onError(mState);
                            }
                        }));
            }
        });


    }

}
