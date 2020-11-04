package com.spadea.xqipao.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {

    /**
     * 开启JobService定时服务
     *
     * @param context
     * @param jobId
     * @param intervalMillis
     */
    public static void start(Context context, int jobId, long intervalMillis) {
        JobInfo.Builder builder = new JobInfo.Builder(jobId, new ComponentName(context, JobSchedulerService.class));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //执行的最小延迟时间
            builder.setMinimumLatency(intervalMillis);
            //执行的最长延时时间
            builder.setOverrideDeadline(intervalMillis);
            //线性重试方案
            builder.setBackoffCriteria(intervalMillis, JobInfo.BACKOFF_POLICY_LINEAR);
        } else {
            builder.setPeriodic(intervalMillis);
        }
        JobScheduler scheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
        if (scheduler != null) {
            scheduler.schedule(builder.build());
        }
    }

    /**
     * 停止JobService定时服务
     *
     * @param context
     * @param jobId
     */
    public static void stop(Context context, int jobId) {
        JobScheduler scheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
        if (scheduler != null) {
            scheduler.cancel(jobId);
        }
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        PollingService.start(this);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}