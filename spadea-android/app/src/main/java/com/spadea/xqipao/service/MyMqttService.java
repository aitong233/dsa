package com.spadea.xqipao.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

import com.spadea.yuyin.BuildConfig;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.utilcode.ServiceUtils;
import com.qpyy.libcommon.event.BossMsgEvent;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.handler.HandlerUtil;

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
import org.jetbrains.annotations.Nullable;

public class MyMqttService extends Service {

    private final static String TAG = MyMqttService.class.getCanonicalName();

    private static int qos = 2;
    private static String HOST = BuildConfig.EMQTT_URL;
    private static String USERNAME = BuildConfig.EMQTT_USER;//用户名
    private static String PASSWORD = BuildConfig.EMQTT_PASSWORD;//密码
    private static MqttAndroidClient mqttAndroidClient;
    private MqttConnectOptions mMqttConnectOptions;
    private static boolean b = true;


    private static MyEmqttConnectListener mMyEmqttConnectListener;
    private static MyEmqttMesgListener mMyEmqttMesgListener;
    private static MyEmqttSubscribeListener mMyEmqttSubscribeListener;

    private static final String TOPIC_BOSS = "boss";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 开启服务
     */
    public static void startService(Context mContext) {
        b = true;
        boolean serviceRunning = ServiceUtils.isServiceRunning(MyMqttService.class.getCanonicalName());
        if (!serviceRunning) {
            mContext.startService(new Intent(mContext, MyMqttService.class));
        }
    }

