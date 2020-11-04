package com.spadea.xqipao.ui.room.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.RoomPitUserModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;


public class GiftUserAdapter extends BaseQuickAdapter<RoomPitUserModel, BaseViewHolder> {


    public GiftUserAdapter() {
        super(R.layout.item_gift_user);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomPitUserModel item) {
        ImageLoader.loadHead(mContext, helper.getView(R.id.riv), item.getHead_picture());
        switch (item.getPit_number()) {
            case "1":
                helper.setText(R.id.tv_pit_number, "一麦");
                break;
            case "2":
                helper.setText(R.id.tv_pit_number, "二麦");
                break;
            case "3":
                helper.setText(R.id.tv_pit_number, "三麦");
                break;
            case "4":
                helper.setText(R.id.tv_pit_number, "四麦");
                break;
            case "5":
                helper.setText(R.id.tv_pit_number, "五麦");
                break;
            case "6":
                helper.setText(R.id.tv_pit_number, "六麦");
                break;
            case "7":
                helper.setText(R.id.tv_pit_number, "七麦");
                break;
            case "8":
                helper.setText(R.id.tv_pit_number, "八麦");
                break;
            case "9":
                helper.setText(R.id.tv_pit_number, "主持麦");
                break;
            default:
                helper.setText(R.id.tv_pit_number, "其他");
                break;
        }

        RoundedImageView roundedImageView = helper.getView(R.id.riv);
        if (item.isSelect()) {
            helper.setBackgroundRes(R.id.tv_pit_number, R.drawable.bg_gift_user_select).setTextColor(R.id.tv_pit_number, Color.parseColor("#FFFFFF"));
            roundedImageView.setBorderColor(Color.parseColor("#FFFF8890"));
        } else {
            roundedImageView.setBorderColor(Color.parseColor("#00000000"));
            helper.setBackgroundRes(R.id.tv_pit_number, R.drawable.bg_gift_user_unselect).setTextColor(R.id.tv_pit_number, Color.parseColor("#333333"));
        }
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




}
