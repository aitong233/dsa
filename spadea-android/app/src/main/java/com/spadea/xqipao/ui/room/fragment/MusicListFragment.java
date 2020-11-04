package com.spadea.xqipao.ui.room.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.db.DbController;
import com.qpyy.libcommon.db.table.MusicTable;
import com.spadea.xqipao.data.even.MusicDelEvent;
import com.spadea.xqipao.data.even.PlayMusicEvent;
import com.spadea.xqipao.utils.SPUtil;
import com.spadea.xqipao.utils.dialog.MusicListAdapter;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.live.activity.MusicSearchActivity;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ScreenUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MusicListFragment extends BaseDialogFragment {


    @BindView(R.id.iv_music_play_pattern)
    ImageView ivMusicPlayPattern;
    @BindView(R.id.tv_music_num)
    TextView tvMusicNum;
    @BindView(R.id.tv_upload)
    TextView tvUpload;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_music_list_close)
    TextView tvMusicListClose;


    private int mMusicTable;
    private MusicListAdapter mMusicListAdapterl;

    public static MusicListFragment newInstance(int musicTable) {
        MusicListFragment musicListFragment = new MusicListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("musicTable", musicTable);
        musicListFragment.setArguments(bundle);
        return musicListFragment;
    }

    @Override
    protected void initData() {
        mMusicTable = getArguments().getInt("musicTable", -1);
        mMusicListAdapterl.setSongid(mMusicTable);
        List<MusicTable> musicTables = DbController.getInstance(mContext).queryMusicListAll();
        mMusicListAdapterl.setNewData(musicTables);
        setMusicCoun(musicTables.size());
    }

    @Override
    protected void initView(View rootView) {
        EventBus.getDefault().register(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(mMusicListAdapterl = new MusicListAdapter());
        mMusicListAdapterl.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MusicTable item = mMusicListAdapterl.getItem(position);
                List<MusicTable> data = mMusicListAdapterl.getData();
                if (item.getSongid() != mMusicListAdapterl.getSongid()) {
                    DbController.getInstance(mContext).deleteMusicBy(item);
                    data.remove(position);
                    mMusicListAdapterl.setNewData(data);
                    setMusicCoun(data.size());
                    EventBus.getDefault().post(new MusicDelEvent());
                }
            }
        });
        mMusicListAdapterl.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MusicTable item = mMusicListAdapterl.getItem(position);
                mMusicListAdapterl.setSongid(item.getSongid());
                EventBus.getDefault().post(item);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_music_list;
    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ScreenUtils.getScreenWidth(), (int) (ScreenUtils.getScreenHeight() * 5.5 / 10));
        window.setWindowAnimations(R.style.ShowDialogBottom);
    }

    @OnClick({R.id.tv_music_list_close, R.id.tv_upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_music_list_close:
                dismiss();
                break;
            case R.id.tv_upload:
                startActivityForResult(new Intent(getActivity(), MusicSearchActivity.class), 0);
                dismiss();
                break;
        }
    }

    private void setMusicCoun(int count) {
        int playPattern = SPUtil.getInt("playPattern", 1);
        switch (playPattern) {
            case 1:
                tvMusicNum.setText("顺序播放(" + count + ")首");
                ivMusicPlayPattern.setImageResource(R.mipmap.icon_pattern);
                break;
            case 2:
                tvMusicNum.setText("单曲循环(" + count + ")首");
                ivMusicPlayPattern.setImageResource(R.mipmap.icon_one);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void playMusicEvent(PlayMusicEvent playMusicEvent) {
        mMusicListAdapterl.setSongid(playMusicEvent.musicTablep.getSongid());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
