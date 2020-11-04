package com.spadea.xqipao.ui.me.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.yuyin.R;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.MyOrderSwitch;
import com.spadea.xqipao.data.SkillPriceSet;
import com.spadea.xqipao.data.SkillSetting;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.ApplySettingContacts;
import com.spadea.xqipao.ui.me.presenter.ApplySettingPresenter;
import com.spadea.xqipao.ui.me.adapter.SkillSettingAdapter;
import com.spadea.xqipao.ui.me.dialog.widget.dialog.WheelDialogFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.APPLY_SETTING, name = "资质设置")
public class ApplySettingActivity extends BaseActivity<ApplySettingPresenter> implements ApplySettingContacts.View, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_noti)
    TextView mTvNoti;
    @BindView(R.id.view_noti)
    View mViewNoti;
    @BindView(R.id.line_noti)
    View mLineNoti;
    @BindView(R.id.cb_yy)
    CheckBox mCbYy;
    @BindView(R.id.tv_auth_title)
    TextView mTvAuthTitle;
    @BindView(R.id.tv_auth_subtitle)
    TextView mTvAuthSubtitle;
    @BindView(R.id.cb_auth)
    CheckBox mCbAuth;
    private SkillSettingAdapter mAdapter;

    private MyOrderSwitch mMyOrderSwitch;

    public ApplySettingActivity() {
        super(R.layout.activity_apply_setting);
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SkillSettingAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
        MvpPre.getSkillList();
        MvpPre.getOrderSwitch();
    }

    @Override
    protected void initView() {
        mTvTitle.setText("资质设置");
    }

    @Override
    protected ApplySettingPresenter bindPresenter() {
        return new ApplySettingPresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @OnClick({R.id.iv_back, R.id.tv_add, R.id.cb_yy, R.id.cb_auth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add:
                ARouter.getInstance().build(ARouters.SKILL_SELECT).navigation();
                break;
            case R.id.cb_yy:
            case R.id.cb_auth:
                mMyOrderSwitch.setFastAnswer(mCbYy.isChecked() ? 1 : 0);
                mMyOrderSwitch.setForbidSomeone(mCbAuth.isChecked() ? 1 : 0);
                MvpPre.updateOrderSwitch(mMyOrderSwitch);
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        SkillSetting item = mAdapter.getItem(position);
        if (item != null) {
            MvpPre.getSkillPriceList(item.getSkillId(), item.getApplyId());
        }
    }

    @Override
    public void forbidUnAuthSuccess(boolean forbid) {
        mCbAuth.setChecked(forbid);
    }

    @Override
    public void fastAnswerSuccess(boolean fastAnswer) {
        mCbYy.setChecked(fastAnswer);
    }

    @Override
    public void myOrderSwitch(MyOrderSwitch orderSwitch) {
        mMyOrderSwitch = orderSwitch;
        mCbYy.setChecked(orderSwitch.getFastAnswer() == 1);
        mCbAuth.setChecked(orderSwitch.getForbidSomeone() == 1);
    }

    @Override
    public void skillList(List<SkillSetting> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void skillPriceList(List<String> list, int applyId) {
        showWheelDialog(list, applyId);
    }

    private void showWheelDialog(List<String> list, int applyId) {
        if (list == null || list.size() == 0) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(WheelDialogFragment.DIALOG_BACK, true);
        bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE, true);
        bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
        bundle.putString(WheelDialogFragment.DIALOG_LEFT, "取消");
        bundle.putString(WheelDialogFragment.DIALOG_RIGHT, "确定");
        String[] items = new String[list.size()];
        list.toArray(items);
        bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, items);

        WheelDialogFragment dialogFragment = WheelDialogFragment.newInstance(WheelDialogFragment.class, bundle);
        dialogFragment.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {
            @Override
            public void onClickLeft(DialogFragment dialog, String value) {
                dialog.dismiss();
            }

            @Override
            public void onClickRight(DialogFragment dialog, String value) {
                dialog.dismiss();
                EventBus.getDefault().post(new SkillPriceSet(applyId, value, 1));
            }

            @Override
            public void onValueChanged(DialogFragment dialog, String value) {
            }
        });

        dialogFragment.show(getSupportFragmentManager(), "");
    }

    @Override
    public void updatePriceSuccess() {
        MvpPre.getSkillList();
    }

    @Override
    public void updateOrderSwitchSuccess(boolean checkFastAnswer, boolean checkedAuth) {
        mCbYy.setChecked(checkFastAnswer);
        mCbAuth.setChecked(checkedAuth);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updatePrice(SkillPriceSet event) {
        MvpPre.updateSkillPrice(event);
    }
}
