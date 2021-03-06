package com.hy.framelibrary.page.activity;

import android.os.Bundle;

import com.hy.framelibrary.base.BasePresenter;
import com.hy.framelibrary.base.IPresenter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseACPresenter extends BaseAC implements IPresenter {

    @Override
    protected void initView(Bundle savedInstanceState) {
        List<BasePresenter> mBasePresenters = initPresenters();
        if (mBasePresenters != null && mBasePresenters.size() > 0) {
            for (BasePresenter basePresenter : mBasePresenters) {
                getLifecycle().addObserver(basePresenter);
            }
        }
        initPView(savedInstanceState);
    }

    protected abstract void initPView(Bundle savedInstanceState);

}
