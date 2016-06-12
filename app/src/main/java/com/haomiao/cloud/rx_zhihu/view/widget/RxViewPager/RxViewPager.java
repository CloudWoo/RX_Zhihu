package com.haomiao.cloud.rx_zhihu.view.widget.RxViewPager;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;


import rx.Observable;

import static com.jakewharton.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.View.Widget.RxViewPager.
 * Created by Cloud on 16/5/31.
 * Instruction
 */
public class RxViewPager {

    @CheckResult @NonNull
    public static Observable<ViewPagerPageChangeEvent> pageChangeEvents(
            @NonNull ViewPager view) {
        checkNotNull(view, "view == null");
        return Observable.create(new ViewPagerPageChangeEventOnSubscribe(view));
    }

    @CheckResult @NonNull
    public static Observable<Boolean> pageScrollStateChangeEvents(
            @NonNull ViewPager view) {
        checkNotNull(view, "view == null");
        return Observable.create(new ViewPagerPageScrollStateChangeEventOnSubscribe(view));
    }


    private RxViewPager() {
        throw new AssertionError("No instances.");
    }


}
