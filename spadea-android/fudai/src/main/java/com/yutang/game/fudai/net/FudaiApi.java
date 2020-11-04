package com.yutang.game.fudai.net;

import com.qpyy.libcommon.constant.URLConstants;
import com.qpyy.libcommon.http.BaseModel;
import com.yutang.game.fudai.bean.CatFishingModel;
import com.yutang.game.fudai.bean.CatHelpModel;
import com.yutang.game.fudai.bean.FishInfoBean;
import com.yutang.game.fudai.bean.GameLog;
import com.yutang.game.fudai.bean.LuckGiftBean;
import com.qpyy.libcommon.bean.LuckyPackLuckyRankItemBean;
import com.yutang.game.fudai.bean.WinJackpotModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.api
 * 创建人 易鹏超
 * 创建时间 2020/9/3 2:56 AM
 * 描述 describe
 */
public interface FudaiApi {

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

    @GET(URLConstants.GET_GAME_LOG)
    Observable<BaseModel<List<GameLog>>> getGameLog();

    @GET(URLConstants.WINRANKING)
    Observable<BaseModel<List<CatFishingModel>>> getWinRanking(@Query("rank_type") int type, @Query("token") String token);

    @GET(URLConstants.LUCKY_RANK_1)
    Observable<BaseModel<List<LuckyPackLuckyRankItemBean>>> getLuckyRank(@Query("token") String token, @Query("type") int type);

}
