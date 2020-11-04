package com.spadea.xqipao.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.R;
import com.spadea.yuyin.widget.CoustomSlidingTabLayout;
import com.spadea.xqipao.data.CategoriesModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.me.contacter.KnapsackContacts;
import com.spadea.xqipao.ui.me.fragment.KnapsackFragment;
import com.spadea.xqipao.ui.me.presenter.KnapsackPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.ME_KNAPSACK, name = "我的背包")
public class KnapsackActivity extends BaseActivity<KnapsackPresenter> implements KnapsackContacts.View {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.coustom_sliding_tab_layout)
    CoustomSlidingTabLayout coustomSlidingTabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;


    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    public KnapsackActivity() {
        super(R.layout.activity_knapsack);
    }

    @Override
    protected void initData() {
        MvpPre.categories();
    }

    @Override
    protected void initView() {
        tvTitle.setText("我的背包");
        viewLine.setVisibility(View.GONE);
    }

    @Override
    protected KnapsackPresenter bindPresenter() {
        return new KnapsackPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void categoriesSuccess(List<CategoriesModel> data) {
        List<Fragment> fragmentList = new ArrayList<>();
        String[] title = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            CategoriesModel categoriesModel = data.get(i);
            title[i] = categoriesModel.getTitle();
            fragmentList.add(KnapsackFragment.newInstance(categoriesModel.getTitle(), categoriesModel.getId()));
        }
        viewpager.setAdapter(myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager()));
        coustomSlidingTabLayout.setViewPager(viewpager, title);
        coustomSlidingTabLayout.setCurrentTab(0);
    }
}
