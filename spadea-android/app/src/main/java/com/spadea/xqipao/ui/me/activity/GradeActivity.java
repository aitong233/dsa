package com.spadea.xqipao.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;
import com.spadea.yuyin.widget.CoustomSlidingTabLayout;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.me.fragment.JueFragment;
import com.spadea.xqipao.ui.me.fragment.VipFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = ARouteConstants.ME_GRADEACTIVITY, name = "会员中心")
public class GradeActivity extends BaseActivity {

    @BindView(R.id.coustom_sliding_tab_layout)
    CoustomSlidingTabLayout coustomSlidingTabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Autowired
    public int type;

    private String[] title = new String[]{"VIP", "爵位"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyFragmentPagerAdapter myFragmentPagerAdapter;


    public GradeActivity() {
        super(R.layout.activity_grade);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarAlpha(this, 0);
        fragmentList.add(VipFragment.newInstance());
        fragmentList.add(JueFragment.newInstance());
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager());
        viewpager.setAdapter(myFragmentPagerAdapter);
        coustomSlidingTabLayout.setViewPager(viewpager, title);
        coustomSlidingTabLayout.setCurrentTab(type);
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

    @OnClick({R.id.iv_back, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_right:
                int currentItem = viewpager.getCurrentItem();
                if (currentItem == 0) {
                    ARouter.getInstance().build(ARouters.H5).withString("title", "VIP等级说明").withString("url", Constant.URL.ARTICLEAPI_NOBILITY).navigation();
                } else {
                    ARouter.getInstance().build(ARouters.H5).withString("title", "爵位说明").withString("url", Constant.URL.ARTICLEAPI_NOBILITY).navigation();
                }
                break;
        }
    }


}
