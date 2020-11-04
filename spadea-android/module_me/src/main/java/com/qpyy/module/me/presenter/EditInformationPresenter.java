package com.qpyy.module.me.presenter;

import android.content.Context;

import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.oss.OSSOperUtils;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.ProfessionBean;
import com.qpyy.module.me.bean.RegionListResp;
import com.qpyy.module.me.bean.UserHomeResp;
import com.qpyy.module.me.bean.VerifySexResp;
import com.qpyy.module.me.contacts.EditInformationContacts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class EditInformationPresenter extends BasePresenter<EditInformationContacts.View> implements EditInformationContacts.IEditInformationPre {
    public EditInformationPresenter(EditInformationContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void upDateUserInfo(Map<String, String> map) {
        ApiClient.getInstance().userUpdate(map, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().updateSuccess();
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void uploadFile(File file, int type) {
        MvpRef.get().showLoadings("上传中...");
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

    @Override
    public void getProfession() {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().getProfession(new BaseObserver<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<String> strings) {
                List<ProfessionBean> data = new ArrayList<>();
                for (String item : strings) {
                    data.add(new ProfessionBean(item));
                }
                MvpRef.get().setProfession(data);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getRegionList() {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().regionList(new BaseObserver<List<RegionListResp>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RegionListResp> regionListResps) {
                MvpRef.get().setRegionList(regionListResps);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getUserHomePage(String userId) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().userHomePage(userId, null, new BaseObserver<UserHomeResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(UserHomeResp userHomeResp) {
                MvpRef.get().setUserHomePage(userHomeResp);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }


    @Override
    public void updateAvatar(String headPicture) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().updateAvatar(headPicture, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().updateAvatarSuccess(headPicture);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void verifyUserSex() {
        ApiClient.getInstance().verifyUserSex(new BaseObserver<VerifySexResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(VerifySexResp s) {
                if (s.getStatus() == 1) {
                    MvpRef.get().showSelectSexDialog();
                } else {
                    ToastUtils.show("无法修改");
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
