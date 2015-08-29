package com.apolloyang.deuter.View;

import android.view.View;

/**
 * Created by julianlo on 8/29/15.
 */
public class OnScrollChangedHelper {

    /* To wire this up:
       - create a subclass of a control
       - add a private member OnScrollChangedHelper
       - override onScrollChanged and call handleOnScrollChanged
       - create a setScrollListener(OnScrollChangedHelper.ScrollListener) method and call handleSetOnScrollListener
         set the return value to your private member
     */

    // region Interfaces
    public interface ScrollListener {
        void onScrollChanged(View sender, int l, int t, int oldl, int oldt);
    }
    // endregion

    // region Members
    private View mView;
    private ScrollListener mScrollListener;
    // endregion

    public OnScrollChangedHelper(View view) {
        mView = view;
    }

    public static OnScrollChangedHelper handleSetScrollListener(View view, OnScrollChangedHelper helper, ScrollListener listener) {
        if (listener != null) {
            if (helper == null) {
                helper = new OnScrollChangedHelper(view);
            }
            helper.mScrollListener = listener;
        } else {
            helper = null;
        }
        return helper;
    }

    public static void handleOnScrollChanged(OnScrollChangedHelper helper, int l, int t, int oldl, int oldt) {
        if ((helper != null) && (helper.mScrollListener != null)) {
            helper.mScrollListener.onScrollChanged(helper.mView, l, t, oldl, oldt);
        }
    }
}
