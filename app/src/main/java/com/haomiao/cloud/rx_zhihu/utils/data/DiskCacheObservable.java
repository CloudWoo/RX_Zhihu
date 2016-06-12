package com.haomiao.cloud.rx_zhihu.utils.data;

import android.util.Log;

import com.google.gson.Gson;
import com.haomiao.cloud.rx_zhihu.CApplication;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuDailyItem;

import java.io.File;
import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.Utils.Data.
 * Created by Cloud on 16/5/27.
 * Instruction
 */
public class DiskCacheObservable {
    private static String DATA_FILE_NAME = "data.db";
    File dataFile = new File(CApplication.getInstance().getFilesDir(), DATA_FILE_NAME);
    Gson gson = new Gson();


    public Observable<ArrayList<ZhiHuDailyItem>> getDiskCache(final boolean isFirst){

        return Observable.create(new Observable.OnSubscribe<ArrayList<ZhiHuDailyItem>>() {
            @Override
            public void call(Subscriber<? super ArrayList<ZhiHuDailyItem>> subscriber) {
                if(isFirst){
                    subscriber.onNext(Database.getInstance().readItems());
                }else {
                    subscriber.onNext(null);
                }
                subscriber.onCompleted();

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public void saveToDisk(final ArrayList<ZhiHuDailyItem> data){
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {

                Database.getInstance().writeItems(data);

                Log.d("DiskCacheObservable", data.get(0).getTitle());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

}
