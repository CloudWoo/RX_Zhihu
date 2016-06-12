package com.haomiao.cloud.rx_zhihu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloud on 16/5/18.
 */
public class ZhiHuDaily {

    /**
     * date : 20150606
     * stories : [{"images":["http://pic1.zhimg.com/bf168baa27e568617aa76c04497c12bc.jpg"],"type":0,"id":4729274,"ga_prefix":"060622","title":"深夜惊奇 · 高考后大悲大喜"},{"title":"一句话剧透《星球大战》：我是你爹不不不（多图）","ga_prefix":"060621","images":["http://pic1.zhimg.com/69af4c594b195bcd61887dcfa4c39cb0.jpg"],"multipic":true,"type":0,"id":4774908},{"images":["http://pic4.zhimg.com/c08828568b292fc37fe6b55304bb28ab.jpg"],"type":0,"id":4785541,"ga_prefix":"060620","title":"珠宝是真是假，谁说了算？"},{"title":"有主食有小吃，有香肠有糖果，原来德国也这么多好吃的（多图）","ga_prefix":"060619","images":["http://pic2.zhimg.com/536b8a467c3d2c1dbd4f378caabc1415.jpg"],"multipic":true,"type":0,"id":4773248},{"images":["http://pic1.zhimg.com/283b4dbdc0b83e234d1d581a3a325fb4.jpg"],"type":0,"id":4780686,"ga_prefix":"060618","title":"之前对哥白尼的认识，可能都是错的"},{"images":["http://pic4.zhimg.com/cebe5f68541ddecfdade6e62c384faf7.jpg"],"type":0,"id":4786208,"ga_prefix":"060616","title":"咦，史上第一个会英语的中国人是怎么学的？"},{"title":"光吃蛋白粉就想增肌，性质跟拜菩萨差不多（多图）","ga_prefix":"060615","images":["http://pic1.zhimg.com/61bab651ee925e704183ec6a2a86ee6c.jpg"],"multipic":true,"type":0,"id":4776328},{"images":["http://pic2.zhimg.com/cbdd7d9f144ad5f9556d1555d8d49665.jpg"],"type":0,"id":4786157,"ga_prefix":"060614","title":"这种能尝出一个新世界的体验，真的好酷"},{"title":"在 AKB48 之前，日本的少女偶像们已经红了快半个世纪（多图）","ga_prefix":"060613","images":["http://pic1.zhimg.com/fa4fe669924d44636b563da01908bbd8.jpg"],"multipic":true,"type":0,"id":4783417},{"images":["http://pic1.zhimg.com/104d4267ea1aa24fea20fa8aba7ad68c.jpg"],"type":0,"id":4783868,"ga_prefix":"060612","title":"用过 Windows 95 就暴露年龄了？他用遍了每一代的版本"},{"title":"光是椅子，就够你在世博会看半天啦（多图）","ga_prefix":"060611","images":["http://pic2.zhimg.com/f5cea00d06ab2e78796bf9797d1506fd.jpg"],"multipic":true,"type":0,"id":4783849},{"images":["http://pic1.zhimg.com/b3f6f0ed75520b89d087753fb0124070.jpg"],"type":0,"id":4786933,"ga_prefix":"060610","title":"科学地告诉你为什么皮卡丘是一只凶残的怪兽"},{"images":["http://pic4.zhimg.com/1875458ad9690f9e59fb4a6d02ba605f.jpg"],"type":0,"id":4786634,"ga_prefix":"060609","title":"苦哈哈地捏寿司，同时还得揣摩小心思，不该多付些银两吗？"},{"images":["http://pic3.zhimg.com/eb9f024be6a4bfd6999161aeeab5246a.jpg"],"type":0,"id":4786931,"ga_prefix":"060608","title":"作为病人，最讨厌医生说什么？"},{"images":["http://pic1.zhimg.com/15489b7f394fff8a23bb602a21719b34.jpg"],"type":0,"id":4785702,"ga_prefix":"060607","title":"库克说谷歌侵犯隐私，他没说错但谷歌也没错"},{"title":"选一选，配制出又美又好用的单身公寓（多图）","ga_prefix":"060607","images":["http://pic2.zhimg.com/1c5cf22f19add5ae93606d1584d01bc9.jpg"],"multipic":true,"type":0,"id":4785599},{"images":["http://pic2.zhimg.com/6d604ed8d6514ae3b58423e5385a66b5.jpg"],"type":0,"id":4784408,"ga_prefix":"060607","title":"布拉特辞职了，中国有希望举办世界杯了？"},{"images":["http://pic1.zhimg.com/1722a37e7a787ef5a1bd62904c4177c4.jpg"],"type":0,"id":4784640,"ga_prefix":"060606","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    /**
     * images : ["http://pic1.zhimg.com/bf168baa27e568617aa76c04497c12bc.jpg"]
     * type : 0
     * id : 4729274
     * ga_prefix : 060622
     * title : 深夜惊奇 · 高考后大悲大喜
     */

    private ArrayList<StoriesEntity> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(ArrayList<StoriesEntity> stories) {
        this.stories = stories;
    }

    public static class StoriesEntity {
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public StoriesEntity(int type, String title) {
            this.title = title;
            this.type = type;
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

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
