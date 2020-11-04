package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.oss.OSSOperUtils;
import com.spadea.yuyin.util.utilcode.FileUtils;
import com.spadea.yuyin.util.utilcode.TimeUtils;
import com.spadea.xqipao.data.ApplyImageItem;
import com.spadea.xqipao.data.SkillApplyModel;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.ui.me.contacter.QualificationContacts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class QualificationPresenter extends BasePresenter<QualificationContacts.View> implements QualificationContacts.IQualificationPre {
    public QualificationPresenter(QualificationContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void uploadImage(List<LocalMedia> mediaList, int position) {
        LocalMedia localMedia1 = mediaList.get(0);
        String filePath = "";
        if (localMedia1.isCompressed()) {
            filePath = localMedia1.getCompressPath();
        } else {
            filePath = localMedia1.getPath();
        }
        String finalLocalPath = filePath;
        File file = new File(filePath);
        String url = "android_images/" + MyApplication.getInstance().getUser().getUser_id() + "/" + TimeUtils.getNowString(new SimpleDateFormat("yyyyMMddHHmmss")) + "_" + FileUtils.getFileName(file);
        MvpRef.get().showLoadings();
        OSSOperUtils.newInstance().putObjectMethod(url, filePath, new OSSProgressCallback() {
            @Override
            public void onProgress(Object request, long currentSize, long totalSize) {

            }
        }, new OSSCompletedCallback() {
            @Override
            public void onSuccess(OSSRequest request, OSSResult result) {
                String headPicUrl = OSSOperUtils.AliYunOSSURLFile + url;
                int mimeType = 0;
                if (localMedia1.getPictureType() != null && localMedia1.getPictureType().startsWith("video")) {
                    mimeType = PictureConfig.TYPE_VIDEO;
                }
                MvpRef.get().disLoadings();
                MvpRef.get().uploadImageSuccess(new ApplyImageItem(headPicUrl, finalLocalPath), position, mimeType);
            }

            @Override
            public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
                LogUtils.e("上传数据失败", request);
                clientException.printStackTrace();
                serviceException.printStackTrace();
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void uploadVoice(String filePath) {
        File file = new File(filePath);
        String url = "android_audio/" + MyApplication.getInstance().getUser().getUser_id() + "/" + TimeUtils.getNowString(new SimpleDateFormat("yyyyMMddHHmmss")) + "_" + FileUtils.getFileName(file);
        MvpRef.get().showLoadings();
        OSSOperUtils.newInstance().putObjectMethod(url, filePath, new OSSProgressCallback() {
            @Override
            public void onProgress(Object request, long currentSize, long totalSize) {

            }
        }, new OSSCompletedCallback() {
            @Override
            public void onSuccess(OSSRequest request, OSSResult result) {
                String voiceUrl = OSSOperUtils.AliYunOSSURLFile + url;
                MvpRef.get().disLoadings();
                MvpRef.get().uploadVoiceSuccess(voiceUrl);
            }

            @Override
            public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
                LogUtils.e("上传数据失败", request);
                clientException.printStackTrace();
                serviceException.printStackTrace();
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void addApply(SkillApplyModel model) {
        MvpRef.get().showLoadings();
        api.addQualificationApply(model, new JavaBaseObserver<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(Boolean model) {
                MvpRef.get().addApplySuccess(model);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void updateApply(SkillApplyModel model) {
        model.setStatus(2);//更新时传审核中
        MvpRef.get().showLoadings();
        api.updateQualificationApply(model, new JavaBaseObserver<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(Boolean model) {
                MvpRef.get().addApplySuccess(model);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getRandomWords(int skillId) {
        api.getApplyRandomWords(skillId, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().randomWords(s);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getRules(int skillId) {
        api.getApplyRulesBySkillId(skillId, new JavaBaseObserver<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<String> strings) {
                MvpRef.get().rules(strings);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}