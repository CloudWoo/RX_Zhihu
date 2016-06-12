package com.haomiao.cloud.rx_zhihu.view.widget.spinkit.style;

import android.animation.ValueAnimator;

import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.animation.SpriteAnimatorBuilder;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.CircleSprite;


/**
 * Created by ybq.
 */
public class Pulse extends CircleSprite {

    @Override
    public ValueAnimator getAnimation() {
        float fractions[] = new float[]{0f, 1f};
        return new SpriteAnimatorBuilder(this).
                scale(fractions, 0f, 1f).
                alpha(fractions, 255, 0).
                duration(1000).
                easeInOut(fractions)
                .build();
    }
}
