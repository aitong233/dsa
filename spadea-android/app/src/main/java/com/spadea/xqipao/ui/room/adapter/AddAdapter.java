package com.spadea.xqipao.ui.room.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.SearchUserModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;

public class AddAdapter extends BaseQuickAdapter<SearchUserModel, BaseViewHolder> {


    private int type = 0;

    public AddAdapter() {
        super(R.layout.item_add_user);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchUserModel item) {
        if (type == 0) {
            if (item.getValue().equals("0")) {
                helper.setText(R.id.tv_operation, "添加管理员");
            } else {
                helper.setText(R.id.tv_operation, "取消管理员");
            }
        } else {
            if (item.getValue().equals("0")) {
                helper.setText(R.id.tv_operation, "添加黑名单");
            } else {
                helper.setText(R.id.tv_operation, "取消黑名单");
            }
        }
        helper.setText(R.id.tv_name, item.getNickname())
                .setText(R.id.tv_id, "用户ID: " + item.getUser_code());
        RoundedImageView roundedImageView = helper.getView(R.id.riv);
        ImageLoader.loadHead(MyApplication.getInstance(), roundedImageView, item.getHead_picture());

    }

    public void setType(int type) {
        this.type = type;
    }
}
