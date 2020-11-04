package com.qpyy.room.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.BallResp;
import com.qpyy.room.contacts.GameContactrs;
import com.qpyy.room.presenter.GamePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class GameDialogFragment extends BaseMvpDialogFragment<GamePresenter> implements GameContactrs.View {


    @BindView(R2.id.iv_globe1)
    ImageView ivGlobel1;
    @BindView(R2.id.iv_globe2)
    ImageView ivGlobel2;
    @BindView(R2.id.iv_globe3)
    ImageView ivGlobel3;
    @BindView(R2.id.iv_start)
    ImageView ivStart;
    @BindView(R2.id.iv_over)
    ImageView ivOver;
    @BindView(R2.id.iv_stop)
    ImageView ivStop;
    @BindView(R2.id.rl_box1)
    RelativeLayout rlBox1;
    @BindView(R2.id.rl_box2)
    RelativeLayout rlBox2;
    @BindView(R2.id.rl_box3)
    RelativeLayout rlBox3;


    private String roomId;
    private String pitNumber;

    private BallResp ballResp;


    public static GameDialogFragment newInstance() {

        Bundle args = new Bundle();

        GameDialogFragment fragment = new GameDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setPitNumber(String pitNumber) {
        this.pitNumber = pitNumber;
    }

    @Override
    protected void initData() {
        if (ballResp != null) {
            ballStartSuccess(ballResp);
        }
    }

    @Override
    protected void initView() {
        ivStart.setEnabled(true);
        ivOver.setEnabled(false);
        ivStop.setEnabled(false);
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setCancelable(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragment_dialog_game;
    }

    @Override
    protected GamePresenter bindPresenter() {
        return new GamePresenter(this, getActivity());
    }

    @OnClick({R2.id.iv_start, R2.id.iv_over, R2.id.iv_stop, R2.id.iv_close})
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.iv_start == id) {
            MvpPre.ballStart(roomId, pitNumber);
        } else if (R.id.iv_over == id) {
            MvpPre.ballThrow(roomId, pitNumber);
        } else if (R.id.iv_stop == id) {
            MvpPre.ballShow(roomId, pitNumber);
        } else if (R.id.iv_close == id) {
            dismiss();
        }
    }

    @Override
    public void ballStartSuccess(BallResp ballResp) {
        this.ballResp = ballResp;
        ivStart.setEnabled(false);
        ivOver.setEnabled(true);
        ivStop.setEnabled(true);
        rlBox1.setBackgroundResource(R.mipmap.img_gift_blue);
        rlBox2.setBackgroundResource(R.mipmap.img_gift_blue);
        rlBox3.setBackgroundResource(R.mipmap.img_gift_blue);
        ivGlobel1.setImageResource(getResources().getIdentifier(ballResp.getFirst(), "mipmap", getContext().getPackageName()));
        ivGlobel2.setImageResource(getResources().getIdentifier(ballResp.getSecond(), "mipmap", getContext().getPackageName()));
        ivGlobel3.setImageResource(getResources().getIdentifier(ballResp.getThird(), "mipmap", getContext().getPackageName()));

    }

    @Override
    public void ballThrowSuccess() {
        ivGlobel1.setImageResource(R.mipmap.abc);
        ivGlobel2.setImageResource(R.mipmap.abc);
        ivGlobel3.setImageResource(R.mipmap.abc);
        rlBox1.setBackgroundResource(R.mipmap.img_gift_red);
        rlBox2.setBackgroundResource(R.mipmap.img_gift_red);
        rlBox3.setBackgroundResource(R.mipmap.img_gift_red);
        ivStart.setEnabled(true);
        ivOver.setEnabled(false);
        ivStop.setEnabled(false);
        ballResp = null;

    }

    @Override
    public void ballShowSuccess(BallResp ballResp) {
        ivGlobel1.setImageResource(R.mipmap.abc);
        ivGlobel2.setImageResource(R.mipmap.abc);
        ivGlobel3.setImageResource(R.mipmap.abc);
        rlBox1.setBackgroundResource(R.mipmap.img_gift_red);
        rlBox2.setBackgroundResource(R.mipmap.img_gift_red);
        rlBox3.setBackgroundResource(R.mipmap.img_gift_red);
        ivStart.setEnabled(true);
        ivOver.setEnabled(false);
        ivStop.setEnabled(false);
        this.ballResp = null;
        dismiss();
    }

    /**
     * 初始化开球数据
     *
     * @param ball
     */
    public void setBall(String ball) {
        if (!TextUtils.isEmpty(ball)) {
            String[] split = ball.split(",");
            if (split.length == 3) {
                BallResp ballResp = new BallResp();
                ballResp.setFirst(split[0]);
                ballResp.setSecond(split[1]);
                ballResp.setThird(split[2]);
                this.ballResp = ballResp;
            }
        }
    }
}
