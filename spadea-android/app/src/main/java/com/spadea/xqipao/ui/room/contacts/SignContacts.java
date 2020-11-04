package com.spadea.xqipao.ui.room.contacts;

import android.app.Activity;

import com.spadea.xqipao.data.SignHistoryResp;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.contacts
 * 创建人 王欧
 * 创建时间 2020/4/25 12:14 PM
 * 描述 describe
 */
public class SignContacts {
    public interface View extends IView<Activity> {
        void signInSuccess();

        void signHistory(SignHistoryResp resp);

        void rewardList(List<SignHistoryResp.RewardData> list);
    }

    public interface SignPre extends IPresenter {
        void getSignHistory();

        void getRewordData();

        void signIn();
    }
}
