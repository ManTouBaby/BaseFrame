package com.hy.framelibrary.net;

import android.app.Application;
import android.text.TextUtils;

import com.hy.framelibrary.net.listener.OnBaseResultListener;
import com.hy.framelibrary.net.listener.OnBaseUploadProgressListener;
import com.hy.framelibrary.net.listener.OnDownProgressListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class NetHelper extends INetHelper {
    private String savePath;

    @Override
    public void init(Application application, Map<String, String> commonHeaders, Map<String, String> commonParams, int retryCount, HttpClient httpClient) {
        savePath = httpClient.getCachePath();
        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo okGo = OkGo.getInstance().init(application)                           //必须调用初始化
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(retryCount);//全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
        if (commonHeaders != null) {
            HttpHeaders headers = new HttpHeaders();
            for (Map.Entry<String, String> entry : commonHeaders.entrySet()) {
                headers.put(entry.getKey(), entry.getValue());
            }
            okGo.addCommonHeaders(headers);
        }        //全局公共头
        if (commonParams != null) {
            HttpParams httpParams = new HttpParams();
            for (Map.Entry<String, String> entry : commonParams.entrySet()) {
                httpParams.put(entry.getKey(), entry.getValue());
            }
            okGo.addCommonParams(httpParams);

        }                     //全局公共参数

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        switch (httpClient.getPrintLevel()) {
            case BODY:
                loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
                break;
            case NONE:
                loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.NONE);        //log打印级别，决定了log显示的详细程度
                break;
            case BASIC:
                loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BASIC);        //log打印级别，决定了log显示的详细程度
                break;
            case HEADERS:
                loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.HEADERS);        //log打印级别，决定了log显示的详细程度
                break;
        }


        builder.addInterceptor(loggingInterceptor);
        loggingInterceptor.setColorLevel(Level.FINER);                               //log颜色级别，决定了log在控制台显示的颜色                            //添加OkGo默认debug日志
        //第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
        //builder.addInterceptor(new ChuckInterceptor(tag));

        //超时时间设置，默认60秒
        builder.readTimeout(httpClient.getReadTimeout(), TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(httpClient.getWriteTimeout(), TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(httpClient.getConnectTimeout(), TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(tag)));            //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(application)));              //使用数据库保持cookie，如果cookie不过期，则一直有效

        okGo.setOkHttpClient(builder.build());                       //建议设置OkHttpClient，不设置会使用默认的
    }


    @Override
    public <T> void getRequestTest(Object tag, String url, OnBaseResultListener<T> onResultListener) {
        GetRequest<String> request = OkGo.<String>get(url).tag(tag);
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String str = response.body();
                onResultListener.convertData(str);
            }
        });
    }

    @Override
    public <T> void getRequest(Object tag, String url, OnBaseResultListener<T> onResultListener) {
        getRequest(tag, url, null, onResultListener);
    }

    @Override
    public <T> void getRequest(Object tag, String url, Map<String, String> params, OnBaseResultListener<T> onResultListener) {
        getRequest(tag, url, params, null, onResultListener);
    }

    @Override
    public <T> void getRequest(Object tag, String url, Map<String, String> param, Map<String, String> headers, OnBaseResultListener<T> onResultListener) {
        GetRequest<String> request = OkGo.<String>get(url).tag(tag);

        if (param != null) request.params(param);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.headers(entry.getKey(), entry.getValue());
            }
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String str = response.body();
                onResultListener.convertData(str);
            }
        });
    }

    @Override
    public void getFile(Object tag, String url, OnDownProgressListener onResultListener) {
        getFile(tag, url, null, onResultListener);
    }


    @Override
    public void getFile(Object tag, String url, Map<String, String> param, OnDownProgressListener onResultListener) {
        getFile(tag, url, null, param, null, onResultListener);
    }

    @Override
    public void getFile(Object tag, String url, String saveName, Map<String, String> param, Map<String, String> headers, OnDownProgressListener onResultListener) {
        GetRequest<File> request = OkGo.<File>get(url).tag(tag);
        if (param != null) request.params(param);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.headers(entry.getKey(), entry.getValue());
            }
        }
        //文件下载时，可以指定下载的文件目录和文件名
        request.execute(new FileCallback() {
            @Override
            public void onStart(Request<File, ? extends Request> request) {
                super.onStart(request);
                onResultListener.onStart();
            }

            @Override
            public void onSuccess(Response<File> response) {
                onResultListener.onComplete(response.body());
            }

            @Override
            public void onError(Response<File> response) {
                onResultListener.onError(response.getException());
            }

            @Override
            public void downloadProgress(Progress progress) {
                onResultListener.onProgress(progress);
            }
        });
    }

    @Override
    public <T> void postRequest(Object tag, String url, OnBaseResultListener<T> onResultListener) {
        postRequest(tag, url, null, onResultListener);
    }

    @Override
    public <T> void postRequest(Object tag, String url, Map<String, String> params, OnBaseResultListener<T> onResultListener) {
        postRequest(tag, url, params, null, onResultListener);
    }

    @Override
    public <T> void postRequest(Object tag, String url, Map<String, String> param, Map<String, String> headers, OnBaseResultListener<T> onResultListener) {
        PostRequest<String> request = OkGo.<String>post(url).tag(tag);

        if (param != null) request.params(param);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.headers(entry.getKey(), entry.getValue());
            }
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String str = response.body();
                onResultListener.convertData(str);
            }
        });
    }

    @Override
    public <T> void postFile(Object tag, String url, String fileKey, List<File> files, OnBaseUploadProgressListener<T> onResultListener) {
        postFile(tag, url, null, fileKey, files, onResultListener);
    }

    @Override
    public <T> void postFile(Object tag, String url, Map<String, String> param, String fileKey, List<File> files, OnBaseUploadProgressListener<T> onResultListener) {
        postFile(tag, url, param, fileKey, files, null, onResultListener);
    }

    @Override
    public <T> void postFile(Object tag, String url, Map<String, File> fileMap, OnBaseUploadProgressListener<T> onResultListener) {
        postFile(tag, url, null, fileMap, onResultListener);
    }

    @Override
    public <T> void postFile(Object tag, String url, Map<String, String> param, Map<String, File> fileMap, OnBaseUploadProgressListener<T> onResultListener) {
        postFile(tag, url, param, fileMap, null, onResultListener);
    }

    @Override
    public <T> void postFile(Object tag, String url, Map<String, String> param, String fileKey, List<File> files, Map<String, String> headers, OnBaseUploadProgressListener<T> onResultListener) {
        PostRequest<String> request = OkGo.<String>post(url).tag(tag);
        if (param != null) request.params(param);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.headers(entry.getKey(), entry.getValue());
            }
        }
        if (fileKey != null && files != null && files.size() > 0) { // 这种方式为同一个key，上传多个文件
            request.addFileParams(fileKey, files);
        }
        request.execute(new StringCallback() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {
                onResultListener.onStart();
            }

            @Override
            public void onSuccess(Response<String> response) {
                onResultListener.convertData(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                onResultListener.onError(response.getException());
            }

            @Override
            public void uploadProgress(Progress progress) {
                onResultListener.onProgress(progress);
            }
        });
    }

    @Override
    public <T> void postFile(Object tag, String url, Map<String, String> param, Map<String, File> fileMap, Map<String, String> headers, OnBaseUploadProgressListener<T> onResultListener) {
        PostRequest<String> request = OkGo.<String>post(url).tag(tag);
        if (param != null) request.params(param);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.headers(entry.getKey(), entry.getValue());
            }
        }
        if (fileMap != null) {//这种方式为一个key，对应一个文件
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                request.params(entry.getKey(), entry.getValue());
            }
        }
        request.execute(new StringCallback() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {
                onResultListener.onStart();
            }

            @Override
            public void onSuccess(Response<String> response) {
                onResultListener.convertData(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                onResultListener.onError(response.getException());
            }

            @Override
            public void uploadProgress(Progress progress) {
                onResultListener.onProgress(progress);
            }
        });
    }

    @Override
    public void onCancelRequest(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }
}
