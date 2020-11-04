package com.qpyy.module.me.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.adapter.FriendsAdapter;
import com.qpyy.module.me.bean.SearchFriendResp;
import com.qpyy.module.me.contacts.SearchFriendConacts;
import com.qpyy.module.me.presenter.SearchFriendPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索好友
 */
@Route(path = ARouteConstants.SEARCH_FRIEND)
public class SearchFriendActivity extends BaseMvpActivity<SearchFriendPresenter> implements SearchFriendConacts.View {


    @BindView(R2.id.ed_text)
    EditText edText;
    @BindView(R2.id.iv_clean)
    ImageView ivClean;
    @BindView(R2.id.tv_close)
    TextView tvClose;
    @BindView(R2.id.tv_number)
    TextView tvNumber;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    private FriendsAdapter mFriendsAdapter;

    @Autowired
    public int type;

    @Override
    protected SearchFriendPresenter bindPresenter() {
        return new SearchFriendPresenter(this, this);
    }

    @Override
    protected void initData() {
        if (type == 0) {
            edText.setHint("请输入好友昵称");
        } else if (type == 1) {
            edText.setHint("请输入关注昵称");
        } else {
            edText.setHint("请输入粉丝昵称");
        }
    }

    @Override
    protected void initView() {
        super.initView();
        edText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyWord = edText.getText().toString().trim();
                if (TextUtils.isEmpty(keyWord)) {
                    ivClean.setVisibility(View.GONE);
                } else {
                    ivClean.setVisibility(View.VISIBLE);
                    MvpPre.search(type, keyWord);
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mFriendsAdapter = new FriendsAdapter());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_search_friend;
    }


    @OnClick({R2.id.tv_close, R2.id.iv_clean})
    public void onClick(View view) {
        if (view.getId() == R.id.tv_close) {
            finish();
        } else if (view.getId() == R.id.iv_clean) {
            edText.setText("");
        }
    }

    @Override
    public void setData(SearchFriendResp resp) {
        String text = "好友";
        if (type == 1) {
            text = "关注";
        } else if (type == 2) {
            text = "粉丝";
        }
        tvNumber.setVisibility(View.VISIBLE);
        tvNumber.setText(String.format("%s个%s", resp.getCount(), text));
        mFriendsAdapter.setNewData(resp.getList());
    }
}
