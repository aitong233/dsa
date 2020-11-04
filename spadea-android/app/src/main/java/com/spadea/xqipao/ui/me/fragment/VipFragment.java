package com.spadea.xqipao.ui.me.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spadea.xqipao.data.VipInfo;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.me.contacter.VipContacts;
import com.spadea.xqipao.ui.me.presenter.VipPresenter;
import com.spadea.xqipao.utils.view.VipView;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;

public class VipFragment extends BaseFragment<VipPresenter> implements VipContacts.View {


    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.vipview)
    VipView vipview;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.riv)
    RoundedImageView riv;
    @BindView(R.id.ll_level)
    LinearLayout llLevel;
    @BindView(R.id.tv_exp)
    TextView tvExp;


    public static Fragment newInstance() {
        VipFragment vipFragment = new VipFragment();
        return vipFragment;
    }


    @Override
    protected VipPresenter bindPresenter() {
        return new VipPresenter(this, mContext);
    }

    @Override
    protected void initData() {
        MvpPre.vipInfo();
    }

    @Override
    protected void initView(View rootView) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vips;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Override
    public void vipInfoSuccess(VipInfo vipInfo) {
        ImageLoader.loadHead(mContext, riv, vipInfo.getHead_picture());
        if (vipInfo.getDiff().equals("0")) {
            llLevel.setVisibility(View.GONE);
        } else {
            llLevel.setVisibility(View.VISIBLE);
            tvText.setText(vipInfo.getDiff());
        }
        tvNickName.setText(vipInfo.getNickname());
        vipview.setCurrentLevel(vipInfo.getCurrent(), vipInfo.getRank_id());
        vipview.setNextLevel(vipInfo.getNext(), vipInfo.getRank_id());
        vipview.setSeekbar(vipInfo.getPercent());
        tvExp.setText("当前经验值:" + vipInfo.getExp());
    }


}
