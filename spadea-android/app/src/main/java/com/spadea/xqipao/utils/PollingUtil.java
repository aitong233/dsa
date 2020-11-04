package com.spadea.xqipao.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.spadea.xqipao.service.JobSchedulerService;
import com.spadea.xqipao.service.PollingService;

import static android.content.Context.ALARM_SERVICE;

public class PollingUtil {

    private final static String TAG = "PollingTask";

    private static boolean sIsPollingStart;

    private static final int POLLING_TASK_ID = 1000;

    /**
     * 执行定时任务
     *
     * @param context
     * @param intervalMillis 间隔（毫秒）
     */
    public static void startPollingTask(final Context context, long intervalMillis) {
        if (sIsPollingStart) {
            return;
        }
        Log.e("xuexiang", "开始执行定时任务");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobSchedulerService.start(context, POLLING_TASK_ID, intervalMillis);
        } else {
            //Android 4.4- 使用 AlarmManager
            AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            if (manager != null) {
                PendingIntent pendingIntent = getPendingIntent(context);
                //注意，这里设置的是非立即执行
                manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + intervalMillis, intervalMillis, pendingIntent);
            }
        }
//        //总体来讲还是RxJava比较精准
//        DisposablePool.get().add(RxJavaUtils.polling(intervalMillis, intervalMillis, TimeUnit.MILLISECONDS, new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong)  {
//                PollingService.start(context);
//            }
//        }, new SimpleThrowableAction(TAG)), TAG);
        sIsPollingStart = true;
    }

    /**
     * 获取轮询意图
     * @param context
     * @return
     */
    private static PendingIntent getPendingIntent(Context context) {
        Intent intent = new Intent(context, PollingService.class);
        return PendingIntent.getService(context, POLLING_TASK_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    /**
     * 停止定时任务
     */
    private static void stopPollingTask(Context context) {
        if (!sIsPollingStart) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobSchedulerService.stop(context, POLLING_TASK_ID);
        } else {
            AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            PendingIntent pendingIntent = getPendingIntent(context);
            if (manager != null && pendingIntent != null) {
                manager.cancel(pendingIntent);
            }
        }
//        DisposablePool.get().remove(TAG);
        sIsPollingStart = false;
    }
}
