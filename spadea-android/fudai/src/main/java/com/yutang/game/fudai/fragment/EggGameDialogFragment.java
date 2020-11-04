package com.yutang.game.fudai.fragment;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.yutang.game.fudai.R;
import com.yutang.game.fudai.R2;
import com.yutang.game.fudai.base.BaseDialogFragment;
import com.yutang.game.fudai.bean.CatHelpModel;
import com.yutang.game.fudai.bean.FishInfoBean;
import com.yutang.game.fudai.bean.GetGameResultEvent;
import com.yutang.game.fudai.bean.LuckGiftBean;
import com.qpyy.libcommon.bean.SpriteInfo;
import com.yutang.game.fudai.bean.WinJackpotModel;
import com.yutang.game.fudai.contacts.EggGameDialogContacts;
import com.yutang.game.fudai.presenter.EggGamePresenter;
import com.yutang.game.fudai.utils.ImageLoader;
import com.yutang.game.fudai.utils.ViewWrapper;
import com.yutang.game.fudai.widget.Anim;
import com.yutang.game.fudai.widget.EggGamePoolDialog;
import com.yutang.game.fudai.widget.EggGameRuleDialog;
import com.yutang.game.fudai.widget.EggOpMoreDialog;
import com.yutang.game.fudai.widget.LuckGameResultView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class EggGameDialogFragment extends BaseDialogFragment<EggGamePresenter> implements EggGameDialogContacts.View, EggOpMoreDialog.OnItemClickListener {
    @BindView(R2.id.tv_balance)
    TextView mTvBalance;

    @BindView(R2.id.game_result)
    LuckGameResultView gameresult;

    @BindView(R2.id.svgav)
    SVGAImageView svga;

    private double balance;
    private double price;

    SVGAParser parser;
    private ObjectAnimator panim;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        parser = new SVGAParser(getContext());
        svg_jl.setLoops(-1);
        playJL("jingling_play.svga");
        svga.setLoops(1);
        svga.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                if (gameicon == null) return;
                gameicon.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int i, double v) {

            }
        });
        MvpPre.getFishInfo(type + "");

    }

    @BindView(R2.id.svg_jingling)
    SVGAImageView svg_jl;

    @BindView(R2.id.svg_update)
    SVGAImageView svg_update;

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        EventBus.getDefault().unregister(this);
    }

    void playUpdate(String name) {
        if (svg_update == null) return;
        if (svg_update.isAnimating()) return;
        svg_update.setLoops(1);
        parser.decodeFromAssets(name, new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                if (svg_update == null) return;
                SVGADrawable drawable = new SVGADrawable(svgaVideoEntity);
                svg_update.setImageDrawable(drawable);
                svg_update.startAnimation();
            }

            @Override
            public void onError() {

            }
        });
    }


    void playJL(String name) {
        parser.decodeFromAssets(name, new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                svg_jl.setVideoItem(svgaVideoEntity);
                svg_jl.startAnimation();
            }

            @Override
            public void onError() {

            }
        });
    }

    void playSvga(String name) {
        parser.decodeFromAssets(name, new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                if (svga == null) return;
                svga.setVideoItem(svgaVideoEntity);
                svga.startAnimation();
                gameicon.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
    }

    int pbMax = 0;

    @Override
    protected void initView(View rootView) {
        Window window = getDialog().getWindow();
        window.setWindowAnimations(R.style.DialogBottomIn);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);
        gameicon.post(new Runnable() {
            @Override
            public void run() {
                gameresult.initPoint((int) boxstart.getX(), (int) boxstart.getY(), (int) gameicon.getX() + SizeUtils.dp2px(30), (int) eggZs.getY(), gameicon.getWidth(), gameicon.getHeight() * 2);
            }
        });
        pb_bg.post(new Runnable() {
            @Override
            public void run() {
                pbMax = pb_bg.getHeight();
            }
        });
        Anim.shakeVertical(tip1);
        Anim.shakeVertical(tip2);
        Anim.shakeVertical(tip3);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.luck_game_layout;
    }

    @Override
    protected EggGamePresenter bindPresenter() {
        return new EggGamePresenter(this, getActivity());
    }


    FishInfoBean info = null;

    @Override
    public void setFishInfo(FishInfoBean fishInfo) {
        Log.i("砸蛋", "setFishInfo: " + new Gson().toJson(fishInfo));
        try {
            info = fishInfo;
            balance = Double.parseDouble(fishInfo.getUser_balance());
            price = Double.parseDouble(fishInfo.getSprite_list().get(0).getLucky_bag_one_cost());
            setPrice(info.getSprite_list().get(0).getLucky_bag_one_cost() + "");
            initEgg(1);
        } catch (Exception e) {
            LogUtils.e("startFishingSuccess", e);
        }
        if (!TextUtils.isEmpty(fishInfo.getUser_balance() + "")) {
            mTvBalance.setText(new BigDecimal(fishInfo.getUser_balance()).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
        } else {
            mTvBalance.setText("0.00");
        }
    }

    @Override
    public void startFishingSuccess(int num) {
//        if (num == 999) {
//            MvpPre.getFishInfo(type + "");
//        }
//        balance = balance - price * num;
//        mTvBalance.setText(new BigDecimal(balance).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
    }


    @Override
    public void gameResult(LuckGiftBean eggGiftModels, int num, int type) {

        if (!TextUtils.isEmpty(eggGiftModels.getUser_balance() + "")) {
            mTvBalance.setText(new BigDecimal(eggGiftModels.getUser_balance()).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
        } else {
            mTvBalance.setText("0.00");
        }
        initJL(eggGiftModels.getSprite_info(), eggGiftModels.getSprite_active() == 1);
        gameresult.addView(type == 1, eggGiftModels.getPrize_info());
        ResultDialogFragment.Companion.newInstance(eggGiftModels.getPrize_info(), num, type).show(getChildFragmentManager(), "福袋结果");

    }

    @BindView(R2.id.jl)
    View jl;
    @BindView(R2.id.pb_all)
    View pb_all;

    void initJL(LuckGiftBean.SpriteInfoBean luck, boolean show) {
        if (!show) {
            ViewCompat.animate(jl).alpha(0).setDuration(300).start();
            ViewCompat.animate(pb_all).alpha(0).setDuration(300).start();
            return;
        } else {
            ViewCompat.animate(jl).alpha(1).setDuration(300).start();
            ViewCompat.animate(pb_all).alpha(1).setDuration(300).start();
        }
        if (type == luck.getLucky_bag_type()) {
            float scale = (float) luck.getCurrent_exp() / luck.getNext_level_exp();
            if (luck.getNext_level_exp() == 0) scale = 1;
            setPb(scale);
        }
        ImageLoader.loadImageNC(gificon, luck.getPicture());
        ImageLoader.loadImageNC(specail, luck.getPicture());
        lev.setText("x" + luck.getCurrent_level());
    }

    @BindView(R2.id.gif_icon)
    ImageView gificon;
    @BindView(R2.id.level)
    TextView lev;

    @Override
    public void gameRule(CatHelpModel catHelpModel) {
        new EggGameRuleDialog(mContext, catHelpModel.getTitle(), catHelpModel.getContent()).show();
    }

    @Override
    public void poolList(List<WinJackpotModel> list) {
        new EggGamePoolDialog(mContext, list, type).show();
    }


    int type = 1;//1普通蛋2金蛋3钻石蛋
    //1单点奖池3普通奖池7金蛋奖池8金蛋单点奖池9钻石蛋奖池10钻石蛋单点奖池

    @BindView(R2.id.click_yin)
    View eggYin;
    @BindView(R2.id.click_jin)
    View eggJin;
    @BindView(R2.id.click_zuan)
    View eggZs;
    @BindView(R2.id.tip1)
    View tip1;
    @BindView(R2.id.tip2)
    View tip2;
    @BindView(R2.id.tip3)
    View tip3;

    void initEgg(int t) {
        this.type = t;
        eggYin.setAlpha(0.6f);
        eggJin.setAlpha(0.6f);
        eggZs.setAlpha(0.6f);
        // eggZs.setBackgroundResource(R.mipmap.egg_click_zs);
        if (info == null)
            return;
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    if (info.getSprite_list().get(0).getSprite_active()) {
                        Anim.alphaIn(tip1);
                    } else {
                        Anim.alphaOut(tip1);
                    }
                    break;
                case 1:
                    if (info.getSprite_list().get(1).getSprite_active()) {
                        Anim.alphaIn(tip2);
                    } else {
                        Anim.alphaOut(tip2);
                    }
                    break;
                case 2:
                    if (info.getSprite_list().get(2).getSprite_active()) {
                        Anim.alphaIn(tip3);
                    } else {
                        Anim.alphaOut(tip3);
                    }
                    break;
            }
        }


        switch (type) {
            case 1:
                ViewCompat.animate(eggYin).alpha(1).setDuration(200).start();
                gameicon.setBackgroundResource(R.mipmap.game_yin);
                setPrice(info.getSprite_list().get(0).getLucky_bag_one_cost());
                if (info.getSprite_list().get(0).getSprite_active()) {
                    initJL(info.getSprite_list().get(0).getSprite_info(), true);
                } else {
                    initJL(info.getSprite_list().get(0).getSprite_info(), false);
                }
                break;
            case 2:
                ViewCompat.animate(eggJin).alpha(1).setDuration(200).start();
                gameicon.setBackgroundResource(R.mipmap.game_jin);
                setPrice(info.getSprite_list().get(1).getLucky_bag_one_cost());
                if (info.getSprite_list().get(1).getSprite_active()) {
                    initJL(info.getSprite_list().get(1).getSprite_info(), true);
                } else {
                    initJL(info.getSprite_list().get(1).getSprite_info(), false);
                }
                break;
            case 3:
                ViewCompat.animate(eggZs).alpha(1).setDuration(200).start();
                gameicon.setBackgroundResource(R.mipmap.game_zuan);
                setPrice(info.getSprite_list().get(2).getLucky_bag_one_cost());
                if (info.getSprite_list().get(2).getSprite_active()) {
                    initJL(info.getSprite_list().get(2).getSprite_info(), true);
                } else {
                    initJL(info.getSprite_list().get(2).getSprite_info(), false);
                }
                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetResult(GetGameResultEvent event) {
        MvpPre.startFishing(event.getNum(), event.getType());
    }

    //    @BindView(R2.id.egg_view)
//    ImageView eggView;
    @BindView(R2.id.price1)
    TextView price1;
    @BindView(R2.id.price2)
    TextView price2;
    @BindView(R2.id.price3)
    TextView price3;

    @BindView(R2.id.pb_bg)
    View pb_bg;
    @BindView(R2.id.pb)
    View pb;

    public void setPb(float scale) {
        Log.i("进度", "setPb: " + scale + "," + (int) (scale * pbMax));
        //if(panim!=null&&panim.isRunning())panim.end();
        ViewWrapper viewWrapper = new ViewWrapper(pb);
        panim = ObjectAnimator.ofInt(viewWrapper, "trueHeight", (int) (scale * pbMax)).setDuration(300);
        panim.start();
    }


    @BindView(R2.id.special_gift)
    ImageView specail;
    @BindView(R2.id.special_pos)
    ImageView specail_pos;

    void showSpecial(String icon, String name) {
        gificon.post(new Runnable() {
            @Override
            public void run() {
                ImageLoader.loadImageNC(specail, icon);
                Anim.moveTo(specail, gificon.getX(), gificon.getY(), specail_pos.getX(), specail_pos.getY());
                ToastUtils.showShort("恭喜您获得黑桃A礼物 " + name);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSpinfo(SpriteInfo sp) {
        if (pb == null || sp == null) return;
        Log.i("黑桃A推送", "onSpinfo: " + new Gson().toJson(sp));
        if (type == sp.getSprite_info().getLucky_bag_type()) {
            float scale = (float) sp.getSprite_info().getCurrent_exp() / sp.getSprite_info().getNext_level_exp();
            if (sp.getSprite_info().getNext_level_exp() == 0) scale = 1;
            setPb(scale);
//            if(sp.getAction()==3){
//                playUpdate("jingling_update.svga");
//            }
            switch (sp.getAction()) {
                case 3:
                    playUpdate("jingling_update.svga");
                    break;

                case 2:
                    initJL(null, false);
                    break;
            }
            lev.setText("");
        }

        if (info != null) {
            FishInfoBean.SpriteListBean change;
            switch (sp.getSprite_info().getLucky_bag_type()) {
                case 1:
                    change = info.getSprite_list().get(0);
                    break;
                case 2:
                    change = info.getSprite_list().get(1);
                    break;
                case 3:
                    change = info.getSprite_list().get(2);
                    break;
                default:
                    change = info.getSprite_list().get(0);
                    break;
            }
            if (sp.getAction() == 2) {
                change.setSprite_active("0");
            } else {
                change.setSprite_active("1");
                change.getSprite_info().setCurrent_level(sp.getSprite_info().getCurrent_level());
                change.getSprite_info().setCurrent_exp(sp.getSprite_info().getCurrent_exp());
                change.getSprite_info().setNext_level_exp(sp.getSprite_info().getNext_level_exp());
                change.getSprite_info().setGift_id(sp.getSprite_info().getGift_id());
                change.getSprite_info().setName(sp.getSprite_info().getName());
                change.getSprite_info().setPicture(sp.getSprite_info().getPicture());
                change.getSprite_info().setSpecial(sp.getSprite_info().getSpecial());
            }
            lev.setText("x" + change.getSprite_info().getCurrent_level());

        }


    }


    @BindView(R2.id.game_icon)
    View gameicon;


    @OnClick({R2.id.tv_recharge, R2.id.tv_list, R2.id.tv_more, R2.id.tv_1, R2.id.tv_10, R2.id.tv_100, R2.id.click_jin, R2.id.click_yin, R2.id.click_zuan})
    public void onViewClicked(View view) {
        if (info == null) return;
        int id = view.getId();
        if (id == R.id.tv_recharge) {
            ARouter.getInstance().build(ARouteConstants.ME_BALANCE).navigation();
        } else if (id == R.id.tv_list) {
            new GameRankDialogFragment().show(getFragmentManager());
        } else if (id == R.id.tv_more) {
            EggOpMoreDialog eggOpMoreDialog = new EggOpMoreDialog(mContext);
            eggOpMoreDialog.setListener(this);
            eggOpMoreDialog.show();
        } else if (id == R.id.tv_1) {
            MvpPre.startFishing(1, type);
            playAnim();
        } else if (id == R.id.tv_10) {
            MvpPre.startFishing(10, type);
            playAnim();
        } else if (id == R.id.tv_100) {
            MvpPre.startFishing(100, type);
            playAnim();
        } else if (id == R.id.click_yin) {
            initEgg(1);
        } else if (id == R.id.click_jin) {
            initEgg(2);
        } else if (id == R.id.click_zuan) {
            initEgg(3);
        }
    }

    private void playAnim() {

        switch (type) {
            case 1:
                svga.setLoops(1);
                playSvga("anim_yin.svga");
                break;

            case 2:
                svga.setLoops(2);
                playSvga("anim_jin.svga");
                break;

            case 3:
                svga.setLoops(3);
                playSvga("anim_zuan.svga");
                break;
        }
    }

    @BindView(R2.id.boxstart)
    View boxstart;


    void setPrice(String s) {
        price1.setText(s);
        price2.setText(s + "0");
        price3.setText(s + "00");
    }

    @Override
    public void logClick() {
        new GameLogDialogFragment().show(getFragmentManager());
    }

    @Override
    public void ruleClick() {
        MvpPre.getRules();
    }


    @OnClick(R2.id.click_pool)
    @Override
    public void poolClick() {
        MvpPre.getPool(type + "");
    }

    @Override
    public void exitClick() {
        dismiss();
    }

    @Override
    public void showLoadings() {
        if (isAdded() && getActivity() != null) {
            ((BaseMvpActivity) getActivity()).showLoadings();
        }
    }

    @Override
    public void showLoadings(String content) {

    }

    @Override
    public void disLoadings() {
        if (isAdded() && getActivity() != null) {
            ((BaseMvpActivity) getActivity()).disLoadings();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @OnClick(R2.id.close)
    public void close(View v) {
        dismiss();
        //Anim.bottomIn(v);
    }


    @Override
    public void dismiss() {
        super.dismiss();
        EventBus.getDefault().unregister(this);
    }
}
