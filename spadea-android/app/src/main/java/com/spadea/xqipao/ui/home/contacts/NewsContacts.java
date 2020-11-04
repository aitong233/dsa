package com.spadea.xqipao.ui.home.contacts;

import android.support.v4.app.FragmentActivity;

import com.hyphenate.chat.EMConversation;
import com.spadea.xqipao.data.NewsModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class NewsContacts {

    public interface View extends IView<FragmentActivity> {
        void userNewsSuccess(NewsModel newsModel);
        void conversationComplete(List<EMConversation> list);
    }

    public interface INewsPre extends IPresenter {
        void userNews();
        void refreshConversation();
    }
}
