package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.OnlineModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class OnlineContacts {

    public interface View extends IView<FragmentActivity> {
          void setRoomOnlineData(List<OnlineModel> data, int page);

          void roomOnlineComplete();
    }

    public interface IOnlinePre extends IPresenter {
        void roomOnline(String roomId,int page);
    }
}
