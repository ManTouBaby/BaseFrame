package com.hy.framelibrary.bean;

import com.hy.framelibrary.page.fragment.BaseFragment;

public class FragmentHolder {
    public Class<? extends BaseFragment> baseFragment;
    public String fragmentTagName;
    public int fragmentDefaultTag;
    public int fragmentSelectTag;

    public FragmentHolder(Class<? extends BaseFragment> baseFragment, String fragmentTagName, int fragmentDefaultTag, int fragmentSelectTag) {
        this.baseFragment = baseFragment;
        this.fragmentTagName = fragmentTagName;
        this.fragmentDefaultTag = fragmentDefaultTag;
        this.fragmentSelectTag = fragmentSelectTag;
    }
}
