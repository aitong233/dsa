package com.yutang.game.fudai.net;

import com.qpyy.libcommon.http.BaseModel;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.http.RetrofitClient;
import com.qpyy.libcommon.http.transform.DefaultTransformer;
import com.qpyy.libcommon.utils.SpUtils;
import com.yutang.game.fudai.bean.CatFishingModel;
import com.yutang.game.fudai.bean.CatHelpModel;
import com.yutang.game.fudai.bean.FishInfoBean;
import com.yutang.game.fudai.bean.GameLog;
import com.yutang.game.fudai.bean.LuckGiftBean;
import com.qpyy.libcommon.bean.LuckyPackLuckyRankItemBean;
import com.yutang.game.fudai.bean.WinJackpotModel;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.api
 * 创建人 易鹏超
 * 创建时间 2020/9/3 10:50 AM
 * 描述 describe
 */
public class ApiClient {
    private static final ApiClient ourInstance = new ApiClient();

    private FudaiApi api;

    public static ApiClient getInstance() {
        return ourInstance;
    }

    private ApiClient() {
        api = RetrofitClient.getInstance().createApiClient(FudaiApi.class);
    }

    public void getFishInfo(String type, BaseObserver<FishInfoBean> observer) {
        api.getFishInfo(SpUtils.getToken(), type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void startFishing(String token, int number, int type, BaseObserver<LuckGiftBean> observer) {
        api.startFishing(token, number, type).compose(new DefaultTransformer<BaseModel<LuckGiftBean>, LuckGiftBean>())
                .subscribe(observer);
    }

    public void getCatHelp(String token, BaseObserver<CatHelpModel> observer) {
        api.getCatHelp(token).compose(new DefaultTransformer<BaseModel<CatHelpModel>, CatHelpModel>())
                .subscribe(observer);
    }

    public void getCatWinJackpot(String token, String type, BaseObserver<List<WinJackpotModel>> observer) {
        api.getCatWinJackpot(token, type).compose(new DefaultTransformer<BaseModel<List<WinJackpotModel>>, List<WinJackpotModel>>())
                .subscribe(observer);
    }

    public void gameLog(BaseObserver<List<GameLog>> observer) {
        api.getGameLog().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getWinRanking(int type, String token, BaseObserver<List<CatFishingModel>> observer) {
        api.getWinRanking(type, token).compose(new DefaultTransformer<>())
                .subscribe(observer);
    }

    public void getLuckyList(String token,int type, BaseObserver<List<LuckyPackLuckyRankItemBean>> observer) {
        api.getLuckyRank(token,type).compose(new DefaultTransformer<>())
                .subscribe(observer);
    }


}
