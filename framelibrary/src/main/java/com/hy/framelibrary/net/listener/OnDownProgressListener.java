package com.hy.framelibrary.net.listener;

import com.lzy.okgo.model.Progress;

import java.io.File;

public interface OnDownProgressListener {
    void onStart();

    void onProgress(Progress progress);

    void onComplete(File file);

    void onError(Throwable e);
}
