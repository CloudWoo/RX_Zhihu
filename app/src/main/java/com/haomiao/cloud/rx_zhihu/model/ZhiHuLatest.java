package com.haomiao.cloud.rx_zhihu.model;

import java.io.Serializable;
import java.util.List;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.Model.
 * Created by Cloud on 16/6/1.
 * Instruction
 */
public class ZhiHuLatest implements Serializable{

    /**
     * date : 20160601
     * stories : [{"images":["http://pic2.zhimg.com/dbce87a8194793be2dd839da9a4b187d.jpg"],"type":0,"id":8381327,"ga_prefix":"060111","title":"越成功的人越自恋？那我为什么还没成功\u2026\u2026"},{"images":["http://pic3.zhimg.com/507bb9ffe6255732b05103ced9170e96.jpg"],"type":0,"id":8383417,"ga_prefix":"060110","title":"怎么租房子不上当？作为房产律师，我有这些建议"},{"images":["http://pic2.zhimg.com/d4e37ee807c87542a1c65f06281dfe45.jpg"],"type":0,"id":8381308,"ga_prefix":"060109","title":"离婚后「被负债」300 万，婚姻法的错？"},{"images":["http://pic1.zhimg.com/f5d9c95912a887b67f984ed5de1b07a4.jpg"],"type":0,"id":8385068,"ga_prefix":"060108","title":"人人都在看「大」数据，费力调查出的数据还有用吗？"},{"images":["http://pic4.zhimg.com/b540a94044847fc4595dc9e4fc17f6d7.jpg"],"type":0,"id":8382455,"ga_prefix":"060107","title":"哺乳期妈妈吃什么，对宝宝的影响其实没那么大"},{"images":["http://pic4.zhimg.com/8089b583d982abbdcdbcbb5eb47062d7.jpg"],"type":0,"id":8383133,"ga_prefix":"060107","title":"网红电商火得不要不要的，符合这些条件的才好拿钱"},{"title":"教你在城市里看日出，开启元气满满的一天","ga_prefix":"060107","images":["http://pic4.zhimg.com/56b192ac1fe1ff2a3063a869d8c7dbff.jpg"],"multipic":true,"type":0,"id":8377594},{"images":["http://pic4.zhimg.com/718cd59d3a4fa1e5a8fb645acf121b9b.jpg"],"type":0,"id":8385108,"ga_prefix":"060107","title":"读读日报 24 小时热门 TOP 5 · 把童年的动漫金曲串烧着弹了一遍"},{"images":["http://pic1.zhimg.com/0b432e9c361d9e785e1c3ee3a06ea138.jpg"],"type":0,"id":8382896,"ga_prefix":"060106","title":"瞎扯 · 熊孩子"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/a38c31e6dfc87a8bd7b288d50e08dc17.jpg","type":0,"id":8385108,"ga_prefix":"060107","title":"读读日报 24 小时热门 TOP 5 · 把童年的动漫金曲串烧着弹了一遍"},{"image":"http://pic4.zhimg.com/de0513f4fe7c34e4151effb66c704393.jpg","type":0,"id":8383133,"ga_prefix":"060107","title":"网红电商火得不要不要的，符合这些条件的才好拿钱"},{"image":"http://pic4.zhimg.com/40ed00f47a14baa77c29e886e6924d97.jpg","type":0,"id":8377594,"ga_prefix":"060107","title":"教你在城市里看日出，开启元气满满的一天"},{"image":"http://pic2.zhimg.com/494354ea836aabdd5a9d42e6473bc475.jpg","type":0,"id":8381308,"ga_prefix":"060109","title":"离婚后「被负债」300 万，婚姻法的错？"},{"image":"http://pic4.zhimg.com/f5e063f5638e1a0f4b3a60ee45a803bb.jpg","type":0,"id":8382410,"ga_prefix":"053117","title":"小时候最有趣最好玩的世界乐园，现在变得破败不堪了\u2026\u2026"}]
     */

    private String date;
    /**
     * images : ["http://pic2.zhimg.com/dbce87a8194793be2dd839da9a4b187d.jpg"]
     * type : 0
     * id : 8381327
     * ga_prefix : 060111
     * title : 越成功的人越自恋？那我为什么还没成功……
     */

    private List<StoriesEntity> stories;
    /**
     * image : http://pic4.zhimg.com/a38c31e6dfc87a8bd7b288d50e08dc17.jpg
     * type : 0
     * id : 8385108
     * ga_prefix : 060107
     * title : 读读日报 24 小时热门 TOP 5 · 把童年的动漫金曲串烧着弹了一遍
     */

    private List<TopStoriesEntity> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesEntity implements Serializable{
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesEntity implements Serializable{
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

