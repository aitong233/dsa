package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.ChangePwdContacts;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.presenter
 * 创建人 王欧
 * 创建时间 2020/3/27 11:06 AM
 * 描述 describe
 */
public class ChangePwdPresenter extends BasePresenter<ChangePwdContacts.View> implements ChangePwdContacts.IChangePwdPre {


    public ChangePwdPresenter(ChangePwdContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void resetPassword(String mobile, String pwd, String code) {
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShort("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showShort("请输入密码");
            return;
        }
        MvpRef.get().showLoadings();
        api.resetPassword(mobile, code, pwd, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().success();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void smsCode(String mobile) {
        MvpRef.get().showLoadings();
        api.sendCode(MyApplication.getInstance().getToken(), mobile, 2, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().smsCodeSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
