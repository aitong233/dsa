package com.yutang.game.fudai.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;

import com.qpyy.libcommon.bean.LuckyPackLuckyRankItemBean;
import com.yutang.game.fudai.R;
import com.yutang.game.fudai.R2;
import com.yutang.game.fudai.adapter.LuckyRankAdapter;
import com.yutang.game.fudai.base.BaseDialogFragment;
import com.yutang.game.fudai.contacts.GameRankContacts;
import com.yutang.game.fudai.presenter.GameRankPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class GameRankDialogFragment extends BaseDialogFragment<GameRankPresenter> implements GameRankContacts.View {


    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.title)
    RadioGroup rgTitle;
    int rankType = 1;
    LuckyRankAdapter luckyRankAdapter = new LuckyRankAdapter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        MvpPre.getLuckyList(1);   //获取礼物榜
    }

    @Override
    protected void initView(View rootView) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(luckyRankAdapter);
        rgTitle.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_gift_rank) {
                rankType = 1;
            } else if (checkedId == R.id.rb_lucky_rank) {
                rankType = 2;
            }
            luckyRankAdapter.setNewData(new ArrayList<>());
            MvpPre.getLuckyList(rankType);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dialog_game_rank;
    }

    @Override
    protected GameRankPresenter bindPresenter() {
        return new GameRankPresenter(this, getActivity());
    }


    @Override
    public void luckyRankList(int type, List<LuckyPackLuckyRankItemBean> list) {
        luckyRankAdapter.setNewData(list);
    }

    @OnClick(R2.id.iv_close)
    public void onViewClicked() {
        dismiss();
    }

    @Override
    public void showLoadings(String content) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
