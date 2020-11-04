package com.spadea.xqipao.utils.view.room.template;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qpyy.libcommon.bean.UserBean;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.xqipao.widget.wheat.BaseWheatOnClickListener;
import com.spadea.xqipao.data.FaceBean;
import com.spadea.xqipao.data.RoomPitBean;
import com.spadea.xqipao.utils.view.MarqueeTextView;
import com.spadea.xqipao.utils.view.room.BaseWheatView;
import com.spadea.xqipao.utils.view.room.ContributionView;
import com.spadea.xqipao.utils.view.room.DefaultWheatView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StationRoomView extends ConstraintLayout implements BaseWheatOnClickListener {


    @BindView(R.id.dwv_host)
    DefaultWheatView dwvHost;
    @BindView(R.id.df_wheat1)
    DefaultWheatView dfWheat1;
    @BindView(R.id.df_wheat2)
    DefaultWheatView dfWheat2;
    @BindView(R.id.df_wheat3)
    DefaultWheatView dfWheat3;
    @BindView(R.id.df_wheat4)
    DefaultWheatView dfWheat4;
    @BindView(R.id.contribution_view)
    ContributionView contributionView;
    @BindView(R.id.tv_host_name)
    TextView mTvHostName;
    @BindView(R.id.tv_ad)
    MarqueeTextView mTvAd;


    private Context mContext;
    private StationRoomOnClickListener mStationRoomOnClickListener;
    private Map<String, BaseWheatView> map = new HashMap<>();


    public StationRoomView(@NonNull Context context) {
        this(context, null);
    }

    public StationRoomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StationRoomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initData();
        initListener();
    }


    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(getContext()).inflate(R.layout.view_room_station, this);
        ButterKnife.bind(this);
    }

    private void initData() {
        dwvHost.setHostWheat(true);
        map.put("5", dfWheat1);
        map.put("6", dfWheat2);
        map.put("7", dfWheat3);
        map.put("8", dfWheat4);
        map.put("9", dwvHost);
    }

    private void initListener() {
        dwvHost.setBaseWheatOnClickListener(this);
        dfWheat1.setBaseWheatOnClickListener(this);
        dfWheat2.setBaseWheatOnClickListener(this);
        dfWheat3.setBaseWheatOnClickListener(this);
        dfWheat4.setBaseWheatOnClickListener(this);
    }

    public void setPlaying(String playing) {
        if (TextUtils.isEmpty(playing)) {
            mTvAd.setVisibility(GONE);
        } else {
            mTvAd.setVisibility(VISIBLE);
            mTvAd.setText(playing);
        }
    }

    public void setWheatData(List<RoomPitBean> data) {
        for (RoomPitBean item : data) {
            BaseWheatView baseWheatView = map.get(item.getPit_number());
            if (baseWheatView != null) {
                baseWheatView.setData(item);
            }
            if ("9".equals(item.getPit_number()) && !TextUtils.isEmpty(item.getUser_id()) && !TextUtils.isEmpty(item.getNickname())) {
                mTvHostName.setVisibility(VISIBLE);
                mTvHostName.setText(item.getNickname());
            } else {
                mTvHostName.setVisibility(INVISIBLE);
            }
        }
    }

    public void setSelfWheatData(String pitNumber){
        if (!TextUtils.isEmpty(pitNumber)){
            UserBean userBean= MyApplication.getInstance().getUser();
            BaseWheatView baseWheatView = map.get(pitNumber);
            if (baseWheatView != null) {
                baseWheatView.setData(userBean,pitNumber);
            }
            if ("9".equals(pitNumber)) {
                mTvHostName.setVisibility(VISIBLE);
                mTvHostName.setText(userBean.getNickname());
            } else {
                mTvHostName.setVisibility(INVISIBLE);
            }
        }
    }


    public void showWheatCardiac(int cardiac) {
        dfWheat1.showXd(cardiac);
        dfWheat2.showXd(cardiac);
        dfWheat3.showXd(cardiac);
        dfWheat4.showXd(cardiac);
        dwvHost.showXd(cardiac);
    }


    public void setWheatCardiac(String pitNum, String cardiac) {
        BaseWheatView baseWheatView = map.get(pitNum);
        if (baseWheatView != null) {
            baseWheatView.setWheatCardiac(cardiac);
        }
    }


    public void showWheatVolumeTips(String pitNum, boolean b) {
        BaseWheatView baseWheatView = map.get(pitNum);
        if (baseWheatView != null) {
            baseWheatView.showVolumeTips(b);
        }
    }

    public void setWheatExpression(FaceBean data) {
        BaseWheatView baseWheatView = map.get(data.getPit());
        if (baseWheatView != null) {
            baseWheatView.setExpression(data);
        }
    }

    public void showWheatGift(int postion, String animation) {
        BaseWheatView baseWheatView = map.get(String.valueOf(postion));
        if (baseWheatView != null) {
            baseWheatView.showGift(animation);
        }
    }


    public void showWheatGame(String pitNum) {
        BaseWheatView baseWheatView = map.get(pitNum);
        if (baseWheatView != null) {
            baseWheatView.startGame();
        }
    }

    public void overWheatGame(String pitNum) {
        BaseWheatView baseWheatView = map.get(pitNum);
        if (baseWheatView != null) {
            baseWheatView.overGame();
        }
    }

    public void setRoomContribution(String data) {
        contributionView.setData(data);
    }


    public void openWheatGame(String pitNum, String qiu1, String qiu2, String qiu3) {
        BaseWheatView baseWheatView = map.get(pitNum);
        if (baseWheatView != null) {
            baseWheatView.openGame(qiu1, qiu2, qiu3);
        }
    }

    public void addEmotionRoomOnClickListener(StationRoomOnClickListener emotionRoomOnClickListener) {
        this.mStationRoomOnClickListener = emotionRoomOnClickListener;
    }

    @Override
    public void wheatAdd(boolean isHostWheat, String pitNumber) {
        if (mStationRoomOnClickListener != null) {
            mStationRoomOnClickListener.wheatAdd(isHostWheat, pitNumber);
        }
    }

    @Override
    public void wheatLock(boolean isHostWheat, String pitNumber) {
        if (mStationRoomOnClickListener != null) {
            mStationRoomOnClickListener.wheatLock(isHostWheat, pitNumber);
        }
    }

    @Override
    public void wheatHeadPicture(boolean isHostWheat, String userId) {
        if (mStationRoomOnClickListener != null) {
            mStationRoomOnClickListener.wheatHeadPicture(isHostWheat, userId);
        }
    }


    public void deleteCardiac(String pitNumber) {
        BaseWheatView baseWheatView = map.get(pitNumber);
        if (baseWheatView != null) {
            baseWheatView.deleteCardiac();
        }
    }

    public void deleteAllCardiac() {
        dfWheat1.deleteCardiac();
        dfWheat2.deleteCardiac();
        dfWheat3.deleteCardiac();
        dfWheat4.deleteCardiac();
        dwvHost.deleteCardiac();
    }

    public void closePit(String pitNumber, String action) {
        BaseWheatView baseWheatView = map.get(pitNumber);
        if (baseWheatView != null) {
            baseWheatView.closePit(action);
        }
    }


    public void roomShutup(String pitNumber, int shutup) {
        BaseWheatView baseWheatView = map.get(pitNumber);
        if (baseWheatView != null) {
            baseWheatView.setShutup(shutup);
        }
    }

    public void pitCountDown(String pitNumber, String time) {
        BaseWheatView baseWheatView = map.get(pitNumber);
        if (baseWheatView != null) {
            baseWheatView.countDownTime(time);
        }
    }

    @OnClick({R.id.iv_host, R.id.iv_guard, R.id.tv_ad})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_host:
                if ((mStationRoomOnClickListener != null)) {
                    mStationRoomOnClickListener.hostListClick();
                }
                break;
            case R.id.iv_guard:
                if ((mStationRoomOnClickListener != null)) {
                    mStationRoomOnClickListener.guardListClick();
                }
                break;
            case R.id.tv_ad:
                if ((mStationRoomOnClickListener != null)) {
                    mStationRoomOnClickListener.playingClick();
                }
                break;
        }
    }
}
