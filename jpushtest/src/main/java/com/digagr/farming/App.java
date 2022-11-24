package com.digagr.farming;


import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.hjq.toast.ToastUtils;
import com.tencent.mmkv.MMKV;

import cn.jpush.android.api.JPushInterface;


/**
 * Copyright(c) 2020 极光
 */
public class App extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //因为引用的包过多，实现多包问题
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        initToast();
        initKV();
        // 初始化SDK
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }

    private void initKV() {
        MMKV.initialize(this);
    }

    private void initToast() {
        ToastUtils.init(this);
    }
}
