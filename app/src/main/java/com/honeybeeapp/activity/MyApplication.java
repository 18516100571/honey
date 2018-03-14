package com.honeybeeapp.activity;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.honeybeeapp.network.HttpUtils;

/**
 * Created by Avazu Holding on 2018/3/14.
 */

public class MyApplication extends Application {
    static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(this);
        }
        cookieManager.setAcceptCookie(true);
        HttpUtils.initOkHttp();

    }

    public static Context getAppContext() {
        return mContext;
    }
}
