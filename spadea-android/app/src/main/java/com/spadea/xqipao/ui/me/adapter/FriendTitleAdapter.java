package com.spadea.xqipao.ui.me.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;

import java.util.List;

public class FriendTitleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    private int indewx = 0;

    public FriendTitleAdapter(List<String> list) {
        super(R.layout.item_friend_title, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);
        if (helper.getAdapterPosition() == indewx) {
            helper.setVisible(R.id.iv, true);
        } else {
            helper.setVisible(R.id.iv, false);
        }
    }

    public void setIndewx(int indewx) {
        this.indewx = indewx;
        notifyDataSetChanged();
    }
}
