package com.haomiao.cloud.rx_zhihu.service;

import android.util.Log;

import com.haomiao.cloud.rx_zhihu.model.ZhiHuBanner;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuDaily;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuDailyItem;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuLatest;
import com.haomiao.cloud.rx_zhihu.utils.DateUtil;
import com.haomiao.cloud.rx_zhihu.utils.data.DataCache;
import com.haomiao.cloud.rx_zhihu.utils.data.Database;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.service.
 * Created by Cloud on 16/6/6.
 * Instruction
 */
public class ServiceAPI {
    private ServiceManger service = ServiceFactory.createServiceFrom(ServiceManger.class, ServiceManger.ENDPOINT);

    public Observable<ArrayList<ZhiHuDailyItem>> getZhiHuDaliyData(final int pageNum) {
        return service.getZhiHuDaliyData(DateUtil.getInstance().getDate(pageNum))
                .map(new Func1<ZhiHuDaily, ArrayList<ZhiHuDailyItem>>() {
                    @Override
                    public ArrayList<ZhiHuDailyItem> call(ZhiHuDaily zhiHuDaily) {
                        ArrayList<ZhiHuDailyItem> list = new ArrayList<ZhiHuDailyItem>();
                        for (int position = 0; position < zhiHuDaily.getStories().size(); position++) {
                            list.add(new ZhiHuDailyItem(
                                    zhiHuDaily.getStories().get(position).getImages().get(0),
                                    zhiHuDaily.getStories().get(position).getId(),
                                    DateUtil.getInstance().getDateTitle(pageNum),
                                    zhiHuDaily.getStories().get(position).getTitle(),
                                    position
                            ));
                        }

                        return list;
                    }
                })
                .doOnNext(new Action1<ArrayList<ZhiHuDailyItem>>() {
                    @Override
                    public void call(ArrayList<ZhiHuDailyItem> zhiHuDailyItems) {
                        new DataCache().cacheData(zhiHuDailyItems, DateUtil.getInstance().getDate(pageNum));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArrayList<ZhiHuBanner>> getBannerData() {
        return service.getBanner()
                .map(new Func1<ZhiHuLatest, ArrayList<ZhiHuBanner>>() {
                    @Override
                    public ArrayList<ZhiHuBanner> call(ZhiHuLatest zhiHuLatest) {
                        List<ZhiHuLatest.TopStoriesEntity> topStoriesEntities = zhiHuLatest.getTop_stories();
                        ArrayList<ZhiHuBanner> list = new ArrayList();
                        for (ZhiHuLatest.TopStoriesEntity topStoriesEntity : topStoriesEntities) {
                            ZhiHuBanner zhiHuBanner = new ZhiHuBanner();
                            zhiHuBanner.id = topStoriesEntity.getId();
                            zhiHuBanner.title = topStoriesEntity.getTitle();
                            zhiHuBanner.images = topStoriesEntity.getImage();
                            list.add(zhiHuBanner);
                        }
                        return list;
                    }
                })
                .doOnNext(new Action1<ArrayList<ZhiHuBanner>>() {
                    @Override
                    public void call(ArrayList<ZhiHuBanner> zhiHuBanners) {
                        new DataCache().cacheData(zhiHuBanners, "banner");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }







}
