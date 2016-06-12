package com.haomiao.cloud.rx_zhihu.service;


import com.haomiao.cloud.rx_zhihu.model.ZhiHuDaily;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuLatest;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Cloud on 16/5/18.
 */
public interface ServiceManger {
    String ENDPOINT = "http://news.at.zhihu.com/";

    // 获取首页日报
    @GET("api/3/news/before/{date}")
    Observable<ZhiHuDaily> getZhiHuDaliyData(@Path("date") String date);

    //首页轮播图
    @GET("api/4/news/latest")
    Observable<ZhiHuLatest> getBanner();

}
