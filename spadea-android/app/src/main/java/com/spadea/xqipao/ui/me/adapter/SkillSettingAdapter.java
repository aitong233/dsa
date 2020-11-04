package com.spadea.xqipao.ui.me.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;
import com.spadea.xqipao.data.SkillPriceSet;
import com.spadea.xqipao.data.SkillSetting;

import org.greenrobot.eventbus.EventBus;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.adapter
 * 创建人 王欧
 * 创建时间 2020/5/25 1:18 PM
 * 描述 describe
 */
public class SkillSettingAdapter extends BaseQuickAdapter<SkillSetting, BaseViewHolder> {
    public SkillSettingAdapter() {
        super(R.layout.rv_item_skill_setting, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, SkillSetting item) {
        helper.setText(R.id.tv_title, item.getSkillName());
        helper.setText(R.id.tv_title_price, String.format("价格(%s)", item.getSkillUnit()));
        helper.setText(R.id.tv_price, item.getPrice() + "泡泡币");

        CheckBox cb = helper.getView(R.id.cb);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                helper.setGone(R.id.group_voice, isChecked);
                helper.setGone(R.id.group_price, isChecked);
            }
        });
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SkillPriceSet(item.getApplyId(), null, cb.isChecked() ? 1 : 0));
            }
        });
        CheckBox cb1 = helper.getView(R.id.cb1);
        cb1.setEnabled(false);
        cb1.setVisibility(View.GONE);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                helper.setGone(R.id.group_price, isChecked);
//                helper.setImageResource(R.id.iv_arrow, isChecked ? R.mipmap.ic_arrow_down : R.mipmap.ic_arrow_up);
            }
        });
        helper.addOnClickListener(R.id.view_price);
        cb.setChecked(item.getOrderSwitch() == 1);
        cb1.setChecked(item.getOrderSwitch() == 1);
    }
}
