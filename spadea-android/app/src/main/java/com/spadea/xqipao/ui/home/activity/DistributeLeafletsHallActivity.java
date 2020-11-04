package com.spadea.xqipao.ui.home.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.HOME_DISTRIBUTELEAFLETSHALL, name = "派单大厅")
public class DistributeLeafletsHallActivity extends BaseActivity {

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

    public DistributeLeafletsHallActivity() {
        super(R.layout.activity_distribute_leaflets_hall);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTitle.setText("派单大厅");
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


    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
