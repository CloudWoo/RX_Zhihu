package com.haomiao.cloud.rx_zhihu.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;


public class GlideUtils {

    private static Context mContext;

public static void init(Context context){
    mContext = context;
}
public static void displayImage(String imageUrls, ImageView mImageView) {
    Glide.with(mContext)
            .load(imageUrls)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(mImageView);
    }

}
