package com.zhanjixun.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import com.zhanjixun.R;

/**
 * 自定义编辑框，带下划线样式
 * 
 * @author 詹命天子
 *
 */
public class LineEditText extends EditText {
	private Paint mPaint;

	public LineEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);

		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.LineEditText);
		mPaint.setColor(ta.getColor(R.styleable.LineEditText_lineColor,
				Color.BLACK));
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 画底线
		canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1, mPaint);
	}
}
