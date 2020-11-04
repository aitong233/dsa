package com.spadea.xqipao.ui.me.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.FriendModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.SelectFriendContacts;
import com.spadea.xqipao.ui.me.fragment.SelectFriendPresenter;
import com.spadea.xqipao.utils.dialog.GiveFriedDialog;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.me.adapter.SelectFriendAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.ME_SELECTFRIEND, name = "选择好友")
public class SelectFriendActivity extends BaseActivity<SelectFriendPresenter> implements SelectFriendContacts.View {


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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    @Autowired
    public String productId;
    @Autowired
    public String priceId;
    @Autowired
    public String title;
    @Autowired
    public String day;


    private SelectFriendAdapter selectFriendAdapter;

    private int page = 1;

    public SelectFriendActivity() {
        super(R.layout.activity_select_friend);
    }

    @Override
    protected void initData() {
        MvpPre.friendList(page);
    }

    @Override
    protected void initView() {
        tvTitle.setText("选择好友");
        CommonEmptyView commonEmptyView = new CommonEmptyView(this);
        mSmartRefreshLayout.setEnableLoadMore(true);
        mSmartRefreshLayout.setEnableRefresh(true);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                MvpPre.friendList(page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                MvpPre.friendList(page);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(selectFriendAdapter = new SelectFriendAdapter());
        selectFriendAdapter.setEmptyView(commonEmptyView);
    }

    @Override
    protected void setListener() {
        super.setListener();
        selectFriendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FriendModel item = selectFriendAdapter.getItem(position);
                GiveFriedDialog giveFriedDialog = new GiveFriedDialog(SelectFriendActivity.this);
                giveFriedDialog.setText("您将赠送给" + item.getNickname() + "【" + title + "】" + "有效期" + day + "天");
                giveFriedDialog.setmGiveFriedOnClickListener(new GiveFriedDialog.GiveFriedOnClickListener() {
                    @Override
                    public void give() {
                        MvpPre.buyShop(item.getFriend_id(), productId, priceId);
                    }
                });
                giveFriedDialog.show();
            }
        });
    }

    @Override
    protected SelectFriendPresenter bindPresenter() {
        return new SelectFriendPresenter(this, this);
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
    public void setData(int page, List<FriendModel> data) {
        if (page == 1) {
            selectFriendAdapter.setNewData(data);
        } else {
            selectFriendAdapter.addData(data);
        }
        if (data == null || data.size() == 0) {
            mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void buyShopSuccess() {
        ToastUtils.showShort("赠送成功");
        finish();
    }

    @Override
    public void finishLoading() {
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
    }
}
