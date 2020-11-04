package com.qpyy.libcommon.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.qpyy.libcommon.BuildConfig;


public class LogUtils {

    /**
     * 是否开启debug
     * 注意：使用Eclipse打包的时候记得取消Build Automatically，否则一直是true
     */
    public static boolean isDebug = BuildConfig.DEBUG;

    /**
     * 错误
     */
    public static void e(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).e(msg + "");
        }
    }

    public static void e(String tag, String title, String msg) {
        if (isDebug) {
            Logger.t(tag + "【" + title + "】").e(msg + "");
        }
    }

    public static void e(String tag, Object o) {
        if (isDebug) {
            Logger.t(tag).e(new Gson().toJson(o) + "");
        }
    }

    public static void e(String tag, String title, Object o) {
        if (isDebug) {
            Logger.t(tag + "【" + title + "】").e(JSON.toJSONString(o));
        }
    }

    /**
     * 调试
     */
    public static void d(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).d(msg + "");
        }
    }

    public static void d(String tag, Object msg) {
        if (isDebug) {
            Logger.t(tag).d(new Gson().toJson(msg) + "");
        }
    }


    /**
     * 信息
     */
    public static void i(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).i(msg + "");
        }
    }

    public static void i(String tag, Object msg) {
        if (isDebug) {
            Logger.t(tag).i(new Gson().toJson(msg) + "");
        }
    }
}

