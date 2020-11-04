package com.spadea.xqipao.ui.order.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.ConvertUtils;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.xqipao.data.OrderMsgResp;
import com.spadea.xqipao.ui.me.adapter.MyOrderAdapter;
import com.spadea.xqipao.ui.order.status.OrderStatusEnum;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.order.adapter
 * 创建人 王欧
 * 创建时间 2020/6/3 11:21 AM
 * 描述 describe
 */
public class OrderNewsAdapter extends BaseQuickAdapter<OrderMsgResp.RecordsBean, BaseViewHolder> {
    public OrderNewsAdapter() {
        super(R.layout.rv_item_order_news, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderMsgResp.RecordsBean item) {
        ImageLoader.loadImage(mContext, helper.getView(R.id.image), item.getHeadPicture());
        helper.setText(R.id.tv_skill_name, item.getSkillName());
        helper.setText(R.id.tv_remark, item.getRemark());
        helper.setText(R.id.tv_time, item.getOrderTime());
        helper.setText(R.id.tv_msg, item.getType() == MyOrderAdapter.TYPE_RECV ? item.getUserDesc() : item.getPlayUserDesc());
        helper.setText(R.id.tv_money, new SpanUtils().append(String.valueOf(item.getTotal())).setBold().appendSpace(2, ConvertUtils.dp2px(2)).append("币").setFontSize(12, true).setForegroundColor(MyApplication.getInstance().getResources().getColor(R.color.color_545454)).create());
        helper.addOnClickListener(R.id.tv_accept);
        helper.addOnClickListener(R.id.tv_reject);
        helper.addOnClickListener(R.id.view_info);
        helper.setGone(R.id.tv_end_state, false);
        helper.setGone(R.id.tv_accept, false);
        helper.setGone(R.id.tv_reject, false);
//        helper.setVisible(R.id.tv_jd, (item.getType() == MyOrderAdapter.TYPE_RECV && !TextUtils.isEmpty(item.getUserDesc())) || (item.getType() == MyOrderAdapter.TYPE_SEND && !TextUtils.isEmpty(item.getPlayUserDesc())));
        helper.setText(R.id.tv_jd, item.getType() == MyOrderAdapter.TYPE_RECV ? "接单:" : "邀请:");
        if (item.getType() == MyOrderAdapter.TYPE_RECV) {
            if (item.getOrderStatus() == OrderStatusEnum.BE_CONFIRM.getValue()) {
                helper.setGone(R.id.tv_accept, true);
                helper.setGone(R.id.tv_reject, true);
                helper.setText(R.id.tv_accept, "接单");
                helper.setText(R.id.tv_reject, "拒绝");
            } else if (item.getOrderStatus() == OrderStatusEnum.REJECTED.getValue()) {
                helper.setGone(R.id.tv_end_state, true);
            } else if (item.getOrderStatus() == OrderStatusEnum.BE_SERVE.getValue()) {
                helper.setGone(R.id.tv_end_state, false);
                helper.setGone(R.id.tv_accept, true);
                helper.setText(R.id.tv_accept, "立即服务");
            } else if (OrderStatusEnum.canEvaluateOrder(item.getOrderStatus())) {
                helper.setGone(R.id.tv_end_state, false);
                helper.setGone(R.id.tv_accept, true);
                helper.setText(R.id.tv_accept, "评价");
            } else if (item.getOrderStatus() == OrderStatusEnum.REFUNDING.getValue()) {
                helper.setGone(R.id.tv_end_state, false);
                helper.setGone(R.id.tv_reject, true);
                helper.setGone(R.id.tv_accept, true);
                helper.setText(R.id.tv_reject, "拒绝");
                helper.setText(R.id.tv_accept, "同意");
            } else if (item.getOrderStatus() == OrderStatusEnum.APPEALING.getValue()) {
                helper.setGone(R.id.tv_end_state, false);
                helper.setGone(R.id.tv_accept, true);
                helper.setText(R.id.tv_accept, "去处理");
            }
        } else {
            if (item.getOrderStatus() == OrderStatusEnum.FIRST_SERVE.getValue()) {
                helper.setGone(R.id.tv_accept, true);
                helper.setGone(R.id.tv_reject, true);
                helper.setText(R.id.tv_accept, "同意");
                helper.setText(R.id.tv_reject, "暂不");
            } else if (item.getOrderStatus() == OrderStatusEnum.REJECTED.getValue()) {
                helper.setGone(R.id.tv_end_state, true);
            } else if (OrderStatusEnum.canFinishOrder(item.getOrderStatus())) {
                helper.setGone(R.id.tv_accept, true);
                helper.setGone(R.id.tv_reject, true);
                helper.setText(R.id.tv_accept, "完成");
                helper.setText(R.id.tv_reject, "退款");
            } else if (OrderStatusEnum.canEvaluateOrder(item.getOrderStatus())) {
                helper.setGone(R.id.tv_end_state, false);
                helper.setGone(R.id.tv_accept, true);
                helper.setText(R.id.tv_accept, "评价");
            } else if (item.getOrderStatus() == OrderStatusEnum.REFUND_REJECTED.getValue()) {
                helper.setGone(R.id.tv_accept, true);
                helper.setGone(R.id.tv_reject, true);
                helper.setText(R.id.tv_accept, "同意");
                helper.setText(R.id.tv_reject, "申诉");
            } else if (item.getOrderStatus() == OrderStatusEnum.APPEALING.getValue()) {
                helper.setGone(R.id.tv_end_state, false);
                helper.setGone(R.id.tv_accept, true);
                helper.setText(R.id.tv_accept, "去处理");
            }
        }

    }
}
