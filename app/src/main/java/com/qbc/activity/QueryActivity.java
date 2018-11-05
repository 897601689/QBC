package com.qbc.activity;

import android.os.Bundle;

import com.qbc.R;

import butterknife.ButterKnife;

/**
 * Created by YF on 2018/11/5
 */
public class QueryActivity extends  BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);
    }
}
