package com.spadea.xqipao.ui.room.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.yuyin.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class AdminListActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    public AdminListActivity() {
        super(R.layout.activity_admin_list);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ivAdd.setVisibility(View.GONE);
    }


    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @OnClick({R.id.iv_add})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_add:
                AddActivity.start(this,0,"108");
                break;
        }
    }


}
