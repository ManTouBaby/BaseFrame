package com.hy.baseframe;

import android.view.LayoutInflater;
import android.view.View;

import com.hy.framelibrary.page.fragment.BaseCoordinatorFragment;

public class FRMe extends BaseCoordinatorFragment {

    @Override
    protected void initContentView(View contentLayout) {
        contentLayout.findViewById(R.id.tv_one).setOnClickListener(v -> {
            goPage(ACDetails.class);
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_me;
    }
}
