package com.spadea.xqipao.ui.login.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.LabelModel;
import com.spadea.yuyin.R;

import java.util.ArrayList;
import java.util.List;

public class LabelAdapter extends BaseQuickAdapter<LabelModel, BaseViewHolder> {

    private int type = 1;

    private List<String> labelModels = new ArrayList<>();


    public LabelAdapter(int type) {
        super(R.layout.item_label);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, LabelModel item) {
        helper.setText(R.id.tv_label, item.getName());

        if (type == 1) {
            if (isB(item.getId())) {
                helper.setTextColor(R.id.tv_label, Color.parseColor("#FFFFFFFF")).setBackgroundRes(R.id.tv_label, R.mipmap.bg_game_select);
            } else {
                helper.setTextColor(R.id.tv_label, Color.parseColor("#FFFF8890")).setBackgroundRes(R.id.tv_label, R.mipmap.bg_game_un_select);
            }
        } else {
            if (isB(item.getId())) {
                helper.setTextColor(R.id.tv_label, Color.parseColor("#FFFFFFFF"))
                        .setBackgroundRes(R.id.tv_label, R.mipmap.bg_emotion_select);
            } else {
                helper.setTextColor(R.id.tv_label, Color.parseColor("#FF88D1FF"))
                        .setBackgroundRes(R.id.tv_label, R.mipmap.bg_emotion_un_select);
            }
        }
    }

    public int getCount() {
        return labelModels.size();
    }

    public String dataToString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < labelModels.size(); i++) {
            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                sb.append(",");
            }
            sb.append(labelModels.get(i));
        }
        return sb.toString();
    }

    public void add(String id) {
        if (isB(id)) {
            labelModels.remove(id);
        } else {
            labelModels.add(id);
        }
        notifyDataSetChanged();
    }


    public boolean isB(String id) {
        for (String item : labelModels) {
            if (id.equals(item)) {
                return true;
            }
        }
        return false;
    }


}
