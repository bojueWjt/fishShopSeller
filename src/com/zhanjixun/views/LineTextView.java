package com.zhanjixun.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class LineTextView extends TextView {
	private Paint mPaint;

	public LineTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(0xFFEAEAEA);
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// »­µ×Ïß
		canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1, mPaint);
	}
}
