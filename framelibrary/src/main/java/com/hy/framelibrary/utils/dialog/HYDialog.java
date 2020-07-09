package com.hy.framelibrary.utils.dialog;


import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.hy.framelibrary.utils.HYLog;
import com.zlylib.upperdialog.ListDialog;
import com.zlylib.upperdialog.TipDialog;

import java.util.List;

public class HYDialog {
    public static void showApkProgressDialog(Activity activity, String url) {
        DownloadApkDialog.with(activity, true, url).setAutoInstall(true);
    }

    public static void showMessageDialog(Context context, String title, String message, OnDialogListener onDialogListener) {
        TipDialog.with(context)
                .title(title)
                .message(message)
                .onYes(data -> {
                    if (onDialogListener != null) onDialogListener.onSubmit();
                })
                .onNo(data -> {
                    if (onDialogListener != null) onDialogListener.onCancel();
                }).show();
    }


    public static void showListDialog(Context context, String title, List<String> strings, int currSelectPos) {
        ListDialog.with(context)
                .cancelable(true)
                // .noTextColor(R.color.colorAccent)
                .noYseBtn() //不显示确定按钮 三个相互冲突，请设置一个
                //.noNoBtn()  //不显示取消按钮
                //.noBtn() // 不显示按钮
                .title(title)
                .datas(strings)
                .currSelectPos(currSelectPos)
                .listener((data, pos) -> {
                    HYLog.i("selectStr--" + data);
                })
                .show();
    }

    public static void showMultipleChoice() {

    }

    public static void showEditDialog() {

    }

}
