package com.hy.framelibrary.bean;

import java.util.List;

public class MeMenuHolder {
    public String menuTypeName;
    public List<MeMenuItem> menuList;

    public MeMenuHolder(String menuTypeName, List<MeMenuItem> menuList) {
        this.menuTypeName = menuTypeName;
        this.menuList = menuList;
    }
}
