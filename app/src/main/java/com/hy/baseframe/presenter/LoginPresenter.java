package com.hy.baseframe.presenter;

import android.support.v7.app.AppCompatActivity;

import com.hy.baseframe.bean.BaseMusic;
import com.hy.baseframe.bean.MusicBean;
import com.hy.baseframe.bean.QWPoliceInfo;
import com.hy.baseframe.bean.WeatherBean;
import com.hy.framelibrary.base.BasePresenter;
import com.hy.framelibrary.net.callbck.OnEntityResultListener;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenter extends BasePresenter {
    String url = "http://112.94.13.13:9000/police/api/getPoliceInfo";
    String weatherUrl = "http://www.weather.com.cn/data/sk/101010100.html";
    String musice = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.5.0&method=baidu.ting.search.merge&format=json&query=%E4%B8%83%E9%87%8C%E9%A6%99&page_no=1&page_size=50&type=-1&data_source=0&use_cluster=1";

    public LoginPresenter(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    public void login(OnEntityResultListener<BaseMusic<MusicBean>> onResultListener) {
        mNetHelper.getRequest(this,musice, onResultListener);
    }

    public void loginPost(OnEntityResultListener<BaseMusic<MusicBean>> onResultListener) {
        mNetHelper.postRequest(this,musice, onResultListener);
    }
}
