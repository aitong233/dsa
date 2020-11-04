package com.hyphenate.easeui.widget.pubilc;

import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.libcommon.widget.GradeView;
import com.qpyy.libcommon.widget.NewView;
import com.qpyy.libcommon.widget.NobilityView;
import com.qpyy.libcommon.widget.RoleView;

public class GameEaseChatRow extends EaseChatRow {


    private NewView ivNew;
    private RoleView ivRole;
    private GradeView ivGrade;
    private NobilityView ivNHobility;
    private TextView tvName;
    private ImageView ivQiu1;
    private ImageView ivQiu2;
    private ImageView ivQiu3;
    private TextView tvContent;



    public GameEaseChatRow(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(R.layout.ease_row_received_message_game, this);
    }

    @Override
    protected void onFindViewById() {
        ivNew = findViewById(R.id.iv_new);
        ivRole = findViewById(R.id.iv_role);
        ivGrade = findViewById(R.id.iv_grade);
        ivNHobility = findViewById(R.id.iv_nobility);
        tvName = findViewById(R.id.tv_name);
        ivQiu1 = findViewById(R.id.iv_qiu1);
        ivQiu2 = findViewById(R.id.iv_qiu2);
        ivQiu3 = findViewById(R.id.iv_qiu3);
        tvContent = findViewById(R.id.tv_content);

    }

    @Override
    protected void onViewUpdate(EMMessage msg) {

    }

    @Override
    protected void onSetUpView() {
        EMTextMessageBody textMessageBody = (EMTextMessageBody) message.getBody();
        Spannable message = EaseSmileUtils.getSmiledText(context, Html.fromHtml(textMessageBody.getMessage()));
        String role = this.message.getStringAttribute("role", "0");
        String userIsNew = this.message.getStringAttribute("user_is_new", "0");
        String rankIcon = this.message.getStringAttribute("rank_icon", "");
        String nobilityIcon = this.message.getStringAttribute("nobility_icon", "");
        String userId = this.message.getStringAttribute("user_id", "");
        String nickname = this.message.getStringAttribute("nickname", "");
        String first = this.message.getStringAttribute("first", "");
        String second = this.message.getStringAttribute("second", "");
        String third = this.message.getStringAttribute("third", "");
        ivRole.setRole(Integer.parseInt(role));
        ivNew.setNew(Integer.parseInt(userIsNew));
        ivGrade.setGrade(rankIcon);
        ivNHobility.setNobility(nobilityIcon);
        tvName.setText(Html.fromHtml(nickname));
        ivQiu1.setImageResource(getResources().getIdentifier(first, "mipmap", getContext().getPackageName()));
        ivQiu2.setImageResource(getResources().getIdentifier(second, "mipmap", getContext().getPackageName()));
        ivQiu3.setImageResource(getResources().getIdentifier(third, "mipmap", getContext().getPackageName()));
        SpanUtils spanUtils = new SpanUtils();
        spanUtils.append(message);
        tvContent.setText(spanUtils.create());
    }
}