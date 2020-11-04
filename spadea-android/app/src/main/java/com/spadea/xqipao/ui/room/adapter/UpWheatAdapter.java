package com.spadea.xqipao.ui.room.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RowWheatModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

import java.util.List;


public class UpWheatAdapter extends BaseQuickAdapter<RowWheatModel, BaseViewHolder> {


    public UpWheatAdapter() {
        super(R.layout.item_up_wheat);
    }

    @Override
    protected void convert(BaseViewHolder helper, RowWheatModel item) {
        ImageLoader.loadHead(MyApplication.getInstance(), helper.getView(R.id.riv), item.getHead_picture());
        helper.setText(R.id.tv_nick_name, item.getNickname())
                .setText(R.id.tv_index, (helper.getAdapterPosition() + 1) + "");
        helper.addOnClickListener(R.id.tv_up_wheat);
        helper.addOnClickListener(R.id.tv_remove);
    }


    public String idToString() {
        List<RowWheatModel> data = getData();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                sb.append(",");
            }
            sb.append(data.get(i).getId());
        }
        return sb.toString();
    }
}
