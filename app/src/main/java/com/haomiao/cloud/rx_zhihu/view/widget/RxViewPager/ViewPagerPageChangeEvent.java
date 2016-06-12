package com.haomiao.cloud.rx_zhihu.view.widget.RxViewPager;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.jakewharton.rxbinding.view.ViewEvent;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.View.Widget.RxViewPager.
 * Created by Cloud on 16/5/31.
 * Instruction
 */
public class ViewPagerPageChangeEvent extends ViewEvent<ViewPager> {

    public int position() {
        return position;
    }

    public float positionOffset() {
        return positionOffset;
    }

    public int positionOffsetPixels() {
        return positionOffsetPixels;
    }

    private final int position;
    private final float positionOffset;
    private final int positionOffsetPixels;

    @CheckResult
    @NonNull
    public static ViewPagerPageChangeEvent create(@NonNull ViewPager view, int position, float positionOffset, int positionOffsetPixels) {
        return new ViewPagerPageChangeEvent(view, position, positionOffset, positionOffsetPixels);
    }




    private ViewPagerPageChangeEvent(@NonNull ViewPager view, int position, float positionOffset, int positionOffsetPixels) {
        super(view);
        this.position = position;
        this.positionOffset = positionOffset;
        this.positionOffsetPixels = positionOffsetPixels;
    }



}
