package com.spadea.xqipao.data.api;


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
import com.spadea.xqipao.data.OrdersResp;
import com.spadea.xqipao.data.PitCountDownBean;
import com.spadea.xqipao.data.ProductsModel;
import com.spadea.xqipao.data.ProtectedItemBean;
import com.spadea.xqipao.data.ProtectedRankingListResp;
import com.qpyy.libcommon.bean.RankInfo;
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
import com.qpyy.libcommon.bean.UserBean;
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

import java.util.List;

public interface DataSource {

    void getCharmList(String token, int type, String roomId, BaseObserver<CharmModel> observer);

    void getWealthList(String token, int type, String roomId, BaseObserver<CharmModel> observer);

    void getWeekStarList(String token, BaseObserver<WeekStarModel> observer);

    void getRoomRankingList(String token, BaseObserver<List<RoomRankingModel>> observer);

    void verificationRoomPassword(String token, String id, String password, BaseObserver<BaseModel> observer);

    void addBank(String token, String bankNum, String cardholder, int bankType, String bankName, String mobile, String bankZhi, String cardNumber, String code, BaseObserver<String> observer);


    void sendCode(String token, String mobile, int type, BaseObserver<String> observer);

    void getUserBank(String token, BaseObserver<UserBankModel> observer);

    void userRecharge(String token, String money, int type, BaseObserver<String> observer);

    void aliPay(String token, String userId, int type, String id, BaseObserver<String> observer);

    void wxPay(String token, String userId, int type, String id, BaseObserver<WxPayModel> observer);

    void editBank(String token, String cardholder, String bank_name,
                  String mobile, String card_number, String id,
                  String bank_num, String bank_zhi, String code, BaseObserver<String> observer);


    void userWithdraw(String token, String bank_id, String number, String password, BaseObserver<String> observer);

    void login(String mobile, String password, String code, int type, BaseObserver<UserBean> observer);

    void oauthLogin(String netease_token, String access_token, int type, BaseObserver<UserBean> observer);

    void setUserSex(String userId, int sex, BaseObserver<String> observer);

    void thirdPartyLogin(String openId, int three_party, String nickname, String head_pic, BaseObserver<UserBean> observer);

    void getEarnings(String token, BaseObserver<EarningsModel> observer);

    void convertEarnings(String token, String number, String password, BaseObserver<String> observer);

    void getCashLog(String token, int p, int change_type, BaseObserver<List<EarningsModel.EarningInfo>> observer);


    void getWinRanking(int type, String token, BaseObserver<List<CatFishingModel>> observer);

    void getCatHelp(String token, BaseObserver<CatHelpModel> observer);


    void getCatWinJackpot(String token, String type, BaseObserver<List<WinJackpotModel>> observer);

    void getBalance(String token, BaseObserver<String> observer);


    void startFishing(String token, int number, int type, BaseObserver<LuckGiftBean> observer);

    void getRoomDetails(String token, String id, String password, BaseObserver<RoomDetailModel> observer);

    void getFaceList(String token, BaseObserver<List<EmojiModel>> observer);

    void joinRoom(String token, String roomId, BaseObserver<RoomInfoModel> observer);


    void getSearChUser(String token, String roomId, String keyword, int type, BaseObserver<List<SearchUserModel>> observer);

    void getRoomList(String roomId, int type, BaseObserver<List<SearchUserModel>> observer);

    void addManager(String token, String roomId, String userId, BaseObserver<RoomManageModel> observer);

    void deleteManager(String token, String roomId, String userId, BaseObserver<RoomManageModel> observer);

    void addRorbid(String token, String roomId, String userId, BaseObserver<String> observer);

    void deleteForbid(String token, String roomId, String userId, BaseObserver<String> observer);

    void addRoomCollect(String token, String roomId, BaseObserver<String> observer);

    void applyWheat(String token, String roomId, String pitNumber, BaseObserver<String> observer);

    void downWheat(String token, String roomId, BaseObserver<String> observer);

    void switchVoice(String token, String id, int type, BaseObserver<String> observer);

    void clearCardiac(String token, String roomId, String pitNumber, BaseObserver<String> observer);

    void shutUp(String token, String userId, String type, String pitNumber, String id, BaseObserver<String> observer);

    void closePit(String token, String type, String pitNumber, String id, BaseObserver<ClosePitModel> observer);

