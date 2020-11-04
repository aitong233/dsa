package com.qpyy.room.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;

import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.RoomAddAdapter;
import com.qpyy.room.bean.SearchUserModel;
import com.qpyy.room.contacts.SearchAdminContacts;
import com.qpyy.room.event.RoomInfoUpdateEvent;
import com.qpyy.room.presenter.SearchAdminPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.activity
 * 创建人 黄强
 * 创建时间 2020/7/30 18:54
 * 描述 describe
 */
@Route(path = ARouteConstants.ROOM_MANAGE)
public class ManageAddActivity extends BaseMvpActivity<SearchAdminPresenter> implements SearchAdminContacts.View {

    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.et_query)
    EditText etQuery;
    @BindView(R2.id.iv_search_submit)
    ImageView ivSearchSubmit;
    @BindView(R2.id.rl_search)
    RecyclerView rlSearch;
    @BindView(R2.id.srl_search_user_refresh)
    SmartRefreshLayout srlSearchUserRefresh;


    @Autowired
    public int addType;//添加对象类型（管理员 0 /黑名单  1）
    @Autowired
    public String roomId;//房间ID

    private RoomAddAdapter addAdapter;
    private String nickName;//用户名


    @Override
    protected SearchAdminPresenter bindPresenter() {
        return new SearchAdminPresenter(this, this);
    }

    @Override
    protected void initView() {
        super.initView();
        if (addType == 0) {
            tvTitle.setText("添加管理员");
        } else {
            tvTitle.setText("添加黑名单");
        }
        rlSearch.setLayoutManager(new LinearLayoutManager(this));
        addAdapter = new RoomAddAdapter(addType);
        rlSearch.setAdapter(addAdapter);
        addAdapter.bindToRecyclerView(rlSearch);
        addAdapter.setEmptyView(R.layout.room_search_layout_empty);//设置空布局
        srlSearchUserRefresh.setOnRefreshListener(new OnRefreshListener() {//刷新列表
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                String keyword = etQuery.getText().toString().trim();
                MvpPre.searchUser(roomId, keyword, addType);
            }
        });
        etQuery.addTextChangedListener(new TextWatcher() {//搜索监听
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String keyword = etQuery.getText().toString().trim();
                if (TextUtils.isEmpty(keyword)) {
//                    MvpPre.getList(roomId, addType);
                } else {
                    MvpPre.searchUser(roomId, keyword, addType);
                }
            }
        });

        addAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SearchUserModel item = (SearchUserModel) adapter.getItem(position);
                if (addType == 0) {
                    if (item.getValue().equals("0")) {
                        MvpPre.addAdmin(roomId, item.getUser_id(), item, position);
                        if(view.getId() == R.id.add_user_btn){
                            view.setVisibility(View.GONE);//隐藏按钮
                            nickName = item.getNickname();//保存用户名
                        }
                    } else {
//                        MvpPre.deleteAdmin(roomId, item.getUser_id(), item, position);
                    }
                } else {
                    if (item.getValue().equals("0")) {
                        MvpPre.addBlacklist(roomId, item.getUser_id(), item, position);
                        if(view.getId() == R.id.add_user_btn){
                            view.setVisibility(View.GONE);
                            nickName = item.getNickname();
                        }
                    } else {
//                        MvpPre.deleteBlacklist(roomId, item.getUser_id(), item, position);
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        etQuery.requestFocus();
//        MvpPre.getList(roomId, addType);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_activity_add;
    }

    @Override
    public void addAdminSuccess(String userId) {
        ToastUtils.show("管理员添加成功");

    }

    @Override
    public void addBlacklistSuccess(String userId) {
        ToastUtils.show("黑名单添加成功");
//        MvpPre.kickOut(userId, roomId,nickName);//踢出房间
    }

    @Override
    public void deleteAdminSuccess(String userId) {
        ToastUtils.show("管理员删除成功");
    }

    @Override
    public void deleteBlacklistSuccess(String userId) {
        ToastUtils.show("黑名单删除成功");
    }

    @Override
    public void setSearchUserData(List<SearchUserModel> data) {
        addAdapter.setNewData(data);//得到数据设置到适配器
    }

    @Override
    public void searchUserComplete() {
        srlSearchUserRefresh.finishRefresh();
    }

    @Override
    public void success(SearchUserModel data, int position) {
        EventBus.getDefault().post(new RoomInfoUpdateEvent());
        addAdapter.notifyItemChanged(position, data);//数据更新
    }

    /**
     * 踢出房间成功
     * @param userName
     */
    @Override

    public void kickOutSuccess(String userName) {
        ToastUtils.show(userName + "被踢出房间");
    }


    @OnClick({R2.id.iv_back})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_back) {
            finish();
        }
    }

}
