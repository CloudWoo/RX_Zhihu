package com.haomiao.cloud.rx_zhihu.view.widget.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.view.animation.LinearInterpolator;

import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.animation.SpriteAnimatorBuilder;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.CircleSprite;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.Sprite;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.SpriteGroup;


/**
 * Created by ybq.
 */
public class ChasingDots extends SpriteGroup {

    @Override
    public Sprite[] onCreateChild() {
        return new Sprite[]{
                new Dot(),
                new Dot()
        };
    }

    @Override
    public void onChildCreated(Sprite... sprites) {
        super.onChildCreated(sprites);
        sprites[1].setAnimationDelay(-1000);
    }

    @Override
    public ValueAnimator getAnimation() {
        float fractions[] = new float[]{0f, 1f};
        return new SpriteAnimatorBuilder(this).
                rotate(fractions, 0, 360).
                duration(2000).
                interpolator(new LinearInterpolator()).
                build();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        int drawW = (int) (bounds.width() * 0.6f);
        getChildAt(0).setDrawBounds(
                bounds.left,
                bounds.top,
                bounds.right
                , bounds.top + drawW
        );
        getChildAt(1).setDrawBounds(
                bounds.left,
                bounds.bottom - drawW,
                bounds.right,
                bounds.bottom
        );
    }


    class Dot extends CircleSprite {
        @Override
        public ValueAnimator getAnimation() {
            float fractions[] = new float[]{0f, 0.5f, 1f};
            return new SpriteAnimatorBuilder(this).
                    scale(fractions, 0f, 1f, 0f).
                    duration(2000).
                    easeInOut(fractions)
                    .build();
        }

    }

}
