package com.hy.framelibrary.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void showShort(Context context, String label) {
        Toast.makeText(context, label, Toast.LENGTH_SHORT).show();
    }
}
