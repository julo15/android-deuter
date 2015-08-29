package com.apolloyang.deuter.View;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by julianlo on 8/29/15.
 */
public class OnClickHelper implements View.OnTouchListener {

    /* To wire this up:
       - create a subclass of a control
       - add a private member OnClickHelper
       - override setOnClickListener and call OnClickHelper.handleSetOnClickListener(View, OnClickHelper, View.OnClickListener).
         push the return value to your private member
     */

    private static class TouchInfo {
        public int actionIndex;
        public float x;
        public float y;
    }

    // Members
    private View mView;
    private View.OnClickListener mListener;
    private TouchInfo mCurrentTouch;

    private OnClickHelper(View view, View.OnClickListener listener) {
        mView = view;
        mListener = listener;
        mView.setOnTouchListener(this);
    }

    public static OnClickHelper handleSetOnClickListener(View view, OnClickHelper helper, View.OnClickListener listener) {
        if (helper != null) {
            helper.clearListener();
        }

        helper = (listener != null) ? new OnClickHelper(view, listener) : null;
        return helper;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (true) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    mCurrentTouch = new TouchInfo();
                    mCurrentTouch.actionIndex = event.getActionIndex();
                    mCurrentTouch.x = event.getX();
                    mCurrentTouch.y = event.getY();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    if ((mCurrentTouch != null) && (mCurrentTouch.actionIndex == event.getActionIndex())) {
                        float dx = event.getX() - mCurrentTouch.x;
                        float dy = event.getY() - mCurrentTouch.y;
                        float distanceSquared = (dx * dx) + (dy * dy);
                        final float MOVE_TOLERANCE = 10;
                        boolean isClick = (distanceSquared < (MOVE_TOLERANCE * MOVE_TOLERANCE));
                        if (isClick) {
                            mListener.onClick(view);
                        }
                        mCurrentTouch = null;
                    }
                    break;
                }
                case MotionEvent.ACTION_CANCEL: {
                    mCurrentTouch = null;
                    break;
                }
            }
        }
        return false;
    }

    private void clearListener() {
        mView.setOnTouchListener(null);
    }
}

