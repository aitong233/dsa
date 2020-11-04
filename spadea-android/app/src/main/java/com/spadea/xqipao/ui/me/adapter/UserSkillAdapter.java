package com.spadea.xqipao.ui.me.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.UserSkillItem;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.SpanUtils;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.adapter
 * 创建人 王欧
 * 创建时间 2020/6/1 2:15 PM
 * 描述 describe
 */
public class UserSkillAdapter extends BaseQuickAdapter<UserSkillItem, BaseViewHolder> {
    public UserSkillAdapter() {
        super(R.layout.rv_item_user_skill, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserSkillItem item) {
        helper.addOnClickListener(R.id.btn_action);
        helper.addOnClickListener(R.id.image);
//        ImageLoader.loadImageCenterCrop(mContext, helper.getView(R.id.image), item.getApplyPicture());
        helper.setText(R.id.tv_title, item.getSkillName());
        helper.setText(R.id.tv_price, new SpanUtils().append(String.valueOf(item.getPrice()).replace(".00", "")).setFontSize(15, true).append("金币/").append(item.getCompany()).create());
    }
}
