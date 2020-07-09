package com.hy.framelibrary.net.exception;

import com.google.gson.Gson;
import com.hy.framelibrary.base.BaseResult;

/**
 * Created by ccb on 2017/12/7.
 */

public class MyException extends IllegalStateException {

    private BaseResult errorBean;

    public MyException(String s) {
        super(s);
        errorBean = new Gson().fromJson(s, BaseResult.class);
    }

    public BaseResult getErrorBean() {
        return errorBean;
    }
}
