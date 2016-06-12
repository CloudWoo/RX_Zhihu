package com.haomiao.cloud.rx_zhihu.view.widget.spinkit.style;

import android.animation.ValueAnimator;

import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.animation.SpriteAnimatorBuilder;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.CircleSprite;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.CircleSpriteGroup;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.Sprite;


/**
 * Created by ybq.
 */
public class Circle extends CircleSpriteGroup {

    @Override
    public Sprite[] onCreateChild() {
        Dot[] dots = new Dot[12];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot();
            dots[i].setAnimationDelay(1200 / 12 * i + -1200);
        }
        return dots;
    }

    class Dot extends CircleSprite {
        @Override
        public ValueAnimator getAnimation() {
            float fractions[] = new float[]{0f, 0.5f, 1f};
            return new SpriteAnimatorBuilder(this).
                    scale(fractions, 0f, 1f, 0f).
                    duration(1200).
                    easeInOut(fractions)
                    .build();
        }
    }
}
