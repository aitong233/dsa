package com.qpyy.module.index.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.KeyWordUtil;
import com.qpyy.module.index.R;
import com.qpyy.module.index.bean.UserResultResp;

/**
 * 搜索用户的结果
 */
public class SearchUserResultAdapter extends BaseQuickAdapter<UserResultResp, BaseViewHolder> {


    private String keyWord = "";

    public SearchUserResultAdapter() {
        super(R.layout.index_item_search_user_result);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserResultResp item) {
        helper.setText(R.id.tv_nick_name, KeyWordUtil.matcherSearchTitle(Color.parseColor("#6765FF"), item.getNickname(), keyWord));
        TextView textViewOnLine = helper.getView(R.id.tv_online);
        textViewOnLine.setText(item.getOnline_text());
        if (item.getOnline_text().equals("在线")) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.index_icon_online);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textViewOnLine.setCompoundDrawables(drawable, null, null, null);
        } else {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.index_icon_offline);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textViewOnLine.setCompoundDrawables(drawable, null, null, null);
        }
        helper.setText(R.id._tv_user_id, item.getUser_code());
        helper.setText(R.id.tv_separation, item.getFans_count());
        if ("0".equals(item.getFollow())) {
            helper.setText(R.id.tv_follow, "关注")
                    .setBackgroundRes(R.id.tv_follow, R.mipmap.index_img_primary_btn_sm);
        } else {
            helper.setText(R.id.tv_follow, "已关注")
                    .setBackgroundRes(R.id.tv_follow, R.mipmap.index_img_primary_btn_disable);
        }
        ImageUtils.loadHeadCC(item.getHead_picture(), helper.getView(R.id.riv_user_head));
        helper.addOnClickListener(R.id.tv_follow);
    }


    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }


}
