package com.hy.framelibrary;

import android.app.Application;

import com.hy.framelibrary.net.HYNetHelper;
import com.hy.framelibrary.utils.HYLog;
import com.hy.framelibrary.utils.HYToast;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import okhttp3.OkHttpClient;

public class HYFrameHelper {
    public static void initFrame(Application application, int retryCount) {
        //初始化日志
        HYLog.init(application);
        //初始化Toast
        HYToast.init(application);
        //初始化网络框架
        HYNetHelper.init(application, retryCount);
    }

    public static void initFrame(Application application, HttpHeaders headers, HttpParams params, OkHttpClient.Builder builder, int retryCount) {
        //初始化日志
        HYLog.init(application);
        //初始化Toast
        HYToast.init(application);
        //初始化网络框架
        HYNetHelper.init(application, headers, params, builder, retryCount);
    }
}
