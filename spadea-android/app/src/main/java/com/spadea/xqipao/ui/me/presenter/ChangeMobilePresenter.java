package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.ChangeMobileContacts;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.presenter
 * 创建人 王欧
 * 创建时间 2020/3/27 10:01 AM
 * 描述 describe
 */
public class ChangeMobilePresenter extends BasePresenter<ChangeMobileContacts.View> implements ChangeMobileContacts.IChangeMobilePre {
    public ChangeMobilePresenter(ChangeMobileContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void smsCode(String mobile, int type) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.showShort("请输入手机号");
            return;
        }
        MvpRef.get().showLoadings();
        api.sendCode(MyApplication.getInstance().getToken(), mobile, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().getSmsCodeSuccess(mobile);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
