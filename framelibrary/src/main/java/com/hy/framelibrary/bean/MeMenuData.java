package com.hy.framelibrary.bean;

public class MeMenuData {
    public static final int TYPE_TITLE=0x100;
    public static final int TYPE_ITEM=0x200;

    public int itemType;
    public int menuTag;
    public String menuTitle;
    public int menuIcon;

    public MeMenuData(int itemType, int menuTag, String menuTitle, int menuIcon) {
        this.itemType = itemType;
        this.menuTag = menuTag;
        this.menuTitle = menuTitle;
        this.menuIcon = menuIcon;
    }
}
