package com.qpyy.room.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.constant.Constants;
import com.qpyy.libcommon.db.DbController;
import com.qpyy.libcommon.db.table.MusicTable;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.SongsAdapter;
import com.qpyy.room.bean.MusicResp;
import com.qpyy.room.bean.UpdateMusicListEvent;
import com.qpyy.room.contacts.SearchSongsContacts;
import com.qpyy.room.presenter.SearchSongsPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.SEARCH_SONGS, name = "音乐搜索")
public class SearchSongsActivity extends BaseMvpActivity<SearchSongsPresenter> implements SearchSongsContacts.View {


    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.edit_query)
    EditText editQuery;
    @BindView(R2.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private int page = 1;
    private String musicName = "热门";
    private SongsAdapter mSongsAdapter;
    private boolean needPlay = false;

    @Override
    protected SearchSongsPresenter bindPresenter() {
        return new SearchSongsPresenter(this, this);
    }

    @Override
    protected void initData() {
        MvpPre.searchMusic(musicName, page);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_activity_search_songs;
    }


    @Override
    protected void initView() {
        super.initView();
        tvTitle.setText("热门推荐");
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(mSongsAdapter = new SongsAdapter());
        mSongsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MusicResp item = mSongsAdapter.getItem(position);
                MvpPre.download(item);
                needPlay = false;//不需要播放
            }
        });
        //列表点击
        mSongsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //直接跳转播放音乐
                MusicResp item = mSongsAdapter.getItem(position);//搜索的实体信息类
                MusicTable musicTable = new MusicTable();//播放音乐需要的实体信息类
                musicTable.setAuthor(item.getAuthor());
                musicTable.setSongid(item.getSongid());
                musicTable.setTitle(item.getTitle());
                musicTable.setUrl(item.getUrl());

                TextView tvAdd = view.findViewById(R.id.tv_add);
                if(tvAdd.getVisibility() == View.GONE){
                    EventBus.getDefault().post(musicTable);//通知RoomActivity播放音乐
                    needPlay = true;//需要播放
                }

            }
        });

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                MvpPre.searchMusic(musicName, page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                MvpPre.searchMusic(musicName, page);
            }
        });

        editQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                page = 1;
                musicName = editQuery.getText().toString().trim();
                if (TextUtils.isEmpty(musicName)) {
                    musicName = "热门";
                }
                MvpPre.searchMusic(musicName, page);
            }
        });
    }

    @Override
    public void setSearchMusicData(List<MusicResp> data) {
        if (page == 1) {
            mSongsAdapter.setNewData(data);
        } else {
            mSongsAdapter.addData(data);
        }
        if (data == null || data.size() == 0) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void onComplete() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void downloadSuccess(MusicResp musicResp) {
        ToastUtils.show("下载成功");
        DbController dbController = DbController.getInstance(this);
        MusicTable musicTable = new MusicTable();
        musicTable.setAuthor(musicResp.getAuthor());
        musicTable.setTitle(musicResp.getTitle());
        musicTable.setSongid(musicResp.getSongid());
        musicTable.setUrl(Constants.MUSIC_PATH + musicResp.getTitle() + ".mp3");
        dbController.insertOrReplace(musicTable);
        mSongsAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new UpdateMusicListEvent(musicTable.getSongid(),needPlay));//更新音乐列表弹窗数据
        disLoadings();
    }

    @Override
    public void downloadFailure() {
        disLoadings();
        ToastUtils.show("下载失败请重试");
    }

    @OnClick(R2.id.iv_back)
    public void onClick(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
