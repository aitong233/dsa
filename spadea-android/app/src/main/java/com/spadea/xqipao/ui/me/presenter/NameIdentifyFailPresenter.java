package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.SkillApplyModel;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.NameIdentifyFailContacts;

import io.reactivex.disposables.Disposable;

public class NameIdentifyFailPresenter extends BasePresenter<NameIdentifyFailContacts.View> implements NameIdentifyFailContacts.INameIdentifyFailPre {
    public NameIdentifyFailPresenter(NameIdentifyFailContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getApply(int skillId) {
        api.getQualificationApply(skillId, new JavaBaseObserver<SkillApplyModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(SkillApplyModel model) {
                MvpRef.get().applyInfo(model);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}