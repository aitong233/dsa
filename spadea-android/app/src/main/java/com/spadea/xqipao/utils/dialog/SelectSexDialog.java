package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectSexDialog extends BaseDialog {


    @BindView(R.id.iv_m)
    ImageView ivM;
    @BindView(R.id.iv_f)
    ImageView ivF;
    @BindView(R.id.rb0)
    CheckBox rb0;
    @BindView(R.id.rb1)
    CheckBox rb1;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private SlectSexListener slectSexListener;

    public SelectSexDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_sex_choose;
    }

    @Override
    public void initView() {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        rb0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rb1.setChecked(false);
                }
            }
        });

        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rb0.setChecked(false);
            }
        });


    }

    @OnClick({R.id.iv_m, R.id.iv_f, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_m:
                rb0.setChecked(true);
                rb1.setChecked(false);
                break;
            case R.id.iv_f:
                rb0.setChecked(false);
                rb1.setChecked(true);
                break;
            case R.id.btn_submit:
                if (rb0.isChecked()) {
                    slectSexListener.onSelectSex(1);
                    dismiss();
                } else if (rb1.isChecked()) {
                    slectSexListener.onSelectSex(2);
                    dismiss();
                } else {
                    ToastUtils.showShort("请选择性别");
                }
                break;
        }
    }

    @Override
    public void initData() {

    }

    public void addSlectSexListener(SlectSexListener slectSexListener) {
        this.slectSexListener = slectSexListener;
    }

    public interface SlectSexListener {
        void onSelectSex(int type);
    }
}
