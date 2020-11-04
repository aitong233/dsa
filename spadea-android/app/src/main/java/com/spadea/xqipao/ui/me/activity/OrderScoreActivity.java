package com.spadea.xqipao.ui.me.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.EvaluateModel;
import com.spadea.xqipao.data.OrderDetailResp;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.OderScoreContacts;
import com.spadea.xqipao.ui.me.presenter.OderScorePresenter;
import com.spadea.xqipao.widget.RatingStarView;
import com.spadea.xqipao.ui.me.adapter.MyOrderAdapter;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.ORDER_SCORE, name = "订单评价")
public class OrderScoreActivity extends BaseActivity<OderScorePresenter> implements OderScoreContacts.View {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.riv)
    RoundedImageView mRiv;
    @BindView(R.id.tv_skill_title)
    TextView mTvSkillTitle;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.tv_unit)
    TextView mTvUnit;
    @BindView(R.id.ll_contact)
    LinearLayout mLlContact;
    @BindView(R.id.rsv_td)
    RatingStarView mRsvTd;
    @BindView(R.id.rsv_pro)
    RatingStarView mRsvPro;
    @BindView(R.id.rsv_exp)
    RatingStarView mRsvExp;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.tv_action)
    TextView mTvAction;
    @BindView(R.id.tv_exp)
    TextView mTvExp;

    @BindView(R.id.tv_serve)
    TextView mTvServe;

    @BindView(R.id.ll_pro)
    FrameLayout mLLPro;

    @BindView(R.id.ll_info)
    LinearLayout mLLInfo;

    @Autowired
    public int orderId;

    @Autowired
    public int type;

    public OrderDetailResp mRecordsBean;

    public OrderScoreActivity() {
        super(R.layout.activity_order_score);
    }

    @Override
    protected void initData() {
        MvpPre.getDetail(orderId);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("订单评价");

    }

    private void setScoreDetail() {
        mTvTitle.setText("订单详情");
        mEtRemark.setEnabled(false);
        mEtRemark.setBackgroundColor(Color.WHITE);
        mTvAction.setVisibility(View.GONE);
        if (mRecordsBean.getServeStar() > 0) {
            mRsvTd.setScore(mRecordsBean.getServeStar());
        }
        if (mRecordsBean.getFeelStar() > 0) {
            mRsvExp.setScore(mRecordsBean.getFeelStar());
        }
        if (mRecordsBean.getSpecialtyStar() > 0) {
            mRsvPro.setScore(mRecordsBean.getSpecialtyStar());
        }
    }

    @Override
    protected OderScorePresenter bindPresenter() {
        return new OderScorePresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }


    @OnClick({R.id.iv_back, R.id.ll_contact, R.id.tv_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_contact:
                if (MyApplication.getInstance().notSelf(type, mRecordsBean.getUserId(), mRecordsBean.getPlayUserId())) {
                    ARouter.getInstance().build(ARouteConstants.HOME_CHART)
                            .withString("userId", mRecordsBean.getEmchatUsername())
                            .withString("nickname", mRecordsBean.getNickname())
                            .withString("avatar", mRecordsBean.getHeadPicture())
                            .navigation();
                }
                break;
            case R.id.tv_action:
                doEvaluate();
                break;
        }
    }

    private void doEvaluate() {
        String detail = mEtRemark.getText().toString();
        EvaluateModel model = new EvaluateModel();
        model.setDetail(detail);
        model.setOrderId(mRecordsBean.getOrderId());
        model.setServeStar(mRsvTd.getScore());
        model.setFeelStar(mRsvExp.getScore());
        if (type == MyOrderAdapter.TYPE_SEND) {
            model.setSpecialtyStar(String.valueOf(mRsvPro.getScore()));
        }
        MvpPre.evaluate(model, type);
    }

    @Override
    public void evaluateComplete() {
        finish();
    }

    @Override
    public void orderDetail(OrderDetailResp resp) {
        mRecordsBean = resp;
        if (type == MyOrderAdapter.TYPE_SEND) {
            mLLPro.setVisibility(View.VISIBLE);
            mTvSkillTitle.setText(mRecordsBean.getSkillName());
            mLLInfo.setVisibility(View.VISIBLE);
        } else {
            mLLPro.setVisibility(View.GONE);
            mLLInfo.setVisibility(View.GONE);
            mTvServe.setText("资料一致");
            mTvExp.setText("体验过程");
            mTvSkillTitle.setText(mRecordsBean.getNickname());
        }
        if (mRecordsBean.getFeelStar() > 0) {
            setScoreDetail();
        }
        ImageLoader.loadImage(this, mRiv, mRecordsBean.getHeadPicture());
        mTvNum.setText(String.valueOf(mRecordsBean.getNumber()));
        mTvUnit.setText(mRecordsBean.getSkillUnit());
        if (!TextUtils.isEmpty(mRecordsBean.getDetail())) {
            mEtRemark.setHint(mRecordsBean.getDetail());
        }
    }
}
