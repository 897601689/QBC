package com.qbc.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qbc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YF on 2018/10/26
 */
public class CloseActivity extends BaseActivity {
    @BindView(R.id.txt_confirm)
    TextView txtConfirm;
    @BindView(R.id.txt_cancel)
    TextView txtCancel;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.close_main)
    ConstraintLayout closeMain;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.txt_confirm, R.id.txt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_confirm:
                closeMain.setBackgroundResource(R.drawable.shutdown_bg);
                loading.setVisibility(View.VISIBLE);
                txtConfirm.setVisibility(View.GONE);
                txtCancel.setVisibility(View.GONE);

                break;
            case R.id.txt_cancel:
                finish();
                break;
        }
    }
}
