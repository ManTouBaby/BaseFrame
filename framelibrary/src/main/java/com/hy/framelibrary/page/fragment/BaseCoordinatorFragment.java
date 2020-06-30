package com.hy.framelibrary.page.fragment;

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
