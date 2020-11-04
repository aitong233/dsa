package com.spadea.xqipao.ui.me.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.ME_QUALIFICATIONAUDIT, name = "资质审核")
public class QualificationAuditActivity extends BaseActivity {


    @BindView(R.id.tv_supplementary_info)
    TextView tvSupplementaryInfo;
    @BindView(R.id.tv_examine)
    TextView tvExamine;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.iv_state)
    ImageView ivState;

    public QualificationAuditActivity() {
        super(R.layout.activity_qualification_audit);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setAndroidNativeLightStatusBar(this, true);
        tvTitle.setText("资质审核");
        viewLine.setVisibility(View.GONE);
        tvTitle.setTextColor(Color.parseColor("#FFFFFF"));
        ivBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_ff));

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


    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
