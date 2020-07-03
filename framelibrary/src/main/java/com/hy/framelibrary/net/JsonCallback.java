package com.hy.framelibrary.net;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Type mType;
    private Class<T> clazz;

    public JsonCallback(Class<T> clazz) {
        this.clazz=clazz;
    }


    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        T data = null;
        Gson gson = new Gson();
        String str = response.body().string();
        if (mType != null) {
            data = gson.fromJson(str, mType);
        }
        if (clazz != null) {
            data = gson.fromJson(str, clazz);
            //可以将错误信息在onError中获取到
            //https://github.com/jeasonlzy/okhttp-OkGo/wiki/Callback#callback%E4%BB%8B%E7%BB%8D
        }
        return data;
    }
}
