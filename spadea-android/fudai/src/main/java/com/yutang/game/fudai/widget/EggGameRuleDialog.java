package com.yutang.game.fudai.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;


import com.blankj.utilcode.util.ConvertUtils;
import com.qpyy.libcommon.widget.dialog.BaseDialog;
import com.yutang.game.fudai.R;
import com.yutang.game.fudai.R2;

import butterknife.BindView;
import butterknife.OnClick;


public class EggGameRuleDialog extends BaseDialog {
    @BindView(R2.id.tv_content)
    TextView mTvContent;

//    @BindView(R.id.tv_title)
//    TextView mTvTitle;


    public EggGameRuleDialog(@NonNull Context context, String title, String content) {
        super(context);
        //mTvTitle.setText(title);
        mTvContent.setText(R.string.game_rule);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_egg_game_rule;
    }

    @Override
    public void initView() {
        getWindow().setLayout(ConvertUtils.dp2px(350), ConvertUtils.dp2px(468));
    }



    @Override
    public void initData() {

    }

    @OnClick(R2.id.iv_close)
    public void onViewClicked() {
        dismiss();
    }
}
