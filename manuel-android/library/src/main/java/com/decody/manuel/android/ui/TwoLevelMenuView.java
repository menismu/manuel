package com.decody.manuel.android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.decody.manuel.android.R;

public class TwoLevelMenuView extends RelativeLayout {

    private View mTopView;

    private View mBottomView;

    public TwoLevelMenuView(Context context) {
        super(context);
    }

    public TwoLevelMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize(context, attrs);
    }

    public TwoLevelMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TwoLevelMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        // retrieve the references of both top and bottom menus
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TwoLevelMenuView,
                0, 0);

        int topMenuId;
        int bottomMenuId;

        try {
            topMenuId = a.getResourceId(R.styleable.TwoLevelMenuView_topMenu, 0);
            bottomMenuId = a.getResourceId(R.styleable.TwoLevelMenuView_bottomMenu, 0);
        } finally {
            a.recycle();
        }

        // set up common layout params
        LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mTopView = inflater.inflate(topMenuId, null);
        mBottomView = inflater.inflate(bottomMenuId, null);

        addView(mBottomView, params);
        addView(mTopView, params);

        setOnTouchListener(new CustomOnTouchListener(getContext()));
    }

    private class CustomOnTouchListener implements View.OnTouchListener {

        private GestureDetector mGestureDetector;

        CustomOnTouchListener(Context context) {
            mGestureDetector = new GestureDetector(context, new CustomGestureListener());
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            return mGestureDetector.onTouchEvent(motionEvent);
        }
    }

    private class CustomGestureListener implements GestureDetector.OnGestureListener {

        /**
         * It is the minimum distance to consider the gesture a vertical scroll.
         */
        private static final int GESTURE_MINIMUM_Y_DISTANCE = 5;

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {

            if ((distanceY < GESTURE_MINIMUM_Y_DISTANCE && distanceY > -GESTURE_MINIMUM_Y_DISTANCE)) {

                return false;
            }

            updateViews(distanceY);

            return true;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        private void updateViews(float distanceY) {
            float newTopY = mTopView.getY() - distanceY;

            // update top view location (vertically)
            if (newTopY < mBottomView.getY() - mBottomView.getHeight()) {
                newTopY = mBottomView.getY() - mBottomView.getHeight();
            } else if (newTopY > mBottomView.getY()) {
                newTopY = mBottomView.getY();
            }

            mTopView.setY(newTopY);

            // update alpha bottom view
            float alpha = (mBottomView.getY() - mTopView.getY()) / mBottomView.getHeight();

            mBottomView.setAlpha(alpha);
        }
    }
}
