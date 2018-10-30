package com.qbc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.qbc.R;
import com.qbc.util.FontsUtils;

/**
 * Created by admin on 2018/3/2.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        FontsUtils.setAppTypeface(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent it = new Intent();
                it.setClass(SplashActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        }, 500);
    }


}
