package com.qpyy.module.index.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.widget.dialog.CommonDialog;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;
import com.qpyy.module.index.adapter.ChatRoomMeFollowAdapter;
import com.qpyy.module.index.adapter.ChatRoomMeFootAdapter;
import com.qpyy.module.index.adapter.ChatRoomMeManageAdapter;
import com.qpyy.module.index.adapter.ChatRoomMeRecommendFollowAdapter;
import com.qpyy.module.index.bean.AttentionResp;
import com.qpyy.module.index.bean.ManageRoomResp;
import com.qpyy.module.index.bean.MyFootResp;
import com.qpyy.module.index.bean.RecommendAttentionResp;
import com.qpyy.module.index.contacts.ChatRoomMeContacts;
import com.qpyy.module.index.event.BannerRefreshEvent;
import com.qpyy.module.index.presenter.ChatRoomMePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页-聊天室-我的
 */
public class ChatRoomMeFragment extends BaseMvpFragment<ChatRoomMePresenter> implements ChatRoomMeContacts.View {


    @BindView(R2.id.iv_fold_follow)
    ImageView imageViewFoldFollow;
    @BindView(R2.id.recycler_view_follow)
    RecyclerView recyclerViewFollow;
    @BindView(R2.id.ll_recommend_follow)
    LinearLayout linearLayoutRecommendFollow;
    @BindView(R2.id.recycle_view_recommend)
    RecyclerView recyclerViewRecommend;
    @BindView(R2.id.iv_manage)
    ImageView imageViewManage;
    @BindView(R2.id.recycle_view_manage)
    RecyclerView recyclerViewManage;
    @BindView(R2.id.iv_delete_foot)
    ImageView imageViewDeleteFoot;
    @BindView(R2.id.recycle_view_foot)
    RecyclerView recyclerViewFoot;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;


    private boolean isFollow = false;
    private boolean isManage = true;

    private int page = 1;

    private ChatRoomMeFootAdapter mChatRoomMeFootAdapter;
    private ChatRoomMeManageAdapter mChatRoomMeManageAdapter;
    private ChatRoomMeFollowAdapter mChatRoomMeFollowAdapter;
    private ChatRoomMeRecommendFollowAdapter mChatRoomMeRecommendFollowAdapter;

    public static ChatRoomMeFragment newInstance() {
        ChatRoomMeFragment fragment = new ChatRoomMeFragment();
        return fragment;
    }


