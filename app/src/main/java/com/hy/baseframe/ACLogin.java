package com.hy.baseframe;

import com.hy.framelibrary.page.activity.BaseAcLogin;

public class ACLogin extends BaseAcLogin {
    @Override
    protected void initLoginView() {
        setLoginContainerBG(R.mipmap.icon_me_bg);
    }

    @Override
    protected void doLogin(String userName, String userPW) {
        loginSuccess();
    }

    @Override
    protected Class<?> getNextPageClass() {
        return ACMain.class;
    }
}
