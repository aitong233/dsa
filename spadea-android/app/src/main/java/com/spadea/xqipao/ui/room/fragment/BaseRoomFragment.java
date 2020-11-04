package com.spadea.xqipao.ui.room.fragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.spadea.yuyin.ui.fragment1.trans.TransEaseChatFragment;
import com.spadea.xqipao.data.FaceBean;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;


public abstract class BaseRoomFragment<P extends IPresenter> extends BaseFragment<P> implements IView<FragmentActivity> {


    protected boolean externalRelease = true;
    public RoomFragmentListener mRoomFragmentListener;
    protected TransEaseChatFragment mTransEaseChatFragment;
    private boolean isWheat = false;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RoomFragmentListener) {
            mRoomFragmentListener = (RoomFragmentListener) context;
        }
    }

    public abstract void setWheatData(List housePitBeanList);


    public abstract void addChatRoom(String toChatUsername, int role);

    public abstract void sendMesg(String content, int role);

    public abstract void sendActionMessage(String text);

    public abstract void sendOperationMessage(String text);

    public abstract void setExpression(FaceBean data);


    public abstract void leaveChatRoom();

    public abstract void sendWelcomeMessage(String nickname);

    public abstract void sendGreetingMessage(String greeting);

    public abstract void sendOfficialNoticeMessage(String officialNotice);

    public abstract void sendText(String s, int role);

    public abstract void sendEmojiMessage(String id, String picture, String name, String special, String valueOf, boolean b, int role);

    public abstract void sendDrawMessage(String randomNum, int role);

    public abstract void sendGiftMessage(String userName, String giftId, String giftPic, String giftName, String giftPrice, String giftSpectial, String giftMum, String pits);

    public abstract void showGift(int postion, String animation);

    public abstract void showView();

    public abstract void setContribution(String contribution);

    public abstract void showGame(String pitNum);

    public abstract void overGame(String pitNum);

    public abstract void openGame(String pitNum, String qiu1, String qiu2, String qiu3);

    public abstract void showCardiac(int cardiac);

    public abstract void setMaiXu(String wheat);

    public abstract void setMaiXuCount(String count);

    public abstract void setUserData(int pit);


    public boolean isWheat() {
        return isWheat;
    }

    public void setWheat(boolean wheat) {
        isWheat = wheat;
    }

    public void sendOpenGameQiuMsg(String pitNum, String content, String qiu1, String qiu2, String qiu3) {

    }


    public void sendStartGameCmdMsg(String pitNum) {

    }

    public void sendOverGameCmdMsg(String pitNum) {
    }

    public abstract void showVolumeTips(String pitNum, boolean b);

    public abstract void setWheatState(int state);


    public abstract void clearPublic();

    public abstract void roomDeleteCardiac(String pitNumber);

    public abstract void roomDeleteAllCardiac();

    public abstract void roomClosePit(String pitNumber, String action);

    public abstract void showBallGame(int showBallGame);

    public abstract void roomShutup(String pitNumber, int shutup);

    public abstract void setWheatCardiac(String pitNum, String cardiac);

    public abstract void showMore(boolean b);

    public abstract void showPitCountDown(String pitNum, String time);

    public abstract void setShowCat(int showCat);

}
