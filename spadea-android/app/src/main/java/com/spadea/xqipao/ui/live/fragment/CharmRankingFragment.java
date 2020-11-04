package com.spadea.xqipao.ui.live.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.yuyin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 魅力榜    财富榜
 */
public class CharmRankingFragment extends BaseFragment {

    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.tv_day_ranking)
    TextView tvDayRanking;
    @BindView(R.id.tv_week_ranking)
    TextView tvWeekRanking;
    @BindView(R.id.tv_total_ranking)
    TextView tvTotalRanking;

    private List<Fragment> fragmentList;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    public static Fragment newInstance(String roomId, int type) {
        CharmRankingFragment charmRankingFragment = new CharmRankingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("roomId", roomId);
        charmRankingFragment.setArguments(bundle);
        return charmRankingFragment;
    }


    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView) {
        int type = getArguments().getInt("type");
        String roomId = getArguments().getString("roomId");
        fragmentList = new ArrayList<>();
        fragmentList.add(RankingFragment.newInstance(roomId,type, 1));
        fragmentList.add(RankingFragment.newInstance(roomId,type, 2));
        fragmentList.add(RankingFragment.newInstance(roomId,type, 0));
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getChildFragmentManager());
        viewPage.setAdapter(myFragmentPagerAdapter);
        viewPage.setCurrentItem(0);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                reset();
                select(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_charm_ranking;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @OnClick({R.id.tv_day_ranking, R.id.tv_week_ranking, R.id.tv_total_ranking})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_day_ranking:
                viewPage.setCurrentItem(0);
                break;
            case R.id.tv_week_ranking:
                viewPage.setCurrentItem(1);
                break;
            case R.id.tv_total_ranking:
                viewPage.setCurrentItem(2);
                break;
        }
    }

    private void reset() {
        tvDayRanking.setBackgroundColor(Color.argb(0, 0, 0, 0));
        tvWeekRanking.setBackgroundColor(Color.argb(0, 0, 0, 0));
        tvTotalRanking.setBackgroundColor(Color.argb(0, 0, 0, 0));
    }

    private void select(int postion) {
        switch (postion) {
            case 0:
                tvDayRanking.setBackgroundResource(R.drawable.bg_indicator);
                break;
            case 1:
                tvWeekRanking.setBackgroundResource(R.drawable.bg_indicator);
                break;
            case 2:
                tvTotalRanking.setBackgroundResource(R.drawable.bg_indicator);
                break;
        }
    }

}
