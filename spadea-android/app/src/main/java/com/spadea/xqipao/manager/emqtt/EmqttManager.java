package com.spadea.xqipao.manager.emqtt;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qpyy.libcommon.bean.UserBean;
import com.spadea.xqipao.service.MyEmqttConnectListener;
import com.spadea.xqipao.service.MyEmqttMesgListener;
import com.spadea.xqipao.service.MyEmqttSubscribeListener;
import com.spadea.xqipao.service.MyMqttService;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.utilcode.ServiceUtils;
import com.spadea.xqipao.data.ApproachBean;
import com.spadea.xqipao.data.even.RoomShowCatEvent;
import com.spadea.xqipao.data.socket.PopularityModel;
import com.spadea.xqipao.data.socket.RoomApplyCountModel;
import com.spadea.xqipao.data.socket.RoomApproachModel;
import com.spadea.xqipao.data.socket.RoomBannedModel;
import com.spadea.xqipao.data.socket.RoomCardiacModel;
import com.spadea.xqipao.data.socket.RoomCardiacSwitchModel;
import com.spadea.xqipao.data.socket.RoomClearScreenModel;
import com.spadea.xqipao.data.socket.RoomClosePitModel;
import com.spadea.xqipao.data.socket.RoomCountDownModel;
import com.spadea.xqipao.data.socket.RoomFaceModel;
import com.spadea.xqipao.data.socket.RoomGetUserOnWheatModel;
import com.spadea.xqipao.data.socket.RoomGiftGiveModel;
import com.spadea.xqipao.data.socket.RoomGiftModel;
import com.spadea.xqipao.data.socket.RoomGofisModel;
import com.spadea.xqipao.data.socket.RoomInfoModel;
import com.spadea.xqipao.data.socket.RoomJueWeiModel;
import com.spadea.xqipao.data.socket.RoomKickOutModel;
import com.spadea.xqipao.data.socket.RoomMaiXuModel;
import com.spadea.xqipao.data.socket.RoomManagerModel;
import com.spadea.xqipao.data.socket.RoomNameModel;
import com.spadea.xqipao.data.socket.RoomPasswordModel;
import com.spadea.xqipao.data.socket.RoomSocketModel;
import com.spadea.xqipao.data.socket.RoomSocketPitModel;
import com.spadea.xqipao.data.socket.RoomWheatModel;
import com.spadea.xqipao.data.socket.WeekStarInModel;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.view.room.animation.ItemRoomGiftBean;

import org.greenrobot.eventbus.EventBus;

public class EmqttManager {

    private final static String TAG = EmqttManager.class.getCanonicalName();

    private static EmqttManager instance;
    private static String mRoomId;

    private RoomEmqttEventListener roomSocketEventListener;

    private Handler mHandler = new Handler(Looper.getMainLooper());


    public EmqttManager() {
        MyMqttService.addMyEmqttConnectListener(myEmqttConnectListener);
        MyMqttService.addMyEmqttMesgListener(myEmqttMesgListener);
        MyMqttService.addMyEmqttSubscribeListener(myEmqttSubscribeListener);
    }

    public static EmqttManager instance(String roomId) {
        if (instance == null) {
            synchronized (EmqttManager.class) {
                if (instance == null)
                    instance = new EmqttManager();
            }
        }
        mRoomId = roomId;
        return instance;
    }

    public void onStart() {
        boolean serviceRunning = ServiceUtils.isServiceRunning("com.spirit.xqipao.service.MyMqttService");
        if (!serviceRunning) {
            MyMqttService.startService(MyApplication.getInstance());
        }
    }


    public void setRoomSocketEventListener(RoomEmqttEventListener roomSocketEventListener) {
        this.roomSocketEventListener = roomSocketEventListener;
    }

    public void subscribe() {
        UserBean user = MyApplication.getInstance().getUser();
        MyMqttService.subscribe("room_" + mRoomId);
        MyMqttService.subscribe("user_" + user.getUser_id());
        MyMqttService.subscribe("room");
    }

    public void cleanSubscribe() {
        roomSocketEventListener = null;
        UserBean user = MyApplication.getInstance().getUser();
        MyMqttService.cleanSubscribe("room_" + mRoomId);
        MyMqttService.cleanSubscribe("user_" + user.getUser_id());
        MyMqttService.cleanSubscribe("room");
    }


    public void sendClearScreen() {
        JSONObject jsonObject = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("room_id", mRoomId);
        jsonObject.put("type", 210);
        jsonObject.put("msg", data);
        jsonObject.put("time", System.currentTimeMillis());
        MyMqttService.publish("room_" + mRoomId, jsonObject.toString());
    }


