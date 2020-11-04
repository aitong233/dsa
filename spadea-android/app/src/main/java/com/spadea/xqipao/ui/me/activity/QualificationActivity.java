package com.spadea.xqipao.ui.me.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.spadea.yuyin.R;
import com.spadea.yuyin.widget.ScrollViewPager;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.SkillApplyModel;
import com.spadea.xqipao.data.SkillSection;
import com.spadea.xqipao.data.even.ApplyStepChangeEvent;
import com.spadea.xqipao.ui.base.view.BaseAppCompatActivity;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.me.fragment.ApplyStep1Fragment;
import com.spadea.xqipao.ui.me.fragment.ApplyStep3Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.ME_QUALIFICATION_APPLY, name = "资质申请")
public class QualificationActivity extends BaseAppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.viewpager)
    ScrollViewPager mViewPager;

    @Autowired
    public SkillSection.Item skill;

    @Autowired
    public SkillApplyModel applyModel;

    public QualificationActivity() {
        super(R.layout.activity_qualification_apply);
    }

    @Override
    protected void initData() {
        mViewPager.setScroll(true);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ApplyStep1Fragment());
//        fragments.add(new ApplyStep2Fragment());
        fragments.add(new ApplyStep3Fragment());
        mViewPager.setAdapter(new MyFragmentPagerAdapter(fragments, getSupportFragmentManager()));
    }

    @Override
    protected void initView() {
        mTvTitle.setText("资质申请");
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 1) {
            finish();
        } else {
            new AlertDialog.Builder(this).setMessage("是否退出资质申请").setPositiveButton("暂不", null).setNegativeButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            }).create().show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void stepChanged(ApplyStepChangeEvent event) {
        mViewPager.setCurrentItem(event.step - 1);
    }
}
