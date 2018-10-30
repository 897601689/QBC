package com.qbc.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qbc.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YF on 2018/10/26
 */
public class CloseActivity extends BaseActivity {
    private static final String TAG = "CloseActivity";
    @BindView(R.id.txt_confirm)
    TextView txtConfirm;
    @BindView(R.id.txt_cancel)
    TextView txtCancel;
    @BindView(R.id.close_main)
    ConstraintLayout closeMain;
    @BindView(R.id.loading)
    AVLoadingIndicatorView loading;

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

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            MyShutDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
            case R.id.txt_cancel:
                finish();
                break;
        }
    }


    //关机代码
    private void MyShutDown() {
        try {
            Process process = Runtime.getRuntime().exec("reboot -p");

            String data = null;
            BufferedReader errorLine = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            BufferedReader inputLine = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String error = null;
            while ((error = errorLine.readLine()) != null && !error.equals("null")) {
                data += error + "\n";
            }

            String input = null;
            while ((input = inputLine.readLine()) != null && !input.equals("null")) {
                data += input + "\n";
            }

        } catch (Exception e) {

        }
    }
}
