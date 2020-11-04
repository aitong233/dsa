package com.spadea.xqipao.ui.me.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.SlidingTabLayout;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.even.BackHomeEvent;
import com.spadea.xqipao.data.even.NameIdentifySuccessEvent;
import com.spadea.xqipao.ui.base.view.BaseAppCompatActivity;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.me.fragment.ManualIdentifyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(name = "实名认证", path = ARouters.NAME_IDENTIFY)
public class NameIdentifyAcitivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.sliding_tab_layout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tv_tip_success)
    TextView mTvTipSuccess;
    @BindView(R.id.ll_success)
    LinearLayout mLLSuccess;

    public NameIdentifyAcitivity() {
        super(R.layout.activity_name_identify);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("实名认证");
        List<Fragment> fragmentList = new ArrayList<>();
//        fragmentList.add(new AliIdentifyFragment());
        fragmentList.add(new ManualIdentifyFragment());
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);
//        mSlidingTabLayout.setViewPager(mViewPager, new String[]{"快捷认证", "人工认证"});
        mSlidingTabLayout.setViewPager(mViewPager, new String[]{"人工认证"});
        mSlidingTabLayout.setCurrentTab(0);
        mTvTipSuccess.setText(new SpanUtils().append("提交成功，审核时间").append("1~3").setForegroundColor(Color.parseColor("#FE9EA4")).append("工作日").create());
    }

    @OnClick({R.id.iv_back, R.id.tv_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_finish:
                EventBus.getDefault().post(new BackHomeEvent());
                finish();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void identifySuccess(NameIdentifySuccessEvent event) {
        mSlidingTabLayout.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
        mLLSuccess.setVisibility(View.VISIBLE);
    }
}
