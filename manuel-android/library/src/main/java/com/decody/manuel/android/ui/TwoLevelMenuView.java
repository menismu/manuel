package com.decody.manuel.android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.decody.manuel.android.R;

public class TwoLevelMenuView extends RelativeLayout {

    private int mTopMenuId;

    private int mBottomMenuId;

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

        try {
            mTopMenuId = a.getResourceId(R.styleable.TwoLevelMenuView_topMenu, 0);
            mBottomMenuId = a.getResourceId(R.styleable.TwoLevelMenuView_bottomMenu, 0);
        } finally {
            a.recycle();
        }

        // set up common layout params
        LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        addView(inflater.inflate(mTopMenuId, null), params);
        addView(inflater.inflate(mBottomMenuId, null), params);
    }
}
