package com.spadea.xqipao.ui.live.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.data.CharmModel;
import com.spadea.xqipao.data.RoomRankingModel;
import com.spadea.xqipao.ui.live.presenter.RoomRankingPresenter;
import com.spadea.xqipao.ui.room.activity.LiveRoomActivity;
import com.spadea.xqipao.utils.dialog.RoomPasswordDialog;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.live.adapter.RoomRankingAdapter;
import com.spadea.xqipao.ui.live.contacts.RoomRankingContacts;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;


/**
 * 房间   家族榜
 */
public class RoomRankingFragment extends BaseFragment<RoomRankingPresenter> implements RoomRankingContacts.View {


    @BindView(R.id.riv_no2)
    RoundedImageView rivNo2;
    @BindView(R.id.riv_no1)
    RoundedImageView rivNo1;
    @BindView(R.id.riv_no3)
    RoundedImageView rivNo3;
    @BindView(R.id.tv_no2_nickname)
    TextView tvNo2Nickname;
    @BindView(R.id.tv_no2_chram_num)
    TextView tvNo2ChramNum;
    @BindView(R.id.tv_no1_nickname)
    TextView tvNo1Nickname;
    @BindView(R.id.tv_no1_charm_num)
    TextView tvNo1CharmNum;
    @BindView(R.id.tv_no3_nickname)
    TextView tvNo3Nickname;
    @BindView(R.id.tv_no3_charm_num)
    TextView tvNo3CharmNum;
    @BindView(R.id.rl_ranking)
    RelativeLayout rlRanking;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rl_ranking_list)
    RelativeLayout rlRankingList;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.roundedimageview)
    RoundedImageView roundedimageview;
    @BindView(R.id.rl_buttom)
    RelativeLayout rlButtom;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_no)
    TextView tvNo;
    @BindView(R.id.tv_distance)
    TextView tvDistance;

    private RoomRankingAdapter roomRankingAdapter;
    private RoomPasswordDialog roomPasswordDialog;


    public static Fragment newInstance(String roomId, int type) {
        RoomRankingFragment roomRankingFragment = new RoomRankingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("roomId", roomId);
        roomRankingFragment.setArguments(bundle);
        return roomRankingFragment;
    }


    @Override
    protected RoomRankingPresenter bindPresenter() {
        return new RoomRankingPresenter(this, mContext);
    }


    @Override
    protected void initData() {
        MvpPre.getRoomRankingList();
    }

    @Override
    protected void initView(View rootView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        CommonEmptyView commonEmptyView = new CommonEmptyView(mContext);
        recyclerView.setAdapter(roomRankingAdapter = new RoomRankingAdapter());
        roomRankingAdapter.setEmptyView(commonEmptyView);
        roomRankingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RoomRankingModel item = roomRankingAdapter.getItem(position);
                startRoom(item);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_room_banking;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @Override
    public void setNo1(RoomRankingModel data) {
        ImageLoader.loadHead(getContext(), rivNo1, data.getPicture());
        tvNo1Nickname.setText(data.getName());
        tvNo1CharmNum.setText("魅力值 " + data.getNumber_format());
        rivNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRoom(data);
            }
        });
    }

    @Override
    public void setNo2(RoomRankingModel data) {
        ImageLoader.loadHead(getContext(), rivNo2, data.getPicture());
        tvNo2Nickname.setText(data.getName());
        tvNo2ChramNum.setText("魅力值 " + data.getNumber_format());
        rivNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRoom(data);
            }
        });
    }

    @Override
    public void setNo3(RoomRankingModel data) {
        ImageLoader.loadHead(getContext(), rivNo3, data.getPicture());
        tvNo3Nickname.setText(data.getName());
        tvNo3CharmNum.setText("魅力值 " + data.getNumber_format());
        rivNo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRoom(data);
            }
        });
    }

    private void startRoom(RoomRankingModel data) {
        LiveRoomActivity.start(getActivity(), data.getRoom_id());
    }

    @Override
    public void setListData(List<RoomRankingModel> list) {
        roomRankingAdapter.setNewData(list);
    }

    @Override
    public void setUserData(CharmModel.MyBean myBean) {
        ImageLoader.loadHead(getContext(), roundedimageview, myBean.getHead_picture());
        tvUserName.setText(myBean.getNickname());
        tvNo.setText("目前排名 " + myBean.getRank());
        if (myBean.getRank() > 10) {
            tvDistance.setVisibility(View.VISIBLE);
            tvDistance.setText("距离上榜还差" + (myBean.getRank() - 10));
        } else {
            tvDistance.setVisibility(View.GONE);
        }
    }

    @Override
    public void networkCompletion() {
        srl.finishRefresh();
    }


}
