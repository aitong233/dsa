package com.qpyy.module.me.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.widget.dialog.BaseDialog;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class EditNickNameDialog extends BaseDialog {

    @BindView(R2.id.ed_content)
    EditText edContent;
    @BindView(R2.id.tv_left)
    TextView tvLeft;
    @BindView(R2.id.tv_right)
    TextView tvRight;
    @BindView(R2.id.tv_title)
    TextView tvTitle;

    private OnEditNickName mOnEditNickName;

    public EditNickNameDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.me_dialog_edit_nick_name;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    public void setName(String name){
        edContent.setText(name);
    }

    @OnClick({R2.id.tv_right, R2.id.tv_left})
    public void onClick(View view) {
        this.dismiss();
        int id = view.getId();
        if (id == R.id.tv_right) {
            String name = edContent.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                ToastUtils.show("请输入昵称");
            }
            if (mOnEditNickName != null) {
                mOnEditNickName.updateName(name);
            }
        }
    }


    public void setHintText(String text) {
        edContent.setHint(text);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setmOnEditNickName(OnEditNickName mOnEditNickName) {
        this.mOnEditNickName = mOnEditNickName;
    }

    public interface OnEditNickName {
        void updateName(String nickName);
    }


}
