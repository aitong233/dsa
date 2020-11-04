package com.spadea.xqipao.ui.me.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hyphenate.chat.EMClient;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.spadea.yuyin.R;
import com.spadea.yuyin.ui.fragment2.setting.blacklist.BlackListBean;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.BlacListSectionBean;
import com.spadea.xqipao.data.BlackSectionBean;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.BlackListContact;
import com.spadea.xqipao.ui.me.presenter.BlackListPresenter;
import com.spadea.xqipao.ui.me.adapter.BlackListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.activity
 * 创建人 王欧
 * 创建时间 2020/4/1 1:55 PM
 * 描述 describe
 */
@Route(path = ARouters.BLACK_LIST, name = "黑名单")
public class BlackListActivity extends BaseActivity<BlackListPresenter> implements BlackListContact.View {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    @BindView(R.id.topbar_line)
    View mTopBarLine;
    @BindView(R.id.et_search)
    EditText mEtSearch;

    int page = 1;

    String keyword = "";

    BlackListAdapter mAdapter;
    private TextWatcher mTextWatcher;

    public BlackListActivity() {
        super(R.layout.activity_black_list);
    }

    @Override
    protected void initData() {
        mTopBarLine.setVisibility(View.GONE);
        mTvTitle.setText("黑名单");
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRv);
        mAdapter.setEmptyView(R.layout.layout_empty_black_list);
        mSrl.autoRefresh();
    }

    @Override
    protected void initView() {
        mAdapter = new BlackListAdapter();
    }

    @Override
    protected void setListener() {
        super.setListener();

        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyword = s.toString();
                page = 1;
                MvpPre.getBlackList(page, keyword);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        mEtSearch.addTextChangedListener(mTextWatcher);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MvpPre.removeUser(mAdapter.getItem(position).t.getBlack_id(), position);
            }
        });
        mSrl.setEnableLoadMore(false);
        mSrl.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                MvpPre.getBlackList(page, keyword);
            }
        });
    }

    @Override
    protected BlackListPresenter bindPresenter() {
        return new BlackListPresenter(this, this);
    }

    @Override
    public void blackList(List<BlacListSectionBean> listBeans) {
        mSrl.finishRefresh();
        mSrl.finishLoadMore();
        List<BlackSectionBean> sectionBeans = new ArrayList<>();
        if (listBeans != null && listBeans.size() > 0) {
            for (BlacListSectionBean listBean : listBeans) {
                sectionBeans.add(new BlackSectionBean(true, listBean.getIndex()));
                for (BlackListBean bean : listBean.getItems()) {
                    sectionBeans.add(new BlackSectionBean(bean));
                }
            }
        }
        mAdapter.setNewData(sectionBeans);
    }

    @Override
    public void userRemoved(int position) {
        try {
            EMClient.getInstance().contactManager().removeUserFromBlackList(mAdapter.getItem(position).t.getEmchat_username());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAdapter.remove(position);
        BlackSectionBean sectionBean = mAdapter.getItem(position - 1);
        if (sectionBean != null && sectionBean.isHeader) {
            mAdapter.remove(position - 1);
        }
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @OnClick({R.id.iv_left, R.id.tv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
            case R.id.tv_left:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mEtSearch.removeTextChangedListener(mTextWatcher);
        super.onDestroy();
    }
}
