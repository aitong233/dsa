package com.qpyy.libcommon.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.google.gson.Gson;
import com.qpyy.libcommon.BuildConfig;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.bean.RoomApplyWheatCountModel;
import com.qpyy.libcommon.bean.RoomBackgroundModel;
import com.qpyy.libcommon.bean.RoomBannedModel;
import com.qpyy.libcommon.bean.RoomClosePitModel;
import com.qpyy.libcommon.bean.RoomCountDownModel;
import com.qpyy.libcommon.bean.RoomDownWheatModel;
import com.qpyy.libcommon.bean.RoomFishingModel;
import com.qpyy.libcommon.bean.RoomJoinMountModel;
import com.qpyy.libcommon.bean.RoomJoinNobilityModel;
import com.qpyy.libcommon.bean.RoomKickOutModel;
import com.qpyy.libcommon.bean.RoomNameModel;
import com.qpyy.libcommon.bean.RoomNoticeModel;
import com.qpyy.libcommon.bean.RoomPopularityModel;
import com.qpyy.libcommon.bean.RoomRollModel;
import com.qpyy.libcommon.bean.RoomStarModel;
import com.qpyy.libcommon.bean.RoomSwitchdModel;
import com.qpyy.libcommon.bean.RoomUserJoinModel;
import com.qpyy.libcommon.bean.RoomUserWheathModel;
import com.qpyy.libcommon.bean.RoomWheatModel;
import com.qpyy.libcommon.bean.SpriteInfo;
import com.qpyy.libcommon.bean.TurntableLuckyRank;
import com.qpyy.libcommon.bean.TurntableLuckyRankEvent;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.bean.WheatVoiceModel;
import com.qpyy.libcommon.event.BossMsgEvent;
import com.qpyy.libcommon.event.PublicScreenEvent;
import com.qpyy.libcommon.event.QDZGameControlEvent;
import com.qpyy.libcommon.event.QiuGameEndEvent;
import com.qpyy.libcommon.event.QiuGameResultEvent;
import com.qpyy.libcommon.event.QiuGameStartEvent;
import com.qpyy.libcommon.event.RoomBeckoningEvent;
import com.qpyy.libcommon.event.RoomFaceEvent;
import com.qpyy.libcommon.event.RoomGuardEvent;
import com.qpyy.libcommon.event.RoomPasswordEvent;
import com.qpyy.libcommon.event.RoomToneEvent;
import com.qpyy.libcommon.event.RoomWheatEvent;
import com.qpyy.libcommon.event.ZegoLogUploadEvent;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.ServiceUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.utils.ThreadPoolUtil;
import com.yutang.game.grabmarbles.event.QDZMqttEvent;
import com.yutang.game.tangguobao.bean.BroadcastGameRsp;
import com.yutang.game.tangguobao.bean.BroadcastRoomRsp;
import com.yutang.game.tangguobao.bean.CreateRoomRsp;
import com.yutang.game.tangguobao.bean.EmqttServiceReconnectEvent;
import com.yutang.game.tangguobao.bean.GameRuleRsp;
import com.yutang.game.tangguobao.bean.GetGameHistoryRsp;
import com.yutang.game.tangguobao.bean.GetRoomGameLogRsp;
import com.yutang.game.tangguobao.bean.GetRoomListRsp;
import com.yutang.game.tangguobao.bean.JoinRoomRsp;
import com.yutang.game.tangguobao.bean.OperateRoomRsp;
import com.yutang.game.tangguobao.bean.ReconnRoomRsp;
import com.yutang.game.tangguobao.bean.RoomConfRsp;
import com.yutang.game.tangguobao.bean.SubscribeBigRoomTopicStatusEvent;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EMqttService extends Service {

    private final static String TAG = "EMQTT消息";

    private static final int qos = 0;
    private static final String HOST = BuildConfig.EMQTT_URL;
    //    private static final String USERNAME = BuildConfig.EMQTT_USER;//用户名
//    private static final String PASSWORD = BuildConfig.EMQTT_PASSWORD;//密码
    private static MqttAndroidClient mqttAndroidClient;
    private MqttConnectOptions mMqttConnectOptions;
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    private static final String TOPIC_BOSS = "boss";
    private static final String TOPIC_ROOM = "room";

    public static String sRoomId;

    public static String sUserId;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e(TAG, "服务创建成功");
        init();
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                doClientConnection();
            }
        }, 1000L, 3000L, TimeUnit.MILLISECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        doClientConnection();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 开启服务
     */
    public static void startService(Context mContext) {
//        boolean serviceRunning = ServiceUtils.isServiceRunning(EMqttService.class.getCanonicalName());
//        if (!serviceRunning) {
            mContext.startService(new Intent(mContext, EMqttService.class));
//        }
    }

    public static void stopService(Context context) {
//        boolean serviceRunning = ServiceUtils.isServiceRunning(EMqttService.class.getCanonicalName());
//        if (serviceRunning) {
            context.stopService(new Intent(context, EMqttService.class));
//        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void subscribeRoom(String roomId) {
        sRoomId = roomId;
        subscribe("room_" + roomId);
    }

    public static void subscribeRedGame() {
        subscribe("red_envelope_single_room_real_time_data");
    }

    public static void subscribeRoom() {
        subscribe("room");
    }

    public static void cleanSubscribeRoom() {
        cleanSubscribe("room");
    }


    public static void cleanSubscribeRoom(String roomId) {
        cleanSubscribe("room_" + roomId);
        sRoomId = null;
    }

    public static void subscribeUser(String userId) {
        sUserId = userId;
        subscribe("user_" + userId);
    }

    public static void cleanSubscribeUser() {
        cleanSubscribe("user_" + sUserId);
        sUserId = null;
    }

    /**
     * 订阅主题
     *
     * @param topic
     */
    public static void subscribe(String topic) {
        try {
            if (isAlreadyConnected()) {
                IMqttToken subToken = mqttAndroidClient.subscribe(topic, qos);
                subToken.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        LogUtils.e(TAG, topic + "主题订阅成功");
                        if (topic.equals("sugarserver/login/get_room_list/" + SpUtils.getUserId())) {
                            EventBus.getDefault().post(new SubscribeBigRoomTopicStatusEvent(true));
                        }
                        EventBus.getDefault().post(EmqttState.TOPICS_SUCCESS);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken,
                                          Throwable exception) {
                        LogUtils.e(TAG, topic + "主题订阅失败");
                        if (topic.equals("sugarserver/login/get_room_list/" + SpUtils.getUserId())) {
                            EventBus.getDefault().post(new SubscribeBigRoomTopicStatusEvent(false));
                        }
                        EventBus.getDefault().post(EmqttState.CLOSE_FAIL);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cleanSubscribe(String topic) {
        try {
            if (isAlreadyConnected()) {
                IMqttToken subToken = mqttAndroidClient.unsubscribe(topic);
                subToken.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        LogUtils.e(TAG, "取消主题成功");
                        EventBus.getDefault().post(EmqttState.CANCEL_TOPICS_SUCCESS);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken,
                                          Throwable exception) {
                        LogUtils.e(TAG, "取消主题失败");
                        EventBus.getDefault().post(EmqttState.CANCEL_TOPICS_FAIL);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布主题
     *
     * @param topic
     */
    public static void publish(String topic, String data) {
        try {
            if (isAlreadyConnected()) {
                MqttMessage mqttMessage = new MqttMessage(data.getBytes());
                IMqttToken subToken = mqttAndroidClient.publish(topic, mqttMessage);
                subToken.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        LogUtils.e(TAG, "发布主题成功", "主题：" + topic + " 消息：" + data);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken,
                                          Throwable exception) {
                        LogUtils.e(TAG, "发布主题失败", "主题：" + topic + " 消息：" + data);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 是否连接
     *
     * @return
     */
    public static boolean isAlreadyConnected() {
        if (mqttAndroidClient != null) {
            try {
                return mqttAndroidClient.isConnected();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 初始化
     */
    private void init() {
        UserBean user = BaseApplication.getIns().getUser();
        String clientId = "android-" + user.getUser_id() + "-" + MqttClient.generateClientId();
        SpUtils.saveEmqttId(clientId);
        if (mqttAndroidClient == null) {
            mqttAndroidClient = new MqttAndroidClient(this, HOST, clientId);
            mqttAndroidClient.setCallback(mqttCallback);
        }
        mMqttConnectOptions = new MqttConnectOptions();
        mMqttConnectOptions.setCleanSession(true); //设置是否清除缓存
        mMqttConnectOptions.setConnectionTimeout(10); //设置超时时间，单位：秒
        mMqttConnectOptions.setKeepAliveInterval(10); //设置心跳包发送间隔，单位：秒
//        mMqttConnectOptions.setUserName(USERNAME);
//        mMqttConnectOptions.setPassword(PASSWORD.toCharArray());
    }

    private synchronized void doClientConnection() {
        try {
            if (isAlreadyConnected()) {
                return;
            }
            if (isConnectIsNomarl()) {
                mqttAndroidClient.connect(mMqttConnectOptions, null, iMqttActionListener);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    public static void closeConnection() {
        if (mqttAndroidClient.isConnected()) {
            try {
                IMqttToken disconnect = mqttAndroidClient.disconnect();
                disconnect.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        EventBus.getDefault().post(EmqttState.CLOSE_SUCCESS);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        EventBus.getDefault().post(EmqttState.CLOSE_FAIL);
                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断网络是否连接
     */
    private boolean isConnectIsNomarl() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }


    //MQTT是否连接成功的监听
    private IMqttActionListener iMqttActionListener = new IMqttActionListener() {

        @Override
        public void onSuccess(IMqttToken arg0) {
            LogUtils.e(TAG, "链接状态成功");
            EventBus.getDefault().post(EmqttState.CONNECTED);
            EventBus.getDefault().post(new EmqttServiceReconnectEvent());
            if (sRoomId != null) {
                subscribeRoom(sRoomId);
            }
            subscribe(TOPIC_BOSS);
            subscribe(TOPIC_ROOM);
            UserBean userBean = BaseApplication.getIns().getUser();
            if (!TextUtils.isEmpty(userBean.getUser_id())) {
                subscribe("user_" + userBean.getUser_id());
            }
        }

        @Override
        public void onFailure(IMqttToken arg0, Throwable arg1) {
            arg1.printStackTrace();
            if (arg1 instanceof MqttException) {
                MqttException mqttException = (MqttException) arg1;
                LogUtils.e(TAG, "链接状态失败" + mqttException.getMessage());
            }
            EventBus.getDefault().post(EmqttState.DISCONNECT);
            doClientConnection();
        }
    };


    //订阅主题的回调
    private MqttCallback mqttCallback = new MqttCallback() {

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            LogUtils.e(TAG, "收到的消息", "主题：" + topic + "  收到的消息：" + message.toString());
            if (TOPIC_BOSS.equals(topic)) {
                EventBus.getDefault().post(new BossMsgEvent(message.toString()));
                return;
            }
            //糖果包开始
            String userId = SpUtils.getUserId();
            if (("sugarserver/login/room_config/" + userId).equals(topic)) {    //获取房间配置
                if (EventBus.getDefault().hasSubscriberForEvent(RoomConfRsp.class)) {
                    EventBus.getDefault().post(GsonUtils.fromJson(message.toString(), RoomConfRsp.class)); //json中包含type
                }
                return;
            }
            if (("sugarserver/login/get_room_list/" + userId).equals(topic)) {  //获取房间列表
                if (EventBus.getDefault().hasSubscriberForEvent(GetRoomListRsp.class)) {
                    EventBus.getDefault().post(GsonUtils.fromJson(message.toString(), GetRoomListRsp.class)); //json中包含type
                }
                return;
            }
            if (("sugarserver/room/create_room/" + userId).equals(topic)) {  //创建房间结果
                if (EventBus.getDefault().hasSubscriberForEvent(CreateRoomRsp.class)) {
                    EventBus.getDefault().post(GsonUtils.fromJson(message.toString(), CreateRoomRsp.class)); //json中包含type
                }
                return;
            }
            if (("sugarserver/room/join_room/" + userId).equals(topic)) {  //加入房间结果
                if (EventBus.getDefault().hasSubscriberForEvent(JoinRoomRsp.class)) {
                    EventBus.getDefault().post(GsonUtils.fromJson(message.toString(), JoinRoomRsp.class)); //json中包含type
                }
                return;
            }
            if (("sugarserver/room/reconn_room/" + userId).equals(topic)) {  //重连房间结果
                if (EventBus.getDefault().hasSubscriberForEvent(ReconnRoomRsp.class)) {
                    EventBus.getDefault().post(GsonUtils.fromJson(message.toString(), ReconnRoomRsp.class)); //json中包含type
                }
                return;
            }
            if (topic.contains("sugarserver/room/broadcast/")) {  //房间红包广播
                String replace = topic.replace("sugarserver/room/broadcast/", "");
                if (replace.contains("/")) {//小房间广播
                    EventBus.getDefault().post(GsonUtils.fromJson(message.toString(), BroadcastGameRsp.class)); //json中包含type
                } else {    //大房间广播
                    BroadcastRoomRsp broadcastRoomRsp = GsonUtils.fromJson(message.toString(), BroadcastRoomRsp.class);
                    EventBus.getDefault().postSticky(broadcastRoomRsp); //json中包含type
                }
                return;
            }
            if (topic.equals("sugarserver/room/game_rule/" + userId)) {
                if (EventBus.getDefault().hasSubscriberForEvent(GameRuleRsp.class)) {
                    EventBus.getDefault().post(GsonUtils.fromJson(message.toString(), GameRuleRsp.class)); //json中包含type
                }
                return;
            }
            if (topic.equals("sugarserver/room/get_room_game_log/" + userId)) {
                if (EventBus.getDefault().hasSubscriberForEvent(GetRoomGameLogRsp.class)) {
                    EventBus.getDefault().post(GsonUtils.fromJson(message.toString(), GetRoomGameLogRsp.class)); //json中包含type
                }
                return;
            }
            if (topic.equals("sugarserver/room/operate_room/" + userId)) {
                if (EventBus.getDefault().hasSubscriberForEvent(OperateRoomRsp.class)) {
                    EventBus.getDefault().post(GsonUtils.fromJson(message.toString(), OperateRoomRsp.class)); //json中包含type
                }
                return;
            }
            if (topic.equals("sugarserver/room/get_game_history/" + userId)) {
                Gson gson = new Gson();
                try {
                    GetGameHistoryRsp getGameHistoryRsp = gson.fromJson(message.toString(), GetGameHistoryRsp.class);
                    EventBus.getDefault().post(getGameHistoryRsp); //json中包含type
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            //糖果包结束
            receiveMessage(topic, message.toString());
        }


        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {

        }

        @Override
        public void connectionLost(Throwable arg0) {
            LogUtils.e(TAG, "链接状态：", "链接断开");
            EventBus.getDefault().post(EmqttState.DISCONNECT);
            ThreadUtils.runOnUiThreadDelayed(new Runnable() {
                @Override
                public void run() {
                    doClientConnection();
                }
            }, 300);

        }
    };


    @Override
    public void onDestroy() {
        try {
            cleanSubscribeUser();
            cleanSubscribeRoom(sRoomId);
            cleanSubscribe(TOPIC_BOSS);
            cleanSubscribe(TOPIC_ROOM);
            if (mqttAndroidClient != null) {
                mqttAndroidClient.disconnect(); //断开连接
                mqttAndroidClient.unregisterResources();
                mqttAndroidClient = null;
                scheduledExecutorService.shutdown();
                scheduledExecutorService = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    private void receiveMessage(String topic, String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        int type = jsonObject.getIntValue("type");
        long time = jsonObject.getLongValue("time");
        String message = jsonObject.getString("msg");
        if (topic.equals("red_envelope_single_room_real_time_data")) {
            switch (type) { //抢糖果相关内容
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    EventBus.getDefault().post(new QDZMqttEvent(data));
                    break;
            }
        } else if (topic.equals("room")) {
            switch (type) {
                case 3001:  //控制抢糖果/抢弹珠游戏入口开关
                    ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, QDZGameControlEvent.class));

                    break;
                case 5150:  //更新福袋/大转盘手气记录榜
                    ThreadPoolUtil.getPool().execute(() -> {
                        List<TurntableLuckyRank> luckyRankList = JSON.parseArray(message, TurntableLuckyRank.class);
                        EventBus.getDefault().post(new TurntableLuckyRankEvent(luckyRankList));
                    });
                    break;
            }
        }
        switch (type) {
            case 5001://延时一秒推送房间-人气变化
                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomPopularityModel.class));
                break;
            case 5003://延时一秒推送房间-坐骑进场特效
                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomJoinMountModel.class));
                break;
            case 5004://延时一秒推送房间-爵位用户进场特效
                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomJoinNobilityModel.class));
                break;
            case 5005://推送房间-上麦申请人数变化
                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomApplyWheatCountModel.class));
                break;
            case 5007://推送房间-用户是否禁言 1禁言2解禁
                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomBannedModel.class));
                break;
            case 5011://推送房间-是否封麦 1封麦2解封
                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomClosePitModel.class));
                break;
            case 5013://推送房间-清空单个麦位心动值
            case 5014://推送房间-清空所有麦位心动值
                ThreadPoolUtil.getPool().execute(new RoomClearCardiacRunnable(message));
                break;
            case 5015://推送房间-设置房间管理员
                ThreadPoolUtil.getPool().execute(new RoomManagerRunnable(message, 2));
                break;
            case 5016://推送房间-删除房间管理员
                ThreadPoolUtil.getPool().execute(new RoomManagerRunnable(message, 3));
                break;
            case 5017://用户开关麦
                EventBus.getDefault().post(JSON.parseObject(message, WheatVoiceModel.class));
                break;
            case 5019://推送所有人-横幅礼物通知c
                ThreadPoolUtil.getPool().execute(new RoomGiftRunable(message));
                break;
            case 5020://推送房间-聊天室礼物通知
                ThreadPoolUtil.getPool().execute(new RoomGiveGiftRunnable(message));
                break;
            case 5030:
            case 5021://推送所有人-小猫钓鱼钓到大礼物时通知
            case 5049://福袋横幅消息
            case 5151: //大转盘高于5200横幅推送
                ThreadPoolUtil.getPool().execute(new RoomFishingRunnable(message, type));
                break;
            case 5022://推送房间-房间密码变化通知 0取消密码1设置或修改密码
            case 5023://推送房间-房间心动值开关变化通知 1开2关
            case 5024://推送房间-上麦模式变化通知 1自由2排麦
                ThreadPoolUtil.getPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        RoomSwitchdModel roomSwitchdModel = JSON.parseObject(message, RoomSwitchdModel.class);
                        switch (type) {
                            case 5022:
                                EventBus.getDefault().post(new RoomPasswordEvent(roomSwitchdModel.getRoom_id(), "1".equals(roomSwitchdModel.getAction())));
                                break;
                            case 5023:
                                EventBus.getDefault().post(new RoomBeckoningEvent(roomSwitchdModel.getRoom_id(), "1".equals(roomSwitchdModel.getAction())));
                                break;
                            case 5024:
                                EventBus.getDefault().post(new RoomWheatEvent(roomSwitchdModel.getRoom_id(), "1".equals(roomSwitchdModel.getAction())));
                                break;
                        }
                    }
                });
                break;
            case 5025://推送房间-修改房间名称
                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomNameModel.class));
                break;
            case 5026://推送房间-周星用户进场特效
                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomStarModel.class));
                break;
            case 5028://推送房间-修改房间背景

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomBackgroundModel.class));
                break;
            case 5029://推送房间-修改房间公告

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomNoticeModel.class));
                break;
            case 5032://推送房间-上麦
                postStickyEvent(message, RoomWheatModel.class);
                break;
            case 5033://推送房间-下麦

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomDownWheatModel.class));
                break;
            case 5034://踢出房间

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomKickOutModel.class));
                break;
            case 5035://推送单独用户-定向推向给上麦的用户

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomUserWheathModel.class));
                break;
            case 5036://推送房间-用户禁麦 1禁麦2解禁
                ThreadPoolUtil.getPool().execute(new RoomBanWheatRunnable(message));
                break;
            case 5037://推送房间-用户进入房间
                ThreadPoolUtil.getPool().execute(() -> {
                    RoomUserJoinModel roomUserJoinModel = JSON.parseObject(message, RoomUserJoinModel.class);
                    EventBus.getDefault().postSticky(roomUserJoinModel);
                });
                break;
            case 5038://麦位倒计时

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomCountDownModel.class));
                break;
            case 5039://扔骰子

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomRollModel.class));
                break;
            case 5040://开通守护推送

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomGuardEvent.class));
                break;
            case 5041://发送表情

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomFaceEvent.class));
                break;
            case 5042://上传即构日志
                EventBus.getDefault().post(new ZegoLogUploadEvent());
                break;
            case 5043://公屏状态

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, PublicScreenEvent.class));
                break;
            case 5044://开球

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, QiuGameStartEvent.class));
                break;
            case 5045://弃球

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, QiuGameEndEvent.class));
                break;
            case 5046://亮球

                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, QiuGameResultEvent.class));
                break;
            case 5047:
                ThreadPoolUtil.getPool().execute(new OrdinaryNoticeRunable<>(message, RoomToneEvent.class));
                break;
            case 5048:
                EventBus.getDefault().post(JSON.parseObject(data, SpriteInfo.class));
                break;
        }

    }

    <T> void postStickyEvent(String message, Class<T> clazz) {
        ThreadPoolUtil.getPool().execute(() -> {
            EventBus.getDefault().postSticky(JSON.parseObject(message, clazz));
        });
    }
}



