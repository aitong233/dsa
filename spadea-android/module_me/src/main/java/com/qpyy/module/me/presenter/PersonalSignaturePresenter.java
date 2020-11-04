package com.qpyy.module.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.contacts.PersonalSignatureContacts;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class PersonalSignaturePresenter extends BasePresenter<PersonalSignatureContacts.View> implements PersonalSignatureContacts.IPersonalSignaturePre {

    public PersonalSignaturePresenter(PersonalSignatureContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void updataPersonalSignature(String text) {
        MvpRef.get().showLoadings();
        Map<String, String> map = new HashMap<>();
        map.put("signature", text);
        ApiClient.getInstance().userUpdate(map, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().updataSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
