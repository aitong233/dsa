package com.qpyy.room.adapter;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.libcommon.widget.GradeView;
import com.qpyy.libcommon.widget.NewView;
import com.qpyy.libcommon.widget.NobilityView;
import com.qpyy.libcommon.widget.RoleView;
import com.qpyy.room.R;
import com.qpyy.room.bean.CardResultBean;
import com.qpyy.room.bean.EMMessageInfo;

import java.lang.reflect.Type;
import java.util.List;


/**
 * @author xf
 */
public class EaseChatAdapter extends BaseMultiItemQuickAdapter<EMMessageInfo, BaseViewHolder> {

    private int[] a = {com.hyphenate.easeui.R.drawable.random0s, com.hyphenate.easeui.R.drawable.random1s, com.hyphenate.easeui.R.drawable.random2s, com.hyphenate.easeui.R.drawable.random3s, com.hyphenate.easeui.R.drawable.random4s, com.hyphenate.easeui.R.drawable.random5s, com.hyphenate.easeui.R.drawable.random6s, com.hyphenate.easeui.R.drawable.random7s, com.hyphenate.easeui.R.drawable.random8s, com.hyphenate.easeui.R.drawable.random9s};


    private RoleView ivRole;
    private NewView ivNew;
    private GradeView ivGrade;
    private NobilityView ivNHobility;
    private TextView tvName;
    private boolean isWelcome = true;//第一次接收到信息是欢迎语，可能有换行和空格 需要分开


    public EaseChatAdapter() {
        super(null);
        addItemType(1, R.layout.ease_row_received_message_system);//系统消息、官方公告
        addItemType(2, R.layout.ease_row_received_message_user_send);//用户发送的消息
        addItemType(3, R.layout.ease_row_received_message_join_room);//加入房间
        addItemType(4, R.layout.ease_row_received_message_new_user);//新用户注册
        addItemType(5, R.layout.ease_row_received_message_wagging);//送礼物
        addItemType(6, R.layout.ease_row_received_message_expression);//摇签、表情
        addItemType(8, R.layout.ease_row_received_message_game);//球球大作战
        addItemType(11, R.layout.ease_row_received_message_fudai);//银福袋
        addItemType(12, R.layout.ease_row_received_message_fudai);//金福袋
        addItemType(13, R.layout.ease_row_received_message_fudai);//钻福袋
        addItemType(14, R.layout.ease_row_received_message_turntable);//大转盘抽奖
        addItemType(1818, R.layout.ease_row_received_message_card);//卡牌
        addItemType(1819, R.layout.ease_row_received_message_touzi);//掷骰子
    }

