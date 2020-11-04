package com.qpyy.module.me.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.contacts.PersonalSignatureContacts;
import com.qpyy.module.me.event.UpdateInfoEvent;
import com.qpyy.module.me.presenter.PersonalSignaturePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.ME_PERSONAL_SIGNATURE, name = "个性签名")
public class PersonalSignatureActivity extends BaseMvpActivity<PersonalSignaturePresenter> implements PersonalSignatureContacts.View {

    @BindView(R2.id.ed_text)
    EditText editTextText;
    @BindView(R2.id.tv_title)
    TextView textViewTitle;
    @BindView(R2.id.tv_number)
    TextView textViewNumber;

    @Autowired
    public String text;

    @Override
    protected PersonalSignaturePresenter bindPresenter() {
        return new PersonalSignaturePresenter(this, this);
    }

    @Override
    protected void initData() {
        editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editTextText.getText().toString().trim();
                textViewNumber.setText(text.length() + "/20");
            }
        });
        if (!TextUtils.isEmpty(text)) {
            editTextText.setText(text);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        textViewTitle.setText("个人签名");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_personal_signature;
    }

    @OnClick({R2.id.iv_back, R2.id.tv_save})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.tv_save) {
            MvpPre.updataPersonalSignature(editTextText.getText().toString().trim());
        }
    }

    @Override
    public void updataSuccess() {
        ToastUtils.show("修改成功");
        EventBus.getDefault().post(new UpdateInfoEvent());
        this.finish();
    }
}
