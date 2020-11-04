package com.spadea.xqipao.ui.me.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.yuyin.R;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.UserInfoDataModel;
import com.spadea.xqipao.data.UserSkillItem;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.me.contacter.UserSkillContacts;
import com.spadea.xqipao.ui.me.presenter.UserSkillPresenter;
import com.spadea.xqipao.ui.ImageBrowseActivity;
import com.spadea.xqipao.ui.me.adapter.UserSkillAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.fragment
 * 创建人 王欧
 * 创建时间 2020/6/1 10:32 AM
 * 描述 describe
 */
public class UserSkillFragment extends BaseFragment<UserSkillPresenter> implements UserSkillContacts.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private UserSkillAdapter mAdapter;

    private UserInfoDataModel userInfo;

    public static UserSkillFragment newInstance(UserInfoDataModel data) {
        UserSkillFragment fragment = new UserSkillFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected UserSkillPresenter bindPresenter() {
        return new UserSkillPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        userInfo = (UserInfoDataModel) getArguments().getSerializable("data");
        MvpPre.getSKills(userInfo.getUser_id());
    }

    @Override
    protected void initView(View rootView) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new UserSkillAdapter();
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_user_skill;
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @Override
    public void userSkills(List<UserSkillItem> userSkillItems) {
        mAdapter.setNewData(userSkillItems);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        UserSkillItem item = mAdapter.getItem(position);
        if (item == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_action:
                ARouter.getInstance().build(ARouters.CONFIRM_ORDER).withString("userId", userInfo.getUser_id()).withInt("id", item.getId()).navigation();
                break;
            case R.id.image:
                ArrayList<String> list = new ArrayList<>();
                list.add(item.getApplyPicture());
                ImageBrowseActivity.start(getActivity(), 0, list);
                break;
        }
    }
}
