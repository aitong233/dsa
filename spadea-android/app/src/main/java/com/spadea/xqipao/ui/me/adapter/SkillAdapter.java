package com.spadea.xqipao.ui.me.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.SkillSection;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

import org.greenrobot.eventbus.EventBus;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.adapter
 * 创建人 王欧
 * 创建时间 2020/5/22 3:30 PM
 * 描述 describe
 */
public class SkillAdapter extends BaseQuickAdapter<SkillSection, BaseViewHolder> {
    public SkillAdapter() {
        super(R.layout.rv_item_skill_section, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, SkillSection item) {
        if (TextUtils.isEmpty(item.getGroupPicture())) {
            helper.setVisible(R.id.tv_title, true);
            helper.setVisible(R.id.iv_image, false);
            helper.setText(R.id.tv_title, item.getGroupName());
        } else {
            helper.setVisible(R.id.tv_title, false);
            helper.setVisible(R.id.iv_image, true);
//            ImageLoader.loadImageCenterCrop(mContext, helper.getView(R.id.iv_image), item.getGroupPicture());
        }
        RecyclerView recyclerView = helper.getView(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        BaseQuickAdapter<SkillSection.Item, BaseViewHolder> adapter = new BaseQuickAdapter<SkillSection.Item, BaseViewHolder>(R.layout.rv_item_skill, item.getSkillResultVOs()) {
            @Override
            protected void convert(BaseViewHolder helper, SkillSection.Item item) {
                helper.setText(R.id.text, item.getSkillName());
                ImageLoader.loadImage(mContext, helper.getView(R.id.image), item.getSkillPicture());
            }
        };
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().post(adapter.getItem(position));
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
