package com.yutang.game.fudai.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.widget.dialog.BaseBottomSheetDialog;
import com.yutang.game.fudai.R;
import com.yutang.game.fudai.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class EggOpMoreDialog extends BaseBottomSheetDialog {
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;

    private BaseQuickAdapter<ItemBean, BaseViewHolder> mAdapter;

    private OnItemClickListener mListener;

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public EggOpMoreDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_egg_op_more;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        mAdapter = new BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.rv_item_room_op_more) {
            @Override
            protected void convert(BaseViewHolder helper, ItemBean item) {
                helper.setImageResource(R.id.iv_icon, item.icon);
                helper.setText(R.id.tv_title, item.title);
            }
        };
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ItemBean item = (ItemBean) adapter.getItem(position);
                if (item == null) {
                    return;
                }
                switch (item.position) {
                    case 0:
                        mListener.logClick();
                        break;
                    case 1:
                        mListener.ruleClick();
                        break;
                    case 2:
                        mListener.poolClick();
                        break;
                    case 3:
                        mListener.exitClick();
                        break;
                }
                dismiss();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mAdapter.setNewData(generateData());
    }

    private List<ItemBean> generateData() {
        List<ItemBean> itemBeans = new ArrayList<>();
        itemBeans.add(new ItemBean(0, "记录", R.mipmap.ic_egg_list));
        itemBeans.add(new ItemBean(1, "规则", R.mipmap.ic_egg_rule));
        itemBeans.add(new ItemBean(2, "奖池", R.mipmap.ic_egg_reward_pool));
        itemBeans.add(new ItemBean(3, "退出", R.mipmap.ic_egg_dismiss));

        return itemBeans;

    }

    private static class ItemBean {
        int position;
        String title;
        @DrawableRes
        int icon;

        ItemBean(int position, String title, int icon) {
            this.position = position;
            this.title = title;
            this.icon = icon;
        }
    }

    public static interface OnItemClickListener {
        void logClick();

        void ruleClick();

        void poolClick();

        void exitClick();
    }
}
