package com.qpyy.room.presenter;

import android.content.Context;

import com.blankj.utilcode.util.ThreadUtils;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.constant.Constants;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.DownloadUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.MusicResp;
import com.qpyy.room.contacts.SearchSongsContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchSongsPresenter extends BasePresenter<SearchSongsContacts.View> implements SearchSongsContacts.SearchSongsIpre {

    public SearchSongsPresenter(SearchSongsContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void searchMusic(String musicName, int page) {
        MvpRef.get().showLoadings();
        //netease qq
        ApiClient.getInstance().searchMusic(musicName, "name", "netease", page, new BaseObserver<List<MusicResp>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<MusicResp> musicResps) {
                MvpRef.get().setSearchMusicData(musicResps);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().onComplete();
            }
        });
    }

    @Override
    public void download(MusicResp musicResp) {
        MvpRef.get().showLoadings("下载中");
        ApiClient.getInstance().download(musicResp.getUrl(), new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    save2Disk(response.body(), musicResp);
                }else {
                    MvpRef.get().downloadFailure();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                MvpRef.get().downloadFailure();
            }
        });
    }

    private void save2Disk(ResponseBody body, MusicResp musicResp) {
        ThreadUtils.executeByCached(new ThreadUtils.SimpleTask<Boolean>() {
            @Override
            public Boolean doInBackground() {
                return DownloadUtils.writeResponseBodyToDisk(body, Constants.MUSIC_PATH, musicResp.getTitle() + ".mp3");
            }

            @Override
            public void onSuccess(Boolean result) {
                if (!isViewAttach()) {
                    return;
                }
                if (result) {
                    MvpRef.get().downloadSuccess(musicResp);
                } else {
                    MvpRef.get().downloadFailure();
                }
            }
        });
    }


}
