package com.spadea.xqipao.ui.room.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.FaceBean;
import com.spadea.xqipao.data.RoomBannerModel;
import com.spadea.xqipao.data.SignSwitchModel;
import com.spadea.xqipao.data.even.ApplyWheatSuccessEvent;
import com.spadea.xqipao.ui.room.presenter.RoomFragmentPresenter;
import com.spadea.xqipao.utils.view.room.template.StationRoomOnClickListener;
import com.spadea.xqipao.utils.view.room.template.StationRoomView;
import com.stx.xhb.xbanner.XBanner;
import com.spadea.yuyin.R;
import com.spadea.yuyin.ui.fragment1.trans.TransEaseChatFragment;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.ui.h5.BossGameDialog;
import com.spadea.xqipao.ui.room.contacts.RoomFragmentContacts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.fragment
 * 创建人 王欧
 * 创建时间 2020/4/7 11:04 AM
 * 描述 describe
 */
public class StationRoomFragment extends BaseRoomFragment<RoomFragmentPresenter> implements StationRoomOnClickListener, XBanner.OnItemClickListener, RoomFragmentContacts.View {


    private final static String TAG = StationRoomFragment.class.getName();


    @BindView(R.id.rl_public_screen)
    RelativeLayout rlPublicScreen;
    @BindView(R.id.iv_mesg)
    ImageView ivMesg;
    @BindView(R.id.iv_emoj)
    ImageView ivEmoj;
    @BindView(R.id.iv_external_release)
    ImageView ivExternalRelease;
    @BindView(R.id.iv_wheat)
    ImageView ivWheat;
    @BindView(R.id.iv_mores)
    ImageView ivMores;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.iv_gift)
    ImageView ivGift;
    @BindView(R.id.tv_mic_up)
    TextView tvMicUp;
    @BindView(R.id.cl)
    ConstraintLayout cl;
    @BindView(R.id.iv_qiu_game)
    ImageView ivQiuGame;
    @BindView(R.id.emojicon_room_view)
    StationRoomView stationRoomView;
    @BindView(R.id.banner)
    XBanner mXBanner;
    @BindView(R.id.iv_activity)
    ImageView mIvActivity;

    public static StationRoomFragment newInstance() {
        return new StationRoomFragment();
    }

    @Override
    protected RoomFragmentPresenter bindPresenter() {
        return new RoomFragmentPresenter(this, getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void applyWheatSuccess(ApplyWheatSuccessEvent event) {
        if (stationRoomView != null) {
            stationRoomView.setSelfWheatData(event.pitNumber);
        }
    }

    @Override
    protected void initData() {
        stationRoomView.addEmotionRoomOnClickListener(this);
        mXBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                RoomBannerModel bannerModel = (RoomBannerModel) model;
                ((ImageView) view).setImageResource(bannerModel.res);
            }
        });
        mXBanner.setOnItemClickListener(this);
        MvpPre.indexSwitch();
    }

    @Override
    protected void initView(View rootView) {
        rlPublicScreen = rootView.findViewById(R.id.rl_public_screen);
        ivMesg = rootView.findViewById(R.id.iv_mesg);
        ivEmoj = rootView.findViewById(R.id.iv_emoj);
        ivWheat = rootView.findViewById(R.id.iv_wheat);
        ivMores = rootView.findViewById(R.id.iv_mores);
        tvCount = rootView.findViewById(R.id.tv_count);
        ivGift = rootView.findViewById(R.id.iv_gift);
        tvMicUp = rootView.findViewById(R.id.tv_mic_up);
        ivQiuGame = rootView.findViewById(R.id.iv_qiu_game);
        stationRoomView = rootView.findViewById(R.id.emojicon_room_view);
        mXBanner = rootView.findViewById(R.id.banner);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_room_station;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Override
    public void setUserData(int pit) {
        if (pit == 0) {
            tvMicUp.setText("上麦");
            tvMicUp.setVisibility(View.GONE);
            ivWheat.setVisibility(View.GONE);
        } else {
            tvMicUp.setText("下麦");
            tvMicUp.setVisibility(View.VISIBLE);
            ivWheat.setVisibility(View.VISIBLE);
        }
    }

    public void setPlaying(String playing) {
        if (stationRoomView != null)
            stationRoomView.setPlaying(playing);
    }

    @Override
    public void setWheatData(List housePitBeanList) {
        if (stationRoomView != null)
            stationRoomView.setWheatData(housePitBeanList);
    }

    /**
     * 添加聊天室
     *
     * @param toChatUsername
     */
    @Override
    public void addChatRoom(String toChatUsername, int role) {
        mTransEaseChatFragment = TransEaseChatFragment.newInstance(toChatUsername, role);
        getChildFragmentManager().beginTransaction().replace(R.id.rl_public_screen, mTransEaseChatFragment).commitAllowingStateLoss();
    }

    @Override
    public void sendMesg(String content, int role) {
        mTransEaseChatFragment.sendText(content, role);
    }

    @Override
    public void sendActionMessage(String text) {
        if (mTransEaseChatFragment != null) {
            mTransEaseChatFragment.sendActionMessage(text);
        }
    }

    @Override
    public void sendOperationMessage(String text) {
        if (mTransEaseChatFragment != null) {
            mTransEaseChatFragment.sendOperationMessage(text);
        }
    }


    @Override
    public void sendOpenGameQiuMsg(String pitNum, String content, String qiu1, String qiu2, String qiu3) {
        if (mTransEaseChatFragment != null) {
            mTransEaseChatFragment.sendOpenGameQiuMessage(pitNum, content, qiu1, qiu2, qiu3);
        }
    }

    @Override
    public void sendStartGameCmdMsg(String pitNum) {
        if (mTransEaseChatFragment != null) {
            mTransEaseChatFragment.sendStartGameCmdMessage(pitNum);
        }
    }

    @Override
    public void sendOverGameCmdMsg(String pitNum) {
        if (mTransEaseChatFragment != null) {
            mTransEaseChatFragment.sendOverGameCmdMessage(pitNum);
        }
    }

    @Override
    public void showVolumeTips(String pitNum, boolean b) {
        if (stationRoomView != null)
            stationRoomView.showWheatVolumeTips(pitNum, b);
    }

    /**
     * 设置麦开关
     *
     * @param state
     */
    @Override
    public void setWheatState(int state) {
        ivWheat.setImageLevel(state);
    }

    @Override
    public void clearPublic() {
        if (mTransEaseChatFragment != null) {
            mTransEaseChatFragment.clearAllMessages();
        }
    }

    @Override
    public void roomDeleteCardiac(String pitNumber) {
        stationRoomView.deleteCardiac(pitNumber);
    }

    @Override
    public void roomDeleteAllCardiac() {
        stationRoomView.deleteAllCardiac();
    }

    @Override
    public void roomClosePit(String pitNumber, String action) {
        stationRoomView.closePit(pitNumber, action);
    }

    @Override
    public void showBallGame(int showBallGame) {
        if (showBallGame == 1) {
            ImageLoader.loadImage(mContext, ivQiuGame, Constant.URL.QIU_IMG);
            ivQiuGame.setVisibility(View.VISIBLE);
        } else {
            ivQiuGame.setVisibility(View.GONE);
        }
    }

    @Override
    public void roomShutup(String pitNumber, int shutup) {
        stationRoomView.roomShutup(pitNumber, shutup);
    }

    @Override
    public void setWheatCardiac(String pitNum, String cardiac) {
        stationRoomView.setWheatCardiac(pitNum, cardiac);
    }

    @Override
    public void showMore(boolean b) {
        if (b) {
            ivMores.setVisibility(View.VISIBLE);
        } else {
            ivMores.setVisibility(View.GONE);
        }
    }

    @Override
    public void showPitCountDown(String pitNum, String time) {
        stationRoomView.pitCountDown(pitNum, time);
    }

    @Override
    public void setShowCat(int showCat) {
        if (mXBanner != null) {
            ArrayList<RoomBannerModel> bannerModels = new ArrayList<>();
            if (showCat == 1) {
                bannerModels.add(new RoomBannerModel(R.mipmap.ic_room_cat));
            }
            if (MyApplication.getInstance().labor) {
                bannerModels.add(new RoomBannerModel(R.mipmap.ic_activity_51));
            }
            mXBanner.setBannerData(bannerModels);
        }
    }


    /**
     * 麦位动画
     *
     * @param data
     */
    @Override
    public void setExpression(FaceBean data) {
        stationRoomView.setWheatExpression(data);
    }

    /**
     * 设置麦序人数
     *
     * @param count
     */
    @Override
    public void setMaiXuCount(String count) {
        tvCount.setText(count);
    }


    @Override
    public void setMaiXu(String wheat) {
        if (wheat.equals("1")) {
            tvCount.setVisibility(View.GONE);
        } else {
            tvCount.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void leaveChatRoom() {
        if (mTransEaseChatFragment != null) {
            mTransEaseChatFragment.leaveChatRoom();
        }
    }

    /**
     * 发送欢迎语
     *
     * @param nickname
     */
    @Override
    public void sendWelcomeMessage(String nickname) {
        mTransEaseChatFragment.sendWelcomeMessage(nickname);
    }

    /**
     * 发送欢迎语
     *
     * @param greeting
     */
    @Override
    public void sendGreetingMessage(String greeting) {
        mTransEaseChatFragment.sendGreetingMessage(greeting);
    }

    /**
     * 发送官方通知
     *
     * @param officialNotice
     */
    @Override
    public void sendOfficialNoticeMessage(String officialNotice) {
        mTransEaseChatFragment.sendOfficialNoticeMessage(officialNotice);
    }

    /**
     * 发送消息
     */
    @Override
    public void sendText(String text, int role) {
        mTransEaseChatFragment.sendText(text, role);
    }

    /**
     * 发送表情消息
     *
     * @param id
     * @param picture
     * @param name
     * @param special
     * @param valueOf
     * @param b
     */
    @Override
    public void sendEmojiMessage(String id, String picture, String name, String special, String valueOf, boolean b, int role) {
        mTransEaseChatFragment.sendEmojiMessage(id, picture, name, special, valueOf, b, role);
    }

    /**
     * 发送表情消息
     *
     * @param randomNum
     */
    @Override
    public void sendDrawMessage(String randomNum, int role) {
        mTransEaseChatFragment.sendDrawMessage(randomNum, role);
    }

    /**
     * 送礼物发送消息
     *
     * @param giftId
     * @param giftPic
     * @param giftName
     * @param giftPrice
     * @param giftSpectial
     * @param giftMum
     * @param pits
     */
    @Override
    public void sendGiftMessage(String userName, String giftId, String giftPic, String giftName, String giftPrice, String giftSpectial, String giftMum, String pits) {
        mTransEaseChatFragment.sendGiftMessage(userName, giftId, giftPic, giftName, giftPrice, giftSpectial, giftMum, pits);
    }

    @Override
    public void showGift(int postion, String animation) {
        stationRoomView.showWheatGift(postion, animation);
    }

    @Override
    public void showView() {
        cl.setVisibility(View.VISIBLE);
    }

    @Override
    public void setContribution(String contribution) {
        stationRoomView.setRoomContribution(contribution);
    }

    @Override
    public void showGame(String pitNum) {
        stationRoomView.showWheatGame(pitNum);
    }

    @Override
    public void overGame(String pitNum) {
        stationRoomView.overWheatGame(pitNum);
    }

    @Override
    public void openGame(String pitNum, String qiu1, String qiu2, String qiu3) {
        stationRoomView.openWheatGame(pitNum, qiu1, qiu2, qiu3);
    }

    @Override
    public void showCardiac(int cardiac) {
        stationRoomView.showWheatCardiac(cardiac);
    }


    /**
     * 发送消息
     */
    @OnClick(R.id.iv_mesg)
    public void onMesg() {
        mRoomFragmentListener.sendMesg();
    }

    /**
     * 选择表情
     */
    @OnClick(R.id.iv_emoj)
    public void onEmoj() {
        mRoomFragmentListener.onEmoj();
    }


    /**
     * 下麦
     */
    @OnClick(R.id.tv_mic_up)
    public void upWheat() {
        mRoomFragmentListener.operationWheat();
    }

    /**
     * 设备静音
     */
    @OnClick(R.id.iv_external_release)
    public void equipmentMute() {
        if (externalRelease) {
            ivExternalRelease.setImageLevel(1);
        } else {
            ivExternalRelease.setImageLevel(0);
        }
        mRoomFragmentListener.muteAllRemoteAudioStreams(externalRelease);
        externalRelease = !externalRelease;
    }


    /**
     * 闭麦操作
     */
    @OnClick(R.id.iv_wheat)
    public void operationWheat() {
        mRoomFragmentListener.closedWheat();
    }


    /**
     * 显示更多
     */
    @OnClick(R.id.iv_mores)
    public void showMores() {
        mRoomFragmentListener.showRoomMore();
    }


    /**
     * 送礼物操作
     */
    @OnClick(R.id.iv_gift)
    public void gift() {
        mRoomFragmentListener.givingGifts("");
    }


    @OnClick(R.id.tv_count)
    public void paiMai() {
        mRoomFragmentListener.rowWheat();
    }

    @OnClick(R.id.iv_qiu_game)
    public void qiuGame() {
        mRoomFragmentListener.startQiuGame();
    }

    @Override
    public void wheatAdd(boolean isHostWheat, String pitNumber) {
        mRoomFragmentListener.addWheat(isHostWheat, pitNumber);
    }

    @Override
    public void wheatLock(boolean isHostWheat, String pitNumber) {
        mRoomFragmentListener.showRoomWheat(pitNumber);
    }

    @Override
    public void wheatHeadPicture(boolean isHostWheat, String userId) {
        mRoomFragmentListener.getRoomUserInfo(userId);
    }


    @Override
    public void guardListClick() {
        mRoomFragmentListener.guardListClick();
    }

    @Override
    public void hostListClick() {
        mRoomFragmentListener.hostListClick();
    }

    @Override
    public void playingClick() {
        mRoomFragmentListener.playingClick();
    }

    @Override
    public void onItemClick(XBanner banner, Object model, View view, int position) {
        RoomBannerModel bannerModel = (RoomBannerModel) model;
        if (bannerModel.res == R.mipmap.ic_room_cat) {
            new BossGameDialog().show(getChildFragmentManager(),"BossGameDialog");
//            startActivityForResult(new Intent(mContext, CatFishingActivity.class), 0);
        } else if (bannerModel.res == R.mipmap.ic_activity_51) {
            ARouter.getInstance().build(ARouters.H5).withString("url", Constant.URL.URL_ACTIVITY_51).withString("title", "五一活动").navigation();
        }
    }

    @Override
    public void indexSwitch(SignSwitchModel.Children children) {
        if (children.getState() == 1) {
            mIvActivity.setVisibility(View.VISIBLE);
            ImageLoader.loadHeadWithoutPlaceholder(MyApplication.getInstance(), mIvActivity, children.getIcon());
            mIvActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build(ARouters.H5).withString("url", children.getUrl()).withString("title", "活动").navigation();
                }
            });
        } else {
            mIvActivity.setVisibility(View.GONE);
        }
    }
}