package com.hyphenate.easeui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.adapter.EaseConversationAdapter;

import java.util.List;

public class EaseConversationList extends ListView {

    protected int primaryColor;
    protected int secondaryColor;
    protected int timeColor;
    protected int primarySize;
    protected int secondarySize;
    protected float timeSize;


    protected Context context;
    protected EaseConversationAdapter adapter;
    protected List<EMConversation> passedListRef = null;


    public EaseConversationList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EaseConversationList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseConversationList);
        primaryColor = ta.getColor(R.styleable.EaseConversationList_cvsListPrimaryTextColor, getResources().getColor(R.color.list_itease_primary_color));
        secondaryColor = ta.getColor(R.styleable.EaseConversationList_cvsListSecondaryTextColor, getResources().getColor(R.color.list_itease_secondary_color));
        timeColor = ta.getColor(R.styleable.EaseConversationList_cvsListTimeTextColor, getResources().getColor(R.color.list_itease_secondary_color));
        primarySize = ta.getDimensionPixelSize(R.styleable.EaseConversationList_cvsListPrimaryTextSize, 0);
        secondarySize = ta.getDimensionPixelSize(R.styleable.EaseConversationList_cvsListSecondaryTextSize, 0);
        timeSize = ta.getDimension(R.styleable.EaseConversationList_cvsListTimeTextSize, 0);

        ta.recycle();

    }

    public void init(List<EMConversation> conversationList) {
        this.init(conversationList, null);
    }

    public void init(List<EMConversation> conversationList, EaseConversationListHelper helper) {
        if (helper != null) {
            this.conversationListHelper = helper;
        }
        adapter = new EaseConversationAdapter(context, 0, conversationList);
        adapter.setCvsListHelper(conversationListHelper);
        adapter.setPrimaryColor(primaryColor);
        adapter.setPrimarySize(primarySize);
        adapter.setSecondaryColor(secondaryColor);
        adapter.setSecondarySize(secondarySize);
        adapter.setTimeColor(timeColor);
        adapter.setTimeSize(timeSize);
        setAdapter(adapter);
    }


    public EMConversation getItem(int position) {
        return (EMConversation) adapter.getItem(position);
    }

    public void refresh(List<EMConversation> loadConversationList) {

        if (adapter != null) {
            adapter.setNewDatas(loadConversationList);
        }
    }

    public void addData(List<EMConversation> loadConversationList) {
        if (adapter != null) {
            adapter.addData(loadConversationList);
        }
    }

    public void filter(CharSequence str) {
        adapter.getFilter().filter(str);
    }


    private EaseConversationListHelper conversationListHelper;


    public interface EaseConversationListHelper {
        /**
         * set content of second line
         *
         * @param lastMessage
         * @return
         */
        String onSetItemSecondaryText(EMMessage lastMessage);
    }

    public void setConversationListHelper(EaseConversationListHelper helper) {
        conversationListHelper = helper;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量的大小由一个32位的数字表示，前两位表示测量模式，后30位表示大小，这里需要右移两位才能拿到测量的大小
        int heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
