package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.spadea.xqipao.utils.dialog.adapter.CatFishingJakpotAdapter;
import com.spadea.xqipao.data.WinJackpotModel;
import com.spadea.yuyin.R;

import java.util.List;

import butterknife.BindView;

public class CatFishingJackpotDialog extends BaseBottomSheetDialog {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private CatFishingJakpotAdapter catFishingJakpotAdapterc;

    public CatFishingJackpotDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * icon_cat_fishing_jackpot_dialog
     *
     * @return
     */

    @Override
    public int getLayout() {
        return R.layout.dialog_cat_fishing_jackpot;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(catFishingJakpotAdapterc = new CatFishingJakpotAdapter());
    }

    public void setData(List<WinJackpotModel> list) {
        if (catFishingJakpotAdapterc != null) {
            catFishingJakpotAdapterc.setNewData(list);
        }
    }

    @Override
    public   void initData() {

    }
}
