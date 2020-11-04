package com.spadea.xqipao.ui.home.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.SearchUserInfo;
import com.spadea.xqipao.ui.home.presenter.SearchUserPresenter;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.home.adapter.SearchUserAdapter;
import com.spadea.xqipao.ui.home.contacts.SearchUserContacts;
import com.spadea.yuyin.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

public class SearchUserFragment extends BaseFragment<SearchUserPresenter> implements SearchUserContacts.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;


    private String keyword;
    private SearchUserAdapter searchUserAdapter;

    @Override
    protected SearchUserPresenter bindPresenter() {
        return new SearchUserPresenter(this, mContext);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView) {
        CommonEmptyView commonEmptyView = new CommonEmptyView(mContext);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(searchUserAdapter = new SearchUserAdapter());
        searchUserAdapter.setEmptyView(commonEmptyView);
    }

    @Override
    protected void initListener() {
        super.initListener();
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                searchKeyWord(keyword);
            }
        });
        searchUserAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchUserInfo item = searchUserAdapter.getItem(position);
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", item.getUser_id()).navigation();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        srl.finishRefresh();
        disLoading();
    }


    public void searchKeyWord(String keyword) {
        if (TextUtils.isEmpty(keyword)){
            srl.finishRefresh();
            ToastUtils.showShort("请输入关键词");
            return;
        }
        this.keyword = keyword;
        if (MvpPre!=null) {
            MvpPre.searchUser(keyword);
        }
    }


    @Override
    public void searchUserSuccess(List<SearchUserInfo> data) {
        searchUserAdapter.setNewData(data);
    }
}
