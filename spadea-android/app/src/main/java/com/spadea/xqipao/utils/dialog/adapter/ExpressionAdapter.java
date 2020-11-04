package com.spadea.xqipao.utils.dialog.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;
import com.spadea.xqipao.data.EmojiModel;

public class ExpressionAdapter extends BaseQuickAdapter<EmojiModel, BaseViewHolder> {


    public ExpressionAdapter() {
        super(R.layout.item_expression);
    }

    @Override
    protected void convert(BaseViewHolder helper, EmojiModel item) {
        helper.setText(R.id.tv_name, item.getName());
        ImageView ivImg = helper.getView(R.id.iv_img);

        if (item.getName().equals("抽签")) {
            Glide.with(mContext).load(R.drawable.random0).into(ivImg);
        }  else {
            Glide.with(mContext).load(item.getSpecial()).into(ivImg);
        }

    }
}
