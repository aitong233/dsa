package com.spadea.xqipao.ui.me.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.SkillSection;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.SkillSelectContacts;
import com.spadea.xqipao.ui.me.presenter.SkillSelectPresenter;
import com.spadea.xqipao.ui.me.adapter.SkillAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(name = "资质申请技能选择", path = ARouters.SKILL_SELECT)
public class SkillSelectActivity extends BaseActivity<SkillSelectPresenter> implements SkillSelectContacts.View {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private SkillAdapter mAdapter;

    public SkillSelectActivity() {
        super(R.layout.activity_skill_select);
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SkillAdapter();
        mRecyclerView.setAdapter(mAdapter);
        MvpPre.getSkillKinds();
    }

    @Override
    protected void initView() {
        mTvTitle.setText("技能选择");
    }

    @Override
    protected SkillSelectPresenter bindPresenter() {
        return new SkillSelectPresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void skillKinds(List<SkillSection> sections) {
        mAdapter.setNewData(sections);
    }

    @Override
    public void skillStatus(int status, SkillSection.Item item) {
        item.setStatus(status);
        switch (status) {
            case 3:
                ARouter.getInstance().build(ARouters.ME_QUALIFICATION_APPLY).withObject("skill", item).navigation();
                break;
            case 0:
                ARouter.getInstance().build(ARouters.APPLY_FAIL).withObject("skill", item).withInt("type", 2).navigation();
                break;
            case 1:
                ToastUtils.showShort("已申请");
                break;
            case 2:
                ToastUtils.showShort("审核中");
                break;
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
    public void skillClick(SkillSection.Item item) {
        MvpPre.checkSkillStatus(item);
    }
}
