package com.hy.framelibrary.base;

public interface OnProgressListener {
    void onStart();
    void onProgress();
    void onComplete();
    void onError();
}
