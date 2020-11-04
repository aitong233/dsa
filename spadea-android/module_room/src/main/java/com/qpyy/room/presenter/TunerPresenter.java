package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.MixerResp;
import com.qpyy.room.contacts.TunerContacts;
import com.qpyy.room.event.RoomMixEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class TunerPresenter extends BasePresenter<TunerContacts.View> implements TunerContacts.TunerIpre {


    private String baseImgUrl = "https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/images/mixer/%s.png";

    public TunerPresenter(TunerContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void mixer() {
        ApiClient.getInstance().mixer(new BaseObserver<List<MixerResp>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<MixerResp> mixerResps) {
                for (MixerResp item : mixerResps) {
                    item.setImgUrtl(String.format(baseImgUrl, item.getId()));
                }
                MvpRef.get().setMixerData(mixerResps);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setUserMixer(String roomId, int id) {
        ApiClient.getInstance().setUserMixer(roomId, id, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                EventBus.getDefault().post(new RoomMixEvent(id));
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
