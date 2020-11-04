package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.ChangeMobileVerifyContacts;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.presenter
 * 创建人 王欧
 * 创建时间 2020/3/26 5:12 PM
 * 描述 describe
 */
public class ChangeMobileVerifyPresenter extends BasePresenter<ChangeMobileVerifyContacts.View> implements ChangeMobileVerifyContacts.IChangeMobileVerifyPre {
    public ChangeMobileVerifyPresenter(ChangeMobileVerifyContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void bindingMobile(String mobile, String code) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.showShort("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShort("请输入验证码");
            return;
        }
        MvpRef.get().showLoadings();
        api.bindMobile(mobile, code, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().bindSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
