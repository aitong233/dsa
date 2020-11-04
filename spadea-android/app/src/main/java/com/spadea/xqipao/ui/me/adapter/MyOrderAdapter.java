package com.spadea.xqipao.ui.me.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.OrdersResp;
import com.spadea.xqipao.ui.order.status.OrderEndStatusEnum;
import com.spadea.xqipao.ui.order.status.OrderStatusEnum;
import com.spadea.xqipao.widget.RatingStarView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.adapter
 * 创建人 王欧
 * 创建时间 2020/6/2 4:11 PM
 * 描述 describe
 */
public class MyOrderAdapter extends BaseQuickAdapter<OrdersResp.RecordsBean, BaseViewHolder> {
    public static final int TYPE_SEND = 1;
    public static final int TYPE_RECV = 0;
    private int type;

    public MyOrderAdapter(int type) {
        super(R.layout.rv_item_my_order, null);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrdersResp.RecordsBean item) {
        helper.addOnClickListener(R.id.btn_remark);
        helper.addOnClickListener(R.id.btn_order);
        helper.setText(R.id.tv_status, OrderEndStatusEnum.getEnumByValue(item.getEndStatus()).getDesc());
        helper.setText(R.id.tv_name, item.getSkillName());
        helper.setText(R.id.tv_time, item.getOrderTime());
        ImageLoader.loadImage(mContext, helper.getView(R.id.riv), item.getHeadPicture());
        helper.setText(R.id.tv_price, String.valueOf(item.getTotal()));
        helper.setText(R.id.tv_pay_status, OrderEndStatusEnum.getEnumByValue(item.getEndStatus()).getDesc());
        helper.setText(R.id.tv_num, String.format("%s/%s", item.getNumber(), item.getSkillUnit()));
        RatingStarView ratingStarView = helper.getView(R.id.rating_view);
        helper.setGone(R.id.btn_remark, false);
        helper.setGone(R.id.btn_order, type == TYPE_SEND && item.getEndStatus() == OrderEndStatusEnum.FINISH.getValue());
        if ((type == TYPE_SEND && item.getEvaluationStatus() == 0) || (type == TYPE_RECV && item.getPlayEvaluationStatus() == 0)) {
            ratingStarView.setVisibility(View.GONE);
            if (item.getOrderStatus() == OrderStatusEnum.FINISHED.getValue()) {
                helper.setGone(R.id.btn_remark, true);
            }
        } else {
            ratingStarView.setVisibility(View.VISIBLE);
            ratingStarView.setScore(item.getScoreStar());
        }
        if (item.getEndStatus() == OrderEndStatusEnum.FINISH.getValue()) {
            helper.setTextColor(R.id.tv_pay_status, MyApplication.getInstance().getResources().getColor(R.color.color_808080));
        } else {
            helper.setTextColor(R.id.tv_pay_status, MyApplication.getInstance().getResources().getColor(R.color.color_main));
        }
    }
}
