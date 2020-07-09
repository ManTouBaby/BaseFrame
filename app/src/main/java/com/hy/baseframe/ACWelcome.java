package com.hy.baseframe;


import com.hy.framelibrary.page.activity.BaseACWelcome;

public class ACWelcome extends BaseACWelcome {

    @Override
    protected void initWelcomeView() {
        setWelcomeBG(R.mipmap.icon_me_bg);
    }

    @Override
    protected Permission[] getPermissions() {
        return new Permission[]{Permission.CAMERA, Permission.ACCESS_FINE_LOCATION, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE};
    }

    @Override
    protected Class<?> getNextPageClass() {
        return ACLogin.class;
    }
}
