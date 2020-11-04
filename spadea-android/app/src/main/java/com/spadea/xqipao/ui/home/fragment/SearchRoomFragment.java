package com.spadea.xqipao.ui.home.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.SearchRoomInfo;
import com.spadea.xqipao.ui.home.presenter.SearchRoomPresenter;
import com.spadea.xqipao.ui.room.activity.LiveRoomActivity;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.home.adapter.SearchRoomAdapter;
import com.spadea.xqipao.ui.home.contacts.SearchRoomContacts;
import com.spadea.yuyin.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

public class SearchRoomFragment extends BaseFragment<SearchRoomPresenter> implements SearchRoomContacts.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private String keyword;
    private SearchRoomAdapter searchRoomAdapter;

    @Override
    protected SearchRoomPresenter bindPresenter() {
        return new SearchRoomPresenter(this, mContext);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView) {
        CommonEmptyView commonEmptyView = new CommonEmptyView(mContext);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(searchRoomAdapter = new SearchRoomAdapter());
        searchRoomAdapter.setEmptyView(commonEmptyView);
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
        searchRoomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchRoomInfo item = searchRoomAdapter.getItem(position);
                LiveRoomActivity.start(getActivity(), item.getRoom_id());
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
        disLoading();
        srl.finishRefresh();
    }


    public void searchKeyWord(String keyword) {
        if (TextUtils.isEmpty(keyword)){
            ToastUtils.showShort("请输入关键词");
            srl.finishRefresh();
            return;
        }
        this.keyword = keyword;
        if (MvpPre!=null) {
            MvpPre.searchRoom(keyword);
        }
    }

    @Override
    public void searchRoomSuccess(List<SearchRoomInfo> data) {
        searchRoomAdapter.setNewData(data);
    }
}
