package com.spadea.xqipao.ui.me.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.OrderSkillSelectItem;
import com.spadea.xqipao.ui.order.widget.CustomWheelView;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.dialog.BaseDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.dialog
 * 创建人 王欧
 * 创建时间 2020/6/2 1:37 PM
 * 描述 describe
 */
public class SkillChooseDialog extends BaseDialog {
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    @BindView(R.id.recycler_view)
    CustomWheelView mRecyclerView;
    private OrderSkillSelectItem mOrderSkillSelectItem;

    public SkillChooseDialog(@NonNull Context context, List<OrderSkillSelectItem> list) {
        super(context);
        mOrderSkillSelectItem = list.get(0);
        mRecyclerView.setAdapter(new MyAdapter(list));
        mRecyclerView.setOnItemSelectedListener(new CustomWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                LogUtils.e("onItemSelected", index);
                mOrderSkillSelectItem = list.get(index);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_skill_choose;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setWindowAnimations(R.style.ShowDialogBottom);
    }

    @OnClick({R.id.tv_cancel, R.id.tv_confirm})
    public void onViewClicked(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.tv_cancel:
                break;
            case R.id.tv_confirm:
                EventBus.getDefault().post(mOrderSkillSelectItem);
                break;
        }
    }

    private static class MyAdapter extends CustomWheelView.WheelAdapter<UserViewHolder> {

        private final List<OrderSkillSelectItem> userBeanList;

        public MyAdapter(List<OrderSkillSelectItem> userBeanList) {
            this.userBeanList = userBeanList;
        }

        @Override
        public int getItemCount() {
            return userBeanList.size();
        }

        @Override
        public UserViewHolder onCreateViewHolder(LayoutInflater inflater, int viewType) {
            return new UserViewHolder(inflater.inflate(R.layout.rv_item_order_skill, null, false));
        }

        @Override
        public void onBindViewHolder(UserViewHolder holder, int position) {
            OrderSkillSelectItem userBean = userBeanList.get(position);
            ImageLoader.loadImage(MyApplication.getInstance(), holder.iv_head, userBean.getSkillImg());
            holder.tv_name.setText(userBean.getSkillName());
        }
    }

    private static class UserViewHolder extends RecyclerView.ViewHolder {

        public final ImageView iv_head;

        public final TextView tv_name;

        public UserViewHolder(View itemView) {
            super(itemView);
            iv_head = (ImageView) itemView.findViewById(R.id.image);
            tv_name = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
