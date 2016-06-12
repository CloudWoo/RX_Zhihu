package com.haomiao.cloud.rx_zhihu.service;


import com.haomiao.cloud.rx_zhihu.model.ZhiHuDaily;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuLatest;
import com.haomiao.cloud.rx_zhihu.model.webBody;

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

    //获取日报详情
    @GET("story/{id}")
    Observable<ZhiHuDaily> getDaliyDetail(@Path("id") String id);

    //首页轮播图
    @GET("api/4/news/latest")
    Observable<ZhiHuLatest> getBanner();


    @GET("api/4/news/8394662")
    Observable<webBody> getXml();






}