    @Override
    protected void convert(BaseViewHolder helper, EMMessageInfo item) {
        EMMessage emMessage = item.getEmMessage();
        EMTextMessageBody txtBody = (EMTextMessageBody) emMessage.getBody();
        Spanned spanned = Html.fromHtml(txtBody.getMessage());
        String message = txtBody.getMessage();
        SpanUtils spanUtils = new SpanUtils();
        String role = emMessage.getStringAttribute("role", "0");
        String userIsNew = emMessage.getStringAttribute("user_is_new", "0");
        String rankIcon = emMessage.getStringAttribute("rank_icon", "");
        String nobilityIcon = emMessage.getStringAttribute("nobility_icon", "");
        String nickname = emMessage.getStringAttribute("nickname", "");
        String type = emMessage.getStringAttribute("type", "");
        switch (helper.getItemViewType()) {
            case 1:
                //如果等于空，隐藏
                if (TextUtils.isEmpty(type)) {
                    helper.getView(R.id.tv_content).setVisibility(View.GONE);
                    return;
                }
                //系统通知 6013
                if ("6013".equals(type)) {
                    helper.setText(R.id.tv_content, Html.fromHtml(txtBody.getMessage()));
                } else if ("6014".equals(type)) {//欢迎语 6014
                    helper.setText(R.id.tv_content, txtBody.getMessage());
//                    if (isWelcome) {
//
//                        isWelcome = false;
//                    } else {

//                    }
                } else {
                    helper.setText(R.id.tv_content, Html.fromHtml(txtBody.getMessage()));
                }
                break;
            case 2:
                ivRole = helper.getView(R.id.iv_role);
                ivNew = helper.getView(R.id.iv_new);
                ivGrade = helper.getView(R.id.iv_grade);
                ivNHobility = helper.getView(R.id.iv_nobility);
                tvName = helper.getView(R.id.tv_name);
                TextView tvContent2 = helper.getView(R.id.tv_content);
                LinearLayout linearLayout2 = helper.getView(R.id.ll0);
                ivRole.setRole(Integer.parseInt(role));
                ivNew.setNew(Integer.parseInt(userIsNew));
                ivGrade.setGrade(rankIcon);
                ivNHobility.setNobility(nobilityIcon);
                tvName.setText(Html.fromHtml(nickname));
                spanUtils.append(spanned);
                tvContent2.setText(spanUtils.create());
                if (item.getCustom() == 0) {
                    linearLayout2.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        int count = 0;

                        @Override
                        public boolean onPreDraw() {
                            if (count > linearLayout2.getChildCount()) {
                                linearLayout2.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                            SpanUtils spanUtils = new SpanUtils();
                            spanUtils.appendSpace(linearLayout2.getMeasuredWidth());
                            spanUtils.append(spanned);
                            tvContent2.setText(spanUtils.create());
                            count++;
                            return true;
                        }


                    });
                } else {
                    spanUtils.appendSpace(item.getCustom());
                    spanUtils.append(spanned);
                    tvContent2.setText(spanUtils.create());
                }

            case 3:
                ivRole = helper.getView(R.id.iv_role);
                ivNew = helper.getView(R.id.iv_new);
                ivGrade = helper.getView(R.id.iv_grade);
                ivNHobility = helper.getView(R.id.iv_nobility);
                tvName = helper.getView(R.id.tv_name);
                TextView tvContent3 = helper.getView(R.id.tv_content);
                LinearLayout linearLayout3 = helper.getView(R.id.ll0);
                ivRole.setRole(Integer.parseInt(role));
                ivNew.setNew(Integer.parseInt(userIsNew));
                ivGrade.setGrade(rankIcon);
                ivNHobility.setNobility(nobilityIcon);
                tvName.setText(Html.fromHtml(nickname));
                if (item.getCustom() == 0) {
                    linearLayout3.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        int count = 0;

                        @Override
                        public boolean onPreDraw() {
                            if (count > linearLayout3.getChildCount()) {
                                linearLayout3.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                            SpanUtils spanUtils = new SpanUtils();
                            spanUtils.appendSpace(linearLayout3.getMeasuredWidth());
                            spanUtils.append(spanned);
                            tvContent3.setText(spanUtils.create());
                            count++;
                            return true;
                        }
                    });
                } else {
                    spanUtils.appendSpace(item.getCustom());
                    spanUtils.append(spanned);
                    tvContent3.setText(spanUtils.create());
                }
                break;
            case 4:
                TextView tvContent7 = helper.getView(R.id.tv_content);
                ivNew = helper.getView(R.id.new_view);
                ivNew.setNew(1);
                tvContent7.setText(new SpanUtils().append(spanned).create());
                break;
            //抽签
            case 5:
                String number = emMessage.getStringAttribute("number", "");
                ivRole = helper.getView(R.id.iv_role);
                ivNew = helper.getView(R.id.iv_new);
                ivGrade = helper.getView(R.id.iv_grade);
                ivNHobility = helper.getView(R.id.iv_nobility);
                tvName = helper.getView(R.id.tv_name);
                TextView tvContent5 = helper.getView(R.id.tv_content);
                ivRole.setRole(Integer.parseInt(role));
                ivNew.setNew(Integer.parseInt(userIsNew));
                ivGrade.setGrade(rankIcon);
                ivNHobility.setNobility(nobilityIcon);
                tvName.setText(Html.fromHtml(nickname));
                tvContent5.setText(new SpanUtils().append(spanned).create());
                helper.setImageResource(R.id.iv_wagginh, a[Integer.parseInt(number)]);
                break;
            //表情
            case 6:
                ivRole = helper.getView(R.id.iv_role);
                ivNew = helper.getView(R.id.iv_new);
                ivGrade = helper.getView(R.id.iv_grade);
                ivNHobility = helper.getView(R.id.iv_nobility);
                tvName = helper.getView(R.id.tv_name);

