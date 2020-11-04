package com.qpyy.module_news.api;

import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.libcommon.constant.URLConstants;
import com.qpyy.libcommon.http.BaseModel;
import com.qpyy.module_news.bean.EmChatUserInfo;
import com.qpyy.module_news.bean.GiftNumBean;
import com.qpyy.module_news.bean.NewsListBean;
import com.qpyy.module_news.bean.ReportType;

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
public interface NewsApi {
    @POST(URLConstants.REPORT_TYPE)
    Observable<BaseModel<List<ReportType>>> reportType();

    @FormUrlEncoded
    @POST(URLConstants.ADD_BLACK_USER)
    Observable<BaseModel<String>> removeBlackUser(@Field("black_id") String blackId, @Field("type") int type); //1为添加，2位移除

    @FormUrlEncoded
    @POST(URLConstants.GET_INFO_BY_EM_CHAT)
    Observable<BaseModel<EmChatUserInfo>> getInfoByEmChat(@Field("emchat_username") String userName);

    @FormUrlEncoded
    @POST(URLConstants.CHAT_USER_REPORT)
    Observable<BaseModel<String>> reportUser(@Field("picture") String picture, @Field("user_id") String user_id, @Field("remark") String remark, @Field("type") String type);

    @POST(URLConstants.GIFT_WALL)
    Observable<BaseModel<List<GiftModel>>> giftWall();

    @FormUrlEncoded
    @POST(URLConstants.GIVE_CHAT_GIFT)
    Observable<BaseModel<String>> giveGift(@Field("gift_id") String gift_id, @Field("user_id") String user_id, @Field("number") String number);

    @FormUrlEncoded
    @POST(URLConstants.SYSTEM_NEWS_LIST)
    Observable<BaseModel<List<NewsListBean>>> systemNewsList(@Field("p") int page);

    @POST(URLConstants.SERVICEUSER)
    Observable<BaseModel<String>> serviceUser();

    @FormUrlEncoded
    @POST(URLConstants.GIFT_NUMBER_SET)
    Observable<BaseModel<List<GiftNumBean>>> giftNumberSet(@Field("room_id") String roomId);
}
