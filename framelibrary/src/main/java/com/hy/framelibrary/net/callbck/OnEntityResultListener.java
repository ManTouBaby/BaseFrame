package com.hy.framelibrary.net.callbck;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.hy.framelibrary.net.listener.OnBaseResultListener;
import com.hy.framelibrary.net.utils.ClassTypeUtil;

import java.io.IOException;
import java.lang.reflect.Type;

public abstract class OnEntityResultListener<T> implements OnBaseResultListener<T> {
    private TypeAdapter mTypeAdapter;

    protected OnEntityResultListener() {
        Type type = this.getClass().getGenericSuperclass();
        Gson gson = new Gson();
        mTypeAdapter = gson.getAdapter(TypeToken.get(ClassTypeUtil.getRawType(type)));
    }

    public void convertData(String json) {
        T data;
        try {
            data = (T) mTypeAdapter.fromJson(json);
            onSuccess(data);
        } catch (IOException e) {
            e.printStackTrace();
            onError(e);
        }
    }


}