    void getRoomUserInfo(String token, String userId, int visit, BaseObserver<RoomUserInfoModel> observer);

    void giftWall(String token, BaseObserver<List<GiftModel>> observer);

    void userBackPack(String token, BaseObserver<List<GiftModel>> observer);

    void getRoomEnter(String token, String roomId, String password, BaseObserver<WheatModel> observer);

    void getRoomExtra(String token, String roomId, String password, BaseObserver<RoomExtraModel> observer);

    void downUserWheat(String token, String pitNumber, String roomId, String userId, BaseObserver<String> observer);

    void follow(String token, String userId, int type, BaseObserver<String> observer);

    void setRoomBanned(String token, String roomId, String userId, int type, BaseObserver<RoomBannedModel> observer);

    void editRoom(String token, String coverPicture, String bgPicture, String password, String playing, String roomId, String roomName, String labelId, String typeId, String greeting, String wheat, String is_password, BaseObserver<String> observer);

    void editRoomBg(String bgPicture, String roomId, BaseObserver<String> observer);

    void giveGift(String token, String userId, String giftId, String roomId, String pit, String num, BaseObserver<RankInfo> observer);

    void giveBackGift(String token, String userId, String giftId, String roomId, String pit, String num, BaseObserver<RankInfo> observer);

    void applyWheatList(String token, String roomId, BaseObserver<List<RowWheatModel>> observer);

    void deleteApply(String token, String ids, String roomId, BaseObserver<String> observer);

    void applyWheatWait(String token, String roomId, String pitNumber, BaseObserver<ApplyWheatWaitModel> observer);

    void agreeApply(String token, String id, String roomId, BaseObserver<AgreeApplyModel> observer);

    void agreeApplyAll(String token, String roomId, BaseObserver<String> observer);


    void clearRoomCardiac(String token, String roomId, BaseObserver<String> observer);

    void setRoomCardiac(String token, String roomId, int state, BaseObserver<String> observer);

    void removeFavorite(String token, String roomId, BaseObserver<String> observer);

    void kickOut(String token, String userId, String roomId, BaseObserver<String> observer);

    void quit(String token, String roomId, BaseObserver<String> observer);

    void roomAuth(String token, String roomId, BaseObserver<RoomAuthModel> observer);

    void online(BaseObserver<String> observer);

    void getBanners(BaseObserver<List<BannerModel>> observer);

    void roomType(BaseObserver<List<RoomTypeModel>> observer);

    void roomList(String typeId, BaseObserver<List<RoomModel>> observer);

    void hotRoom(String userId, BaseObserver<List<RoomModel>> observer);

    void manageRoom(int page, BaseObserver<MyManageRoomModel> observer);

    void collectRoom(int page, BaseObserver<List<ManageRoomModel>> observer);

    void isFoundRoom(String token, BaseObserver<String> observer);


    void friendList(int page,BaseObserver<List<FriendModel>> observer);

    void followList(int page,BaseObserver<List<FriendModel>> observer);

    void fansList(int page,BaseObserver<List<FriendModel>> observer);

    void userInfo(BaseObserver<UserInfoModel> observer);

    void categories(BaseObserver<List<CategoriesModel>> observer);

    void products(String categoryId, BaseObserver<List<ProductsModel>> observer);

    void buyShop(String friendId, String productId, String priceId, BaseObserver<String> observer);

    void userInfoData(String userId, String emchatUsername, int visit, BaseObserver<UserInfoDataModel> observer);


    void searchUser(String keyword, BaseObserver<List<SearchUserInfo>> observer);

    void searchRoom(String keyword, BaseObserver<List<SearchRoomInfo>> observer);

    void vipInfo(BaseObserver<VipInfo> observer);

    void serviceUser(BaseObserver<String> observer);

    void articleCategories(BaseObserver<List<HelpTitleModel>> observer);

    void articleList(String articleCatId, BaseObserver<List<HelpModel>> observer);

    void userNobilityInfo(BaseObserver<NobilityInfo> observer);

    void nobility(BaseObserver<List<NobilityModel>> observer);

    void buyNobility(String nobilityId, BaseObserver<String> observer);

    void renewNobility(String day, BaseObserver<String> observer);

    void myProducts(String categoryId, BaseObserver<List<MyProductsModel>> observer);

    void myUsingProducts(String categoryId, BaseObserver<UsingProductsModel> observer);

    void useProduct(String id, BaseObserver<String> observer);

