package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.MyOrderSwitch;
import com.spadea.xqipao.data.SkillPriceSet;
import com.spadea.xqipao.data.SkillSetting;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class ApplySettingContacts {


    public interface View extends IView<Activity> {
        void forbidUnAuthSuccess(boolean forbid);

        void fastAnswerSuccess(boolean fastAnswer);

        void myOrderSwitch(MyOrderSwitch orderSwitch);

        void skillList(List<SkillSetting> list);

        void skillPriceList(List<String> list, int applyId);

        void updatePriceSuccess();

        void updateOrderSwitchSuccess(boolean checkFastAnswer, boolean checkedAuth);
    }

    public interface IApplySettingPre extends IPresenter {
        void forbidUnAuth(boolean forbid);

        void fastAnswer(boolean fastAnswer);

        void getOrderSwitch();

        void getSkillList();

        void getSkillPriceList(int skillId,int applyId);

        void updateSkillPrice(SkillPriceSet set);

        void updateOrderSwitch(MyOrderSwitch myOrderSwitch);
    }
}