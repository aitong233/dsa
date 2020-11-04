package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.BlacListSectionBean;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.BlackListContact;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.presenter
 * 创建人 王欧
 * 创建时间 2020/4/1 2:02 PM
 * 描述 describe
 */
public class BlackListPresenter extends BasePresenter<BlackListContact.View> implements BlackListContact.BlackListPre {
    public BlackListPresenter(BlackListContact.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getBlackList(int page,String keyword) {
        api.userBlackList(page, keyword,new BaseObserver<List<BlacListSectionBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<BlacListSectionBean> listBeans) {
                MvpRef.get().blackList(listBeans);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void removeUser(String blackId, int position) {
        MvpRef.get().showLoadings();
        api.removeUserBlack(blackId, 2, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().userRemoved(position);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
