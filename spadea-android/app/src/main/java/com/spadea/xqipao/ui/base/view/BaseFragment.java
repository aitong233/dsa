package com.spadea.xqipao.ui.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.utils.dialog.LoadingAnim;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView<FragmentActivity> {
    protected P MvpPre;
    protected View rootView;
    protected Context mContext;
    protected Unbinder mUnbinder;
    protected LoadingAnim loadingAnim;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvpPre = bindPresenter();
    }

    protected abstract P bindPresenter();

    @Override
    public FragmentActivity getSelfActivity() {
        return getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = LayoutInflater.from(getActivity()).inflate(getLayoutId(), container, false);
            mContext = getContext();
            mUnbinder = ButterKnife.bind(this, rootView);
            initView(rootView);
            initData();
            initListener();
        } else {
            mUnbinder = ButterKnife.bind(this, rootView);
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    protected void initListener() {

    }


    protected abstract void initData();

    protected abstract void initView(View rootView);

    protected abstract int getLayoutId();


    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 在生命周期结束时，将presenter与view之间的联系断开，防止出现内存泄露
         */
        mUnbinder.unbind();
        if (MvpPre != null) {
            MvpPre.detachView();
        }
    }


    /**
     * 隐藏系统键盘
     */

    public void closeInputMethod(EditText editText) {
        try {
            InputMethodManager imm = (InputMethodManager) mContext.getApplicationContext().
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }

    /**
     * 显示系统键盘
     */
    public static void openInputMethod(final EditText editText, final Context context) {
        if (editText != null) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethodManager != null;
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 200);
    }

    public void showLoading() {
        if (!isAdded() || getActivity() == null) {
            return;
        }
        if (loadingAnim == null) {
            loadingAnim = new LoadingAnim(mContext);
        }
        loadingAnim.show();
    }

    public void disLoading() {
        if (loadingAnim != null) {
            loadingAnim.cancel();
        }
    }


}
