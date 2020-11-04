package com.spadea.xqipao.ui.room.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.RoomExtraModel;
import com.spadea.xqipao.ui.room.contacts.RoomSettingContacts;
import com.spadea.xqipao.ui.room.presenter.RoomSettingPresenter;
import com.spadea.xqipao.utils.IndentTextWatcher;
import com.spadea.xqipao.ui.base.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RoomSettingActivity extends BaseActivity<RoomSettingPresenter> implements RoomSettingContacts.View {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.ed_room_name)
    EditText edRoomName;
    @BindView(R.id.sc)
    SwitchCompat sc;
    @BindView(R.id.ed_welcom)
    EditText edWelcom;
    @BindView(R.id.ed_ad)
    EditText edAd;
    @BindView(R.id.ll_ad)
    LinearLayout llAd;

    private RoomExtraModel mRoomExtraModel;
    private String roomId;
    private String password;


    public static void start(Context context, String roomId, String password, int isFm) {
        Intent intent = new Intent(context, RoomSettingActivity.class);
        intent.putExtra("roomId", roomId);
        intent.putExtra("password", password);
        intent.putExtra("isFm", isFm);
        context.startActivity(intent);
    }


    public RoomSettingActivity() {
        super(R.layout.activity_room_setting);
    }

    @Override
    protected void initData() {
        roomId = getIntent().getStringExtra("roomId");
        password = getIntent().getStringExtra("password");
        int isFm = getIntent().getIntExtra("isFm", 0);
        llAd.setVisibility(isFm == 1 ? View.VISIBLE : View.GONE);
        MvpPre.getRoomExtra(roomId, password);
    }

    @Override
    protected void initView() {
        tvTitle.setText("房间设置");
        tvRight.setText("完成");
        tvRight.setVisibility(View.VISIBLE);
        edWelcom.addTextChangedListener(new IndentTextWatcher());
    }

    @Override
    protected RoomSettingPresenter bindPresenter() {
        return new RoomSettingPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Override
    public void setRoomExtraSuccess(RoomExtraModel roomExtraModel) {
        mRoomExtraModel = roomExtraModel;
        edRoomName.setText(roomExtraModel.getRoom_name());
//        1.自由上麦 2.申请上麦
        if (roomExtraModel.getWheat().equals("1")) {
            sc.setChecked(true);
        } else {
            sc.setChecked(false);
        }
        edWelcom.setText(mRoomExtraModel.getGreeting());
        edAd.setText(mRoomExtraModel.getPlaying());
    }

    @Override
    public void editRoomSuccess() {
        ToastUtils.showShort("房间信息修改完成");
        finish();
    }


    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                String roomName = edRoomName.getText().toString();
                String welocm = edWelcom.getText().toString();
                String ad = edAd.getText().toString();
                if (TextUtils.isEmpty(roomName)) {
                    ToastUtils.showShort("请输入房间名称");
                    return;
                }
                String wheat;
                if (sc.isChecked()) {
                    wheat = "1";
                } else {
                    wheat = "2";
                }
                MvpPre.editRoom("", "", "", ad, roomId, roomName, "", "", welocm, wheat, "");
                break;

        }
    }

}