    @Override
    protected ChatRoomMePresenter bindPresenter() {
        return new ChatRoomMePresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        MvpPre.getAttentionList();
    }

    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        MvpPre.getMyFoot(page);
    }

    @Override
    protected void initView() {
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                MvpPre.getMyFoot(page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                EventBus.getDefault().post(new BannerRefreshEvent());
                page = 1;
                MvpPre.getAttentionList();
                MvpPre.getMyFoot(page);
                MvpPre.getManageLists();
            }
        });
        imageViewFoldFollow.setImageLevel(1);
        recyclerViewFollow.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewFollow.setAdapter(mChatRoomMeFollowAdapter = new ChatRoomMeFollowAdapter());
        //推荐关注
        recyclerViewRecommend.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewRecommend.setAdapter(mChatRoomMeRecommendFollowAdapter = new ChatRoomMeRecommendFollowAdapter());
        //我关注的房间
        recyclerViewManage.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewManage.setAdapter(mChatRoomMeManageAdapter = new ChatRoomMeManageAdapter());
        //我的足迹
        recyclerViewFoot.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFoot.setAdapter(mChatRoomMeFootAdapter = new ChatRoomMeFootAdapter());
        mChatRoomMeFootAdapter.bindToRecyclerView(recyclerViewFoot);
        mChatRoomMeFootAdapter.setEmptyView(R.layout.index_view_empty_foot);
        mChatRoomMeManageAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                ManageRoomResp item = mChatRoomMeManageAdapter.getItem(position);
                if (item != null && item.getSys_type_id() < 1) {
                    mChatRoomMeManageAdapter.setIndex(position);
                }
                return false;
            }
        });
        mChatRoomMeFollowAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                mChatRoomMeFollowAdapter.setIndex(position);
                return false;
            }
        });
        mChatRoomMeRecommendFollowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RecommendAttentionResp item = mChatRoomMeRecommendFollowAdapter.getItem(position);
                if (item != null) {
                    ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", item.getRoom_id()).navigation();
                }
            }
        });
        mChatRoomMeManageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (isAdded() && getActivity() != null) {
                    CommonDialog commonDialog = new CommonDialog(getActivity());
                    commonDialog.setContent("确认删除当前管理的厅吗？");
                    commonDialog.setmOnClickListener(new CommonDialog.OnClickListener() {
                        @Override
                        public void onLeftClick() {

                        }

                        @Override
                        public void onRightClick() {
                            MvpPre.removeManage(mChatRoomMeManageAdapter.getItem(position).getId(), position);
                        }
                    });
                }

            }
        });
        mChatRoomMeFollowAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MvpPre.removeFavorite(mChatRoomMeFollowAdapter.getItem(position).getRoom_id(), position);
            }
        });
        //关注列表点击
        mChatRoomMeFollowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AttentionResp item = mChatRoomMeFollowAdapter.getItem(position);
                if (item != null) {
                    ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", item.getRoom_id()).navigation();
                }
            }
        });
        //点击管理的厅
        mChatRoomMeManageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ManageRoomResp item = mChatRoomMeManageAdapter.getItem(position);
                if (item != null) {
                    ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", item.getRoom_id()).navigation();
                }
            }
        });
        //点击足迹
        mChatRoomMeFootAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyFootResp item = mChatRoomMeFootAdapter.getItem(position);
                if (item != null) {
                    ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", item.getRoom_id()).navigation();
                }
            }
        });
    }

    @OnClick({R2.id.iv_fold_follow, R2.id.tv_change, R2.id.iv_onekey, R2.id.iv_manage, R2.id.iv_delete_foot})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_fold_follow) {
            if (isFollow) {
                imageViewFoldFollow.setImageLevel(1);
                recyclerViewFollow.setVisibility(View.VISIBLE);
                MvpPre.getAttentionList();
            } else {
                imageViewFoldFollow.setImageLevel(0);
                recyclerViewFollow.setVisibility(View.GONE);
            }
            isFollow = !isFollow;
        } else if (id == R.id.tv_change) {
            MvpPre.getRecommendAttentionList();
        } else if (id == R.id.iv_onekey) {
            List<RecommendAttentionResp> data = mChatRoomMeRecommendFollowAdapter.getData();
            if (data != null && data.size() != 0) {
                MvpPre.ghostAttention(data);
            }
        } else if (id == R.id.iv_manage) {
            if (isManage) {
                imageViewManage.setImageLevel(1);
                recyclerViewManage.setVisibility(View.VISIBLE);
                MvpPre.getManageLists();
            } else {
                imageViewManage.setImageLevel(0);
                recyclerViewManage.setVisibility(View.GONE);
            }
            isManage = !isManage;
        } else if (id == R.id.iv_delete_foot) {
            showDelFootDialog();
        }
    }

    private void showDelFootDialog() {
        if (isAdded() && getActivity() != null) {
            CommonDialog commonDialog = new CommonDialog(getActivity());
            commonDialog.setContent("确认清空您的足迹吗？");
            commonDialog.setmOnClickListener(new CommonDialog.OnClickListener() {
                @Override
                public void onLeftClick() {

                }

                @Override
                public void onRightClick() {
                    MvpPre.delfoot();
                }
            });
            commonDialog.show();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_fragment_chatroom_me;
    }

    @Override
    public void setAttentionListData(List<AttentionResp> data) {
        mChatRoomMeFollowAdapter.setNewData(data);
        if (data.size() <= 3) {
            MvpPre.getRecommendAttentionList();
        } else {
            linearLayoutRecommendFollow.setVisibility(View.GONE);
        }
    }

    @Override
    public void setRecommendAttentionList(List<RecommendAttentionResp> data) {
        linearLayoutRecommendFollow.setVisibility(View.VISIBLE);
        mChatRoomMeRecommendFollowAdapter.setNewData(data);
    }

    @Override
    public void ghostAttentionSuccess() {
        MvpPre.getAttentionList();
    }

    @Override
    public void setManageData(List<ManageRoomResp> data) {
        mChatRoomMeManageAdapter.setIndex(-1);
        mChatRoomMeManageAdapter.setNewData(data);
    }

    @Override
    public void removeManageSuccess(int postion) {
        mChatRoomMeManageAdapter.setIndex(-1);
        mChatRoomMeManageAdapter.remove(postion);
    }

    @Override
    public void setMyFootData(List<MyFootResp> data, int page) {
        if (data == null || data.size() == 0) {
            imageViewDeleteFoot.setVisibility(View.GONE);
        } else {
            imageViewDeleteFoot.setVisibility(View.VISIBLE);
        }
        if (page == 1) {
            mChatRoomMeFootAdapter.setNewData(data);
        } else {
            if (data == null || data.size() == 0) {
                mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mChatRoomMeFootAdapter.addData(data);
            }
        }
    }

    @Override
    public void delfootSuccess() {
        page = 1;
        MvpPre.getMyFoot(page);
    }

    @Override
    public void finishRefresh() {
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void removeFavoriteSuccess(int position) {
        mChatRoomMeFollowAdapter.setIndex(-1);
        mChatRoomMeFollowAdapter.remove(position);
    }
}
