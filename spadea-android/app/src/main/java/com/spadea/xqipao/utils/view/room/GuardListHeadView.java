package com.spadea.xqipao.utils.view.room;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.ProtectedRankingItemBean;
import com.spadea.xqipao.ui.room.adapter.GuardMedalAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.view.room
 * 创建人 王欧
 * 创建时间 2020/4/2 3:40 PM
 * 描述 describe
 */
public class GuardListHeadView extends LinearLayout {
    @BindView(R.id.riv_avatar)
    RoundedImageView mRivAvatar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;

    public GuardListHeadView(Context context, ProtectedRankingItemBean itemBean) {
        super(context);
        initView(context, itemBean);
    }

    private void initView(Context context, ProtectedRankingItemBean itemBean) {
        LayoutInflater.from(context).inflate(R.layout.header_guard_list, this, true);
        ButterKnife.bind(this);
        ImageLoader.loadHead(MyApplication.getInstance(), mRivAvatar, itemBean.getHead_picture());
        mTvName.setText(itemBean.getNickname());
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId",itemBean.getUser_id()).navigation();
            }
        });
        mTvDesc.setText(String.format("%s位：剩余%s天", itemBean.getType_name(), itemBean.getDays()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(new GuardMedalAdapter(itemBean.getProtect_info()));
    }
}
