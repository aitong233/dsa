package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.AnchorRankingListResp;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.contacts
 * 创建人 王欧
 * 创建时间 2020/4/2 4:34 PM
 * 描述 describe
 */
public class RoomHostListContact {
    public interface  View extends IView<FragmentActivity>{
        void anchorRankingList(AnchorRankingListResp resp);
        void followUserSuccess(int position,int type);
    }

    public interface RoomHostListPre extends IPresenter{
        void getAnchorRankingList(String roomId, String type);
        void followUser(int position,String userId, int type);
    }
}
