package com.hy.framelibrary.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * @author:MtBaby
 * @date:2020/06/30 22:20
 * @desc:
 */
public class MePortraitBehavior extends CoordinatorLayout.Behavior<ImageView> {
    /**
     * 处于中心时候原始X轴
     */
    private int mOriginalHeaderX = 0;
    /**
     * 处于中心时候原始Y轴
     */
    private int mOriginalHeaderY = 0;

    public MePortraitBehavior() {
    }

    public MePortraitBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        // 计算X轴坐标
        if (mOriginalHeaderX == 0) {
            this.mOriginalHeaderX = dependency.getWidth() / 2 - child.getWidth() / 2;
        }
        // 计算Y轴坐标
        if (mOriginalHeaderY == 0) {
            mOriginalHeaderY = dependency.getHeight() - child.getHeight();
        }

        //计算toolbar从开始移动到最后的百分比
        float percent = dependency.getY() / mOriginalHeaderY;
        if (Math.abs(percent) > 1) {
            percent = 1;
        } else {
            percent = Math.abs(percent);
        }
        child.setY(mOriginalHeaderY - mOriginalHeaderY * percent);
        child.setX(mOriginalHeaderX - mOriginalHeaderX * percent);
        System.out.println("依赖控件高度变化:" + percent + "   " + dependency.getY() + "   " + dependency.getHeight() + "   自身控件位置变化:" + child.getY() + "  " + child.getX());
        return true;
    }
}
