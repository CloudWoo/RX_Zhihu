package com.haomiao.cloud.rx_zhihu.view.widget.Theme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.haomiao.cloud.rx_zhihu.R;
import com.haomiao.cloud.rx_zhihu.utils.SPUtils;
import com.haomiao.cloud.rx_zhihu.view.activity.MainActivity;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.View.Widget.
 * Created by Cloud on 16/5/27.
 * Instruction
 */
public class MyRelativelayout extends RelativeLayout {
    public MyRelativelayout(Context context) {
        super(context);
    }

    public MyRelativelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        IntentFilter filter = new IntentFilter(MainActivity.ACTION_MODE_CHANGE);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(SPUtils.getInstance().getTheme() == R.style.LightTheme){
                    setBackgroundColor(getResources().getColor(R.color.white));
                }else {
                    setBackgroundColor(getResources().getColor(R.color.black_600));

                }
            }
        };
        context.registerReceiver(broadcastReceiver, filter);

    }

    public MyRelativelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }






}
