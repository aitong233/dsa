package com.qpyy.module_news.api;

import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.http.RetrofitClient;
import com.qpyy.libcommon.http.transform.DefaultTransformer;
import com.qpyy.module_news.bean.EmChatUserInfo;
import com.qpyy.module_news.bean.GiftNumBean;
import com.qpyy.module_news.bean.NewsListBean;
import com.qpyy.module_news.bean.ReportType;

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

    private NewsApi api;

    public static ApiClient getInstance() {
        return ourInstance;
    }

    private ApiClient() {
        api = RetrofitClient.getInstance().createApiClient(NewsApi.class);
    }


    public void reportType(BaseObserver<List<ReportType>> observer) {
        api.reportType().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void addUserBlack(String blackId, int type, BaseObserver<String> observer) {
        api.removeBlackUser(blackId, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void getInfoByEmChat(String emChatUserName, BaseObserver<EmChatUserInfo> observer) {
        api.getInfoByEmChat(emChatUserName).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void reportUser(String picture, String user_id, String remark, String type, BaseObserver<String> observer) {
        api.reportUser(picture, user_id, remark, type).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void giftWall(BaseObserver<List<GiftModel>> observer) {
        api.giftWall().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void giveGift(String gift_id, String user_id, String number, BaseObserver<String> observer) {
        api.giveGift(gift_id, user_id, number).compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void systemNewsList(int page, BaseObserver<List<NewsListBean>> observer) {
        api.systemNewsList(page).compose(new DefaultTransformer<>()).subscribe(observer);

    }

    public void serviceUser(BaseObserver<String> observer) {
        api.serviceUser().compose(new DefaultTransformer<>()).subscribe(observer);
    }

    public void giftNumberSet(String roomId, BaseObserver<List<GiftNumBean>> observer) {
        api.giftNumberSet(roomId).compose(new DefaultTransformer<>()).subscribe(observer);
    }

}
