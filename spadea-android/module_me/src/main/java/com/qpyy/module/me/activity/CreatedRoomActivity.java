package com.qpyy.module.me.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.adapter.RoomTypeAdapter;
import com.qpyy.libcommon.bean.CheckTxtResp;
import com.qpyy.module.me.bean.RoomTypeInfo;
import com.qpyy.module.me.contacts.CreatedRoomConactos;
import com.qpyy.module.me.presenter.CreatedRoomPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = ARouteConstants.CREATED_ROOM, name = "创建房间")
public class CreatedRoomActivity extends BaseMvpActivity<CreatedRoomPresenter> implements CreatedRoomConactos.View {

    @BindView(R2.id.recycler_view_room_type)
    RecyclerView recyclerViewRoomType;

    @BindView(R2.id.tv_title)
    TextView tvTitle;

    @BindView(R2.id.ed_room_name)
    EditText editTextRoomName;

    private RoomTypeAdapter mRoomTypeAdapter;
    private String roomName;

    @Override
    protected CreatedRoomPresenter bindPresenter() {
        return new CreatedRoomPresenter(this, this);
    }

    @Override
    protected void initData() {
        List<RoomTypeInfo> roomTypeInfos = new ArrayList<>();
        roomTypeInfos.add(new RoomTypeInfo("聊天", 23));
        roomTypeInfos.add(new RoomTypeInfo("K歌", 21));
        mRoomTypeAdapter.setNewData(roomTypeInfos);
    }

    @Override
    protected void initView() {
        super.initView();
        tvTitle.setText("创建房间");
        recyclerViewRoomType.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewRoomType.setAdapter(mRoomTypeAdapter = new RoomTypeAdapter());
        mRoomTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mRoomTypeAdapter.setIndex(position);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_created_room;
    }

    @OnClick({R2.id.iv_back, R2.id.tv_save})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.tv_save) {
            roomName = editTextRoomName.getText().toString().trim();
            if (TextUtils.isEmpty(roomName)) {
                ToastUtils.show("请输入房间名");
                return;
            }
            MvpPre.checkTxt(roomName);
        }
    }

    @Override
    public void checkTxtSuccess(CheckTxtResp result) {
        if (result.getResult() == 0) {
            RoomTypeInfo roomTypeInfo = mRoomTypeAdapter.getSelect();
            MvpPre.addUserRoom(roomName, String.valueOf(roomTypeInfo.getType()));
        } else {
            ToastUtils.show("房间名称包含敏感词汇请重新输入");
        }
    }

    @Override
    public void addUserRoomSuccess(String roomId) {
        ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", roomId).navigation();
        finish();
    }
}
