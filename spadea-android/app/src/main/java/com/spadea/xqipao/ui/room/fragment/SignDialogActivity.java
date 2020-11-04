package com.spadea.xqipao.ui.room.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.PreferencesUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.SignHistoryResp;
import com.spadea.xqipao.ui.room.presenter.SignPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.room.contacts.SignContacts;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.fragment
 * 创建人 王欧
 * 创建时间 2020/4/25 12:14 PM
 * 描述 describe
 */
@Route(path = ARouters.SIGN, name = "签到弹窗")
public class SignDialogActivity extends BaseActivity<SignPresenter> implements SignContacts.View {
    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.iv_title)
    ImageView mIvTitle;
    @BindView(R.id.tv_current)
    TextView mTvCurrent;
    @BindView(R.id.iv_day1)
    ImageView mIvDay1;
    @BindView(R.id.iv_day2)
    ImageView mIvDay2;
    @BindView(R.id.iv_day3)
    ImageView mIvDay3;
    @BindView(R.id.iv_day4)
    ImageView mIvDay4;
    @BindView(R.id.iv_day5)
    ImageView mIvDay5;
    @BindView(R.id.iv_day6)
    ImageView mIvDay6;
    @BindView(R.id.iv_day7)
    ImageView mIvDay7;
    @BindView(R.id.iv_action)
    ImageView mIvAction;
    @BindView(R.id.view_bottom)
    ImageView mViewBottom;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.iv_reward_1)
    ImageView mIvReward1;
    @BindView(R.id.iv_reward_cover_1)
    ImageView mIvRewardCover1;
    @BindView(R.id.iv_reward_2)
    ImageView mIvReward2;
    @BindView(R.id.iv_reward_cover_2)
    ImageView mIvRewardCover2;
    @BindView(R.id.iv_reward_3)
    ImageView mIvReward3;
    @BindView(R.id.iv_reward_cover_3)
    ImageView mIvRewardCover3;
    @BindView(R.id.tv_reward1)
    TextView mTvReward1;
    @BindView(R.id.tv_reward_name1)
    TextView mTvRewardName1;
    @BindView(R.id.tv_reward_2)
    TextView mTvReward2;
    @BindView(R.id.tv_reward_name2)
    TextView mTvRewardName2;
    @BindView(R.id.tv_reward3)
    TextView mTvReward3;
    @BindView(R.id.tv_reward_name3)
    TextView mTvRewardName3;
    private boolean mTodaySigned;

    public SignDialogActivity() {
        super(R.layout.activity_dialog_sign);
    }

    @Override
    protected void initData() {
        PreferencesUtils.putBoolean(this, TimeUtils.getNowString(new SimpleDateFormat("yyyyMMdd")), true);
        MvpPre.getSignHistory();
        MvpPre.getRewordData();
    }

    @Override
    protected void initView() {
        setFinishOnTouchOutside(true);
        BarUtils.setNavBarVisibility(this, false);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (int) (ScreenUtils.getScreenWidth() * 328.0 / 375);
        getWindow().setAttributes(lp);
        setTitle("");
    }


    @Override
    protected SignPresenter bindPresenter() {
        return new SignPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_action, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_action:
                if (!mTodaySigned) {
                    MvpPre.signIn();
                }
                break;
            case R.id.iv_close:
                finish();
                break;
        }
    }

    @Override
    public void signInSuccess() {
        MvpPre.getSignHistory();
        MvpPre.getRewordData();
    }

    @Override
    public void signHistory(SignHistoryResp resp) {
        mProgressBar.setProgress((int) (resp.getTotal() / 7.0 * 100));
        mTvCurrent.setText(new SpanUtils().append("已连续签到  ").append(String.valueOf(resp.getTotal())).setForegroundColor(Color.parseColor("#FE611C")).append("  天").create());
        mTodaySigned = resp.getToday() == 1;
        mIvAction.setImageResource(mTodaySigned ? R.mipmap.ic_signed_action : R.mipmap.ic_sign_action);
        List<SignHistoryResp.Data> list = resp.getList();
        if (list.size() < 7) {
            finish();
            return;
        }
        ImageLoader.loadImage(this, mIvDay1, list.get(0).getPicture());
        ImageLoader.loadImage(this, mIvDay2, list.get(1).getPicture());
        ImageLoader.loadImage(this, mIvDay3, list.get(2).getPicture());
        ImageLoader.loadImage(this, mIvDay4, list.get(3).getPicture());
        ImageLoader.loadImage(this, mIvDay5, list.get(4).getPicture());
        ImageLoader.loadImage(this, mIvDay6, list.get(5).getPicture());
        ImageLoader.loadImage(this, mIvDay7, list.get(6).getPicture());
    }

    @Override
    public void rewardList(List<SignHistoryResp.RewardData> list) {
        if (list != null && list.size() > 2) {
            mTvReward1.setText(String.format("使用%s天", list.get(0).getValue()));
            mTvReward2.setText(String.format("使用%s天", list.get(1).getValue()));
            mTvReward3.setText(String.format("使用%s天", list.get(2).getValue()));
            mTvRewardName1.setText(list.get(0).getName());
            mTvRewardName2.setText(list.get(1).getName());
            mTvRewardName3.setText(list.get(2).getName());
            ImageLoader.loadImage(this, mIvReward1, list.get(0).getPicture());
            ImageLoader.loadImage(this, mIvReward2, list.get(1).getPicture());
            ImageLoader.loadImage(this, mIvReward3, list.get(2).getPicture());
        }
    }
}
