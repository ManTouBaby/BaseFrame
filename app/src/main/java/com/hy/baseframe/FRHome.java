package com.hy.baseframe;

import android.view.View;
import android.widget.TextView;

import com.hy.baseframe.presenter.HomePresenter;
import com.hy.framelibrary.base.BasePresenter;
import com.hy.framelibrary.net.listener.OnDownProgressListener;
import com.hy.framelibrary.page.fragment.BaseFragmentPresenter;
import com.hy.framelibrary.utils.HYLog;
import com.hy.framelibrary.utils.dialog.DownloadApkDialog;
import com.hy.framelibrary.utils.dialog.HYDialog;
import com.hy.framelibrary.utils.dialog.OnDialogListener;
import com.hy.framelibrary.utils.dialog.OnSubmitCallBack;
import com.lzy.okgo.model.Progress;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FRHome extends BaseFragmentPresenter {
    String apk = "http://s.shouji.qihucdn.com/170417/87d93d33e2ec5ae15b93fdaa642ffeb0/com.hiapk.marketpho_16793302.apk?en=curpage%3D%26exp%3D1594719292%26from%3Dopenbox_detail_index%26m2%3D%26ts%3D1594114492%26tok%3D90a3bbf96afdbe8f90227e45a67d8999%26v%3D%26f%3Dz.apk";

    private HomePresenter mHomePresenter;
    private TextView mDownButton;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initPView(View view) {
        mDownButton = view.findViewById(R.id.tv_down_file);
        mDownButton.setOnClickListener(v -> {
//            DownloadApkDialog.with(getActivity(), true, apk).setAutoInstall(true);
//            HYDialog.showMessageDialog(getContext(), "提示", "消息弹窗测测试", null);
//            HYDialog.showMessageDialog(getContext(), "提示", "消息弹窗测测试", null);
//            HYDialog.showMessageDialog(getContext(), "提示", "消息弹窗测测试", null);
            List<String> listDates = new ArrayList<>();
            listDates.add("功能一");
            listDates.add("功能二");
            listDates.add("功能三");
            HYDialog.showListDialog(getContext(),"功能",listDates,0);
//            showLoading("加载中...");
        });
    }

    @Override
    protected List<BasePresenter> initPresenters() {
        List<HomePresenter> homePresenters = new ArrayList<>();
        homePresenters.add(mHomePresenter = new HomePresenter(this));
        return null;
    }
}
