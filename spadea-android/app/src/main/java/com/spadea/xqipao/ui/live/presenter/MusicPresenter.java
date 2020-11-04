package com.spadea.xqipao.ui.live.presenter;


import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.spadea.yuyin.util.utilcode.EncryptUtils;
import com.spadea.yuyin.widget.MyProgressDialog;
import com.spadea.xqipao.data.MusicModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.data.even.MusicDownEvent;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.music.MusicManagementUtil;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.live.contacts.MusicContacts;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;


public class MusicPresenter extends BasePresenter<MusicContacts.View> implements MusicContacts.IMusicPre {


    private List<MusicModel> allMusicFile;
    private int page = 1;
    private String keyWord = "";

    public MusicPresenter(MusicContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getLocalMusic(Context context) {
        allMusicFile = MusicManagementUtil.getAllMusicFile(context);
        getView().setMusicNum("本地歌曲" + allMusicFile.size());
        getView().setNewData(allMusicFile);
    }


    @Override
    public void searchLocalMusic(String keyWord) {
        if (allMusicFile == null || allMusicFile.size() == 0) {
            return;
        }
        if (TextUtils.isEmpty(keyWord)) {
            getView().setNewData(allMusicFile);
            return;
        }
        List<MusicModel> list = new ArrayList<>();
        for (MusicModel item : allMusicFile) {
            if ((item.getTitle() != null && item.getTitle().contains(keyWord)) || (item.getAuthor() != null && item.getAuthor().contains(keyWord))) {
                list.add(item);
            }
        }
        getView().setNewData(list);
    }


    private void searchNetMusic() {
        api.searchMusic(keyWord, "name", "netease", page, new BaseObserver<List<MusicModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<MusicModel> musicModels) {
                if (musicModels != null && musicModels.size() != 0) {
                    if (page == 1) {
                        getView().setNewData(musicModels);
                    } else {
                        getView().addData(musicModels);
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void searchNetMusicRefresh(String keyWord) {
        page = 1;
        this.keyWord = keyWord;
        searchNetMusic();
    }

    @Override
    public void searchNetMusicLomer() {
        page++;
        searchNetMusic();
    }

    @Override
    public void downMp3(MusicModel musicModel) {
        MyProgressDialog myProgressDialog = new MyProgressDialog(mContext);
        myProgressDialog.show();
        String dataPath = Environment.getExternalStorageDirectory() + "/YuTang/mp3/";
        String name = EncryptUtils.encryptMD5ToString(musicModel.getUrl()) + ".mp3";
        LogUtils.e("路径:", dataPath + "   " + name);
        OkGo.<File>get(musicModel.getUrl())
                .tag(musicModel)
                .execute(new FileCallback(dataPath, name) {   //指定下载的路径  下载文件名
                    @Override
                    public void onSuccess(Response<File> response) {
                        myProgressDialog.dismiss();
                        EventBus.getDefault().post(new MusicDownEvent(musicModel, response.body().getPath()));
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        myProgressDialog.dismiss();
                        Log.e("randomcode", "下载失败1 " + response.getException().getMessage());
                    }

                });
    }


}
