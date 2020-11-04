package com.spadea.xqipao.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

public class PollingService extends IntentService {
    private static final String SERVICE_NAME = "PollingService";


    /**
     * 执行定时轮询服务
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, PollingService.class);
        context.startService(intent);
    }


    public PollingService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //在子线程中
        if (intent == null) {
            return;
        }

    }
}