    void downProduct(String id, BaseObserver<String> observer);

    void updateUserInfo(String signature, String birthday, String constellation, String profession,
                        String city_id, String user_photo, String sex, String head_picture,
                        String nickname, String province_id, String userNo, String county_id, BaseObserver<String> observer);

    void indexLabel(String categoryId, int p, BaseObserver<List<LabelModel>> observer);

    void addLabel(String ids, BaseObserver<String> observer);


    void userNews(BaseObserver<NewsModel> observer);

    void cashType(BaseObserver<List<CashTypeModel>> observer);

    void roomOnline(String roomId, int page, BaseObserver<List<OnlineModel>> observer);

    void getRoomUserInfo(String roomId, String userId, BaseObserver<RoomUserInfo> observer);


    void roomUserShutUp(String roomId, String userId, int type, BaseObserver<RoomShutUp> observer);

    void roomPitInfo(String roomId, String pitNumber, BaseObserver<RoomPitInfo> observer);

    void comeUser(String token, int pager, BaseObserver<List<LatelyVisitInfo>> observer);

    void getTopTwo(BaseObserver<TopTwoModel> observer);

    void appUpdate(BaseObserver<AppUpdateModel> observer);

    void checkUpdate(BaseObserver<AppUpdateModel> observer);

    void randomHotRoom(BaseObserver<String> observer);

    void roomGetIn(String roomId, String password, BaseObserver<RoomDetailBean> observer);

    void putOnWheat(String roomId, String userId, BaseObserver<String> observer);

    void updatePassword(String roomId, String password, BaseObserver<String> observer);

    void getRoomPitUser(String roomId, String userId, BaseObserver<List<RoomPitUserModel>> observer);


    void roomPoll(String roomId, int type, BaseObserver<RoomPollModel> observer);

    void getInRoomInfo(String roomId, BaseObserver<RoomDetailBean> observer);

    void userFiles(BaseObserver<UserBean> observer);

    void getRegionList(String parentId, BaseObserver<List<RegionListBean>> observer);

    void bindMobile(String mobile, String code, BaseObserver<String> observer);

    void resetPassword(String mobile, String code, String password, BaseObserver<String> observer);

    void searchMusic(String input, String filter, String type, int page, BaseObserver<List<MusicModel>> observer);

    void messageSetting(int broadcast, int fans, int news_voice, int news_vibrate, int only_friend, BaseObserver<String> observer);

    void userBlackList(int page, String keyword, BaseObserver<List<BlacListSectionBean>> observer);

    void removeUserBlack(String blackId, int type, BaseObserver<String> observer);

    void pitCountDown(String roomId, String pitNumber, String time, BaseObserver<PitCountDownBean> observer);

    void applyWheatFm(String roomId, String pitNumber, BaseObserver<FmApplyWheatResp> observer);

    void openFmProtected(String roomId, String type, String userId, BaseObserver<String> observer);

    void getProtectedRankingList(String roomId, BaseObserver<ProtectedRankingListResp> observer);

    void getAnchorRankingList(String roomId, String type, BaseObserver<AnchorRankingListResp> observer);

    void getProtectedList(BaseObserver<List<ProtectedItemBean>> observer);

    void getFishInfo(String type, BaseObserver<FishInfoBean> observer);

    void getRoomBackgroudList(BaseObserver<List<RoomBgBean>> observer);

    void updateUserAvatar(String headPicture, BaseObserver<UpdateUserAvatarResp> observer);

    void quitRoomWithUserId(String roomId, String userId, BaseObserver<String> observer);

    void cancelRoomManager(String roomId, BaseObserver<String> observer);

    void getSignHostory(BaseObserver<SignHistoryResp> observer);

    void getSignRewardList(BaseObserver<List<SignHistoryResp.RewardData>> observer);

    void signIn(BaseObserver<SignHistoryResp.RewardData> observer);

    void signSwitch(BaseObserver<SignSwitchModel> observer);

    void searchGuildById(String id, BaseObserver<GuildInfo> observer);

    void myGuildInfo(BaseObserver<MyGuildInfo> observer);

    void applyJoinGuild(String id, BaseObserver<String> observer);

    void quitGuild(String id, BaseObserver<String> observer);

    void setSecondPassword(String mobile, String password, String code, BaseObserver<String> observer);

    void nameAuth(String userId, String fullName, String idNumber, String idCard, String front, String back, BaseObserver<String> observer);

