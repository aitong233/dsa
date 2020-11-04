package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.EmojiModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class EmojContacts {

    public interface View extends IView<FragmentActivity> {
        void setFraceListData(List<EmojiModel> data);
    }


    public interface IEmojPre extends IPresenter {
        void getFaceList();
    }

}
