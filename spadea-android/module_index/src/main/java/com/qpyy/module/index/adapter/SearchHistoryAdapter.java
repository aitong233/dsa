package com.qpyy.module.index.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.module.index.R;

/**
 * 历史记录
 */
public class SearchHistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SearchHistoryAdapter() {
        super(R.layout.index_item_history);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_text, item);
    }
}
