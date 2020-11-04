package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.spadea.xqipao.utils.dialog.adapter.CatFishingRankingAdapter;
import com.spadea.xqipao.data.CatFishingModel;
import com.spadea.yuyin.R;

import java.util.List;

import butterknife.BindView;

public class CatFishingRankingDialog extends BaseBottomSheetDialog {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Context mContext;
    private CatFishingRankingAdapter catFishingRankingAdapter;

    public CatFishingRankingDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_cat_fishing_ranking;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(catFishingRankingAdapter = new CatFishingRankingAdapter());
    }

    public  void setData(List<CatFishingModel> catFishingModels){
        if (catFishingRankingAdapter!=null){
            catFishingRankingAdapter.setNewData(catFishingModels);
        }
    }
    @Override
    public void initData() {

    }
}
