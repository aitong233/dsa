package com.spadea.xqipao.ui.room.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.easeui.utils.view.GradeView;
import com.hyphenate.easeui.utils.view.JueView;
import com.spadea.xqipao.data.CharmModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;


public class RoomRankingAdapter extends BaseQuickAdapter<CharmModel.ListsBean, BaseViewHolder> {

    // 1 魅力  2 财富
    private int type = 1;

    public RoomRankingAdapter() {
        super(R.layout.item_roomranking);
    }

    @Override
    protected void convert(BaseViewHolder helper, CharmModel.ListsBean item) {
        helper.setText(R.id.tv_postion, String.valueOf(helper.getAdapterPosition() + 1))
                .setText(R.id.tv_user_name, item.getNickname())
                .setText(R.id.tv_num, type == 1 ? "魅力" + item.getNumber() : "财富" + item.getNumber());

        ImageLoader.loadHead(mContext, helper.getView(R.id.riv), item.getHead_picture());
//        if (!TextUtils.isEmpty(item.getRank_info().getPicture())) {
//            ImageLoader.loadImage(mContext, helper.getView(R.id.iv_img), item.getRank_info().getPicture());
//            helper.setVisible(R.id.iv_img, true);
//        } else {
//            helper.setVisible(R.id.iv_img, false);
//        }
        GradeView gradeView = helper.getView(R.id.view_grade);
        JueView jueView = helper.getView(R.id.view_jue);
        gradeView.setGrade(item.getRank_info().getRank_id(), item.getRank_info().getRank_name());
        jueView.setJue(item.getRank_info().getNobility_id(), item.getRank_info().getNobility_name());
    }

    public void setType(int type) {
        this.type = type;
    }
}
