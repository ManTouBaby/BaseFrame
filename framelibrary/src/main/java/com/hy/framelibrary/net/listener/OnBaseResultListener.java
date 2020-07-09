package com.hy.framelibrary.net.listener;

public interface OnBaseResultListener<T> {
    void convertData(String json);

    void onSuccess(T json);

    void onError(Exception e);
}
