package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.utilcode.ObjectUtils;
import com.spadea.xqipao.data.SkillSection;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.SkillSelectContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class SkillSelectPresenter extends BasePresenter<SkillSelectContacts.View> implements SkillSelectContacts.ISkillSelectPre {
    public SkillSelectPresenter(SkillSelectContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getSkillKinds() {
        MvpRef.get().showLoadings();
        api.getSkillKinds(MyApplication.getInstance().getUser().getUser_id(), new JavaBaseObserver<List<SkillSection>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<SkillSection> sections) {
                if (!ObjectUtils.isEmpty(sections)) {
                    MvpRef.get().skillKinds(sections);
                }
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void checkSkillStatus(SkillSection.Item item) {
        api.checkSkillStatus(item.getId(), new JavaBaseObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(Integer integer) {
                MvpRef.get().skillStatus(integer, item);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}