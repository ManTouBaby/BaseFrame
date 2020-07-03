package com.hy.framelibrary.base;

public interface OnResultListener<T> {
    void onSuccess(T data);

    void onFail(String message);
}
