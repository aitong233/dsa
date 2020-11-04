package com.spadea.xqipao.ui.room.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.ui.room.presenter.HoldingWheatPresenter;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.room.contacts.HoldingWheatContacts;
import com.spadea.yuyin.R;

import butterknife.OnClick;

public class HoldingWheatFragment extends BaseDialogFragment<HoldingWheatPresenter> implements HoldingWheatContacts.View {

    private RoomFragmentListener mRoomFragmentListener;
    private String roomId;

    public static HoldingWheatFragment newInstance(String roomId) {
        HoldingWheatFragment holdingWheatFragment = new HoldingWheatFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        holdingWheatFragment.setArguments(bundle);
        return holdingWheatFragment;
    }

    @Override
    protected void initData() {
        roomId = getArguments().getString("roomId");
    }

    @Override
    protected void initView(View rootView) {
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_holdingwheat;
    }

    @Override
    protected HoldingWheatPresenter bindPresenter() {
        return new HoldingWheatPresenter(this, mContext);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RoomFragmentListener) {
            mRoomFragmentListener = (RoomFragmentListener) context;
        }
    }


    @OnClick({R.id.tv_remove, R.id.tv_up_wheat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_up_wheat:
                MvpPre.putOnWheat(roomId, MyApplication.getInstance().getUser().getUser_id());
                break;
        }
        this.dismiss();
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setCancelable(true);
    }


}
