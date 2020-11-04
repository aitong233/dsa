package com.qpyy.module.index.api;

import com.qpyy.libcommon.constant.URLConstants;
import com.qpyy.libcommon.http.BaseModel;
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

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.api
 * 创建人 王欧
 * 创建时间 2020/6/29 10:45 AM
 * 描述 describe
 */
public interface IndexApi {
    @POST(URLConstants.CHARM_LIST)
    @FormUrlEncoded
    Observable<BaseModel<CharmRankingResp>> getCharmList(@Field("type") int type, @Field("room_id") String roomId);

    @POST(URLConstants.WEALTH_LIST)
    @FormUrlEncoded
    Observable<BaseModel<CharmRankingResp>> getWealthList(@Field("type") int type, @Field("room_id") String roomId);

    @POST(URLConstants.WEEK_STAR)
    @FormUrlEncoded
    Observable<BaseModel<WeekStarResp>> getWeekStarList(@Field("type") int type, @Field("room_id") String roomId);

    @POST(URLConstants.WEEK_STAR_ROOM)
    @FormUrlEncoded
    Observable<BaseModel<WeekStarResp.GiftRoomBean>> getWeekStarRoom(@Field("room_id") String roomId);

    @POST(URLConstants.WEEK_STAR_RICH)
    @FormUrlEncoded
    Observable<BaseModel<WeekStarResp.GiftRichBean>> getWeekStarRich(@Field("room_id") String roomId);

    @POST(URLConstants.WEEK_STAR_CHARM)
    @FormUrlEncoded
    Observable<BaseModel<WeekStarResp.GiftCharmBean>> getWeekStarCharm(@Field("room_id") String roomId);

    @POST(URLConstants.LAST_WEEK_STAR)
    @FormUrlEncoded
    Observable<BaseModel<LastWeekStarResp>> getLastWeekStarList(@Field("type") int type, @Field("room_id") String roomId);

    @FormUrlEncoded
    @POST(URLConstants.SEARCH)
    Observable<BaseModel<SearchResp>> search(@Field("keyword") String keywordc);

    @POST(URLConstants.ROOM_CATEGORY)
    Observable<BaseModel<List<RoomTypeModel>>> getRoomCategories();

    @POST(URLConstants.INDEX_BANNERS)
    Observable<BaseModel<List<BannerModel>>> getBanners();

    @FormUrlEncoded
    @POST(URLConstants.FOLLOW)
    Observable<BaseModel<String>> follow(@Field("user_id") String userId, @Field("type") int type);

    @FormUrlEncoded
    @POST(URLConstants.ROOM_LIST)
    Observable<BaseModel<List<RoomModel>>> getRoomList(@Field("type_id") String typeId);

    @POST(URLConstants.ATTENTION_LIST)
    Observable<BaseModel<List<AttentionResp>>> attentionList();

    @POST(URLConstants.RECOMMEND_ATTENTION_LIST)
    Observable<BaseModel<List<RecommendAttentionResp>>> recommendAttentionList();

    @FormUrlEncoded
    @POST(URLConstants.GHOST_ATTENTION)
    Observable<BaseModel<String>> ghostAttention(@Field("room_ids") String roomIds);

    @POST(URLConstants.MANAGE_LISTS)
    Observable<BaseModel<List<ManageRoomResp>>> manageLists();

    @FormUrlEncoded
    @POST(URLConstants.REMOVE_MANAGE)
    Observable<BaseModel<String>> removeManage(@Field("id") String id);

    @FormUrlEncoded
    @POST(URLConstants.MYFOOT)
    Observable<BaseModel<List<MyFootResp>>> getMyFoot(@Field("p") int page);

    @POST(URLConstants.DELFOOT)
    Observable<BaseModel<String>> delfoot();

    @POST(URLConstants.HOT_ROOM_LIST)
    Observable<BaseModel<List<RoomModel>>> getHotRoomList();

    @FormUrlEncoded
    @POST(URLConstants.RECOMMEND_ROOM_LIST)
    Observable<BaseModel<List<RoomModel>>> getRecommendRoomList(@Field("p") int page);

    @FormUrlEncoded
    @POST(URLConstants.REMOVE_FAVORITE)
    Observable<BaseModel<String>> removeFavorite(@Field("room_id") String roomId);
}
