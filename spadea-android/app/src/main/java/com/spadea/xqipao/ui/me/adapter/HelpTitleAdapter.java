package com.spadea.xqipao.ui.me.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.HelpTitleModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

public class HelpTitleAdapter extends BaseQuickAdapter<HelpTitleModel, BaseViewHolder> {


    public HelpTitleAdapter() {
        super(R.layout.item_help_title);
    }

    @Override
    protected void convert(BaseViewHolder helper, HelpTitleModel item) {
        ImageLoader.loadImage(mContext, helper.getView(R.id.iv), item.getIcon());
        helper.setText(R.id.tv_name, item.getCat_name());

    }
}
