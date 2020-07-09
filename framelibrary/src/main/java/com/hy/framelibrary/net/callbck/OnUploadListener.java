package com.hy.framelibrary.net.callbck;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.hy.framelibrary.net.listener.OnBaseUploadProgressListener;
import com.hy.framelibrary.net.utils.ClassTypeUtil;
import com.lzy.okgo.model.Progress;

import java.io.IOException;
import java.lang.reflect.Type;

public abstract class OnUploadListener<T> implements OnBaseUploadProgressListener<T> {
    private TypeAdapter mTypeAdapter;

    public OnUploadListener() {
        Type type = this.getClass().getGenericSuperclass();
        Gson gson = new Gson();
        mTypeAdapter = gson.getAdapter(TypeToken.get(ClassTypeUtil.getRawType(type)));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onProgress(Progress progress) {

    }

    @Override
    public void convertData(String json) {
        T data;
        try {
            data = (T) mTypeAdapter.fromJson(json);
            onComplete(data);
        } catch (IOException e) {
            e.printStackTrace();
            onError(e);
        }
    }



    @Override
    public void onError(Throwable e) {

    }
}
