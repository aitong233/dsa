package com.qpyy.libcommon.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.webkit.WebView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.hjq.toast.ToastUtils;
import com.lahm.library.EasyProtectorLib;
import com.lahm.library.EmulatorCheckCallback;
import com.mob.MobSDK;
import com.opensource.svgaplayer.SVGAParser;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.qpyy.libcommon.BuildConfig;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.db.DbController;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.widget.CustomRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.libcommon.base
 * 创建人 王欧
 * 创建时间 2020/6/24 4:36 PM
 * 描述 describe
 */
public class BaseApplication extends Application {

    private static BaseApplication sInstance;

    public String emulator = "0";
    public boolean isShow;
    public boolean isPlaying;
    public String playName;
    public String playCover;
    public String playId;
    public UserBean mUserBean;
    public boolean showSelf;//盲盒是否能送自己


    public static BaseApplication getIns() {
        return sInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initWebView();
        initToast();
        Utils.init(this);
        initARouter();
        initLogger();
        MobSDK.init(this);
        if (BuildConfig.DEBUG) {
            Thread.setDefaultUncaughtExceptionHandler(new OwnUncaughtExceptionHandler());
        }
        checkInEmulator();
        initGreenDao();
        initSvgaParser();
    }

    private void initSvgaParser() {
        SVGAParser.Companion.shareParser().init(this);
    }

    private void initGreenDao() {
        DbController.getInstance(this);
    }

    private void initToast() {
        ToastUtils.init(this);
    }

    public void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new CustomRefreshHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public static class OwnUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            StackTraceElement[] elements = e.getStackTrace();

            StringBuilder reason = new StringBuilder(e.toString());
            if (elements != null && elements.length > 0) {
                for (StackTraceElement element : elements) {
                    reason.append("\n");
                    reason.append(element.toString());
                }
            }
            Logger.e("error", reason.toString());
            AppUtils.exitApp();
        }
    }

    private void checkInEmulator() {
        emulator = EasyProtectorLib.checkIsRunningInEmulator(this, new EmulatorCheckCallback() {
            @Override
            public void findEmulator(String emulatorInfo) {
                Logger.e(emulatorInfo);
            }
        }) ? "1" : "0";
    }


    private void initWebView() {
        //Android P 以及之后版本不支持同时从多个进程使用具有相同数据目录的WebView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName(this);
            if (!"com.spadea.yuyin".equals(processName)) {//判断不等于默认进程名称
                WebView.setDataDirectorySuffix(processName);
            }
        }
    }


    public String getProcessName(Context context) {
        if (context == null) return null;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }

    public UserBean getUser() {
        if (mUserBean == null) {
            mUserBean = SpUtils.getUserInfo();
        }
        return mUserBean;
    }

    public void setUser(UserBean userBean) {
        mUserBean = userBean;
        SpUtils.saveUserId(userBean.getUser_id());
        SpUtils.saveUserInfo(userBean);
    }
}
