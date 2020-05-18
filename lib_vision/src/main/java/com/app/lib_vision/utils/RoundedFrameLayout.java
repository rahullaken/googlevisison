package com.app.lib_vision.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import static android.graphics.Path.Direction.CCW;
import static android.graphics.Path.Direction.CW;

public class RoundedFrameLayout extends FrameLayout {
    private float mRadius;
    private Path mPath = new Path();
    private RectF mRect = new RectF();

    Paint paintBorder, paintCircle;

    public RoundedFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRadius = attrs.getAttributeFloatValue(null, "corner_radius", 0f);
        init();
    }

    private void init() {
        paintBorder = new Paint();
        paintBorder.setColor(Color.RED);
        paintBorder.setStrokeWidth(20);
        paintBorder.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float w = getWidth();
        float h = getHeight();
        mPath.reset();
        mRect.set(0, 0, w, h);
        mPath.addRoundRect(mRect, mRadius, mRadius, CCW);
        mPath.close();
        super.onDraw(canvas);
        canvas.drawPath(mPath, paintBorder);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // compute the mPath
        float centerX = w / 2f; // calculating half width
        float centerY = h / 2f; // calculating half height
        mPath.reset();
        mPath.addCircle(centerX, centerY, Math.min(centerX, centerY), CW);
        mPath.close();

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.clipPath(mPath);
        super.dispatchDraw(canvas);
        canvas.drawPath(mPath, paintBorder);
    }

    public void setStrokeColor(int color) {
        paintBorder.setColor(color);
        postInvalidate();
    }
}
