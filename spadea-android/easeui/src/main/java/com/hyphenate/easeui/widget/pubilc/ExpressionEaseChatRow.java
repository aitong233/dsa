package com.hyphenate.easeui.widget.pubilc;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.widget.GradeView;
import com.qpyy.libcommon.widget.NewView;
import com.qpyy.libcommon.widget.NobilityView;
import com.qpyy.libcommon.widget.RoleView;

/**
 * 发送表情
 */
public class ExpressionEaseChatRow extends EaseChatRow {


    private NewView ivNew;
    private RoleView ivRole;
    private GradeView ivGrade;
    private NobilityView ivNHobility;
    private TextView tvName;

    private ImageView ivExpression;


    public ExpressionEaseChatRow(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(R.layout.ease_row_received_message_expression, this);
    }

    @Override
    protected void onFindViewById() {
        ivNew = findViewById(R.id.iv_new);
        ivRole = findViewById(R.id.iv_role);
        ivGrade = findViewById(R.id.iv_grade);
        ivNHobility = findViewById(R.id.iv_nobility);
        tvName = findViewById(R.id.tv_name);
        ivExpression = findViewById(R.id.iv_expression);

    }

    @Override
    protected void onViewUpdate(EMMessage msg) {

    }

    @Override
    protected void onSetUpView() {
        EMTextMessageBody textMessageBody = (EMTextMessageBody) message.getBody();
        String message = textMessageBody.getMessage();
        String role = this.message.getStringAttribute("role", "0");
        String userIsNew = this.message.getStringAttribute("user_is_new", "0");
        String rankIcon = this.message.getStringAttribute("rank_icon", "");
        String nobilityIcon = this.message.getStringAttribute("nobility_icon", "");
        String userId = this.message.getStringAttribute("user_id", "");
        String nickname = this.message.getStringAttribute("nickname", "");
        ivRole.setRole(Integer.parseInt(role));
        ivNew.setNew(Integer.parseInt(userIsNew));
        ivGrade.setGrade(rankIcon);
        ivNHobility.setNobility(nobilityIcon);
        tvName.setText(Html.fromHtml(nickname));
        ImageLoader.loadIcon(context, ivExpression, message);

    }
}