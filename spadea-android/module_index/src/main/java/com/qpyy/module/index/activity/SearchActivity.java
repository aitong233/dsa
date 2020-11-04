package com.qpyy.module.index.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.widget.dialog.CommonDialog;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;
import com.qpyy.module.index.adapter.SearchHistoryAdapter;
import com.qpyy.module.index.adapter.SearchRecordAdapter;
import com.qpyy.module.index.adapter.SearchRoomResultAdapter;
import com.qpyy.module.index.adapter.SearchUserResultAdapter;
import com.qpyy.module.index.bean.RecordSection;
import com.qpyy.module.index.bean.RoomResultResp;
import com.qpyy.module.index.bean.SearchResp;
import com.qpyy.module.index.bean.UserResultResp;
import com.qpyy.module.index.contacts.SearchContacts;
import com.qpyy.module.index.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.INDEX_SEARCH)
public class SearchActivity extends BaseMvpActivity<SearchPresenter> implements SearchContacts.View, CommonDialog.OnClickListener {

    @BindView(R2.id.recycle_view_history)
    RecyclerView recyclerViewHistory;
    @BindView(R2.id.edit_query)
    EditText editTextQuery;
    @BindView(R2.id.ll_history)
    LinearLayout llHistory;
    @BindView(R2.id.recycle_view_record)
    RecyclerView recyclerViewRecord;
    @BindView(R2.id.recycle_view_room)
    RecyclerView recyclerViewRoom;
    @BindView(R2.id.recycle_view_user)
    RecyclerView recyclerViewUser;
    @BindView(R2.id.tv_search)
    TextView textViewSerarch;
    @BindView(R2.id.iv_close)
    ImageView imageViewClose;
    @BindView(R2.id.rl_room)
    RelativeLayout relativeLayoutRoom;
    @BindView(R2.id.rl_user)
    RelativeLayout relativeLayoutUser;
    @BindView(R2.id.tv_room_count)
    TextView textViewRoomCount;
    @BindView(R2.id.rl_null)
    RelativeLayout relativeLayoutNull;
    @BindView(R2.id.rl_result)
    RelativeLayout relativeLayoutResult;
    @BindView(R2.id.nestedscrollview)
    NestedScrollView nestedScrollView;


    private String keyWord;
    private List<RoomResultResp.RoomResultInfo> roomResultInfoList = new ArrayList<>();


    private SearchHistoryAdapter mSearchHistoryAdapter;
    private SearchRecordAdapter mSearchRecordAdapter;
    private SearchRoomResultAdapter mSearchRoomResultAdapter;
    private SearchUserResultAdapter mSearchUserResultAdapter;
    private CommonDialog commonDialog;

    @Override
    protected SearchPresenter bindPresenter() {
        return new SearchPresenter(this, this);
    }

