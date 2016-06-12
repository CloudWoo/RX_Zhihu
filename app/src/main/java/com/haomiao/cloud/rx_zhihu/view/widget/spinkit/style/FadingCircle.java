package com.haomiao.cloud.rx_zhihu.view.widget.spinkit.style;

import android.animation.ValueAnimator;

import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.animation.SpriteAnimatorBuilder;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.CircleSprite;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.CircleSpriteGroup;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.Sprite;


/**
 * Created by ybq.
 */
public class FadingCircle extends CircleSpriteGroup {

    @Override
    public Sprite[] onCreateChild() {
        Dot[] dots = new Dot[12];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot();
            dots[i].setAnimationDelay(100 * i);
        }
        return dots;
    }

    class Dot extends CircleSprite {

        @Override
        public ValueAnimator getAnimation() {
            float fractions[] = new float[]{0f, 0.4f, 1f};
            return new SpriteAnimatorBuilder(this).
                    alpha(fractions, 0, 255, 0).
                    duration(1200).
                    easeInOut(fractions).build();
        }
    }
}
