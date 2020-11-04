package com.qpyy.module.index.api;

import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.http.RetrofitClient;
import com.qpyy.libcommon.http.transform.DefaultTransformer;
import com.qpyy.module.index.bean.AttentionResp;
import com.qpyy.module.index.bean.BannerModel;
import com.qpyy.module.index.bean.CharmRankingResp;
import com.qpyy.module.index.bean.LastWeekStarResp;
import com.qpyy.module.index.bean.ManageRoomResp;
import com.qpyy.module.index.bean.MyFootResp;
import com.qpyy.module.index.bean.RecommendAttentionResp;
import com.qpyy.module.index.bean.RoomModel;
import com.qpyy.module.index.bean.RoomTypeModel;
import com.qpyy.module.index.bean.SearchResp;
import com.qpyy.module.index.bean.WeekStarResp;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.api
 * 创建人 王欧
 * 创建时间 2020/6/29 10:50 AM
 * 描述 describe
 */
public class ApiClient {
    private static final ApiClient ourInstance = new ApiClient();

    private IndexApi api;

    public static ApiClient getInstance() {
        return ourInstance;
    }

    private ApiClient() {
        api = RetrofitClient.getInstance().createApiClient(IndexApi.class);
    }

    public void getCharmList(String roomId, int type, BaseObserver<CharmRankingResp> observer) {
        api.getCharmList(type, roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getWealthList(String roomId, int type, BaseObserver<CharmRankingResp> observer) {
        api.getWealthList(type, roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getWeekStarList(String roomId, int type, BaseObserver<WeekStarResp> observer) {
        api.getWeekStarList(type, roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getWeekStarCharm(String roomId, BaseObserver<WeekStarResp.GiftCharmBean> observer) {
        api.getWeekStarCharm(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getWeekStarRoom(String roomId, BaseObserver<WeekStarResp.GiftRoomBean> observer) {
        api.getWeekStarRoom(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getWeekStarRich(String roomId, BaseObserver<WeekStarResp.GiftRichBean> observer) {
        api.getWeekStarRich(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getLastWeekStarList(String roomId, int type, BaseObserver<LastWeekStarResp> observer) {
        api.getLastWeekStarList(type, roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getRoomCategories(BaseObserver<List<RoomTypeModel>> observer) {
        api.getRoomCategories().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getBanners(BaseObserver<List<BannerModel>> observer) {
        api.getBanners().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getSearch(String keyWord, BaseObserver<SearchResp> observer) {
        api.search(keyWord).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void followUser(String userId, int type, BaseObserver<String> observer) {
        api.follow(userId, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getRoomList(String roomType, BaseObserver<List<RoomModel>> observer) {
        api.getRoomList(roomType).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void attentionList(BaseObserver<List<AttentionResp>> observer) {
        api.attentionList().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void recommendAttentionList(BaseObserver<List<RecommendAttentionResp>> observer) {
        api.recommendAttentionList().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void ghostAttention(String roomIds, BaseObserver<String> observer) {
        api.ghostAttention(roomIds).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void manageLists(BaseObserver<List<ManageRoomResp>> observer) {
        api.manageLists().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void removeManage(String id, BaseObserver<String> observer) {
        api.removeManage(id).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getMyFoot(BaseObserver<List<MyFootResp>> observer,int page) {
        api.getMyFoot(page).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void delfoot(BaseObserver<String> observer) {
        api.delfoot().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getHotRoomList(BaseObserver<List<RoomModel>> observer) {
        api.getHotRoomList().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getRecommendRoomList(int page, BaseObserver<List<RoomModel>> observer) {
        api.getRecommendRoomList(page).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void removeFavorite(String roomId, BaseObserver<String> observer) {
        api.removeFavorite(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

}
