package com.hy.framelibrary.utils;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class HYToast {
    private static Application mApplication;

    public static void init(Application application) {
        mApplication = application;
    }

    public static void showShort(String label) {
        Toast.makeText(mApplication, label, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String label) {
        Toast.makeText(mApplication, label, Toast.LENGTH_LONG).show();
    }
}
