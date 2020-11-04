package com.spadea.xqipao.ui.live.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.spadea.xqipao.ui.live.contacts.MusicSearchContacts;
import com.spadea.xqipao.ui.live.fragment.MusicFragment;
import com.spadea.xqipao.ui.live.presenter.MusicSearchPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.yuyin.R;

import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.yuyin.util.utilcode.BarUtils;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MusicSearchActivity extends BaseActivity<MusicSearchPresenter> implements MusicSearchContacts.View {


    @BindView(R.id.ctl)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.view_page)
    ViewPager viewPager;

    private String[] titleList = new String[]{"本地音乐", "热门推荐"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyFragmentPagerAdapter myFragmentPagerAdapter;


    public MusicSearchActivity() {
        super(R.layout.activity_music_search);
    }


    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setAndroidNativeLightStatusBar(this, false);
        fragmentList.add(MusicFragment.newInstance(0));
        fragmentList.add(MusicFragment.newInstance(1));
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager());
        viewPager.setAdapter(myFragmentPagerAdapter);
        slidingTabLayout.setViewPager(viewPager, titleList);
    }

    @Override
    protected MusicSearchPresenter bindPresenter() {
        return new MusicSearchPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }
}
