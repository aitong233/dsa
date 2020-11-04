package com.spadea.xqipao.ui.login.presenter;

import android.content.Context;

import com.fm.openinstall.OpenInstall;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.utils.SPUtil;
import com.spadea.xqipao.utils.StatisticsUtils;
import com.tendcloud.tenddata.TDAccount;
import com.qpyy.libcommon.bean.UserBean;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.login.contacter.QuickLoginContacts;

import io.reactivex.disposables.Disposable;

public class QuickLoginPresenter extends BasePresenter<QuickLoginContacts.View> implements QuickLoginContacts.IQuickLoginPre {
    public QuickLoginPresenter(QuickLoginContacts.View view, Context context) {
        super(view, context);
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
                    MvpRef.get().loginSuccess(userBean);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    MvpRef.get().go2OtherLogin();
                }

                @Override
                public void onComplete() {
                    MvpRef.get().disLoadings();
                }
            });
        }
    }
}