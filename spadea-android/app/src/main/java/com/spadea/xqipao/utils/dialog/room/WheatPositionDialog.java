package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.data.RowWheatModel;
import com.spadea.xqipao.utils.dialog.BaseBottomSheetDialog;
import com.spadea.xqipao.ui.room.adapter.UpWheatAdapter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WheatPositionDialog extends BaseBottomSheetDialog {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_remove)
    TextView tvRemove;
    @BindView(R.id.tv_up_wheat)
    TextView tvUpWheat;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_count)
    TextView tvCount;


    private UpWheatAdapter upWheatAdapter;
    private WheatPositionListene mWheatPositionListene;
    private List<RowWheatModel> list = new ArrayList<>();

    public WheatPositionDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_wheat_postition;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(upWheatAdapter = new UpWheatAdapter());
        upWheatAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RowWheatModel item = upWheatAdapter.getItem(position);
                if (mWheatPositionListene != null) {
                    switch (view.getId()) {
                        case R.id.tv_up_wheat:
                            mWheatPositionListene.upWheat(item, position);
                            break;
                        case R.id.tv_remove:
                            mWheatPositionListene.removeWheat(item.getId(), position);
                            break;
                    }
                }
            }
        });
    }


    @Override
    public void initData() {

    }

    public void setmWheatPositionListene(WheatPositionListene mWheatPositionListene) {
        this.mWheatPositionListene = mWheatPositionListene;
    }

    public void setData(List<RowWheatModel> rowWheatModels) {
        this.list = rowWheatModels;
        upWheatAdapter.setNewData(rowWheatModels);
        tvCount.setText("等待连麦人数（" + upWheatAdapter.getData().size() + "）");
    }

    public void removePostion(int postion) {
        if (upWheatAdapter != null) {
            if (postion == -1) {
                upWheatAdapter.setNewData(new ArrayList<>());
            } else {
                upWheatAdapter.remove(postion);
            }
            tvCount.setText("等待连麦人数（" + upWheatAdapter.getData().size() + "）");
        }
    }


    @OnClick({R.id.tv_remove, R.id.tv_up_wheat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_remove:
                if (list.size() == 0) {
                    ToastUtils.showShort("暂无可操作人员");
                    return;
                }
                String ids = dataToString(list);
                if (mWheatPositionListene != null) {
                    mWheatPositionListene.removeWheat(ids, -1);
                }
                break;
            case R.id.tv_up_wheat:
                if (list.size() == 0) {
                    ToastUtils.showShort("暂无可操作人员");
                    return;
                }
                if (mWheatPositionListene != null) {
                    mWheatPositionListene.oneWheat(-1);
                }
                break;
        }
    }

    public String getRowWheatCount() {
        if (upWheatAdapter != null) {
            return String.valueOf(upWheatAdapter.getData().size());
        } else {
            return "0";
        }
    }


    public interface WheatPositionListene {
        void upWheat(RowWheatModel rowWheatModel, int postion);

        void removeWheat(String ids, int postion);

        void oneWheat(int postion);
    }


    private static String dataToString(List<RowWheatModel> dataList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataList.size(); i++) {
            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                sb.append(",");
            }
            sb.append(dataList.get(i).getId());
        }
        return sb.toString();
    }


}
