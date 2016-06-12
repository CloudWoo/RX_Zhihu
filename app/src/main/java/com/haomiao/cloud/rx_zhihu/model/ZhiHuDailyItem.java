package com.haomiao.cloud.rx_zhihu.model;

import java.io.Serializable;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.Model.
 * Created by Cloud on 16/5/26.
 * Instruction
 */
public class ZhiHuDailyItem implements Serializable {
    String images;
    int id;
    String date;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    int position;

    public ZhiHuDailyItem(String images, int id, String date, String title, int position) {
        this.images = images;
        this.id = id;
        this.date = date;
        this.title = title;
        this.position = position;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title;
}
