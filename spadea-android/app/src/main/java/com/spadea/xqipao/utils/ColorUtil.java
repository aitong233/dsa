package com.spadea.xqipao.utils;

import android.graphics.Color;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils
 * 创建人 王欧
 * 创建时间 2020/5/18 6:31 PM
 * 描述 describe
 */
public class ColorUtil {
    public static int getColorWithRankId(int rankId) {
        switch (rankId) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return Color.rgb(205, 230, 240);
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                return Color.rgb(92, 237, 140);
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
                return Color.rgb(58, 200, 254);
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
                return Color.rgb(255, 187, 65);
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
                return Color.rgb(245, 57, 235);
            case 51:
            case 52:
            case 53:
            case 54:
                return Color.rgb(255, 208, 113);
        }
        return Color.parseColor("#E2BC41");
    }
}
