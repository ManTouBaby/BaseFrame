package com.hy.framelibrary.page.fragment;

import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.hy.framelibrary.R;
import com.hy.framelibrary.adapter.MeMenuAdapter;
import com.hy.framelibrary.bean.MeMenuData;
import com.hy.framelibrary.bean.MeMenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseCoordinatorFragment extends BaseFragment implements MeMenuAdapter.OnItemClickListener {
    private ImageView mHeaderBG;

    @Override
    protected int getLayout() {
        return R.layout.frame_base_coordinator_fragment;
    }

    @Override
    protected void initView(View view) {
        mHeaderBG = view.findViewById(R.id.frame_me_header_bg);
        initMenuDates(view);
        initMeView();
    }


    public void setHeaderBG(@DrawableRes int resId) {
        this.mHeaderBG.setBackgroundResource(resId);
    }

    private void initMenuDates(View view) {
        RecyclerView menuRecyclerView = view.findViewById(R.id.me_menu_container);
        Map<String, List<MeMenuItem>> menuDates = getMenuDates();
        List<MeMenuData> meMenuData = new ArrayList<>();
        for (Map.Entry<String, List<MeMenuItem>> listEntry : menuDates.entrySet()) {
            MeMenuData menuData = new MeMenuData(MeMenuData.TYPE_TITLE, -1, listEntry.getKey(), -1);
            meMenuData.add(menuData);
            for (MeMenuItem menuItem : listEntry.getValue()) {
                MeMenuData itemData = new MeMenuData(MeMenuData.TYPE_ITEM, menuItem.menuTag, menuItem.menuTitle, menuItem.menuIcon);
                meMenuData.add(itemData);
            }
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        menuRecyclerView.setHasFixedSize(true);
        menuRecyclerView.setLayoutManager(gridLayoutManager);
        MeMenuAdapter mMeMenuAdapter = new MeMenuAdapter(meMenuData);
        menuRecyclerView.setAdapter(mMeMenuAdapter);
        mMeMenuAdapter.setOnItemClickListener(this);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemType = meMenuData.get(position).itemType;
                if (itemType == MeMenuData.TYPE_ITEM) {
                    return 1;
                } else {
                    return 5;
                }
            }
        });
    }

    protected abstract void initMeView();

    protected abstract Map<String, List<MeMenuItem>> getMenuDates();

    protected abstract void onMenuItemClick(MeMenuItem meMenuItem);

    @Override
    public void onItemClick(MeMenuData meMenuData) {
        MeMenuItem meMenuItem = new MeMenuItem(meMenuData.menuTag, meMenuData.menuTitle, meMenuData.menuIcon);
        onMenuItemClick(meMenuItem);
    }

}
