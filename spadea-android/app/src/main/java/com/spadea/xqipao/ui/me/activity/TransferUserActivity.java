package com.spadea.xqipao.ui.me.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qpyy.libcommon.bean.TransferUserModel;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.Price;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.TransferUserContacter;
import com.spadea.xqipao.ui.me.presenter.TransferUserPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.ME_TRANSFERUSER, name = "转账-用户")
public class TransferUserActivity extends BaseActivity<TransferUserPresenter> implements TransferUserContacter.View {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.ed_user_id)
    EditText edUserId;
    @BindView(R.id.iv_next)
    TextView ivNext;

    public TransferUserActivity() {
        super(R.layout.activity_transfer_user);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTitle.setText("发红包");
        viewLine.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {
        edUserId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String userId = edUserId.getText().toString();
                if (TextUtils.isEmpty(userId)) {

                    ivNext.setEnabled(false);
                } else {

                    ivNext.setEnabled(true);
                }
            }
        });
    }

    @Override
    protected TransferUserPresenter bindPresenter() {
        return new TransferUserPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @OnClick({R.id.iv_next, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_next:
                String userId = edUserId.getText().toString();
                if (TextUtils.isEmpty(userId)) {
                    ToastUtils.showShort("请输入用户账号");
                    return;
                }
                MvpPre.getTransferUser(userId);
                break;
        }

    }

    @Override
    public void setTransferUser(TransferUserModel transferUser) {
        if (transferUser != null) {
            ARouter.getInstance().build(ARouteConstants.ME_TRANSFER).withSerializable("transferUser", transferUser).navigation();
        } else {
            ToastUtils.showShort("用户查找失败");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSend(Price p) {
       finish();
    }
}
