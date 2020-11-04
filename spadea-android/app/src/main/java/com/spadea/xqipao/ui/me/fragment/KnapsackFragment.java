package com.spadea.xqipao.ui.me.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.data.MyProductsModel;
import com.spadea.xqipao.data.UsingProductsModel;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.me.activity.KnapsackActivity;
import com.spadea.xqipao.ui.me.contacter.KnapsackItemContacter;
import com.spadea.xqipao.ui.me.presenter.KnapsackItemPresenter;
import com.spadea.xqipao.utils.view.DecorationHeadView;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.qpyy.libcommon.bean.UserBean;
import com.spadea.xqipao.ui.me.adapter.KnapsackAdapter;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

public class KnapsackFragment extends BaseFragment<KnapsackItemPresenter> implements KnapsackItemContacter.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.dhv)
    DecorationHeadView dhv;
    @BindView(R.id.tv_titles)
    TextView tvTitles;


    private KnapsackAdapter knapsackAdapter;
    private String type;
    private View view;

    public static Fragment newInstance(String title, String type) {
        KnapsackFragment knapsackFragment = new KnapsackFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("title", title);
        knapsackFragment.setArguments(bundle);
        return knapsackFragment;
    }


    @Override
    protected KnapsackItemPresenter bindPresenter() {
        return new KnapsackItemPresenter(this, mContext);
    }

    @Override
    protected void initData() {
        type = getArguments().getString("type");
        MvpPre.myProducts(type);
        MvpPre.myUsingProducts(type);
    }


    @Override
    protected void initView(View rootView) {
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_shop, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(knapsackAdapter = new KnapsackAdapter());
        knapsackAdapter.setEmptyView(view);
    }

    @Override
    protected void initListener() {
        super.initListener();
        view.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("dressPosition", type);
            Objects.requireNonNull(getActivity()).setResult(RESULT_OK, intent);
            getActivity().finish();
        });
        knapsackAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyProductsModel item = knapsackAdapter.getItem(position);
                switch (view.getId()) {
                    case R.id.iv_renew:
//                        ARouter.getInstance().build(ARouters.ME_JUERENEW).navigation();
                        getActivity().finish();
                        break;
                    case R.id.iv_un_use:
                        MvpPre.useProduct(item.getId());
                        break;
                    case R.id.iv_use:
                        MvpPre.downProduct(item.getId());
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knapsack;
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @Override
    public void myProductsSuccess(List<MyProductsModel> data) {
        knapsackAdapter.setNewData(data);
    }

    @Override
    public void myUsingProductsSuccess(UsingProductsModel data) {
        UserBean user = MyApplication.getInstance().getUser();
        if (TextUtils.isEmpty(data.getId())) {
            tvTitles.setText("还未设置~");
            dhv.setData(user.getHead_picture(), "");
        } else {
            tvTitles.setText(data.getTitle());
        }
        if ("1".equals(type)) {
            dhv.setData(user.getHead_picture(), data.getPicture());
        } else {
            dhv.setData(user.getHead_picture(), "");
        }
    }

    @Override
    public void useProductSuccess() {
        ToastUtils.showShort("使用成功");
        MvpPre.myProducts(type);
        MvpPre.myUsingProducts(type);
    }

    @Override
    public void downProductSuccess() {
        ToastUtils.showShort("成功摘下挂件");
        MvpPre.myProducts(type);
        MvpPre.myUsingProducts(type);
    }

}
