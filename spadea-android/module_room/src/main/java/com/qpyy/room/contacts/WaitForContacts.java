package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.ApplyWheatUsersResp;

import java.util.List;

public final class WaitForContacts {


    public interface View extends IView<Activity> {

        void agreeApplySuccess(int postion);

        void deleteApplySuccess(int postion);

        void agreeApplyAllSuccess();

        void setApplyWheatUsersData(List<ApplyWheatUsersResp.ListData> data);

        void setUserCount(String count);
    }

    public interface IWaitForPre extends IPresenter {
        void agreeApply(String id, String roomId, int postion);

        void deleteApply(String id, String roomId, int postion);

        void agreeApplyAll(String roomId);

        void applyWheatUsers(String roomId);
    }


}
