package com.qpyy.libcommon.http;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.MetaDataUtils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.qpyy.libcommon.BuildConfig;
import com.qpyy.libcommon.base.AccessTokenInterceptor;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.base.MyConverterFactory;
import com.qpyy.libcommon.bean.CheckImageResp;
import com.qpyy.libcommon.bean.CheckTxtResp;
import com.qpyy.libcommon.bean.EmChatUserInfo;
import com.qpyy.libcommon.bean.SignSwitchModel;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.http.transform.DefaultTransformer;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.utils.SystemUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitClient {

    private static RetrofitClient INSTANCE;
    private static ApiServer sApiServer;
    public static final int DEFAULT_TIME_OUT = 60;
    private static OkHttpClient client;
    private final Retrofit mRetrofit;

    public OkHttpClient getHttpClient() {
        return client;
    }


    private OkHttpClient provideOkHttpClient() {
        final Map<String, String> headers = new HashMap<>();
        headers.put("deviceId", SystemUtils.getShortClientID(BaseApplication.getIns()));
        headers.put("appVersion", BuildConfig.VERSION_NAME + "." + BuildConfig.VERSION_CODE);
        headers.put("versionName", BuildConfig.VERSION_NAME);
        headers.put("versionCode", String.valueOf(BuildConfig.VERSION_CODE));
        headers.put("clientType", "android");
        headers.put("emulator", BaseApplication.getIns().emulator);
        headers.put("deviceName", SystemUtils.getDeviceBrand() + SystemUtils.getSystemModel() + SystemUtils.getSystemVersion());
        try {
            String channelId = MetaDataUtils.getMetaDataInApp("TD_CHANNEL_ID");
            headers.put("CHANNELID", channelId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SetCookieCache cookieCache = new SetCookieCache();
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(cookieCache, new SharedPrefsCookiePersistor(BaseApplication.getIns()));
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new AccessTokenInterceptor(headers))
                .cookieJar(cookieJar)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .build();
        RetrofitClient.client = client;
        return client;
    }

    private Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(MyConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)

                .build();
    }

    private RetrofitClient() {
        mRetrofit = provideRetrofit(provideOkHttpClient());
        sApiServer = mRetrofit.create(ApiServer.class);
    }

    public static RetrofitClient getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitClient();
                }
            }
        }
        return INSTANCE;
    }

    public <T> T createApiClient(Class<T> apiClientClass) {
        return mRetrofit.create(apiClientClass);
    }

    public void login() {
//        login("17326032805", "123456");
        login("18229732986", "123456");
    }

    public void login(String mobile, String password) {
        sApiServer.login(mobile, password, null, 1)
                .compose(new DefaultTransformer<>()).
                subscribe(new BaseObserver<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        LogUtils.e("登录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
                        SpUtils.putToken(userBean.getToken());
                        BaseApplication.getIns().setUser(userBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void sendCode(String mobile, int type, BaseObserver<String> observer) {
        sApiServer.sendCode(mobile, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void login(String mobile, String password, String code, int type, BaseObserver<UserBean> observer) {
        sApiServer.login(mobile, password, code, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void oauthLogin(String netease_token, String access_token, int type, BaseObserver<UserBean> observer) {
        sApiServer.oauthLogin(netease_token, access_token, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void thirdPartyLogin(String openId, int three_party, String nickname, String head_pic, BaseObserver<UserBean> observer) {
        sApiServer.thirdPartyLogin(openId, three_party, nickname, head_pic).compose(new DefaultTransformer<BaseModel<UserBean>, UserBean>()).subscribe(observer);
    }

    public void getBalance(BaseObserver<String> observer) {
        sApiServer.getBalance().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void followUser(String userId, int type, BaseObserver<String> observer) {
        sApiServer.follow(userId, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //1昵称 2房间名称 3公屏 4 私聊 5个性签名
    public void checkTxt(String content, String type, BaseObserver<CheckTxtResp> observer) {
        sApiServer.checkTxt(content, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void checkImage(String image, String type, BaseObserver<CheckImageResp> observer) {
        sApiServer.checkImage(image, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void signSwitch(BaseObserver<SignSwitchModel> observer) {
        sApiServer.signSwitch().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getInfoByEmChat(String emChatUserName, BaseObserver<EmChatUserInfo> observer) {
        sApiServer.getInfoByEmChat(emChatUserName).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //环信公屏和私聊消息
    public void sendTxtMessage(String user_id, String type, String content, String room_id, BaseObserver<String> observer) {
        sApiServer.sendTxtMessage(user_id, type, content, room_id).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //发生图片
    public void sendImageMessage(String user_id, String image, BaseObserver<String> observer) {
        sApiServer.sendImgMessage(user_id, image).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //发生录音
    public void sendAudioMessage(String user_id, String audio, BaseObserver<String> observer) {
        sApiServer.sendAudioMessage(user_id, audio).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }


}
