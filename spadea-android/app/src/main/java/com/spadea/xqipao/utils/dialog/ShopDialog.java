package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.ScreenUtils;
import com.spadea.xqipao.utils.dialog.adapter.ShopDayAdapter;
import com.spadea.xqipao.data.ProductsModel;
import com.qpyy.libcommon.bean.UserBean;
import com.spadea.xqipao.utils.view.DecorationHeadView;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopDialog extends BaseDialog {


    @BindView(R.id.dhv)
    DecorationHeadView dhv;
    @BindView(R.id.cl_head)
    ConstraintLayout rlPendant;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rl_give)
    RelativeLayout rlGive;
    @BindView(R.id.rl_payment)
    RelativeLayout rlPayment;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    private ShopDayAdapter shopDayAdapter;
    private ProductsModel productsModel;
    private String id;
    private ShopOnClickListener mShopOnClickListener;

    public ShopDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_shop;
    }

    @Override
    public void initView() {
        getWindow().setBackgroundDrawableResource(R.drawable.bg_r5_white);
        getWindow().setLayout((int) (ScreenUtils.getScreenWidth() / 375.0 * 300), WindowManager.LayoutParams.WRAP_CONTENT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shopDayAdapter = new ShopDayAdapter());
        shopDayAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductsModel.PricesBean item = shopDayAdapter.getItem(position);
                shopDayAdapter.setIndex(position);
                tvPrice.setText("支付" + item.getPrice() + "金币");
            }
        });
    }

    @Override
    public void initData() {

    }


    public void setData(ProductsModel item, String id) {
        this.productsModel = item;
        this.id = id;
        if (id.equals("1")) {
            rlPendant.setVisibility(View.VISIBLE);
            ivImg.setVisibility(View.GONE);
            UserBean user = MyApplication.getInstance().getUser();
            dhv.setData(user.getHead_picture(), item.getPicture());
        } else {
            rlPendant.setVisibility(View.GONE);
            ivImg.setVisibility(View.VISIBLE);
            ImageLoader.loadImage(mContext, ivImg, item.getPicture());
        }
        tvTitle.setText(item.getTitle());
        if (item.getPrices() == null || item.getPrices().size() == 0) {
            rlGive.setVisibility(View.GONE);
            rlPayment.setVisibility(View.GONE);
        } else {
            rlGive.setVisibility(View.VISIBLE);
            rlPayment.setVisibility(View.VISIBLE);
            shopDayAdapter.setNewData(item.getPrices());
            if (item.getPrices() != null && item.getPrices().size() != 0) {
                tvPrice.setText("支付" + item.getPrices().get(0).getPrice() + "金币");
            }
            shopDayAdapter.setIndex(0);
        }
    }

    @OnClick({R.id.rl_give, R.id.rl_payment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_give:
                if (mShopOnClickListener != null && productsModel != null) {
                    mShopOnClickListener.give(productsModel, shopDayAdapter.getPriceId(), shopDayAdapter.getPrice().getPrice(), shopDayAdapter.getPrice().getDay());
                }
                break;
            case R.id.rl_payment:
                if (mShopOnClickListener != null && productsModel != null) {
                    mShopOnClickListener.payment(productsModel, shopDayAdapter.getPriceId(), shopDayAdapter.getPrice().getPrice());
                }
                break;
        }
        this.dismiss();
    }

    public void setShopOnClickListener(ShopOnClickListener shopOnClickListener) {
        this.mShopOnClickListener = shopOnClickListener;
    }

    public interface ShopOnClickListener {
        void give(ProductsModel productsModel, String priceId, String price, String day);

        void payment(ProductsModel productsModel, String priceId, String price);
    }


}
