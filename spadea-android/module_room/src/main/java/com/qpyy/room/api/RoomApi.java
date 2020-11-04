package com.qpyy.room.api;

import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.libcommon.bean.RankInfo;
import com.qpyy.libcommon.bean.RechargeInfoModel;
import com.qpyy.libcommon.bean.RoomBannedModel;
import com.qpyy.libcommon.constant.URLConstants;
import com.qpyy.libcommon.http.BaseModel;
import com.qpyy.room.bean.AgreeApplyResp;
import com.qpyy.room.bean.AnchorRankingListResp;
import com.qpyy.room.bean.ApplyWheatUsersResp;
import com.qpyy.room.bean.ApplyWheatWaitResp;
import com.qpyy.room.bean.BallResp;
import com.qpyy.room.bean.CardResultBean;
import com.qpyy.room.bean.CatHelpModel;
import com.qpyy.room.bean.CategoriesModel;
import com.qpyy.room.bean.CharmRankingResp;
import com.qpyy.room.bean.ClosePitModel;
import com.qpyy.room.bean.ExclusiveEmojiResp;
import com.qpyy.room.bean.FansNotifyInfo;
import com.qpyy.room.bean.FishInfoBean;
import com.qpyy.room.bean.FmApplyWheatResp;
import com.qpyy.room.bean.GiftBackResp;
import com.qpyy.room.bean.GiftNumBean;
import com.qpyy.room.bean.LuckGiftBean;
import com.qpyy.room.bean.MixerResp;
import com.qpyy.room.bean.MusicResp;
import com.qpyy.room.bean.NewsListBean;
import com.qpyy.room.bean.NewsModel;
import com.qpyy.room.bean.NextBoxContentResp;
import com.qpyy.room.bean.PitCountDownBean;
import com.qpyy.room.bean.ProductsModel;
import com.qpyy.room.bean.ProtectedItemBean;
import com.qpyy.room.bean.ProtectedRankingListResp;
import com.qpyy.room.bean.PutOnWheatResp;
import com.qpyy.room.bean.RoomAdminModel;
import com.qpyy.room.bean.RoomBgBean;
import com.qpyy.room.bean.RoomExtraModel;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.room.bean.RoomOnlineResp;
import com.qpyy.room.bean.RoomPitInfo;
import com.qpyy.room.bean.RoomPitUserModel;
import com.qpyy.room.bean.RoomPollModel;
import com.qpyy.room.bean.RoomSceneItem;
import com.qpyy.room.bean.RoomShutUp;
import com.qpyy.room.bean.RoomUserInfoResp;
import com.qpyy.room.bean.SearchUserModel;
import com.qpyy.room.bean.WealthRankingResp;
import com.qpyy.room.bean.WinJackpotModel;
import com.qpyy.room.bean.WxPayModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.api
 * 创建人 王欧
 * 创建时间 2020/7/24 2:56 PM
 * 描述 describe
 */
public interface RoomApi {

    @FormUrlEncoded
    @POST(URLConstants.ROOM_GET_IN)
    Observable<BaseModel<RoomInfoResp>> roomGetIn(@Field("room_id") String roomId, @Field("password") String password);


