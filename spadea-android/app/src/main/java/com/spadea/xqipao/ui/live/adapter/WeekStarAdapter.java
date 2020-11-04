package com.spadea.xqipao.ui.live.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.WeekStarBean;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

public class WeekStarAdapter extends BaseQuickAdapter<WeekStarBean, BaseViewHolder> {

    private int type = 0;

    public WeekStarAdapter(int type) {
        super(R.layout.item_week_star);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, WeekStarBean item) {
        helper.setText(R.id.tv_ranking, item.getRank() + "")
                .setText(R.id.tv_nickname, item.getNickname())
                .setText(R.id.tv_gif_num, (type == 0 ? "魅力值  " : "财富值  ") + item.getTotal_price());
        ImageLoader.loadHead(mContext, helper.getView(R.id.riv), item.getHead_picture());
    }
}
