package com.hy.framelibrary.page.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.hy.framelibrary.R;
import com.hy.framelibrary.utils.ToastUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public abstract class BaseACWelcome extends BaseAC implements EasyPermissions.PermissionCallbacks {
    private final int PERMISSIONS_REQUEST_CODE = 0x900;
    private int REQUEST_PERMISSION_SETTING = 0x800;
    String[] permissions;
    private ImageView mWelcomeBG;

    @Override
    protected int getLayout() {
        return R.layout.frame_base_welcome;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setFullScreen();
        mWelcomeBG = findViewById(R.id.welcome_bg);
        Permission[] permissions = getPermissions();
        this.permissions = new String[permissions.length];
        for (int i = 0; i < permissions.length; i++) {
            Permission permission = permissions[i];
            this.permissions[i] = permission.permission;
        }
        requestPermission();
        initWelcomeView();
    }

    protected abstract void initWelcomeView();

    public void setWelcomeBG(@DrawableRes int resId) {
        this.mWelcomeBG.setBackgroundResource(resId);
    }

    protected abstract Permission[] getPermissions();

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @AfterPermissionGranted(PERMISSIONS_REQUEST_CODE)
    private void requestPermission() {
        //权限判断，第一次弹出系统的授权提示框
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //@AfterPermissionGranted 有权限直接执行 todo...
            gotoNextPage();
        } else {
            //没有权限的话，先提示，点确定后弹出系统的授权提示框
            EasyPermissions.requestPermissions(this, "使用过程需要用到相关权限", PERMISSIONS_REQUEST_CODE, permissions);
        }
    }

    private void gotoNextPage() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(BaseACWelcome.this, getNextPageClass());
                startActivity(intent);
            }
        }, 3000);

    }

    protected abstract Class<?> getNextPageClass();

    /**
     * 允许权限成功后触发
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (perms.size() == this.permissions.length) {
            gotoNextPage();
        }
    }

    /**
     * 禁止权限后触发
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //在权限弹窗中，用户勾选了'不在提示'且拒绝权限的情况触发，可以进行相关提示。
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("必须同意相关权限才能使用")
                .setNegativeButton("取消", (dialog, which) -> {
                    dialog.dismiss();
                    ToastUtil.showShort(getContext(), "程序将在3秒后退出");
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 3000);
                })
                .setPositiveButton("同意", (dialog, which) -> {
                    dialog.dismiss();
                    if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                    }else {
                        EasyPermissions.requestPermissions(this, "使用过程需要用到相关权限", PERMISSIONS_REQUEST_CODE, permissions);
                    }

                })
                .show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_PERMISSION_SETTING){
            requestPermission();
        }
    }

    public enum Permission {
        READ_CALENDAR(Manifest.permission.READ_CALENDAR),
        WRITE_CALENDAR(Manifest.permission.WRITE_CALENDAR),
        CAMERA(Manifest.permission.CAMERA),
        READ_CONTACTS(Manifest.permission.READ_CONTACTS),
        WRITE_CONTACTS(Manifest.permission.WRITE_CONTACTS),
        GET_ACCOUNTS(Manifest.permission.GET_ACCOUNTS),
        ACCESS_FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION),
        ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION),
        RECORD_AUDIO(Manifest.permission.RECORD_AUDIO),
        READ_PHONE_STATE(Manifest.permission.READ_PHONE_STATE),
        CALL_PHONE(Manifest.permission.CALL_PHONE),
        READ_CALL_LOG(Manifest.permission.READ_CALL_LOG),
        WRITE_CALL_LOG(Manifest.permission.WRITE_CALL_LOG),
        ADD_VOICEMAIL(Manifest.permission.ADD_VOICEMAIL),
        USE_SIP(Manifest.permission.USE_SIP),
        PROCESS_OUTGOING_CALLS(Manifest.permission.PROCESS_OUTGOING_CALLS),
        BODY_SENSORS(Manifest.permission.BODY_SENSORS),
        SEND_SMS(Manifest.permission.SEND_SMS),
        RECEIVE_SMS(Manifest.permission.RECEIVE_SMS),
        READ_SMS(Manifest.permission.READ_SMS),
        RECEIVE_WAP_PUSH(Manifest.permission.RECEIVE_WAP_PUSH),
        RECEIVE_MMS(Manifest.permission.RECEIVE_MMS),
        READ_EXTERNAL_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE),
        WRITE_EXTERNAL_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        private String permission;

        Permission(String permission) {
            this.permission = permission;
        }
    }

}
