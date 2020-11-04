package com.spadea.xqipao.ui.me.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.bean.TransferUserModel;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.Price;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.TransferContacts;
import com.spadea.xqipao.ui.me.presenter.TransferPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.ME_TRANSFER, name = "转账-用户")
public class TransferActivity extends BaseActivity<TransferPresenter> implements TransferContacts.View {


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
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.ed_num)
    EditText edNum;
    @BindView(R.id.iv_next)
    TextView ivNext;

    @Autowired
    public int fid = -1;

    @Autowired
    public TransferUserModel transferUser;

    public TransferActivity() {
        super(R.layout.activity_transfer);
    }


    @Override
    protected void initData() {
        ImageLoader.loadHead(this, ivImg, transferUser.getHead_picture());
        tvUserName.setText(transferUser.getNickname());
        tvUserId.setText("ID: " + transferUser.getUser_code());
    }

    @Override
    protected void initView() {
        tvTitle.setText("发红包");
    }

    @Override
    protected void setListener() {
        edNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String data = edNum.getText().toString();
                if (TextUtils.isEmpty(data)) {
                    ivNext.setEnabled(false);
                } else {
                    ivNext.setEnabled(true);
                }
            }
        });
    }

    @Override
    protected TransferPresenter bindPresenter() {
        return new TransferPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    String price = "0";

    @OnClick({R.id.iv_next, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_next:
                String s = edNum.getText().toString();
                price = edNum.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtils.showShort("请输入正确的转账金额");
                    return;
                }
                Double num = Double.valueOf(s);
                if (num <= 0) {
                    ToastUtils.showShort("请输入正确的转账金额");
                    return;
                }
                new AlertDialog.Builder(this).setTitle("请确认信息").setMessage(String.format("是否向用户 '%s' 发送 %s 红包", transferUser.getNickname(), s)).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (transferUser.isIM()) {
                            Log.i("ds", "onClick: IM");
                            MvpPre.imTransfer(transferUser.getIm_id(), s);
                        } else {
                            MvpPre.userTransfer(transferUser.getUser_id(), s);
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }


    @Override
    public void userTransferSuccess() {
        edNum.setText("");
        ToastUtils.showShort("红包发送成功");
        EventBus.getDefault().postSticky(new Price(price, fid));
        finish();
    }
}
