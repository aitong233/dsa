package com.spadea.yuyin.net

import com.qpyy.libcommon.BuildConfig


/**
 * Copyright (c) 1
 *
 * @Description
 * @Author         1
 * @Copyright      Copyright (c) 1
 * @Date           $date$ $time$
 */
class UrlUtils {

    val APIHTTP = BuildConfig.BASE_URL
    var login = "$APIHTTP/Api/PublicApi/login"//登录

    var userInfo = "$APIHTTP/Api/UserCenterApi/userInfo"//获取个人资料

    var smsCode = "$APIHTTP/Api/PublicApi/smsCode"//发送手机验证码

    var checkOldMobile = "$APIHTTP/Api/UserCenterApi/checkOldMobile"//验证旧手机
    var bindingMobile = "$APIHTTP/Api/UserCenterApi/bindingMobile"//绑定手机号

    var realNameAuthentication = "$APIHTTP/Api/UserCenterApi/realNameAuthentication"//实名认证

    var userSetInform = "$APIHTTP/Api/UserCenterApi/userSetInform"//消息设置
    var userSetCloaking = "$APIHTTP/Api/UserCenterApi/userSetCloaking"//隐身设置
    var feedback = "$APIHTTP/Api/UserCenterApi/feedback"//意见反馈

    var register = APIHTTP + "Api/PublicApi/register"//注册

    var info = "$APIHTTP/Api/HelpCenter/info"//详情

    var shutup = "$APIHTTP/Api/UserRoomApi/shutup"//禁言/解除禁麦房间用户

    var reportUser = "$APIHTTP/Api/UserRoomApi/reportUser"//举报用户

    var tipOffRoom = "$APIHTTP/Api/UserRoomApi/tipOffRoom"//举报房间
    var giftWall = "$APIHTTP/Api/UserCenterApi/giftWall"//礼物墙

    var nobility = "$APIHTTP/Api/articleApi/nobility"//爵位说明web
    var member = "$APIHTTP/Api/articleApi/member"//爵位说明web
    var giveGift = "$APIHTTP/Api/UserCenterApi/giveGift"//赠送礼物

    var userNews = "$APIHTTP/Api/UserCenterApi/userNews"//验证房间密码

    var serviceUser = "$APIHTTP/Api/UserCenterApi/serviceUser"//消息列表


    val closepit = "$APIHTTP/Api/UserRoomApi/closePit" //封麦
}