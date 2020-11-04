package com.spadea.xqipao.ui.room.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.SearchUserModel;
import com.spadea.xqipao.ui.room.contacts.AddContacts;
import com.spadea.xqipao.ui.room.presenter.AddPresenter;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.room.adapter.AddAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddActivity extends BaseActivity<AddPresenter> implements AddContacts.View {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ed_serach)
    EditText edSerach;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private int type;
    private String roomId;
    private AddAdapter addAdapter;


    public static void start(Context context, int type, String roomId) {
        Intent intent = new Intent(context, AddActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("roomId", roomId);
        context.startActivity(intent);
    }


    public AddActivity() {
        super(R.layout.activity_add);
    }

    @Override
    protected void initData() {
        MvpPre.getList(roomId, type);
    }

    @Override
    protected void initView() {
        viewLine.setVisibility(View.INVISIBLE);
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setAndroidNativeLightStatusBar(this, false);
        tvTitle.setTextColor(Color.parseColor("#FFFFFF"));
        ivBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_ff));
        CommonEmptyView commonEmptyView = new CommonEmptyView(this);
        type = getIntent().getIntExtra("type", 0);
        roomId = getIntent().getStringExtra("roomId");
        if (type == 0) {
            tvTitle.setText("添加管理员");
        } else {
            tvTitle.setText("添加黑名单");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(addAdapter = new AddAdapter());
        addAdapter.setType(type);
        addAdapter.setEmptyView(commonEmptyView);
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                String keyword = edSerach.getText().toString().trim();
                MvpPre.searChUser(roomId, keyword, type);
            }
        });
        edSerach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String keyword = edSerach.getText().toString().trim();
                if (TextUtils.isEmpty(keyword)) {
                    MvpPre.getList(roomId, type);
                } else {
                    MvpPre.searChUser(roomId, keyword, type);
                }
            }
        });
        addAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchUserModel item = (SearchUserModel) adapter.getItem(position);
                if (type == 0) {
                    if (item.getValue().equals("0")) {
                        MvpPre.addManager(roomId, item.getUser_id(), item, position);
                    } else {
                        MvpPre.deleteManager(roomId, item.getUser_id(), item, position);
                    }
                } else {
                    if (item.getValue().equals("0")) {
                        MvpPre.addRorbid(roomId, item.getUser_id(), item, position);
                    } else {
                        MvpPre.deleteForbid(roomId, item.getUser_id(), item, position);
                    }
                }
            }
        });
    }

    @Override
    protected AddPresenter bindPresenter() {
        return new AddPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Override
    public void addAdminSuccess(String userId) {
        ToastUtils.showShort("管理员添加成功");
    }

    @Override
    public void addRorbidSuccess(String userId) {
        ToastUtils.showShort("黑名单添加成功");
    }

    @Override
    public void deleteManagerSuccess(String userId) {
        ToastUtils.showShort("删除管理员成功");
    }

    @Override
    public void deleteForbidSuccess(String userId) {
        ToastUtils.showShort("删除黑名单成功");

    }

    @Override
    public void setSearChUserData(List<SearchUserModel> data) {
        addAdapter.setNewData(data);
    }

    @Override
    public void searchUserComplete() {
        srl.finishRefresh();
    }

    @Override
    public void success(SearchUserModel data, int posiont) {
        addAdapter.notifyItemChanged(posiont, data);
    }


    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        finish();
    }

}
