package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.UserSkillItem;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.UserSkillContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class UserSkillPresenter extends BasePresenter<UserSkillContacts.View> implements UserSkillContacts.IUserSkillPre {
    public UserSkillPresenter(UserSkillContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getSKills(String userId) {
        api.getSkillListByUserId(userId, new JavaBaseObserver<List<UserSkillItem>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<UserSkillItem> userSkillItems) {
                MvpRef.get().userSkills(userSkillItems);
            }

            @Override
            public void onComplete() {

            }
        });

    }
}