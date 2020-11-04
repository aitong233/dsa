package com.spadea.xqipao.data.api;

import com.qpyy.libcommon.bean.RechargeInfoModel;
import com.qpyy.libcommon.bean.TransferUserModel;
import com.qpyy.libcommon.constant.URLConstants;
import com.spadea.xqipao.common.Constant;
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
import com.qpyy.libcommon.bean.RankInfo;
import com.qpyy.libcommon.bean.UserBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServer {


    @GET(Constant.URL.CHARM)
    Observable<BaseModel<CharmModel>> getCharmList(@Query("token") String token, @Query("type") int type, @Query("room_id") String roomId);


    @GET(Constant.URL.WEALTH)
    Observable<BaseModel<CharmModel>> getWealthList(@Query("token") String token, @Query("type") int type, @Query("room_id") String roomId);


    @GET(Constant.URL.WEEK_STAR)
    Observable<BaseModel<WeekStarModel>> getWeekStarList(@Query("token") String token);


    @GET(Constant.URL.ROOM)
    Observable<BaseModel<List<RoomRankingModel>>> getRoomRankingList(@Query("token") String token);


    @FormUrlEncoded
    @POST(Constant.URL.CHECKROOMPASSWORD)
    Observable<BaseModel> verificationRoomPassword(@Field("token") String token, @Field("id") String id, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constant.URL.ADDBANK)
    Observable<BaseModel<String>> addBank(@Field("token") String token, @Field("bank_num") String bankNum, @Field("cardholder") String cardholder, @Field("bank_type") int bankType,
                                          @Field("bank_name") String bankName, @Field("mobile") String mobile, @Field("bank_zhi") String bankZhi, @Field("card_number") String cardNumber,
                                          @Field("code") String code
    );

    @FormUrlEncoded
    @POST(Constant.URL.SEND_CODE)
    Observable<BaseModel<String>> sendCode(@Field("token") String token, @Field("mobile") String mobile, @Field("type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.USER_BANK)
    Observable<BaseModel<UserBankModel>> getUserBank(@Field("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.ALIPAY_INFO)
    Observable<BaseModel<UserBankModel>> getAlipayInfo(@Field("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.RECHARGE)
    Observable<BaseModel<String>> userRecharge(@Field("token") String token, @Field("money") String money, @Field("type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.RECHARGE_INFO)
    Observable<BaseModel<ArrayList<RechargeInfoModel>>> rechargeInfo(@Field("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.ALIPAYMENT)
    Observable<BaseModel<String>> aliPay(@Field("token") String token, @Field("user_id") String userId, @Field("type") int type, @Field("id") String id);

    @FormUrlEncoded
    @POST(Constant.URL.WXPAYMENT)
    Observable<BaseModel<WxPayModel>> wxPay(@Field("token") String token, @Field("user_id") String userId, @Field("type") int type, @Field("id") String id);


    @FormUrlEncoded
    @POST(Constant.URL.EDITBANK)
    Observable<BaseModel<String>> editBank(@Field("token") String token, @Field("cardholder") String cardholder, @Field("bank_name") String bank_name,
                                           @Field("mobile") String mobile, @Field("card_number") String card_number, @Field("id") String id,
                                           @Field("bank_num") String bank_num, @Field("bank_zhi") String bank_zhi, @Field("code") String code);

    @FormUrlEncoded
    @POST(Constant.URL.ADDALIPAY)
    Observable<BaseModel<String>> addAlipay(@Field("token") String token, @Field("alipay_account") String alipay_account, @Field("alipay_realname") String alipay_realname, @Field("code") String code
    );

    @FormUrlEncoded
    @POST(Constant.URL.USERWITHDRAW)
    Observable<BaseModel<String>> userWithdraw(@Field("token") String token, @Field("bank_id") String bank_id, @Field("number") String number, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constant.URL.USERWITHDRAW)
    Observable<BaseModel<String>> userWithdraw(@Field("token") String token, @Field("number") String number);

    @FormUrlEncoded
    @POST(Constant.URL.LOGIN)
    Observable<BaseModel<UserBean>> login(@Field("mobile") String mobile, @Field("password") String password, @Field("code") String code, @Field("type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.LOGIN)
    Observable<BaseModel<UserBean>> oauthLogin(@Field("netease_token") String netease_token, @Field("access_token") String access_token, @Field("type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.SETUSERSEX)
    Observable<BaseModel<String>> setUserSex(@Field("user_id") String user_id, @Field("sex") int sex);

    @FormUrlEncoded
    @POST(Constant.URL.THIRDPARTYLOGIN)
    Observable<BaseModel<UserBean>> thirdPartyLogin(@Field("openid") String openid, @Field("three_party") int three_party, @Field("nickname") String nickname, @Field("head_pic") String head_pic);


    @FormUrlEncoded
    @POST(Constant.URL.EARNINGS)
    Observable<BaseModel<EarningsModel>> getEarnings(@Field("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.CONVERTEARNINGS)
    Observable<BaseModel<String>> convertEarnings(@Field("token") String token, @Field("number") String number, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constant.URL.EXCHANGEROOMEARNINGS)
    Observable<BaseModel<String>> exchangeRoomEarnings(@Field("number") String number, @Field("password") String password);

    @POST(Constant.URL.USER_PROFIT)
    Observable<BaseModel<ProfitModel>> userProfit();

    @POST(Constant.URL.ROOM_PROFIT)
    Observable<BaseModel<ProfitModel>> roomProfit();

    @FormUrlEncoded
    @POST(Constant.URL.APPLYROOMPROFIT)
    Observable<BaseModel<String>> applyRoomProfit(@Field("password") String password, @Field("number") String number);

    @FormUrlEncoded
    @POST(Constant.URL.CASHLOG)
    Observable<BaseModel<List<EarningsModel.EarningInfo>>> getCashLog(@Field("token") String token, @Field("p") int p, @Field("change_type") int change_type);


    @GET(Constant.URL.WINRANKING)
    Observable<BaseModel<List<CatFishingModel>>> getWinRanking(@Query("rank_type") int type, @Query("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.CATHELP)
    Observable<BaseModel<CatHelpModel>> getCatHelp(@Field("token") String token);

    @GET(Constant.URL.WINJACKPOT)
    Observable<BaseModel<List<WinJackpotModel>>> getCatWinJackpot(@Query("token") String token, @Query("lucky_bag_type") String type);


    @FormUrlEncoded
    @POST(Constant.URL.BALANCE)
    Observable<BaseModel<String>> getBalance(@Field("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.FISHING)
    Observable<BaseModel<LuckGiftBean>> startFishing(@Field("token") String token, @Field("lottery_number") int number, @Field("lucky_bag_type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.GET_FISH_INFO)
    Observable<BaseModel<FishInfoBean>> getFishInfo(@Field("token") String token, @Query("lucky_bag_type") String type);

    @FormUrlEncoded
    @POST(Constant.URL.ROOMD_DETAILS)
    Observable<BaseModel<RoomDetailModel>> getRoomDetails(@Field("token") String token, @Field("id") String id, @Field("password") String password);


    @FormUrlEncoded
    @POST(Constant.URL.FACELIST)
    Observable<BaseModel<List<EmojiModel>>> getFaceList(@Field("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.JOINROOM)
    Observable<BaseModel<RoomInfoModel>> joinRoom(@Field("token") String token, @Field("room_id") String room_id);


    @FormUrlEncoded
    @POST(Constant.URL.SEARCH_USER)
    Observable<BaseModel<List<SearchUserModel>>> getSearChUser(@Field("token") String token, @Field("room_id") String roomId, @Field("keyword") String keyword, @Field("type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.ROOM_GET_LIST)
    Observable<BaseModel<List<SearchUserModel>>> getRoomList(@Field("room_id") String roomId, @Field("type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.ADD_MANAGER)
    Observable<BaseModel<RoomManageModel>> addManager(@Field("token") String token, @Field("room_id") String roomId, @Field("user_id") String userId);


    @FormUrlEncoded
    @POST(Constant.URL.DELETE_MANAGER)
    Observable<BaseModel<RoomManageModel>> deleteManager(@Field("token") String token, @Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(Constant.URL.ADD_FORBID)
    Observable<BaseModel<String>> addRorbid(@Field("token") String token, @Field("room_id") String roomId, @Field("user_id") String userId);


    @FormUrlEncoded
    @POST(Constant.URL.DELETE_FORBID)
    Observable<BaseModel<String>> deleteForbid(@Field("token") String token, @Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(Constant.URL.ADDFAVORITE)
    Observable<BaseModel<String>> addRoomCollect(@Field("token") String token, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(Constant.URL.APPLY_WHEAT)
    Observable<BaseModel<String>> applyWheat(@Field("token") String token, @Field("room_id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(Constant.URL.DOWN_WHEAT)
    Observable<BaseModel<String>> downWheat(@Field("token") String token, @Field("room_id") String roomId);


    @FormUrlEncoded
    @POST(Constant.URL.SWITCHVOICE)
    Observable<BaseModel<String>> switchVoice(@Field("token") String token, @Field("id") String id, @Field("type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.CLEAR_CARDIAC)
    Observable<BaseModel<String>> clearCardiac(@Field("token") String token, @Field("id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(Constant.URL.SHUT_UP)
    Observable<BaseModel<String>> shutUp(@Field("token") String token, @Field("user_id") String userId, @Field("type") String type, @Field("pit_number") String pitNumber, @Field("id") String id);

    @FormUrlEncoded
    @POST(Constant.URL.CLOSEPIT)
    Observable<BaseModel<ClosePitModel>> closePit(@Field("token") String token, @Field("type") String type, @Field("pit_number") String pitNumber, @Field("id") String id);

    @FormUrlEncoded
    @POST(Constant.URL.ROOM_USER_INFO)
    Observable<BaseModel<RoomUserInfoModel>> getRoomUserInfo(@Field("token") String token, @Field("user_id") String userId, @Field("visit") int visit);

    @FormUrlEncoded
    @POST(Constant.URL.GIFT_WALL)
    Observable<BaseModel<List<GiftModel>>> giftWall(@Field("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.USER_BACKPACK)
    Observable<BaseModel<List<GiftModel>>> userBackPack(@Field("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.ROOM_ENTER)
    Observable<BaseModel<WheatModel>> getRoomEnter(@Field("token") String token, @Field("room_id") String roomId, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constant.URL.ROOM_EXTRAINFO)
    Observable<BaseModel<RoomExtraModel>> getRoomExtra(@Field("token") String token, @Field("room_id") String roomId, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constant.URL.DOWN_USER_WHEAT)
    Observable<BaseModel<String>> downUserWheat(@Field("token") String token, @Field("pit_number") String pitNumber, @Field("room_id") String roomId, @Field("user_id") String userId);


    @FormUrlEncoded
    @POST(Constant.URL.FOLLOW)
    Observable<BaseModel<String>> follow(@Field("token") String token, @Field("user_id") String userId, @Field("type") int type);

    /**
     * user_id
     * id房间id
     * type1禁言2取消禁言
     *
     * @return
     */
    @FormUrlEncoded
    @POST(Constant.URL.SETROOMBANNED)
    Observable<BaseModel<RoomBannedModel>> setRoomBanned(@Field("token") String token, @Field("id") String roomId, @Field("user_id") String userId, @Field("type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.EDIT_ROOM)
    Observable<BaseModel<String>> editRoom(@Field("token") String token, @Field("cover_picture") String coverPicture, @Field("bg_picture") String bgPicture, @Field("password") String password,
                                           @Field("playing") String playing, @Field("room_id") String roomId, @Field("room_name") String roomName, @Field("label_id") String labelId, @Field("type_id") String typeId, @Field("greeting") String greeting, @Field("wheat") String wheat,
                                           @Field("is_password") String is_password
    );

    @FormUrlEncoded
    @POST(Constant.URL.EDIT_ROOM_BG)
    Observable<BaseModel<String>> editRoomBg(@Field("token") String token, @Field("bg_picture") String bgPicture, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(Constant.URL.GIVEGIFT)
    Observable<BaseModel<RankInfo>> giveGift(@Field("token") String token, @Field("user_id") String userId, @Field("gift_id") String giftId, @Field("room_id") String roomId, @Field("pit") String pit, @Field("number") String num);

    @FormUrlEncoded
    @POST(Constant.URL.GIVEBACKGIFT)
    Observable<BaseModel<RankInfo>> giveBackGift(@Field("token") String token, @Field("user_id") String userId, @Field("gift_id") String giftId, @Field("room_id") String roomId, @Field("pit") String pit, @Field("number") String num);


    @FormUrlEncoded
    @POST(Constant.URL.APPLYWHEATLIST)
    Observable<BaseModel<List<RowWheatModel>>> applyWheatList(@Field("token") String token, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(Constant.URL.DELETEAPPLY)
    Observable<BaseModel<String>> deleteApply(@Field("token") String token, @Field("ids") String ids, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(Constant.URL.APPLYWHEATWAIT)
    Observable<BaseModel<ApplyWheatWaitModel>> applyWheatWait(@Field("token") String token, @Field("room_id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(Constant.URL.AGREEAPPLY)
    Observable<BaseModel<AgreeApplyModel>> agreeApply(@Field("token") String token, @Field("id") String id, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(Constant.URL.AGREEAPPLYALL)
    Observable<BaseModel<String>> agreeApplyAll(@Field("token") String token, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(Constant.URL.CLEARROOMCARDIAC)
    Observable<BaseModel<String>> clearRoomCardiac(@Field("token") String token, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(Constant.URL.SETROOMCARDIAC)
    Observable<BaseModel<String>> setRoomCardiac(@Field("token") String token, @Field("room_id") String roomId, @Field("state") int state);

    @FormUrlEncoded
    @POST(Constant.URL.REMOVEFAVORITE)
    Observable<BaseModel<String>> removeFavorite(@Field("token") String token, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(Constant.URL.KICKOUT)
    Observable<BaseModel<String>> kickOut(@Field("token") String token, @Field("user_id") String userId, @Field("room_id") String roomId);


    @FormUrlEncoded
    @POST(Constant.URL.QUIT)
    Observable<BaseModel<String>> quit(@Field("token") String token, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(Constant.URL.ROOMAUTH)
    Observable<BaseModel<RoomAuthModel>> roomAuth(@Field("token") String token, @Field("room_id") String roomId);


    @POST(Constant.URL.ONLINE)
    Observable<BaseModel<String>> online();

    @POST(Constant.URL.BANNERS)
    Observable<BaseModel<List<BannerModel>>> getBanners();

    @POST(Constant.URL.ROOMTYPE)
    Observable<BaseModel<List<RoomTypeModel>>> roomType();

    @FormUrlEncoded
    @POST(Constant.URL.ROOMLIST)
    Observable<BaseModel<List<RoomModel>>> roomList(@Field("type_id") String typeId);

    @FormUrlEncoded
    @POST(Constant.URL.HOTROOM)
    Observable<BaseModel<List<RoomModel>>> hotRoom(@Field("user_id") String userId);

    @FormUrlEncoded
    @POST(Constant.URL.MANAGE_ROOM)
    Observable<BaseModel<MyManageRoomModel>> manageRoom(@Field("p") int page);

    @FormUrlEncoded
    @POST(Constant.URL.COLLECT_ROOM)
    Observable<BaseModel<List<ManageRoomModel>>> collectRoom(@Field("p") int page);

    @FormUrlEncoded
    @POST(Constant.URL.ISFOUNDROOM)
    Observable<BaseModel<String>> isFoundRoom(@Field("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.FRIENDLIST)
    Observable<BaseModel<List<FriendModel>>> friendList(@Field("p") int p);

    @FormUrlEncoded
    @POST(Constant.URL.FOLLOWLIST)
    Observable<BaseModel<List<FriendModel>>> followList(@Field("p") int p);

    @FormUrlEncoded
    @POST(Constant.URL.FANSLIST)
    Observable<BaseModel<List<FriendModel>>> fansList(@Field("p") int p);

    @POST(Constant.URL.MYINFO)
    Observable<BaseModel<UserInfoModel>> userInfo();

    @POST(Constant.URL.CATEGORIES)
    Observable<BaseModel<List<CategoriesModel>>> categories();

    @FormUrlEncoded
    @POST(Constant.URL.PRODUCTS)
    Observable<BaseModel<List<ProductsModel>>> products(@Field("category_id") String categoryId);

    @FormUrlEncoded
    @POST(Constant.URL.BUY_SHOP)
    Observable<BaseModel<String>> buyShop(@Field("friend_id") String friendId, @Field("product_id") String productId, @Field("price_id") String priceId);


    @FormUrlEncoded
    @POST(Constant.URL.USERINFO)
    Observable<BaseModel<UserInfoDataModel>> userInfoData(@Field("user_id") String userId, @Field("emchat_username") String emchatUsername, @Field("visit") int visit);

    @FormUrlEncoded
    @POST(Constant.URL.SEARCHUSER)
    Observable<BaseModel<List<SearchUserInfo>>> searchUser(@Field("keyword") String keyword);

    @FormUrlEncoded
    @POST(Constant.URL.SEARCHROOM)
    Observable<BaseModel<List<SearchRoomInfo>>> searchRoom(@Field("keyword") String keyword);

    @POST(Constant.URL.VIPINFO)
    Observable<BaseModel<VipInfo>> vipinfo();

    @POST(Constant.URL.SERVICEUSER)
    Observable<BaseModel<String>> serviceUser();

    @POST(Constant.URL.ARTICLE_CATEGORIES)
    Observable<BaseModel<List<HelpTitleModel>>> articleCategories();

    @FormUrlEncoded
    @POST(Constant.URL.ARTICLE_LIST)
    Observable<BaseModel<List<HelpModel>>> articleList(@Field("article_cat_id") String articleCatId);

    @POST(Constant.URL.USER_NOBILITYINFO)
    Observable<BaseModel<NobilityInfo>> userNobilityInfo();

    @POST(Constant.URL.NOBILITY)
    Observable<BaseModel<List<NobilityModel>>> nobility();

    @FormUrlEncoded
    @POST(Constant.URL.BUYNOBILITY)
    Observable<BaseModel<String>> buyNobility(@Field("nobility_id") String nobilityId);

    @FormUrlEncoded
    @POST(Constant.URL.RENEWNOBILITY)
    Observable<BaseModel<String>> renewNobility(@Field("day") String day);

    @FormUrlEncoded
    @POST(Constant.URL.MYPRODUCTS)
    Observable<BaseModel<List<MyProductsModel>>> myProducts(@Field("category_id") String categoryId);

    @FormUrlEncoded
    @POST(Constant.URL.MYUSINGPRODUCTS)
    Observable<BaseModel<UsingProductsModel>> myUsingProducts(@Field("category_id") String categoryId);

    @FormUrlEncoded
    @POST(Constant.URL.USEPRODUCT)
    Observable<BaseModel<String>> useProduct(@Field("id") String id);

    @FormUrlEncoded
    @POST(Constant.URL.MALL_DOWN_PRODUCT)
    Observable<BaseModel<String>> downProduct(@Field("id") String id);

    @FormUrlEncoded
    @POST(Constant.URL.UPDATE_USERINFO)
    Observable<BaseModel<String>> updateUserInfo(@Field("signature") String signature, @Field("birthday") String birthday, @Field("constellation") String constellation, @Field("profession") String profession,
                                                 @Field("city_id") String city_id, @Field("user_photo") String user_photo, @Field("sex") String sex, @Field("head_picture") String head_picture,
                                                 @Field("nickname") String nickname, @Field("province_id") String province_id, @Field("user_no") String userNo, @Field("county_id") String county_id);

    @FormUrlEncoded
    @POST(Constant.URL.INDEX_LABEL)
    Observable<BaseModel<List<LabelModel>>> indexLabel(@Field("category_id") String categoryId, @Field("p") int p);

    @FormUrlEncoded
    @POST(Constant.URL.ADDLABEL)
    Observable<BaseModel<String>> addLabel(@Field("ids") String ids);


    @POST(Constant.URL.USERNEWS)
    Observable<BaseModel<NewsModel>> userNews();

    @POST(Constant.URL.CASHTYPE)
    Observable<BaseModel<List<CashTypeModel>>> cashType();

    @FormUrlEncoded
    @POST(Constant.URL.ROOM_ONLINE)
    Observable<BaseModel<List<OnlineModel>>> roomOnline(@Field("room_id") String roomId, @Field("p") int page);


    @FormUrlEncoded
    @POST(Constant.URL.ROOMUSERINFO)
    Observable<BaseModel<RoomUserInfo>> getRoomUserInfo(@Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(Constant.URL.ROOM_USER_SHUTUP)
    Observable<BaseModel<RoomShutUp>> roomUserShutUp(@Field("room_id") String roomId, @Field("user_id") String userId, @Field("type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.ROOM_PITINFO)
    Observable<BaseModel<RoomPitInfo>> roomPitInfo(@Field("room_id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(Constant.URL.COMEUSER)
    Observable<BaseModel<List<LatelyVisitInfo>>> comeUser(@Field("token") String token, @Field("p") int p);

    @POST(Constant.URL.TOPTWO)
    Observable<BaseModel<TopTwoModel>> getTopTwo();

    @GET(Constant.URL.APPUPDATE)
    Observable<BaseModel<AppUpdateModel>> appUpdate();

    @GET(Constant.URL.CHECK_UPDATE)
    Observable<BaseModel<AppUpdateModel>> checkUpdate();

    @GET(Constant.URL.RANDOMHOTROOM)
    Observable<BaseModel<String>> randomHotRoom();

    @FormUrlEncoded
    @POST(Constant.URL.ROOM_GETIN)
    Observable<BaseModel<RoomDetailBean>> roomGetIn(@Field("room_id") String roomId, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constant.URL.PUTONWHEAT)
    Observable<BaseModel<String>> putOnWheat(@Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(Constant.URL.UPDATEPASSWORD)
    Observable<BaseModel<String>> updatePassword(@Field("room_id") String roomId, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constant.URL.PITLIST)
    Observable<BaseModel<List<RoomPitUserModel>>> getRoomPitUser(@Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(Constant.URL.ROOM_ROLL)
    Observable<BaseModel<RoomPollModel>> roomPoll(@Field("room_id") String roomId, @Field("type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.GETINROOMINFO)
    Observable<BaseModel<RoomDetailBean>> getInRoomInfo(@Field("room_id") String roomId);

    @POST(Constant.URL.USER_FILES)
    Observable<BaseModel<UserBean>> userFiles();

    @FormUrlEncoded
    @POST(Constant.URL.REGION_LIST)
    Observable<BaseModel<List<RegionListBean>>> regionList(@Field("parent_id") String parentId);

    @FormUrlEncoded
    @POST(Constant.URL.BIND_MOBILE)
    Observable<BaseModel<String>> bindMobile(@Field("mobile") String mobile, @Field("code") String code);

    @FormUrlEncoded
    @POST(Constant.URL.RESET_PASSWORD)
    Observable<BaseModel<String>> resetPassword(@Field("mobile") String mobile, @Field("code") String code, @Field("password") String password);

    @GET(Constant.URL.SEARCHMUSIC)
    Observable<BaseModel<List<MusicModel>>> searchMusic(@Query("input") String input, @Query("filter") String filter, @Query("type") String type, @Query("page") int page);

    @FormUrlEncoded
    @POST(Constant.URL.MESSAGE_SETTING)
    Observable<BaseModel<String>> messageSetting(@Field("broadcast") int broadcast, @Field("fans") int fans, @Field("news_voice") int news_voice, @Field("news_vibrate") int news_vibrate, @Field("only_friend") int only_friend);

    @FormUrlEncoded
    @POST(Constant.URL.USER_BLACK_LIST)
    Observable<BaseModel<List<BlacListSectionBean>>> userBlackList(@Field("p") int page, @Field("keyword") String keyword);

    @FormUrlEncoded
    @POST(Constant.URL.ADD_BLACK_USER)
    Observable<BaseModel<String>> removeBlackUser(@Field("black_id") String blackId, @Field("type") int type);

    @FormUrlEncoded
    @POST(Constant.URL.PIT_COUNT_DOWN)
    Observable<BaseModel<PitCountDownBean>> pitCountDown(@Field("room_id") String roomId, @Field("token") String token, @Field("pit_number") String pitNumber, @Field("time") String time);

    @FormUrlEncoded
    @POST(Constant.URL.APPLY_WHEAT_FM)
    Observable<BaseModel<FmApplyWheatResp>> applyWheatFm(@Field("token") String token, @Field("room_id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(Constant.URL.FM_OPEN_PROTECTED)
    Observable<BaseModel<String>> openFmProtected(@Field("token") String token, @Field("room_id") String roomId, @Field("type") String type, @Field("user_id_protect") String userId);

    @FormUrlEncoded
    @POST(Constant.URL.GET_PROTECTED_RANKING_LIST)
    Observable<BaseModel<ProtectedRankingListResp>> getProtectedRankingList(@Field("token") String token, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(Constant.URL.GET_ANCHOR_RANKING_LIST)
    Observable<BaseModel<AnchorRankingListResp>> getAnchorRankingList(@Field("token") String token, @Field("room_id") String roomId, @Field("type") String type);

    @FormUrlEncoded
    @POST(Constant.URL.GET_PROTECTED_LIST)
    Observable<BaseModel<List<ProtectedItemBean>>> getProtectedList(@Field("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.ROOM_BACKGROUND_LIST)
    Observable<BaseModel<List<RoomBgBean>>> getRoomBackgroundList(@Field("token") String token);

    @FormUrlEncoded
    @POST(Constant.URL.UPDATE_USER_AVATR)
    Observable<BaseModel<UpdateUserAvatarResp>> updateUserAvatar(@Field("token") String token, @Field("head_picture") String head_picture);

    @FormUrlEncoded
    @POST(Constant.URL.QUIT_ROOM_WITH_USER_ID)
    Observable<BaseModel<String>> quitRoomWithUserId(@Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(Constant.URL.CANCEL_ROOM_MANAGER)
    Observable<BaseModel<String>> cancelRoomManager(@Field("token") String token, @Field("room_id") String roomId);

    @POST(Constant.URL.SIGN_HISTORY)
    Observable<BaseModel<SignHistoryResp>> signHistory();

    @POST(Constant.URL.SIGN_REWARD_CONTINOUS)
    Observable<BaseModel<List<SignHistoryResp.RewardData>>> signRewardContinuous();

    @POST(Constant.URL.SIGN_IN)
    Observable<BaseModel<SignHistoryResp.RewardData>> signIn();

    @POST(Constant.URL.SIGN_SWITCH)
    Observable<BaseModel<SignSwitchModel>> signSwitch();

    @FormUrlEncoded
    @POST(Constant.URL.SEARCH_GUILD_BY_ID)
    Observable<BaseModel<GuildInfo>> searchGuildById(@Field("guild_no") String id);

    @FormUrlEncoded
    @POST(Constant.URL.QUIT_GUILD)
    Observable<BaseModel<String>> quitGuild(@Field("id") String id);

    @FormUrlEncoded
    @POST(Constant.URL.APPLY_JOIN_GUILD)
    Observable<BaseModel<String>> applyJoinGuild(@Field("id") String id);

    @POST(Constant.URL.MY_GUILD_INFO)
    Observable<BaseModel<MyGuildInfo>> myGuildInfo();

    @FormUrlEncoded
    @POST(Constant.URL.SET_SECOND_PASSWORD)
    Observable<BaseModel<String>> setSecondPassword(@Field("mobile") String mobile, @Field("password") String password, @Field("code") String code);

    @FormUrlEncoded
    @POST(URLConstants.NAME_AUTH)
    Observable<BaseModel<String>> nameAuth(@Field("userId") String userId, @Field("fullName") String fullName, @Field("idNumber") String idNumber, @Field("idCard") String idCard, @Field("front") String front, @Field("back") String back);

    @POST(Constant.URL.GET_NAME_AUTH_STATUS + "/{id}")
    Observable<BaseModel<NameAuthResult>> getNameAuthStatus(@Path("id") String userId);

    @FormUrlEncoded
    @POST(Constant.URL.JAVA_UNION_INFO)
    Observable<BaseModel<List<MyGuildInfo>>> getUnionInfoByUserId(@Field("userId") String userId);

    @FormUrlEncoded
    @POST(Constant.URL.JAVA_UNION_APPLY_STATE)
    Observable<BaseModel<GuildState>> getUnionStateByUserId(@Field("userId") String userId);

    @FormUrlEncoded
    @POST(Constant.URL.JAVA_QUERY_UNION)
    Observable<BaseModel<GuildInfo>> searchUnionInfo(@Field("unionNum") String unionNum);

    @FormUrlEncoded
    @POST(Constant.URL.JAVA_JOIN_UNION)
    Observable<BaseModel<String>> applyUnion(@Field("applyType") int applyType, @Field("unionId") String unionId);

    @POST(Constant.URL.USER_PHOTO)
    Observable<BaseModel<List<MyPhotoItem>>> userPhotos();

    @FormUrlEncoded
    @POST(Constant.URL.DELETE_USER_PHOTO)
    Observable<BaseModel<String>> deleteUserPhoto(@Field("id") String ids);

    @FormUrlEncoded
    @POST(Constant.URL.ADD_USER_PHOTO)
    Observable<BaseModel<String>> addUserPhoto(@Field("photo") String photo);

    @GET(Constant.URL.GET_SKILL_KINDS)
    Observable<BaseModel<List<SkillSection>>> getSkillKinds(@Query("userId") String userId);

    @GET(Constant.URL.IS_ALLOW_WITH_SKILL_ID)
    Observable<BaseModel<Integer>> getIsAllowWithSkill(@Query("skillId") int skillId);

    @GET(Constant.URL.GET_QUALIFICATION_APPLY)
    Observable<BaseModel<SkillApplyModel>> getQualificationApply(@Query("skillId") int skillId);

    @POST(Constant.URL.ADD_QUALIFICATION_APPLY)
    Observable<BaseModel<Boolean>> addQualificationApply(@Body SkillApplyModel body);

    @POST(Constant.URL.UPDATE_QUALIFICATION_APPLY)
    Observable<BaseModel<Boolean>> updateQualificationApply(@Body SkillApplyModel body);

    @GET(Constant.URL.GET_APPLY_RANDOM_WORDS)
    Observable<BaseModel<String>> getApplyRandomWords(@Query("skillId") int skillId);

    @GET(Constant.URL.GET_APPLY_RULE_BY_SKILL_ID)
    Observable<BaseModel<List<String>>> getApplyRulesBySkillId(@Query("skillId") int skillId);

    @POST(Constant.URL.SKILL_FAST_ANSWER)
    Observable<BaseModel<String>> skillFastAnswer(@Field("fastAnswer") int fastAnswer);

    @FormUrlEncoded
    @POST(Constant.URL.SKILL_FORBID_FOR_USER_UNAUTH)
    Observable<BaseModel<String>> skillForbidUnAuth(@Field("forbidSomeone") int forbidSomeone);

    @POST(Constant.URL.UPDATE_SKILL_PRICE_OR_SWITCH)
    Observable<BaseModel<String>> updateSkillPrice(@Body SkillPriceSet body);

    @GET(Constant.URL.GET_USER_SKILLS)
    Observable<BaseModel<List<SkillSetting>>> getUserSkills();

    @GET(Constant.URL.GET_SKILL_PRICE_LIST)
    Observable<BaseModel<List<String>>> getSkillPriceList(@Query("skillId") int skillId);

    @GET(Constant.URL.GET_ORDER_SWITCH)
    Observable<BaseModel<MyOrderSwitch>> getOrderSwitch();

    @POST(Constant.URL.UPDATE_ORDER_SWITCH)
    Observable<BaseModel<String>> updateOrderSwitch(@Body MyOrderSwitch body);

    @GET(Constant.URL.GET_SKILL_LIST_BY_USER_ID)
    Observable<BaseModel<List<UserSkillItem>>> getSkillListByUserId(@Query("userId") String userId);

    @GET(Constant.URL.GET_ORDER_SKILL_LIST)
    Observable<BaseModel<List<OrderSkillSelectItem>>> getOrderSkillList(@Query("userId") String userId);

    @GET(Constant.URL.GET_SKILL_INFO)
    Observable<BaseModel<UserSkillInfo>> getUserSkillInfo(@Query("userId") String userId, @Query("id") int id);

    @POST(Constant.URL.ADD_ORDER)
    Observable<BaseModel<String>> addOrder(@Body AddOrderModel body);

    @POST(Constant.URL.ORDER_PAY)
    Observable<BaseModel<String>> orderPay(@Body OrderPayModel body);

    @POST(Constant.URL.VERIFY_ORDER_TIME)
    Observable<BaseModel<String>> verifyOrderTime(@Body VerifyOrderTimeModel body);

    @POST(Constant.URL.QUERY_MY_RECV_ORDER)
    Observable<BaseModel<OrdersResp>> getRecvOrders(@Body OrdersModel body);

    @POST(Constant.URL.QUERY_MY_SEND_ORDER)
    Observable<BaseModel<OrdersResp>> getSendOrders(@Body OrdersModel body);

    @GET(Constant.URL.GET_LAST_ORDER_MSG)
    Observable<BaseModel<LastOrderMsg>> getLastOrderMsg(@Query("userEaseCharm") String userEaseCharm);

    @POST(Constant.URL.GET_ORDER_MSG)
    Observable<BaseModel<OrderMsgResp>> getOrderMsg(@Body OrdersModel body);

    @POST(Constant.URL.BOSS_ACCEPT_SERVICE)
    Observable<BaseModel<String>> bossAcceptService(@Body UpdateOrderModel body);

    @POST(Constant.URL.ACCOMPANY_ACCEPT)
    Observable<BaseModel<String>> accompanyAcceptService(@Body UpdateOrderModel body);

    @GET(Constant.URL.BOSS_CONFIRM_ORDER)
    Observable<BaseModel<String>> bossConfirmOrder(@Query("orderId") int orderId);

    @GET(Constant.URL.BOSS_REFUND_ORDER)
    Observable<BaseModel<String>> boosRefundOrder(@Query("orderId") int orderId);

    @GET(Constant.URL.ACCOMPANY_AGREE_REFUND_ORDER)
    Observable<BaseModel<String>> agreeRefund(@Query("orderId") int orderId);

    @GET(Constant.URL.ACCOMPANY_DISAGREE_REFUND_ORDER)
    Observable<BaseModel<String>> disagreeRefund(@Query("orderId") int orderId);

    @GET(Constant.URL.BOSS_AGREE_REFUSE_REFUND_ORDER)
    Observable<BaseModel<String>> agreeRefuseRefund(@Query("orderId") int orderId);

    @GET(Constant.URL.ACCOMPANY_SERVICE)
    Observable<BaseModel<String>> accompanyService(@Query("orderId") int orderId);

    @POST(Constant.URL.BOSS_APPEALING)
    Observable<BaseModel<String>> bossAppealing(@Body AppealingModel body);

    @GET(Constant.URL.GET_ORDER_DETAIL_BY_ID)
    Observable<BaseModel<OrderDetailResp>> getOrderDetail(@Query("orderId") int orderId, @Query("type") int type);

    @POST(Constant.URL.EVALUATION_ACCOMPANY)
    Observable<BaseModel<String>> evaluateAccompany(@Body EvaluateModel body);

    @POST(Constant.URL.EVALUATION_BOSS)
    Observable<BaseModel<String>> evaluateBoss(@Body EvaluateModel body);

    @POST(Constant.URL.MESSAGE_FILTER)
    Observable<BaseModel<String>> filterMessage(@Body Map<String, String> body);

    @GET(Constant.URL.GET_ORDER_EVALUATE_DETAIL)
    Observable<BaseModel<OrderDetailResp>> getOrderEvaluateDetail(@Query("orderId") int orderId);

    @FormUrlEncoded
    @POST(Constant.URL.LOGOUT_REASON)
    Observable<BaseModel<String>> logoutReason(@Field("token") String token, @Field("mobile") String mobile,
                                               @Field("reason") String reason, @Field("code") String code);

    @FormUrlEncoded
    @POST(Constant.URL.LOGOUT_STATUS)
    Observable<BaseModel<LogoutReasonModel>> getlogoutStatus(@Field("token") String token, @Field("mobile") String mobile);

    @GET(Constant.URL.GET_GAME_LOG)
    Observable<BaseModel<List<GameLog>>> getGameLog();

    @FormUrlEncoded
    @POST(Constant.URL.TRANSFER_USER)
    Observable<BaseModel<TransferUserModel>> transferUser(@Field("user_code") String userCode);

    @FormUrlEncoded
    @POST(Constant.URL.USER_TRANSFER)
    Observable<BaseModel<String>> userTransfer(@Field("user_id") String userId, @Field("gold") String gold);

    @FormUrlEncoded
    @POST(Constant.URL.USER_TRANSFER)
    Observable<BaseModel<String>> userTransferIM(@Field("emchat_username") String userId, @Field("gold") String gold);

}
