package com.decody.manuel.android.ui;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class TwoLevelMenuView extends RelativeLayout {

    public TwoLevelMenuView(Context context) {
        super(context);
    }

    public TwoLevelMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TwoLevelMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TwoLevelMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
