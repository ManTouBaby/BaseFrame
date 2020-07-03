package com.hy.framelibrary.utils;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

public class HYLog {
    private static String TAG;

    public static void init(Application application) {
        if (TextUtils.isEmpty(TAG)) TAG = "HY-" + application.getPackageName();
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void i(String msg) {
        Log.i(TAG, msg);
    }
}
