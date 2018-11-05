package com.qbc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qbc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YF on 2018/11/5
 */
public class SetActivity extends BaseActivity {

    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.txt_time)
    TextView txtTime;
    @BindView(R.id.txt_calibration)
    TextView txtCalibration;
    @BindView(R.id.txt_maintain)
    TextView txtMaintain;
    @BindView(R.id.txt_back)
    TextView txtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.txt_time, R.id.txt_calibration, R.id.txt_maintain, R.id.txt_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_time:
                break;
            case R.id.txt_calibration:
                break;
            case R.id.txt_maintain:
                break;
            case R.id.txt_back:
                finish();
                break;
        }
    }
}
