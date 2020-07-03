package com.hy.framelibrary.bean;

public class MeMenuItem {
    public String menuTypeName;
    public int menuTag;
    public String menuTitle;
    public int menuIcon;

    public MeMenuItem(int menuTag, String menuTitle, int menuIcon) {
        this.menuTag = menuTag;
        this.menuIcon = menuIcon;
        this.menuTitle = menuTitle;
    }
}
