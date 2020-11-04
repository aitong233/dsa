package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.MyOrderSwitch;
import com.spadea.xqipao.data.SkillPriceSet;
import com.spadea.xqipao.data.SkillSetting;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.ApplySettingContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ApplySettingPresenter extends BasePresenter<ApplySettingContacts.View> implements ApplySettingContacts.IApplySettingPre {
    public ApplySettingPresenter(ApplySettingContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void forbidUnAuth(boolean forbid) {
        MvpRef.get().showLoadings();
        api.skillForbidUnAuth(forbid ? 1 : 0, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().forbidUnAuthSuccess(forbid);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                MvpRef.get().forbidUnAuthSuccess(!forbid);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void fastAnswer(boolean fastAnswer) {
        MvpRef.get().showLoadings();
        api.skillFastAnswer(fastAnswer ? 1 : 0, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().fastAnswerSuccess(fastAnswer);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                MvpRef.get().fastAnswerSuccess(!fastAnswer);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getOrderSwitch() {
        api.getOrderSwitch(new JavaBaseObserver<MyOrderSwitch>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(MyOrderSwitch orderSwitch) {
                MvpRef.get().myOrderSwitch(orderSwitch);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getSkillList() {
        api.getUserSkills(new JavaBaseObserver<List<SkillSetting>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<SkillSetting> list) {
                MvpRef.get().skillList(list);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getSkillPriceList(int skillId, int applyId) {
        api.getSkillPriceList(skillId, new JavaBaseObserver<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<String> list) {
                MvpRef.get().skillPriceList(list, applyId);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void updateSkillPrice(SkillPriceSet set) {
        api.updateSkillPrice(set, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().updatePriceSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void updateOrderSwitch(MyOrderSwitch myOrderSwitch) {
        api.updateOrderSwitch(myOrderSwitch, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
//                ToastUtils.showShort("设置成功");
                MvpRef.get().updateOrderSwitchSuccess(myOrderSwitch.getFastAnswer() == 1, myOrderSwitch.getForbidSomeone() == 1);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
//                ToastUtils.showShort("设置失败");
                MvpRef.get().updateOrderSwitchSuccess(!(myOrderSwitch.getFastAnswer() == 1), !(myOrderSwitch.getForbidSomeone() == 1));
            }

            @Override
            public void onComplete() {
            }
        });
    }
}