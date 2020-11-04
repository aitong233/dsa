package com.spadea.xqipao.ui.me.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.bean.RechargeInfoModel;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.RoomRechargeAdapter;
import com.qpyy.room.bean.WxPayModel;
import com.qpyy.room.contacts.RechargeContacter;
import com.qpyy.room.presenter.RechargePresenter;
import com.spadea.yuyin.util.pay.PaymentUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/11 13:55
 * 描述 充值弹窗
 */
@Route(path = ARouteConstants.RECHARGE_DIALOG)
public class RechargeDialogFragment extends BaseMvpDialogFragment<RechargePresenter> implements RechargeContacter.View {

    private static final String TAG = "RechargeDialogFragment";
    @BindView(R2.id.tv_pay_title)
    TextView tvPayTitle;
    @BindView(R2.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R2.id.rv_recharge)
    RecyclerView rvRecharge;
    @BindView(R2.id.rb_wx)
    RadioButton rbWx;
    @BindView(R2.id.rb_zfb)
    RadioButton rbZfb;
    @BindView(R2.id.tv_pay_submit)
    TextView tvPaySubmit;
    @BindView(R2.id.ll_wx)
    LinearLayout llWx;
    @BindView(R2.id.ll_zfb)
    LinearLayout llZfb;

    private String payMoneys = "0";//充值金额(6,30,88,189,388,988)
    private int payType = 1;//支付方式(支付宝)
    private RoomRechargeAdapter adapter = new RoomRechargeAdapter();


    public static RechargeDialogFragment newInstance() {
        Bundle args = new Bundle();
        RechargeDialogFragment fragment = new RechargeDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
//        window.setWindowAnimations(0);//取消动画
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.4f;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    @Override
    protected int getLayoutId() {
        Log.d(TAG, "(Start)启动了===========================RechargeDialogFragment");
        return R.layout.room_dialog_recharge;
    }


    @Override
    protected RechargePresenter bindPresenter() {
        return new RechargePresenter(this, getContext());
    }

    @Override
    public void initData() {
        MvpPre.rechargeInfo();
    }

    @Override
    protected void initView() {
        rvRecharge.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvRecharge.setAdapter(adapter);
        initListener();
    }


    /**
     * 初始化监听
     */
    private void initListener() {
        adapter.setOnItemClickListener((adapter, view, position) -> {
            RechargeDialogFragment.this.adapter.setSelectedPosition(position);
            adapter.notifyDataSetChanged();
            RechargeInfoModel rechargeInfoModel = RechargeDialogFragment.this.adapter.getData().get(position);
            payMoneys = rechargeInfoModel.getMoney();
        });
    }

    /**
     * 重置选择数量的布局颜色
     */
    private void resetView() {
        adapter.setSelectedPosition(-1);
        adapter.notifyDataSetChanged();
    }

    @OnClick({ R2.id.tv_pay_submit})
    public void onViewClicked(View view) {
        resetView();//偷懒了
        int id = view.getId();
        if (id == R.id.tv_pay_submit) {//获取支付方式和充值金额
            MvpPre.userRecharge(payMoneys, payType);
        }
    }

    @Override
    public void userRechargeSuccess(String orderId, int type) {
        if (type == 1) {
            MvpPre.aliPay(orderId, 1);
        } else {
            MvpPre.weixinPay(orderId, 2);
        }
    }

    @Override
    public void aliPayment(String orderNo) {
        PaymentUtil.payAlipay(getContext(), orderNo);
    }

    @Override
    public void weixinPayment(WxPayModel wxPayModel) {

    }

    @Override
    public void rechargeInfoSuccess(ArrayList<RechargeInfoModel> s) {
        adapter.setNewData(s);
    }

}
