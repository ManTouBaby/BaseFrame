package com.hy.baseframe;

import android.app.Application;

import com.hy.framelibrary.HYFrameHelper;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HYFrameHelper.initFrame(this, 1);
    }
}
