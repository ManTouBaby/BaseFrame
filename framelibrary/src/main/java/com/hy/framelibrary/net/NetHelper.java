package com.hy.framelibrary.net;

import com.hy.framelibrary.base.OnResultListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.util.List;
import java.util.Map;

public class NetHelper implements INetHelper {
    private IJsonConvert mIJsonConvert;

    public NetHelper(IJsonConvert mIJsonConvert) {
        this.mIJsonConvert = mIJsonConvert;
    }

    @Override
    public <T> void getRequest(Class<T> tClass, String url, OnResultListener<T> onResultListener) {
        getRequest(tClass, url, null, onResultListener);
    }

    @Override
    public <T> void getRequest(Class<T> tClass, String url, Map<String, String> params, OnResultListener<T> onResultListener) {
        getRequest(tClass, url, params, null, onResultListener);
    }

    @Override
    public <T> void getRequest(Class<T> tClass, String url, Map<String, String> param, Map<String, String> headers, OnResultListener<T> onResultListener) {
        GetRequest<T> request = OkGo.<T>get(url).tag(this);

        if (param != null) request.params(param);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.headers(entry.getKey(), entry.getValue());
            }
        }
        request.execute(new JsonCallback<T>(tClass) {
            @Override
            public void onSuccess(Response<T> response) {
                T body = response.body();
                onResultListener.onSuccess(body);
            }
        });
    }

    @Override
    public void getFile(String url, OnResultListener<File> onResultListener) {
        getFile(url, null, onResultListener);
    }


    @Override
    public void getFile(String url, Map<String, String> param, OnResultListener<File> onResultListener) {
        getFile(url, param, null, onResultListener);
    }

    @Override
    public void getFile(String url, Map<String, String> param, Map<String, String> headers, OnResultListener<File> onResultListener) {
        GetRequest<File> request = OkGo.<File>get(url).tag(this);
        if (param != null) request.params(param);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.headers(entry.getKey(), entry.getValue());
            }
        }
        //文件下载时，可以指定下载的文件目录和文件名
        request.execute(new FileCallback("", "") {
            @Override
            public void onSuccess(Response<File> response) {
                onResultListener.onSuccess(response.body());
            }


        });
    }

    @Override
    public <T> void postRequest(Class<T> tClass, String url, OnResultListener<T> onResultListener) {
        postRequest(tClass, url, null, onResultListener);
    }

    @Override
    public <T> void postRequest(Class<T> tClass, String url, Map<String, String> params, OnResultListener<T> onResultListener) {
        postRequest(tClass, url, params, null, onResultListener);
    }

    @Override
    public <T> void postRequest(Class<T> tClass, String url, Map<String, String> param, Map<String, String> headers, OnResultListener<T> onResultListener) {
        PostRequest<T> request = OkGo.<T>post(url).tag(this);

        if (param != null) request.params(param);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.headers(entry.getKey(), entry.getValue());
            }
        }
        request.execute(new JsonCallback<T>(tClass) {
            @Override
            public void onSuccess(Response<T> response) {
                onResultListener.onSuccess(response.body());
            }
        });
    }

    @Override
    public <T> void postFile(Class<T> tClass, String url, String fileKey, List<File> files, OnResultListener<T> onResultListener) {
        postFile(tClass, url, null, fileKey, files, onResultListener);
    }

    @Override
    public <T> void postFile(Class<T> tClass, String url, Map<String, String> param, String fileKey, List<File> files, OnResultListener<T> onResultListener) {
        postFile(tClass, url, param, fileKey, files, null, onResultListener);
    }

    @Override
    public <T> void postFile(Class<T> tClass, String url, Map<String, File> fileMap, OnResultListener<T> onResultListener) {
        postFile(tClass, url, null, fileMap, onResultListener);
    }

    @Override
    public <T> void postFile(Class<T> tClass, String url, Map<String, String> param, Map<String, File> fileMap, OnResultListener<T> onResultListener) {
        postFile(tClass, url, param, fileMap, null, onResultListener);
    }

    @Override
    public <T> void postFile(Class<T> tClass, String url, Map<String, String> param, String fileKey, List<File> files, Map<String, String> headers, OnResultListener<T> onResultListener) {
        PostRequest<String> request = OkGo.<String>post(url).tag(this);
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
//                        btnFormUpload.setText("正在上传中...");
            }

            @Override
            public void onSuccess(Response<String> response) {
//                        btnFormUpload.setText("上传完成"+response.body());
            }

            @Override
            public void onError(Response<String> response) {
//                        btnFormUpload.setText("上传出错");
            }

            @Override
            public void uploadProgress(Progress progress) {
//                System.out.println("uploadProgress: " + progress);
            }
        });
    }

    @Override
    public <T> void postFile(Class<T> tClass, String url, Map<String, String> param, Map<String, File> fileMap, Map<String, String> headers, OnResultListener<T> onResultListener) {
        PostRequest<String> request = OkGo.<String>post(url).tag(this);
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
//                        btnFormUpload.setText("正在上传中...");
            }

            @Override
            public void onSuccess(Response<String> response) {
//                        btnFormUpload.setText("上传完成"+response.body());
            }

            @Override
            public void onError(Response<String> response) {
//                        btnFormUpload.setText("上传出错");
            }

            @Override
            public void uploadProgress(Progress progress) {
                System.out.println("uploadProgress: " + progress);
            }
        });
    }


    @Override
    public void onCancelRequest() {
        OkGo.getInstance().cancelTag(this);
    }
}
