package com.yutang.game.turntable.net;

import com.qpyy.libcommon.bean.TurntableLuckyRank;
import com.qpyy.libcommon.http.BaseModel;
import com.yutang.game.turntable.bean.GameHelpModel;
import com.yutang.game.turntable.bean.GameInfoModel;
import com.yutang.game.turntable.bean.GameLogModel;
import com.yutang.game.turntable.bean.GamePoolModel;
import com.yutang.game.turntable.bean.LuckyModel;
import com.yutang.game.turntable.bean.SmashModel;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TurntableApi {
    @FormUrlEncoded
    @POST(TurntableURL.GAME_HELP)
    Observable<BaseModel<GameHelpModel>> getGameHelp(@Field("token") String token);

    @FormUrlEncoded
    @POST(TurntableURL.GAME_POOL)
    Observable<BaseModel<ArrayList<GamePoolModel>>> getGamePool(@Field("token") String token);

    @FormUrlEncoded
    @POST(TurntableURL.GAME_LOG)
    Observable<BaseModel<List<GameLogModel>>> getGameLog(@Field("token") String token);

    @FormUrlEncoded
    @POST(TurntableURL.SMASH)
    Observable<BaseModel<ArrayList<SmashModel>>> smash(@Field("token") String token, @Field("number") int number);

    @FormUrlEncoded
    @POST(TurntableURL.LUCKY_RANK)
    Observable<BaseModel<ArrayList<TurntableLuckyRank>>> getLuckyRank(@Field("token") String token);

    @FormUrlEncoded
    @POST(TurntableURL.GAME_INFO)
    Observable<BaseModel<GameInfoModel>> getGameInfo(@Field("token") String token);
}
