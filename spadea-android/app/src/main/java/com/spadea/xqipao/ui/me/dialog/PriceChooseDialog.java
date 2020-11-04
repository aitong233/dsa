package com.spadea.xqipao.ui.me.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.xqipao.data.SkillPriceSet;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.dialog.BaseBottomSheetDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.dialog.room
 * 创建人 王欧
 * 创建时间 2020/3/30 3:08 PM
 * 描述 describe
 */
public class PriceChooseDialog extends BaseBottomSheetDialog {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private int applyId;

    public PriceChooseDialog(@NonNull Context context, int id, List<String> items) {
        super(context);
        this.applyId = id;
        mRecyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.rv_item_dialog_count_down, items) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.text, item);
                helper.setTextColor(R.id.text, MyApplication.getInstance().getResources().getColor(R.color.color_545454));
                helper.getView(R.id.text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtils.e("PriceItem", item);
                        EventBus.getDefault().post(new SkillPriceSet(applyId, item, 1));
                        dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_skill_price_choose;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.tv_close)
    public void onViewClicked() {
        dismiss();
    }
}
