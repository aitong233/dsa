package com.qpyy.room.adapter;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.R;
import com.qpyy.room.bean.RoomPitUserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/8/6 14:48
 * 描述 describe
 */
public class GiftUserAdapter extends BaseQuickAdapter<RoomPitUserModel, BaseViewHolder> {


    public GiftUserAdapter() {
        super(R.layout.room_rv_item_gift_wheat);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomPitUserModel item) {
        ImageUtils.loadHeadCC(item.getHead_picture(), helper.getView(R.id.riv_gift_wheat_head_pic));

        RoundedImageView rivPit = helper.getView(R.id.riv_pit);
        RoundedImageView rivHead = helper.getView(R.id.riv_gift_wheat_head_pic);
        rivPit.setVisibility(item.isSelect() ? View.VISIBLE : View.GONE);
//        rivHead.setVisibility(item.getUser_id().equals(SpUtils.getUserId()) ? View.GONE : View.VISIBLE);
    }

    public boolean isAll() {
        int count = 0;
        List<RoomPitUserModel> data = getData();
        for (RoomPitUserModel item : data) {
            if (item.isSelect()) {
                count++;
            }
        }
        if (count == data.size()) {
            return true;
        } else {
            return false;
        }
    }

    public void allElection(boolean b) {
        List<RoomPitUserModel> data = getData();
        for (RoomPitUserModel item : data) {
            item.setSelect(b);
        }
        notifyDataSetChanged();
    }

    public int getSelectCount() {
        int count = 0;
        List<RoomPitUserModel> data = getData();
        for (RoomPitUserModel item : data) {
            if (item.isSelect()) {
                count++;
            }
        }
        return count;
    }

    public String getUserIdToString() {
        List<RoomPitUserModel> data = getData();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelect()) {
                if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                    sb.append(",");
                }
                sb.append(data.get(i).getUser_id());
            }
        }
        return sb.toString();
    }

    public String getUserPitToString() {
        List<RoomPitUserModel> data = getData();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelect()) {
                if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                    sb.append(",");
                }
                sb.append(data.get(i).getPit_number());
            }
        }
        return sb.toString();
    }


    public List<RoomPitUserModel> getGiftUser() {
        List<RoomPitUserModel> data = getData();
        List<RoomPitUserModel> giftUserList = new ArrayList<>();
        for (RoomPitUserModel item : data) {
            if (item.isSelect()) {
                giftUserList.add(item);
            }
        }
        return giftUserList;
    }

    /**
     * 清除所有选中
     */
    public void clearAllSelected() {
        List<RoomPitUserModel> data = getData();
        for (RoomPitUserModel item : data) {
            item.setSelect(false);
        }
        notifyDataSetChanged();
    }

}