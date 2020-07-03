package com.hy.framelibrary.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.app.AppCompatActivity;

import com.hy.framelibrary.net.JsonConvertAdapter;
import com.hy.framelibrary.net.NetHelper;
import com.hy.framelibrary.net.INetHelper;
import com.hy.framelibrary.utils.HYLog;

import java.lang.ref.SoftReference;

public class BasePresenter implements LifecycleObserver {
    private SoftReference<AppCompatActivity> softReference;
    protected INetHelper mNetHelper;

    public BasePresenter(AppCompatActivity appCompatActivity) {
        mNetHelper = new NetHelper(new JsonConvertAdapter());
        softReference = new SoftReference<>(appCompatActivity);
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
        mNetHelper.onCancelRequest();
        softReference.clear();
    }

}