    @GET(URLConstants.MQTT_HEART_BEAT)
    Observable<BaseModel<String>> mqttHeartBeat(@Query("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.ROOM_ONLINE)
    Observable<BaseModel<RoomOnlineResp>> getRoomOnline(@Field("room_id") String roomId, @Field("p") int page);

    @POST(URLConstants.CHARM_LIST)
    @FormUrlEncoded
    Observable<BaseModel<CharmRankingResp>> getCharmList(@Field("type") int type, @Field("room_id") String roomId);

    @POST(URLConstants.WEALTH_LIST)
    @FormUrlEncoded
    Observable<BaseModel<WealthRankingResp>> getWealthList(@Field("type") int type, @Field("room_id") String roomId);

    @GET(URLConstants.SEARCHMUSIC)
    Observable<BaseModel<List<MusicResp>>> searchMusic(@Query("input") String input, @Query("filter") String filter, @Query("type") String type, @Query("page") int page);

    @Streaming //大文件时要加不然会OOM
    @GET
    Call<ResponseBody> download(@Url String fileUrl);

    @FormUrlEncoded
    @POST(URLConstants.SEARCH_USER)
    Observable<BaseModel<List<SearchUserModel>>> getSearChUser(@Field("token") String token, @Field("room_id") String roomId, @Field("keyword") String keyword, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.ROOM_GET_LIST)
    Observable<BaseModel<List<SearchUserModel>>> getRoomList(@Field("room_id") String roomId, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.ADD_MANAGER)
    Observable<BaseModel<RoomAdminModel>> addManager(@Field("token") String token, @Field("room_id") String roomId, @Field("user_id") String userId);


    @FormUrlEncoded
    @POST(URLConstants.DELETE_MANAGER)
    Observable<BaseModel<RoomAdminModel>> deleteManager(@Field("token") String token, @Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(URLConstants.ADD_FORBID)
    Observable<BaseModel<String>> addRorbid(@Field("token") String token, @Field("room_id") String roomId, @Field("user_id") String userId);


    @FormUrlEncoded
    @POST(URLConstants.DELETE_FORBID)
    Observable<BaseModel<String>> deleteForbid(@Field("token") String token, @Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(URLConstants.ROOM_EXTRAINFO)
    Observable<BaseModel<RoomExtraModel>> getRoomExtra(@Field("token") String token, @Field("room_id") String roomId, @Field("password") String password);

    @FormUrlEncoded
    @POST(URLConstants.EDIT_ROOM)
    Observable<BaseModel<String>> editRoom(@Field("token") String token, @Field("cover_picture") String coverPicture, @Field("bg_picture") String bgPicture, @Field("password") String password,
                                           @Field("playing") String playing, @Field("room_id") String roomId, @Field("room_name") String roomName, @Field("label_id") String labelId,
                                           @Field("type_id") String typeId, @Field("greeting") String greeting, @Field("wheat") String wheat,
                                           @Field("is_password") String is_password);

    @FormUrlEncoded
    @POST(URLConstants.AGREEAPPLY)
    Observable<BaseModel<AgreeApplyResp>> agreeApply(@Field("id") String id, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.DELETEAPPLY)
    Observable<BaseModel<String>> deleteApply(@Field("ids") String ids, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.ROOM_BACKGROUND_LIST)
    Observable<BaseModel<List<RoomBgBean>>> getRoomBackgroundList(@Field("token") String token);

    @FormUrlEncoded
    @POST(URLConstants.AGREEAPPLYALL)
    Observable<BaseModel<String>> agreeApplyAll(@Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.APPLYWHEATUSERS)
    Observable<BaseModel<ApplyWheatUsersResp>> applyWheatUsers(@Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.EDIT_ROOM_BG)
    Observable<BaseModel<String>> editRoomBg(@Field("token") String token, @Field("bg_picture") String bgPicture, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.UPDATEPASSWORD)
    Observable<BaseModel<String>> updatePassword(@Field("room_id") String roomId, @Field("password") String password);

    @FormUrlEncoded
    @POST(URLConstants.ADD_FAVORITE)
    Observable<BaseModel<String>> addRoomCollect(@Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.ROOM_QUIT)
    Observable<BaseModel<String>> quit(@Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.BALANCE)
    Observable<BaseModel<String>> getBalance(@Field("token") String token);

    @FormUrlEncoded
    @POST(URLConstants.GIVEGIFT)
    Observable<BaseModel<RankInfo>> giveGift(@Field("token") String token, @Field("user_id") String userId, @Field("gift_id") String giftId,
                                             @Field("room_id") String roomId, @Field("pit") String pit, @Field("number") String num, @Field("gift_type") int gift_type);

    @FormUrlEncoded
    @POST(URLConstants.GIVEBACKGIFT)
    Observable<BaseModel<RankInfo>> giveBackGift(@Field("token") String token, @Field("user_id") String userId, @Field("gift_id")
            String giftId, @Field("room_id") String roomId, @Field("pit") String pit, @Field("number") String num);

    @FormUrlEncoded
    @POST(URLConstants.PITLIST)
    Observable<BaseModel<List<RoomPitUserModel>>> getRoomPitUser(@Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(URLConstants.GIFT_WALL)
    Observable<BaseModel<List<GiftModel>>> giftWall(@Field("token") String token);

    @FormUrlEncoded
    @POST(URLConstants.USER_BACKPACK)
    Observable<BaseModel<GiftBackResp>> userBackPack(@Field("token") String token);

    @FormUrlEncoded
    @POST(URLConstants.APPLY_WHEAT)
    Observable<BaseModel<String>> applyWheat(@Field("room_id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(URLConstants.DOWN_WHEAT)
    Observable<BaseModel<String>> downWheat(@Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.APPLY_WHEAT_WAIT)
    Observable<BaseModel<ApplyWheatWaitResp>> applyWheatWait(@Field("room_id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(URLConstants.GET_IN_ROOM_INFO)
    Observable<BaseModel<RoomInfoResp>> getInRoomInfo(@Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.APPLY_WHEAT_FM)
    Observable<BaseModel<FmApplyWheatResp>> applyWheatFm(@Field("room_id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(URLConstants.FM_OPEN_PROTECTED)
    Observable<BaseModel<String>> openFmProtected(@Field("room_id") String roomId, @Field("type") String type, @Field("user_id_protect") String userId);

    @FormUrlEncoded
    @POST(URLConstants.GET_PROTECTED_RANKING_LIST)
    Observable<BaseModel<ProtectedRankingListResp>> getProtectedRankingList(@Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.GET_ANCHOR_RANKING_LIST)
    Observable<BaseModel<AnchorRankingListResp>> getAnchorRankingList(@Field("room_id") String roomId, @Field("type") String type);

    @POST(URLConstants.GET_PROTECTED_LIST)
    Observable<BaseModel<List<ProtectedItemBean>>> getProtectedList();

    @FormUrlEncoded
    @POST(URLConstants.SYSTEM_NEWS_LIST)
    Observable<BaseModel<List<NewsListBean>>> systemNewsList(@Field("p") int page);

    @POST(URLConstants.SERVICEUSER)
    Observable<BaseModel<String>> serviceUser();

    @FormUrlEncoded
    @POST(URLConstants.EDIT_ROOM)
    Observable<BaseModel<String>> roomUpdate(@FieldMap Map<String, String> map);

    @POST(URLConstants.CATEGORIES)
    Observable<BaseModel<List<CategoriesModel>>> categories();

    @FormUrlEncoded
    @POST(URLConstants.PRODUCTS)
    Observable<BaseModel<List<ProductsModel>>> products(@Field("category_id") String categoryId);

    @FormUrlEncoded
    @POST(URLConstants.BUY_SHOP)
    Observable<BaseModel<String>> buyShop(@Field("friend_id") String friendId, @Field("product_id") String productId, @Field("price_id") String priceId);

    @FormUrlEncoded
    @POST(URLConstants.ROOMUSERINFO)
    Observable<BaseModel<RoomUserInfoResp>> getRoomUserInfo(@Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(URLConstants.FOLLOW)
    Observable<BaseModel<String>> follow(@Field("token") String token, @Field("user_id") String userId, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.DOWN_USER_WHEAT)
    Observable<BaseModel<String>> downUserWheat(@Field("token") String token, @Field("pit_number") String pitNumber, @Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(URLConstants.KICKOUT)
    Observable<BaseModel<String>> kickOut(@Field("token") String token, @Field("user_id") String userId, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.ROOM_USER_SHUTUP)
    Observable<BaseModel<RoomShutUp>> roomUserShutUp(@Field("room_id") String roomId, @Field("pit_number") String userId, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.SETROOMBANNED)
    Observable<BaseModel<RoomBannedModel>> setRoomBanned(@Field("token") String token, @Field("id") String roomId, @Field("user_id") String userId, @Field("type") int type);

    @POST(URLConstants.FACE_LIST)
    Observable<BaseModel<List<ExclusiveEmojiResp>>> faceList();

    @POST(URLConstants.FACE_SPECIAL)
    Observable<BaseModel<List<ExclusiveEmojiResp>>> faceSpecial();

    @FormUrlEncoded
    @POST(URLConstants.RECHARGE)
    Observable<BaseModel<String>> userRecharge(@Field("token") String token, @Field("money") String money, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.RECHARGE_INFO)
    Observable<BaseModel<ArrayList<RechargeInfoModel>>> rechargeInfo(@Field("token") String token);

    @FormUrlEncoded
    @POST(URLConstants.ALIPAYMENT)
    Observable<BaseModel<String>> aliPay(@Field("token") String token, @Field("user_id") String userId, @Field("type") int type, @Field("id") String id);

    @FormUrlEncoded
    @POST(URLConstants.WXPAYMENT)
    Observable<BaseModel<WxPayModel>> wxPay(@Field("token") String token, @Field("user_id") String userId, @Field("type") int type, @Field("id") String id);

    @FormUrlEncoded
    @POST(URLConstants.PUT_ON_WHEAT)
    Observable<BaseModel<PutOnWheatResp>> putOnWheat(@Field("room_id") String roomId, @Field("user_id") String userId);

    @FormUrlEncoded
    @POST(URLConstants.ROOM_GUIDE)
    Observable<BaseModel<String>> roomGuide(@Field("room_id") String roomId);

    @POST(URLConstants.ROOM_FANS_NOTICE_INFO)
    Observable<BaseModel<FansNotifyInfo>> fansNoticeInfo();

    @FormUrlEncoded
    @POST(URLConstants.ROOM_FANS_NOTICE)
    Observable<BaseModel<String>> fansNotice(@Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.GIFT_NUMBER_SET)
    Observable<BaseModel<List<GiftNumBean>>> giftNumberSet(@Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.GIVE_BACK_GIFT_ALL)
    Observable<BaseModel<RankInfo>> giveGiftBackAll(@Field("room_id") String roomId, @Field("user_id") String userId, @Field("pit") String pit);

    @FormUrlEncoded
    @POST(URLConstants.SEND_FACE)
    Observable<BaseModel<String>> sendFace(@Field("room_id") String roomId, @Field("face_id") String face_id, @Field("pit_number") String pit, @Field("type") int type);

    @POST(URLConstants.SOUND_EFFECT)
    Observable<BaseModel<List<RoomSceneItem>>> soundAffectInfo();

    @FormUrlEncoded
    @POST(URLConstants.UPDATE_SOUND_EFFECT)
    Observable<BaseModel<String>> updateSoundAffect(@Field("room_id") String roomId, @Field("sound_effect_id") String sound_effect_id);

    @FormUrlEncoded
    @POST(URLConstants.PIT_COUNT_DOWN)
    Observable<BaseModel<PitCountDownBean>> pitCountDown(@Field("room_id") String roomId, @Field("pit_number") String pitNumber, @Field("time") String time);

    @FormUrlEncoded
    @POST(URLConstants.LOG_EMCHAT)
    Observable<BaseModel<String>> logEmchat(@Field("code") int code, @Field("msg") String msg, @Field("toChatUsername") String toChatUsername);

    @FormUrlEncoded
    @POST(URLConstants.ROOM_PITINFO)
    Observable<BaseModel<RoomPitInfo>> roomPitInfo(@Field("room_id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(URLConstants.SETROOMCARDIAC)
    Observable<BaseModel<String>> setRoomCardiac(@Field("token") String token, @Field("room_id") String roomId, @Field("state") int state);

    @FormUrlEncoded
    @POST(URLConstants.CLEARROOMCARDIAC)
    Observable<BaseModel<String>> clearRoomCardiac(@Field("token") String token, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.CLEAR_CARDIAC)
    Observable<BaseModel<String>> clearCardiac(@Field("token") String token, @Field("id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(URLConstants.CLOSEPIT)
    Observable<BaseModel<ClosePitModel>> closePit(@Field("token") String token, @Field("type") String type, @Field("pit_number") String pitNumber, @Field("id") String id);

    @FormUrlEncoded
    @POST(URLConstants.SENDCHATMSG)
    Observable<BaseModel<String>> sendTxtMessage(@Field("user_id") String user_id, @Field("type") String type, @Field("content") String content, @Field("room_id") String room_id);

    @POST(URLConstants.USERNEWS)
    Observable<BaseModel<NewsModel>> userNews();

    @FormUrlEncoded
    @POST(URLConstants.BALLSTART)
    Observable<BaseModel<BallResp>> ballStart(@Field("room_id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(URLConstants.BALLTHROW)
    Observable<BaseModel<String>> ballThrow(@Field("room_id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(URLConstants.BALLSHOW)
    Observable<BaseModel<BallResp>> ballShow(@Field("room_id") String roomId, @Field("pit_number") String pitNumber);

    @FormUrlEncoded
    @POST(URLConstants.ROOM_ROLL)
    Observable<BaseModel<RoomPollModel>> roomPoll(@Field("room_id") String roomId, @Field("type") int type, @Field("pit_number") String pitNumber);

    @GET(URLConstants.RANCARDS)
    Observable<BaseModel<CardResultBean>> ranCards(@Query("token") String token, @Query("count") String count);

    @FormUrlEncoded
    @POST(URLConstants.RANTOUZI)
    Observable<BaseModel<String>> ranTouzi(@Field("token") String token);

    @POST(URLConstants.MIXER)
    Observable<BaseModel<List<MixerResp>>> mixer();

    @FormUrlEncoded
    @POST(URLConstants.SETUSERMIXER)
    Observable<BaseModel<String>> setUserMixer(@Field("room_id") String roomId, @Field("id") int id);

    @FormUrlEncoded
    @POST(URLConstants.SWITCHVOICE)
    Observable<BaseModel<String>> switchVoice(@Field("id") String id, @Field("pit_number") String pitNumber, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.SWITCHPUBLICSCREEN)
    Observable<BaseModel<String>> switchPublicScreen(@Field("room_id") String room_id, @Field("status") String status);

    @FormUrlEncoded
    @POST(URLConstants.CLOSE_ALL_PIT)
    Observable<BaseModel<String>> closeAllPit(@Field("room_id") String id);

    @GET(URLConstants.GET_FISH_INFO)
    Observable<BaseModel<FishInfoBean>> getFishInfo(@Query("token") String token, @Query("lucky_bag_type") String type);

    @FormUrlEncoded
    @POST(URLConstants.FISHING)
    Observable<BaseModel<LuckGiftBean>> startFishing(@Field("token") String token, @Field("lottery_number") int number, @Field("lucky_bag_type") int type);

    @FormUrlEncoded
    @POST(URLConstants.CATHELP)
    Observable<BaseModel<CatHelpModel>> getCatHelp(@Field("token") String token);

    @GET(URLConstants.WINJACKPOT)
    Observable<BaseModel<List<WinJackpotModel>>> getCatWinJackpot(@Query("token") String token, @Query("lucky_bag_type") String type);

    @POST(URLConstants.NEXT_BOX_CONTENT)
    Observable<BaseModel<NextBoxContentResp>> getNextBoxContent();
}
