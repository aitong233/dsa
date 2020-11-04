package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.spadea.xqipao.utils.dialog.adapter.CatFishingInJakpotAdapter;
import com.spadea.xqipao.data.EggGiftModel;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.yuyin.R;

import java.util.List;

import butterknife.BindView;

public class CatFishingInJackpotDialog extends BaseBottomSheetDialog {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CatFishingInJakpotAdapter catFishingInJakpotAdapter;

    public CatFishingInJackpotDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * icon_cat_fishing_jackpot_dialog
     *
     * @return
     */

    @Override
    public int getLayout() {
        return R.layout.dialog_cat_fishing_in_jackpot;
    }

    @Override
    public void initView() {
        CommonEmptyView commonEmptyView = new CommonEmptyView(mContext);
        commonEmptyView.setEmptyText("很遗憾没能中奖");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(catFishingInJakpotAdapter = new CatFishingInJakpotAdapter());
        catFishingInJakpotAdapter.setEmptyView(commonEmptyView);
    }

    public void setData(List<EggGiftModel> list) {
        if (catFishingInJakpotAdapter != null) {
            catFishingInJakpotAdapter.setNewData(list);
        }
    }

    @Override
    public void initData() {

    }
}
