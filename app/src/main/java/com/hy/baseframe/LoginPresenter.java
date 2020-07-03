package com.hy.baseframe;

import android.support.v7.app.AppCompatActivity;

import com.hy.framelibrary.base.BasePresenter;
import com.hy.framelibrary.base.OnResultListener;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenter extends BasePresenter {
    String url = "http://112.94.13.13:9000/police/api/getPoliceInfo";
    String weatherUrl = "http://www.weather.com.cn/data/sk/101010100.html";

    public LoginPresenter(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    public void login(OnResultListener<String> onResultListener) {
        Map<String, String> params = new HashMap<>();
        params.put("policeNum", "fj0001");
        params.put("policeId", "fj0001");
        mNetHelper.getRequest(String.class, weatherUrl, onResultListener);
    }
}
