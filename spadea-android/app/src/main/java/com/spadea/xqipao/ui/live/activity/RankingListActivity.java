package com.spadea.xqipao.ui.live.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.spadea.xqipao.ui.live.fragment.CharmRankingFragment;
import com.spadea.xqipao.ui.live.fragment.WeekStarRankingFragment;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RankingListActivity extends BaseActivity {


    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.sliding_tab_layout)
    SlidingTabLayout slidingTabLayout;

    private String roomId = "";


    public static void startActivity(Activity activity, String roomId) {
        Intent intent = new Intent(activity, RankingListActivity.class);
        intent.putExtra("roomId", roomId);
        activity.startActivity(intent);
    }


//    , "周星榜", "房间榜"
    private String[] titleList = new String[]{"魅力榜", "财富榜","周星榜"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    public RankingListActivity() {
        super(R.layout.activity_ranking_list);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        roomId = getIntent().getStringExtra("roomId");
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setAndroidNativeLightStatusBar(this, false);
        fragmentList.add(CharmRankingFragment.newInstance(roomId,0));
        fragmentList.add(CharmRankingFragment.newInstance(roomId,1));
        fragmentList.add(WeekStarRankingFragment.newInstance(roomId,2));
//        fragmentList.add(RoomRankingFragment.newInstance(roomId,3));
//        fragmentList.add(RoomRankingFragment.newInstance(roomId,4));
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager());
        viewPage.setAdapter(myFragmentPagerAdapter);
        slidingTabLayout.setViewPager(viewPage, titleList);
    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        finish();
    }
}
