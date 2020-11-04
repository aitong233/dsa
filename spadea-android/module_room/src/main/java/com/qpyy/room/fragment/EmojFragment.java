package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.widget.dialog.CommonDialog;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.EmojAdapter;
import com.qpyy.room.adapter.ViewPagerAdapter;
import com.qpyy.room.bean.ExclusiveEmojiResp;
import com.qpyy.room.bean.SendFaceEvent;
import com.qpyy.room.contacts.EmojContacts;
import com.qpyy.room.presenter.EmojPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EmojFragment extends BaseMvpFragment<EmojPresenter> implements EmojContacts.View {

    @BindView(R2.id.view_pager)
    ViewPager viewPager;
    @BindView(R2.id.ll_dot)
    LinearLayout llDot;


    private int type = 0;
    private int isGameRoom = 0;
    private String roomId;
    private String pitNumber;
    private int curIndex = 0;//当前页
    private LayoutInflater mInflater;

    public static EmojFragment newInstance(int type, String roomId, String pitNumber, int isGameRoom) {
        EmojFragment emojFragment = new EmojFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("isGameRoom", isGameRoom);
        bundle.putString("roomId", roomId);
        bundle.putString("pitNumber", pitNumber);
        emojFragment.setArguments(bundle);
        return emojFragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        type = arguments.getInt("type");
        isGameRoom = arguments.getInt("isGameRoom");
        roomId = arguments.getString("roomId");
        pitNumber = arguments.getString("pitNumber");
    }

    @Override
    protected EmojPresenter bindPresenter() {
        return new EmojPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        if (type == 0) {
            MvpPre.getEmojData(isGameRoom);
        } else {
            MvpPre.getExclusiveEmojData();
        }
    }

    @Override
    protected void initView() {
        mInflater = LayoutInflater.from(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragmenr_room_emoj;
    }

    @Override
    public void setEmojData(List<List<ExclusiveEmojiResp>> data) {
        List<View> viewList = new ArrayList<>();
        for (List<ExclusiveEmojiResp> item : data) {
            RecyclerView recyclerView = (RecyclerView) mInflater.inflate(R.layout.room_vp_emoj, viewPager, false);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
            EmojAdapter emojAdapter = new EmojAdapter(item);
            recyclerView.setAdapter(emojAdapter);
            emojAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ExclusiveEmojiResp item1 = emojAdapter.getItem(position);
                    if (type == 0) {
                        switch (item1.getName()) {
                            case "抽签":
                                MvpPre.roomPoll(roomId, pitNumber);
                                break;
                            case "发牌x1":
                                MvpPre.ranCards("1");
                                break;
                            case "发牌x2":
                                MvpPre.ranCards("2");
                                break;
                            case "发牌x3":
                                MvpPre.ranCards("3");
                                break;
                            case "发牌x5":
                                MvpPre.ranCards("5");
                                break;
                            case "掷骰子":
                                MvpPre.ranTouzi();
                                break;
                            default:
//                                MvpPre.sendFace(roomId, item1.getId(), pitNumber, 1);
                                EventBus.getDefault().post(new SendFaceEvent(item1.getPicture(), item1.getSpecial(), roomId, item1.getId(), pitNumber, 1));//直接添加到公屏消息
                                sendSuccess();
                                break;
                        }
                    } else {
                        if ("0".equals(item1.getAuth())) {
                            showTipDialog();
                            return;
                        }
//                        MvpPre.sendFace(roomId, item1.getId(), pitNumber, 2);
                        EventBus.getDefault().post(new SendFaceEvent(item1.getPicture(), item1.getSpecial(), roomId, item1.getId(), pitNumber, 2));//直接添加到公屏消息
                    }
                }
            });
            viewList.add(recyclerView);
            llDot.addView(mInflater.inflate(R.layout.room_emoj_dot, null));
            llDot.getChildAt(0).findViewById(R.id.image)
                    .setBackgroundResource(R.mipmap.room_gift_indicatior_select);
        }
        viewPager.setAdapter(new ViewPagerAdapter(viewList, getActivity()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                llDot.getChildAt(curIndex)
                        .findViewById(R.id.image)
                        .setBackgroundResource(R.mipmap.room_gift_indicatior_normal);
                // 圆点选中
                llDot.getChildAt(position)
                        .findViewById(R.id.image)
                        .setBackgroundResource(R.mipmap.room_gift_indicatior_select);
                curIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void showTipDialog() {
        UserBean userBean = BaseApplication.getIns().getUser();
        CommonDialog commonDialog = new CommonDialog(getActivity());
        if (userBean.getRank_info() != null && userBean.getRank_info().nobility_id > 0) {
            commonDialog.setContent("此表情为爵位专属表情此表情为爵位专属表情，是否开通爵位?");
        } else {
            commonDialog.setContent("您的等级暂不支持使用此表情，是否提升等级?");
        }
        commonDialog.setLeftText("再想想");
        commonDialog.setRightText("去开通");
        commonDialog.setmOnClickListener(new CommonDialog.OnClickListener() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {
                ARouter.getInstance().build(ARouteConstants.ME_GRADEACTIVITY).withInt("type", 0).navigation();
            }
        });
        commonDialog.show();
    }

    @Override
    public void sendSuccess() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof DialogFragment) {
            ((DialogFragment) parentFragment).dismiss();
        }
    }


}
