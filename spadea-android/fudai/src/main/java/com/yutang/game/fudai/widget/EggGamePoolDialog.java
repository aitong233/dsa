package com.yutang.game.fudai.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.libcommon.widget.dialog.BaseDialog;
import com.yutang.game.fudai.R;
import com.yutang.game.fudai.R2;
import com.yutang.game.fudai.bean.WinJackpotModel;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class EggGamePoolDialog extends BaseDialog {
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.title)
    TextView title;

    public EggGamePoolDialog(@NonNull final Context context, List<WinJackpotModel> list, int type) {
        super(context);
        switch (type) {
            case 1:
                title.setText("银福袋奖池");
                break;

            case 2:
                title.setText("金福袋奖池");
                break;

            case 3:
                title.setText("钻福袋奖池");
                break;
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        mRecyclerView.setAdapter(new BaseQuickAdapter<WinJackpotModel, BaseViewHolder>(R.layout.item_game_pool, list) {
            @Override
            protected void convert(BaseViewHolder helper, WinJackpotModel item) {
                View sp = helper.getView(R.id.sp);
                View def = helper.getView(R.id.iv_bg);

                if ("1".equals(item.getSpecial())) {
                    sp.setVisibility(View.VISIBLE);
                    def.setVisibility(View.GONE);
                    ImageLoader.loadImage(context, helper.getView(R.id.img), item.getPicture());
                    helper.setText(R.id.tv_name, item.getName());
                    ObjectAnimator anim = ObjectAnimator.ofFloat(helper.getView(R.id.light), "rotation", 0f, 360f);
                    anim.setDuration(1888);
                    anim.setInterpolator(new LinearInterpolator());
                    anim.setRepeatCount(ValueAnimator.INFINITE);
                    anim.start();
                } else {
                    sp.setVisibility(View.GONE);
                    def.setVisibility(View.VISIBLE);
                    ImageLoader.loadImage(context, helper.getView(R.id.image), item.getPicture());
                    helper.setText(R.id.tv_name, item.getName());
                    helper.setText(R.id.tv_price, String.format("%s", item.getPrice()));
                }
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_egg_game_pool;
    }

    @Override
    public void initView() {
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void initData() {

    }

    @OnClick(R2.id.iv_close)
    public void onViewClicked() {
        dismiss();
    }
}
