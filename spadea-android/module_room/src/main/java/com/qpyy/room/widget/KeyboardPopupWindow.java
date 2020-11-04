package com.qpyy.room.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.hjq.toast.ToastUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.widget
 * 创建人 黄强
 * 创建时间 2020/8/6 16:55
 * 描述 自定义数字键盘
 */

public class KeyboardPopupWindow extends PopupWindow {
    private static final String TAG = "KeyboardPopupWindow";
    private Context context;
    private View anchorView;
    private View parentView;
    private EditText editText;
    private boolean isRandomSort = false;//数字是否随机排序
    private List<Integer> list = new ArrayList<>();
    private int[] commonButtonIds = new int[]{R.id.bt_keyboard1, R.id.bt_keyboard2, R.id.bt_keyboard3, R.id.bt_keyboard4,
            R.id.bt_keyboard5, R.id.bt_keyboard6, R.id.bt_keyboard7, R.id.bt_keyboard8, R.id.bt_keyboard9, R.id.bt_keyboard0};

    private KeyboardCompleteListener mListener;

    /**
     * @param context
     * @param anchorView
     * @param
     * @param
     */
    public KeyboardPopupWindow(Context context, View anchorView) {
        this.context = context;
        this.anchorView = anchorView;
        this.isRandomSort = isRandomSort;
        if (context == null || anchorView == null) {
            return;
        }
        initConfig();
        initView();
        Log.d(TAG, "KeyboardPopupWindow: =====================打开了自定义键盘");
    }


    private void initConfig() {
        setOutsideTouchable(false);
        setFocusable(false);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        forbidDefaultSoftKeyboard();
    }

    /**
     * 禁止系统默认的软键盘弹出
     */
    private void forbidDefaultSoftKeyboard() {
        if (editText == null) {
            return;
        }
        if (android.os.Build.VERSION.SDK_INT > 10) {//4.0以上，使用反射的方式禁止系统自带的软键盘弹出
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 刷新自定义的popupwindow是否outside可触摸反应：如果是不可触摸的，则显示该软键盘view
     *
     * @param isTouchable
     */
    public void refreshKeyboardOutSideTouchable(boolean isTouchable) {
        setOutsideTouchable(isTouchable);
        if (!isTouchable) {
            show();
        } else {
            dismiss();
        }
    }

    private void initView() {
        parentView = LayoutInflater.from(context).inflate(R.layout.room_custom_num_keyboard, null);
        initKeyboardView(parentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(parentView);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setTouchable(true);
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
    }

    private void initKeyboardView(View view) {
        //初始化编辑框
        editText = view.findViewById(R.id.et_keyboard_input);
        editText.setFocusable(false);//禁止编辑框获取焦点
        //①给数字键设置点击监听
        for (int i = 0; i < commonButtonIds.length; i++) {
            final Button button = view.findViewById(commonButtonIds[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int curSelection = editText.getSelectionStart();
                    int length = editText.getText().toString().length();
                    if (curSelection < length) {
                        String content = editText.getText().toString();
                        editText.setText(content.substring(0, curSelection) + button.getText() + content.subSequence(curSelection, length));
                        editText.setSelection(curSelection + 1);
                    } else {
                        if(length == 0 && button.getText().toString().equals("0") || equals("")){
                            return;
                        }
                        editText.setText(editText.getText().toString() + button.getText());
                        editText.setSelection(editText.getText().toString().length());
                    }
                }
            });
        }

        //③给叉按键设置点击监听
        view.findViewById(R.id.rl_keyboard_cross).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = editText.getText().toString().length();
                int curSelection = editText.getSelectionStart();
                if (length > 0 && curSelection > 0 && curSelection <= length) {
                    String content = editText.getText().toString();
                    editText.setText(content.substring(0, curSelection - 1) + content.subSequence(curSelection, length));
                    editText.setSelection(curSelection - 1);
                }
            }
        });

        view.findViewById(R.id.iv_keyboard_cross).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        //确定
        view.findViewById(R.id.bt_keyboard_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 您输入的数量是：" + editText.getText().toString());
                mListener.inputComplete(editText.getText().toString());
            }
        });
    }


    public void show() {
        if (!isShowing() && anchorView != null) {
//            doRandomSortOp();
            this.showAtLocation(anchorView, Gravity.BOTTOM, 0, 0);
        }
    }

    /**
     * 随机分布数字
     */
    private void doRandomSortOp() {
        if (parentView == null) {
            return;
        }
        if (!isRandomSort) {
            for (int i = 0; i < commonButtonIds.length; i++) {
                final Button button = parentView.findViewById(commonButtonIds[i]);
                button.setText("" + i);
            }
        } else {
            list.clear();
            Random ran = new Random();
            while (list.size() < commonButtonIds.length) {
                int n = ran.nextInt(commonButtonIds.length);
                if (!list.contains(n))
                    list.add(n);
            }
            for (int i = 0; i < commonButtonIds.length; i++) {
                final Button button = parentView.findViewById(commonButtonIds[i]);
                button.setText("" + list.get(i));
            }
        }
    }

    public void releaseResources() {
        this.dismiss();
        context = null;
        anchorView = null;
        if (list != null) {
            list.clear();
            list = null;
        }
    }

    public void addRoomPasswordListener(KeyboardCompleteListener listener) {
        mListener = listener;
    }

    public interface KeyboardCompleteListener {

        void inputComplete(String inputContent);//输入完成
    }
}

