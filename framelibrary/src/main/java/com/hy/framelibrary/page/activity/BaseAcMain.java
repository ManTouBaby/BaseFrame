package com.hy.framelibrary.page.activity;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hy.framelibrary.bean.FragmentHolder;
import com.hy.framelibrary.R;
import com.hy.framelibrary.page.fragment.BaseCoordinatorFragment;
import com.hy.framelibrary.page.fragment.BaseFragment;
import com.hy.framelibrary.utils.HYLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.sugarkawhi.bottomnavigationbar.BottomNavigationBar;
import me.sugarkawhi.bottomnavigationbar.BottomNavigationEntity;

public abstract class BaseAcMain extends BaseAC {
    private SparseArray<BaseFragment> mBaseFragments = new SparseArray<>();
    private List<FragmentHolder> mFragmentHolders;
    private FragmentManager mFragmentManager;
    private int mSelectIndex = 0;
    private List<BottomNavigationEntity> mEntities;
    private int mStatusBarColor;


    @Override
    protected int getLayout() {
        return R.layout.frame_base_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        HYLog.d("initView");
        setTranslucentStatus();
        mFragmentHolders = getFragmentHolders();
        mFragmentManager = getSupportFragmentManager();
        initMainView();
        initBottomDates();
        if (savedInstanceState != null) {
            mSelectIndex = savedInstanceState.getInt("mSelectIndex");
//            HYLog.e("保存的位置:" + mSelectIndex);
            for (int i = 0; i < mFragmentHolders.size(); i++) {
                FragmentHolder fragmentHolder = mFragmentHolders.get(i);
                BaseFragment baseFragment = (BaseFragment) mFragmentManager.findFragmentByTag(fragmentHolder.fragmentTagName);
//                if (baseFragment != null) HYLog.e("保存的状态:" + baseFragment.getClass());
                if (!(baseFragment instanceof BaseCoordinatorFragment)) {
                    baseFragment.setAddStatusBar(true, mStatusBarColor);
                }
                mBaseFragments.put(i, baseFragment);
            }
        }
//        HYLog.e("准备初始化页面");
        selectItemView(mSelectIndex);
        BottomNavigationBar mNavigationBar = findViewById(R.id.bottomNavigationBar);
        //点击item
        mNavigationBar.setBnbItemSelectListener(position -> {
            mSelectIndex = position;
//        HYLog.e("onBnbItemSelect");
            selectItemView(mSelectIndex);
        });
        mNavigationBar.setEntities(mEntities);
        mNavigationBar.setCurrentPosition(mSelectIndex);
    }

    protected void setStatusBarColor(@ColorInt int color) {
        mStatusBarColor = color;
    }

    private void initBottomDates() {
        mEntities = new ArrayList<>();
        if (mFragmentHolders != null) for (int i = 0; i < mFragmentHolders.size(); i++) {
            FragmentHolder fragmentHolder = mFragmentHolders.get(i);
            mEntities.add(new BottomNavigationEntity(
                    fragmentHolder.fragmentTagName,
                    fragmentHolder.fragmentDefaultTag,
                    fragmentHolder.fragmentSelectTag, i));
        }
    }

    private void selectItemView(int selectIndex) {
        FragmentHolder fragmentHolder = mFragmentHolders.get(selectIndex);
        addFragment(selectIndex, fragmentHolder);
    }

    protected abstract List<FragmentHolder> getFragmentHolders();

    private void addFragment(int selectIndex, FragmentHolder fragmentHolder) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        try {
            BaseFragment baseFragment = mBaseFragments.get(selectIndex);
            hideAllFragment(transaction);
            if (baseFragment == null) {
                baseFragment = fragmentHolder.baseFragment.newInstance();
                mBaseFragments.put(selectIndex, baseFragment);
                if (!(baseFragment instanceof BaseCoordinatorFragment)) {
                    baseFragment.setAddStatusBar(true, mStatusBarColor);
                }
                transaction.add(R.id.fl_fragment_container, baseFragment, fragmentHolder.fragmentTagName);
            } else {
                transaction.show(baseFragment);
            }
            transaction.commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("mSelectIndex", mSelectIndex);
        super.onSaveInstanceState(outState);
    }


    private void hideAllFragment(FragmentTransaction transaction) {
        for (int i = 0; i < mBaseFragments.size(); i++) {
            BaseFragment baseFragment = mBaseFragments.valueAt(i);
            transaction.hide(baseFragment);
        }
    }

    protected abstract void initMainView();


}
