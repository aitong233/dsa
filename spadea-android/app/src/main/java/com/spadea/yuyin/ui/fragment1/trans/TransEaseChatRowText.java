package com.spadea.yuyin.ui.fragment1.trans;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.text.Html;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.utils.view.GradeView;
import com.hyphenate.easeui.utils.view.JueView;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.ConvertUtils;
import com.spadea.yuyin.util.utilcode.SizeUtils;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.yuyin.R;
import com.spadea.xqipao.data.even.EaseMesgEvent;
import com.spadea.xqipao.utils.ColorUtil;

import org.greenrobot.eventbus.EventBus;

public class TransEaseChatRowText extends EaseChatRow {

    private ConstraintLayout cl;

    private LinearLayout ll_level;
    private ImageView iv_level;
    private TextView tv_level;
    private TextView tv_name;
    private LinearLayout ll0;
    private JueView jueView;
    private GradeView gradeView;
    private ImageView ivOffical;
    private ImageView ivUserNew;
    private TextView tvWelcome;


    public TransEaseChatRowText(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
                R.layout.trans_ease_row_received_message : R.layout.trans_ease_row_received_message, this);
    }

    @Override
    protected void onFindViewById() {
        cl = findViewById(R.id.cl);
        ll_level = findViewById(R.id.ll_level);
        iv_level = findViewById(R.id.iv_level);
        tv_level = findViewById(R.id.tv_level);
        tv_name = findViewById(R.id.tv_name);
        ll0 = findViewById(R.id.ll0);
        jueView = findViewById(R.id.view_jue);
        gradeView = findViewById(R.id.view_grade);
        ivOffical = findViewById(R.id.iv_official);
        ivUserNew = findViewById(R.id.iv_user_new);
        tvWelcome = findViewById(R.id.tv_welcome);
    }

    @Override
    public void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        Spannable span = EaseSmileUtils.getSmiledText(context, Html.fromHtml(txtBody.getMessage()));
        String nickname = message.getStringAttribute("nickname", "");
        String avatar = message.getStringAttribute("avatar", "");
        int rankId = Integer.parseInt(message.getStringAttribute("rank_id", "0"));
        String rankName = message.getStringAttribute("rank_name", "");
        int nobilityId = message.getIntAttribute("nobility_id", 0);
        String nobilityName = message.getStringAttribute("nobility_name", "");
        int role = message.getIntAttribute("role", 0);
        int userIsNew = message.getIntAttribute("user_is_new", 0);
        int action = message.getIntAttribute("action", 0);
        jueView.setJue(nobilityId, nobilityName);
        gradeView.setGrade(rankId, rankName);
        switch (role) {
            case 1:
                ivOffical.setVisibility(VISIBLE);
                ivOffical.setImageResource(R.mipmap.img_host);
                break;
            case 2:
                ivOffical.setVisibility(VISIBLE);
                ivOffical.setImageResource(R.mipmap.img_admin);
                break;
            case 5:
                ivOffical.setVisibility(VISIBLE);
                ivOffical.setImageResource(R.mipmap.img_official);
                break;
            default:
                ivOffical.setVisibility(GONE);
                break;
        }
        ivUserNew.setVisibility(userIsNew == 1 ? VISIBLE : GONE);
        tvWelcome.setVisibility(action == 666 ? VISIBLE : GONE);

        SpanUtils spanUtils = new SpanUtils();
        ll0.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        switch (action) {
            case 1:
            case 5:
            case 7:
            case 8:
            case 9:
                ll0.setVisibility(View.GONE);
                break;
            default:
                ll0.setVisibility(View.VISIBLE);
                spanUtils.appendSpace(ll0.getMeasuredWidth() + SizeUtils.dp2px(4));
                tv_name.post(new Runnable() {
                    @Override
                    public void run() {
                        if (tv_name != null) {
                            int lines = tv_name.getLineCount();
                            if (lines > 1) {
                                tv_name.setPadding(0, ConvertUtils.dp2px(4), 0, 0);
                            } else {
                                tv_name.setPadding(0, 0, 0, 0);
                            }
                        }
                    }
                });
                break;
        }

        switch (action) {
            case 1://欢迎
                tv_name.setText(span);
                break;
            case 20200726://礼物
                Glide.with(context).load(ImageLoader.getUrl(message.getStringAttribute("gift_pic", ""))).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                        if (TextUtils.isEmpty(nickname)) {
                            tv_name.setText(spanUtils.append(message.getFrom() + "：").append(span).setForegroundColor(getResources().getColor(R.color.color_fde800)).appendImage(drawable, SpanUtils.ALIGN_TOP).append("×" + message.getStringAttribute("gift_num", "")).setForegroundColor(getResources().getColor(R.color.color_fde800)).create());
                        } else {
                            tv_name.setText(spanUtils.append(nickname + "：").append(span).setForegroundColor(getResources().getColor(R.color.color_fde800)).append(" ").appendImage(drawable, SpanUtils.ALIGN_TOP).append("×" + message.getStringAttribute("gift_num", "")).setForegroundColor(getResources().getColor(R.color.color_fde800)).create());
                        }
                    }
                });
                break;
            case 3://表情
                if (TextUtils.isEmpty(message.getStringAttribute("random_num", ""))) {
                    Glide.with(context).load(ImageLoader.getUrl(message.getStringAttribute("face_spectial", ""))).into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                            if (TextUtils.isEmpty(nickname)) {
                                tv_name.setText(spanUtils.append(message.getFrom() + "：").appendImage(drawable, SpanUtils.ALIGN_TOP).setFontSize(50).create());
                            } else {
                                tv_name.setText(spanUtils.append(nickname + "：").appendImage(drawable, SpanUtils.ALIGN_TOP).setFontSize(50).create());
                            }
                        }
                    });
                } else {
                    int[] a = {R.drawable.random0s, R.drawable.random1s, R.drawable.random2s, R.drawable.random3s, R.drawable.random4s, R.drawable.random5s, R.drawable.random6s, R.drawable.random7s, R.drawable.random8s, R.drawable.random9s};
                    int random_num = Integer.parseInt(message.getStringAttribute("random_num", ""));
                    if (TextUtils.isEmpty(nickname)) {
                        tv_name.setText(spanUtils.append(message.getFrom() + "：").appendImage(context.getResources().getDrawable(a[random_num]), SpanUtils.ALIGN_TOP).create());
                    } else {
                        tv_name.setText(spanUtils.append(nickname + "：").appendImage(context.getResources().getDrawable(a[random_num]), SpanUtils.ALIGN_TOP).create());
                    }
                }

                break;
            case 4:
                if (TextUtils.isEmpty(nickname)) {
                    tv_name.setText(spanUtils.append(message.getFrom() + "：").append(span).setForegroundColor(getResources().getColor(R.color.color_a439f4)).create());
                } else {
                    tv_name.setText(spanUtils.append(nickname + "：").append(span).setForegroundColor(getResources().getColor(R.color.color_a439f4)).create());
                }
                break;
            case 5:
                tv_name.setText(spanUtils.append(txtBody.getMessage()).setForegroundColor(getResources().getColor(R.color.color_e2bc41)).create());
                break;
            case 6:
                tv_name.setText(spanUtils.append(span).setForegroundColor(getResources().getColor(R.color.color_a439f4)).create());
                break;
            case 7:
                tv_name.setText(span);
                break;
            case 8: //官方公告
                tv_name.setText(spanUtils.append(span).setForegroundColor(getResources().getColor(R.color.color_e2bc41)).create());
                break;
            case 9:
                tv_name.setText(spanUtils.append(span).setForegroundColor(getResources().getColor(R.color.color_ffd700)).create());
                break;
            case 11:
                String qiu1 = message.getStringAttribute("qiu1", "");
                String qiu2 = message.getStringAttribute("qiu2", "");
                String qiu3 = message.getStringAttribute("qiu3", "");
                tv_name.setText(spanUtils.append(nickname + "：")
                        .append(span).setForegroundColor(getResources().getColor(R.color.color_fde800))
                        .append("   ")
                        .appendImage(context.getResources().getDrawable(getResources().getIdentifier(qiu1, "mipmap", context.getPackageName())), SpanUtils.ALIGN_TOP)
                        .append("   ")
                        .appendImage(context.getResources().getDrawable(getResources().getIdentifier(qiu2, "mipmap", context.getPackageName())), SpanUtils.ALIGN_TOP)
                        .append("   ")
                        .appendImage(context.getResources().getDrawable(getResources().getIdentifier(qiu3, "mipmap", context.getPackageName())), SpanUtils.ALIGN_TOP).create());
                break;
            case 666:
                tv_name.setText(spanUtils.append(nickname).setForegroundColor(ColorUtil.getColorWithRankId(rankId)).append("  进入房间").create());
                break;
            default:
                if (TextUtils.isEmpty(nickname)) {
                    tv_name.setText(spanUtils.append(message.getFrom() + "：").setForegroundColor(getResources().getColor(R.color.white)).append(span).setForegroundColor(getResources().getColor(R.color.white)).create());
                } else {
                    tv_name.setText(spanUtils.append(nickname + "：").setForegroundColor(getResources().getColor(R.color.white)).append(span).setForegroundColor(getResources().getColor(R.color.white)).create());
                }
                break;
        }
        cl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = message.getStringAttribute("user_id", "");
                if (!TextUtils.isEmpty(userId)) {
                    EventBus.getDefault().post(new EaseMesgEvent(message.getStringAttribute("user_id", "")));
                }
            }
        });
    }

    @Override
    protected void onViewUpdate(EMMessage msg) {

    }
}
