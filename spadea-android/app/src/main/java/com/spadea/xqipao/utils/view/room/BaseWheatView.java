package com.spadea.xqipao.utils.view.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.qpyy.libcommon.bean.UserBean;
import com.spadea.xqipao.widget.wheat.BaseWheatOnClickListener;
import com.spadea.xqipao.data.FaceBean;
import com.spadea.xqipao.data.RoomPitBean;

public abstract class BaseWheatView extends FrameLayout {

    protected String mPitNumber = "9";
    protected RoomPitBean mRoomPitBean;
    protected Context mContext;
    protected boolean isHostWheat = false;
    protected BaseWheatOnClickListener mBaseWheatOnClickListener;


    public BaseWheatView(@NonNull Context context) {
        super(context);
    }

    public BaseWheatView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseWheatView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void showVolumeTips(boolean b);

    public abstract void setData(RoomPitBean data);

    public abstract void showXd(int cardiac);

    public abstract void startGame();

    public abstract void openGame(String data1, String data2, String data3);

    public abstract void overGame();

    public abstract void setExpression(FaceBean data);

    public abstract void showGift(String url);


    public void setBaseWheatOnClickListener(BaseWheatOnClickListener mBaseWheatOnClickListener) {
        this.mBaseWheatOnClickListener = mBaseWheatOnClickListener;
    }

    public abstract void deleteCardiac();

    public abstract void closePit(String action);

    public abstract void setShutup(int shutup);

    public abstract void setWheatCardiac(String cardiac);

    public abstract void countDownTime(String time);

    public void setHostWheat(boolean hostWheat) {
        isHostWheat = hostWheat;
    }

    public void setData(UserBean userBean,String pitNumber) {
        if (mRoomPitBean!=null){
            mRoomPitBean.setNickname(userBean.getNickname());
            mRoomPitBean.setHead_picture(userBean.getHead_picture());
            mRoomPitBean.setPit_number(pitNumber);
            mRoomPitBean.setUser_id(userBean.getUser_id());
            mRoomPitBean.setSex(String.valueOf(userBean.getSex()));
            mRoomPitBean.setRank_info(userBean.getRank_info());
            setData(mRoomPitBean);
        }
    }
}
