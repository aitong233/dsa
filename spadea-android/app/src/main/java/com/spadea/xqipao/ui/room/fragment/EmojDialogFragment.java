package com.spadea.xqipao.ui.room.fragment;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.yuyin.R;
import com.spadea.xqipao.data.EmojiModel;
import com.spadea.xqipao.ui.room.presenter.EmojPresenter;
import com.spadea.xqipao.utils.dialog.adapter.ExpressionAdapter;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.room.contacts.EmojContacts;

import java.util.List;

import butterknife.BindView;

public class EmojDialogFragment extends BaseDialogFragment<EmojPresenter> implements EmojContacts.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ExpressionAdapter expressionAdapter;
    private EmojSelectListener mEmojSelectListener;


    public static EmojDialogFragment newInstance() {
        return new EmojDialogFragment();
    }


    @Override
    protected void initData() {
        MvpPre.getFaceList();
    }

    @Override
    protected void initView(View rootView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setAdapter(expressionAdapter = new ExpressionAdapter());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragmenr_emoj;
    }

    @Override
    protected void initListener() {
        expressionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EmojiModel item = expressionAdapter.getItem(position);
                if (mEmojSelectListener != null) {
                    mEmojSelectListener.onSelectEmoj(item);
                }
                EmojDialogFragment.this.dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setWindowAnimations(R.style.ShowDialogBottom);
        setCancelable(true);
    }

    public void setEmojSelectListener(EmojSelectListener mEmojSelectListener) {
        this.mEmojSelectListener = mEmojSelectListener;
    }

    @Override
    protected EmojPresenter bindPresenter() {
        return new EmojPresenter(this, mContext);
    }



    @Override
    public void setFraceListData(List<EmojiModel> data) {
        EmojiModel emojiModel = new EmojiModel();
        emojiModel.setName("抽签");
        data.add(0, emojiModel);
        expressionAdapter.setNewData(data);
    }


    public interface EmojSelectListener {
        void onSelectEmoj(EmojiModel emojiModel);
    }
}
