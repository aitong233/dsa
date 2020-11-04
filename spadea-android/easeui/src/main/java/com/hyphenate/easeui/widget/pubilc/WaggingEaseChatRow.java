package com.hyphenate.easeui.widget.pubilc;

import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.widget.GradeView;
import com.qpyy.libcommon.widget.NewView;
import com.qpyy.libcommon.widget.NobilityView;
import com.qpyy.libcommon.widget.RoleView;

/**
 * 摇签
 */
public class WaggingEaseChatRow extends EaseChatRow {


    private NewView ivNew;
    private RoleView ivRole;
    private GradeView ivGrade;
    private NobilityView ivNHobility;
    private TextView tvName;
    private TextView tvContent;
    private ImageView ivWagging;

    private int[] a = {R.drawable.random0s, R.drawable.random1s, R.drawable.random2s, R.drawable.random3s, R.drawable.random4s, R.drawable.random5s, R.drawable.random6s, R.drawable.random7s, R.drawable.random8s, R.drawable.random9s};


    public WaggingEaseChatRow(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(R.layout.ease_row_received_message_wagging, this);
    }

    @Override
    protected void onFindViewById() {
        ivNew = findViewById(R.id.iv_new);
        ivRole = findViewById(R.id.iv_role);
        ivGrade = findViewById(R.id.iv_grade);
        ivNHobility = findViewById(R.id.iv_nobility);
        tvName = findViewById(R.id.tv_name);
        tvContent = findViewById(R.id.tv_content);
        ivWagging = findViewById(R.id.iv_wagginh);
    }

    @Override
    protected void onViewUpdate(EMMessage msg) {

    }

    @Override
    protected void onSetUpView() {
        EMTextMessageBody textMessageBody = (EMTextMessageBody) message.getBody();
        Spannable span = EaseSmileUtils.getSmiledText(context, Html.fromHtml(textMessageBody.getMessage()));
        String role = this.message.getStringAttribute("role", "0");
        String userIsNew = this.message.getStringAttribute("user_is_new", "0");
        String rankIcon = this.message.getStringAttribute("rank_icon", "");
        String nobilityIcon = this.message.getStringAttribute("nobility_icon", "");
        String userId = this.message.getStringAttribute("user_id", "");
        String nickname = this.message.getStringAttribute("nickname", "");
        String number = this.message.getStringAttribute("number", "");
        ivRole.setRole(Integer.parseInt(role));
        ivNew.setNew(Integer.parseInt(userIsNew));
        ivGrade.setGrade(rankIcon);
        ivNHobility.setNobility(nobilityIcon);
        tvName.setText(Html.fromHtml(nickname));
        SpanUtils spanUtils = new SpanUtils();
        spanUtils.append(span);
        tvContent.setText(spanUtils.create());
        ivWagging.setImageResource(a[Integer.parseInt(number)]);
    }
}