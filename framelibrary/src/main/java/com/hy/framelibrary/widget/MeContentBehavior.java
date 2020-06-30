package com.hy.framelibrary.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author:MtBaby
 * @date:2020/06/30 21:55
 * @desc:
 */
public class MeContentBehavior extends CoordinatorLayout.Behavior<RelativeLayout> {
    public MeContentBehavior() {
    }

    public MeContentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RelativeLayout child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RelativeLayout child, View dependency) {
        System.out.println("依赖控件高度变化:---"+dependency.getY());
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
