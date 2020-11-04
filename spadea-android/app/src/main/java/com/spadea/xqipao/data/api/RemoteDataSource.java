package com.spadea.xqipao.data.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.blankj.utilcode.util.MetaDataUtils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import com.qpyy.libcommon.BuildConfig;
import com.qpyy.libcommon.bean.RechargeInfoModel;
import com.qpyy.libcommon.bean.TransferUserModel;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.AddOrderModel;
import com.spadea.xqipao.data.AgreeApplyModel;
import com.spadea.xqipao.data.AnchorRankingListResp;
import com.spadea.xqipao.data.AppUpdateModel;
import com.spadea.xqipao.data.AppealingModel;
import com.spadea.xqipao.data.ApplyWheatWaitModel;
import com.spadea.xqipao.data.BannerModel;
import com.spadea.xqipao.data.BaseModel;
import com.spadea.xqipao.data.BlacListSectionBean;
import com.spadea.xqipao.data.CashTypeModel;
import com.spadea.xqipao.data.CatFishingModel;
import com.spadea.xqipao.data.CatHelpModel;
import com.spadea.xqipao.data.CategoriesModel;
import com.spadea.xqipao.data.CharmModel;
import com.spadea.xqipao.data.ClosePitModel;
import com.spadea.xqipao.data.EarningsModel;
import com.spadea.xqipao.data.EmojiModel;
import com.spadea.xqipao.data.EvaluateModel;
import com.spadea.xqipao.data.FishInfoBean;
import com.spadea.xqipao.data.FmApplyWheatResp;
import com.spadea.xqipao.data.FriendModel;
import com.spadea.xqipao.data.GameLog;
import com.spadea.xqipao.data.GiftModel;
import com.spadea.xqipao.data.GuildInfo;
import com.spadea.xqipao.data.GuildState;
import com.spadea.xqipao.data.HelpModel;
import com.spadea.xqipao.data.HelpTitleModel;
import com.spadea.xqipao.data.LabelModel;
import com.spadea.xqipao.data.LastOrderMsg;
import com.spadea.xqipao.data.LatelyVisitInfo;
import com.spadea.xqipao.data.LogoutReasonModel;
import com.spadea.xqipao.data.LuckGiftBean;
import com.spadea.xqipao.data.ManageRoomModel;
import com.spadea.xqipao.data.MusicModel;
import com.spadea.xqipao.data.MyGuildInfo;
import com.spadea.xqipao.data.MyManageRoomModel;
import com.spadea.xqipao.data.MyOrderSwitch;
import com.spadea.xqipao.data.MyPhotoItem;
import com.spadea.xqipao.data.MyProductsModel;
import com.spadea.xqipao.data.NameAuthResult;
import com.spadea.xqipao.data.NewsModel;
import com.spadea.xqipao.data.NobilityInfo;
import com.spadea.xqipao.data.NobilityModel;
import com.spadea.xqipao.data.OnlineModel;
import com.spadea.xqipao.data.OrderDetailResp;
import com.spadea.xqipao.data.OrderMsgResp;
import com.spadea.xqipao.data.OrderPayModel;
import com.spadea.xqipao.data.OrderSkillSelectItem;
import com.spadea.xqipao.data.OrdersModel;
import com.spadea.xqipao.data.OrdersResp;
import com.spadea.xqipao.data.PitCountDownBean;
import com.spadea.xqipao.data.ProductsModel;
import com.spadea.xqipao.data.ProfitModel;
import com.spadea.xqipao.data.ProtectedItemBean;
import com.spadea.xqipao.data.ProtectedRankingListResp;
import com.spadea.xqipao.data.RegionListBean;
import com.spadea.xqipao.data.RoomAuthModel;
import com.spadea.xqipao.data.RoomBannedModel;
import com.spadea.xqipao.data.RoomBgBean;
import com.spadea.xqipao.data.RoomDetailBean;
import com.spadea.xqipao.data.RoomDetailModel;
import com.spadea.xqipao.data.RoomExtraModel;
import com.spadea.xqipao.data.RoomInfoModel;
import com.spadea.xqipao.data.RoomManageModel;
import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.data.RoomPitInfo;
import com.spadea.xqipao.data.RoomPitUserModel;
import com.spadea.xqipao.data.RoomPollModel;
import com.spadea.xqipao.data.RoomRankingModel;
import com.spadea.xqipao.data.RoomShutUp;
import com.spadea.xqipao.data.RoomTypeModel;
import com.spadea.xqipao.data.RoomUserInfo;
import com.spadea.xqipao.data.RoomUserInfoModel;
import com.spadea.xqipao.data.RowWheatModel;
import com.spadea.xqipao.data.SearchRoomInfo;
import com.spadea.xqipao.data.SearchUserInfo;
import com.spadea.xqipao.data.SearchUserModel;
import com.spadea.xqipao.data.SignHistoryResp;
import com.spadea.xqipao.data.SignSwitchModel;
import com.spadea.xqipao.data.SkillApplyModel;
import com.spadea.xqipao.data.SkillPriceSet;
import com.spadea.xqipao.data.SkillSection;
import com.spadea.xqipao.data.SkillSetting;
import com.spadea.xqipao.data.TopTwoModel;
import com.spadea.xqipao.data.UpdateOrderModel;
import com.spadea.xqipao.data.UpdateUserAvatarResp;
import com.spadea.xqipao.data.UserBankModel;
import com.spadea.xqipao.data.UserInfoDataModel;
import com.spadea.xqipao.data.UserInfoModel;
import com.spadea.xqipao.data.UserSkillInfo;
import com.spadea.xqipao.data.UserSkillItem;
import com.spadea.xqipao.data.UsingProductsModel;
import com.spadea.xqipao.data.VerifyOrderTimeModel;
import com.spadea.xqipao.data.VipInfo;
import com.spadea.xqipao.data.WeekStarModel;
import com.spadea.xqipao.data.WheatModel;
import com.spadea.xqipao.data.WinJackpotModel;
import com.spadea.xqipao.data.WxPayModel;
import com.spadea.xqipao.data.api.transform.BaseTransformer;
import com.spadea.xqipao.data.api.transform.DefaultTransformer;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.SystemUtils;
import com.qpyy.libcommon.bean.RankInfo;
import com.qpyy.libcommon.bean.UserBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RemoteDataSource implements DataSource {

    private static RemoteDataSource INSTANCE;
    private static ApiServer sApiServer;
    public static final int DEFAULT_TIME_OUT = 60;
    private static OkHttpClient client;

    public OkHttpClient getHttpClient() {
        return client;
    }


    private OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                if (BuildConfig.DEBUG) {
                    Log.e("网路请求", message);
                }
            }
        });
        final Map<String, String> headers = new HashMap<>();
        headers.put("deviceId", SystemUtils.getShortClientID(MyApplication.getContext()));
        headers.put("appVersion", BuildConfig.VERSION_NAME + BuildConfig.VERSION_CODE);
        headers.put("versionName", BuildConfig.VERSION_NAME);
        headers.put("versionCode", String.valueOf(BuildConfig.VERSION_CODE));
        headers.put("clientType", "android");
        headers.put("emulator", MyApplication.getIns().emulator);
        headers.put("deviceName", SystemUtils.getDeviceBrand() + SystemUtils.getSystemModel() + SystemUtils.getSystemVersion());
        String channelId = MetaDataUtils.getMetaDataInApp("TD_CHANNEL_ID");
        LogUtils.e("CHANNELID", channelId);
        headers.put("CHANNELID", channelId);

        SetCookieCache cookieCache = new SetCookieCache();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(cookieCache, new SharedPrefsCookiePersistor(MyApplication.getInstance()));
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new AccessTokenInterceptor(headers))
                .cookieJar(cookieJar)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .build();
        RemoteDataSource.client = client;
        return client;
    }

    private Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(MyConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(com.qpyy.libcommon.BuildConfig.BASE_URL)
                .client(client)

                .build();
    }

    private RemoteDataSource() {
        Retrofit retrofit = provideRetrofit(provideOkHttpClient());
        sApiServer = retrofit.create(ApiServer.class);
    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (RemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getCharmList(String token, int type, String roomId, BaseObserver<CharmModel> observer) {
        sApiServer.getCharmList(token, type, roomId).compose(new DefaultTransformer<BaseModel<CharmModel>, CharmModel>())
                .subscribe(observer);
    }

    @Override
    public void getWealthList(String token, int type, String roomId, BaseObserver<CharmModel> observer) {
        sApiServer.getWealthList(token, type, roomId).compose(new DefaultTransformer<BaseModel<CharmModel>, CharmModel>())
                .subscribe(observer);
    }

    @Override
    public void getWeekStarList(String token, BaseObserver<WeekStarModel> observer) {
        sApiServer.getWeekStarList(token).compose(new DefaultTransformer<BaseModel<WeekStarModel>, WeekStarModel>())
                .subscribe(observer);
    }

    @Override
    public void getRoomRankingList(String token, BaseObserver<List<RoomRankingModel>> observer) {
        sApiServer.getRoomRankingList(token).compose(new DefaultTransformer<BaseModel<List<RoomRankingModel>>, List<RoomRankingModel>>())
                .subscribe(observer);
    }

    @Override
    public void verificationRoomPassword(String token, String id, String password, BaseObserver<BaseModel> observer) {
        sApiServer.verificationRoomPassword(token, id, password).compose(new BaseTransformer())
                .safeSubscribe(observer);
    }

    @Override
    public void addBank(String token, String bankNum, String cardholder, int bankType, String bankName, String mobile, String bankZhi, String cardNumber, String code, BaseObserver<String> observer) {
        sApiServer.addBank(token, bankNum, cardholder, bankType, bankName, mobile, bankZhi, cardNumber, code).compose(new DefaultTransformer<BaseModel<String>, String>())
                .subscribe(observer);
    }

    @Override
    public void sendCode(String token, String mobile, int type, BaseObserver<String> observer) {
        sApiServer.sendCode(token, mobile, type).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getUserBank(String token, BaseObserver<UserBankModel> observer) {
        sApiServer.getUserBank(token).compose(new DefaultTransformer<BaseModel<UserBankModel>, UserBankModel>()).subscribe(observer);
    }

    public void getAlipayInfo(String token, BaseObserver<UserBankModel> observer) {
        sApiServer.getAlipayInfo(token).compose(new DefaultTransformer<BaseModel<UserBankModel>, UserBankModel>()).subscribe(observer);
    }

    @Override
    public void userRecharge(String token, String money, int type, BaseObserver<String> observer) {
        sApiServer.userRecharge(token, money, type).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void rechargeInfo(String token, BaseObserver<ArrayList<RechargeInfoModel>> observer) {
        sApiServer.rechargeInfo(token).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    @Override
    public void aliPay(String token, String userId, int type, String id, BaseObserver<String> observer) {
        sApiServer.aliPay(token, userId, type, id).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void wxPay(String token, String userId, int type, String id, BaseObserver<WxPayModel> observer) {
        sApiServer.wxPay(token, userId, type, id).compose(new DefaultTransformer<BaseModel<WxPayModel>, WxPayModel>()).subscribe(observer);
    }

    @Override
    public void editBank(String token, String cardholder, String bank_name, String mobile, String card_number, String id, String bank_num, String bank_zhi, String code, BaseObserver<String> observer) {
        sApiServer.editBank(token, cardholder, bank_name, mobile, card_number, id, bank_num, bank_zhi, code).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void bindAlipay(String token, String id, String name, String code, BaseObserver<String> observer) {
        sApiServer.addAlipay(token, id, name, code).compose(new DefaultTransformer<BaseModel<String>, String>())
                .subscribe(observer);
    }

    @Override
    public void userWithdraw(String token, String bank_id, String number, String password, BaseObserver<String> observer) {
        sApiServer.userWithdraw(token, bank_id, number, password).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void userWithdraw(String token, String number, BaseObserver<String> observer) {
        sApiServer.userWithdraw(token, number).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void login(String mobile, String password, String code, int type, BaseObserver<UserBean> observer) {
        sApiServer.login(mobile, password, code, type).compose(new DefaultTransformer<BaseModel<UserBean>, UserBean>()).subscribe(observer);
    }

    @Override
    public void oauthLogin(String netease_token, String access_token, int type, BaseObserver<UserBean> observer) {
        sApiServer.oauthLogin(netease_token, access_token, type).compose(new DefaultTransformer<BaseModel<UserBean>, UserBean>()).subscribe(observer);
    }


    @Override
    public void setUserSex(String userId, int sex, BaseObserver<String> observer) {
        sApiServer.setUserSex(userId, sex).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void thirdPartyLogin(String openId, int three_party, String nickname, String head_pic, BaseObserver<UserBean> observer) {
        sApiServer.thirdPartyLogin(openId, three_party, nickname, head_pic).compose(new DefaultTransformer<BaseModel<UserBean>, UserBean>()).subscribe(observer);
    }

    @Override
    public void getEarnings(String token, BaseObserver<EarningsModel> observer) {
        sApiServer.getEarnings(token).compose(new DefaultTransformer<BaseModel<EarningsModel>, EarningsModel>()).subscribe(observer);
    }

    @Override
    public void convertEarnings(String token, String number, String password, BaseObserver<String> observer) {
        sApiServer.convertEarnings(token, number, password).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void exchangeRoomEarnings(String num, String password, BaseObserver<String> observer) {
        sApiServer.exchangeRoomEarnings(num, password).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void userProfit(BaseObserver<ProfitModel> observer) {
        sApiServer.userProfit().compose(new DefaultTransformer<BaseModel<ProfitModel>, ProfitModel>()).subscribe(observer);
    }

    public void roomProfit(BaseObserver<ProfitModel> observer) {
        sApiServer.roomProfit().compose(new DefaultTransformer<BaseModel<ProfitModel>, ProfitModel>()).subscribe(observer);
    }

    public void applyRoomProfit(String password, String number, BaseObserver<String> observer) {
        sApiServer.applyRoomProfit(password, number).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getCashLog(String token, int p, int change_type, BaseObserver<List<EarningsModel.EarningInfo>> observer) {
        sApiServer.getCashLog(token, p, change_type).compose(new DefaultTransformer<BaseModel<List<EarningsModel.EarningInfo>>, List<EarningsModel.EarningInfo>>())
                .subscribe(observer);
    }

    @Override
    public void getWinRanking(int type, String token, BaseObserver<List<CatFishingModel>> observer) {
        sApiServer.getWinRanking(type, token).compose(new DefaultTransformer<BaseModel<List<CatFishingModel>>, List<CatFishingModel>>())
                .subscribe(observer);
    }

    @Override
    public void getCatHelp(String token, BaseObserver<CatHelpModel> observer) {
        sApiServer.getCatHelp(token).compose(new DefaultTransformer<BaseModel<CatHelpModel>, CatHelpModel>())
                .subscribe(observer);
    }


    @Override
    public void getCatWinJackpot(String token, String type, BaseObserver<List<WinJackpotModel>> observer) {
        sApiServer.getCatWinJackpot(token, type).compose(new DefaultTransformer<BaseModel<List<WinJackpotModel>>, List<WinJackpotModel>>())
                .subscribe(observer);
    }

    @Override
    public void getBalance(String token, BaseObserver<String> observer) {
        sApiServer.getBalance(token).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void startFishing(String token, int number, int type, BaseObserver<LuckGiftBean> observer) {
        sApiServer.startFishing(token, number, type).compose(new DefaultTransformer<BaseModel<LuckGiftBean>, LuckGiftBean>())
                .subscribe(observer);
    }

    @Override
    public void getRoomDetails(String token, String id, String password, BaseObserver<RoomDetailModel> observer) {
        sApiServer.getRoomDetails(token, id, password).compose(new DefaultTransformer<BaseModel<RoomDetailModel>, RoomDetailModel>()).subscribe(observer);
    }

    @Override
    public void getFaceList(String token, BaseObserver<List<EmojiModel>> observer) {
        sApiServer.getFaceList(token).compose(new DefaultTransformer<BaseModel<List<EmojiModel>>, List<EmojiModel>>())
                .subscribe(observer);
    }

    @Override
    public void joinRoom(String token, String roomId, BaseObserver<RoomInfoModel> observer) {
        sApiServer.joinRoom(token, roomId).compose(new DefaultTransformer<BaseModel<RoomInfoModel>, RoomInfoModel>()).subscribe(observer);
    }


    @Override
    public void getSearChUser(String token, String roomId, String keyword, int type, BaseObserver<List<SearchUserModel>> observer) {
        sApiServer.getSearChUser(token, roomId, keyword, type).compose(new DefaultTransformer<BaseModel<List<SearchUserModel>>, List<SearchUserModel>>())
                .subscribe(observer);
    }

    @Override
    public void getRoomList(String roomId, int type, BaseObserver<List<SearchUserModel>> observer) {
        sApiServer.getRoomList(roomId, type).compose(new DefaultTransformer<BaseModel<List<SearchUserModel>>, List<SearchUserModel>>())
                .subscribe(observer);
    }

    @Override
    public void addManager(String token, String roomId, String userId, BaseObserver<RoomManageModel> observer) {
        sApiServer.addManager(token, roomId, userId).compose(new DefaultTransformer<BaseModel<RoomManageModel>, RoomManageModel>()).subscribe(observer);
    }

    @Override
    public void deleteManager(String token, String roomId, String userId, BaseObserver<RoomManageModel> observer) {
        sApiServer.deleteManager(token, roomId, userId).compose(new DefaultTransformer<BaseModel<RoomManageModel>, RoomManageModel>()).subscribe(observer);
    }

    @Override
    public void addRorbid(String token, String roomId, String userId, BaseObserver<String> observer) {
        sApiServer.addRorbid(token, roomId, userId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void deleteForbid(String token, String roomId, String userId, BaseObserver<String> observer) {
        sApiServer.deleteForbid(token, roomId, userId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void addRoomCollect(String token, String roomId, BaseObserver<String> observer) {
        sApiServer.addRoomCollect(token, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void applyWheat(String token, String roomId, String pitNumber, BaseObserver<String> observer) {
        sApiServer.applyWheat(token, roomId, pitNumber).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void downWheat(String token, String roomId, BaseObserver<String> observer) {
        sApiServer.downWheat(token, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void switchVoice(String token, String id, int type, BaseObserver<String> observer) {
        sApiServer.switchVoice(token, id, type).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void clearCardiac(String token, String roomId, String pitNumber, BaseObserver<String> observer) {
        sApiServer.clearCardiac(token, roomId, pitNumber).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void shutUp(String token, String userId, String type, String pitNumber, String id, BaseObserver<String> observer) {
        sApiServer.shutUp(token, userId, type, pitNumber, id).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void closePit(String token, String type, String pitNumber, String id, BaseObserver<ClosePitModel> observer) {
        sApiServer.closePit(token, type, pitNumber, id).compose(new DefaultTransformer<BaseModel<ClosePitModel>, ClosePitModel>()).subscribe(observer);
    }

    @Override
    public void getRoomUserInfo(String token, String userId, int visit, BaseObserver<RoomUserInfoModel> observer) {
        sApiServer.getRoomUserInfo(token, userId, visit).compose(new DefaultTransformer<BaseModel<RoomUserInfoModel>, RoomUserInfoModel>()).subscribe(observer);
    }

    @Override
    public void giftWall(String token, BaseObserver<List<GiftModel>> observer) {
        sApiServer.giftWall(token).compose(new DefaultTransformer<BaseModel<List<GiftModel>>, List<GiftModel>>())
                .subscribe(observer);
    }

    @Override
    public void userBackPack(String token, BaseObserver<List<GiftModel>> observer) {
        sApiServer.userBackPack(token).compose(new DefaultTransformer<BaseModel<List<GiftModel>>, List<GiftModel>>())
                .subscribe(observer);
    }

    @Override
    public void getRoomExtra(String token, String roomId, String password, BaseObserver<RoomExtraModel> observer) {
        sApiServer.getRoomExtra(token, roomId, password).compose(new DefaultTransformer<BaseModel<RoomExtraModel>, RoomExtraModel>()).subscribe(observer);
    }

    @Override
    public void downUserWheat(String token, String pitNumber, String roomId, String userId, BaseObserver<String> observer) {
        sApiServer.downUserWheat(token, pitNumber, roomId, userId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void follow(String token, String userId, int type, BaseObserver<String> observer) {
        sApiServer.follow(token, userId, type).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void setRoomBanned(String token, String roomId, String userId, int type, BaseObserver<RoomBannedModel> observer) {
        sApiServer.setRoomBanned(token, roomId, userId, type).compose(new DefaultTransformer<BaseModel<RoomBannedModel>, RoomBannedModel>()).subscribe(observer);
    }

    @Override
    public void editRoom(String token, String coverPicture, String bgPicture, String password, String playing, String roomId, String roomName, String typeId, String labelId, String greeting, String wheat, String is_password, BaseObserver<String> observer) {
        sApiServer.editRoom(token, coverPicture, bgPicture, password, playing, roomId, roomName, labelId, typeId, greeting, wheat, is_password).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void editRoomBg(String bgPicture, String roomId, BaseObserver<String> observer) {
        sApiServer.editRoomBg(MyApplication.getInstance().getToken(), bgPicture, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void giveGift(String token, String userId, String giftId, String roomId, String pit, String num, BaseObserver<RankInfo> observer) {
        sApiServer.giveGift(token, userId, giftId, roomId, pit, num).compose(new DefaultTransformer<BaseModel<RankInfo>, RankInfo>()).subscribe(observer);
    }

    @Override
    public void giveBackGift(String token, String userId, String giftId, String roomId, String pit, String num, BaseObserver<RankInfo> observer) {
        sApiServer.giveBackGift(token, userId, giftId, roomId, pit, num).compose(new DefaultTransformer<BaseModel<RankInfo>, RankInfo>()).subscribe(observer);
    }

    @Override
    public void applyWheatList(String token, String roomId, BaseObserver<List<RowWheatModel>> observer) {
        sApiServer.applyWheatList(token, roomId).compose(new DefaultTransformer<BaseModel<List<RowWheatModel>>, List<RowWheatModel>>())
                .subscribe(observer);
    }

    @Override
    public void deleteApply(String token, String ids, String roomId, BaseObserver<String> observer) {
        sApiServer.deleteApply(token, ids, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void applyWheatWait(String token, String roomId, String pitNumber, BaseObserver<ApplyWheatWaitModel> observer) {
        sApiServer.applyWheatWait(token, roomId, pitNumber).compose(new DefaultTransformer<BaseModel<ApplyWheatWaitModel>, ApplyWheatWaitModel>()).subscribe(observer);
    }

    @Override
    public void agreeApply(String token, String id, String roomId, BaseObserver<AgreeApplyModel> observer) {
        sApiServer.agreeApply(token, id, roomId).compose(new DefaultTransformer<BaseModel<AgreeApplyModel>, AgreeApplyModel>()).subscribe(observer);
    }

    @Override
    public void agreeApplyAll(String token, String roomId, BaseObserver<String> observer) {
        sApiServer.agreeApplyAll(token, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void clearRoomCardiac(String token, String roomId, BaseObserver<String> observer) {
        sApiServer.clearRoomCardiac(token, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void setRoomCardiac(String token, String roomId, int state, BaseObserver<String> observer) {
        sApiServer.setRoomCardiac(token, roomId, state).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void removeFavorite(String token, String roomId, BaseObserver<String> observer) {
        sApiServer.removeFavorite(token, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void kickOut(String token, String userId, String roomId, BaseObserver<String> observer) {
        sApiServer.kickOut(token, userId, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void quit(String token, String roomId, BaseObserver<String> observer) {
        sApiServer.quit(token, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void roomAuth(String token, String roomId, BaseObserver<RoomAuthModel> observer) {
        sApiServer.roomAuth(token, roomId).compose(new DefaultTransformer<BaseModel<RoomAuthModel>, RoomAuthModel>()).subscribe(observer);
    }

    @Override
    public void online(BaseObserver<String> observer) {
        sApiServer.online().compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getBanners(BaseObserver<List<BannerModel>> observer) {
        sApiServer.getBanners().compose(new DefaultTransformer<BaseModel<List<BannerModel>>, List<BannerModel>>())
                .subscribe(observer);
    }

    @Override
    public void roomType(BaseObserver<List<RoomTypeModel>> observer) {
        sApiServer.roomType().compose(new DefaultTransformer<BaseModel<List<RoomTypeModel>>, List<RoomTypeModel>>())
                .subscribe(observer);
    }

    @Override
    public void roomList(String typeId, BaseObserver<List<RoomModel>> observer) {
        sApiServer.roomList(typeId).compose(new DefaultTransformer<BaseModel<List<RoomModel>>, List<RoomModel>>())
                .subscribe(observer);
    }

    @Override
    public void hotRoom(String userId, BaseObserver<List<RoomModel>> observer) {
        sApiServer.hotRoom(userId).compose(new DefaultTransformer<BaseModel<List<RoomModel>>, List<RoomModel>>())
                .subscribe(observer);
    }

    @Override
    public void manageRoom(int page, BaseObserver<MyManageRoomModel> observer) {
        sApiServer.manageRoom(page).compose(new DefaultTransformer<BaseModel<MyManageRoomModel>, MyManageRoomModel>())
                .subscribe(observer);
    }

    @Override
    public void collectRoom(int page, BaseObserver<List<ManageRoomModel>> observer) {
        sApiServer.collectRoom(page).compose(new DefaultTransformer<BaseModel<List<ManageRoomModel>>, List<ManageRoomModel>>())
                .subscribe(observer);
    }

    @Override
    public void isFoundRoom(String token, BaseObserver<String> observer) {
        sApiServer.isFoundRoom(token).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void friendList(int page, BaseObserver<List<FriendModel>> observer) {
        sApiServer.friendList(page).compose(new DefaultTransformer<BaseModel<List<FriendModel>>, List<FriendModel>>())
                .subscribe(observer);
    }

    @Override
    public void followList(int page, BaseObserver<List<FriendModel>> observer) {
        sApiServer.followList(page).compose(new DefaultTransformer<BaseModel<List<FriendModel>>, List<FriendModel>>())
                .subscribe(observer);

    }

    @Override
    public void fansList(int page, BaseObserver<List<FriendModel>> observer) {
        sApiServer.fansList(page).compose(new DefaultTransformer<BaseModel<List<FriendModel>>, List<FriendModel>>())
                .subscribe(observer);
    }

    @Override
    public void userInfo(BaseObserver<UserInfoModel> observer) {
        sApiServer.userInfo().compose(new DefaultTransformer<BaseModel<UserInfoModel>, UserInfoModel>()).subscribe(observer);
    }

    @Override
    public void categories(BaseObserver<List<CategoriesModel>> observer) {
        sApiServer.categories().compose(new DefaultTransformer<BaseModel<List<CategoriesModel>>, List<CategoriesModel>>())
                .subscribe(observer);
    }

    @Override
    public void products(String categoryId, BaseObserver<List<ProductsModel>> observer) {
        sApiServer.products(categoryId).compose(new DefaultTransformer<BaseModel<List<ProductsModel>>, List<ProductsModel>>())
                .subscribe(observer);
    }

    @Override
    public void buyShop(String friendId, String productId, String priceId, BaseObserver<String> observer) {
        sApiServer.buyShop(friendId, productId, priceId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void userInfoData(String userId, String emchatUsername, int visit, BaseObserver<UserInfoDataModel> observer) {
        sApiServer.userInfoData(userId, emchatUsername, visit).compose(new DefaultTransformer<BaseModel<UserInfoDataModel>, UserInfoDataModel>()).subscribe(observer);
    }

    @Override
    public void searchUser(String keyword, BaseObserver<List<SearchUserInfo>> observer) {
        sApiServer.searchUser(keyword).compose(new DefaultTransformer<BaseModel<List<SearchUserInfo>>, List<SearchUserInfo>>())
                .subscribe(observer);
    }

    @Override
    public void searchRoom(String keyword, BaseObserver<List<SearchRoomInfo>> observer) {
        sApiServer.searchRoom(keyword).compose(new DefaultTransformer<BaseModel<List<SearchRoomInfo>>, List<SearchRoomInfo>>())
                .subscribe(observer);
    }

    @Override
    public void vipInfo(BaseObserver<VipInfo> observer) {
        sApiServer.vipinfo().compose(new DefaultTransformer<BaseModel<VipInfo>, VipInfo>()).subscribe(observer);
    }

    @Override
    public void serviceUser(BaseObserver<String> observer) {
        sApiServer.serviceUser().compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void articleCategories(BaseObserver<List<HelpTitleModel>> observer) {
        sApiServer.articleCategories().compose(new DefaultTransformer<BaseModel<List<HelpTitleModel>>, List<HelpTitleModel>>()).subscribe(observer);
    }

    @Override
    public void articleList(String articleCatId, BaseObserver<List<HelpModel>> observer) {
        sApiServer.articleList(articleCatId).compose(new DefaultTransformer<BaseModel<List<HelpModel>>, List<HelpModel>>()).subscribe(observer);
    }

    @Override
    public void userNobilityInfo(BaseObserver<NobilityInfo> observer) {
        sApiServer.userNobilityInfo().compose(new DefaultTransformer<BaseModel<NobilityInfo>, NobilityInfo>()).subscribe(observer);
    }

    @Override
    public void nobility(BaseObserver<List<NobilityModel>> observer) {
        sApiServer.nobility().compose(new DefaultTransformer<BaseModel<List<NobilityModel>>, List<NobilityModel>>()).subscribe(observer);
    }

    @Override
    public void buyNobility(String nobilityId, BaseObserver<String> observer) {
        sApiServer.buyNobility(nobilityId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void renewNobility(String day, BaseObserver<String> observer) {
        sApiServer.renewNobility(day).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void myProducts(String categoryId, BaseObserver<List<MyProductsModel>> observer) {
        sApiServer.myProducts(categoryId).compose(new DefaultTransformer<BaseModel<List<MyProductsModel>>, List<MyProductsModel>>()).subscribe(observer);
    }

    @Override
    public void myUsingProducts(String categoryId, BaseObserver<UsingProductsModel> observer) {
        sApiServer.myUsingProducts(categoryId).compose(new DefaultTransformer<BaseModel<UsingProductsModel>, UsingProductsModel>()).subscribe(observer);
    }

    @Override
    public void useProduct(String id, BaseObserver<String> observer) {
        sApiServer.useProduct(id).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void downProduct(String id, BaseObserver<String> observer) {
        sApiServer.downProduct(id).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void updateUserInfo(String signature, String birthday, String constellation, String profession, String city_id, String user_photo, String sex, String head_picture, String nickname, String province_id, String userNo, String county_id, BaseObserver<String> observer) {
        sApiServer.updateUserInfo(signature, birthday, constellation, profession, city_id, user_photo, sex, head_picture, nickname, province_id, userNo, county_id).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void indexLabel(String categoryId, int p, BaseObserver<List<LabelModel>> observer) {
        sApiServer.indexLabel(categoryId, p).compose(new DefaultTransformer<BaseModel<List<LabelModel>>, List<LabelModel>>()).subscribe(observer);
    }

    @Override
    public void addLabel(String ids, BaseObserver<String> observer) {
        sApiServer.addLabel(ids).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void userNews(BaseObserver<NewsModel> observer) {
        sApiServer.userNews().compose(new DefaultTransformer<BaseModel<NewsModel>, NewsModel>()).subscribe(observer);
    }

    @Override
    public void cashType(BaseObserver<List<CashTypeModel>> observer) {
        sApiServer.cashType().compose(new DefaultTransformer<BaseModel<List<CashTypeModel>>, List<CashTypeModel>>()).subscribe(observer);
    }

    @Override
    public void roomOnline(String roomId, int page, BaseObserver<List<OnlineModel>> observer) {
        sApiServer.roomOnline(roomId, page).compose(new DefaultTransformer<BaseModel<List<OnlineModel>>, List<OnlineModel>>()).subscribe(observer);
    }

    @Override
    public void getRoomUserInfo(String roomId, String userId, BaseObserver<RoomUserInfo> observer) {
        sApiServer.getRoomUserInfo(roomId, userId).compose(new DefaultTransformer<BaseModel<RoomUserInfo>, RoomUserInfo>()).subscribe(observer);
    }

    @Override
    public void roomUserShutUp(String roomId, String userId, int type, BaseObserver<RoomShutUp> observer) {
        sApiServer.roomUserShutUp(roomId, userId, type).compose(new DefaultTransformer<BaseModel<RoomShutUp>, RoomShutUp>()).subscribe(observer);
    }

    @Override
    public void roomPitInfo(String roomId, String pitNumber, BaseObserver<RoomPitInfo> observer) {
        sApiServer.roomPitInfo(roomId, pitNumber).compose(new DefaultTransformer<BaseModel<RoomPitInfo>, RoomPitInfo>()).subscribe(observer);
    }

    @Override
    public void comeUser(String token, int pager, BaseObserver<List<LatelyVisitInfo>> observer) {
        sApiServer.comeUser(token, pager).compose(new DefaultTransformer<BaseModel<List<LatelyVisitInfo>>, List<LatelyVisitInfo>>()).subscribe(observer);
    }

    @Override
    public void getTopTwo(BaseObserver<TopTwoModel> observer) {
        sApiServer.getTopTwo().compose(new DefaultTransformer<BaseModel<TopTwoModel>, TopTwoModel>()).subscribe(observer);
    }

    @Override
    public void appUpdate(BaseObserver<AppUpdateModel> observer) {
        sApiServer.appUpdate().compose(new DefaultTransformer<BaseModel<AppUpdateModel>, AppUpdateModel>()).subscribe(observer);
    }

    @Override
    public void checkUpdate(BaseObserver<AppUpdateModel> observer) {
        sApiServer.checkUpdate().compose(new DefaultTransformer<BaseModel<AppUpdateModel>, AppUpdateModel>()).subscribe(observer);
    }

    @Override
    public void randomHotRoom(BaseObserver<String> observer) {
        sApiServer.randomHotRoom().compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void roomGetIn(String roomId, String password, BaseObserver<RoomDetailBean> observer) {
        sApiServer.roomGetIn(roomId, password).compose(new DefaultTransformer<BaseModel<RoomDetailBean>, RoomDetailBean>()).subscribe(observer);
    }

    @Override
    public void putOnWheat(String roomId, String userId, BaseObserver<String> observer) {
        sApiServer.putOnWheat(roomId, userId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void updatePassword(String roomId, String password, BaseObserver<String> observer) {
        sApiServer.updatePassword(roomId, password).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getRoomPitUser(String roomId, String userId, BaseObserver<List<RoomPitUserModel>> observer) {
        sApiServer.getRoomPitUser(roomId, userId).compose(new DefaultTransformer<BaseModel<List<RoomPitUserModel>>, List<RoomPitUserModel>>()).subscribe(observer);
    }

    @Override
    public void roomPoll(String roomId, int type, BaseObserver<RoomPollModel> observer) {
        sApiServer.roomPoll(roomId, type).compose(new DefaultTransformer<BaseModel<RoomPollModel>, RoomPollModel>()).subscribe(observer);
    }

    @Override
    public void getInRoomInfo(String roomId, BaseObserver<RoomDetailBean> observer) {
        sApiServer.getInRoomInfo(roomId).compose(new DefaultTransformer<BaseModel<RoomDetailBean>, RoomDetailBean>()).subscribe(observer);
    }

    @Override
    public void getRoomEnter(String token, String roomId, String password, BaseObserver<WheatModel> observer) {
        sApiServer.getRoomEnter(token, roomId, password).compose(new DefaultTransformer<BaseModel<WheatModel>, WheatModel>()).subscribe(observer);
    }

    @Override
    public void userFiles(BaseObserver<UserBean> observer) {
        sApiServer.userFiles().compose(new DefaultTransformer<BaseModel<UserBean>, UserBean>()).subscribe(observer);
    }

    @Override
    public void getRegionList(String parentId, BaseObserver<List<RegionListBean>> observer) {
        sApiServer.regionList(parentId).compose(new DefaultTransformer<BaseModel<List<RegionListBean>>, List<RegionListBean>>()).subscribe(observer);
    }

    @Override
    public void bindMobile(String mobile, String code, BaseObserver<String> observer) {
        sApiServer.bindMobile(mobile, code).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void resetPassword(String mobile, String code, String password, BaseObserver<String> observer) {
        sApiServer.resetPassword(mobile, code, password).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void searchMusic(String input, String filter, String type, int page, BaseObserver<List<MusicModel>> observer) {
        sApiServer.searchMusic(input, filter, type, page).compose(new DefaultTransformer<BaseModel<List<MusicModel>>, List<MusicModel>>()).subscribe(observer);
    }

    @Override
    public void messageSetting(int broadcast, int fans, int news_voice, int news_vibrate, int only_friend, BaseObserver<String> observer) {
        sApiServer.messageSetting(broadcast, fans, news_voice, news_vibrate, only_friend).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void userBlackList(int page, String keyword, BaseObserver<List<BlacListSectionBean>> observer) {
        sApiServer.userBlackList(page, keyword).compose(new DefaultTransformer<BaseModel<List<BlacListSectionBean>>, List<BlacListSectionBean>>()).subscribe(observer);
    }

    @Override
    public void removeUserBlack(String blackId, int type, BaseObserver<String> observer) {
        sApiServer.removeBlackUser(blackId, type).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void pitCountDown(String roomId, String pitNumber, String time, BaseObserver<PitCountDownBean> observer) {
        sApiServer.pitCountDown(roomId, MyApplication.getInstance().getToken(), pitNumber, time).compose(new DefaultTransformer<BaseModel<PitCountDownBean>, PitCountDownBean>()).subscribe(observer);
    }

    @Override
    public void applyWheatFm(String roomId, String pitNumber, BaseObserver<FmApplyWheatResp> observer) {
        sApiServer.applyWheatFm(MyApplication.getInstance().getToken(), roomId, pitNumber).compose(new DefaultTransformer<BaseModel<FmApplyWheatResp>, FmApplyWheatResp>()).subscribe(observer);
    }

    @Override
    public void openFmProtected(String roomId, String type, String userId, BaseObserver<String> observer) {
        sApiServer.openFmProtected(MyApplication.getInstance().getToken(), roomId, type, userId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getProtectedRankingList(String roomId, BaseObserver<ProtectedRankingListResp> observer) {
        sApiServer.getProtectedRankingList(MyApplication.getInstance().getToken(), roomId).compose(new DefaultTransformer<BaseModel<ProtectedRankingListResp>, ProtectedRankingListResp>()).subscribe(observer);
    }

    @Override
    public void getAnchorRankingList(String roomId, String type, BaseObserver<AnchorRankingListResp> observer) {
        sApiServer.getAnchorRankingList(MyApplication.getInstance().getToken(), roomId, type).compose(new DefaultTransformer<BaseModel<AnchorRankingListResp>, AnchorRankingListResp>()).subscribe(observer);
    }

    @Override
    public void getProtectedList(BaseObserver<List<ProtectedItemBean>> observer) {
        sApiServer.getProtectedList(MyApplication.getInstance().getToken()).compose(new DefaultTransformer<BaseModel<List<ProtectedItemBean>>, List<ProtectedItemBean>>()).subscribe(observer);
    }


    @Override
    public void getFishInfo(String type, BaseObserver<FishInfoBean> observer) {
        sApiServer.getFishInfo(MyApplication.getInstance().getToken(), type).compose(new DefaultTransformer<BaseModel<FishInfoBean>, FishInfoBean>()).subscribe(observer);
    }

    @Override
    public void getRoomBackgroudList(BaseObserver<List<RoomBgBean>> observer) {
        sApiServer.getRoomBackgroundList(MyApplication.getInstance().getToken()).compose(new DefaultTransformer<BaseModel<List<RoomBgBean>>, List<RoomBgBean>>()).subscribe(observer);
    }

    @Override
    public void updateUserAvatar(String headPicture, BaseObserver<UpdateUserAvatarResp> observer) {
        sApiServer.updateUserAvatar(MyApplication.getInstance().getToken(), headPicture).compose(new DefaultTransformer<BaseModel<UpdateUserAvatarResp>, UpdateUserAvatarResp>()).subscribe(observer);
    }

    @Override
    public void quitRoomWithUserId(String roomId, String userId, BaseObserver<String> observer) {
        sApiServer.quitRoomWithUserId(roomId, userId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void cancelRoomManager(String roomId, BaseObserver<String> observer) {
        sApiServer.cancelRoomManager(MyApplication.getInstance().getToken(), roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getSignHostory(BaseObserver<SignHistoryResp> observer) {
        sApiServer.signHistory().compose(new DefaultTransformer<BaseModel<SignHistoryResp>, SignHistoryResp>()).subscribe(observer);
    }

    @Override
    public void getSignRewardList(BaseObserver<List<SignHistoryResp.RewardData>> observer) {
        sApiServer.signRewardContinuous().compose(new DefaultTransformer<BaseModel<List<SignHistoryResp.RewardData>>, List<SignHistoryResp.RewardData>>()).subscribe(observer);
    }

    @Override
    public void signIn(BaseObserver<SignHistoryResp.RewardData> observer) {
        sApiServer.signIn().compose(new DefaultTransformer<BaseModel<SignHistoryResp.RewardData>, SignHistoryResp.RewardData>()).subscribe(observer);
    }

    @Override
    public void signSwitch(BaseObserver<SignSwitchModel> observer) {
        sApiServer.signSwitch().compose(new DefaultTransformer<BaseModel<SignSwitchModel>, SignSwitchModel>()).subscribe(observer);
    }

    @Override
    public void searchGuildById(String id, BaseObserver<GuildInfo> observer) {
        sApiServer.searchGuildById(id).compose(new DefaultTransformer<BaseModel<GuildInfo>, GuildInfo>()).subscribe(observer);
    }

    @Override
    public void myGuildInfo(BaseObserver<MyGuildInfo> observer) {
        sApiServer.myGuildInfo().compose(new DefaultTransformer<BaseModel<MyGuildInfo>, MyGuildInfo>()).subscribe(observer);
    }

    @Override
    public void applyJoinGuild(String id, BaseObserver<String> observer) {
        sApiServer.applyJoinGuild(id).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void quitGuild(String id, BaseObserver<String> observer) {
        sApiServer.quitGuild(id).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void setSecondPassword(String mobile, String password, String code, BaseObserver<String> observer) {
        sApiServer.setSecondPassword(mobile, password, code).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void nameAuth(String userId, String fullName, String idNumber, String idCard, String front, String back, BaseObserver<String> observer) {
        sApiServer.nameAuth(userId, fullName, idNumber, idCard, front, back).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getNameAuthStatus(String userId, JavaBaseObserver<NameAuthResult> observer) {
        sApiServer.getNameAuthStatus(userId).compose(new DefaultTransformer<BaseModel<NameAuthResult>, NameAuthResult>()).subscribe(observer);
    }

    @Override
    public void getUnionStateByUserId(String userId, JavaBaseObserver<GuildState> observer) {
        sApiServer.getUnionStateByUserId(userId).compose(new DefaultTransformer<BaseModel<GuildState>, GuildState>()).subscribe(observer);
    }

    @Override
    public void getUnionInfoByUserId(String userId, JavaBaseObserver<List<MyGuildInfo>> observer) {
        sApiServer.getUnionInfoByUserId(userId).compose(new DefaultTransformer<BaseModel<List<MyGuildInfo>>, List<MyGuildInfo>>()).subscribe(observer);
    }

    @Override
    public void searchUnionInfo(String unionNum, JavaBaseObserver<GuildInfo> observer) {
        sApiServer.searchUnionInfo(unionNum).compose(new DefaultTransformer<BaseModel<GuildInfo>, GuildInfo>()).subscribe(observer);
    }

    @Override
    public void applyUnion(int applyType, String unionId, JavaBaseObserver<String> observer) {
        sApiServer.applyUnion(applyType, unionId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getSkillKinds(String userId, JavaBaseObserver<List<SkillSection>> observer) {
        sApiServer.getSkillKinds(userId).compose(new DefaultTransformer<BaseModel<List<SkillSection>>, List<SkillSection>>()).subscribe(observer);
    }

    @Override
    public void getUserPhotos(BaseObserver<List<MyPhotoItem>> observer) {
        sApiServer.userPhotos().compose(new DefaultTransformer<BaseModel<List<MyPhotoItem>>, List<MyPhotoItem>>()).subscribe(observer);
    }

    @Override
    public void deleteUserPhotos(String ids, BaseObserver<String> observer) {
        sApiServer.deleteUserPhoto(ids).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void addUserPhotos(String photo, BaseObserver<String> observer) {
        sApiServer.addUserPhoto(photo).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void addQualificationApply(SkillApplyModel model, JavaBaseObserver<Boolean> observer) {
        sApiServer.addQualificationApply(model).compose(new DefaultTransformer<BaseModel<Boolean>, Boolean>()).subscribe(observer);
    }

    @Override
    public void updateQualificationApply(SkillApplyModel model, JavaBaseObserver<Boolean> observer) {
        sApiServer.updateQualificationApply(model).compose(new DefaultTransformer<BaseModel<Boolean>, Boolean>()).subscribe(observer);
    }

    @Override
    public void checkSkillStatus(int skillId, JavaBaseObserver<Integer> observer) {
        sApiServer.getIsAllowWithSkill(skillId).compose(new DefaultTransformer<BaseModel<Integer>, Integer>()).subscribe(observer);
    }

    @Override
    public void getQualificationApply(int skillId, JavaBaseObserver<SkillApplyModel> observer) {
        sApiServer.getQualificationApply(skillId).compose(new DefaultTransformer<BaseModel<SkillApplyModel>, SkillApplyModel>()).subscribe(observer);
    }

    @Override
    public void getApplyRandomWords(int skillId, JavaBaseObserver<String> observer) {
        sApiServer.getApplyRandomWords(skillId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getApplyRulesBySkillId(int skillId, JavaBaseObserver<List<String>> observer) {
        sApiServer.getApplyRulesBySkillId(skillId).compose(new DefaultTransformer<BaseModel<List<String>>, List<String>>()).subscribe(observer);
    }

    @Override
    public void skillForbidUnAuth(int forbid, JavaBaseObserver<String> observer) {
        sApiServer.skillForbidUnAuth(forbid).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void skillFastAnswer(int answer, JavaBaseObserver<String> observer) {
        sApiServer.skillFastAnswer(answer).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void updateSkillPrice(SkillPriceSet set, JavaBaseObserver<String> observer) {
        sApiServer.updateSkillPrice(set).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getUserSkills(JavaBaseObserver<List<SkillSetting>> observer) {
        sApiServer.getUserSkills().compose(new DefaultTransformer<BaseModel<List<SkillSetting>>, List<SkillSetting>>()).subscribe(observer);
    }

    @Override
    public void getSkillPriceList(int skillId, JavaBaseObserver<List<String>> observer) {
        sApiServer.getSkillPriceList(skillId).compose(new DefaultTransformer<BaseModel<List<String>>, List<String>>()).subscribe(observer);
    }

    @Override
    public void getOrderSwitch(JavaBaseObserver<MyOrderSwitch> observer) {
        sApiServer.getOrderSwitch().compose(new DefaultTransformer<BaseModel<MyOrderSwitch>, MyOrderSwitch>()).subscribe(observer);
    }

    @Override
    public void updateOrderSwitch(MyOrderSwitch orderSwitch, JavaBaseObserver<String> observer) {
        sApiServer.updateOrderSwitch(orderSwitch).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getSkillListByUserId(String userId, JavaBaseObserver<List<UserSkillItem>> observer) {
        sApiServer.getSkillListByUserId(userId).compose(new DefaultTransformer<BaseModel<List<UserSkillItem>>, List<UserSkillItem>>()).subscribe(observer);
    }

    @Override
    public void getOrderSkillList(String userId, JavaBaseObserver<List<OrderSkillSelectItem>> observer) {
        sApiServer.getOrderSkillList(userId).compose(new DefaultTransformer<BaseModel<List<OrderSkillSelectItem>>, List<OrderSkillSelectItem>>()).subscribe(observer);
    }

    @Override
    public void getUserSkillInfo(String userId, int id, JavaBaseObserver<UserSkillInfo> observer) {
        sApiServer.getUserSkillInfo(userId, id).compose(new DefaultTransformer<BaseModel<UserSkillInfo>, UserSkillInfo>()).subscribe(observer);
    }

    @Override
    public void addOrder(AddOrderModel model, JavaBaseObserver<String> observer) {
        sApiServer.addOrder(model).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void verifyOrderTime(VerifyOrderTimeModel model, JavaBaseObserver<String> observer) {
        sApiServer.verifyOrderTime(model).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void orderPay(OrderPayModel model, JavaBaseObserver<String> observer) {
        sApiServer.orderPay(model).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getRecvOrders(int page, JavaBaseObserver<OrdersResp> observer) {
        sApiServer.getRecvOrders(new OrdersModel(page)).compose(new DefaultTransformer<BaseModel<OrdersResp>, OrdersResp>()).subscribe(observer);
    }

    @Override
    public void getSendOrders(int page, JavaBaseObserver<OrdersResp> observer) {
        sApiServer.getSendOrders(new OrdersModel(page)).compose(new DefaultTransformer<BaseModel<OrdersResp>, OrdersResp>()).subscribe(observer);
    }

    @Override
    public void getLastOrderMsg(String easeName, JavaBaseObserver<LastOrderMsg> observer) {
        sApiServer.getLastOrderMsg(easeName).compose(new DefaultTransformer<BaseModel<LastOrderMsg>, LastOrderMsg>()).subscribe(observer);
    }

    @Override
    public void getOrderMsg(int page, JavaBaseObserver<OrderMsgResp> observer) {
        sApiServer.getOrderMsg(new OrdersModel(page)).compose(new DefaultTransformer<BaseModel<OrderMsgResp>, OrderMsgResp>()).subscribe(observer);
    }

    @Override
    public void bossAcceptService(UpdateOrderModel body, JavaBaseObserver<String> observer) {
        sApiServer.bossAcceptService(body).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void accompanyAcceptService(UpdateOrderModel body, JavaBaseObserver<String> observer) {
        sApiServer.accompanyAcceptService(body).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void bossConfirmOrder(int orderId, JavaBaseObserver<String> observer) {
        sApiServer.bossConfirmOrder(orderId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void boosRefundOrder(int orderId, JavaBaseObserver<String> observer) {
        sApiServer.boosRefundOrder(orderId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void agreeRefund(int orderId, JavaBaseObserver<String> observer) {
        sApiServer.agreeRefund(orderId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void disagreeRefund(int orderId, JavaBaseObserver<String> observer) {
        sApiServer.disagreeRefund(orderId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void agreeRefuseRefund(int orderId, JavaBaseObserver<String> observer) {
        sApiServer.agreeRefuseRefund(orderId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void accompanyService(int orderId, JavaBaseObserver<String> observer) {
        sApiServer.accompanyService(orderId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void bossAppealing(AppealingModel body, JavaBaseObserver<String> observer) {
        sApiServer.bossAppealing(body).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getOrderDetail(int orderId, int orderType, JavaBaseObserver<OrderDetailResp> observer) {
        sApiServer.getOrderDetail(orderId, orderType).compose(new DefaultTransformer<BaseModel<OrderDetailResp>, OrderDetailResp>()).subscribe(observer);
    }

    @Override
    public void evaluateAccompany(EvaluateModel model, JavaBaseObserver<String> observer) {
        sApiServer.evaluateAccompany(model).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void evaluateBoss(EvaluateModel model, JavaBaseObserver<String> observer) {
        sApiServer.evaluateBoss(model).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void filterMessage(String msg, JavaBaseObserver<String> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("content", msg);
        sApiServer.filterMessage(params).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getOrderEvaluateDetail(int orderId, JavaBaseObserver<OrderDetailResp> observer) {
        sApiServer.getOrderEvaluateDetail(orderId).compose(new DefaultTransformer<BaseModel<OrderDetailResp>, OrderDetailResp>()).subscribe(observer);
    }

    public void gameLog(BaseObserver<List<GameLog>> observer) {
        sApiServer.getGameLog().compose(new DefaultTransformer<BaseModel<List<GameLog>>, List<GameLog>>()).subscribe(observer);
    }

    @Override
    public void logoutReason(String token, String mobile, String reason, String code, BaseObserver<String> observer) {
        sApiServer.logoutReason(token, mobile, reason, code).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    @Override
    public void getlogoutStatus(String token, String mobile, BaseObserver<LogoutReasonModel> observer) {
        sApiServer.getlogoutStatus(token, mobile).compose(new DefaultTransformer<BaseModel<LogoutReasonModel>, LogoutReasonModel>()).subscribe(observer);
    }

    public void transferUser(String userCode, BaseObserver<TransferUserModel> observer) {
        sApiServer.transferUser(userCode).compose(new DefaultTransformer<BaseModel<TransferUserModel>, TransferUserModel>()).subscribe(observer);
    }

    public void userTransfer(String userId, String gold, BaseObserver<String> observer) {
        sApiServer.userTransfer(userId, gold).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void userTransferIM(String imid, String gold, BaseObserver<String> observer) {
        sApiServer.userTransferIM(imid, gold).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

}