    void getNameAuthStatus(String userId, JavaBaseObserver<NameAuthResult> observer);

    void getUnionStateByUserId(String userId, JavaBaseObserver<GuildState> observer);

    void getUnionInfoByUserId(String userId, JavaBaseObserver<List<MyGuildInfo>> observer);

    void searchUnionInfo(String unionNum, JavaBaseObserver<GuildInfo> observer);

    void applyUnion(int applyType, String unionId, JavaBaseObserver<String> observer);

    void getUserPhotos(BaseObserver<List<MyPhotoItem>> observer);

    void deleteUserPhotos(String ids, BaseObserver<String> observer);

    void addUserPhotos(String photo, BaseObserver<String> observer);

    void getSkillKinds(String userId, JavaBaseObserver<List<SkillSection>> observer);

    void addQualificationApply(SkillApplyModel model, JavaBaseObserver<Boolean> observer);

    void updateQualificationApply(SkillApplyModel model, JavaBaseObserver<Boolean> observer);

    void checkSkillStatus(int skillId, JavaBaseObserver<Integer> observer);

    void getQualificationApply(int skillId, JavaBaseObserver<SkillApplyModel> observer);

    void getApplyRandomWords(int skillId, JavaBaseObserver<String> observer);

    void getApplyRulesBySkillId(int skillId, JavaBaseObserver<List<String>> observer);

    void skillForbidUnAuth(int forbid, JavaBaseObserver<String> observer);

    void skillFastAnswer(int answer, JavaBaseObserver<String> observer);

    void updateSkillPrice(SkillPriceSet set, JavaBaseObserver<String> observer);

    void getUserSkills(JavaBaseObserver<List<SkillSetting>> observer);

    void getSkillPriceList(int skillId, JavaBaseObserver<List<String>> observer);

    void getOrderSwitch(JavaBaseObserver<MyOrderSwitch> observer);

    void updateOrderSwitch(MyOrderSwitch orderSwitch, JavaBaseObserver<String> observer);

    void getSkillListByUserId(String userId, JavaBaseObserver<List<UserSkillItem>> observer);

    void getOrderSkillList(String userId, JavaBaseObserver<List<OrderSkillSelectItem>> observer);

    void getUserSkillInfo(String userId, int id, JavaBaseObserver<UserSkillInfo> observer);

    void addOrder(AddOrderModel model, JavaBaseObserver<String> observer);

    void verifyOrderTime(VerifyOrderTimeModel model, JavaBaseObserver<String> observer);

    void orderPay(OrderPayModel model, JavaBaseObserver<String> observer);

    void getRecvOrders(int page, JavaBaseObserver<OrdersResp> observer);

    void getSendOrders(int page, JavaBaseObserver<OrdersResp> observer);

    void getLastOrderMsg(String easeName, JavaBaseObserver<LastOrderMsg> observer);

    void getOrderMsg(int page, JavaBaseObserver<OrderMsgResp> observer);

    void bossAcceptService(UpdateOrderModel body, JavaBaseObserver<String> observer);

    void accompanyAcceptService(UpdateOrderModel body, JavaBaseObserver<String> observer);

    void bossConfirmOrder(int orderId, JavaBaseObserver<String> observer);

    void boosRefundOrder(int orderId, JavaBaseObserver<String> observer);

    void agreeRefund(int orderId, JavaBaseObserver<String> observer);

    void disagreeRefund(int orderId, JavaBaseObserver<String> observer);

    void agreeRefuseRefund(int orderId, JavaBaseObserver<String> observer);

    void accompanyService(int orderId, JavaBaseObserver<String> observer);

    void bossAppealing(AppealingModel body, JavaBaseObserver<String> observer);

    void getOrderDetail(int orderId, int orderType, JavaBaseObserver<OrderDetailResp> observer);

    void evaluateAccompany(EvaluateModel model, JavaBaseObserver<String> observer);

    void evaluateBoss(EvaluateModel model, JavaBaseObserver<String> observer);

    void filterMessage(String msg, JavaBaseObserver<String> observer);

    void getOrderEvaluateDetail(int orderId, JavaBaseObserver<OrderDetailResp> observer);

    void logoutReason(String token, String mobile, String reason, String code, BaseObserver<String> observer);

    void getlogoutStatus(String token, String mobile, BaseObserver<LogoutReasonModel> observer);
}
