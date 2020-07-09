package com.hy.baseframe;

import android.app.Application;

import com.hy.framelibrary.HYFrameHelper;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new HYFrameHelper.Builder()
                .setRetryCount(3)
                .create(this);
    }
}
