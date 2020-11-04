package com.spadea.xqipao.ui.me.activity;


import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.NobilityInfo;
import com.spadea.xqipao.data.NobilityModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.JueUpgradeContacts;
import com.spadea.xqipao.ui.me.presenter.JueUpgradePresenteer;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.me.adapter.JueUpgradeAfapter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = ARouters.ME_JUEUPGRADE, name = "爵位升级")
public class JueUpgradeActivity extends BaseActivity<JueUpgradePresenteer> implements JueUpgradeContacts.View {

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
    @BindView(R.id.riv)
    RoundedImageView riv;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_reward)
    TextView tvReward;
    @BindView(R.id.tv_upgrade)
    TextView tvUpgrade;

    private JueUpgradeAfapter jueUpgradeAfapter;
    private NobilityModel item;

    public JueUpgradeActivity() {
        super(R.layout.activity_jue_upgrade);
    }

    @Override
    protected void initData() {
        MvpPre.userNobilityInfo();
        MvpPre.nobility();
    }

    @Override
    protected void initView() {
        CommonEmptyView commonEmptyView = new CommonEmptyView(this);
        tvTitle.setText("爵位充值");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(jueUpgradeAfapter = new JueUpgradeAfapter());
        jueUpgradeAfapter.setEmptyView(commonEmptyView);
    }

    @Override
    protected JueUpgradePresenteer bindPresenter() {
        return new JueUpgradePresenteer(this, this);
    }

    @Override
    protected void setListener() {
        super.setListener();
        jueUpgradeAfapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {


            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                item = jueUpgradeAfapter.getItem(position);
                jueUpgradeAfapter.setIndex(position);
                tvUpgrade.setText("立即支付" + item.getPrice() + "金币");
                SpanUtils spanUtils = new SpanUtils();
                spanUtils.append("开通成功").setForegroundColor(Color.parseColor("#FF999999"));
                spanUtils.append("奖励" + item.getRebate() + "金币").setForegroundColor(Color.parseColor("#FFFF8890"));
                spanUtils.append("，立即到账").setForegroundColor(Color.parseColor("#FF999999"));
                tvReward.setText(spanUtils.create());
            }
        });
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_back, R.id.tv_upgrade})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_upgrade:
                if (item == null) {
                    ToastUtils.showShort("请选择要购买的爵位");
                    return;
                }
                MvpPre.buyNobility(item.getId());
                break;
        }
    }

    @Override
    public void nobilitySuccess(List<NobilityModel> data) {
        jueUpgradeAfapter.setNewData(data);
    }

    @Override
    public void userNobilityInfoSuccess(NobilityInfo data) {
        ImageLoader.loadHead(this, riv, data.getInfo().getHead_picture());
        tvNickName.setText(data.getInfo().getNickname());
        if (TextUtils.isEmpty(data.getInfo().getExpired_time())) {
            tvTime.setVisibility(View.GONE);
        } else {
            tvTime.setText(data.getInfo().getNobility_name() + " 有效期至：" + data.getInfo().getExpired_time());
        }
    }

    @Override
    public void buyNobilitySuccess() {
        ToastUtils.showShort("爵位购买成功");
        finish();
    }
}