    public void nobilityUser(ApproachBean approachBean) {
        JSONObject jsonObject = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("room_id", mRoomId);
        data.put("data", approachBean);
        jsonObject.put("type", 1001);
        jsonObject.put("msg", data);
        jsonObject.put("time", System.currentTimeMillis());
        MyMqttService.publish("room_" + mRoomId, jsonObject.toString());
    }


    public void sendFace(String pit, String faceSpectial, String roomId, int type, int number) {
        JSONObject jsonObject = new JSONObject();
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        message.put("type", type);
        message.put("number", number);
        message.put("pit", pit);
        message.put("face_spectial", faceSpectial);
        data.put("room_id", roomId);
        data.put("data", message);
        jsonObject.put("type", 121);
        jsonObject.put("msg", data);
        jsonObject.put("time", System.currentTimeMillis());
        MyMqttService.publish("room_" + mRoomId, jsonObject.toString());
    }


    public void getUserOnWheat(String userId) {
        JSONObject jsonObject = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("room_id", mRoomId);
        jsonObject.put("type", 120);
        jsonObject.put("time", System.currentTimeMillis());
        jsonObject.put("msg", data);
        MyMqttService.publish("user_" + userId, jsonObject.toString());
    }

    public void sendCountDownTime(String roomId, String pit, String time) {
        JSONObject jsonObject = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("pit", pit);
        data.put("time", time);
        data.put("room_id", roomId);
        jsonObject.put("msg", data);
        jsonObject.put("type", 122);
        jsonObject.put("time", System.currentTimeMillis());
        MyMqttService.publish("room_" + mRoomId, jsonObject.toString());
    }


    private MyEmqttConnectListener myEmqttConnectListener = new MyEmqttConnectListener() {
        @Override
        public void onConnectSuccess() {
            if (roomSocketEventListener != null) {
                roomSocketEventListener.reconnectSuccess();
            }
        }

        @Override
        public void onConnectFailure() {

        }
    };

    private MyEmqttSubscribeListener myEmqttSubscribeListener = new MyEmqttSubscribeListener() {
        @Override
        public void onSubscribeSuccess(String topic) {
        }

        @Override
        public void onSubscribeFailure() {
            if (roomSocketEventListener != null) {
                roomSocketEventListener.reconnectSuccess();
            }
        }
    };


