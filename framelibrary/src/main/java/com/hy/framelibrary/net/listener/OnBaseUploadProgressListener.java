package com.hy.framelibrary.net.listener;

import com.lzy.okgo.model.Progress;

import java.io.File;

public interface OnBaseUploadProgressListener<T> {
    void onStart();

    void onProgress(Progress progress);

    void convertData(String json);

    void onComplete(T data);

    void onError(Throwable e);
}
