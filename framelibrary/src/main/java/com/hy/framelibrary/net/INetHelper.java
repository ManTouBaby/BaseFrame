package com.hy.framelibrary.net;

import android.app.Application;

import com.hy.framelibrary.net.listener.OnBaseResultListener;
import com.hy.framelibrary.net.listener.OnDownProgressListener;
import com.hy.framelibrary.net.listener.OnBaseUploadProgressListener;

import java.io.File;
import java.util.List;
import java.util.Map;


public abstract class INetHelper {

    public abstract void init(Application application, Map<String, String> commonHeaders, Map<String, String> commonParams, int retryCount, HttpClient httpClient);

    public abstract <T> void getRequestTest(Object tag, String url, OnBaseResultListener<T> onResultListener);

    public abstract <T> void getRequest(Object tag, String url, OnBaseResultListener<T> onResultListener);

    public abstract <T> void getRequest(Object tag, String url, Map<String, String> param, OnBaseResultListener<T> onResultListener);

    public abstract <T> void getRequest(Object tag, String url, Map<String, String> param, Map<String, String> headers, OnBaseResultListener<T> onResultListener);

    public abstract void getFile(Object tag, String url, OnDownProgressListener progressListener);

    public abstract void getFile(Object tag, String url, Map<String, String> param, OnDownProgressListener progressListener);

    public abstract void getFile(Object tag, String url, String saveName, Map<String, String> param, Map<String, String> headers, OnDownProgressListener progressListener);

    public abstract <T> void postRequest(Object tag, String url, OnBaseResultListener<T> onResultListener);

    public abstract <T> void postRequest(Object tag, String url, Map<String, String> param, OnBaseResultListener<T> onResultListener);

    public abstract <T> void postRequest(Object tag, String url, Map<String, String> param, Map<String, String> headers, OnBaseResultListener<T> onResultListener);

    public abstract <T> void postFile(Object tag, String url, String fileKey, List<File> files, OnBaseUploadProgressListener<T> onProgressListener);

    public abstract <T> void postFile(Object tag, String url, Map<String, String> param, String fileKey, List<File> files, OnBaseUploadProgressListener<T> onProgressListener);

    public abstract <T> void postFile(Object tag, String url, Map<String, File> fileMap, OnBaseUploadProgressListener<T> onProgressListener);

    public abstract <T> void postFile(Object tag, String url, Map<String, String> param, Map<String, File> fileMap, OnBaseUploadProgressListener<T> onProgressListener);

    public abstract <T> void postFile(Object tag, String url, Map<String, String> param, String fileKey, List<File> files, Map<String, String> headers, OnBaseUploadProgressListener<T> onProgressListener);

    public abstract <T> void postFile(Object tag, String url, Map<String, String> param, Map<String, File> fileMap, Map<String, String> headers, OnBaseUploadProgressListener<T> onProgressListener);

    public abstract void onCancelRequest(Object tag);
}
