package com.spadea.xqipao.ui.order.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
import com.spadea.yuyin.util.utilcode.ConvertUtils;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.yuyin.util.utilcode.TimeUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.AddOrderModel;
import com.spadea.xqipao.data.OrderPayModel;
import com.spadea.xqipao.data.OrderSkillSelectItem;
import com.spadea.xqipao.data.UserSkillInfo;
import com.spadea.xqipao.data.VerifyOrderTimeModel;
import com.spadea.xqipao.ui.order.contacts.ConfirmOrderContacts;
import com.spadea.xqipao.ui.order.presenter.ConfirmOrderPresenter;
import com.spadea.xqipao.widget.dialog.CustomAlertDialog;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.dialog.OrderNoBalanceDialog;
import com.spadea.xqipao.ui.me.dialog.SkillChooseDialog;
import com.spadea.xqipao.ui.order.dialog.OrderTimePicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.CONFIRM_ORDER, name = "确认订单")
public class ConfirmOrderActivity extends BaseActivity<ConfirmOrderPresenter> implements ConfirmOrderContacts.View {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.riv)
    RoundedImageView mRiv;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_skill)
    TextView mTvSkill;
    @BindView(R.id.ll_skill)
    LinearLayout mLlSkill;
    @BindView(R.id.tv_order_count)
    TextView mTvOrderCount;
    @BindView(R.id.ll_count)
    LinearLayout mLlCount;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.ll_time)
    LinearLayout mLlTime;
    @BindView(R.id.tv_coupon)
    TextView mTvCoupon;
    @BindView(R.id.ll_coupon)
    LinearLayout mLlCoupon;
    @BindView(R.id.tv_total)
    TextView mTvTotal;
    @BindView(R.id.ll_total)
    LinearLayout mLlTotal;
    @BindView(R.id.tv_exp_title)
    TextView mTvExpTitle;
    @BindView(R.id.tv_exp_tip)
    TextView mTvExpTip;
    @BindView(R.id.et_exp)
    EditText mEtExp;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.iv_gender)
    ImageView mIvGender;
    @BindView(R.id.tv_time_count)
    TextView mTvTimeCount;
    @BindView(R.id.tv_num_total)
    TextView mTvNumTotal;

    public UserSkillInfo mUserSkillInfo;

    @Autowired
    public String userId;

    @Autowired
    public int id;

    private int orderCount = 1;

    private CountDownTimer mCountDownTimer;

    private String orderId;

    private String serviceTime;

    public ConfirmOrderActivity() {
        super(R.layout.activity_confirm_order);
    }

    @Override
    protected void initData() {
        MvpPre.getSkillInfo(userId, id);
    }

    private void setTotal() {
        try {
            int count = Integer.parseInt(mTvOrderCount.getText().toString());
            mTvNumTotal.setText(String.format("数量:X%s", count));
            mTvTotal.setText(new SpanUtils().append("小计").appendSpace(ConvertUtils.dp2px(6)).append(String.valueOf(count * mUserSkillInfo.getPrice())).setFontSize(18, true).setForegroundColor(getResources().getColor(R.color.color_main)).setBold().appendSpace(ConvertUtils.dp2px(2)).append("黑桃A").setForegroundColor(getResources().getColor(R.color.color_main)).create());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {
        mTvTitle.setText("确认订单");
        mEtExp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvExpTip.setText(String.format("%s/50个字", s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected ConfirmOrderPresenter bindPresenter() {
        return new ConfirmOrderPresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @Override
    public void onBackPressed() {
        new CustomAlertDialog(this, "离完成就差一步，真的要退出吗？", "再想想", "确认", new CustomAlertDialog.CustomDialogListener() {
            @Override
            public void onConfirm() {
                ConfirmOrderActivity.super.onBackPressed();
            }

            @Override
            public void onCancel() {

            }
        }).show();
    }

    @OnClick({R.id.iv_back, R.id.ll_skill, R.id.ll_count, R.id.ll_time, R.id.ll_coupon, R.id.ll_total, R.id.tv_submit, R.id.iv_count_reduce, R.id.iv_count_increase})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.ll_skill:
                MvpPre.getSkillList(userId);
                break;
            case R.id.ll_count:
                break;
            case R.id.ll_time:
                showTimePicker();
                break;
            case R.id.ll_coupon:
                break;
            case R.id.ll_total:
                break;
            case R.id.tv_submit:
                if (TextUtils.isEmpty(orderId)) {
                    createOrder();
                } else {
                    MvpPre.pay(new OrderPayModel(orderId));
                }
                break;
            case R.id.iv_count_increase:
                orderCount++;
                mTvOrderCount.setText(String.valueOf(orderCount));
                setTotal();
                break;
            case R.id.iv_count_reduce:
                if (orderCount > 1) {
                    orderCount--;
                    mTvOrderCount.setText(String.valueOf(orderCount));
                }
                setTotal();
                break;
        }
    }

    private void showTimePicker() {
        new OrderTimePicker(this, new OrderTimePicker.OnSelectedListener() {
            @Override
            public void onSelected(long time, String timeStr) {
                if (mUserSkillInfo == null) {
                    MvpPre.getSkillInfo(userId, id);
                    return;
                }
                serviceTime = String.valueOf(time / 1000);
                VerifyOrderTimeModel model = new VerifyOrderTimeModel();
                int number = Integer.parseInt(mTvOrderCount.getText().toString());
                model.setNumber(number);
                model.setSkillId(mUserSkillInfo.getSkillId());
                model.setTimeStart(serviceTime);
                model.setUserId(String.valueOf(mUserSkillInfo.getUserId()));
                MvpPre.verifyTime(model, timeStr);
            }
        }).show();
    }

    private void createOrder() {
        if (mUserSkillInfo == null) {
            MvpPre.getSkillInfo(userId, id);
            return;
        }
        if (TextUtils.isEmpty(serviceTime)) {
            ToastUtils.showShort("请选择服务时间");
            return;
        }
        String remark = mEtExp.getText().toString();
        AddOrderModel addOrderModel = new AddOrderModel();
        addOrderModel.setApplyId(mUserSkillInfo.getId());
        int number = Integer.parseInt(mTvOrderCount.getText().toString());
        addOrderModel.setNumber(number);
        addOrderModel.setRemarks(remark);
        addOrderModel.setServiceTime(serviceTime);
        MvpPre.createOrder(addOrderModel, mTvSubmit);
    }

    @Override
    public void skillList(List<OrderSkillSelectItem> list) {
        new SkillChooseDialog(this, list).show();
    }

    @Override
    public void skillInfo(UserSkillInfo skillInfo) {
        mUserSkillInfo = skillInfo;
        ImageLoader.loadImage(this, mRiv, mUserSkillInfo.getHeadPicture());
        mTvUserName.setText(mUserSkillInfo.getNickname());
        if (mUserSkillInfo.getSex() == 1) {
            mIvGender.setImageResource(R.mipmap.ic_order_female);
        } else {
            mIvGender.setImageResource(R.mipmap.ic_order_male);
        }
        mTvPrice.setText(new SpanUtils().append(skillInfo.getPrice() + "黑桃A").setForegroundColor(getResources().getColor(R.color.color_main)).append("/").append(skillInfo.getCompany()).create());
        mTvSkill.setText(skillInfo.getSkillName());
        setTotal();
    }

    @Override
    public void orderCreated(String resp) {
        countDownTimer();
        orderId = resp;
        MvpPre.pay(new OrderPayModel(orderId));
    }

    private void countDownTimer() {
        releaseTimer();
        mCountDownTimer = new CountDownTimer(15 * 60 * 1000L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvTimeCount.setVisibility(View.VISIBLE);
                mTvTimeCount.setText(new SpanUtils().append(TimeUtils.millis2String(millisUntilFinished, new SimpleDateFormat("mm分钟ss秒"))).setForegroundColor(MyApplication.getInstance().getResources().getColor(R.color.color_main)).append("后，如果您未付款，订单将自动关闭").create());
            }

            @Override
            public void onFinish() {
                mTvTimeCount.setVisibility(View.GONE);
            }
        };
        mCountDownTimer.start();
    }

    @Override
    public void timeVerified(VerifyOrderTimeModel model, String timeStr) {
        mTvTime.setText(timeStr);
    }

    @Override
    public void paySuccess() {
        finish();
        if (MyApplication.getInstance().notSelf(String.valueOf(mUserSkillInfo.getUserId()))) {
            ARouter.getInstance().build(ARouteConstants.HOME_CHART)
                    .withString("userId", mUserSkillInfo.getEmchatUsername())
                    .withString("nickname", mUserSkillInfo.getNickname())
                    .withString("avatar", mUserSkillInfo.getHeadPicture())
                    .navigation();
        }
    }

    @Override
    public void showNoBalance(String balance) {
        new OrderNoBalanceDialog(this, balance).show();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        releaseTimer();
        super.onDestroy();
    }

    private void releaseTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectSkill(OrderSkillSelectItem skillSelectItem) {
        MvpPre.getSkillInfo(userId, skillSelectItem.getId());
    }
}
