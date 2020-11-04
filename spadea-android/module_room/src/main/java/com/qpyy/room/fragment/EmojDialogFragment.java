package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.Window;

import com.flyco.tablayout.SlidingTabLayout;
import com.qpyy.libcommon.adapter.MyFragmentPagerAdapter;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.room.R;
import com.qpyy.room.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 表情弹窗
 */
public class EmojDialogFragment extends BaseMvpDialogFragment {


    @BindView(R2.id.sliding_tab_layout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager viewPager;

    private String roomId;

    private String pitNumber;

    private int isGameRoom = 0;

    public static EmojDialogFragment newInstance(String roomId, String pitNumber, int is_game_room) {
        EmojDialogFragment emojDialogFragment = new EmojDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        bundle.putString("pitNumber", pitNumber);
        bundle.putInt("is_game_room", is_game_room);
        emojDialogFragment.setArguments(bundle);
        return emojDialogFragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomId = arguments.getString("roomId");
        pitNumber = arguments.getString("pitNumber");
        isGameRoom = arguments.getInt("is_game_room");
    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(EmojFragment.newInstance(0, roomId, pitNumber,isGameRoom));
//        fragmentList.add(EmojFragment.newInstance(1, roomId, pitNumber, 0));
        viewPager.setAdapter(new MyFragmentPagerAdapter(fragmentList, getChildFragmentManager()));
//        String[] title = new String[]{"默认", "专属表情"};
        String[] title = new String[]{"默认"};
        slidingTabLayout.setViewPager(viewPager, title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragmenr_room_emoj_dialog;
    }


    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.BOTTOM);
    }

    public void show(FragmentManager childFragmentManager) {
        show(childFragmentManager, "EmojDialogFragment");
    }
}
