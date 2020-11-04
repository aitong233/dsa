package com.spadea.yuyin;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.http.HttpResponseCache;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.MetaDataUtils;
import com.fm.openinstall.OpenInstall;
import com.hyphenate.EMCallBack;
import com.spadea.yuyin.ModuleIndexEventBusIndex;
import com.spadea.yuyin.ModuleMeEventBusIndex;
import com.spadea.yuyin.ModuleRoomEventBusIndex;
import com.spadea.yuyin.MyEventBusIndex;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.netease.nis.quicklogin.QuickLogin;
import com.qpyy.libcommon.BuildConfig;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.db.DbController;
import com.qpyy.libcommon.event.LoginOutEvent;
import com.qpyy.libcommon.service.EMqttService;
import com.spadea.xqipao.receiver.NetStateReceiver;
import com.spadea.xqipao.receiver.TagAliasOperatorHelper;
import com.tencent.bugly.Bugly;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tendcloud.appcpa.TalkingDataAppCpa;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.yutang.game.grabmarbles.GrabMarblesManager;
import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.PreferencesUtils;
import com.spadea.yuyin.util.utilcode.LogUtils;
import com.spadea.yuyin.util.utilcode.Utils;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.data.api.RemoteDataSource;
import com.spadea.xqipao.echart.EChartHelper;
import com.spadea.xqipao.ui.SplashActivity;
import com.spadea.xqipao.ui.me.adapter.MyOrderAdapter;
import com.spadea.xqipao.utils.QuickLoginUiConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import java.io.File;
import java.io.IOException;

import cn.jpush.android.api.JPushInterface;
import io.reactivex.disposables.Disposable;

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author 1
 * @Copyright Copyright (c) 1
 * @Date $date$ $time$
 */

public class MyApplication extends BaseApplication {
    static MyApplication instance;
    static Context context;
    public boolean labor;
    public int chat_min_level;
    private String userId;

    public QuickLogin quickLogin;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
        Utils.init(this);
        initOkGo();
        initEmChart();
        initGreenDao();
        initCrashReport();
        initCache();
        initEventBus();
        initJPush();
        initOpenInstall();
        initTCAgent();
        initQuickLogin();
        initUmeng();
        initWxPay();
        GrabMarblesManager.INSTANCE.init(this, BuildConfig.DEBUG);
    }

    private void initWxPay() {
        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, null);
        wxapi.registerApp("wx621581e64de29ec4");
    }

    //提升eventbus性能
    private void initEventBus() {
        try {
            EventBus.builder().addIndex(new MyEventBusIndex()).addIndex(new ModuleRoomEventBusIndex()).addIndex(new ModuleMeEventBusIndex()).addIndex(new ModuleIndexEventBusIndex()).installDefaultEventBus();
        } catch (EventBusException e) {
            e.printStackTrace();
        }
    }


    private void initUmeng() {
        String channelId = "default";
        try {
            channelId = MetaDataUtils.getMetaDataInApp("TD_CHANNEL_ID");
        } catch (Exception e) {
            e.printStackTrace();
        }

        UMConfigure.init(this, "5f86bfb980455950e4aa0fa0", channelId, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
        //选择AUTO页面采集模式，统计SDK基础指标无需手动埋点可自动采集。
//建议在宿主App的Application.onCreate函数中调用此函数。
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
    }

    private void initQuickLogin() {
        quickLogin = QuickLogin.getInstance(this, Constant.Account.BUSINESSID);
        quickLogin.setPrefetchNumberTimeout(3000);
        quickLogin.setFetchNumberTimeout(3000);
        quickLogin.setUnifyUiConfig(QuickLoginUiConfig.getUiConfig(this));
    }

    private void initTCAgent() {
//        JLibrary.InitEntry(this); //移动安全联盟统一SDK初始化
        TCAgent.LOG_ON = BuildConfig.DEBUG;
        TCAgent.init(this);
        TCAgent.setReportUncaughtExceptions(true);
        //移动广告监测
        TalkingDataAppCpa.init(this, "F8C0257271EC46FB8270D134E76DAAF5", MetaDataUtils.getMetaDataInApp("TD_CHANNEL_ID"));
    }


    private void initJPush() {
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);
    }

    private void initCache() {
        File cacheDir = new File(getCacheDir(), "http");
        try {
            HttpResponseCache.install(cacheDir, 1024 * 1024 * 128);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initARouter() {
        ARouter.init(this);
    }

    private void initCrashReport() {
        Bugly.init(getApplicationContext(), com.qpyy.libcommon.BuildConfig.BUGLY_APPID, BuildConfig.DEBUG);
    }

    private void initGreenDao() {
        DbController.getInstance(this);
    }

    private void initOkGo() {
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(RemoteDataSource.getInstance().getHttpClient())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }

    /**
     * 退出登录
     */
    public void reLogin() {
        if (!TextUtils.isEmpty(playId) && !TextUtils.isEmpty(userId)) {
            MobclickAgent.onProfileSignOff();
            RemoteDataSource.getInstance().quitRoomWithUserId(playId, userId, new BaseObserver<String>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(String s) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
        EventBus.getDefault().post(new LoginOutEvent());
        EChartHelper.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                EMqttService.stopService(MyApplication.getContext());
                PreferencesUtils.clear(getContext());
                TagAliasOperatorHelper.getInstance().deleteAlias();
                Intent intent = new Intent(getContext(), SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    @Override
    public UserBean getUser() {
        UserBean userBean = super.getUser();
        if (userBean == null) {
            return new UserBean();
        }
        userBean.setNews_vibrate(PreferencesUtils.getInt(this, Constants.NEWS_VIBRATE, 1));
        userBean.setNews_voice(PreferencesUtils.getInt(this, Constants.NEWS_VOICE, 1));
        return userBean;
    }

    @Override
    public void setUser(UserBean userBean) {
        super.setUser(userBean);
        userId = userBean.getUser_id();
        Bugly.setUserId(this, userBean.getUser_code());
        EChartHelper.getInstance().setSettingMsgSound(userBean.getNews_voice() == 1);
        EChartHelper.getInstance().setSettingMsgVibrate(userBean.getNews_vibrate() == 1);
        try {
            EChartHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(userBean.getNickname());
            EChartHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(userBean.getHead_picture());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setToken(String token) {
        PreferencesUtils.putString(this, "token", token);
    }

    public String getToken() {
        return PreferencesUtils.getString(this, "token", "");
    }

    public boolean hasToken() {
        if (TextUtils.isEmpty(MyApplication.getInstance().getToken()))
            return false;
        return true;
    }

    private void initEmChart() {
        EChartHelper.getInstance().init(this);
    }

    public static Context getContext() {
        return context;
    }


    private void initOpenInstall() {
        if (OpenInstall.isMainProcess(this)) {
            OpenInstall.init(this);
            NetStateReceiver mNetStateReceiver = new NetStateReceiver();
            IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(mNetStateReceiver, filter);
        }
    }

    public boolean notSelf(int type, int userId, int playUserId) {
        if (type == MyOrderAdapter.TYPE_RECV) {
            LogUtils.e("notSelf", notSelf(String.valueOf(playUserId)));
            return notSelf(String.valueOf(playUserId));
        }
        return notSelf(String.valueOf(userId));
    }


    public boolean notSelf(String userId) {
        return !userId.equals(getUser().getUser_id());
    }
}
