package com.decody.manuel.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.decody.manuel.android.R;

public class MainActivity extends Activity implements View.OnClickListener {

    private ViewGroup mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mListView = (ViewGroup) findViewById(R.id.list);

        addDemo("Menu and Submenu", MenuAndSubmenuActivity.class);
    }

    @Override
    public void onClick(View view) {
        Class activityClass = (Class) view.getTag();

        startActivity(new Intent(this, activityClass));
    }

    private void addDemo(String demoName, Class<? extends Activity> activityClass) {
        Button b = new Button(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        b.setLayoutParams(layoutParams);
        b.setText(demoName);
        b.setTag(activityClass);
        b.setOnClickListener(this);

        mListView.addView(b);
    }
}
