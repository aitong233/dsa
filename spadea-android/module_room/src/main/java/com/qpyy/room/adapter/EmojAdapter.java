package com.qpyy.room.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.room.R;
import com.qpyy.room.bean.ExclusiveEmojiResp;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/8/6 14:48
 * 描述 describe
 */
public class EmojAdapter extends BaseQuickAdapter<ExclusiveEmojiResp, BaseViewHolder> {


    public EmojAdapter(List<ExclusiveEmojiResp> data) {
        super(R.layout.room_rv_item_emoj, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExclusiveEmojiResp item) {
        ImageView view = helper.getView(R.id.image);
        helper.setVisible(R.id.tv_text, false);
        if (item.getName().equals("抽签")) {
            Glide.with(mContext).load(R.drawable.random0).into(view);
        } else if (item.getName().startsWith("发牌")) {
            Glide.with(mContext).load(R.drawable.card).into(view);
            helper.setVisible(R.id.tv_text, true);
            String[] strings = item.getName().split("x");
            switch (strings[1]) {
                case "1":
                    helper.setText(R.id.tv_text, "一张");
                    break;
                case "2":
                    helper.setText(R.id.tv_text, "二张");
                    break;
                case "3":
                    helper.setText(R.id.tv_text, "三张");
                    break;
                case "5":
                    helper.setText(R.id.tv_text, "五张");
                    break;
                default:
                    break;
            }
        } else if (item.getName().equals("掷骰子")) {
            Glide.with(mContext).load(R.drawable.pic_touzi).into(view);
        } else {
            ImageLoader.loadImage(mContext, view, item.getSpecial());
        }
        helper.setText(R.id.tv_name, item.getName());
        //根据爵位判断是否允许使用表情
        if (!TextUtils.isEmpty(item.getAuth()) && !"1".equals(item.getAuth())) {
            //只要不等于空 就可以使用表情
            helper.getView(R.id.iv_mask).setVisibility(View.VISIBLE);
            helper.getView(R.id.iv_lock).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.iv_mask).setVisibility(View.GONE);
            helper.getView(R.id.iv_lock).setVisibility(View.GONE);
        }
    }
}