                ivRole.setRole(Integer.parseInt(role));
                ivNew.setNew(Integer.parseInt(userIsNew));
                ivGrade.setGrade(rankIcon);
                ivNHobility.setNobility(nobilityIcon);
                tvName.setText(Html.fromHtml(nickname));
                ImageLoader.loadIcon(mContext, helper.getView(R.id.iv_expression), message);
                break;
            //球球大作战
            case 8:
                String first = emMessage.getStringAttribute("first", "");
                String second = emMessage.getStringAttribute("second", "");
                String third = emMessage.getStringAttribute("third", "");

                ivRole = helper.getView(R.id.iv_role);
                ivNew = helper.getView(R.id.iv_new);
                ivGrade = helper.getView(R.id.iv_grade);
                ivNHobility = helper.getView(R.id.iv_nobility);
                tvName = helper.getView(R.id.tv_name);
                TextView tvContent8 = helper.getView(R.id.tv_content);

                ivRole.setRole(Integer.parseInt(role));
                ivNew.setNew(Integer.parseInt(userIsNew));
                ivGrade.setGrade(rankIcon);
                ivNHobility.setNobility(nobilityIcon);
                tvName.setText(Html.fromHtml(nickname));
                if (emMessage.getIntAttribute("type", 0) == 6019) {
                    helper.setImageResource(R.id.iv_qiu1, mContext.getResources().getIdentifier(first, "mipmap", mContext.getPackageName()));
                    helper.setImageResource(R.id.iv_qiu2, mContext.getResources().getIdentifier(second, "mipmap", mContext.getPackageName()));
                    helper.setImageResource(R.id.iv_qiu3, mContext.getResources().getIdentifier(third, "mipmap", mContext.getPackageName()));
                }
                spanUtils.append(spanned);
                tvContent8.setText(spanUtils.create());
                break;
            case 1818:
                ivNHobility = helper.getView(R.id.iv_nobility);
                ivNHobility.setNobility(nobilityIcon);
                ivRole = helper.getView(R.id.iv_role);
                ivRole.setRole(Integer.parseInt(role));
                LinearLayout linearLayout1818 = helper.getView(R.id.ll0);
                ivNew = helper.getView(R.id.iv_new);
                ivNew.setNew(Integer.parseInt(userIsNew));
                ivGrade = helper.getView(R.id.iv_grade);
                ivGrade.setGrade(rankIcon);
                tvName = helper.getView(R.id.tv_name);
                tvName.setText(Html.fromHtml(nickname));
                String result = emMessage.getStringAttribute("cards", "[]");
                Type gt = new TypeToken<List<CardResultBean.CardsBean>>() {
                }.getType();
                List<CardResultBean.CardsBean> list = new Gson().fromJson(result, gt);
                TextView tv_card1 = helper.getView(R.id.tv_card1);
                TextView tv_card2 = helper.getView(R.id.tv_card2);
                TextView tv_card3 = helper.getView(R.id.tv_card3);
                TextView tv_card4 = helper.getView(R.id.tv_card4);
                TextView tv_card5 = helper.getView(R.id.tv_card5);
                TextView[] tvCards = {tv_card1, tv_card2, tv_card3, tv_card4, tv_card5};
                for (TextView tvCard : tvCards) {
                    tvCard.setVisibility(View.GONE);
                }
                for (int i = 0; i < list.size(); i++) {
                    TextView tvCard = tvCards[i];
                    tvCard.setVisibility(View.VISIBLE);
                    CardResultBean.CardsBean cardsBean = list.get(i);
                    setCard(tvCard, cardsBean);
                }

