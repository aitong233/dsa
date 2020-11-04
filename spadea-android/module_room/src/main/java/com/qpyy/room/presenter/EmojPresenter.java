package com.qpyy.room.presenter;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.CardResultBean;
import com.qpyy.room.bean.ExclusiveEmojiResp;
import com.qpyy.room.bean.RoomPollModel;
import com.qpyy.room.contacts.EmojContacts;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class EmojPresenter extends BasePresenter<EmojContacts.View> implements EmojContacts.IEmojPre {
    public EmojPresenter(EmojContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getEmojData(int isGameRoom) {
        ApiClient.getInstance().faceList(new BaseObserver<List<ExclusiveEmojiResp>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<ExclusiveEmojiResp> exclusiveEmojiResps) {
                ExclusiveEmojiResp emojiModel = new ExclusiveEmojiResp();
                emojiModel.setName("抽签");
                exclusiveEmojiResps.add(0, emojiModel);
                if (isGameRoom == 1) {
                    String[] strings = {"掷骰子", "发牌x5", "发牌x3", "发牌x2", "发牌x1"};
                    for (int i = 0; i < 5; i++) {
                        ExclusiveEmojiResp emojiModel1 = new ExclusiveEmojiResp();
                        emojiModel1.setName(strings[i]);
                        exclusiveEmojiResps.add(0, emojiModel1);
                    }
                }
                MvpRef.get().setEmojData(splitList(exclusiveEmojiResps, 15));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getExclusiveEmojData() {
        ApiClient.getInstance().faceSpecial(new BaseObserver<List<ExclusiveEmojiResp>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<ExclusiveEmojiResp> exclusiveEmojiResps) {
                MvpRef.get().setEmojData(splitList(exclusiveEmojiResps, 15));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void sendFace(String roomId, String face_id, String pit, int type) {
        ApiClient.getInstance().sendFace(roomId, face_id, pit, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().sendSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void roomPoll(String roomId, String pitNumber) {
        ApiClient.getInstance().roomPoll(roomId, "0".equals(pitNumber) ? 0 : 1, pitNumber, new BaseObserver<RoomPollModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomPollModel roomPollModel) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void ranCards(String count) {

        ApiClient.getInstance().ranCards(SpUtils.getToken(), count, new BaseObserver<CardResultBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CardResultBean cardResultBean) {
                ToastUtils.showShort("发牌结果已发送至公屏");
                MvpRef.get().sendSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void ranTouzi() {
        ApiClient.getInstance().ranTouzi(SpUtils.getToken(), new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String cardResultBean) {
                ToastUtils.showShort("投掷结果已发送至公屏");
                MvpRef.get().sendSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private List<List<ExclusiveEmojiResp>> splitList(List<ExclusiveEmojiResp> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return new ArrayList<>();
        }
        List<List<ExclusiveEmojiResp>> result = new ArrayList<List<ExclusiveEmojiResp>>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<ExclusiveEmojiResp> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
