package com.spadea.xqipao.ui.room.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.spadea.yuyin.R;
import com.spadea.xqipao.data.GameInfo;
import com.spadea.xqipao.ui.room.presenter.GamePresenter;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.room.contacts.GameContactrs;

import butterknife.BindView;
import butterknife.OnClick;

public class GameDialogFragment extends BaseDialogFragment<GamePresenter> implements GameContactrs.View {


    @BindView(R.id.iv_globe1)
    ImageView ivGlobel1;
    @BindView(R.id.iv_globe2)
    ImageView ivGlobel2;
    @BindView(R.id.iv_globe3)
    ImageView ivGlobel3;
    @BindView(R.id.iv_start)
    ImageView ivStart;
    @BindView(R.id.iv_over)
    ImageView ivOver;
    @BindView(R.id.iv_stop)
    ImageView ivStop;
    @BindView(R.id.rl_box1)
    RelativeLayout rlBox1;
    @BindView(R.id.rl_box2)
    RelativeLayout rlBox2;
    @BindView(R.id.rl_box3)
    RelativeLayout rlBox3;

    private GameInfo gameInfo1 = new GameInfo();
    private GameInfo gameInfo2 = new GameInfo();
    private GameInfo gameInfo3 = new GameInfo();
    private RoomFragmentListener mRoomFragmentListener;


    public static GameDialogFragment newInstance() {
        GameDialogFragment gameDialogFragment = new GameDialogFragment();
        return gameDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RoomFragmentListener) {
            mRoomFragmentListener = (RoomFragmentListener) context;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView) {
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
        return R.layout.fragment_dialog_game;
    }

    @Override
    protected GamePresenter bindPresenter() {
        return new GamePresenter(this, mContext);
    }


    @OnClick({R.id.iv_start, R.id.iv_over, R.id.iv_stop, R.id.iv_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                this.getDialog().dismiss();
                break;
            case R.id.iv_start:
                MvpPre.start();
                ivStart.setEnabled(false);
                ivOver.setEnabled(true);
                ivStop.setEnabled(true);
                mRoomFragmentListener.sendStartGame("开球");
                break;
            case R.id.iv_over:
                ivGlobel1.setImageResource(R.mipmap.abc);
                ivGlobel2.setImageResource(R.mipmap.abc);
                ivGlobel3.setImageResource(R.mipmap.abc);
                rlBox1.setBackgroundResource(R.mipmap.img_gift_red);
                rlBox2.setBackgroundResource(R.mipmap.img_gift_red);
                rlBox3.setBackgroundResource(R.mipmap.img_gift_red);
                ivStart.setEnabled(true);
                ivOver.setEnabled(false);
                ivStop.setEnabled(false);
                mRoomFragmentListener.sendOverGame("丢球");
                break;
            case R.id.iv_stop:
                ivGlobel1.setImageResource(R.mipmap.abc);
                ivGlobel2.setImageResource(R.mipmap.abc);
                ivGlobel3.setImageResource(R.mipmap.abc);
                rlBox1.setBackgroundResource(R.mipmap.img_gift_red);
                rlBox2.setBackgroundResource(R.mipmap.img_gift_red);
                rlBox3.setBackgroundResource(R.mipmap.img_gift_red);
                ivStart.setEnabled(true);
                ivOver.setEnabled(false);
                ivStop.setEnabled(false);
                mRoomFragmentListener.sendGameData("亮球", gameInfo1.toString(), gameInfo2.toString(), gameInfo3.toString());
                this.getDialog().dismiss();
                break;
        }
    }


    @Override
    public void setData(GameInfo gameInfo1, GameInfo gameInfo2, GameInfo gameInfo3) {
        this.gameInfo1 = gameInfo1;
        this.gameInfo2 = gameInfo2;
        this.gameInfo3 = gameInfo3;
        rlBox1.setBackgroundResource(R.mipmap.img_gift_blue);
        rlBox2.setBackgroundResource(R.mipmap.img_gift_blue);
        rlBox3.setBackgroundResource(R.mipmap.img_gift_blue);
        ivGlobel1.setImageResource(getResources().getIdentifier(gameInfo1.getColor() + gameInfo1.getNum(), "mipmap", mContext.getPackageName()));
        ivGlobel2.setImageResource(getResources().getIdentifier(gameInfo2.getColor() + gameInfo2.getNum(), "mipmap", mContext.getPackageName()));
        ivGlobel3.setImageResource(getResources().getIdentifier(gameInfo3.getColor() + gameInfo3.getNum(), "mipmap", mContext.getPackageName()));
    }
}
