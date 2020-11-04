package com.qpyy.room.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.bean.ApplyWheatUsersResp;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/7/25 11:01
 * 描述 describe
 */
public class WaitForAdapter extends BaseQuickAdapter<ApplyWheatUsersResp.ListData, BaseViewHolder> {

    private int role;

    public WaitForAdapter(int role) {
        super(R.layout.room_rv_item_wait_for);
        this.role = role;
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplyWheatUsersResp.ListData item) {
        helper.setText(R.id.tv_index, String.valueOf(helper.getAdapterPosition() + 1))
                .setText(R.id.tv_user_name, item.getNickname());
        if (TextUtils.isEmpty(item.getPit_number())) {
            helper.setGone(R.id.tv_explain, false);
        } else {
            helper.setGone(R.id.tv_explain, true);
            helper.setText(R.id.tv_explain, String.format("申请上%s号麦", item.getPit_number()));
        }
        helper.setGone(R.id.ll, role < 3);
        helper.addOnClickListener(R.id.tv_remove);
        helper.addOnClickListener(R.id.tv_up);
        ImageUtils.loadHeadCC(item.getHead_picture(), helper.getView(R.id.room_item_head));
    }

    public String idToString() {
        List<ApplyWheatUsersResp.ListData> data = getData();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                sb.append(",");
            }
            sb.append(data.get(i).getId());
        }
        return sb.toString();
    }
}
