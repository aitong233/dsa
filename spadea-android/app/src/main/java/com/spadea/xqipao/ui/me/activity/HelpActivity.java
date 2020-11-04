package com.spadea.xqipao.ui.me.activity;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.xqipao.data.HelpModel;
import com.spadea.xqipao.data.HelpTitleModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.HelpContacter;
import com.spadea.xqipao.ui.me.presenter.HelpPresenter;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.me.adapter.HelpAdapter;
import com.spadea.xqipao.ui.me.adapter.HelpTitleAdapter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = ARouteConstants.ME_HELP, name = "帮助中心")
public class HelpActivity extends BaseActivity<HelpPresenter> implements HelpContacter.View {

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

    @BindView(R.id.recyclerView_title)
    RecyclerView recyclerViewTitle;

    private HelpAdapter helpAdapter;
    private HelpTitleAdapter helpTitleAdapter;

    public HelpActivity() {
        super(R.layout.activity_help);
    }

    @Override
    protected void initData() {
        CommonEmptyView commonEmptyView = new CommonEmptyView(this);
        recyclerViewTitle.setAdapter(helpTitleAdapter = new HelpTitleAdapter());
        recyclerView.setAdapter(helpAdapter = new HelpAdapter());
        helpAdapter.setEmptyView(commonEmptyView);
        MvpPre.articleCategories();
    }

    @Override
    protected void initView() {
        tvTitle.setText("帮助中心");
        viewLine.setVisibility(View.GONE);
        BarUtils.setStatusBarAlpha(this, 0);
        tvTitle.setTextColor(Color.parseColor("#FFFFFF"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ivBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_ff));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewTitle.setLayoutManager(linearLayoutManager);
    }


    @Override
    protected void setListener() {
        super.setListener();
        helpTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HelpTitleModel item = helpTitleAdapter.getItem(position);
                MvpPre.articleList(item.getArticle_cat_id());
            }
        });
        helpAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                helpAdapter.setIndex(position);
            }
        });
    }

    @Override
    protected HelpPresenter bindPresenter() {
        return new HelpPresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
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
    public void articleCategoriesSuccess(List<HelpTitleModel> data) {
        helpTitleAdapter.setNewData(data);
        MvpPre.articleList(data.get(0).getArticle_cat_id());
    }

    @Override
    public void articleListSuccess(List<HelpModel> data) {
        helpAdapter.setNewData(data);
    }

}
