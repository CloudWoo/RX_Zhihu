package com.haomiao.cloud.rx_zhihu.view.widget.Theme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.haomiao.cloud.rx_zhihu.R;
import com.haomiao.cloud.rx_zhihu.utils.SPUtils;
import com.haomiao.cloud.rx_zhihu.view.activity.Main2Activity;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.View.Widget.Theme.
 * Created by Cloud on 16/5/27.
 * Instruction
 */
public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        IntentFilter filter = new IntentFilter(Main2Activity.ACTION_MODE_CHANGE);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.d("MyTextView", "SPUtils.getInstance().getTheme():" + SPUtils.getInstance().getTheme());
                if(SPUtils.getInstance().getTheme() == R.style.LightTheme){
                    setTextColor(getResources().getColor(R.color.white));
                }else {
                    setTextColor(getResources().getColor(R.color.black_900));

                }
            }
        };
        context.registerReceiver(broadcastReceiver, filter);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
