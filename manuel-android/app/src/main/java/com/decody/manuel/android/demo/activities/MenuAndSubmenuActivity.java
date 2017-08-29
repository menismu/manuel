package com.decody.manuel.android.demo.activities;

import android.app.Activity;
import android.os.Bundle;

import com.decody.manuel.android.demo.R;
import com.decody.manuel.android.ui.TwoLevelMenuView;

public class MenuAndSubmenuActivity extends Activity {

    private TwoLevelMenuView mTwoLevelMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menuandsubmenu);

        mTwoLevelMenu = findViewById(R.id.two_level_menu);
    }
}
