package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.bean.CheckTxtResp;
import com.qpyy.libcommon.db.DbController;
import com.qpyy.libcommon.db.table.MusicTable;
import com.qpyy.libcommon.http.RetrofitClient;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.ApplyWheatWaitModel;
import com.spadea.xqipao.data.EmojiModel;
import com.spadea.xqipao.data.FmApplyWheatResp;
import com.spadea.xqipao.data.PitCountDownBean;
import com.spadea.xqipao.data.ProtectedItemBean;
import com.spadea.xqipao.data.RoomDetailBean;
import com.spadea.xqipao.data.RoomPollModel;
import com.spadea.xqipao.data.api.APIException;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.LiveRoomContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class LiveRoomPresenter extends BasePresenter<LiveRoomContacts.View> implements LiveRoomContacts.ILiveRoomPre {

//    private boolean isApplyWheating;

    public LiveRoomPresenter(LiveRoomContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void roomGetIn(String roomId, String password) {
        MvpRef.get().showLoadings();
        api.roomGetIn(roomId, password, new BaseObserver<RoomDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomDetailBean roomDetailBean) {
                try {
                    MvpRef.get().setRoomData(roomDetailBean, true);
                } catch (NullPointerException e) {
                    MvpRef.get().roomEnterFail();
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (e instanceof APIException) {
                    APIException apiException = (APIException) e;
                    if (apiException.getCode() == 6) {
                        MvpRef.get().roomAuthSuccess();
                    } else {
                        ToastUtils.showShort(apiException.getMessage());
                        MvpRef.get().roomEnterFail();
                    }
                } else {
                    ToastUtils.showShort("数据错误");
                    MvpRef.get().roomEnterFail();
                }
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void addRoomCollect(String roomId) {
        api.addRoomCollect(MyApplication.getInstance().getToken(), roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().addRoomCollectSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void removeFavorite(String roomId) {
        api.removeFavorite(MyApplication.getInstance().getToken(), roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().removeFavoriteSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 下麦
     *
     * @param roomId
     */
    @Override
    public void downWheat(String roomId) {
        api.downWheat(MyApplication.getInstance().getToken(), roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 上麦
     *
     * @param roomId
     * @param pitNumber
     */
    @Override
    public void applyWheat(String roomId, String pitNumber) {
//        if (isApplyWheating){
//            return;
//        }
//        isApplyWheating=true;
        api.applyWheat(MyApplication.getInstance().getToken(), roomId, pitNumber, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                //上麦成功直接更新数据
//                EventBus.getDefault().post(new ApplyWheatSuccessEvent(pitNumber));
            }

            @Override
            public void onComplete() {
//                isApplyWheating=false;
            }
        });
    }

    /**
     * 申请上麦
     *
     * @param roomId
     * @param pitNumber
     */
    @Override
    public void applyWheatWait(String roomId, String pitNumber) {
        api.applyWheatWait(MyApplication.getInstance().getToken(), roomId, pitNumber, new BaseObserver<ApplyWheatWaitModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(ApplyWheatWaitModel applyWheatWaitModel) {
                MvpRef.get().applyWheatWaitSuccess(applyWheatWaitModel.getState(), applyWheatWaitModel.getPit_number());
            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * 关注用户
     *
     * @param userId
     * @param type
     */
    @Override
    public void follow(String userId, int type) {
        if (isViewAttach()) {
            api.follow(MyApplication.getInstance().getToken(), userId, type, new BaseObserver<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(String s) {
                    if (type == 1) {
                        ToastUtils.showShort("关注成功");
                    } else {
                        ToastUtils.showShort("取消关注成功");
                    }
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }


    @Override
    public int getLocalMusicCount() {
        return DbController.getInstance(mContext).queryMUsicCount();
    }

    @Override
    public List<MusicTable> getLovalMusicData() {
        return DbController.getInstance(mContext).queryMusicListAll();
    }

    @Override
    public void quitRoom(String roomId) {
        if (isViewAttach()) {
            MvpRef.get().showLoadings();
            api.quit(MyApplication.getInstance().getToken(), roomId, new BaseObserver<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(String s) {
                    MvpRef.get().quitRoomSuccess();
                }

                @Override
                public void onComplete() {
                    MvpRef.get().disLoadings();
                }
            });
        }
    }

    @Override
    public void setRoomPassword(String roomId, String password) {
        if (isViewAttach()) {
            api.updatePassword(roomId, password, new BaseObserver<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(String s) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    @Override
    public void roomPoll(String roomId, boolean b, EmojiModel emojiModel) {
        if (isViewAttach()) {
            api.roomPoll(roomId, 1, new BaseObserver<RoomPollModel>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(RoomPollModel roomPollModel) {
                    MvpRef.get().setRoomPoll(roomPollModel, b, emojiModel);
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }


    @Override
    public void getInRoomInfo(String roomId) {
        if (isViewAttach()) {
            api.getInRoomInfo(roomId, new BaseObserver<RoomDetailBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(RoomDetailBean roomDetailBean) {
                    MvpRef.get().setRoomData(roomDetailBean, false);
                }

                @Override
                public void onComplete() {

                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    if (e instanceof APIException) {
                        APIException apiException = (APIException) e;
                        if (apiException.getCode() == 6) {
                            MvpRef.get().roomAuthSuccess();
                        } else {
                            ToastUtils.showShort(apiException.getMessage());
                            MvpRef.get().roomEnterFail();
                        }
                    } else {
                        ToastUtils.showShort("数据错误");
                        MvpRef.get().roomEnterFail();
                    }
                }
            });
        }
    }

    @Override
    public void pitCountDown(String roomId, String pitNumber, String time) {
        api.pitCountDown(roomId, pitNumber, time, new BaseObserver<PitCountDownBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(PitCountDownBean pitCountDownBean) {
                MvpRef.get().piCountDownSuccess(roomId, pitNumber, String.valueOf(pitCountDownBean.getTime()));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void applyWheatFm(String roomId, String pitNumber) {
        api.applyWheatFm(roomId, pitNumber, new BaseObserver<FmApplyWheatResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(FmApplyWheatResp resp) {
                resp.setPit_number(pitNumber);
                MvpRef.get().applyWheatFmCallback(resp);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void openFmProtected(String roomId, String type, String userId) {
        api.openFmProtected(roomId, type, userId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                ToastUtils.showShort("开通成功");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getProtectedList(int type) {
        api.getProtectedList(new BaseObserver<List<ProtectedItemBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<ProtectedItemBean> list) {
                MvpRef.get().protectedList(list, type);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void filterMessage(String msg) {
        RetrofitClient.getInstance().checkTxt(msg,"3", new com.qpyy.libcommon.http.BaseObserver<CheckTxtResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CheckTxtResp checkTxtResp) {
                if (checkTxtResp.getResult() == 0) {
                    MvpRef.get().sendTextMsg(msg);
                } else {
                    ToastUtils.showShort("涉嫌违规");
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }


}
