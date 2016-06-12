package com.haomiao.cloud.rx_zhihu.view.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import com.haomiao.cloud.rx_zhihu.R;
import com.haomiao.cloud.rx_zhihu.utils.LocalDisplay;


public class ViewPagerIndicator extends View{

	private static float cx = 0;
	private static float cy = 0;
	private static float radius = 0;
	private static float offset = 0;
	private Paint mPaint;
	private Paint mPaint1;
	private int num = 3 ;
	private boolean hasMove;
	@Override
	protected void onDraw(Canvas canvas) {
		radius = LocalDisplay.getScreenWidth()/100;
		cy = (float) (getHeight() * 0.9);
		cx = (float) (LocalDisplay.getScreenWidth()/ 2 - (num-1) * 1.5 * radius);
		for (int i = 0; i < num; i++) {
			canvas.drawCircle(cx+i*radius*3, cy , radius, mPaint);	

		}
		if(hasMove){
			canvas.drawCircle(cx+offset, cy , radius, mPaint1);
		}
	}
	public void move(float persent,int position){
		hasMove = true;
		if(position == num){
			offset = position*radius*3;
		}else{
			offset = persent*radius*3 + position*radius*3;				
		}
		invalidate();
	}
	public ViewPagerIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(getResources().getColor(R.color.grey_300));
		mPaint1 = new Paint();
		mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint1.setColor(getResources().getColor(R.color.blue_400));

	}
	public void setNum(int num){
		this.num = num;
	}
}
