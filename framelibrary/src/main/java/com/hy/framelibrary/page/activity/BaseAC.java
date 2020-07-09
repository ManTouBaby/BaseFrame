package com.hy.framelibrary.page.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.hy.framelibrary.R;
import com.hy.framelibrary.base.BasePresenter;
import com.hy.framelibrary.base.IPresenter;
import com.hy.framelibrary.utils.HYLog;
import com.zlylib.upperdialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAC extends AppCompatActivity {
    private Dialog mLoadingDialog;

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

    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    protected BaseAC getContext() {
        return this;
    }

    protected void go(Class<?> aClass) {
        go(aClass, null);
    }

    protected void go(Class<?> aClass, Bundle bundle) {
        Intent intent = new Intent(this, aClass);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void goForResult(Class<?> aClass, int requestCode) {
        goForResult(aClass, null, requestCode);
    }

    protected void goForResult(Class<?> aClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, aClass);
        if (bundle != null) intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    protected void showLoading(String message) {
        if (mLoadingDialog == null || !mLoadingDialog.isShowing())
            mLoadingDialog = LoadingDialog.createLoadingDialog(getContext(), message);
    }

    protected void closeLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.cancel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeLoading();
    }
}
