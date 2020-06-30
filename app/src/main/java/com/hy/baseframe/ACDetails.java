package com.hy.baseframe;

import android.os.Bundle;

import com.hy.framelibrary.page.activity.BaseAC;

public class ACDetails extends BaseAC {
    String dd;

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        findViewById(R.id.mt_one).setOnClickListener(v -> {
            dd.equals("ss");
        });
    }


}
