package com.qpyy.room.dialog;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.base.BaseAppCompatActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.UpdateRoomName;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/21 17:07
 * 描述 describe
 */
@Route(path = ARouteConstants.EDIT_ROOM_NAME)
public class ModifyRoomNameDialog extends BaseAppCompatActivity {
    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_room_title)
    TextView tvRoomTitle;
    @BindView(R2.id.iv_commit)
    ImageView ivCommit;
    @BindView(R2.id.tv_hint_title)
    TextView tvHintTitle;
    @BindView(R2.id.et_name)
    EditText etName;

    @Autowired
    public String roomName;//房间名称

    @Override
    public int getLayoutId() {
        return R.layout.room_modify_room_name;
    }

    @Override
    public void initView() {
        initListener();//开启监听事件
    }

    @Override
    public void initData() {
        etName.setText(roomName);//初始化房间名
        try {
            etName.setSelection(roomName.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (roomName.equals(etName.getText().toString()) && roomName.length() > 0) {
                    ivCommit.setEnabled(false);//名字一样不能修改
                    ivCommit.setImageResource(R.mipmap.room_modify_room_name_can_not);
                } else if (etName.getText().toString().length() > 0) {//名字长度大于0
                    ivCommit.setEnabled(true);//允许修改
                    ivCommit.setImageResource(R.mipmap.room_modify_room_name);
                } else {//为空，不能点击确认
                    ivCommit.setEnabled(false);//名字一样不能修改
                    ivCommit.setImageResource(R.mipmap.room_modify_room_name_can_not);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R2.id.iv_back, R2.id.iv_commit})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.iv_commit) {
            EventBus.getDefault().post(new UpdateRoomName(etName.getText().toString()));//通知房间信息修改名称
            finish();
        }
    }
}
