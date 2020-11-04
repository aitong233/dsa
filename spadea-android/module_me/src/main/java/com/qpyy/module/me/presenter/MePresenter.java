package com.qpyy.module.me.presenter;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.http.APIException;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.GuildResp;
import com.qpyy.module.me.bean.MyInfoResp;
import com.qpyy.module.me.bean.NameAuthResult;
import com.qpyy.module.me.bean.UserFillResp;
import com.qpyy.module.me.contacts.MeConacts;

import io.reactivex.disposables.Disposable;

public class MePresenter extends BasePresenter<MeConacts.View> implements MeConacts.IMePre {

    public MePresenter(MeConacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getMyInfo() {
        ApiClient.getInstance().getMyInfo(new BaseObserver<MyInfoResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(MyInfoResp myInfoResp) {
                UserBean user = BaseApplication.getIns().getUser();
                try {
                    user.setRole(Integer.parseInt(myInfoResp.getRole()));
                    user.setUser_is_new(Integer.parseInt(myInfoResp.getUser_is_new()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                user.setNickname(myInfoResp.getNickname());
                try {
                    user.setSex(Integer.parseInt(myInfoResp.getSex()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                user.setHead_picture(myInfoResp.getHead_picture());
                user.setRank_info(myInfoResp.getRank_info());
                BaseApplication.getIns().setUser(user);
                MvpRef.get().myInfoSuccess(myInfoResp);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void serviceUser() {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().serviceUser(new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().serviceSuccess(s);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }


    @Override
    public void getNameAuthResult(int type) {
        ApiClient.getInstance().getNameAuthResult(new BaseObserver<NameAuthResult>(false) {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(NameAuthResult result) {
                if (result.getApp_status() == 2) {
                    go2NameAuth(type);
                } else if (result.getApp_status() == 0) {
                    ToastUtils.show("审核中");
                } else if (result.getApp_status() == 1) {
                    ToastUtils.show("已认证");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (e instanceof APIException && ((APIException) e).getCode() == 0) {
                    go2NameAuth(type);
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getGuildInfo() {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().guildInfo(new BaseObserver<GuildResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(GuildResp guildResp) {
                if (guildResp.getState() == -1) {
                    ARouter.getInstance().build(ARouteConstants.JOIN_GUILD).navigation();
                } else if (guildResp.getState() == 0) {
                    ToastUtils.show("申请中");
                } else {
                    ARouter.getInstance().build(ARouteConstants.MY_GUILD).navigation();
                }
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    private void go2NameAuth(int type) {
        if (type == 0) {
            ARouter.getInstance().build(ARouteConstants.ME_NAME_AUTH).navigation();
        }
    }
}
