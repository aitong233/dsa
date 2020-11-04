package com.spadea.xqipao.ui.me.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;
import com.spadea.xqipao.data.BlackSectionBean;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.adapter
 * 创建人 王欧
 * 创建时间 2020/4/1 3:40 PM
 * 描述 describe
 */
public class BlackListAdapter extends BaseSectionQuickAdapter<BlackSectionBean, BaseViewHolder> {
    public BlackListAdapter() {
        super(R.layout.rv_item_black_list, R.layout.rv_item_black_list_header, null);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, BlackSectionBean item) {
        helper.setText(R.id.text, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, BlackSectionBean item) {
        helper.setText(R.id.tv_name, item.t.getNickname());
        helper.addOnClickListener(R.id.tv_remove);
    }
}
