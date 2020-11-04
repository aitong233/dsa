package com.spadea.xqipao.ui.chart.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.R;
import com.spadea.xqipao.ui.chart.contacts.RemarkNameContacts;
import com.spadea.xqipao.ui.chart.presenter.RemarkNamePresenter;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.REMARK_NAME)
public class RemarkNameActivity extends BaseMvpActivity<RemarkNamePresenter> implements RemarkNameContacts.View {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.iv_clear)
    ImageView mIvClear;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        mTvTitle.setText("设置备注名");
        mEtRemark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    mIvClear.setVisibility(View.GONE);
                } else {
                    mIvClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remark_name;
    }

    @Override
    protected RemarkNamePresenter bindPresenter() {
        return new RemarkNamePresenter(this, this);
    }

    @OnClick({R.id.iv_back, R.id.iv_clear, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_clear:
                mEtRemark.setText("");
                break;
            case R.id.tv_save:
                break;
        }
    }
}
