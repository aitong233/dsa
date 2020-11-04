package com.spadea.xqipao.ui.chart.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.oss.OSSOperUtils;
import com.qpyy.module_news.api.ApiClient;
import com.qpyy.module_news.bean.ReportType;
import com.spadea.xqipao.ui.chart.contacts.ChatReportContacts;

import java.io.File;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class ChatReportPresenter extends BasePresenter<ChatReportContacts.View> implements ChatReportContacts.IChatReportPre {
    public ChatReportPresenter(ChatReportContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getReportType() {
        ApiClient.getInstance().reportType(new BaseObserver<List<ReportType>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<ReportType> list) {
                MvpRef.get().reportType(list);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void reportUser(String picture, String user_id, String remark, String type) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().reportUser(picture, user_id, remark, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().reportSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void uploadFile(File file, int type) {
        MvpRef.get().showLoadings();
        String url = OSSOperUtils.getPath(file, type);
        OSSOperUtils.newInstance().putObjectMethod(url, file.getPath(), new OSSOperUtils.OssCallback() {
            @Override
            public void onSuccess() {
                MvpRef.get().disLoadings();
                MvpRef.get().upLoadSuccess(OSSOperUtils.AliYunOSSURLFile + url, type);
            }

            @Override
            public void onFail() {
                MvpRef.get().disLoadings();
            }
        });
    }
}