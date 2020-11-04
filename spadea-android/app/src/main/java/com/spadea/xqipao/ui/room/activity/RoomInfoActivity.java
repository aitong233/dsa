package com.spadea.xqipao.ui.room.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.makeramen.roundedimageview.RoundedImageView;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.RoomExtraModel;
import com.spadea.xqipao.ui.room.contacts.RoomInfoContacts;
import com.spadea.xqipao.ui.room.presenter.RoomInfoPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.room.adapter.RoomInfoAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 房间信息
 */

@Route(path = ARouters.ROOM_ROOMINFO, name = "房间信息")
public class RoomInfoActivity extends BaseActivity<RoomInfoPresenter> implements RoomInfoContacts.View {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.riv)
    RoundedImageView riv;
    @BindView(R.id.tv_room_name)
    TextView tvRoomName;
    @BindView(R.id.tv_room_id)
    TextView tvRoomId;
    @BindView(R.id.tv_popularity)
    TextView tvPopularity;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.riv_host_user)
    RoundedImageView rivHostUser;
    @BindView(R.id.tv_manager_count)
    TextView tvManagerCount;
    @BindView(R.id.recyclerview_admin)
    RecyclerView recyclerviewAdmin;
    @BindView(R.id.tv_black_count)
    TextView tvBlackCount;
    @BindView(R.id.recyclerview_blacklist)
    RecyclerView recyclerviewBlacklist;
    @BindView(R.id.iv_add_admin)
    ImageView ivAddAdmin;
    @BindView(R.id.iv_add_black)
    ImageView ivAddBlack;

    @Autowired
    public String roomId = "";
    @Autowired
    public String password = "";
    @Autowired
    public int jurisdiction = 0;
    @Autowired
    public int isFm = 0;

    private RoomInfoAdapter adminRoomInfoAdapter;
    private RoomInfoAdapter blackRoomInfoAdapter;


    public RoomInfoActivity() {
        super(R.layout.activity_room_info_x);
    }

    @Override
    protected void initData() {
        if (jurisdiction == 0) {
            ivAddAdmin.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initView() {
        tvTitle.setText("房间信息");
        recyclerviewAdmin.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerviewBlacklist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerviewAdmin.setAdapter(adminRoomInfoAdapter = new RoomInfoAdapter());
        recyclerviewBlacklist.setAdapter(blackRoomInfoAdapter = new RoomInfoAdapter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        MvpPre.getRoomExtra(roomId, password);
    }


    @Override
    protected RoomInfoPresenter bindPresenter() {
        return new RoomInfoPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Override
    public void setRoomExtraSuccess(RoomExtraModel roomExtraModel) {
        tvRoomName.setText(roomExtraModel.getRoom_name());
        tvRoomId.setText("房间ID: " + roomExtraModel.getRoom_code());
        tvPopularity.setText("人气： " + roomExtraModel.getPopularity());
        if (TextUtils.isEmpty(roomExtraModel.getCover_picture())){
            ImageLoader.loadImage(this, riv, roomExtraModel.getHead_picture());
        }else {
            ImageLoader.loadImage(this, riv, roomExtraModel.getCover_picture());
        }

        ImageLoader.loadHead(this, rivHostUser, roomExtraModel.getHead_picture());
        tvManagerCount.setText("(" + roomExtraModel.getManager_list().size() + ")");
        tvBlackCount.setText("(" + roomExtraModel.getBlack_list().size() + ")");
        adminRoomInfoAdapter.setNewData(roomExtraModel.getManager_list());
        blackRoomInfoAdapter.setNewData(roomExtraModel.getBlack_list());
    }

    @Override
    public void delete(int type, int postion) {
        if (type == 0) {
            adminRoomInfoAdapter.remove(postion);
        } else {
            blackRoomInfoAdapter.remove(postion);
        }
    }

    @Override
    public void deleteManagerSuccess(String userId) {
        if (adminRoomInfoAdapter != null) {
            tvManagerCount.setText("(" + adminRoomInfoAdapter.getData().size() + ")");
        }
    }

    @Override
    public void deleteForbidSuccess(String userId) {
        if (blackRoomInfoAdapter != null) {
            tvBlackCount.setText("(" + blackRoomInfoAdapter.getData().size() + ")");
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_add_admin, R.id.tv_setting, R.id.iv_add_black})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add_admin:
                AddActivity.start(this, 0, roomId);
                break;
            case R.id.iv_add_black:
                AddActivity.start(this, 1, roomId);
                break;
            case R.id.tv_setting:
                RoomSettingActivity.start(this, roomId, password, isFm);
                break;
        }
    }

}
