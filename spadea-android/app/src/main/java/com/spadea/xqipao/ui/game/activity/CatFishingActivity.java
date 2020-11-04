package com.spadea.xqipao.ui.game.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.FishInfoBean;
import com.spadea.xqipao.ui.game.contacts.CatFishingContacts;
import com.spadea.xqipao.ui.game.presenter.CatFishingPresenter;
import com.spadea.xqipao.utils.view.CircularProgressView;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.activity.BalanceActivity;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.CAT_FISH_GAME)
public class CatFishingActivity extends BaseActivity<CatFishingPresenter> implements CatFishingContacts.View {

    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.circular_progress_view)
    CircularProgressView circularProgressView;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.iv_img)
    ImageView ivImg;

    private int num = 0;

    private double balance;

    private double price;


    public CatFishingActivity() {
        super(R.layout.activity_fishing);
    }


    public static void start(Context context) {
        context.startActivity(new Intent(context, CatFishingActivity.class));
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MvpPre.getFishInfo();
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setAndroidNativeLightStatusBar(this, false);
    }

    @Override
    protected CatFishingPresenter bindPresenter() {
        return new CatFishingPresenter(this, this);
    }

    @Override
    public void showLoadings() {
    }

    @Override
    public void disLoadings() {
    }


    @OnClick({R.id.rl_recharge, R.id.iv_ranking, R.id.iv_help, R.id.iv_jackpot, R.id.iv_100, R.id.iv_1, R.id.iv_10, R.id.rl_start, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_recharge:
                startActivity(new Intent(this, BalanceActivity.class));
                break;
            case R.id.iv_ranking:
                MvpPre.getWinRanking();
                break;
            case R.id.iv_help:
                MvpPre.getCatHelp();
                break;
            case R.id.iv_jackpot:
                MvpPre.getCatWinJackpot();
                break;
            case R.id.iv_100:
                tvText.setText("连钓");
                tvCount.setText("100次");
                num = 100;
                break;
            case R.id.iv_10:
                tvText.setText("连钓");
                tvCount.setText("10次");
                num = 10;
                break;
            case R.id.iv_1:
                tvText.setText("连钓");
                tvCount.setText("1次");
                num = 1;
                break;
            case R.id.rl_start:
                if (num == 0) {
                    ToastUtils.showShort("请选择钓鱼次数");
                    return;
                }
                MvpPre.startFishing(num);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }


    @Override
    public void setFishInfo(FishInfoBean fishInfo) {
//        try {
//            balance = Double.parseDouble(fishInfo.getBalance());
//            price = Double.parseDouble(fishInfo.getPrice());
//        } catch (Exception e) {
//            LogUtils.e("startFishingSuccess", e);
//        }
//        if (!TextUtils.isEmpty(fishInfo.getBalance())) {
//            tvNum.setText(new BigDecimal(fishInfo.getBalance()).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
//        } else {
//            tvNum.setText("0.00");
//        }
    }

    @Override
    public void startFishingSuccess(int num) {
        balance = balance - price * num;
        tvNum.setText(new BigDecimal(balance).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
    }
}
