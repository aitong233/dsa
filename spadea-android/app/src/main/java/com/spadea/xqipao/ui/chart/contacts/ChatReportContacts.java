package com.spadea.xqipao.ui.chart.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module_news.bean.ReportType;

import java.io.File;
import java.util.List;

public final class ChatReportContacts {


    public interface View extends IView<Activity> {
        void reportType(List<ReportType> list);

        void reportSuccess();

        void upLoadSuccess(String url, int type);
    }

    public interface IChatReportPre extends IPresenter {
        void getReportType();

        void reportUser(String picture, String user_id, String remark, String type);

        void uploadFile(File file, int type);
    }
}