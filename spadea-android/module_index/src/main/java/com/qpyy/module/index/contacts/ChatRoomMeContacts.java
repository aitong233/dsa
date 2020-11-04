package com.qpyy.module.index.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.index.bean.AttentionResp;
import com.qpyy.module.index.bean.ManageRoomResp;
import com.qpyy.module.index.bean.MyFootResp;
import com.qpyy.module.index.bean.RecommendAttentionResp;
import com.qpyy.module.index.bean.RecordSection;
import com.qpyy.module.index.bean.SearchResp;

import java.util.List;

public final class ChatRoomMeContacts {

    public interface View extends IView<Activity> {
        void setAttentionListData(List<AttentionResp> data);

        void setRecommendAttentionList(List<RecommendAttentionResp> data);

        void ghostAttentionSuccess();

        void setManageData(List<ManageRoomResp> data);

        void removeManageSuccess(int postion);

        void setMyFootData(List<MyFootResp> data,int page);

        void delfootSuccess();

        void finishRefresh();

        void removeFavoriteSuccess(int position);
    }

    public interface ISearchPre extends IPresenter {
        void getAttentionList();

        void getRecommendAttentionList();

        void ghostAttention(List<RecommendAttentionResp> data);

        void getManageLists();

        void removeManage(String id, int postion);

        void getMyFoot(int page);

        void delfoot();

        void removeFavorite(String roomId,int position);
    }
}
