package com.spadea.xqipao.ui.me.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.xqipao.data.SkillPriceItem;
import com.spadea.xqipao.utils.dialog.BaseBottomSheetDialog;

import java.util.List;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.dialog
 * 创建人 王欧
 * 创建时间 2020/5/25 1:50 PM
 * 描述 describe
 */
public class SkillPriceDialog extends BaseBottomSheetDialog {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private List<SkillPriceItem> items;
    private BaseQuickAdapter<SkillPriceItem, BaseViewHolder> mAdapter;

    public SkillPriceDialog(@NonNull Context context, List<SkillPriceItem> items) {
        super(context);
        this.items = items;
        setData();
    }

    private void setData() {
        mAdapter.setNewData(items);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_skill_price;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new BaseQuickAdapter<SkillPriceItem, BaseViewHolder>(R.layout.rv_item_skill_price) {
            @Override
            protected void convert(BaseViewHolder helper, SkillPriceItem item) {
                helper.setText(R.id.tv_price, new SpanUtils().append("10").setForegroundColor(MyApplication.getInstance().getResources().getColor(R.color.color_main)).append("金币").create());
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }
}
