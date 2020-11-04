package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ScreenUtils;
import com.spadea.xqipao.utils.dialog.BaseDialog;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.dialog.room
 * 创建人 王欧
 * 创建时间 2020/4/11 7:51 PM
 * 描述 describe
 */
public class RoomPlayingDialog extends BaseDialog {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_content)
    TextView mTvContent;

    private String content;

    public RoomPlayingDialog(@NonNull Context context, String content) {
        super(context);
        this.content = content;
        mTvContent.setText(content);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_room_playing;
    }

    @Override
    public void initView() {
        getWindow().setBackgroundDrawableResource(R.drawable.bg_room_playing_dialog);
        getWindow().setLayout((int) ((ScreenUtils.getScreenWidth() / 375.0) * 175), WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void initData() {

    }
}
