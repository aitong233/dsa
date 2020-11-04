package com.yutang.game.fudai.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageLoader;
import com.yutang.game.fudai.R;
import com.yutang.game.fudai.R2;
import com.yutang.game.fudai.base.BaseDialogFragment;
import com.yutang.game.fudai.bean.GameLog;
import com.yutang.game.fudai.contacts.GameLogContacts;
import com.yutang.game.fudai.presenter.GameLogPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class GameLogDialogFragment extends BaseDialogFragment<GameLogPresenter> implements GameLogContacts.View {
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    //todo p 分页

    @Override
    protected void initData() {
        MvpPre.getGameLog();
    }

    @Override
    protected void initView(View rootView) {
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dialog_game_log;
    }

    @Override
    protected GameLogPresenter bindPresenter() {
        return new GameLogPresenter(this, getActivity());
    }

    @Override
    public void gameLog(List<GameLog> list) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(new BaseQuickAdapter<GameLog, BaseViewHolder>(R.layout.rv_item_game_log, list) {
            @Override
            protected void convert(BaseViewHolder helper, GameLog item) {
                helper.setText(R.id.tv_time, item.getCreated_at() + "");
                ImageLoader.loadImage(getContext(), helper.getView(R.id.image), item.getPicture());
                helper.setText(R.id.tv_name, String.format("%s × %s", item.getName(), item.getCount()));
            }
        });
    }


    @OnClick(R2.id.view_close)
    public void onViewClicked() {
        dismiss();
    }

    @Override
    public void showLoadings(String content) {

    }
}
