package com.hy.framelibrary;

import android.app.Application;

import com.hy.framelibrary.net.HttpClient;
import com.hy.framelibrary.net.INetHelper;
import com.hy.framelibrary.net.NetHelper;
import com.hy.framelibrary.utils.HYLog;
import com.hy.framelibrary.utils.HYToast;

import java.util.Map;

public class HYFrameHelper {
    private static HYFrameHelper mFrameHelper;
    private INetHelper netHelper;
    private HttpClient httpClient;

    private HYFrameHelper(Application application, INetHelper netHelper, HttpClient httpClient) {
        this.netHelper = netHelper;
        this.httpClient = httpClient;
        //初始化日志
        HYLog.init(application);
        //初始化Toast
        HYToast.init(application);
    }

    private static HYFrameHelper instance(Application application, INetHelper netHelper, HttpClient httpClient) {
        if (mFrameHelper == null) {
            synchronized (HYFrameHelper.class) {
                if (mFrameHelper == null) {
                    mFrameHelper = new HYFrameHelper(application, netHelper, httpClient);
                }
            }
        }
        return mFrameHelper;
    }

    public static HYFrameHelper getInstance() {
        return mFrameHelper;
    }

    public INetHelper getNetHelper() {
        return netHelper;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public static class Builder {
        INetHelper netHelper;

        Map<String, String> commonHeaders;//公共头部
        Map<String, String> commonParams;//公共参数
        HttpClient httpClient;
        int retryCount;


        public Builder setNetHelper(INetHelper netHelper) {
            this.netHelper = netHelper;
            return this;
        }

        public Builder setCommoneHeaders(Map<String, String> commonHeaders) {
            this.commonHeaders = commonHeaders;
            return this;
        }

        public Builder setCommonParams(Map<String, String> commonParams) {
            this.commonParams = commonParams;
            return this;
        }

        public Builder setRetryCount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }

        public Builder setHttpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public HYFrameHelper create(Application application) {
            if (netHelper == null) netHelper = new NetHelper();
            if (httpClient == null) httpClient = getHttpClient();
            netHelper.init(application, commonHeaders, commonParams, retryCount, httpClient);
            return HYFrameHelper.instance(application, netHelper, httpClient);
        }

        private HttpClient getHttpClient() {
            return new HttpClient.Builder().build();
        }
    }

}
