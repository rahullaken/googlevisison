package com.app.googlevision.utils;

import android.content.Context;
import android.graphics.Color;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

public class FaceTracker extends Tracker<Face> {
    private static final String TAG = FaceTracker.class.getSimpleName();

    private RoundedFrameLayout mRoundedFrameLayout;
    private OnUpdateValue mOnUpdateValue;
    private Context mContext;

    public FaceTracker(Context context, RoundedFrameLayout roundedFrameLayout, OnUpdateValue onUpdateValue) {
        mContext = context;
        mRoundedFrameLayout = roundedFrameLayout;
        mOnUpdateValue = onUpdateValue;
    }

    @Override
    public void onUpdate(Detector.Detections<Face> detections, Face face) {
        super.onUpdate(detections, face);
        if ((Math.abs(face.getEulerY()) > -12 && Math.abs(face.getEulerY()) < 12)) {
            mRoundedFrameLayout.setStrokeColor(Color.GREEN);
            mOnUpdateValue.onUpdateValue(true);
            //mCapture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_camera_enable));
        } else {
            mRoundedFrameLayout.setStrokeColor(Color.RED);
            mOnUpdateValue.onUpdateValue(false);
            //mCapture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_camera_disable));
        }

    }
}
