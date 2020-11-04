package com.spadea.xqipao.ui.login.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.LabelModel;
import com.spadea.xqipao.ui.home.activity.HomeActivity;
import com.spadea.xqipao.ui.login.contacter.LabelContacts;
import com.spadea.xqipao.ui.login.presenter.LabelPresenter;
import com.spadea.xqipao.utils.SPUtil;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.login.adapter.LabelAdapter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;


import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = ARouters.ME_LABEL, name = "选择标签")
public class LabelActivity extends BaseActivity<LabelPresenter> implements LabelContacts.View {

    @BindView(R.id.tv_game_f5)
    TextView tvGameF5;
    @BindView(R.id.recyclerView_game)
    RecyclerView recyclerViewGame;
    @BindView(R.id.tv_emotion_f5)
    TextView tvEmotionF5;
    @BindView(R.id.recyclerView_emotion)
    RecyclerView recyclerViewEmotion;


    private int pageGame = 1;
    private int pageEmotion = 1;

    private LabelAdapter gameLabelAdapter;
    private LabelAdapter emotionLabelAdapter;


    public LabelActivity() {
        super(R.layout.activity_label);
    }

    @Override
    protected void initData() {
        MvpPre.indexLabel("1", pageGame);
        MvpPre.indexLabel("2", pageEmotion);
    }

    @Override
    protected void initView() {
        recyclerViewGame.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewEmotion.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewGame.setAdapter(gameLabelAdapter = new LabelAdapter(1));
        recyclerViewEmotion.setAdapter(emotionLabelAdapter = new LabelAdapter(2));
    }

    @Override
    protected void setListener() {
        super.setListener();
        gameLabelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LabelModel item = gameLabelAdapter.getItem(position);
                int count = gameLabelAdapter.getCount();
                int count1 = emotionLabelAdapter.getCount();
                if (count + count1 > 3) {
                    if (gameLabelAdapter.isB(item.getId())) {
                        gameLabelAdapter.add(item.getId());
                    } else {
                        ToastUtils.showShort("最多选择4个标签");
                    }
                    return;
                } else {
                    gameLabelAdapter.add(item.getId());
                }
            }
        });
        emotionLabelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LabelModel item = emotionLabelAdapter.getItem(position);
                int count = gameLabelAdapter.getCount();
                int count1 = emotionLabelAdapter.getCount();
                if (count + count1 > 3) {
                    if (emotionLabelAdapter.isB(item.getId())) {
                        emotionLabelAdapter.add(item.getId());
                    } else {
                        ToastUtils.showShort("最多选择4个标签");
                    }
                    return;
                } else {
                    emotionLabelAdapter.add(item.getId());
                }
            }
        });
    }

    @Override
    protected LabelPresenter bindPresenter() {
        return new LabelPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Override
    public void indexLabelSuccess(String categoryId, List<LabelModel> data) {
        if (categoryId.equals("1")) {
            gameLabelAdapter.setNewData(data);
        } else {
            emotionLabelAdapter.setNewData(data);
        }
    }

    @Override
    public void addLabelSuccess() {
        ToastUtils.showShort("设置完成");
        String roomId = SPUtil.getString(Constant.Channel.ROOMID);
        startActivity(new Intent(this, HomeActivity.class).putExtra("roomId",roomId));
        finish();
    }


    @OnClick({R.id.tv_game_f5, R.id.tv_emotion_f5, R.id.tv_commit, R.id.iv_skip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_game_f5:
                pageGame++;
                MvpPre.indexLabel("1", pageGame);
                break;
            case R.id.tv_emotion_f5:
                pageEmotion++;
                MvpPre.indexLabel("2", pageEmotion);
                break;
            case R.id.tv_commit:
                int count1 = gameLabelAdapter.getCount();
                int count2 = emotionLabelAdapter.getCount();
                if (count1 == 0 && count2 == 0) {
                    ToastUtils.showShort("请选择标签");
                    return;
                }
                String ids = null;
                if (count1 != 0) {
                    ids = gameLabelAdapter.dataToString();
                }
                if (count2 != 0) {
                    if (TextUtils.isEmpty(ids)) {
                        ids = emotionLabelAdapter.dataToString();
                    } else {
                        ids += "," + emotionLabelAdapter.dataToString();
                    }
                }
                MvpPre.addLabel(ids);
                break;
            case R.id.iv_skip:
                String roomId = SPUtil.getString(Constant.Channel.ROOMID);
                startActivity(new Intent(this, HomeActivity.class).putExtra("roomId",roomId));
                break;
        }
    }


}
