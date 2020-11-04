package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;
import com.spadea.xqipao.data.ProtectedItemBean;
import com.spadea.xqipao.utils.dialog.BaseBottomSheetDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.dialog.room
 * 创建人 王欧
 * 创建时间 2020/4/7 9:41 AM
 * 描述 describe
 */
public class OpenGuardDialog extends BaseBottomSheetDialog implements OnGuardCheckedChangedListener {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_guard)
    ImageView mIvGuard;
    @BindView(R.id.iv_medal)
    ImageView mIvMedal;
    @BindView(R.id.tv_guard)
    TextView mTvGuard;
    @BindView(R.id.tv_medal)
    TextView mTvMedal;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_medal)
    LinearLayout mLlMedal;
    @BindView(R.id.ll_tag)
    LinearLayout mLlTag;
    @BindView(R.id.tv_action)
    TextView mTvAction;

    private OnSelectedProtectListener mListener;

    private List<ProtectedItemBean> list;
    private GuardAdapter mAdapter;
    private int type;
    private String userName;

    public OpenGuardDialog(String userName, int type, @NonNull Context context, List<ProtectedItemBean> list, OnSelectedProtectListener listener) {
        super(context);
        this.mListener = listener;
        this.list = list;
        this.type = type;
        this.userName=userName;
        setData();
    }

    private void setData() {
        mTvTitle.setText(String.format("开通当前主持（%s）的守护",userName));
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mAdapter = new GuardAdapter(list, this);
        for (int i = 0; i < list.size(); i++) {
            ProtectedItemBean bean = list.get(i);
            if (String.valueOf(type).equals(bean.getType())) {
                mAdapter.setCheckedPosition(i);
                break;
            }
        }
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_open_guard;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_action:
                if (mListener != null) {
                    mListener.onSelectedProtect(mAdapter.getCheckedItemType());
                }
                break;
        }
        dismiss();
    }

    @Override
    public void checkedChanged(ProtectedItemBean item) {
        mTvAction.setText(String.format("立即开通(%s金币/%s天)", item.getMoney(),item.getDays()));
        switch (item.getType()) {
            case "1":
                mTvName.setText("黄金守护专属特权");
                mIvGuard.setImageResource(R.mipmap.ic_guard_gold);
                mTvGuard.setText("黄金守护位");
                mIvMedal.setImageResource(R.mipmap.ic_medal_gold);
                break;
            case "2":
                mTvName.setText("白银守护专属特权");
                mIvGuard.setImageResource(R.mipmap.ic_guard_silver);
                mIvMedal.setImageResource(R.mipmap.ic_medal_silver);
                mTvGuard.setText("白银守护位");
                break;
            case "3":
                mTvName.setText("青铜守护专属特权");
                mTvGuard.setText("青铜守护位");
                mIvGuard.setImageResource(R.mipmap.ic_guard_bronze);
                mIvMedal.setImageResource(R.mipmap.ic_medal_bronze);
                break;
        }
    }

    private static class GuardAdapter extends BaseQuickAdapter<ProtectedItemBean, BaseViewHolder> {
        private int checkedPosition;

        private OnGuardCheckedChangedListener mOnGuardCheckedChanged;

        public GuardAdapter(List<ProtectedItemBean> list, OnGuardCheckedChangedListener listener) {
            super(R.layout.rv_item_guard_select, list);
            this.mOnGuardCheckedChanged = listener;
        }

        public void setCheckedPosition(int checkedPosition) {
            this.checkedPosition = checkedPosition;
            mOnGuardCheckedChanged.checkedChanged(getItem(checkedPosition));
        }

        @Override
        protected void convert(BaseViewHolder helper, ProtectedItemBean item) {
            CheckBox checkBox = helper.getView(R.id.cb);
            if ("1".equals(item.getType())) {
                checkBox.setBackgroundResource(R.drawable.rb_guard_gold);
            } else if ("2".equals(item.getType())) {
                checkBox.setBackgroundResource(R.drawable.rb_guard_silver);
            } else if ("3".equals(item.getType())) {
                checkBox.setBackgroundResource(R.drawable.rb_guard_bronze);
            }

            if (checkedPosition == helper.getAdapterPosition()) {
                checkBox.setChecked(true);
                ViewCompat.animate(checkBox)
                        .setDuration(200)
                        .scaleX(1f)
                        .scaleY(1f)
                        .start();
            } else {
                checkBox.setChecked(false);
                ViewCompat.animate(checkBox)
                        .setDuration(200)
                        .scaleX(1 / 1.1f)
                        .scaleY(1 / 1.1f)
                        .start();
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        checkedPosition = helper.getAdapterPosition();
                        mOnGuardCheckedChanged.checkedChanged(getItem(checkedPosition));
                        notifyDataSetChanged();
                    }
                }
            });
        }

        String getCheckedItemType() {
            return getItem(checkedPosition).getType();
        }
    }

    public interface OnSelectedProtectListener {
        void onSelectedProtect(String type);
    }

}
