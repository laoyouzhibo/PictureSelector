package com.luck.picture.lib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.luck.picture.lib.R;


public final class BackgroundProgressArc extends View {
	private Paint mBgPaint;
	private Paint mStrokePaint;
	private float mStrokeWidth;
	private float mHalfStrokeWidth;
	private RectF mRectF;
	private float mProgress;
	private int mBgColor;
	private int mArcColor;

	private void init(AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.BackgroundProgressArc, 0, 0);
			mBgColor = a.getColor(R.styleable.BackgroundProgressArc_bgArcColor, 0x66FFFFFF);
			mArcColor = a.getColor(R.styleable.BackgroundProgressArc_progressArcColor, Color.WHITE);
			mStrokeWidth = a.getDimension(R.styleable.BackgroundProgressArc_BackgroundProgressArc_strokeWidth, 16.0F);
			a.recycle();
		}

		mHalfStrokeWidth = mStrokeWidth / 2;
		mBgPaint = new Paint();
		mBgPaint.setAntiAlias(true);
		mBgPaint.setColor(0x66FFFFFF);
		mBgPaint.setStrokeWidth(16f);
		mBgPaint.setStyle(Paint.Style.STROKE);
		mBgPaint.setStrokeCap(Paint.Cap.ROUND);

		mStrokePaint = new Paint();
		mStrokePaint.setAntiAlias(true);
		mStrokePaint.setColor(Color.WHITE);
		mStrokePaint.setStrokeWidth(16f);
		mStrokePaint.setStyle(Paint.Style.STROKE);
		mStrokePaint.setStrokeCap(Paint.Cap.ROUND);

	}

	@SuppressLint("DrawAllocation")
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
		int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
		mRectF = new RectF(
				mHalfStrokeWidth + getPaddingLeft(), mHalfStrokeWidth + getPaddingTop(),
				width - getPaddingRight() - mHalfStrokeWidth, height - getPaddingBottom() - mHalfStrokeWidth
		);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawArc(mRectF, 0.0F, 360.0F, false, mBgPaint);
		Float sweepAngle = mProgress * 360f;
		canvas.drawArc(mRectF, -90.0F, sweepAngle, false, mStrokePaint);
	}

	public void setProgress(float progress) {
		this.mProgress = progress;
		this.invalidate();
	}

	public BackgroundProgressArc(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		this.init(attrs);
	}
}



