package com.haomiao.cloud.rx_zhihu.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.haomiao.cloud.rx_zhihu.utils.TransitionHelper;
import com.haomiao.cloud.rx_zhihu.view.activity.DailyItemActivity;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.View.
 * Created by Cloud on 16/5/24.
 * Instruction
 */
public class ActionManger {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void toDailyDetail(Activity activity, int id){
//        Intent intent = new Intent(context, DailyItemActivity.class);
//        intent.putExtra("id", ""+id);
//        context.startActivity(intent);

        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, true);
        Intent i = new Intent(activity, DailyItemActivity.class);
        i.putExtra("id", ""+id);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
        activity.startActivity(i, transitionActivityOptions.toBundle());






    }


}
