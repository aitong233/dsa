package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.MixerResp;

import java.util.List;

public final class TunerContacts {


    public interface View extends IView<Activity> {

        void setMixerData(List<MixerResp> mixerResps);
    }

    public interface TunerIpre extends IPresenter {
        void mixer();

        void setUserMixer(String roomId, int id);
    }

}