    @Override
    protected void initData() {

        //搜索记录
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewRecord.setLayoutManager(linearLayoutManager);
        recyclerViewRecord.setAdapter(mSearchRecordAdapter = new SearchRecordAdapter());
        mSearchRecordAdapter.bindToRecyclerView(recyclerViewRecord);
        mSearchRecordAdapter.setEmptyView(R.layout.index_view_empty);
        //搜索历史
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        recyclerViewHistory.setLayoutManager(flexboxLayoutManager);
        recyclerViewHistory.setAdapter(mSearchHistoryAdapter = new SearchHistoryAdapter());

        //搜索到用户数据
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUser.setAdapter(mSearchUserResultAdapter = new SearchUserResultAdapter());
        //搜索到房间信息
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewRoom.setLayoutManager(gridLayoutManager);
        recyclerViewRoom.setAdapter(mSearchRoomResultAdapter = new SearchRoomResultAdapter());

        editTextQuery.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyWord = editTextQuery.getText().toString();
                if (StringUtils.isEmpty(keyWord)) {
                    imageViewClose.setImageLevel(1);
                    imageViewClose.performClick();
                } else {
                    mSearchUserResultAdapter.setKeyWord(keyWord);
                    mSearchRecordAdapter.setKeyWord(keyWord);
                    imageViewClose.setImageLevel(2);
                    llHistory.setVisibility(View.GONE);
                    MvpPre.getSearchHistory();
                    MvpPre.fuzzyQuery(keyWord);
                }
            }
        });
        MvpPre.getSearchHistory();

        //快捷搜索列表点击
        mSearchRecordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MvpPre.saveSearchHistory(mSearchRecordAdapter.getItem(position).t);
                MvpPre.search(mSearchRecordAdapter.getItem(position).t);
            }
        });
        //搜索历史点击
        mSearchHistoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                editTextQuery.setText(mSearchHistoryAdapter.getItem(position));
                try {
                    editTextQuery.setSelection(mSearchHistoryAdapter.getItem(position).length());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //关注或取消关注
        mSearchUserResultAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                UserResultResp item = mSearchUserResultAdapter.getItem(position);
                MvpPre.followUser(item.getUser_id(), item.getFollow().equals("0") ? 1 : 2, position);
            }
        });
        //用户列表点击事件
        mSearchUserResultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserResultResp item = mSearchUserResultAdapter.getItem(position);
                if (item != null) {
                    ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", item.getUser_id()).navigation();
                }
            }
        });
        //房间列表点击时事件
        mSearchRoomResultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RoomResultResp.RoomResultInfo item = mSearchRoomResultAdapter.getItem(position);
                if (item != null) {
                    ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", item.getRoom_id()).navigation();
                }
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.index_activity_search;
    }

    /**
     * 搜索历史
     *
     * @param data
     */
    @Override
    public void setSearchHistory(List<String> data) {
        mSearchHistoryAdapter.setNewData(data);
    }

    /**
     * 搜索的结果
     *
     * @param data
     */
    @Override
    public void setSearch(SearchResp data) {
        recyclerViewRecord.setVisibility(View.GONE);
        llHistory.setVisibility(View.GONE);
        relativeLayoutResult.setVisibility(View.VISIBLE);
        RoomResultResp room_result = data.getRoom_result();
        List<UserResultResp> user_result = data.getUser_result();
        if (room_result.getCount() == 0 && (user_result == null || user_result.size() == 0)) {
            relativeLayoutNull.setVisibility(View.VISIBLE);
            nestedScrollView.setVisibility(View.GONE);
        } else {
            relativeLayoutNull.setVisibility(View.GONE);
            nestedScrollView.setVisibility(View.VISIBLE);
            if (room_result.getCount() == 0) {
                recyclerViewRoom.setVisibility(View.GONE);
                relativeLayoutRoom.setVisibility(View.GONE);
            } else {
                roomResultInfoList = room_result.getList();
                recyclerViewRoom.setVisibility(View.VISIBLE);
                relativeLayoutRoom.setVisibility(View.VISIBLE);
                if (room_result.getList().size() > 2) {
                    mSearchRoomResultAdapter.setNewData(room_result.getList().subList(0, 2));
                } else {
                    mSearchRoomResultAdapter.setNewData(room_result.getList());
                }
                textViewRoomCount.setText("全部  " + room_result.getCount());
            }
            if (user_result != null && user_result.size() != 0) {
                relativeLayoutUser.setVisibility(View.VISIBLE);
                recyclerViewUser.setVisibility(View.VISIBLE);
                mSearchUserResultAdapter.setNewData(user_result);
            } else {
                relativeLayoutUser.setVisibility(View.GONE);
                recyclerViewUser.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 模糊快捷查询
     *
     * @param data
     */
    @Override
    public void setFuzzyQuery(List<RecordSection> data) {
        recyclerViewRecord.setVisibility(View.VISIBLE);
        llHistory.setVisibility(View.GONE);
        relativeLayoutResult.setVisibility(View.GONE);
        mSearchRecordAdapter.setNewData(data);
    }

    /**
     * 关注成功
     *
     * @param type
     * @param postion
     */
    @Override
    public void followUserSuccess(int type, int postion) {
        UserResultResp item = mSearchUserResultAdapter.getItem(postion);
        item.setFollow(type == 1 ? "1" : "0");
        mSearchUserResultAdapter.notifyItemChanged(postion, item);
    }


    @OnClick({R2.id.iv_delete, R2.id.tv_search, R2.id.iv_close, R2.id.tv_room_count, R2.id.iv_back})
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.iv_delete) {
            if (commonDialog == null) {
                commonDialog = new CommonDialog(this);
                commonDialog.setmOnClickListener(this);
            }
            commonDialog.show();
        } else if (id == R.id.tv_search) {
            if (keyWord != null && !TextUtils.isEmpty(keyWord.trim())) {
                MvpPre.saveSearchHistory(keyWord);
                MvpPre.search(keyWord);

            } else {
                ToastUtils.show("搜索内容不能为空！");
            }
        } else if (id == R.id.iv_close) {
            if (!StringUtils.isEmpty(keyWord)) {
                editTextQuery.setText("");
            }
            recyclerViewRecord.setVisibility(View.GONE);
            llHistory.setVisibility(View.VISIBLE);
            relativeLayoutResult.setVisibility(View.GONE);
        } else if (id == R.id.tv_room_count) {
            mSearchRoomResultAdapter.setNewData(roomResultInfoList);
            relativeLayoutRoom.setVisibility(View.GONE);
            relativeLayoutUser.setVisibility(View.GONE);
            recyclerViewUser.setVisibility(View.GONE);
        } else if (id == R.id.iv_back) {
            finish();
        }
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
        MvpPre.deleteSearchHistory();
        MvpPre.getSearchHistory();
    }


}
