package com.spadea.xqipao.common.aroute;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.spadea.xqipao.utils.LogUtils;

@Interceptor(name = "login", priority = 6)
public class LoginInterceptorImpl implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String path = postcard.getPath();
        boolean isLogin = true;

        if (isLogin) { // 如果已经登录不拦截
            callback.onContinue(postcard);
        } else {  // 如果没有登录
//            switch (path) {
//                // 需要登录的直接拦截下来
//                case ARouters.TRAN_ENTRUSTACTIVITY:
//                case ARouters.MY_AUTHENTICATIONACTIVITY:
//                case ARouters.MY_SAFETYCENTERACTIVITY:
//                case ARouters.MY_SETTINGACTIVITY:
//                case ARouters.MY_INVITATIONACTIVITY:
//                case ARouters.MY_WALLETACTIVITY:
//                case ARouters.MY_MESSAGEACTIVITY:
//                case ARouters.MY_PERSONALDATAACTIVITY:
//                case ARouters.MY_DENSESUGARBENEFITACTIVITY:
//                    callback.onInterrupt(null);
//                    break;
//                default:
//                    // 不需要登录的直接进入这个页面
//                    callback.onContinue(postcard);
//                    break;
//            }
        }

    }



    @Override
    public void init(Context context) {
        LogUtils.e("路由", "路由登录拦截器初始化成功"); //只会走一次
    }

}