package com.hy.baseframe;

import android.os.Bundle;

import com.hy.framelibrary.base.BasePresenter;
import com.hy.framelibrary.base.OnResultListener;
import com.hy.framelibrary.page.activity.BaseAcLogin;
import com.hy.framelibrary.utils.HYToast;

import java.util.ArrayList;
import java.util.List;

public class ACLogin extends BaseAcLogin {
    private LoginPresenter mLoginPresenter;

    @Override
    protected void initLoginView() {
        setLoginContainerBG(R.mipmap.icon_me_bg);
    }

    @Override
    public List<BasePresenter> initPresenters() {
        List<BasePresenter> basePresenters = new ArrayList<>();
        basePresenters.add(mLoginPresenter = new LoginPresenter(this));
        return basePresenters;
    }

    @Override
    protected void doLogin(String userName, String userPW) {
        mLoginPresenter.login(new OnResultListener<String>() {
            @Override
            public void onSuccess(String data) {


                HYToast.showShort("登录成功");
                loginSuccess();
            }

            @Override
            public void onFail(String message) {
                HYToast.showShort("登录失败");
            }

        });
//        loginSuccess();
//        finish();
    }

    @Override
    protected Class<?> getNextPageClass() {
        return ACMain.class;
    }


}