    public static void stopService(Context context) {
        b = false;
        boolean serviceRunning = ServiceUtils.isServiceRunning(MyMqttService.class.getCanonicalName());
        if (serviceRunning) {
            context.stopService(new Intent(context, MyMqttService.class));
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 发布 （模拟其他客户端发布消息）
     *
     * @param message 消息
     */
    public static void publish(String topic, String message) {
        if (mqttAndroidClient == null) {
            LogUtils.e(TAG, "mqttAndroidClient is null", "发送失败");
            return;
        }
        try {
            //参数分别为：主题、消息的字节数组、服务质量、是否在服务器保留断开连接后的最后一条消息
            IMqttDeliveryToken publish = mqttAndroidClient.publish(topic, message.getBytes(), qos, false);
            publish.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    LogUtils.e(TAG, "发送消息", "发送成功");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    LogUtils.e(TAG, "发送消息", "发送失败");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public static void subscribe(String topic) {
        try {
            if (mqttAndroidClient.isConnected()) {
                IMqttToken subToken = mqttAndroidClient.subscribe(topic, qos);
                subToken.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        if (mMyEmqttSubscribeListener != null) {
                            mMyEmqttSubscribeListener.onSubscribeSuccess(topic);
                        }
                        LogUtils.e(TAG, "订阅成功：" + topic);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken,
                                          Throwable exception) {
                        if (!TOPIC_BOSS.equals(topic) && mMyEmqttSubscribeListener != null) {
                            mMyEmqttSubscribeListener.onSubscribeFailure();
                        }
                        LogUtils.e(TAG, "订阅失败：" + topic);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cleanSubscribe(String topic) {
        try {
            if (mqttAndroidClient.isConnected()) {
                IMqttToken subToken = mqttAndroidClient.unsubscribe(topic);
                subToken.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        LogUtils.e(TAG, "取消成功" + topic);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken,
                                          Throwable exception) {
                        LogUtils.e(TAG, "取消失败" + topic);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化
     */
    private void init() {
        String CLIENTID = "android-" + MyApplication.getInstance().getUser().getUser_id() + "-" + MqttClient.generateClientId();
        mqttAndroidClient = new MqttAndroidClient(this, HOST, CLIENTID);
        mqttAndroidClient.setCallback(mqttCallback); //设置监听订阅消息的回调
        mMqttConnectOptions = new MqttConnectOptions();
        mMqttConnectOptions.setCleanSession(true); //设置是否清除缓存
        mMqttConnectOptions.setConnectionTimeout(10); //设置超时时间，单位：秒
        mMqttConnectOptions.setKeepAliveInterval(10); //设置心跳包发送间隔，单位：秒
        mMqttConnectOptions.setUserName(USERNAME); //设置用户名
        mMqttConnectOptions.setPassword(PASSWORD.toCharArray()); //设置密码
        if (!mqttAndroidClient.isConnected()) {
            doClientConnection();
        }
    }

    private void doClientConnection() {
        if (mqttAndroidClient != null && !mqttAndroidClient.isConnected() && isConnectIsNomarl() && b) {
            try {
                mqttAndroidClient.connect(mMqttConnectOptions, null, iMqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection() {
        if (mqttAndroidClient.isConnected()) {
            try {
                IMqttToken disconnect = mqttAndroidClient.disconnect();
                disconnect.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        LogUtils.e(TAG, "断开链接", "断开链接成功");
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        LogUtils.e(TAG, "断开链接", "断开链接失败" + exception.getMessage());
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
            String name = info.getTypeName();
            Log.i(TAG, "当前网络名称：" + name);
            return true;
        } else {
            Log.i(TAG, "没有可用网络");
            /*没有可用网络的时候，延迟3秒再尝试重连*/
            HandlerUtil.getInstance(this).postDelayed(new Runnable() {
                @Override
                public void run() {
                    doClientConnection();
                }
            }, 3000);
            return false;
        }
    }


    //MQTT是否连接成功的监听
    private IMqttActionListener iMqttActionListener = new IMqttActionListener() {

        @Override
        public void onSuccess(IMqttToken arg0) {
            if (mMyEmqttConnectListener != null) {
                mMyEmqttConnectListener.onConnectSuccess();
            }
            LogUtils.e(TAG, "链接状态：", "链接成功");
            subscribe(TOPIC_BOSS);
        }

        @Override
        public void onFailure(IMqttToken arg0, Throwable arg1) {
            if (mMyEmqttConnectListener != null) {
                mMyEmqttConnectListener.onConnectFailure();
            }
            if (arg0 instanceof MqttException) {
                LogUtils.e(TAG, "链接状态：", "链接失败" + ((MqttException) arg1).getMessage());
            }
            HandlerUtil.getInstance(getApplicationContext()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        doClientConnection();
                    }
                }
            }, 3000);
        }
    };


    //订阅主题的回调
    private MqttCallback mqttCallback = new MqttCallback() {

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            LogUtils.e(topic, message.toString());
            if (mMyEmqttMesgListener != null) {
                mMyEmqttMesgListener.messageArrived(topic, message.toString());
            }
            LogUtils.e(TAG, "收到的消息", "主题：" + topic + "  收到的消息：" + message.toString());
            //收到其他客户端的消息后，响应给对方告知消息已到达或者消息有问题等
            if (TOPIC_BOSS.equals(topic)) {
                EventBus.getDefault().post(new BossMsgEvent(message.toString()));
            }
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {

        }

        @Override
        public void connectionLost(Throwable arg0) {
            if (mMyEmqttConnectListener != null) {
                mMyEmqttConnectListener.onConnectFailure();
            }
            LogUtils.e(TAG, "链接状态：", "链接断开");

            HandlerUtil.getInstance(getApplicationContext()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        doClientConnection();
                    }
                }
            }, 300);
        }
    };


    @Override
    public void onDestroy() {
        try {
            cleanSubscribe(TOPIC_BOSS);
            if (mqttAndroidClient != null) {
                mqttAndroidClient.disconnect(); //断开连接
                mqttAndroidClient.unregisterResources();
                mqttAndroidClient = null;
            }
            LogUtils.e(TAG, "服务关闭", "资源释放成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }


    public static void addMyEmqttMesgListener(MyEmqttMesgListener myEmqttMesgListener) {
        mMyEmqttMesgListener = myEmqttMesgListener;
    }

    public static void addMyEmqttConnectListener(MyEmqttConnectListener myEmqttConnectListener) {
        mMyEmqttConnectListener = myEmqttConnectListener;
    }

    public static void addMyEmqttSubscribeListener(MyEmqttSubscribeListener myEmqttSubscribeListener) {
        mMyEmqttSubscribeListener = myEmqttSubscribeListener;
    }


}



