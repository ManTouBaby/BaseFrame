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
            this.mOriginalHeaderX = (int) child.getX();
        }
        // 计算Y轴坐标
        if (mOriginalHeaderY == 0) {
            mOriginalHeaderY = (int) child.getY();
        }

        float density = parent.getContext().getResources().getDisplayMetrics().density;
        //计算toolbar从开始移动到最后的百分比
        float percentY = dependency.getY() / ((dependency.getHeight() - 86 * density));
        float percentX = dependency.getY() / (dependency.getHeight() - 86 * density);
        if (Math.abs(percentY) > 1) {
            percentY = 1;
        } else {
            percentY = Math.abs(percentY);
        }
        if (Math.abs(percentX) > 1) {
            percentX = 1;
        } else {
            percentX = Math.abs(percentX);
        }
        float y = mOriginalHeaderY - (mOriginalHeaderY - 21 * density) * percentY;
        float x = mOriginalHeaderX - mOriginalHeaderX * percentX;
        child.setY(y);
        child.setX(x);
        child.setScaleX(1f - (0.45f * percentX));
        child.setScaleY(1f - (0.45f * percentX));
        System.out.println("控件位置变化:" + y + "  " + x + "   " + percentY);
//        System.out.println("依赖控件高度变化:" + percentX + "   " + dependency.getY() + "   " + dependency.getHeight() + "   自身控件位置变化:" + child.getY() + "  " + child.getX());
        return true;
    }
}
