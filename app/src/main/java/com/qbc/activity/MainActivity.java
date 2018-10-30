package com.qbc.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qbc.R;
import com.qbc.widget.PushToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.txt_test)
    TextView txtTest;
    @BindView(R.id.txt_close)
    TextView txtClose;
    @BindView(R.id.txt_set)
    TextView txtSet;
    @BindView(R.id.txt_query)
    TextView txtQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    /**
     * 检测设备是否存在Camera硬件
     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // 存在
            return true;
        } else {
            // 不存在
            return false;
        }
    }


    @OnClick({R.id.txt_test, R.id.txt_close, R.id.txt_set, R.id.txt_query})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.txt_test:
                intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_close:
                intent = new Intent(this, CloseActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_set:

                break;
            case R.id.txt_query:
                break;
        }
    }
}
