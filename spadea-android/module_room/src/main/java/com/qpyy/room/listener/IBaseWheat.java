package com.qpyy.room.listener;

import com.qpyy.libcommon.bean.RoomClearCardiacAllModel;
import com.qpyy.libcommon.bean.RoomClearCardiacModel;
import com.qpyy.libcommon.bean.RoomClosePitModel;
import com.qpyy.libcommon.bean.RoomCountDownModel;
import com.qpyy.libcommon.bean.RoomDownWheatModel;
import com.qpyy.libcommon.bean.RoomGiveGiftModel;
import com.qpyy.libcommon.bean.RoomRollModel;
import com.qpyy.libcommon.bean.RoomWheatModel;
import com.qpyy.libcommon.event.QiuGameEndEvent;
import com.qpyy.libcommon.event.QiuGameResultEvent;
import com.qpyy.libcommon.event.QiuGameStartEvent;
import com.qpyy.libcommon.event.RoomBanWheatEvent;
import com.qpyy.libcommon.event.RoomBeckoningEvent;
import com.qpyy.libcommon.event.RoomFaceEvent;
import com.qpyy.room.bean.ClosePhone;
import com.qpyy.room.bean.RoomPitBean;
import com.qpyy.room.event.SoundLevelEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.widget
 * 创建人 王欧
 * 创建时间 2020/8/14 10:51 AM
 * 描述 describe
 */
public interface IBaseWheat {
    void register(Object obj);

    void unRegister(Object obj);

    void setCardiac(String rough_number);

    void clearCardiac();

    void setData(RoomPitBean pitBean);

    boolean isOn();

    void showGift(RoomGiveGiftModel.GiftListBean listBean);

    /**
     * 麦位心动值
     *
     * @param cardiacListBean
     */
    void subscribeMessages(RoomGiveGiftModel.CardiacListBean cardiacListBean);

    /**
     * 房间心动值开关变化通知 1开2关
     *
     * @param roomBeckoningEvent
     */
    void subscribeMessages(RoomBeckoningEvent roomBeckoningEvent);

    /**
     * 清空单个麦位心动值
     *
     * @param roomClearCardiacModel
     */
    void subscribeMessages(RoomClearCardiacModel roomClearCardiacModel);

    /**
     * 清空所有麦位心动值
     *
     * @param roomClearCardiacAllModel
     */
    void subscribeMessages(RoomClearCardiacAllModel roomClearCardiacAllModel);

    /**
     * 房间内上麦
     *
     * @param roomWheatModel
     */
    void subscribeMessages(RoomWheatModel roomWheatModel);

    /**
     * 房间内下麦
     *
     * @param roomDownWheatModel
     */
    void subscribeMessages(RoomDownWheatModel roomDownWheatModel);

    /**
     * 麦位倒计时
     *
     * @param roomCountDownModel
     */
    void subscribeMessages(RoomCountDownModel roomCountDownModel);

    /**
     * 禁麦麦位数据
     *
     * @param roomBanWheatEvent
     */
    void subscribeMessages(RoomBanWheatEvent roomBanWheatEvent);

    /**
     * 是否封麦 1封麦2解封
     *
     * @param roomClosePitModel
     */
    void subscribeMessages(RoomClosePitModel roomClosePitModel);

    /**
     * 麦位声浪
     *
     * @param soundLevelEvent
     */
    void subscribeMessages(SoundLevelEvent soundLevelEvent);

    /**
     * 麦位表情
     *
     * @param roomFaceEvent
     */
    void subscribeMessages(RoomFaceEvent roomFaceEvent);

    /**
     * 抽签
     *
     * @param roomRollModel
     */
    void subscribeMessages(RoomRollModel roomRollModel);

    /**
     * 开球
     *
     * @param event
     */
    void subscribeMessages(QiuGameStartEvent event);

    /**
     * 气球
     *
     * @param event
     */
    void subscribeMessages(QiuGameEndEvent event);

    /**
     * 亮球
     *
     * @param event
     */
    void subscribeMessages(QiuGameResultEvent event);

    /**
     * 是否锁麦
     *
     * @return
     */
    boolean isLocked();

    /**
     * 是否主持
     *
     * @return
     */
    boolean isHost();

}
