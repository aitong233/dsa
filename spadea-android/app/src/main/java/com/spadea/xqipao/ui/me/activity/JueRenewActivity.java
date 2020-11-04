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
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.JueRenewContacts;
import com.spadea.xqipao.ui.me.presenter.JueRenewPresenter;
import com.spadea.xqipao.ui.me.adapter.JueRenewAdapter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.ME_JUERENEW, name = "爵位续费")
public class JueRenewActivity extends BaseActivity<JueRenewPresenter> implements JueRenewContacts.View {

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


    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.tv_renew)
    TextView tvRenew;

    private JueRenewAdapter jueRenewAdapter;
    private NobilityInfo.RenewBean item;

    public JueRenewActivity() {
        super(R.layout.activity_jue_renew);
    }

    @Override
    protected void initData() {
        MvpPre.userNobilityInfo();
    }

    @Override
    protected void initView() {
        tvTitle.setText("爵位续费");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(jueRenewAdapter = new JueRenewAdapter());
    }

    @Override
    protected void setListener() {
        super.setListener();
        jueRenewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                item = jueRenewAdapter.getItem(position);
                jueRenewAdapter.setIndex(position);
                tvRenew.setText("立即支付" + item.getPrice() + "金币");
                SpanUtils spanUtils = new SpanUtils();
                spanUtils.append("续费成功").setForegroundColor(Color.parseColor("#FF999999"));
                spanUtils.append("奖励" + item.getRebate() + "金币").setForegroundColor(Color.parseColor("#FFFF8890"));
                spanUtils.append("，立即到账").setForegroundColor(Color.parseColor("#FF999999"));
                tvText.setText(spanUtils.create());
            }
        });
    }

    @Override
    protected JueRenewPresenter bindPresenter() {
        return new JueRenewPresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @OnClick({R.id.iv_back, R.id.tv_renew})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_renew:
                if (item == null) {
                    ToastUtils.showShort("请选择续费的爵位");
                    return;
                }
                MvpPre.renewNobility(item.getDay());
                break;
        }
    }

    @Override
    public void userNobilityInfoSuccess(NobilityInfo data) {
        ImageLoader.loadImage(this, riv, data.getInfo().getHead_picture());
        tvNickName.setText(data.getInfo().getNickname());
        if (TextUtils.isEmpty(data.getInfo().getExpired_time())) {
            tvTime.setVisibility(View.GONE);
        } else {
            tvTime.setText(data.getInfo().getNobility_name() + " 有效期至：" + data.getInfo().getExpired_time());
        }
        jueRenewAdapter.setNobilityPicture(data.getInfo().getNobility_picture());
        jueRenewAdapter.setNewData(data.getRenew());
    }

    @Override
    public void renewNobilitySuccess() {
        ToastUtils.showShort("续费成功");
        finish();
    }


}
