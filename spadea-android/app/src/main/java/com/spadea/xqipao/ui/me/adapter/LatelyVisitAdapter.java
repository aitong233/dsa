package com.spadea.xqipao.ui.me.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.easeui.utils.view.GradeView;
import com.hyphenate.easeui.utils.view.JueView;
import com.spadea.xqipao.data.LatelyVisitInfo;
import com.qpyy.libcommon.bean.RankInfo;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

public class LatelyVisitAdapter extends BaseQuickAdapter<LatelyVisitInfo, BaseViewHolder> {


    public LatelyVisitAdapter() {
        super(R.layout.item_lately_visit);
    }

    @Override
    protected void convert(BaseViewHolder helper, LatelyVisitInfo item) {
        ImageLoader.loadHead(mContext, helper.getView(R.id.riv), item.getHead_picture());
        helper.setText(R.id.tv_name, item.getNickname())
                .setText(R.id.tv_content, item.getSignature())
                .setText(R.id.tv_time, item.getAdd_time());
        RankInfo rank_info = item.getRank_info();
        GradeView gradeView = helper.getView(R.id.gradeview);
        JueView jueview = helper.getView(R.id.jueview);
        gradeView.setGrade(rank_info.getRank_id(), rank_info.getRank_name());
        jueview.setJue(rank_info.getNobility_id(), rank_info.getNobility_name());
        switch (item.getSex()) {
            case "1":
                helper.setImageResource(R.id.iv_sex, R.drawable.bang_icon_man)
                        .setText(R.id.tv_age, String.valueOf(item.getAge()))
                        .setBackgroundRes(R.id.ll_sex, R.drawable.bg_boy);
                break;
            case "2":
                helper.setImageResource(R.id.iv_sex, R.drawable.bang_icon_women)
                        .setText(R.id.tv_age, String.valueOf(item.getAge()))
                        .setBackgroundRes(R.id.ll_sex, R.drawable.bg_girl);
                break;
        }
    }

}
