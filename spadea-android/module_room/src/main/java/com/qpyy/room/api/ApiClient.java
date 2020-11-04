package com.qpyy.room.api;

import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.libcommon.bean.RankInfo;
import com.qpyy.libcommon.bean.RechargeInfoModel;
import com.qpyy.libcommon.bean.RoomBannedModel;
import com.qpyy.libcommon.http.BaseModel;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.http.RetrofitClient;
import com.qpyy.libcommon.http.transform.DefaultTransformer;
import com.qpyy.libcommon.utils.SpUtils;
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
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.api
 * 创建人 王欧
 * 创建时间 2020/6/29 10:50 AM
 * 描述 describe
 */
public class ApiClient {
    private static final ApiClient ourInstance = new ApiClient();

    private RoomApi api;

    public static ApiClient getInstance() {
        return ourInstance;
    }

    private ApiClient() {
        api = RetrofitClient.getInstance().createApiClient(RoomApi.class);
    }

    //获取房间在线人数数据
    public void getRoomOnlineList(String roomId, int page, BaseObserver<RoomOnlineResp> observer) {
        api.getRoomOnline(roomId, page).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //获取房间魅力榜数据
    public void getCharmList(String roomId, int type, BaseObserver<CharmRankingResp> observer) {
        api.getCharmList(type, roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //获取房间财富榜数据
    public void getWealthList(String roomId, int type, BaseObserver<WealthRankingResp> observer) {
        api.getWealthList(type, roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //获取房间信息
    public void roomGetIn(String roomId, String password, BaseObserver<RoomInfoResp> observer) {
        api.roomGetIn(roomId, password).compose(new DefaultTransformer<>()).subscribe(observer);
    }
    //mqtt心跳
    public void mqttHeartBeat(String roomId, BaseObserver<String> observer) {
        api.mqttHeartBeat(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //搜索音乐
    public void searchMusic(String input, String filter, String type, int page, BaseObserver<List<MusicResp>> observer) {
        api.searchMusic(input, filter, type, page).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //下载
    public void download(String fileUrl, Callback<ResponseBody> observer) {
        api.download(fileUrl).enqueue(observer);
    }

    //获取搜索用户
    public void getSearChUser(String token, String roomId, String keyword, int type, BaseObserver<List<SearchUserModel>> observer) {
        api.getSearChUser(token, roomId, keyword, type).compose(new DefaultTransformer<
                BaseModel<List<SearchUserModel>>, List<SearchUserModel>>())
                .subscribe(observer);
    }

    //获取房间列表
    public void getRoomList(String roomId, int type, BaseObserver<List<SearchUserModel>> observer) {
        api.getRoomList(roomId, type).compose(new DefaultTransformer<BaseModel<List<SearchUserModel>>, List<SearchUserModel>>())
                .subscribe(observer);
    }

    //添加管理员
    public void addManager(String token, String roomId, String userId, BaseObserver<RoomAdminModel> observer) {
        api.addManager(token, roomId, userId).compose(new DefaultTransformer<BaseModel<RoomAdminModel>, RoomAdminModel>()).subscribe(observer);
    }

    //删除管理员
    public void deleteManager(String token, String roomId, String userId, BaseObserver<RoomAdminModel> observer) {
        api.deleteManager(token, roomId, userId).compose(new DefaultTransformer<BaseModel<RoomAdminModel>, RoomAdminModel>()).subscribe(observer);
    }

    //添加黑名单
    public void addRorbid(String token, String roomId, String userId, BaseObserver<String> observer) {
        api.addRorbid(token, roomId, userId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //删除黑名单
    public void deleteForbid(String token, String roomId, String userId, BaseObserver<String> observer) {
        api.deleteForbid(token, roomId, userId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //获取房间其他信息
    public void getRoomExtra(String token, String roomId, String password, BaseObserver<RoomExtraModel> observer) {
        api.getRoomExtra(token, roomId, password).compose(new DefaultTransformer<BaseModel<RoomExtraModel>, RoomExtraModel>()).subscribe(observer);
    }

    //修改房间信息
    public void editRoom(String token, String coverPicture, String bgPicture, String password, String playing, String roomId, String roomName, String typeId, String labelId, String greeting, String wheat, String is_password, BaseObserver<String> observer) {
        api.editRoom(token, coverPicture, bgPicture, password, playing, roomId, roomName, labelId, typeId, greeting, wheat, is_password).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void agreeApply(String id, String roomId, BaseObserver<AgreeApplyResp> observer) {
        api.agreeApply(id, roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void deleteApply(String id, String roomId, BaseObserver<String> observer) {
        api.deleteApply(id, roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void agreeApplyAll(String roomId, BaseObserver<String> observer) {
        api.agreeApplyAll(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void applyWheatUsers(String roomId, BaseObserver<ApplyWheatUsersResp> observer) {
        api.applyWheatUsers(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void editRoomBg(String bgPicture, String roomId, BaseObserver<String> observer) {
        api.editRoomBg(SpUtils.getToken(), bgPicture, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }


    public void getRoomBackgroudList(BaseObserver<List<RoomBgBean>> observer) {
        api.getRoomBackgroundList(SpUtils.getToken()).compose(new DefaultTransformer<BaseModel<List<RoomBgBean>>, List<RoomBgBean>>()).subscribe(observer);
    }


    public void updatePassword(String roomId, String password, BaseObserver<String> observer) {
        api.updatePassword(roomId, password).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void addRoomCollect(String roomId, BaseObserver<String> observer) {
        api.addRoomCollect(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void quit(String roomId, BaseObserver<String> observer) {
        api.quit(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void applyWheat(String roomId, String pitNumber, BaseObserver<String> observer) {
        api.applyWheat(roomId, pitNumber).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void downWheat(String roomId, BaseObserver<String> observer) {
        api.downWheat(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void applyWheatWait(String roomId, String pitNumber, BaseObserver<ApplyWheatWaitResp> observer) {
        api.applyWheatWait(roomId, pitNumber).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getRoomInfo(String roomId, BaseObserver<RoomInfoResp> observer) {
        api.getInRoomInfo(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void applyWheatFm(String roomId, String pitNumber, BaseObserver<FmApplyWheatResp> observer) {
        api.applyWheatFm(roomId, pitNumber).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void openFmProtected(String roomId, String type, String userId, BaseObserver<String> observer) {
        api.openFmProtected(roomId, type, userId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getProtectedRankingList(String roomId, BaseObserver<ProtectedRankingListResp> observer) {
        api.getProtectedRankingList(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getAnchorRankingList(String roomId, String type, BaseObserver<AnchorRankingListResp> observer) {
        api.getAnchorRankingList(roomId, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getProtectedList(BaseObserver<List<ProtectedItemBean>> observer) {
        api.getProtectedList().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //获取余额
    public void getBalance(String token, BaseObserver<String> observer) {
        api.getBalance(token).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //赠送礼物
    public void giveGift(String token, String userId, String giftId, String roomId, String pit, String num, int gift_type, BaseObserver<RankInfo> observer) {
        api.giveGift(token, userId, giftId, roomId, pit, num, gift_type).compose(new DefaultTransformer<BaseModel<RankInfo>, RankInfo>()).subscribe(observer);
    }

    //回物
    public void giveBackGift(String token, String userId, String giftId, String roomId, String pit, String num, BaseObserver<RankInfo> observer) {
        api.giveBackGift(token, userId, giftId, roomId, pit, num).compose(new DefaultTransformer<BaseModel<RankInfo>, RankInfo>()).subscribe(observer);
    }

    //获取房间上麦用户
    public void getRoomPitUser(String roomId, String userId, BaseObserver<List<RoomPitUserModel>> observer) {
        api.getRoomPitUser(roomId, userId).compose(new DefaultTransformer<BaseModel<List<RoomPitUserModel>>, List<RoomPitUserModel>>()).subscribe(observer);
    }

    //礼物图
    public void giftWall(String token, BaseObserver<List<GiftModel>> observer) {
        api.giftWall(token).compose(new DefaultTransformer<BaseModel<List<GiftModel>>, List<GiftModel>>())
                .subscribe(observer);
    }

    public void userBackPack(String token, BaseObserver<GiftBackResp> observer) {
        api.userBackPack(token).compose(new DefaultTransformer<>())
                .subscribe(observer);
    }

    //系统消息列表
    public void systemNewsList(int page, BaseObserver<List<NewsListBean>> observer) {
        api.systemNewsList(page).compose(new DefaultTransformer<>()).subscribe(observer);

    }

    public void serviceUser(BaseObserver<String> observer) {
        api.serviceUser().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //购买类别
    public void categories(BaseObserver<List<CategoriesModel>> observer) {
        api.categories().compose(new DefaultTransformer<BaseModel<List<CategoriesModel>>, List<CategoriesModel>>())
                .subscribe(observer);
    }

    //产品
    public void products(String categoryId, BaseObserver<List<ProductsModel>> observer) {
        api.products(categoryId).compose(new DefaultTransformer<BaseModel<List<ProductsModel>>, List<ProductsModel>>())
                .subscribe(observer);
    }

    //购物
    public void buyShop(String friendId, String productId, String priceId, BaseObserver<String> observer) {
        api.buyShop(friendId, productId, priceId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //更新房间信息
    public void roomUpdate(Map<String, String> map, BaseObserver<String> observer) {
        api.roomUpdate(map).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //用户信息
    public void getRoomUserInfo(String roomId, String userId, BaseObserver<RoomUserInfoResp> observer) {
        api.getRoomUserInfo(roomId, userId).compose(new DefaultTransformer<>()).subscribe(observer);
    }


    public void follow(String token, String userId, int type, BaseObserver<String> observer) {
        api.follow(token, userId, type).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void downUserWheat(String token, String pitNumber, String roomId, String userId, BaseObserver<String> observer) {
        api.downUserWheat(token, pitNumber, roomId, userId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //踢出
    public void kickOut(String token, String userId, String roomId, BaseObserver<String> observer) {
        api.kickOut(token, userId, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //禁麦 1为禁麦 2解禁
    public void roomUserShutUp(String roomId, String pitNumber, int type, BaseObserver<RoomShutUp> observer) {
        api.roomUserShutUp(roomId, pitNumber, type).compose(new DefaultTransformer<BaseModel<RoomShutUp>, RoomShutUp>()).subscribe(observer);
    }

    //禁言
    public void setRoomBanned(String token, String roomId, String userId, int type, BaseObserver<RoomBannedModel> observer) {
        api.setRoomBanned(token, roomId, userId, type).compose(new DefaultTransformer<BaseModel<RoomBannedModel>, RoomBannedModel>()).subscribe(observer);
    }


    public void faceList(BaseObserver<List<ExclusiveEmojiResp>> observer) {
        api.faceList().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void faceSpecial(BaseObserver<List<ExclusiveEmojiResp>> observer) {
        api.faceSpecial().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //用户充值
    public void userRecharge(String token, String money, int type, BaseObserver<String> observer) {
        api.userRecharge(token, money, type).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void rechargeInfo(String token, BaseObserver<ArrayList<RechargeInfoModel>> observer) {
        api.rechargeInfo(token).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //支付宝支付
    public void aliPay(String token, String userId, int type, String id, BaseObserver<String> observer) {
        api.aliPay(token, userId, type, id).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //微信支付
    public void wxPay(String token, String userId, int type, String id, BaseObserver<WxPayModel> observer) {
        api.wxPay(token, userId, type, id).compose(new DefaultTransformer<BaseModel<WxPayModel>, WxPayModel>()).subscribe(observer);
    }

    public void putOnWheat(String roomId, String userId, BaseObserver<PutOnWheatResp> observer) {
        api.putOnWheat(roomId, userId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void roomGuide(String roomId, BaseObserver<String> observer) {
        api.roomGuide(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void fansNotify(String roomId, BaseObserver<String> observer) {
        api.fansNotice(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void fansNotifyInfo(BaseObserver<FansNotifyInfo> observer) {
        api.fansNoticeInfo().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void giftNumberSet(String roomId, BaseObserver<List<GiftNumBean>> observer) {
        api.giftNumberSet(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void giveGiftBackAll(String roomId, String userId, String pit, BaseObserver<RankInfo> observer) {
        api.giveGiftBackAll(roomId, userId, pit).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void sendFace(String roomId, String face_id, String pit, int type, BaseObserver<String> observer) {
        api.sendFace(roomId, face_id, pit, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void soundAffectInfo(BaseObserver<List<RoomSceneItem>> observer) {
        api.soundAffectInfo().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void updateSoundAffect(String roomId, String id, BaseObserver<String> observer) {
        api.updateSoundAffect(roomId, id).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void pitCountDown(String roomId, String pitNumber, String time, BaseObserver<PitCountDownBean> observer) {
        api.pitCountDown(roomId, pitNumber, time).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void logEmchat(int code, String msg, String toChatUsername, BaseObserver<String> observer) {
        api.logEmchat(code, msg, toChatUsername).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    //房间麦位信息
    public void roomPitInfo(String roomId, String pitNumber, BaseObserver<RoomPitInfo> observer) {
        api.roomPitInfo(roomId, pitNumber).compose(new DefaultTransformer<BaseModel<RoomPitInfo>, RoomPitInfo>()).subscribe(observer);
    }

    //设置麦位心动值
    public void setRoomCardiac(String token, String roomId, int state, BaseObserver<String> observer) {
        api.setRoomCardiac(token, roomId, state).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //清除房间心动值
    public void clearRoomCardiac(String token, String roomId, BaseObserver<String> observer) {
        api.clearRoomCardiac(token, roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //清除麦位心动值
    public void clearCardiac(String token, String roomId, String pitNumber, BaseObserver<String> observer) {
        api.clearCardiac(token, roomId, pitNumber).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //锁麦
    public void closePit(String token, String type, String pitNumber, String id, BaseObserver<ClosePitModel> observer) {
        api.closePit(token, type, pitNumber, id).compose(new DefaultTransformer<BaseModel<ClosePitModel>, ClosePitModel>()).subscribe(observer);
    }

    //环信公屏和私聊消息
    public void sendTxtMessage(String user_id, String type, String content, String room_id, BaseObserver<String> observer) {
        api.sendTxtMessage(user_id, type, content, room_id).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //用户消息
    public void userNews(BaseObserver<NewsModel> observer) {
        api.userNews().compose(new DefaultTransformer<BaseModel<NewsModel>, NewsModel>()).subscribe(observer);
    }

    public void ballStart(String roomId, String pitNumber, BaseObserver<BallResp> observer) {
        api.ballStart(roomId, pitNumber).compose(new DefaultTransformer<BaseModel<BallResp>, BallResp>()).subscribe(observer);
    }

    public void ballThrow(String roomId, String pitNumber, BaseObserver<String> observer) {
        api.ballThrow(roomId, pitNumber).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void ballShow(String roomId, String pitNumber, BaseObserver<BallResp> observer) {
        api.ballShow(roomId, pitNumber).compose(new DefaultTransformer<BaseModel<BallResp>, BallResp>()).subscribe(observer);
    }

    //抽签
    public void roomPoll(String roomId, int type, String pitNumber, BaseObserver<RoomPollModel> observer) {
        api.roomPoll(roomId, type, pitNumber).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void ranCards(String token, String count, BaseObserver<CardResultBean> observer) {
        api.ranCards(token, count).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void ranTouzi(String token, BaseObserver<String> observer) {
        api.ranTouzi(token).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void mixer(BaseObserver<List<MixerResp>> observer) {
        api.mixer().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void setUserMixer(String roomId, int id, BaseObserver<String> observer) {
        api.setUserMixer(roomId, id).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void switchVoice(String id, String pitNumber, int type, BaseObserver<String> observer) {
        api.switchVoice(id, pitNumber, type).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    //获取显示心动

    //获取开关公屏
    public void switchPublicScreen(String room_id, String status, BaseObserver<String> observer) {
        api.switchPublicScreen(room_id, status).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void closeAllPit(String roomId, BaseObserver<String> observer) {
        api.closeAllPit(roomId).compose(new DefaultTransformer<BaseModel<String>, String>()).subscribe(observer);
    }

    public void getFishInfo(String type,BaseObserver<FishInfoBean> observer) {
        api.getFishInfo(SpUtils.getToken(),type).compose(new DefaultTransformer<BaseModel<FishInfoBean>, FishInfoBean>()).subscribe(observer);
    }

    public void startFishing(String token, int number,int type, BaseObserver<LuckGiftBean> observer) {
        api.startFishing(token, number,type).compose(new DefaultTransformer<BaseModel<LuckGiftBean>, LuckGiftBean>())
                .subscribe(observer);
    }

    public void getCatHelp(String token, BaseObserver<CatHelpModel> observer) {
        api.getCatHelp(token).compose(new DefaultTransformer<BaseModel<CatHelpModel>, CatHelpModel>())
                .subscribe(observer);
    }
    public void getCatWinJackpot(String token, String type,BaseObserver<List<WinJackpotModel>> observer) {
        api.getCatWinJackpot(token,type).compose(new DefaultTransformer<BaseModel<List<WinJackpotModel>>, List<WinJackpotModel>>())
                .subscribe(observer);
    }

    public void getNextBoxContent(BaseObserver<NextBoxContentResp> observer) {
        api.getNextBoxContent().compose(new DefaultTransformer<>()).subscribe(observer);
    }
}
