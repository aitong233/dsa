package com.spadea.xqipao.ui.me.contacter;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.UserSkillItem;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class UserSkillContacts {


    public interface View extends IView<FragmentActivity> {
        void userSkills(List<UserSkillItem> userSkillItems);
    }

    public interface IUserSkillPre extends IPresenter {
        void getSKills(String userId);
    }
}