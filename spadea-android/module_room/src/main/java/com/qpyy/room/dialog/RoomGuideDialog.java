package com.qpyy.room.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.blankj.utilcode.util.BarUtils;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.contacts.RoomGuideContacts;
import com.qpyy.room.presenter.RoomGuidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/7/28 16:20
 * 描述 房间引导
 */
public class RoomGuideDialog extends BaseMvpDialogFragment<RoomGuidePresenter> implements RoomGuideContacts.View {

    private static final String TAG = "RoomGuideDialog";
    @BindView(R2.id.iv_i_know)
    ImageView ivIKnow;
    @BindView(R2.id.iv_ins2)
    ImageView ivIns2;
    @BindView(R2.id.iv_i_know2)
    ImageView ivIKnow2;
    @BindView(R2.id.cl_guide2)
    ConstraintLayout clGuide2;
    @BindView(R2.id.iv_txt3)
    ImageView ivTxt3;
    @BindView(R2.id.iv_i_know3)
    ImageView ivIKnow3;
    @BindView(R2.id.iv_ins3)
    ImageView ivIns3;
    @BindView(R2.id.cl_guide3)
    ConstraintLayout clGuide3;
    @BindView(R2.id.iv_txt4)
    ImageView ivTxt4;
    @BindView(R2.id.iv_i_know4)
    ImageView ivIKnow4;
    @BindView(R2.id.iv_ins4)
    ImageView ivIns4;
    @BindView(R2.id.cl_guide4)
    ConstraintLayout clGuide4;
    @BindView(R2.id.iv_txt5)
    ImageView ivTxt5;
    @BindView(R2.id.iv_i_know5)
    ImageView ivIKnow5;
    @BindView(R2.id.iv_ins5)
    ImageView ivIns5;
    @BindView(R2.id.cl_guide5)
    ConstraintLayout clGuide5;
    @BindView(R2.id.cl_guide1)
    ConstraintLayout clGuide1;

    private String roomId;

    public static RoomGuideDialog newInstance(String roomId, int step) {//step步骤

        Bundle args = new Bundle();
        args.putString("roomId", roomId);
        args.putInt("step", step);
        RoomGuideDialog fragment = new RoomGuideDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        this.roomId = arguments.getString("roomId");
    }

    @Override
    public int getLayoutId() {
        return R.layout.room_fragment_guide;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
//        BarUtils.setStatusBarColor(window, Color.TRANSPARENT);
//        window.setDimAmount(0);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4C000000")));
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected RoomGuidePresenter bindPresenter() {
        return new RoomGuidePresenter(this, getActivity());
    }

    @Override
    public void initData() {

    }


    @OnClick(R2.id.iv_i_know)
    public void onViewClicked() {



    }

    public void show(FragmentManager manager) {
        show(manager, TAG);
    }

    @Override
    public void success() {
        dismiss();
    }


    @OnClick({R2.id.iv_i_know, R2.id.iv_i_know2, R2.id.iv_i_know3, R2.id.iv_i_know4, R2.id.iv_i_know5})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_i_know) {//显示第二步
            clGuide1.setVisibility(View.GONE);
            clGuide2.setVisibility(View.VISIBLE);
        } else if (id == R.id.iv_i_know2) {//显示第三步
            clGuide2.setVisibility(View.GONE);
            clGuide3.setVisibility(View.VISIBLE);
        } else if (id == R.id.iv_i_know3) {//显示第四步
            clGuide3.setVisibility(View.GONE);
            clGuide4.setVisibility(View.VISIBLE);
        } else if (id == R.id.iv_i_know4) {//显示第五步
            clGuide4.setVisibility(View.GONE);
            clGuide5.setVisibility(View.VISIBLE);
        } else if (id == R.id.iv_i_know5) {//引导结束
            MvpPre.completeGuide(roomId);
        }
    }


}
