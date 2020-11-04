package com.spadea.xqipao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.CategoriesModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.me.contacter.ShopContacts;
import com.spadea.xqipao.ui.me.fragment.ShopFragment;
import com.spadea.xqipao.ui.me.presenter.ShopPresenter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.widget.CoustomSlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.ME_SHOP, name = "个性装扮")
public class ShopActivity extends BaseActivity<ShopPresenter> implements ShopContacts.View {


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
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.coustom_sliding_tab_layout)
    CoustomSlidingTabLayout coustomSlidingTabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    public ShopActivity() {
        super(R.layout.activity_shop);
    }

    @Override
    protected void initData() {
        MvpPre.categories();
    }

    @Override
    protected void initView() {
        tvTitle.setText("个性装扮");
        tvRight.setText("我的背包");
        tvRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        super.setListener();
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouteConstants.ME_KNAPSACK).navigation(ShopActivity.this, 10);
            }
        });
    }

    @Override
    protected ShopPresenter bindPresenter() {
        return new ShopPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_back, R.id.tv_recharge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_recharge:
                ARouter.getInstance().build(ARouters.ME_BALANCE).navigation();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBalance();
    }

    public void getBalance() {
        MvpPre.getBalance();
    }

    @Override
    public void setBalanceMoney(String money) {
        tvTotal.setText(money);
    }

    private List<CategoriesModel> data;

    @Override
    public void categoriesSuccess(List<CategoriesModel> data) {
        this.data = data;
        List<Fragment> fragmentList = new ArrayList<>();
        String[] title = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            CategoriesModel categoriesModel = data.get(i);
            title[i] = categoriesModel.getTitle();
            fragmentList.add(ShopFragment.newInstance(categoriesModel.getTitle(), categoriesModel.getId()));
        }
        viewpager.setAdapter(myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager()));
        coustomSlidingTabLayout.setViewPager(viewpager, title);
        coustomSlidingTabLayout.setCurrentTab(0);
    }

    public String getMoney() {
        return tvTotal.getText().toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 10: //跳转请求码
                    if (data != null) {
                        String dressPosition = data.getStringExtra("dressPosition");
                        for (int i = 0; i < this.data.size(); i++) {
                            CategoriesModel categoriesModel = this.data.get(i);
                            if (dressPosition.equals(categoriesModel.getId())) {
                                coustomSlidingTabLayout.setCurrentTab(i);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
