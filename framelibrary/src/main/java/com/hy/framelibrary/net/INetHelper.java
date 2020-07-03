package com.hy.framelibrary.net;

import com.hy.framelibrary.base.OnResultListener;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface INetHelper {
    <T> void getRequest(Class<T> tClass,String url, OnResultListener<T> onResultListener);

    <T> void getRequest(Class<T> tClass,String url, Map<String, String> param, OnResultListener<T> onResultListener);

    <T> void getRequest(Class<T> tClass,String url, Map<String, String> param, Map<String, String> headers, OnResultListener<T> onResultListener);

   void getFile(String url, OnResultListener<File> onResultListener);

    void getFile(String url, Map<String, String> param, OnResultListener<File> onResultListener);

    void getFile(String url, Map<String, String> param, Map<String, String> headers, OnResultListener<File> onResultListener);

    <T> void postRequest(Class<T> tClass,String url, OnResultListener<T> onResultListener);

    <T> void postRequest(Class<T> tClass,String url, Map<String, String> param, OnResultListener<T> onResultListener);

    <T> void postRequest(Class<T> tClass,String url, Map<String, String> param, Map<String, String> headers, OnResultListener<T> onResultListener);

    <T> void postFile(Class<T> tClass,String url, String fileKey, List<File> files, OnResultListener<T> onResultListener);

    <T> void postFile(Class<T> tClass,String url, Map<String, String> param, String fileKey, List<File> files, OnResultListener<T> onResultListener);

    <T> void postFile(Class<T> tClass,String url, Map<String, File> fileMap, OnResultListener<T> onResultListener);

    <T> void postFile(Class<T> tClass,String url, Map<String, String> param, Map<String, File> fileMap, OnResultListener<T> onResultListener);

    <T> void postFile(Class<T> tClass,String url, Map<String, String> param, String fileKey, List<File> files, Map<String, String> headers, OnResultListener<T> onResultListener);

    <T> void postFile(Class<T> tClass,String url, Map<String, String> param, Map<String, File> fileMap, Map<String, String> headers, OnResultListener<T> onResultListener);

    void onCancelRequest();


}
