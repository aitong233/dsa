package com.spadea.xqipao.ui.order.activity;

import android.view.View;
import android.widget.ImageView;
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
import com.spadea.xqipao.data.OrderDetailResp;
import com.spadea.xqipao.data.UpdateOrderModel;
import com.spadea.xqipao.ui.order.contacts.OrderDetailContacts;
import com.spadea.xqipao.ui.order.presenter.OrderDetailPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.adapter.MyOrderAdapter;
import com.spadea.xqipao.ui.order.status.OrderEndStatusEnum;
import com.spadea.xqipao.ui.order.status.OrderStatusEnum;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.ORDER_DETAIL, name = "订单详情")
public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> implements OrderDetailContacts.View {

    @Autowired
    public int orderId;
    @Autowired
    public int type;

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.riv)
    RoundedImageView mRiv;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.iv_gender)
    ImageView mIvGender;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.iv_chat)
    ImageView mIvChat;
    @BindView(R.id.tv_pay_state)
    TextView mTvPayState;
    @BindView(R.id.ll_skill)
    LinearLayout mLlSkill;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.ll_count)
    LinearLayout mLlCount;
    @BindView(R.id.tv_method)
    TextView mTvMethod;
    @BindView(R.id.ll_time)
    LinearLayout mLlTime;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.ll_coupon)
    LinearLayout mLlCoupon;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.ll_total)
    LinearLayout mLlTotal;
    @BindView(R.id.tv_remark)
    TextView mTvRemark;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.ll_actions)
    LinearLayout mLlActions;

    private OrderDetailResp mOrderDetailResp;

    public OrderDetailActivity() {
        super(R.layout.activity_order_detail);
    }

    @Override
    protected void initData() {
        MvpPre.getDetail(orderId, type);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("订单详情");
    }

    @Override
    protected OrderDetailPresenter bindPresenter() {
        return new OrderDetailPresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }


    @OnClick({R.id.iv_back, R.id.iv_chat, R.id.tv_submit, R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_chat:
                if (mOrderDetailResp != null) {
                    finish();
                    chat2User();
                }
                break;
            case R.id.tv_submit:
                if (type == MyOrderAdapter.TYPE_SEND && OrderStatusEnum.canFinishOrder(mOrderDetailResp.getOrderStatus())) {
                    MvpPre.bossConfirmOrder(mOrderDetailResp.getOrderId());
                } else if (type == MyOrderAdapter.TYPE_RECV && mOrderDetailResp.getOrderStatus() == OrderStatusEnum.BE_SERVE.getValue()) {
                    MvpPre.accompanyService(mOrderDetailResp.getOrderId());
                }
                break;
            case R.id.tv_left:
                MvpPre.accompanyAccept(new UpdateOrderModel(mOrderDetailResp.getOrderId(), 0));
                break;
            case R.id.tv_right:
                MvpPre.accompanyAccept(new UpdateOrderModel(mOrderDetailResp.getOrderId(), 1));
                break;
        }
    }

    private void chat2User() {
        if (MyApplication.getInstance().notSelf(type,mOrderDetailResp.getUserId(),mOrderDetailResp.getPlayUserId())) {
            ARouter.getInstance().build(ARouteConstants.HOME_CHART)
                    .withString("userId", mOrderDetailResp.getEmchatUsername())
                    .withString("nickname", mOrderDetailResp.getNickname())
                    .withString("avatar", mOrderDetailResp.getHeadPicture())
                    .navigation();
        }
    }

    @Override
    public void orderDetail(OrderDetailResp resp) {
        mOrderDetailResp = resp;
        ImageLoader.loadImage(this, mRiv, resp.getHeadPicture());
        mTvUserName.setText(resp.getNickname());
        if (resp.getSex() == 1) {
            mIvGender.setImageResource(R.mipmap.ic_order_female);
        } else {
            mIvGender.setImageResource(R.mipmap.ic_order_male);
        }
        mTvPayState.setText(OrderEndStatusEnum.getEnumByValue(resp.getEndStatus()).getDesc());
        mTvType.setText(resp.getSkillName());
        mTvMethod.setText("语音连麦");
        mTvTime.setText(resp.getOrderTime());
        mTvNumber.setText(String.format("x%s", resp.getNumber()));
        mTvRemark.setText(resp.getRemark());
        if (type == MyOrderAdapter.TYPE_SEND && OrderStatusEnum.canFinishOrder(resp.getOrderStatus())) {
            mLlActions.setVisibility(View.GONE);
            mTvSubmit.setVisibility(View.VISIBLE);
            mTvSubmit.setText("完成");
        } else if (type == MyOrderAdapter.TYPE_RECV && resp.getOrderStatus() == OrderStatusEnum.BE_CONFIRM.getValue()) {
            mLlActions.setVisibility(View.VISIBLE);
            mTvSubmit.setVisibility(View.GONE);
        } else if (type == MyOrderAdapter.TYPE_RECV && resp.getOrderStatus() == OrderStatusEnum.BE_SERVE.getValue()) {
            mLlActions.setVisibility(View.GONE);
            mTvSubmit.setVisibility(View.VISIBLE);
            mTvSubmit.setText("立即服务");
        }

    }

    @Override
    public void confirmOrder() {
        finish();
    }

    @Override
    public void accompanyAccept(UpdateOrderModel model) {
        //接单则切换为立即服务
        if (model.getType() == 1) {
            mOrderDetailResp.setOrderStatus(OrderStatusEnum.BE_SERVE.getValue());
            mLlActions.setVisibility(View.GONE);
            mTvSubmit.setVisibility(View.VISIBLE);
            mTvSubmit.setText("立即服务");
        } else {
            //拒绝结束当前页面
            finish();
        }
    }

    @Override
    public void accompanyService() {
        finish();
        chat2User();
    }
}
