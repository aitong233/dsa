package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.SecondLevelPasswordContacter;

import io.reactivex.disposables.Disposable;

public class SecondLevelPasswordPresenter extends BasePresenter<SecondLevelPasswordContacter.View> implements SecondLevelPasswordContacter.ISecondLevelPre {

    public SecondLevelPasswordPresenter(SecondLevelPasswordContacter.View view, Context context) {
        super(view, context);
    }

    @Override
    public void sendCode(String phoneNumber, int type) {
        MvpRef.get().showLoadings();
        api.sendCode(MyApplication.getInstance().getToken(), phoneNumber, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                ToastUtils.showShort("短信验证码发送成功请注意查收");
                MvpRef.get().sendCodeSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void settingPassword(String phone, String password, String code) {
        MvpRef.get().showLoadings();
        api.setSecondPassword(phone, password, code, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                ToastUtils.showShort("二级密码设置成功");
                MvpRef.get().settingPasswordSuess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }


}
