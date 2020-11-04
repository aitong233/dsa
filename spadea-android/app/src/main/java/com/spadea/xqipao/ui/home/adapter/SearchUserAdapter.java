package com.spadea.xqipao.ui.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.easeui.utils.view.GradeView;
import com.hyphenate.easeui.utils.view.JueView;
import com.qpyy.libcommon.bean.RankInfo;
import com.spadea.xqipao.data.SearchUserInfo;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

public class SearchUserAdapter extends BaseQuickAdapter<SearchUserInfo, BaseViewHolder> {

    public SearchUserAdapter() {
        super(R.layout.item_search_user);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchUserInfo item) {
        ImageLoader.loadHead(mContext, helper.getView(R.id.riv), item.getHead_picture());
        helper.setText(R.id.tv_nick_name, item.getNickname());
        if (item.getSex().equals("0")) {
            helper.setVisible(R.id.tv_sex, false);
        } else {
            helper.setVisible(R.id.tv_sex, true);
            if (item.getSex().equals("1")) {
                helper.setText(R.id.tv_sex, "男神").setBackgroundRes(R.id.tv_sex, R.drawable.bg_boy);
            } else {
                helper.setText(R.id.tv_sex, "女神").setBackgroundRes(R.id.tv_sex, R.drawable.bg_girl);
            }
        }

        RankInfo rank_id = item.getRank_info();
        GradeView gradeView = helper.getView(R.id.view_grade);
        JueView jueView = helper.getView(R.id.view_jue);
        gradeView.setGrade(rank_id.getRank_id(), rank_id.getRank_name());
        jueView.setJue(rank_id.getNobility_id(), rank_id.getNobility_name());

    }
}
