package com.spadea.xqipao.ui.live.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.db.DbController;
import com.qpyy.libcommon.db.table.MusicTable;
import com.spadea.xqipao.data.MusicModel;
import com.spadea.xqipao.data.even.MusicDelEvent;
import com.spadea.xqipao.data.even.MusicDownEvent;
import com.spadea.xqipao.ui.live.presenter.MusicPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.live.adapter.MusicAdapter;
import com.spadea.xqipao.ui.live.contacts.MusicContacts;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class MusicFragment extends BaseFragment<MusicPresenter> implements MusicContacts.View {


    @BindView(R.id.ed_serach)
    EditText edSerach;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    private int type = 0;
    private MusicAdapter musicAdapter;


    /**
     * 音乐搜索fragment
     *
     * @param type 0 本地音乐    1热门推荐
     * @return
     */
    public static MusicFragment newInstance(int type) {
        MusicFragment musicFragment = new MusicFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        musicFragment.setArguments(bundle);
        return musicFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_music;
    }


    @Override
    protected MusicPresenter bindPresenter() {
        return new MusicPresenter(this, getContext());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        edSerach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == 6 || actionId == 5) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    closeInputMethod(edSerach);
                    if (type == 0) {
                        MvpPre.searchLocalMusic(edSerach.getText().toString());
                    } else {
                        MvpPre.searchNetMusicRefresh(edSerach.getText().toString());
                    }
                }
                return false;
            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (type == 0) {
                    MvpPre.searchLocalMusic(edSerach.getText().toString());
                } else {
                    MvpPre.searchNetMusicRefresh(edSerach.getText().toString());
                }
            }
        });
        if (type == 1) {
            musicAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    MvpPre.searchNetMusicLomer();
                }
            }, recyclerView);
        }

        musicAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MusicModel item = musicAdapter.getItem(position);
                if (type == 0) {
                    MusicTable musicTable = new MusicTable();
                    musicTable.setAuthor(item.getAuthor());
                    musicTable.setSongid(item.getSongid());
                    musicTable.setTitle(item.getTitle());
                    musicTable.setUrl(item.getUrl());
                    DbController.getInstance(getContext()).insertOrReplace(musicTable);
                    musicAdapter.notifyItemChanged(position);
                } else {
                    if (TextUtils.isEmpty(item.getUrl())) {
                        ToastUtils.showShort("下载链接无效");
                    } else {
                        MvpPre.downMp3(item);
                    }
                }
            }
        });
    }

    @Override
    protected void initView(View rootView) {
        EventBus.getDefault().register(this);
        type = getArguments().getInt("type");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(musicAdapter = new MusicAdapter());
        if (type == 0) {
            tvInfo.setVisibility(View.VISIBLE);
            MvpPre.getLocalMusic(getContext());
        } else {
            tvInfo.setVisibility(View.GONE);
            MvpPre.searchNetMusicRefresh("热门");
        }

    }


    @Override
    public void setMusicNum(String text) {
        tvInfo.setText(text);
    }

    @Override
    public void setNewData(List<MusicModel> list) {
        musicAdapter.setNewData(list);
        smartRefreshLayout.finishRefresh();
    }

    @Override
    public void addData(List<MusicModel> list) {
        if (list.size() == 0) {
            musicAdapter.loadMoreEnd();
        } else {
            musicAdapter.addData(list);
            musicAdapter.loadMoreComplete();
        }
    }


    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void downMp3(MusicDownEvent musicDownEven) {
        MusicModel item = musicDownEven.getMusicModel();
        MusicTable musicTable = new MusicTable();
        musicTable.setAuthor(item.getAuthor());
        musicTable.setSongid(item.getSongid());
        musicTable.setTitle(item.getTitle());
        musicTable.setUrl(musicDownEven.getPath());
        DbController.getInstance(getContext()).insertOrReplace(musicTable);
        musicAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new MusicDelEvent());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
