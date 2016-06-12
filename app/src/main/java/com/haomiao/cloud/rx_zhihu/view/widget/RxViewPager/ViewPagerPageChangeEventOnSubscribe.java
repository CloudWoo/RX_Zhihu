package com.haomiao.cloud.rx_zhihu.view.widget.RxViewPager;

import android.support.v4.view.ViewPager;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static com.jakewharton.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.View.Widget.RxViewPager.
 * Created by Cloud on 16/5/31.
 * Instruction
 */
final class ViewPagerPageChangeEventOnSubscribe implements Observable.OnSubscribe<ViewPagerPageChangeEvent>{
    final ViewPager viewPager;
    private int mPosition;
    private float mPositionOffset;
    private int mPositionOffsetPixels;
    private boolean isDragging;
    public ViewPagerPageChangeEventOnSubscribe(ViewPager viewPager) {
        this.viewPager = viewPager;
    }



    @Override
    public void call(final Subscriber<? super ViewPagerPageChangeEvent> subscriber) {
        checkUiThread();
        final ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPosition = position;
                mPositionOffset = positionOffset;
                mPositionOffsetPixels = positionOffsetPixels;

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(ViewPagerPageChangeEvent.create(viewPager, mPosition, mPositionOffset, mPositionOffsetPixels));

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("ViewPagerPageChangeEven", "change");
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        isDragging = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        isDragging = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        isDragging = false;
                        break;
                    default:
                        break;
                }
            }

        };

        viewPager.addOnPageChangeListener(listener);

        subscriber.add(new MainThreadSubscription() {
            @Override protected void onUnsubscribe() {
                viewPager.addOnPageChangeListener(null);
            }
        });
    }
}
