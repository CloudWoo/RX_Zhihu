package com.haomiao.cloud.rx_zhihu.model;

import java.io.Serializable;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.Model.
 * Created by Cloud on 16/6/1.
 * Instruction
 */
public class ZhiHuBanner implements Serializable{
    public String images;
    public int id;
    public String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
