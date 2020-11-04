package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.ProtectedRankingListResp;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.contacts
 * 创建人 王欧
 * 创建时间 2020/4/2 2:28 PM
 * 描述 describe
 */
public class RoomGuardListContact {
    public interface View extends IView<FragmentActivity> {
        void protectedRankingList(ProtectedRankingListResp resp);
    }

    public interface RoomGuardListPre extends IPresenter {
        void getProtectedRankingList(String roomId);
    }
}
