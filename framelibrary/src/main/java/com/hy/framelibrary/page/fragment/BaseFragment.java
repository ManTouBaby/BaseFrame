package com.hy.framelibrary.page.fragment;

import android.app.Dialog;
import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hy.framelibrary.R;
import com.zlylib.upperdialog.LoadingDialog;

public abstract class BaseFragment extends Fragment {
    private boolean isAddStatusBar = false;
    private int statusColor = -1;

    private LinearLayout mFragmentView;
    private Dialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.frame_base_fragment, null);
        if (isAddStatusBar) mFragmentView.addView(createStatusBarView(), 0);
        mFragmentView.addView(inflater.inflate(getLayout(), container, false));
        return mFragmentView;
    }

    public void setAddStatusBar(boolean addStatusBar, @ColorInt int statusColor) {
        isAddStatusBar = addStatusBar;
        this.statusColor = statusColor;

        Lifecycle lifecycle = getLifecycle();
    }

    private View createStatusBarView() {
        View view = new View(getContext());
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatueBarHeight());
        view.setLayoutParams(layoutParams);
        if (statusColor != -1) view.setBackgroundColor(statusColor);
        return view;
    }

    private int getStatueBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return (int) getResources().getDimension(identifier);
        }
        return 0;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
    }

    protected abstract int getLayout();

    protected abstract void initView(View view);

    protected void go(Class<?> aClass) {
        go(aClass, null);
    }

    protected void go(Class<?> aClass, Bundle bundle) {
        Intent intent = new Intent(getContext(), aClass);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void goForResult(Class<?> aClass, int requestCode) {
        goForResult(aClass, null, requestCode);
    }

    protected void goForResult(Class<?> aClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getContext(), aClass);
        if (bundle != null) intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    protected void showLoading(){
        showLoading("数据加载中...");
    }
    protected void showLoading(String message) {
        if (mLoadingDialog == null || !mLoadingDialog.isShowing()){
            mLoadingDialog = LoadingDialog.createLoadingDialog(getContext(), message);
            mLoadingDialog.show();
        }
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
