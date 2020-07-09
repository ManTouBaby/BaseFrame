package com.hy.framelibrary.page.fragment;

import android.view.View;

import com.hy.framelibrary.base.BasePresenter;

import java.util.List;

public abstract class BaseFragmentPresenter extends BaseFragment {

    @Override
    protected void initView(View view) {
        List<BasePresenter> mBasePresenters = initPresenters();
        if (mBasePresenters != null && mBasePresenters.size() > 0) {
            for (BasePresenter basePresenter : mBasePresenters) {
                getLifecycle().addObserver(basePresenter);
            }
        }
        initPView(view);
    }

    protected abstract void initPView(View view);

    protected abstract List<BasePresenter> initPresenters();
}
