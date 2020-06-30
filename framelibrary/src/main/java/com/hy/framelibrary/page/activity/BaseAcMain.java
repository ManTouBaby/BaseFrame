package com.hy.framelibrary.page.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hy.framelibrary.bean.FragmentHolder;
import com.hy.framelibrary.R;
import com.hy.framelibrary.page.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import me.sugarkawhi.bottomnavigationbar.BottomNavigationBar;
import me.sugarkawhi.bottomnavigationbar.BottomNavigationEntity;

public abstract class BaseAcMain extends BaseAC {
    private BottomNavigationBar mNavigationBar;

    private List<BaseFragment> mBaseFragments = new ArrayList<>();
    private List<FragmentHolder> mFragmentHolders;
    private FragmentManager mFragmentManager;
    private int mSelectIndex = 0;
    private List<BottomNavigationEntity> mEntities;

    @Override
    protected int getLayout() {
        return R.layout.frame_base_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mNavigationBar = findViewById(R.id.bottomNavigationBar);
        //点击item
        mNavigationBar.setBnbItemSelectListener(position -> {
            mSelectIndex = position;
            selectItemView();
        });
        mFragmentHolders = getFragmentHolders();
        mFragmentManager = getSupportFragmentManager();
        initMainView();
        initBottomDates();
        mNavigationBar.setEntities(mEntities);
        mNavigationBar.setCurrentPosition(mSelectIndex);
        selectItemView();
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

    private void selectItemView() {
        FragmentHolder fragmentHolder = mFragmentHolders.get(mSelectIndex);
        addFragment(fragmentHolder);
    }

    protected abstract List<FragmentHolder> getFragmentHolders();

    private void addFragment(FragmentHolder fragmentHolder) {
        try {
            BaseFragment baseFragment = (BaseFragment) mFragmentManager.findFragmentByTag(fragmentHolder.fragmentTagName);
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            hideAllFragment(transaction);
            if (baseFragment == null) {
                baseFragment = fragmentHolder.baseFragment.newInstance();
                mBaseFragments.add(baseFragment);
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
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        for (BaseFragment baseFragment : mBaseFragments) {
            transaction.remove(baseFragment);
        }
        transaction.commitAllowingStateLoss();
        super.onSaveInstanceState(outState);
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        for (BaseFragment baseFragment : mBaseFragments) {
            transaction.hide(baseFragment);
        }
    }

    protected abstract void initMainView();
}
