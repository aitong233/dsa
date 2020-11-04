package com.spadea.xqipao.ui.room.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.easeui.utils.view.GradeView;
import com.hyphenate.easeui.utils.view.JueView;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.OnlineModel;


public class OnLineAdapter extends BaseQuickAdapter<OnlineModel, BaseViewHolder> {


    public OnLineAdapter() {
        super(R.layout.item_online);
    }

    @Override
    protected void convert(BaseViewHolder helper, OnlineModel item) {
        ImageLoader.loadHead(mContext, helper.getView(R.id.riv), item.getHead_picture());
//        if (!TextUtils.isEmpty(item.getRank_info().getPicture())) {
//            ImageLoader.loadImage(mContext, helper.getView(R.id.iv_img), item.getRank_info().getPicture());
//            helper.setVisible(R.id.iv_img, true);
//        } else {
//            helper.setVisible(R.id.iv_img, false);
//        }
        helper.setText(R.id.tv_user_name, item.getNickname())
                .setText(R.id.tv_user_id, "ID：" + item.getUser_code());
        GradeView gradeView = helper.getView(R.id.view_grade);
        JueView jueView = helper.getView(R.id.view_jue);
        gradeView.setGrade(item.getRank_info().getRank_id(), item.getRank_info().getRank_name());
        jueView.setJue(item.getRank_info().getNobility_id(), item.getRank_info().getNobility_name());

        ImageView ivRole = helper.getView(R.id.iv_role);
        ivRole.setVisibility(View.VISIBLE);
        helper.setVisible(R.id.iv_user_new, item.getUser_is_new() == 1);
        switch (item.getRole()) {
            case 1:
                ivRole.setImageResource(R.mipmap.img_host);
                break;
            case 2:
                ivRole.setImageResource(R.mipmap.img_admin);
                break;
            case 3:
                ivRole.setVisibility(View.GONE);
                break;
            case 5:
                ivRole.setImageResource(R.mipmap.img_official);
                break;
        }
    }
}
