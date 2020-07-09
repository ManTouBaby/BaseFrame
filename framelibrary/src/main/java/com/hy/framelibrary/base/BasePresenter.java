package com.hy.framelibrary.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.hy.framelibrary.HYFrameHelper;
import com.hy.framelibrary.net.INetHelper;
import com.hy.framelibrary.utils.HYLog;

import java.lang.ref.SoftReference;

public class BasePresenter implements LifecycleObserver {
    private SoftReference<AppCompatActivity> softReference;
    private SoftReference<Fragment> fragmentSoftReference;
    protected INetHelper mNetHelper;

    public BasePresenter(AppCompatActivity appCompatActivity) {
        mNetHelper = HYFrameHelper.getInstance().getNetHelper();
        softReference = new SoftReference<>(appCompatActivity);
    }

    public BasePresenter(Fragment fragment) {
        mNetHelper = HYFrameHelper.getInstance().getNetHelper();
        fragmentSoftReference = new SoftReference<>(fragment);
    }

    public void setNetHelper(INetHelper mNetHelper) {
        this.mNetHelper = mNetHelper;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        HYLog.d("onCreate: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        HYLog.d("onDestroy: ");
        mNetHelper.onCancelRequest(this);
        if (softReference != null) softReference.clear();
        if (fragmentSoftReference != null) fragmentSoftReference.clear();
    }

}
