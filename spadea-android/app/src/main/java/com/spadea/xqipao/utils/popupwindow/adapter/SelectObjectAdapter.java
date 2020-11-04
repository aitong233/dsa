package com.spadea.xqipao.utils.popupwindow.adapter;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.GiftUserBean;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

import java.util.List;

public class SelectObjectAdapter extends BaseQuickAdapter<GiftUserBean, BaseViewHolder> {


    public SelectObjectAdapter() {
        super(R.layout.item_gift_left);
    }

    @Override
    protected void convert(BaseViewHolder helper, GiftUserBean item) {
        helper.setText(R.id.tv_nickname, item.getNickname());
        TextView tvRole = helper.getView(R.id.tv_role);
        if (item.getPit_number().equals("9")) {
            tvRole.setText("主持");
        } else {
            tvRole.setText(item.getPit_number() + "号麦");
        }
        ImageLoader.loadHead(MyApplication.getInstance(), helper.getView(R.id.riv), item.getHead_picture());
        ImageView cb = helper.getView(R.id.cb);
        if (item.isB()) {
            cb.setImageLevel(1);
        } else {
            cb.setImageLevel(0);
        }
    }

    public boolean isAll() {
        List<GiftUserBean> data = getData();
        for (GiftUserBean giftUserBean : data) {
            if (!giftUserBean.isB()) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        String text = "";
        List<GiftUserBean> data = getData();
        for (GiftUserBean giftUserBean : data) {
            if (giftUserBean.isB()) {
                text += giftUserBean.getNickname() + ";";
            }
        }
        if (TextUtils.isEmpty(text)) {
            return "选择打赏对象";
        } else {
            return text;
        }
    }


}
