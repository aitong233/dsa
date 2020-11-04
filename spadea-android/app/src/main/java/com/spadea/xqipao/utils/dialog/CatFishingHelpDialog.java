package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spadea.yuyin.R;

import butterknife.BindView;

public class CatFishingHelpDialog extends BaseBottomSheetDialog {

    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;

    public CatFishingHelpDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_cat_fishing_help;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    public void setData(String text) {
        tvContent.setText(Html.fromHtml(text));
    }


}
