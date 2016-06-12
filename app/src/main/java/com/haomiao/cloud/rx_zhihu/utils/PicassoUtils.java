package com.haomiao.cloud.rx_zhihu.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;


public class PicassoUtils {

    private static Context mContext;

public static void init(Context context){
    mContext = context;
}
public static void displayImage(String imageUrls, ImageView mImageView) {

//        Picasso.with(mContext)
//                .load(imageUrls)
////                .placeholder(R.mipmap.bg_loading)
////                .error(R.mipmap.bg_load_fail)
//                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
//                .config(Bitmap.Config.RGB_565)
//                .into(mImageView);
    Glide.with(mContext)
            .load(imageUrls)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(mImageView);




    }
public static void displayCacheImage(String imageUrls, ImageView mImageView) {

        Picasso.with(mContext)
                .load(imageUrls)
//                .placeholder(R.mipmap.bg_loading)
//                .error(R.mipmap.bg_load_fail)
                .config(Bitmap.Config.RGB_565)
                .into(mImageView);

    }
}