    private MyEmqttMesgListener myEmqttMesgListener = new MyEmqttMesgListener() {

        @Override
        public void messageArrived(String topic, String data) {
            if (roomSocketEventListener != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = JSON.parseObject(data);
                            int type = jsonObject.getIntValue("type");
                            long time = jsonObject.getLongValue("time");
                            String message = jsonObject.getString("msg");
                            LogUtils.e(TAG, "延时", System.currentTimeMillis() - time);
                            switch (type) {
                                case 101:
                                    PopularityModel popularityModel = JSONObject.parseObject(message, PopularityModel.class);
                                    if (mRoomId.equals(popularityModel.getRoom_id())) {
                                        roomSocketEventListener.roomPopularity(popularityModel.getPopularity());
                                    }
                                    break;
                                case 102:
                                    RoomKickOutModel roomKickOutModel = JSONObject.parseObject(message, RoomKickOutModel.class);
                                    if (roomKickOutModel.getRoom_id().equals(mRoomId)) {
                                        if (roomKickOutModel.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                                            roomSocketEventListener.roomKickOut(roomKickOutModel.getAction());
                                        } else {
                                            roomSocketEventListener.roomPopularity(roomKickOutModel.getPopularity());
                                            roomSocketEventListener.roomApplyWheat(roomKickOutModel.getPit_info());
                                        }
                                    }

                                    break;
                                case 105:
                                    RoomWheatModel roomWheatModel = JSONObject.parseObject(message, RoomWheatModel.class);
                                    if (mRoomId.equals(roomWheatModel.getRoom_id())) {
                                        roomSocketEventListener.roomApplyWheat(roomWheatModel.getPit_info());
                                        if (roomWheatModel.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                                            roomSocketEventListener.roomDownWheat(roomWheatModel.getPit_number());
                                        }
                                    }
                                    break;
                                case 103:
                                    RoomWheatModel roomUpWheatModel = JSONObject.parseObject(message, RoomWheatModel.class);
                                    if (mRoomId.equals(roomUpWheatModel.getRoom_id())) {
                                        roomSocketEventListener.roomApplyWheat(roomUpWheatModel.getPit_info());
                                        roomSocketEventListener.roomApplyCount(roomUpWheatModel.getCount());
                                        if (roomUpWheatModel.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                                            roomSocketEventListener.roomUpWheat(roomUpWheatModel.getPit_number());
                                        }
                                    }
                                    break;
                                case 104:
                                case 118:
                                    RoomApplyCountModel roomApplyCountModel = JSONObject.parseObject(message, RoomApplyCountModel.class);
                                    if (roomApplyCountModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.roomApplyCount(roomApplyCountModel.getCount());
                                    }
                                    break;
                                //禁言用户
                                case 106:
                                    RoomBannedModel roomBannedModel = JSONObject.parseObject(message, RoomBannedModel.class);
                                    if (roomBannedModel.getRoom_id().equals(mRoomId) && roomBannedModel.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                                        roomSocketEventListener.roomBanned(roomBannedModel.getAction());
                                    }
                                    break;
                                //禁麦
                                case 107:
                                    RoomBannedModel roomShutupModel = JSONObject.parseObject(message, RoomBannedModel.class);
                                    if (roomShutupModel.getRoom_id().equals(mRoomId)) {
                                        if (roomShutupModel.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                                            roomSocketEventListener.roomShutup(roomShutupModel.getAction(), roomShutupModel.getPit_number());
                                        }
                                        roomSocketEventListener.roomUserShutup(roomShutupModel.getPit_number(), roomShutupModel.getAction());
                                    }
                                    break;
                                //封麦
                                case 108:
                                    RoomClosePitModel roomClosePitModel = JSONObject.parseObject(message, RoomClosePitModel.class);
                                    if (roomClosePitModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.roomClosePit(roomClosePitModel.getPit_number(), roomClosePitModel.getAction());
                                    }
                                    break;
                                //清空房间具体麦位心动值
                                case 109:
                                    RoomCardiacModel roomCardiacModel = JSONObject.parseObject(message, RoomCardiacModel.class);
                                    if (mRoomId.equals(roomCardiacModel.getRoom_id())) {
                                        roomSocketEventListener.roomDeleteCardiac(roomCardiacModel.getPit_number());
                                    }
                                    break;
                                //清空房间所有麦位心动值
                                case 110:
                                    RoomSocketModel roomSocketModel = JSONObject.parseObject(message, RoomSocketModel.class);
                                    if (mRoomId.equals(roomSocketModel.getRoom_id())) {
                                        roomSocketEventListener.roomDeleteAllCardiac();
                                    }
                                    break;
                                //设置房间管理员
                                case 111:
                                    RoomManagerModel roomAddManagerModel = JSONObject.parseObject(message, RoomManagerModel.class);
                                    if (roomAddManagerModel.getRoom_id().equals(mRoomId) && roomAddManagerModel.getManager_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                                        roomSocketEventListener.roomAddManager();
                                    }
                                    break;
                                //删除房间管理员
                                case 112:
                                    RoomManagerModel roomDeleteManagerModel = JSONObject.parseObject(message, RoomManagerModel.class);
                                    if (roomDeleteManagerModel.getRoom_id().equals(mRoomId) && roomDeleteManagerModel.getManager_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                                        roomSocketEventListener.roomDeleteManager();
                                    }
                                    break;
                                //房间加锁
                                case 116:
                                    RoomPasswordModel roomPasswordModel = JSONObject.parseObject(message, RoomPasswordModel.class);
                                    if (roomPasswordModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.roomPassword(roomPasswordModel.getAction());
                                    }
                                    break;
                                //房间心动值开关设置
                                case 117:
                                    RoomCardiacSwitchModel roomCardiacSwitchModel = JSONObject.parseObject(message, RoomCardiacSwitchModel.class);
                                    if (roomCardiacSwitchModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.roomCardiacSwitch(roomCardiacSwitchModel.getAction());
                                    }
                                    break;
                                //1自由2排麦
                                case 119:
                                    RoomMaiXuModel roomMaiXuModel = JSONObject.parseObject(message, RoomMaiXuModel.class);
                                    if (roomMaiXuModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.roomMaiXu(roomMaiXuModel.getAction());
                                    }
                                    break;
                                //批量同意上麦 定向推送给上麦的用户
                                case 1004:
                                    RoomSocketPitModel roomSocketPitModel = JSONObject.parseObject(message, RoomSocketPitModel.class);
                                    if (mRoomId.equals(roomSocketPitModel.getRoom_id())) {
                                        roomSocketEventListener.roomUserPit(roomSocketPitModel.getPit_number());
                                    }
                                    break;
                                case 120:
                                    RoomGetUserOnWheatModel roomGetUserOnWheatModel = JSONObject.parseObject(message, RoomGetUserOnWheatModel.class);
                                    if (roomGetUserOnWheatModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.roomGetUserOnWheat();
                                    }
                                    break;
                                case 121:
                                    RoomFaceModel roomFaceModel = JSONObject.parseObject(message, RoomFaceModel.class);
                                    if (roomFaceModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.roomFace(roomFaceModel.getData());
                                    }
                                    break;
                                case 1001:
                                    RoomJueWeiModel roomJueWeiModel = JSONObject.parseObject(message, RoomJueWeiModel.class);
                                    if (roomJueWeiModel.getRoom_id().equals(mRoomId)) {
                                        ApproachBean approachBean = roomJueWeiModel.getData();
                                        approachBean.setDisplay(roomJueWeiModel.getDisplay());
                                        roomSocketEventListener.roomJueWeiIn(approachBean);
                                    }
                                    break;
                                case 1011:
                                    RoomApproachModel roomApproachModel = JSONObject.parseObject(message, RoomApproachModel.class);
                                    EventBus.getDefault().post(roomApproachModel);
                                    break;
                                case 115:
                                    RoomGofisModel roomGofisModel = JSONObject.parseObject(message, RoomGofisModel.class);
                                    roomSocketEventListener.roomGofis(roomGofisModel.getText());
                                    break;
                                case 114:
                                    RoomGiftModel roomGiftModel = JSONObject.parseObject(message, RoomGiftModel.class);
                                    roomSocketEventListener.roomGift(roomGiftModel);
                                    break;
                                case 1114:
                                    RoomGiftGiveModel roomGiftGiveModel = JSONObject.parseObject(message, RoomGiftGiveModel.class);
                                    if (roomGiftGiveModel.getRoom_id().equals(mRoomId)) {
                                        for (RoomGiftGiveModel.GiftListBean item : roomGiftGiveModel.getGift_list()) {
                                            ItemRoomGiftBean itemRoomGiftBean = new ItemRoomGiftBean();
                                            itemRoomGiftBean.setFormUser(item.getNickname_from());
                                            itemRoomGiftBean.setToUser(item.getNickname_to());
                                            itemRoomGiftBean.setGiftImgUrl(item.getPicture());
                                            itemRoomGiftBean.setNum(item.getNumber());
                                            roomSocketEventListener.roomGiftGive(itemRoomGiftBean);
                                            if (item.getSpecial().indexOf(".svga") > 0) {
                                                roomSocketEventListener.roomGiftShow(item.getSpecial());
                                            }
                                        }
                                        roomSocketEventListener.roomContribution(roomGiftGiveModel.getContribution());
                                        roomSocketEventListener.roomPopularity(String.valueOf(roomGiftGiveModel.getPopularity()));
                                        for (RoomGiftGiveModel.CardiacListBean item : roomGiftGiveModel.getCardiac_list()) {
                                            roomSocketEventListener.roomCardiac(item.getPit_number(), item.getRough_number());
                                        }
                                        if (MyApplication.getInstance().getUser().getUser_id().equals(roomGiftGiveModel.getUser_id())) {
                                            EventBus.getDefault().post(new RoomShowCatEvent(roomGiftGiveModel.getShow_cat()));
                                        }
                                    }
                                    break;
                                case 210:
                                    RoomClearScreenModel roomClearScreenModel = JSONObject.parseObject(message, RoomClearScreenModel.class);
                                    if (roomClearScreenModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.roomPublicScreen();
                                    }
                                    break;
                                case 130:
                                    RoomNameModel roomNameModel = JSONObject.parseObject(message, RoomNameModel.class);
                                    if (roomNameModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.roomName(roomNameModel.getName());
                                    }
                                    break;
                                case 1003:
                                    RoomWheatModel roomWheatModel1 = JSONObject.parseObject(message, RoomWheatModel.class);
                                    if (mRoomId.equals(roomWheatModel1.getRoom_id())) {
                                        roomSocketEventListener.roomApplyWheat(roomWheatModel1.getPit_info());
                                        roomSocketEventListener.roomApplyCount(roomWheatModel1.getCount());
                                    }
                                    break;
                                case 122:
                                    RoomCountDownModel roomCountDownModel = JSONObject.parseObject(message, RoomCountDownModel.class);
                                    if (roomCountDownModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.roomCountDown(roomCountDownModel.getPit(), roomCountDownModel.getTime());
                                    }
                                    break;
                                case 131:
                                    RoomInfoModel roomInfoModel = JSONObject.parseObject(message, RoomInfoModel.class);
                                    if (roomInfoModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.setRoomBackground(roomInfoModel.getPicture());
                                    }
                                    break;
                                case 132:
                                    RoomInfoModel roomPlaying = JSONObject.parseObject(message, RoomInfoModel.class);
                                    if (roomPlaying.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.setRoomPlaying(roomPlaying.getPlaying());
                                    }
                                    break;
                                case 1318:
                                    WeekStarInModel weekStarInModel = JSONObject.parseObject(message, WeekStarInModel.class);
                                    if (weekStarInModel.getRoom_id().equals(mRoomId)) {
                                        roomSocketEventListener.weekStarIn(weekStarInModel);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        }
    };


}
