package com.haomiao.cloud.rx_zhihu.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.View.Widget.
 * Created by Cloud on 16/5/24.
 * Instruction
 */
public class LoadMoreRecycler extends RecyclerView{

    public LoadMoreRecycler(Context context) {
        super(context);
    }

    public LoadMoreRecycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreRecycler(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

    }


    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

    }
}
