package com.qpyy.module.me.api;

import com.qpyy.libcommon.bean.CheckImageResp;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.http.RetrofitClient;
import com.qpyy.libcommon.http.transform.DefaultTransformer;
import com.qpyy.module.me.bean.ComeUserResp;
import com.qpyy.module.me.bean.FriendBean;
import com.qpyy.module.me.bean.GiftBean;
import com.qpyy.module.me.bean.GuildResp;
import com.qpyy.module.me.bean.GuildStateResp;
import com.qpyy.module.me.bean.MyInfoResp;
import com.qpyy.module.me.bean.NameAuthResult;
import com.qpyy.module.me.bean.PhotoWallResp;
import com.qpyy.module.me.bean.RegionListResp;
import com.qpyy.module.me.bean.SearchFriendResp;
import com.qpyy.module.me.bean.UserFillResp;
import com.qpyy.module.me.bean.UserHomeResp;
import com.qpyy.module.me.bean.UserRoomResp;
import com.qpyy.module.me.bean.VerifySexResp;

import java.util.List;
import java.util.Map;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.api
 * 创建人 王欧
 * 创建时间 2020/6/29 10:50 AM
 * 描述 describe
 */
public class ApiClient {
    private static final ApiClient ourInstance = new ApiClient();

    private MeApi api;

    public static ApiClient getInstance() {
        return ourInstance;
    }

    private ApiClient() {
        api = RetrofitClient.getInstance().createApiClient(MeApi.class);
    }


    public void getFansList(int page, BaseObserver<List<FriendBean>> observer) {
        api.fansList(page).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getFriendList(int page, BaseObserver<List<FriendBean>> observer) {
        api.friendList(page).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getFollowList(int page, BaseObserver<List<FriendBean>> observer) {
        api.followList(page).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void searchFriend(String keyword, BaseObserver<SearchFriendResp> observer) {
        api.searchFriend(keyword).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void searchFans(String keyword, BaseObserver<SearchFriendResp> observer) {
        api.searchFans(keyword).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void searchFollow(String keyword, BaseObserver<SearchFriendResp> observer) {
        api.searchFollow(keyword).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getMyInfo(BaseObserver<MyInfoResp> observer) {
        api.getMyInfo().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void userUpdate(Map<String, String> map, BaseObserver<String> observer) {
        api.userUpdate(map).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getProfession(BaseObserver<List<String>> observer) {
        api.getProfession().compose(new DefaultTransformer<>()).subscribe(observer);
    }


    public void regionList(BaseObserver<List<RegionListResp>> observer) {
        api.regionList().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void userHomePage(String userId, String emchatUsername, BaseObserver<UserHomeResp> observer) {
        api.userHomePage(userId, emchatUsername).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void serviceUser(BaseObserver<String> observer) {
        api.serviceUser().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void addUserRoom(String roomName, String labelId, BaseObserver<String> observer) {
        api.addUserRoom(roomName, labelId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void giftWall(String userId, BaseObserver<List<GiftBean>> observer) {
        api.giftWall(userId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void userRoom(String userId, BaseObserver<UserRoomResp> observer) {
        api.userRoom(userId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void photoWall(String userId, int p, BaseObserver<PhotoWallResp> observer) {
        api.photoWall(userId, p).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void deletePhoto(String id, BaseObserver<String> observer) {
        api.deletePhoto(id).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void addPhoto(String photo, BaseObserver<String> observer) {
        api.addPhoto(photo).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void follow(String userId, int type, BaseObserver<String> observer) {
        api.follow(userId, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void addBlackUser(String blackId, int type, BaseObserver<String> observer) {
        api.addBlackUser(blackId, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void checkImage(String url, BaseObserver<CheckImageResp> observer) {
        api.checkImage(url).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getNameAuthResult(BaseObserver<NameAuthResult> observer) {
        api.getNameAuthResult().compose(new DefaultTransformer<>()).subscribe(observer);
    }


    public void userVisit(int page, BaseObserver<List<ComeUserResp>> observer) {
        api.userVisit(page).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void union(BaseObserver<GuildStateResp> observer) {
        api.union().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void updateAvatar(String headPicture, BaseObserver<String> observer) {
        api.updateAvatar(headPicture).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void verifyUserSex(BaseObserver<VerifySexResp> observer) {
        api.verifyUserSex().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void userFill(String user_no, String nickname, String sex, BaseObserver<UserFillResp> observer) {
        api.userFill(user_no, nickname, sex).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void myGuildInfo(String userId, BaseObserver<String> observer) {
        api.myGuildInfo(userId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void searchGuild(String sociaty_no, BaseObserver<List<String>> observer) {
        api.searchGuild(sociaty_no).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void joinGuild(String userId, String sociaty_no, BaseObserver<String> observer) {
        api.joinGuild(userId, sociaty_no).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void exitGuild(String userId, BaseObserver<String> observer) {
        api.exitGuild(userId).compose(new DefaultTransformer<>()).subscribe(observer);
    }


    public void guildSearch(String id, BaseObserver<GuildResp> observer) {
        api.guildSearch(id).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void guildJoin(String id, BaseObserver<String> observer) {
        api.guildJoin(id).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void guildInfo(BaseObserver<GuildResp> observer) {
        api.guildInfo().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void guildQuit(BaseObserver<String> observer) {
        api.guildQuit().compose(new DefaultTransformer<>()).subscribe(observer);
    }
}
