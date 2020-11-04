package com.yutang.game.turntable.net;

import com.qpyy.libcommon.bean.TurntableLuckyRank;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.http.RetrofitClient;
import com.qpyy.libcommon.http.transform.DefaultTransformer;
import com.qpyy.libcommon.utils.SpUtils;
import com.yutang.game.turntable.bean.GameHelpModel;
import com.yutang.game.turntable.bean.GameInfoModel;
import com.yutang.game.turntable.bean.GameLogModel;
import com.yutang.game.turntable.bean.GamePoolModel;
import com.yutang.game.turntable.bean.LuckyModel;
import com.yutang.game.turntable.bean.SmashModel;

import java.util.ArrayList;
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

    private TurntableApi api;

    public static ApiClient getInstance() {
        return ourInstance;
    }

    private ApiClient() {
        api = RetrofitClient.getInstance().createApiClient(TurntableApi.class);
    }

    public void getGameHelp(BaseObserver<GameHelpModel> observer) {
        api.getGameHelp(SpUtils.getToken()).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getGameLog(BaseObserver<List<GameLogModel>> observer) {
        api.getGameLog(SpUtils.getToken()).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getGamePool(BaseObserver<ArrayList<GamePoolModel>> observer) {
        api.getGamePool(SpUtils.getToken()).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getLuckyRank(BaseObserver<ArrayList<TurntableLuckyRank>> observer) {
        api.getLuckyRank(SpUtils.getToken()).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void smash(int number, BaseObserver<ArrayList<SmashModel>> observer) {
        api.smash(SpUtils.getToken(), number).compose(new DefaultTransformer<>()).subscribe(observer);
    }
    public void getGameInfo( BaseObserver<GameInfoModel> observer) {
        api.getGameInfo(SpUtils.getToken()).compose(new DefaultTransformer<>()).subscribe(observer);
    }

}
