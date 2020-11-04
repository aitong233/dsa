package com.qpyy.room.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.bean.MixerResp;
import com.qpyy.room.bean.TunerBean;
import com.qpyy.room.dialog.TunerSheetDialog;


public class TunerListAdapter extends BaseQuickAdapter<MixerResp, BaseViewHolder> {
    private int index = 0;

    public TunerListAdapter() {
        super(R.layout.room_rv_item_tuner);
    }

    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, MixerResp item) {
        TextView tvName = helper.getView(R.id.tv_tuner_name);
        tvName.setText(item.getName());
        ImageLoader.loadIcon(mContext, helper.getView(R.id.iv_tuner_pic), item.getImgUrtl());
        if (index == item.getId()) {
            helper.getView(R.id.iv_tuner_pic_cover).setVisibility(View.VISIBLE);
            helper.setTextColor(R.id.tv_tuner_name, BaseApplication.getIns().getResources().getColor(R.color.color_FF6765FF));
        } else {
            helper.getView(R.id.iv_tuner_pic_cover).setVisibility(View.GONE);
            helper.setTextColor(R.id.tv_tuner_name, BaseApplication.getIns().getResources().getColor(R.color.white));
        }
    }
}
