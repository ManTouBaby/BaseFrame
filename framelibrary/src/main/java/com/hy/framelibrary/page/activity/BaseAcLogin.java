package com.hy.framelibrary.page.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hy.framelibrary.R;
import com.hy.framelibrary.utils.HYToast;

public abstract class BaseAcLogin extends BasePAC {
    private RelativeLayout mLoginContainer;
    private EditText loginUser;
    private EditText loginPW;
    private CheckBox checkBox;
    private TextView mAppVerison;

    private SharedPreferences mLoginRemember;

    @Override
    protected int getLayout() {
        return R.layout.frame_base_login;
    }

    @Override
    protected void initPView(Bundle savedInstanceState) {
        setFullScreen();
        mLoginContainer = findViewById(R.id.frame_login_bg);
        loginUser = findViewById(R.id.mi_login_user);
        loginPW = findViewById(R.id.mi_login_ps);
        mAppVerison = findViewById(R.id.frame_app_version);
        checkBox = findViewById(R.id.login_remember);

        mLoginRemember = getSharedPreferences("LoginRemember", Context.MODE_PRIVATE);
        boolean isRemember = mLoginRemember.getBoolean("isRemember", false);
        if (isRemember) {
            String loginUserLabel = mLoginRemember.getString("loginUser", null);
            String loginPSLabel = mLoginRemember.getString("loginPS", null);
            loginUser.setText(loginUserLabel);
            loginPW.setText(loginPSLabel);
        }
        checkBox.setChecked(isRemember);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> mLoginRemember.edit().putBoolean("isRemember", isChecked).apply());
        Button button = findViewById(R.id.mi_login_submit);
        button.setOnClickListener(v -> {
            String loginName = loginUser.getText().toString();
            String loginPW = this.loginPW.getText().toString();
            if (TextUtils.isEmpty(loginName)) {
                HYToast.showShort("登录账号不能为空");
                return;
            }
            if (TextUtils.isEmpty(loginPW)) {
                HYToast.showShort("登录密码不能为空");
                return;
            }
            doLogin(loginName, loginPW);
        });

        initLoginView();
    }

    protected void setVersion(String version) {
        mAppVerison.setText(version);
    }

    protected abstract void initLoginView();

    protected void setLoginContainerBG(@DrawableRes int resId) {
        mLoginContainer.setBackgroundResource(resId);
    }

    protected void loginSuccess() {
        if (checkBox.isChecked()) {
            mLoginRemember.edit().putString("loginUser", loginUser.getText().toString()).apply();
            mLoginRemember.edit().putString("loginPS", loginPW.getText().toString()).apply();
        }
        Intent intent = new Intent(this, getNextPageClass());
        startActivity(intent);
    }

    protected void loginFail(String msg) {
        HYToast.showShort(msg);
    }

    protected abstract void doLogin(String userName, String userPW);

    protected abstract Class<?> getNextPageClass();


}
