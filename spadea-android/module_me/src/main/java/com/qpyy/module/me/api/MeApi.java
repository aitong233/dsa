package com.qpyy.module.me.api;

import com.qpyy.libcommon.bean.CheckImageResp;
import com.qpyy.libcommon.constant.URLConstants;
import com.qpyy.libcommon.http.BaseModel;
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

import java.net.URL;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.api
 * 创建人 王欧
 * 创建时间 2020/6/29 10:45 AM
 * 描述 describe
 */
public interface MeApi {
    @FormUrlEncoded
    @POST(URLConstants.FRIEND_LIST)
    Observable<BaseModel<List<FriendBean>>> friendList(@Field("p") int p);

    @FormUrlEncoded
    @POST(URLConstants.FOLLOW_LIST)
    Observable<BaseModel<List<FriendBean>>> followList(@Field("p") int p);

    @FormUrlEncoded
    @POST(URLConstants.FANS_LIST)
    Observable<BaseModel<List<FriendBean>>> fansList(@Field("p") int p);

    @FormUrlEncoded
    @POST(URLConstants.SEARCH_FRIEND)
    Observable<BaseModel<SearchFriendResp>> searchFriend(@Field("keyword") String keyword);

    @FormUrlEncoded
    @POST(URLConstants.SEARCH_FANS)
    Observable<BaseModel<SearchFriendResp>> searchFans(@Field("keyword") String keyword);

    @FormUrlEncoded
    @POST(URLConstants.SEARCH_FOLLOW)
    Observable<BaseModel<SearchFriendResp>> searchFollow(@Field("keyword") String keyword);

    @POST(URLConstants.MY_INFO)
    Observable<BaseModel<MyInfoResp>> getMyInfo();

    @FormUrlEncoded
    @POST(URLConstants.USER_UPDATE)
    Observable<BaseModel<String>> userUpdate(@FieldMap Map<String, String> map);

    @POST(URLConstants.PROFESSION)
    Observable<BaseModel<List<String>>> getProfession();


    @POST(URLConstants.REGION_LIST)
    Observable<BaseModel<List<RegionListResp>>> regionList();

    @FormUrlEncoded
    @POST(URLConstants.USER_HOME_PAGE)
    Observable<BaseModel<UserHomeResp>> userHomePage(@Field("user_id") String userId, @Field("emchat_username") String emchatUsername);

    @POST(URLConstants.SERVICEUSER)
    Observable<BaseModel<String>> serviceUser();

    @FormUrlEncoded
    @POST(URLConstants.ADD_USER_ROOM)
    Observable<BaseModel<String>> addUserRoom(@Field("room_name") String roomName, @Field("label_id") String labelId);

    @FormUrlEncoded
    @POST(URLConstants.GIFTWALL)
    Observable<BaseModel<List<GiftBean>>> giftWall(@Field("user_id") String userId);

    @FormUrlEncoded
    @POST(URLConstants.USER_ROOM)
    Observable<BaseModel<UserRoomResp>> userRoom(@Field("user_id") String userId);

    @FormUrlEncoded
    @POST(URLConstants.PHOTOWALL)
    Observable<BaseModel<PhotoWallResp>> photoWall(@Field("user_id") String userId, @Field("p") int p);

    @FormUrlEncoded
    @POST(URLConstants.DELETEPHOTO)
    Observable<BaseModel<String>> deletePhoto(@Field("id") String userId);

    @FormUrlEncoded
    @POST(URLConstants.ADDPHOTO)
    Observable<BaseModel<String>> addPhoto(@Field("photo") String photo);

    @FormUrlEncoded
    @POST(URLConstants.FOLLOW)
    Observable<BaseModel<String>> follow(@Field("user_id") String userId, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.ADD_BLACK_USER)
    Observable<BaseModel<String>> addBlackUser(@Field("black_id") String blackId, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.CHECKIMAGE)
    Observable<BaseModel<CheckImageResp>> checkImage(@Field("image") String image);

    @POST(URLConstants.NAME_AUTH_RESULT)
    Observable<BaseModel<NameAuthResult>> getNameAuthResult();

    @FormUrlEncoded
    @POST(URLConstants.COMEUSER)
    Observable<BaseModel<List<ComeUserResp>>> userVisit(@Field("p") int page);

    @POST(URLConstants.UNION)
    Observable<BaseModel<GuildStateResp>> union();

    @FormUrlEncoded
    @POST(URLConstants.USER_UPDATE)
    Observable<BaseModel<String>> updateAvatar(@Field("head_picture") String headPicture);

    @POST(URLConstants.VERIFY_USER_SEX)
    Observable<BaseModel<VerifySexResp>> verifyUserSex();

    @POST(URLConstants.USER_FILL)
    @FormUrlEncoded
    Observable<BaseModel<UserFillResp>> userFill(@Field("user_no") String user_no, @Field("nickname") String nickname, @Field("sex") String sex);

    @POST(URLConstants.MY_GUILD_INFO)
    @FormUrlEncoded
    Observable<BaseModel<String>> myGuildInfo(@Field("user_id") String useId);

    @POST(URLConstants.SEARCH_GUILD)
    @FormUrlEncoded
    Observable<BaseModel<List<String>>> searchGuild(@Field("sociaty_no") String sociaty_no);

    @POST(URLConstants.JOIN_GUILD)
    @FormUrlEncoded
    Observable<BaseModel<String>> joinGuild(@Field("user_id") String useId, @Field("sociaty_no") String sociaty_no);

    @POST(URLConstants.MY_GUILD_INFO)
    @FormUrlEncoded
    Observable<BaseModel<String>> exitGuild(@Field("user_id") String useId);

    @FormUrlEncoded
    @POST(URLConstants.GUILD_SEARCH)
    Observable<BaseModel<GuildResp>> guildSearch(@Field("guild_no") String id);

    @FormUrlEncoded
    @POST(URLConstants.GUILD_JOIN)
    Observable<BaseModel<String>> guildJoin(@Field("id") String id);


    @POST(URLConstants.GUILD_INFO)
    Observable<BaseModel<GuildResp>> guildInfo();

    @POST(URLConstants.GUILD_QUIT)
    Observable<BaseModel<String>> guildQuit();
}
