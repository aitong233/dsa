package com.qpyy.module_main.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module_main.contacts.PasswordLoginContacts;

import io.reactivex.disposables.Disposable;

public class PasswordLoginPresenter extends BasePresenter<PasswordLoginContacts.View> implements PasswordLoginContacts.IPasswordLoginPre {
    public PasswordLoginPresenter(PasswordLoginContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void sendCode(String phoneNumber, int type) {
        if (isViewAttach()) {
            MvpRef.get().showLoadings();
            api.sendCode(phoneNumber, type, new BaseObserver<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(String s) {
                    MvpRef.get().sendCodeSuccess();
                }

                @Override
                public void onComplete() {
                    MvpRef.get().disLoadings();
                }
            });
        }
    }

    @Override
    public void login(String mobile, String password, String code, int type) {
        if (isViewAttach()) {
            MvpRef.get().showLoadings();
            api.login(mobile, password, code, type, new BaseObserver<UserBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(UserBean userBean) {
//                    OpenInstall.reportEffectPoint(Constant.OpenInstall.PHONELOGIN, 1);
//                    OpenInstall.reportEffectPoint(Constant.OpenInstall.LOGIN, 1);
//                    if (userBean.getLogin_type().equals("register")) {
//                        StatisticsUtils.onRegister(userBean.getUser_id(), TDAccount.AccountType.REGISTERED, userBean.getNickname());
//                        OpenInstall.reportEffectPoint(Constant.OpenInstall.PHONEREGISTER, 1);
//                        OpenInstall.reportRegister();
//                    } else {
//                        SPUtil.saveboolean(Constant.Channel.ISFIRST, true);
//                    }
//                    StatisticsUtils.onLogin(userBean.getUser_id(), TDAccount.AccountType.REGISTERED, userBean.getNickname());
                    MvpRef.get().loginSuccess(userBean);
                }


                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    MvpRef.get().disLoadings();
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
            api.thirdPartyLogin(openId, three_party, nickname, head_pic, new BaseObserver<UserBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(UserBean userBean) {
//                    if (three_party == 1) {
//                        OpenInstall.reportEffectPoint(Constant.OpenInstall.WXLOGIN, 1);
//                        OpenInstall.reportEffectPoint(Constant.OpenInstall.LOGIN, 1);
//                        StatisticsUtils.onLogin(userBean.getUser_id(), TDAccount.AccountType.WEIXIN, userBean.getNickname());
//                        if (userBean.getLogin_type().equals("register")) {
//                            OpenInstall.reportEffectPoint(Constant.OpenInstall.WXREGISTER, 1);
//                            OpenInstall.reportRegister();
//                            StatisticsUtils.onRegister(userBean.getUser_id(), TDAccount.AccountType.WEIXIN, userBean.getNickname());
//                        } else {
//                            SPUtil.saveboolean(Constant.Channel.ISFIRST, true);
//                        }
//                    } else {
//                        OpenInstall.reportEffectPoint(Constant.OpenInstall.QQLOGIN, 1);
//                        OpenInstall.reportEffectPoint(Constant.OpenInstall.LOGIN, 1);
//                        StatisticsUtils.onLogin(userBean.getUser_id(), TDAccount.AccountType.QQ, userBean.getNickname());
//                        if (userBean.getLogin_type().equals("register")) {
//                            OpenInstall.reportEffectPoint(Constant.OpenInstall.QQREGISTER, 1);
//                            OpenInstall.reportRegister();
//                            StatisticsUtils.onRegister(userBean.getUser_id(), TDAccount.AccountType.QQ, userBean.getNickname());
//                        } else {
//                            SPUtil.saveboolean(Constant.Channel.ISFIRST, true);
//                        }
//                    }
                    MvpRef.get().loginSuccess(userBean);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    MvpRef.get().disLoadings();
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
//                    OpenInstall.reportEffectPoint(Constant.OpenInstall.PHONELOGIN, 1);
//                    OpenInstall.reportEffectPoint(Constant.OpenInstall.LOGIN, 1);
//                    if (userBean.getLogin_type().equals("register")) {
//                        StatisticsUtils.onRegister(userBean.getUser_id(), TDAccount.AccountType.REGISTERED, userBean.getNickname());
//                        OpenInstall.reportEffectPoint(Constant.OpenInstall.PHONEREGISTER, 1);
//                        OpenInstall.reportRegister();
//                    } else {
//                        SPUtil.saveboolean(Constant.Channel.ISFIRST, true);
//                    }
//                    StatisticsUtils.onLogin(userBean.getUser_id(), TDAccount.AccountType.REGISTERED, userBean.getNickname());
                    MvpRef.get().loginSuccess(userBean);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    MvpRef.get().disLoadings();
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

}