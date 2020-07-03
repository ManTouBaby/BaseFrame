package com.hy.baseframe;

import com.hy.framelibrary.bean.MeMenuItem;
import com.hy.framelibrary.page.fragment.BaseCoordinatorFragment;
import com.hy.framelibrary.utils.HYLog;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FRMe extends BaseCoordinatorFragment {
    @Override
    protected void initMeView() {
        setHeaderBG(R.mipmap.icon_me_bg);
    }

    @Override
    protected Map<String, List<MeMenuItem>> getMenuDates() {
        Map<String, List<MeMenuItem>> stringListMap = new LinkedHashMap<>();
        List<MeMenuItem> meMenuItems = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            MeMenuItem meMenuItem = new MeMenuItem(i, "菜单一" + i, R.mipmap.icon_kaoqin);
            meMenuItems.add(meMenuItem);
        }
        stringListMap.put("类型一", meMenuItems);

        List<MeMenuItem> meMenuItems2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            MeMenuItem meMenuItem = new MeMenuItem(i, "菜单二" + i, R.mipmap.icon_kaoqin);
            meMenuItems2.add(meMenuItem);
        }
        stringListMap.put("类型二", meMenuItems2);
        return stringListMap;
    }

    @Override
    protected void onMenuItemClick(MeMenuItem meMenuItem) {
        HYLog.d("单击的菜单:" + meMenuItem.menuTitle);
        go(ACDetails.class);
    }


}
