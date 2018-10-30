package com.qbc.widget;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qbc.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by YF on 2018/10/30
 */
public class PushToast {
    private Activity mActivity;
    private static PushToast mInstance;
    private Toast mToast;
    private final int SHOW = 1;
    private final int HIDE = 0;
    private Object mTN;
    private Method mShow;
    private Method mHide;
    private Field mViewFeild;
    private long durationTime = 3*1000;

    public static PushToast getInstance() {
        if (mInstance == null) {
            mInstance = new PushToast();
        }
        return mInstance;
    }

    public void init(Activity activity) {
        mActivity = activity;
    }

    public void createToast(String title, String content) {
        if (mActivity == null) {
            return;
        }
        LayoutInflater inflater = mActivity.getLayoutInflater();//调用Activity的getLayoutInflater()
//        LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_push_toast, null); //加載layout下的布局
        LinearLayout llPushContent = (LinearLayout) view.findViewById(R.id.ll_push_content);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvTitle.setText(title);
        tvContent.setText(content);
        mToast = new Toast(mActivity);
        mToast.setView(view);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.TOP, 0, 0);
        reflectEnableClick();
        reflectToast();
        llPushContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String newsFlashId = params.get("InformationID");
                //Router.newIntent(mActivity).to(NewsFlashDetailActivity.class).putString(IntentKey.NEWS_FLASH_ID,newsFlashId).launch();
                handler.sendEmptyMessage(HIDE);
            }
        });
        if(mShow != null && mHide != null){
            handler.sendEmptyMessage(SHOW);
        }else{
            mToast.show();
        }
    }

    private void reflectEnableClick() {
        try {
            Object mTN;
            mTN = getField(mToast, "mTN");
            if (mTN != null) {
                Object mParams = getField(mTN, "mParams");
                if (mParams instanceof WindowManager.LayoutParams) {
                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                    //显示与隐藏动画
//                    params.windowAnimations = R.style.ClickToast;
                    //Toast可点击
                    params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                    //设置viewgroup宽高
                    params.width = WindowManager.LayoutParams.MATCH_PARENT; //设置Toast宽度为屏幕宽度
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT; //设置高度
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射字段
     *
     * @param object    要反射的对象
     * @param fieldName 要反射的字段名称
     */
    private static Object getField(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW:
                    handler.sendEmptyMessageDelayed(HIDE, durationTime);
                    show();
                    break;
                case HIDE:
                    hide();
                    break;
            }
        }
    };

    public void reflectToast() {
        Field field = null;
        try {
            field = mToast.getClass().getDeclaredField("mTN");
            field.setAccessible(true);
            mTN = field.get(mToast);
            mShow = mTN.getClass().getDeclaredMethod("show");
            mHide = mTN.getClass().getDeclaredMethod("hide");
            mViewFeild = mTN.getClass().getDeclaredField("mNextView");
            mViewFeild.setAccessible(true);
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        try {
            Field mNextViewField = mTN.getClass().getDeclaredField("mNextView");
            mNextViewField.setAccessible(true);
            LayoutInflater inflate = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = mToast.getView();
            mNextViewField.set(mTN, v);
            Method method = mTN.getClass().getDeclaredMethod("show", null);
            method.invoke(mTN,  null);
            mShow.invoke(mTN,  null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hide() {
        try {
            mHide.invoke(mTN,  null);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}
