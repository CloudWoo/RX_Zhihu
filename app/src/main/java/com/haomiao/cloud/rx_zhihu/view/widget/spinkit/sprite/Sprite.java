package com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Property;

import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.animation.AnimationUtils;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.animation.FloatProperty;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.animation.IntProperty;


/**
 * Created by ybq.
 */
public abstract class Sprite extends Drawable implements
        ValueAnimator.AnimatorUpdateListener
        , Animatable
        , Drawable.Callback {

    private static final Rect ZERO_BOUNDS_RECT = new Rect();
    protected Rect drawBounds = ZERO_BOUNDS_RECT;
    private float scale = 1;
    private float scaleX = 1;
    @SuppressWarnings("unused")
    public static final Property<Sprite, Float> SCALE_X = new FloatProperty<Sprite>("scaleX") {
        @Override
        public void setValue(Sprite object, float value) {
            object.setScaleX(value);
        }

        @Override
        public Float get(Sprite object) {
            return object.getScaleX();
        }
    };
    private float scaleY = 1;
    public static final Property<Sprite, Float> SCALE_Y = new FloatProperty<Sprite>("scaleY") {
        @Override
        public void setValue(Sprite object, float value) {
            object.setScaleY(value);
        }

        @Override
        public Float get(Sprite object) {
            return object.getScaleY();
        }
    };
    public static final Property<Sprite, Float> SCALE = new FloatProperty<Sprite>("scale") {
        @Override
        public void setValue(Sprite object, float value) {
            object.setScale(value);
        }

        @Override
        public Float get(Sprite object) {
            return object.getScale();
        }
    };
    private float pivotX;
    private float pivotY;
    private int animationDelay;
    private int rotateX;
    public static final Property<Sprite, Integer> ROTATE_X = new IntProperty<Sprite>("rotateX") {
        @Override
        public void setValue(Sprite object, int value) {
            object.setRotateX(value);
        }

        @Override
        public Integer get(Sprite object) {
            return object.getRotateX();
        }
    };
    private int rotateY;
    public static final Property<Sprite, Integer> ROTATE_Y = new IntProperty<Sprite>("rotateY") {
        @Override
        public void setValue(Sprite object, int value) {
            object.setRotateY(value);
        }

        @Override
        public Integer get(Sprite object) {
            return object.getRotateY();
        }
    };
    private int translateX;
    @SuppressWarnings("unused")
    public static final Property<Sprite, Integer> TRANSLATE_X = new IntProperty<Sprite>("translateX") {
        @Override
        public void setValue(Sprite object, int value) {
            object.setTranslateX(value);
        }

        @Override
        public Integer get(Sprite object) {
            return object.getTranslateX();
        }
    };
    private int translateY;
    @SuppressWarnings("unused")
    public static final Property<Sprite, Integer> TRANSLATE_Y = new IntProperty<Sprite>("translateY") {
        @Override
        public void setValue(Sprite object, int value) {
            object.setTranslateY(value);
        }

        @Override
        public Integer get(Sprite object) {
            return object.getTranslateY();
        }
    };
    private int rotate;
    public static final Property<Sprite, Integer> ROTATE = new IntProperty<Sprite>("rotate") {
        @Override
        public void setValue(Sprite object, int value) {
            object.setRotate(value);
        }

        @Override
        public Integer get(Sprite object) {
            return object.getRotate();
        }
    };
    private float translateXPercentage;
    public static final Property<Sprite, Float> TRANSLATE_X_PERCENTAGE = new FloatProperty<Sprite>("translateXPercentage") {
        @Override
        public void setValue(Sprite object, float value) {
            object.setTranslateXPercentage(value);
        }

        @Override
        public Float get(Sprite object) {
            return object.getTranslateXPercentage();
        }
    };
    private float translateYPercentage;
    public static final Property<Sprite, Float> TRANSLATE_Y_PERCENTAGE = new FloatProperty<Sprite>("translateYPercentage") {
        @Override
        public void setValue(Sprite object, float value) {
            object.setTranslateYPercentage(value);
        }

        @Override
        public Float get(Sprite object) {
            return object.getTranslateYPercentage();
        }
    };
    private ValueAnimator animator;
    private int alpha = 255;
    public static final Property<Sprite, Integer> ALPHA = new IntProperty<Sprite>("alpha") {
        @Override
        public void setValue(Sprite object, int value) {
            object.setAlpha(value);
        }

        @Override
        public Integer get(Sprite object) {
            return object.getAlpha();
        }
    };
    private Camera mCamera;
    private Matrix mMatrix;

    public Sprite() {
        mCamera = new Camera();
        mMatrix = new Matrix();
    }

    public abstract int getColor();

    public abstract void setColor(int color);

    @Override
    public int getAlpha() {
        return alpha;
    }

    @Override
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.RGBA_8888;
    }

    public float getTranslateXPercentage() {
        return translateXPercentage;
    }

    public void setTranslateXPercentage(float translateXPercentage) {
        this.translateXPercentage = translateXPercentage;
    }

    public float getTranslateYPercentage() {
        return translateYPercentage;
    }

    public void setTranslateYPercentage(float translateYPercentage) {
        this.translateYPercentage = translateYPercentage;
    }

    public int getTranslateX() {
        return translateX;
    }

    public void setTranslateX(int translateX) {
        this.translateX = translateX;
    }

    public int getTranslateY() {
        return translateY;
    }

    public void setTranslateY(int translateY) {
        this.translateY = translateY;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
        setScaleX(scale);
        setScaleY(scale);
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public int getRotateX() {
        return rotateX;
    }

    public void setRotateX(int rotateX) {
        this.rotateX = rotateX;
    }

    public int getRotateY() {
        return rotateY;
    }

    public void setRotateY(int rotateY) {
        this.rotateY = rotateY;
    }

    public float getPivotX() {
        return pivotX;
    }

    public void setPivotX(float pivotX) {
        this.pivotX = pivotX;
    }

    public float getPivotY() {
        return pivotY;
    }

    public void setPivotY(float pivotY) {
        this.pivotY = pivotY;
    }

    @SuppressWarnings("unused")
    public int getAnimationDelay() {
        return animationDelay;
    }

    public Sprite setAnimationDelay(int animationDelay) {
        this.animationDelay = animationDelay;
        return
                this;
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    public abstract ValueAnimator getAnimation();

    @Override
    public void start() {
        animator = obtainAnimation();
        if (animator == null) {
            return;
        }
        if (animator.isStarted()) {
            return;
        }

        AnimationUtils.start(animator);
        invalidateSelf();
    }

    public ValueAnimator obtainAnimation() {
        if (animator == null) {
            animator = getAnimation();
        }
        if (animator != null) {
            animator.setStartDelay(animationDelay);
            animator.addUpdateListener(this);
        }
        return animator;
    }

    @Override
    public void stop() {
        if (animator != null) {
            animator.end();
            reset();
            onAnimationUpdate(animator);
        }
    }

    protected abstract void drawSelf(Canvas canvas);

    public void reset() {
        scale = 1;
        rotateX = 0;
        rotateY = 0;
        translateX = 0;
        translateY = 0;
        rotate = 0;
        translateXPercentage = 0f;
        translateYPercentage = 0f;
    }

    @Override
    public boolean isRunning() {
        return AnimationUtils.isRunning(animator);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        setDrawBounds(bounds);
    }

    public void setDrawBounds(int left, int top, int right, int bottom) {
        this.drawBounds = new Rect(left, top, right, bottom);
        setPivotX(getDrawBounds().centerX());
        setPivotY(getDrawBounds().centerY());
    }

    @Override
    public void invalidateDrawable(Drawable who) {
        invalidateSelf();
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {

    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {

    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public Rect getDrawBounds() {
        return drawBounds;
    }

    public void setDrawBounds(Rect drawBounds) {
        setDrawBounds(drawBounds.left, drawBounds.top, drawBounds.right, drawBounds.bottom);
    }

    @Override
    public void draw(Canvas canvas) {

        int tx = getTranslateX();
        tx = tx == 0 ? (int) (getBounds().width() * getTranslateXPercentage()) : tx;
        int ty = getTranslateY();
        ty = ty == 0 ? (int) (getBounds().height() * getTranslateYPercentage()) : ty;
        canvas.translate(tx,
                ty);
        canvas.scale(getScaleX(), getScaleY(), getPivotX(), getPivotY());
        canvas.rotate(getRotate(), getPivotX(), getPivotY());

        if (getRotateX() != 0 || getRotateY() != 0) {
            mCamera.save();
            mCamera.rotateX(getRotateX());
            mCamera.rotateY(getRotateY());
            mCamera.getMatrix(mMatrix);
            mMatrix.preTranslate(-getPivotX(), -getPivotY());
            mMatrix.postTranslate(getPivotX(), getPivotY());
            mCamera.restore();
            canvas.concat(mMatrix);
        }
        drawSelf(canvas);
    }

    public Rect clipSquare(Rect rect) {
        int w = rect.width();
        int h = rect.height();
        int min = Math.min(w, h);
        int cx = rect.centerX();
        int cy = rect.centerY();
        int r = min / 2;
        return new Rect(
                cx - r,
                cy - r,
                cx + r,
                cy + r
        );
    }


}
