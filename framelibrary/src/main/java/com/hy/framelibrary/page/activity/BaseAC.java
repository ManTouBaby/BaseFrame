package com.hy.framelibrary.page.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.hy.framelibrary.R;

public abstract class BaseAC extends AppCompatActivity {
    private AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView(savedInstanceState);
    }

    protected abstract int getLayout();

    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 设置内容全屏,即内容延伸至状态栏底部,状态栏文字还在
     */
    public void setFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void startLoading() {
        if (dialog == null) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            dialog = alertDialog.setView(R.layout.popup_loading).show();
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    protected BaseAC getContext(){
        return this;
    }

    private void stopLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
