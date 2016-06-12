package com.haomiao.cloud.rx_zhihu.utils;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.haomiao.cloud.rx_zhihu.event.Events;
import com.haomiao.cloud.rx_zhihu.event.RxBus;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.Utils.
 * Created by Cloud on 16/5/30.
 * Instruction
 */
public class AutoScrollObservable {
    private ViewPager viewPager ;
    private RxAppCompatActivity context;
    private boolean isAlive = true;
    private boolean isDragging = false;
    private boolean isVisible = true;
    private Subscription subscription;


    public static void start(ViewPager viewPager, RxAppCompatActivity context){
        new AutoScrollObservable(viewPager, context);
    }
    private AutoScrollObservable(ViewPager viewPager, RxAppCompatActivity context) {
        this.viewPager = viewPager;
        this.context = context;
        autoScroll();
        RxBus.with(context)
                .setEvent(Events.BANNER_VISIBILITY)
                .setEndEvent(ActivityEvent.DESTROY)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        isVisible = (Boolean) events.message;
                    }
                })
                .create();

        RxBus.with(context)
                .setEvent(Events.BANNER_DRAG_STATE)
                .setEndEvent(ActivityEvent.DESTROY)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        isDragging = (Boolean) events.message;
                    }
                })
                .create();

        RxBus.with(context)
                .setEvent(Events.BANNER_ISALIVE)
                .setEndEvent(ActivityEvent.DESTROY)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        isAlive = (Boolean) events.message;
                    }
                })
                .create();
    }

    private void  autoScroll() {
        viewPager.setCurrentItem(1000);
        subscription =  Observable.timer(4, TimeUnit.SECONDS)
                    .repeat(Integer.MAX_VALUE)
                    .filter(new Func1<Long, Boolean>() {
                        @Override
                        public Boolean call(Long aLong) {
                            return isAlive && !isDragging && isVisible;
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Long>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(Long aLong) {
                            Log.d("AutoScrollObservable", "100");
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1 );
                        }
                    });

    }



}