                break;
            case 1819:
                ivNHobility = helper.getView(R.id.iv_nobility);
                ivNHobility.setNobility(nobilityIcon);
                ivRole = helper.getView(R.id.iv_role);
                ivRole.setRole(Integer.parseInt(role));
                ivNew = helper.getView(R.id.iv_new);
                ivNew.setNew(Integer.parseInt(userIsNew));
                ivGrade = helper.getView(R.id.iv_grade);
                ivGrade.setGrade(rankIcon);
                tvName = helper.getView(R.id.tv_name);
                tvName.setText(Html.fromHtml(nickname));
                int tp = emMessage.getIntAttribute("result", 1);
                helper.setImageResource(R.id.iv_touzi, touziRes[tp - 1]);
                break;
            case 11:
                String img = "<img src=\"" + R.drawable.icon_fudai_yin + "\"/>";
                TextView tvFudai1 = helper.getView(R.id.tv_content);
                tvFudai1.setText(Html.fromHtml(img + txtBody.getMessage(), imageGetter, null));
                break;
            case 12:
                img = "<img src=\"" + R.drawable.icon_fudai_jin + "\"/>";
                TextView tvFudai2 = helper.getView(R.id.tv_content);
                tvFudai2.setText(Html.fromHtml(img + txtBody.getMessage(), imageGetter, null));
                break;
            case 13:
                img = "<img src=\"" + R.drawable.icon_fudai_zuan + "\"/>";
                TextView tvFudai3 = helper.getView(R.id.tv_content);
                tvFudai3.setText(Html.fromHtml(img + txtBody.getMessage(), imageGetter, null));
                break;
            default:
                helper.setText(R.id.tv_content, Html.fromHtml(txtBody.getMessage()));
                break;
        }
    }

    int[] touziRes = {R.drawable.touzi_1, R.drawable.touzi_2, R.drawable.touzi_3, R.drawable.touzi_4, R.drawable.touzi_5, R.drawable.touzi_6};

    void setCard(TextView tvCard, CardResultBean.CardsBean cardsBean) {
        switch (cardsBean.getCardtype()) {
            case "1":
                tvCard.setBackgroundResource(R.drawable.card_hong);
                tvCard.setTextColor(mContext.getResources().getColor(R.color.red));
                break;
            case "2":
                tvCard.setBackgroundResource(R.drawable.card_hei);
                tvCard.setTextColor(mContext.getResources().getColor(R.color.black));
                break;
            case "3":
                tvCard.setBackgroundResource(R.drawable.card_fang);
                tvCard.setTextColor(mContext.getResources().getColor(R.color.red));
                break;
            case "4":
                tvCard.setBackgroundResource(R.drawable.card_mei);
                tvCard.setTextColor(mContext.getResources().getColor(R.color.black));
                break;
        }
        tvCard.setText(String.format(" %s", cardsBean.getCardcode()));

    }

    private Html.ImageGetter imageGetter = source -> {
        int resId = Integer.parseInt(source);
        Drawable drawable = mContext.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, SizeUtils.dp2px(40f), SizeUtils.dp2px(30f));
        return drawable;
    };

    /**
     * 清除数据
     *
     * @param
     */
    public void clearData() {
        this.mData.clear();
        notifyDataSetChanged();
    }

    public void clearSomeData() {
        if (this.mData != null && mData.size() > 500) {
            List<EMMessageInfo> emMessageInfos = mData.subList(500, mData.size());
            setNewData(emMessageInfos);
        }
    }

}
