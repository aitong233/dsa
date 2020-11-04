package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;
import com.spadea.xqipao.ui.room.fragment.RoomFragmentListener;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.dialog.BaseBottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.dialog.room
 * 创建人 王欧
 * 创建时间 2020/3/30 3:08 PM
 * 描述 describe
 */
public class CountDownChooseDialog extends BaseBottomSheetDialog {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private int pitNumber;



    public CountDownChooseDialog(@NonNull Context context, int pitNumber) {
        super(context);
        this.pitNumber = pitNumber;
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_count_down_choose;
    }

    @Override
    public void initView() {
        List<ItemBean> items = new ArrayList<>();
        items.add(new ItemBean(60, "一分钟"));
        items.add(new ItemBean(3 * 60, "三分钟"));
        items.add(new ItemBean(5 * 60, "五分钟"));
        items.add(new ItemBean(10 * 60, "十分钟"));
        items.add(new ItemBean(15 * 60, "十五分钟"));
        items.add(new ItemBean(0, "关闭"));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.rv_item_dialog_count_down, items) {
            @Override
            protected void convert(BaseViewHolder helper, ItemBean item) {
                helper.setText(R.id.text, item.title);
                helper.getView(R.id.text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtils.e("countDownItem", item.title);
                        if (mContext instanceof RoomFragmentListener) {
                            ((RoomFragmentListener) mContext).pitCountDown(String.valueOf(pitNumber), String.valueOf(item.second));
                        }
                        dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void initData() {

    }

    private static class ItemBean {
        int second;
        String title;

        ItemBean(int second, String title) {
            this.second = second;
            this.title = title;
        }
    }
}
