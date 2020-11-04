package com.qpyy.module_news.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NotificationUtils;

public class NotificationPageHelper {
    /**
     * 跳转到通知设置界面
     */
    public static void open(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                context.startActivity(intent);
            }/* else if (context.getPackageManager().getLaunchIntentForPackage("com.iqoo.secure") != null) {
                context.startActivity(context.getPackageManager().getLaunchIntentForPackage("com.iqoo.secure"));
            } else if (context.getPackageManager().getLaunchIntentForPackage("com.oppo.safe") != null) {
                context.startActivity(context.getPackageManager().getLaunchIntentForPackage("com.oppo.safe"));
            }*/ else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra("app_package", context.getPackageName());
                intent.putExtra("app_uid", context.getApplicationInfo().uid);
                context.startActivity(intent);
            } else {
                AppUtils.launchAppDetailsSettings();
            }
        } catch (Exception e) {
            LogUtils.e(e);
            AppUtils.launchAppDetailsSettings();
        }
    }
}
