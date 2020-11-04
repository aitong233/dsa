package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.constant.SPConstants;
import com.qpyy.libcommon.db.DbController;
import com.qpyy.libcommon.db.table.MusicTable;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.MusicWindowsListAdapter;
import com.qpyy.room.bean.CurrentMusic;
import com.qpyy.room.bean.MusicDelEvent;
import com.qpyy.room.bean.UpdateMusicListEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/5 18:24
 * 描述 describe
 */
public class MusicListDialogFragment extends BaseMvpDialogFragment {

    private static final String TAG = "MusicListDialogFragment";
    @BindView(R2.id.iv_music_play_style)
    ImageView ivMusicPlayStyle;
    @BindView(R2.id.tv_music_play_style)
    TextView tvMusicPlayStyle;
    @BindView(R2.id.tv_music_upload)
    TextView tvMusicUpload;
    @BindView(R2.id.cl_music_list_title_layout)
    ConstraintLayout clMusicListTitleLayout;
    @BindView(R2.id.rv_music_list)
    RecyclerView rvMusicList;
    @BindView(R2.id.tv_close_music_list)
    TextView tvCloseMusicList;
    private MusicWindowsListAdapter musicListAdapter;
    private int playPattern;

    private int loopType = 1;//循环类型（1列表循环 2 单曲循环 3随机播放）
    private int mCurrentMusic;
    private boolean isPlay;


    public static MusicListDialogFragment newInstance(int musicTable, boolean isPlay) {
        MusicListDialogFragment musicListDialogFragment = new MusicListDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SPConstants.CURRENT_MUSIC, musicTable);
        bundle.putBoolean("isPlay", isPlay);
        musicListDialogFragment.setArguments(bundle);
        return musicListDialogFragment;
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.4f;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setGravity(Gravity.BOTTOM);
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        mCurrentMusic = arguments.getInt(SPConstants.CURRENT_MUSIC);
        isPlay = arguments.getBoolean("isPlay");
        if (!isPlay) {
            mCurrentMusic = 0;
        }
        SpUtils.setPlayCurrentMusic(mCurrentMusic);

    }

    @Override
    public void initData() {
        musicListAdapter.setSongId(mCurrentMusic, isPlay);
        List<MusicTable> musicTables = DbController.getInstance(getContext()).queryMusicListAll();
        musicListAdapter.setNewData(musicTables);
        setMusicCoun(musicTables.size());
    }

    @Override
    public void initView() {
        rvMusicList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMusicList.setAdapter(musicListAdapter = new MusicWindowsListAdapter());
        musicListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MusicTable item = musicListAdapter.getItem(position);
                boolean canRemove = (item.getSongid() == musicListAdapter.getSongId())/* && !isPlay*/;
                if ((item.getSongid() != musicListAdapter.getSongId() || canRemove)) {
                    List<MusicTable> data = musicListAdapter.getData();
                    DbController.getInstance(getContext()).deleteMusicBy(data.get(position));
                    data.remove(position);
                    musicListAdapter.setNewData(data);
                    EventBus.getDefault().post(new MusicDelEvent(item.getSongid()));
                    getMusicCount();
                }
            }
        });

        musicListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MusicTable musicTable = musicListAdapter.getItem(position);
                if (mCurrentMusic != musicTable.getSongid()) {
                    isPlay = true;
                    musicListAdapter.setSongId(musicTable.getSongid(), isPlay);//设置正在播放的音乐，更新ui
                    mCurrentMusic = musicTable.getSongid();
                    EventBus.getDefault().post(musicTable);//通知播放
                } else {
                    EventBus.getDefault().post(new CurrentMusic(isPlay));
                    isPlay = !isPlay;
                    musicListAdapter.setSongId(musicTable.getSongid(), isPlay);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        Log.d(TAG, "(Start)启动了===========================MusicListDialogFragment");
        return R.layout.room_dialog_music_win_list;
    }

    private void getMusicCount() {
        int i = DbController.getInstance(getContext()).queryMUsicCount();
        playPattern = SpUtils.getPlayPattern();
        ivMusicPlayStyle.setImageLevel(playPattern);
        switch (playPattern) {
            case 0:
                tvMusicPlayStyle.setText("顺序播放(" + i + ")首");
                break;
            case 1:
                tvMusicPlayStyle.setText("随机播放(" + i + ")首");
                break;
            case 2:
                tvMusicPlayStyle.setText("单曲循环(" + i + ")首");
                break;
        }
    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    private void setMusicCoun(int count) {
        int playPattern = SpUtils.getPlayPattern();
        switch (playPattern) {
            case 1:
                tvMusicPlayStyle.setText("顺序播放(" + count + ")首");
                ivMusicPlayStyle.setImageResource(R.mipmap.room_music_win_circulation);
                break;
            case 2:
                tvMusicPlayStyle.setText("单曲循环(" + count + ")首");
                ivMusicPlayStyle.setImageResource(R.mipmap.room_music_win_singlecircle);
                break;
            case 3:
                tvMusicPlayStyle.setText("随机播放(" + count + ")首");
                ivMusicPlayStyle.setImageResource(R.mipmap.room_music_win_random);
                break;
        }
    }


    @OnClick({R2.id.iv_music_play_style, R2.id.tv_music_upload, R2.id.tv_close_music_list})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_music_play_style) {
            if (loopType == 1) {
                //这个就是单曲循环了
                loopType = 2;
                ivMusicPlayStyle.setImageResource(R.mipmap.room_music_win_singlecircle);
            } else if (loopType == 2) {
                //这个就是随机播放了
                loopType = 3;
                ivMusicPlayStyle.setImageResource(R.mipmap.room_music_win_random);
            } else {
                //再就是列表循环了
                loopType = 1;
                ivMusicPlayStyle.setImageResource(R.mipmap.room_music_win_circulation);
            }
            //循环方式
        } else if (id == R.id.tv_music_upload) {//上传
            ARouter.getInstance().build(ARouteConstants.SEARCH_SONGS).navigation();
            //关闭
        } else if (id == R.id.tv_close_music_list) {
            this.dismiss();
        }
    }

    /**
     * 更新音乐列表
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateMusicList(UpdateMusicListEvent updateMusicListEvent) {
        List<MusicTable> musicTables = DbController.getInstance(getContext()).queryMusicListAll();
        musicListAdapter.setNewData(musicTables);
        setMusicCoun(musicTables.size());
        musicListAdapter.setSongId(updateMusicListEvent.getCurrentMusic(),updateMusicListEvent.isPlay());
        musicListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
