package com.hy.framelibrary.page.fragment;

import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;

import com.hy.framelibrary.R;

public abstract class BaseCoordinatorFragment extends BaseFragment {

    @Override
    protected int getLayout() {
        return R.layout.frame_base_coordinator_fragment;
    }

    @Override
    protected void initView(View view) {
        AppBarLayout appBarLayout = view.findViewById(R.id.app_bar);
//        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getActivity().setActionBar(toolbar);
        }
//        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
//            if (Math.abs(verticalOffset)<appBarLayout.getTotalScrollRange()){
//                //展开时
//                //去掉默认的标题
////                getActivity().getActionBar().setDisplayShowTitleEnabled(false);
//                //隐藏toolbar
//                toolbar.setVisibility(View.GONE);
//            }else if (Math.abs(verticalOffset)>=appBarLayout.getTotalScrollRange()){
//                //折叠时
//                //将标题设置为显示
////                getActivity().getActionBar().setDisplayShowTitleEnabled(true);
//                //显示toolbar
//                toolbar.setVisibility(View.VISIBLE);
//            }
//        });

        NestedScrollView nestedScrollView = view.findViewById(R.id.nsv_content_container);
        View contentLayout =   LayoutInflater.from(getContext()).inflate(getContentLayout(),null);
        if (contentLayout != null) {
            nestedScrollView.addView(contentLayout);
            initContentView(contentLayout);
        }


    }

    protected abstract int getContentLayout();

    protected abstract void initContentView(View contentLayout);


}
