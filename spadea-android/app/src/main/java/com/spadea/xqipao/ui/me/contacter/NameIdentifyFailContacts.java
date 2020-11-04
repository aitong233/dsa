package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.SkillApplyModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class NameIdentifyFailContacts {


    public interface View extends IView<Activity> {
        void applyInfo(SkillApplyModel info);
    }

    public interface INameIdentifyFailPre extends IPresenter {
        void getApply(int skillId);
    }
}