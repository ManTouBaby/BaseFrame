package com.hy.framelibrary.net.callbck;

import com.hy.framelibrary.net.listener.OnBaseResultListener;

public abstract class OnStringResultListener implements OnBaseResultListener<String> {
    public void convertData(String json) {
        onSuccess(json);
    }

}
