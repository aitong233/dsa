package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.spadea.yuyin.R;

import butterknife.BindView;

public class PropDialog extends BaseBottomSheetDialog {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public PropDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_prop;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void initData() {

    }
}
