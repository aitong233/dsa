package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.SkillSection;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class SkillSelectContacts {


    public interface View extends IView<Activity> {
        void skillKinds(List<SkillSection> sections);

        void skillStatus(int status,SkillSection.Item item);

    }

    public interface ISkillSelectPre extends IPresenter {
        void getSkillKinds();

        void checkSkillStatus(SkillSection.Item item);
    }
}