package com.hy.baseframe.presenter;


import android.support.v4.app.Fragment;

import com.hy.framelibrary.base.BasePresenter;
import com.hy.framelibrary.net.listener.OnDownProgressListener;

public class HomePresenter extends BasePresenter {

    public HomePresenter(Fragment fragment) {
        super(fragment);
    }

    public void downloadFile(String url,OnDownProgressListener onDownProgressListener) {
        mNetHelper.getFile(this, url, onDownProgressListener);
    }

}
