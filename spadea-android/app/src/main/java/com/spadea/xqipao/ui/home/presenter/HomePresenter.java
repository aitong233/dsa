package com.spadea.xqipao.ui.home.presenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.qpyy.libcommon.bean.UserBean;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.AppUpdateModel;
import com.spadea.xqipao.data.NewsModel;
import com.spadea.xqipao.data.SignSwitchModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.echart.EChartHelper;
import com.spadea.xqipao.utils.BrandUtils;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.home.contacts.HomeContacts;

import io.reactivex.disposables.Disposable;

public class HomePresenter extends BasePresenter<HomeContacts.View> implements HomeContacts.IHomePre {

    public HomePresenter(HomeContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void appUpdate() {
        api.appUpdate(new BaseObserver<AppUpdateModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(AppUpdateModel appUpdataModel) {
                MvpRef.get().appUpdate(appUpdataModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void randomHotRoom() {
        api.randomHotRoom(new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().setRandomHotRoom(s);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void userNews() {
        api.userNews(new BaseObserver<NewsModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(NewsModel newsModel) {
                MvpRef.get().userNewsSuccess(newsModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void signSwitch() {
        api.signSwitch(new BaseObserver<SignSwitchModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(SignSwitchModel signSwitchModel) {
                MyApplication.getInstance().labor = signSwitchModel.getLabor() == 1;
                MyApplication.getInstance().chat_min_level = signSwitchModel.getChat_min_level();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isIgnoringBatteryOptimizations()) {
                try {
                    requestIgnoreBatteryOptimizations();
                } catch (Exception e) {
                    if (BrandUtils.isXiaomi()) {
                        goXiaomiSetting();
                    } else if (BrandUtils.isHuawei()) {
                        goHuaweiSetting();
                    } else if (BrandUtils.isOPPO()) {
                        goOPPOSetting();
                    } else if (BrandUtils.isVIVO()) {
                        goVIVOSetting();
                    } else if (BrandUtils.isMeizu()) {
                        goMeizuSetting();
                    } else if (BrandUtils.isSamsung()) {
                        goSamsungSetting();
                    } else if (BrandUtils.isLeTV()) {
                        goLetvSetting();
                    } else if (BrandUtils.isSmartisan()) {
                        goSmartisanSetting();
                    }
                }
            }
        }
    }

    @Override
    public void loginIm() {
        boolean connected = EMClient.getInstance().isConnected();
        if (!connected) {
            UserBean user = MyApplication.getInstance().getUser();
            if (TextUtils.isEmpty(user.getEmchat_username()) || TextUtils.isEmpty(user.getEmchat_password())) {
                ToastUtils.showShort("无环信账号或密码，请重新登录");
                MyApplication.getInstance().reLogin();
                return;
            }
            EMClient.getInstance().login(user.getEmchat_username(), user.getEmchat_password(), new EMCallBack() {
                @Override
                public void onSuccess() {
                    EChartHelper.getInstance().setCurrentUserName(user.getEmchat_username());
                    EChartHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(user.getNickname());
                    EChartHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(user.getHead_picture());
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    boolean updatenick = EMClient.getInstance().pushManager().updatePushNickname(user.getEmchat_username());
                    if (!updatenick) {
                        LogUtils.e("LoginActivity", "update current user nick fail");
                    }

                }

                @Override
                public void onError(int i, String s) {
                    ToastUtils.showShort("登录失败：" + i + "  " + s);
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isIgnoringBatteryOptimizations() {
        boolean isIgnoring = false;
        PowerManager powerManager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(mContext.getPackageName());
        }
        return isIgnoring;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestIgnoreBatteryOptimizations() {
        Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
        intent.setData(Uri.parse("package:" + mContext.getPackageName()));
        mContext.startActivity(intent);
    }


    /**
     * 跳转到指定应用的首页
     */
    private void showActivity(@NonNull String packageName) {
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(packageName);
        mContext.startActivity(intent);
    }

    /**
     * 跳转到指定应用的指定页面
     */
    private void showActivity(@NonNull String packageName, @NonNull String activityDir) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityDir));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }


    private void goXiaomiSetting() {
        showActivity("com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity");
    }

    private void goHuaweiSetting() {
        try {
            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        } catch (Exception e) {
            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.bootstart.BootStartActivity");
        }
    }

    private void goOPPOSetting() {
        try {
            showActivity("com.coloros.phonemanager");
        } catch (Exception e1) {
            try {
                showActivity("com.oppo.safe");
            } catch (Exception e2) {
                try {
                    showActivity("com.coloros.oppoguardelf");
                } catch (Exception e3) {
                    showActivity("com.coloros.safecenter");
                }
            }
        }
    }

    private void goVIVOSetting() {
        showActivity("com.iqoo.secure");
    }

    private void goMeizuSetting() {
        showActivity("com.meizu.safe");
    }

    private void goSamsungSetting() {
        try {
            showActivity("com.samsung.android.sm_cn");
        } catch (Exception e) {
            showActivity("com.samsung.android.sm");
        }
    }

    private void goLetvSetting() {
        showActivity("com.letv.android.letvsafe",
                "com.letv.android.letvsafe.AutobootManageActivity");
    }

    private void goSmartisanSetting() {
        showActivity("com.smartisanos.security");
    }
}
