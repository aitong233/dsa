package com.spadea.xqipao.ui.me.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.easeui.utils.view.GradeView;
import com.hyphenate.easeui.utils.view.JueView;
import com.spadea.xqipao.data.FriendModel;
import com.qpyy.libcommon.bean.RankInfo;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

public class SelectFriendAdapter extends BaseQuickAdapter<FriendModel, BaseViewHolder> {

    public SelectFriendAdapter() {
        super(R.layout.item_select_friend);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendModel item) {
        ImageLoader.loadHead(mContext, helper.getView(R.id.riv), item.getHead_picture());
        helper.setText(R.id.tv_nickname, item.getNickname());
        if (TextUtils.isEmpty(item.getSignature())) {
            helper.setText(R.id.tv_signature, "暂无签名");
        } else {
            helper.setText(R.id.tv_signature, item.getSignature());
        }
        switch (item.getSex()) {
            case "1":
                helper.setImageResource(R.id.iv_sex, R.drawable.bang_icon_man).setText(R.id.tv_age, item.getAge());
                helper.setBackgroundRes(R.id.ll_sex, R.drawable.bg_boy);
                break;
            case "2":
                helper.setImageResource(R.id.iv_sex, R.drawable.bang_icon_women).setText(R.id.tv_age, item.getAge());
                helper.setBackgroundRes(R.id.ll_sex, R.drawable.bg_girl);
                break;
        }
        RankInfo rank_id = item.getRank_info();
        GradeView gradeView = helper.getView(R.id.view_grade);
        JueView jueView = helper.getView(R.id.view_jue);
        gradeView.setGrade(rank_id.getRank_id(), rank_id.getRank_name());
        jueView.setJue(rank_id.getNobility_id(), rank_id.getNobility_name());
    }


}
