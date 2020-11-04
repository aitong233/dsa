package com.spadea.xqipao.ui.login.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.fm.openinstall.OpenInstall;
import com.hyphenate.chat.EMClient;
import com.qpyy.libcommon.bean.UserBean;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.data.even.LoginFinishEvent;
import com.spadea.xqipao.data.even.SplashFinishEvent;
import com.spadea.xqipao.echart.db.EChartDBManager;
import com.spadea.xqipao.receiver.TagAliasOperatorHelper;
import com.spadea.xqipao.ui.home.activity.HomeActivity;
import com.spadea.xqipao.utils.SPUtil;
import com.spadea.xqipao.utils.StatisticsUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.tendcloud.tenddata.TDAccount;
import com.spadea.yuyin.util.PreferencesUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.login.activity.CodeLoginActivity;
import com.spadea.xqipao.ui.login.activity.ImproveInfoActivity;
import com.spadea.xqipao.ui.login.contacter.LoginContacter;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import io.reactivex.disposables.Disposable;

public class LoginPresenter extends BasePresenter<LoginContacter.View> implements LoginContacter.ILoginPre {

    private boolean isLogin;

    public LoginPresenter(LoginContacter.View view, Context context) {
        super(view, context);
    }


    @Override
    public void sendCode(String phoneNumber, int type) {
        if (isViewAttach()) {
            MvpRef.get().showLoadings();
            api.sendCode("", phoneNumber, type, new BaseObserver<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(String s) {
                    MvpRef.get().sendCodeSuccess(phoneNumber);
                }

                @Override
                public void onComplete() {
                    MvpRef.get().disLoadings();
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    CrashReport.postCatchedException(e);
                }

            });
        }
    }

    @Override
    public void login(String mobile, String password, String code, int type) {
        if (!isLogin && isViewAttach()) {
            MvpRef.get().showLoadings();
            isLogin = true;
            api.login(mobile, password, code, type, new BaseObserver<UserBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(UserBean userBean) {
                    if (userBean.getCode() == 2) {
                        sendCode(mobile, 1);
                        return;
                    }
                    OpenInstall.reportEffectPoint(Constant.OpenInstall.PHONELOGIN, 1);
                    OpenInstall.reportEffectPoint(Constant.OpenInstall.LOGIN, 1);
                    if (userBean.getLogin_type().equals("register")) {
                        StatisticsUtils.onRegister(userBean.getUser_id(), TDAccount.AccountType.REGISTERED, userBean.getNickname());
                        OpenInstall.reportEffectPoint(Constant.OpenInstall.PHONEREGISTER, 1);
                        OpenInstall.reportRegister();
                    } else {
                        SPUtil.saveboolean(Constant.Channel.ISFIRST, true);
                    }
                    StatisticsUtils.onLogin(userBean.getUser_id(), TDAccount.AccountType.REGISTERED, userBean.getNickname());
                    loginSuccess(userBean);
                }


                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    MvpRef.get().disLoadings();
                    isLogin = false;
                    CrashReport.postCatchedException(e);
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }


    @Override
    public void thirdPartyLogin(String openId, int three_party, String nickname, String head_pic) {
        if (isViewAttach()) {
            MvpRef.get().showLoadings();
            api.thirdPartyLogin(openId, three_party, null, null, new BaseObserver<UserBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(UserBean userBean) {
                    if (three_party == 1) {
                        OpenInstall.reportEffectPoint(Constant.OpenInstall.WXLOGIN, 1);
                        OpenInstall.reportEffectPoint(Constant.OpenInstall.LOGIN, 1);
                        StatisticsUtils.onLogin(userBean.getUser_id(), TDAccount.AccountType.WEIXIN, userBean.getNickname());
                        if (userBean.getLogin_type().equals("register")) {
                            OpenInstall.reportEffectPoint(Constant.OpenInstall.WXREGISTER, 1);
                            OpenInstall.reportRegister();
                            StatisticsUtils.onRegister(userBean.getUser_id(), TDAccount.AccountType.WEIXIN, userBean.getNickname());
                        } else {
                            SPUtil.saveboolean(Constant.Channel.ISFIRST, true);
                        }
                    } else {
                        OpenInstall.reportEffectPoint(Constant.OpenInstall.QQLOGIN, 1);
                        OpenInstall.reportEffectPoint(Constant.OpenInstall.LOGIN, 1);
                        StatisticsUtils.onLogin(userBean.getUser_id(), TDAccount.AccountType.QQ, userBean.getNickname());
                        if (userBean.getLogin_type().equals("register")) {
                            OpenInstall.reportEffectPoint(Constant.OpenInstall.QQREGISTER, 1);
                            OpenInstall.reportRegister();
                            StatisticsUtils.onRegister(userBean.getUser_id(), TDAccount.AccountType.QQ, userBean.getNickname());
                        } else {
                            SPUtil.saveboolean(Constant.Channel.ISFIRST, true);
                        }
                    }
                    loginSuccess(userBean);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    MvpRef.get().disLoadings();
                    CrashReport.postCatchedException(e);
                }

                @Override
                public void onComplete() {

                }

            });
        }
    }

    @Override
    public void oauthLogin(String netease_token, String access_token, int type) {
        if (isViewAttach()) {
            MvpRef.get().showLoadings();
            api.oauthLogin(netease_token, access_token, type, new BaseObserver<UserBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(UserBean userBean) {
                    OpenInstall.reportEffectPoint(Constant.OpenInstall.PHONELOGIN, 1);
                    OpenInstall.reportEffectPoint(Constant.OpenInstall.LOGIN, 1);
                    if (userBean.getLogin_type().equals("register")) {
                        StatisticsUtils.onRegister(userBean.getUser_id(), TDAccount.AccountType.REGISTERED, userBean.getNickname());
                        OpenInstall.reportEffectPoint(Constant.OpenInstall.PHONEREGISTER, 1);
                        OpenInstall.reportRegister();
                    } else {
                        SPUtil.saveboolean(Constant.Channel.ISFIRST, true);
                    }
                    StatisticsUtils.onLogin(userBean.getUser_id(), TDAccount.AccountType.REGISTERED, userBean.getNickname());
                    loginSuccess(userBean);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    MvpRef.get().disLoadings();
                    ActivityUtils.startActivity(CodeLoginActivity.class);
                    EventBus.getDefault().post(new SplashFinishEvent());
                    CrashReport.postCatchedException(e);
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    public void loginSuccess(UserBean userBean) {
        MvpRef.get().disLoadings();
        if (TextUtils.isEmpty(userBean.getEmchat_username()) || TextUtils.isEmpty(userBean.getEmchat_password())) {
            ToastUtils.showShort("登录失败请重试");
            return;
        }
        EMClient.getInstance().logout(true);
        EChartDBManager.getInstance(MyApplication.getInstance()).closeDB();
        MyApplication.getInstance().setUser(userBean);
        MyApplication.getInstance().setToken(userBean.getToken());
        PreferencesUtils.putString(MyApplication.getInstance(), "mobile", userBean.getMobile());
        TagAliasOperatorHelper.getInstance().setAlias(MyApplication.getInstance().getUser().getUser_id());
        isLogin = false;
        if (isViewAttach()) {
            MvpRef.get().disLoadings();
        }
        if (userBean.getSex() == 0) {
            try {
                Intent intent = new Intent(com.blankj.utilcode.util.ActivityUtils.getTopActivity(), ImproveInfoActivity.class);
                intent.putExtra("nickname", String.format("用户%s", userBean.getUser_code()));
                com.blankj.utilcode.util.ActivityUtils.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            com.blankj.utilcode.util.ActivityUtils.startActivity(HomeActivity.class);
        }
        EventBus.getDefault().post(new LoginFinishEvent());
        EventBus.getDefault().post(new SplashFinishEvent());
    }

    public void qqLogin() {
        if (!AppUtils.isAppInstalled("com.tencent.mobileqq")) {
            ToastUtils.showShort("请安装QQ客户端");
            return;
        }
        Platform platformQQ = ShareSDK.getPlatform(QQ.NAME);
        platformQQ.showUser(null);
        platformQQ.removeAccount(true);
        platformQQ.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                if (MvpRef.get().getSelfActivity() != null) {
                    MvpRef.get().getSelfActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            thirdPartyLogin(platform.getDb().getUserId(), 2, platform.getDb().getUserName(), platform.getDb().getUserIcon());
                        }
                    });
                }

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastUtils.showShort("登录失败");
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
    }

    public void wechatLogin() {
        if (!AppUtils.isAppInstalled("com.tencent.mm")) {
            ToastUtils.showShort("请安装微信客户端");
            return;
        }
        Platform platformWX = ShareSDK.getPlatform(Wechat.NAME);
        platformWX.showUser(null);
        platformWX.removeAccount(true);
        platformWX.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                if (MvpRef.get().getSelfActivity() != null) {
                    MvpRef.get().getSelfActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            thirdPartyLogin(platform.getDb().getUserId(), 1, platform.getDb().getUserName(), platform.getDb().getUserIcon());
                        }
                    });
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastUtils.showShort("登录失败");
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
    }

    public void ysxl() {
        ARouter.getInstance().build(ARouters.H5).withString("url", Constant.URL.URL_USER_YSXY).withString("title", "隐私协议").navigation();
    }

    public void yhxy() {
        ARouter.getInstance().build(ARouters.H5).withString("url", Constant.URL.URL_USER_YHXY).withString("title", "用户协议").navigation();
    }

}
