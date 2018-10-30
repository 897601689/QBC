package com.qbc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qbc.R;
import com.qbc.widget.PushToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YF on 2018/10/29
 */
public class TestActivity extends BaseActivity {

    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.del)
    TextView del;
    @BindView(R.id.edit_id)
    EditText editId;
    @BindView(R.id.txt_btn)
    TextView txtBtn;
    @BindView(R.id.layout_id)
    LinearLayout layoutId;
    @BindView(R.id.yes)
    TextView yes;
    @BindView(R.id.on)
    TextView on;
    @BindView(R.id.layout_mg)
    LinearLayout layoutMg;
    @BindView(R.id.layout_load)
    LinearLayout layoutLoad;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //PushToast.getInstance().init(this);
        editId.setSelection(editId.getText().toString().length());
    }

    @OnClick({R.id.del, R.id.txt_btn, R.id.yes, R.id.on})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.del:
                if (editId.getText().length() > 0)
                    editId.setText(editId.getText().toString().substring(0, editId.getText().length() - 1));

                break;
            case R.id.txt_btn:
                if (editId.getText().length() > 0) {
                    layoutId.setVisibility(View.GONE);
                    layoutMg.setVisibility(View.VISIBLE);
                }else{
                    //PushToast.getInstance().createToast("测试测试","请输入测试样本ID！");
                    Toast.makeText(this,"请输入测试样本ID！",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.yes:
                layoutLoad.setVisibility(View.VISIBLE);
                layoutId.setVisibility(View.GONE);
                layoutMg.setVisibility(View.GONE);
                break;
            case R.id.on:
                layoutId.setVisibility(View.VISIBLE);
                layoutMg.setVisibility(View.GONE);
                break;
        }
    }



}
