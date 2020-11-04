package com.qpyy.module.index.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.R2;
import com.qpyy.libcommon.utils.KeyWordUtil;
import com.qpyy.module.index.R;
import com.qpyy.module.index.bean.RecordSection;

/**
 * 模糊搜索结果
 */
public class SearchRecordAdapter extends BaseSectionQuickAdapter<RecordSection, BaseViewHolder> {


    private String keyWord = "";

    public SearchRecordAdapter() {
        super(R.layout.index_item_record, R.layout.index_item_tecord_head, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, RecordSection item) {
        helper.setText(R.id.tv_text, KeyWordUtil.matcherSearchTitle(Color.parseColor("#6765FF"), item.t, keyWord));
        helper.addOnClickListener(R.id.tv_text);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, RecordSection item) {
        helper.setText(R.id.tv_head, item.header);
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }


}
