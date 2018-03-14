package com.honeybeeapp.utils;

import android.util.Log;

import com.honeybeeapp.base.Constants;

/**
 * Created by Avazu Holding on 2018/3/1.
 */

public class LogHelp {

    public static void i(String key, String message) {
        if (Constants.isLogOpen)
            Log.i(key,  message);
    }

    public static void i(String message) {
        if (Constants.isLogOpen)
            Log.i("honeyLog",  message);
    }

    public static void w(String key, String message) {
        if (Constants.isLogOpen)
            Log.i(key,  message);
    }

    public static void w(String message) {
        if (Constants.isLogOpen)
            Log.i("honeyLog",  message);
    }

    public static void e(String key, String message) {
        if (Constants.isLogOpen)
            Log.i(key,  message);
    }

    public static void e(String message) {
        if (Constants.isLogOpen)
            Log.i("honeyLog",  message);
    }

    public static void d(String key, String message) {
        if (Constants.isLogOpen)
            Log.i(key,  message);
    }

    public static void d(String message) {
        if (Constants.isLogOpen)
            Log.i("honeyLog",  message);
    }

    public static void v(String key, String message) {
        if (Constants.isLogOpen)
            Log.i(key,  message);
    }

    public static void v(String message) {
        if (Constants.isLogOpen)
            Log.i("honeyLog",  message);
    }

}
