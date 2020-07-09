package com.hy.framelibrary.utils.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hy.framelibrary.HYFrameHelper;
import com.hy.framelibrary.R;
import com.hy.framelibrary.net.INetHelper;
import com.hy.framelibrary.net.listener.OnDownProgressListener;
import com.hy.framelibrary.utils.ApkInstall;
import com.hy.framelibrary.utils.HYLog;
import com.lzy.okgo.model.Progress;
import com.zlylib.upperdialog.Upper;
import com.zlylib.upperdialog.manager.Layer;
import com.zlylib.upperdialog.utils.ResUtils;
import com.zlylib.upperdialog.utils.Utils;

import java.io.File;
import java.math.BigDecimal;

/**
 * 版本更新弹窗
 *
 * @author zhangliyang
 * @date 2018/8/6-上午9:17
 */
public class DownloadApkDialog {

    private final Activity mActivity;
    private Layer upperLayer = null;
    private final boolean isForce;
    private boolean isAutoInstall = true;

    private ProgressBar progressBar;
    private TextView tvProgress;
    private TextView tvApkSize;
    private TextView tvState;
    private File mApk;

    public static DownloadApkDialog with(Activity activity, boolean isForce, String url) {
        Utils.init(activity);
        return new DownloadApkDialog(activity, isForce, url);
    }

    private DownloadApkDialog(Activity activity, boolean isForce, String url) {
        Utils.init(activity);
        this.mActivity = activity;
        this.isForce = isForce;
        showDialog();
        startDownload(url);
    }

    public DownloadApkDialog setAutoInstall(boolean autoInstall) {
        isAutoInstall = autoInstall;
        return this;
    }

    private void startDownload(String url) {
        INetHelper netHelper = HYFrameHelper.getInstance().getNetHelper();
        netHelper.getFile(this, url, new OnDownProgressListener() {
            @Override
            public void onStart() {
                preDownload();
                HYLog.i("开始下载:");
            }

            @Override
            public void onProgress(Progress progress) {
                int progressSize = (int) (((double)progress.currentSize / (double) progress.totalSize) * 100);
                HYLog.i("正在下载:" + progress.currentSize + "/" + progress.totalSize + "---" + progressSize);
                tvApkSize.setText(formatSize(progress.totalSize));
                setProgress(progressSize);
            }

            @Override
            public void onComplete(File file) {
                HYLog.i("下载成功:" + file.getPath());
                mApk = file;
                if (isAutoInstall && tvState != null) {
                    tvState.performClick();
                }
            }

            @Override
            public void onError(Throwable e) {
                HYLog.i("下载失败:" + e.getCause());
            }
        });
    }

    private void showDialog() {
        upperLayer = Upper.dialog(mActivity)
                .contentView(R.layout.basic_ui_dialog_download)
                .gravity(Gravity.CENTER)
                .backgroundDimDefault()
                .cancelableOnTouchOutside(false)
                .cancelableOnClickKeyBack(false)
                .bindData(layer -> {
                    progressBar = layer.getView(R.id.basic_ui_pb_dialog_download);
                    tvProgress = layer.getView(R.id.basic_ui_tv_dialog_download_progress);
                    tvApkSize = layer.getView(R.id.basic_ui_tv_dialog_download_apk_size);
                    tvState = layer.getView(R.id.basic_ui_tv_dialog_download_state);
                })
                .onClick((layer, v) -> {
                    if (mApk == null) {
                        return;
                    }
                    if (!isForce) {
                        dismiss();
                    }
                    ApkInstall.installApk(mActivity, mApk);
                }, R.id.basic_ui_tv_dialog_download_state);
        upperLayer.show();
    }

    private void preDownload() {
        if (progressBar != null) {
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }
        if (tvApkSize != null) {
            tvApkSize.setText("0B");
        }
        if (tvProgress != null) {
            tvProgress.setText("0");
        }
        if (tvState != null) {
            tvState.setText(com.zlylib.upperdialog.R.string.basic_ui_dialog_download_state_downloading);
        }
    }

    private void setProgress(int progress) {
        if (progressBar != null) {
            progressBar.setProgress(progress);
        }
        if (tvProgress != null) {
            tvProgress.setText("" + progress);
        }
        if (progress >= 100) {
            if (tvState != null) {
                tvState.setText(com.zlylib.upperdialog.R.string.basic_ui_dialog_download_state_install);
                tvState.setTextColor(ResUtils.getColor(mActivity, com.zlylib.upperdialog.R.color.text_main));
            }
        }
    }

    private void dismiss() {
        if (upperLayer != null) {
            upperLayer.dismiss();
        }
    }

    /**
     * 格式化单位
     */
    private static String formatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0KB";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
