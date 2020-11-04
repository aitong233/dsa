package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.utils.dialog.adapter.ExpressionAdapter;
import com.spadea.xqipao.data.EmojiModel;
import com.spadea.xqipao.utils.dialog.BaseBottomSheetDialog;
import com.spadea.yuyin.R;

import java.util.List;

import butterknife.BindView;

public class ExpressionDialog extends BaseBottomSheetDialog {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ExpressionAdapter expressionAdapter;
    private ExpressionListener expressionListener;

    public ExpressionDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_express;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setAdapter(expressionAdapter = new ExpressionAdapter());
    }

    @Override
    public void initData() {
        expressionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EmojiModel item = expressionAdapter.getItem(position);
                if (expressionListener != null) {
                    expressionListener.onSelectItem(item);
                    dismiss();
                }
            }
        });
    }


    public void setData(List<EmojiModel> emojiModels) {
        if (expressionAdapter != null) {
            EmojiModel emojiModel = new EmojiModel();
            emojiModel.setId("0");
            emojiModel.setName("抽签");
            emojiModels.add(0, emojiModel);
            expressionAdapter.setNewData(emojiModels);
        }
    }


    public void setExpressionListener(ExpressionListener expressionListener) {
        this.expressionListener = expressionListener;
    }

    public interface ExpressionListener {
        void onSelectItem(EmojiModel emojiModel);
    }
}
