package com.spadea.xqipao.ui.live.contacts;


import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.MusicModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class MusicContacts {

    public interface View extends IView<FragmentActivity> {
        void setMusicNum(String text);

        void setNewData(List<MusicModel> list);

        void addData(List<MusicModel> list);
    }

    public interface IMusicPre extends IPresenter {

        void getLocalMusic(Context context);

        void searchLocalMusic(String keyWord);


        void searchNetMusicRefresh(String keyWord);

        void searchNetMusicLomer();


        void downMp3(MusicModel musicModel);
    }


}
