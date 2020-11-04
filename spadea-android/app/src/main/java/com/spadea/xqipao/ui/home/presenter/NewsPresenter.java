package com.spadea.xqipao.ui.home.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;

import com.blankj.utilcode.util.ThreadUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.orhanobut.logger.Logger;
import com.spadea.xqipao.data.NewsModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.home.contacts.NewsContacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class NewsPresenter extends BasePresenter<NewsContacts.View> implements NewsContacts.INewsPre {

    public NewsPresenter(NewsContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void userNews() {
        api.userNews(new BaseObserver<NewsModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(NewsModel newsModel) {
                MvpRef.get().userNewsSuccess(newsModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void refreshConversation() {
        Logger.e("refreshConversation");
        ThreadUtils.executeByCached(new ThreadUtils.SimpleTask<List<EMConversation>>() {
            @Override
            public List<EMConversation> doInBackground() throws Throwable {
                return loadConversationList();
            }

            @Override
            public void onSuccess(List<EMConversation> result) {
                if (isViewAttach()) {
                    Logger.e("refreshConversationSuccess");
                    MvpRef.get().conversationComplete(result);
                }
            }
        });
    }

    private List<EMConversation> loadConversationList() {
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        List<Pair<Long, EMConversation>> sortList = new ArrayList<Pair<Long, EMConversation>>();
        for (EMConversation conversation : conversations.values()) {
            if (conversation.getAllMessages().size() != 0) {
                if (conversation.getType() == EMConversation.EMConversationType.Chat) {
                    sortList.add(new Pair<Long, EMConversation>(conversation.getLastMessage().getMsgTime(), conversation));
                }
            }
        }

        try {
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<EMConversation> list = new ArrayList<EMConversation>();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            EMConversation emConversation = sortItem.second;
            if (!TextUtils.isEmpty(emConversation.getExtField())) {
                list.add(emConversation);
            }
        }
        return list;
    }

    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() {
            @Override
            public int compare(final Pair<Long, EMConversation> con1, final Pair<Long, EMConversation> con2) {
                if (con1.first.equals(con2.first)) {
                    return 0;
                } else if (con2.first.longValue() > con1.first.longValue()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }
}
