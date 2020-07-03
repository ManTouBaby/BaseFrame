package com.hy.framelibrary.net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonConvertAdapter<T> implements IJsonConvert<T>{

    @Override
    public T jsonConvert(String json) {
       T t = new Gson().fromJson(json, new TypeToken<T>() { }.getType());
        return t;
    }
}
