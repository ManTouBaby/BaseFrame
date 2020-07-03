package com.hy.baseframe;

import com.hy.framelibrary.base.BasePresenter;
import com.hy.framelibrary.page.activity.BaseAcMain;
import com.hy.framelibrary.bean.FragmentHolder;

import java.util.ArrayList;
import java.util.List;

public class ACMain extends BaseAcMain {

    @Override
    protected void initMainView() {
        setStatusBarColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected List<FragmentHolder> getFragmentHolders() {
        List<FragmentHolder> fragmentHolders = new ArrayList<>();
        fragmentHolders.add(new FragmentHolder(FRHome.class, "主页", R.mipmap.icon_home_default, R.mipmap.icon_home_select));
        fragmentHolders.add(new FragmentHolder(FrMessage.class, "消息", R.mipmap.icon_home_default, R.mipmap.icon_home_select));
        fragmentHolders.add(new FragmentHolder(FrContact.class, "通讯录", R.mipmap.icon_home_default, R.mipmap.icon_home_select));
        fragmentHolders.add(new FragmentHolder(FRMe.class, "我的", R.mipmap.icon_home_default, R.mipmap.icon_home_select));
        return fragmentHolders;
    }


}
