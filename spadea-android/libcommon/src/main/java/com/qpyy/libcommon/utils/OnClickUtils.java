package com.qpyy.libcommon.utils;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.fragment
 * 创建人 黄强
 * 创建时间 2020/8/12 16:24
 * 描述 describe
 */

import android.util.Log;

/**
 * 防止按钮多次重复点击
 */

public class OnClickUtils {

    private static long lastClickTime = 0;
    private static long DIFF = 1000;//间隔1秒
    private static int lastButtonId = -1;

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(-1, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击
     *
     * @param diff
     * @return
     */
    public static boolean isFastDoubleClick(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            Log.d("isFastDoubleClick", "短时间内按钮多次触发: ");
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }
}

