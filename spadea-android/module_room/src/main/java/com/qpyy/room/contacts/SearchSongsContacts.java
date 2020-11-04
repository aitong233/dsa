package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.MusicResp;

import java.util.List;

public final class SearchSongsContacts {

    public interface View extends IView<Activity> {
        void setSearchMusicData(List<MusicResp> data);

        void onComplete();

        void downloadSuccess(MusicResp musicResp);

        void downloadFailure();
    }

    public interface SearchSongsIpre extends IPresenter {
        void searchMusic(String musicName, int page);

        void download(MusicResp musicResp);
    }
}
