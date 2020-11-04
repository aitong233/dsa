package com.qpyy.module.me.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.me.R;
import com.qpyy.module.me.bean.PhotoWallResp;

public class UserPhotoWallAdapter extends BaseQuickAdapter<PhotoWallResp.GiftResp, BaseViewHolder> {


    private boolean b = false;


    public UserPhotoWallAdapter() {
        super(R.layout.me_item_user_photo_wall);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoWallResp.GiftResp item) {
        if (!"0".equals(item.getId())) {
            ImageUtils.loadCenterCrop(item.getUrl(), helper.getView(R.id.riv_user_head));
            if ("-1".equals(item.getId())) {
                helper.setVisible(R.id.iv_close, false);
            } else {
                helper.setVisible(R.id.iv_close, b);
                helper.addOnClickListener(R.id.iv_close);
            }
        } else {
            helper.setImageResource(R.id.riv_user_head, R.mipmap.me_add_img);
            helper.setVisible(R.id.iv_close, false);
        }
    }


    public void setDelete(boolean b) {
        this.b = b;
        notifyDataSetChanged();
    }

    public boolean getDelete() {
        return b;
    }
}
