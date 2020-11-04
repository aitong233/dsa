package com.spadea.xqipao.common;


import com.spadea.yuyin.BuildConfig;

public class Constant {


    public static final String PLAY_MODE = "PLAY_MODE"; //播放模式  0 顺序循序播放   1随机播放   2单曲循环
    public static final String AUTHORIZATION = "AUTHORIZATION";
    public static final String ISFIRSTS = "ISFIRSTS";
    public static final String SCHEME = "qpyy";

    public final class URL {

        public static final String URL_ACTIVITY_51 = com.qpyy.libcommon.BuildConfig.BASE_URL + "/home/banner/detail?id=20";
        public static final String QIU_IMG = "https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/images/qiu.gif";
        public static final String SHARE = com.qpyy.libcommon.BuildConfig.BASE_URL + "/api/AboutApi/download";
        private static final String BASE_URL = com.qpyy.libcommon.BuildConfig.BASE_URL;
        public static final String URL_LOGOUT_HELP = BASE_URL + "/help/#/help";
        public static final String URL_LOGOUT_CANCELLATION = BASE_URL + "/help/#/cancellation";
        public static final String URL_USER_YHXY = BASE_URL + "/api/article/info/id/18";
        public static final String URL_USER_YSXY = BASE_URL + "/api/article/info/id/19";
        public static final String HOME_BANNER = BASE_URL + "/home/banner/detail?id=";
        public static final String ARTICLE = BASE_URL + "/api/articleApi/info/id/";
        public static final String ARTICLEAPI_NOBILITY = BASE_URL + "/Api/articleApi/nobility";
        public static final String SETSECONDPASSWORD = BASE_URL + "/Api/UserCenterApi/setSecondPassword";
        public static final String PROTOCOL = BASE_URL + "/Api/PublicApi/protocol";
        public static final String CHARM = "/api/ranking/charm";
        public static final String WEALTH = "/api/ranking/rich";
        public static final String WEEK_STAR = "/api/ranking/star";
        public static final String ROOM = "/api/ranking/room";
        public static final String CHECKROOMPASSWORD = "/Api/UserRoomApi/checkRoomPassword";
        public static final String ADDBANK = "/Api/UserCenterApi/addUserBank";
        public static final String SEND_CODE = "/Api/PublicApi/smsCode";
        public static final String USER_BANK = "/api/UserCenterApi/userBank";
        public static final String ALIPAY_INFO = "/api/user/alipayInfo";
        public static final String RECHARGE = "/api/UserCenterApi/userRechargeMoney";
        public static final String RECHARGE_INFO = "api/UserCenterApi/rechargeInfo";
        public static final String ALIPAYMENT = "/Api/Payment/payment";
        public static final String WXPAYMENT = "/Api/Wxpay/payment";
        public static final String EDITBANK = "/api/UserCenterApi/editBank";
        public static final String USERWITHDRAW = "/Api/UserCenterApi/userWithdraw";
        public static final String LOGIN = "/Api/PublicApi/login";
        public static final String SETUSERSEX = "/Api/PublicApi/setUserSex";
        public static final String THIRDPARTYLOGIN = "/Api/PublicApi/thirdPartyLogin";
        public static final String CONVERTEARNINGS = "/Api/UserCenterApi/convertEarnings";
        public static final String EARNINGS = "/api/account/earnings";
        public static final String CASHLOG = "/api/account/cashLog";
        public static final String WINRANKING = "/api/LuckyBag/rankList";
        public static final String CATHELP = "/Api/game/help";
        public static final String WINJACKPOT = "/api/LuckyBag/prizelConfig";
        public static final String BALANCE = "/api/account/balance";
        public static final String FISHING = "/Api/LuckyBag/lottery";
        public static final String ROOMD_DETAILS = "/Api/UserRoomApi/userRoomInfo";
        public static final String FACELIST = "/Api/UserRoomApi/faceList";
        public static final String SEARCH_USER = "/api/room/search";
        public static final String ADD_MANAGER = "/api/room/setManager";
        public static final String DELETE_MANAGER = "/api/room/deleteManager";
        public static final String ADD_FORBID = "/api/room/forbid";
        public static final String DELETE_FORBID = "/api/room/deleteForbid";
        public static final String JOINROOM = "/api/room/join";
        public static final String ADDFAVORITE = "/api/room/addFavorite";
        public static final String APPLY_WHEAT = "/api/room/applyWheat";
        public static final String DOWN_WHEAT = "/api/room/downWheat";
        public static final String SWITCHVOICE = "/api/room/switchVoice";
        public static final String CLEAR_CARDIAC = "/api/room/clearCardiac";
        public static final String SHUT_UP = "/Api/UserRoomApi/shutup";
        public static final String CLOSEPIT = "/Api/UserRoomApi/closePit";
        public static final String ROOM_USER_INFO = "/api/user/info";
        public static final String GIFT_WALL = "/Api/UserCenterApi/giftWall";
        public static final String USER_BACKPACK = "/Api/UserRoomApi/userBackPack";
        public static final String ROOM_ENTER = "/api/room/enter";
        public static final String ROOM_EXTRAINFO = "/api/room/extraInfo";
        public static final String DOWN_USER_WHEAT = "/api/room/downUserWheat";
        public static final String FOLLOW = "/api/user/follow";
        public static final String SETROOMBANNED = "/api/UserRoomApi/setRoomBanned";
        public static final String EDIT_ROOM = "/api/room/edit";
        public static final String EDIT_ROOM_BG = "/api/room/editBackground";
        public static final String GIVEGIFT = "/api/room/giveGift";
        public static final String GIVEBACKGIFT = "/api/room/giveBackGift";
        public static final String APPLYWHEATLIST = "/api/room/applyWheatList";
        public static final String DELETEAPPLY = "/api/room/deleteApply";
        public static final String APPLYWHEATWAIT = "/api/room/applyWheatWait";
        public static final String AGREEAPPLY = "/api/room/agreeApply";
        public static final String AGREEAPPLYALL = "/api/room/agreeApplyAll";
        public static final String CLEARROOMCARDIAC = "/api/room/clearRoomCardiac";
        public static final String SETROOMCARDIAC = "/api/room/setRoomCardiac";
        public static final String REMOVEFAVORITE = "/api/room/removeFavorite";
        public static final String KICKOUT = "/api/room/kickOut";
        public static final String QUIT = "/api/room/quit";
        public static final String ROOMAUTH = "/api/room/roomAuth";
        public static final String ONLINE = "/api/index/online";
        public static final String BANNERS = "/api/index/banners";
        public static final String ROOMTYPE = "/api/index/roomType";
        public static final String ROOMLIST = "/api/index/roomList";
        public static final String HOTROOM = "/api/index/hotRoom";
        public static final String MANAGE_ROOM = "/api/room/manage";
        public static final String COLLECT_ROOM = "/api/room/collect";
        public static final String ISFOUNDROOM = "/Api/UserRoomApi/isFoundRoom";
        public static final String FRIENDLIST = "/api/user/friendList";
        public static final String FOLLOWLIST = "/api/user/followList";
        public static final String FANSLIST = "/api/user/fansList";
        public static final String MYINFO = "/api/user/myInfo";
        public static final String CATEGORIES = "/api/mall/categories";
        public static final String PRODUCTS = "/api/mall/products";
        public static final String BUY_SHOP = "/api/mall/buy";
        public static final String USERINFO = "/api/user/info";
        public static final String SEARCHUSER = "/api/index/searchUser";
        public static final String SEARCHROOM = "/api/index/searchRoom";
        public static final String VIPINFO = "/api/user/vipInfo";
        public static final String SERVICEUSER = "/Api/UserCenterApi/serviceUser";
        public static final String ARTICLE_CATEGORIES = "/api/article/categories";
        public static final String ARTICLE_LIST = "/api/article/list";
        public static final String USER_NOBILITYINFO = "/api/user/nobilityInfo";
        public static final String NOBILITY = "/api/user/nobility";
        public static final String BUYNOBILITY = "/api/user/buyNobility";
        public static final String RENEWNOBILITY = "/api/user/renewNobility";
        public static final String MYPRODUCTS = "/api/mall/myProducts";
        public static final String MYUSINGPRODUCTS = "/api/mall/myUsingProducts";
        public static final String USEPRODUCT = "/api/mall/useProduct";
        public static final String UPDATE_USERINFO = "/api/user/update";
        public static final String INDEX_LABEL = "/api/index/label";
        public static final String ADDLABEL = "/api/user/addLabel";
        public static final String USERNEWS = "/Api/UserCenterApi/userNews";
        public static final String CASHTYPE = "/api/account/cashType";
        public static final String ROOM_ONLINE = "/api/room/online";
        public static final String ROOMUSERINFO = "/api/user/roomInfo";
        public static final String ROOM_USER_SHUTUP = "/api/room/shutup";
        public static final String ROOM_PITINFO = "/api/room/pitInfo";
        public static final String COMEUSER = "/Api/UserCenterApi/comeUser";
        public static final String TOPTWO = "/api/index/topTwo";
        public static final String APPUPDATE = "/api/PublicApi/androidVersion";
        public static final String CHECK_UPDATE = "/api/PublicApi/IsUpdateVersion";
        public static final String RANDOMHOTROOM = "/api/room/randomHotRoom";
        public static final String ROOM_GETIN = "/api/room/getIn";
        public static final String PUTONWHEAT = "/api/room/putOnWheat";
        public static final String UPDATEPASSWORD = "/api/room/updatePassword";
        public static final String PITLIST = "/api/room/pitList";
        public static final String ROOM_ROLL = "/api/room/roll";
        public static final String GETINROOMINFO = "/api/room/getInRoomInfo";
        public static final String USER_FILES = "/Api/UserCenterApi/userInfo";
        public static final String REGION_LIST = "/Api/PublicApi/regionList";
        public static final String BIND_MOBILE = "/Api/UserCenterApi/bindingMobile";
        public static final String RESET_PASSWORD = "/Api/UserCenterApi/resetPassword";
        public static final String SEARCHMUSIC = "/api/MusicApi/index";
        public static final String MESSAGE_SETTING = "/Api/UserCenterApi/userSetInform";
        public static final String USER_BLACK_LIST = "/Api/UserCenterApi/userBlackList";
        public static final String ADD_BLACK_USER = "/Api/UserCenterApi/addBlackUser";
        public static final String PIT_COUNT_DOWN = "/api/room/pitCountDown";
        public static final String APPLY_WHEAT_FM = "/api/room/applyWheatFm"; //电台上麦
        public static final String FM_OPEN_PROTECTED = "/api/room/openProtect"; //开通守护接口
        public static final String GET_PROTECTED_RANKING_LIST = "/api/room/protectRanking";//守护排行榜接口
        public static final String GET_PROTECTED_LIST = "/api/room/getProtectList";//获取守护信息列表
        public static final String GET_ANCHOR_RANKING_LIST = "/api/room/getAnchorRankingList";//主播排行榜
        public static final String GET_FISH_INFO = "/api/LuckyBag/allSprite";//小猫钓鱼信息
        public static final String ROOM_BACKGROUND_LIST = "/api/index/roomBackground";//房间背景列表
        public static final String UPDATE_USER_AVATR = "/api/user/updateAvatar";//修改用户头像，审核
        public static final String QUIT_ROOM_WITH_USER_ID = "/api/index/quitRoom";//用户被动登出后退出房间
        public static final String CANCEL_ROOM_MANAGER = "/api/room/cancleRoomManger";//取消房间管理员
        public static final String SIGN_HISTORY = "/api/sign/history";//签到记录
        public static final String SIGN_REWARD_CONTINOUS = "/api/sign/rewardContinuous";//连续签到奖励
        public static final String SIGN_IN = "/api/sign/signIn";//取消房间管理员
        public static final String SIGN_SWITCH = "/api/index/switch";//开关
        public static final String MY_GUILD_INFO = "/api/guild/info";//我的公会信息
        public static final String APPLY_JOIN_GUILD = "/api/guild/join";//申请加入公会
        public static final String SEARCH_GUILD_BY_ID = "/api/guild/search";//搜索公会
        public static final String QUIT_GUILD = "/api/guild/quit";//退出公会
        public static final String SET_SECOND_PASSWORD = "/Api/UserCenterApi/setSecondPassword";//设置二级密码
        public static final String MALL_DOWN_PRODUCT = "/api/mall/downProduct";//设置二级密码
        public static final String ROOM_GET_LIST = "/api/room/getList";//获取房间管理列表(0)和黑名单列表(1)
        public static final String ADD_NAME_AUTH = "/api/javaAuth/addAuth";//实名认证
        public static final String GET_NAME_AUTH_STATUS = "/api/javaAuth/getAppStatus";//实名认证结果
        public static final String JAVA_JOIN_UNION = "/api/javaUnion/apply";//加入公会
        public static final String JAVA_QUERY_UNION = "/api/javaUnion/getUnionByUnionNum";//查询公会
        public static final String JAVA_UNION_INFO = "/api/JavaUnion/getUnionInfoByUserId";//公会信息
        public static final String JAVA_UNION_APPLY_STATE = "/api/javaUnion/getApplyStateByUserId";//公会状态
        public static final String USER_PHOTO = "/api/user/photo";//用户照片
        public static final String ADD_USER_PHOTO = "/api/user/addPhoto";//添加照片
        public static final String DELETE_USER_PHOTO = "/api/user/deletePhoto";//删除照片
        public static final String LOGOUT_REASON = "/api/user/cancelAccount";//用户注销
        public static final String LOGOUT_STATUS = "/api/user/verifyCancel";//用户注销状态
        public static final String TRANSFER_USER = "/api/user/getTransferUser";
        public static final String USER_TRANSFER = "/api/user/transfer";
        public static final String EXCHANGEROOMEARNINGS = "/api/user/exchangeRoomEarnings";
        public static final String USER_PROFIT = "/api/user/profit";
        public static final String ROOM_PROFIT = "/api/user/profit_room";
        public static final String APPLYROOMPROFIT = "/api/user/applyRoomProfit";
        public static final String ADDALIPAY = "/api/user/bindAlipay";

        //        JAVA接口
        public static final String GET_SKILL_KINDS = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/getSkillKinds";//查询可申请的技能

        public static final String IS_ALLOW_WITH_SKILL_ID = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/getIsAllowWithSkill";//查询用户是否可申请某个技能资质

        public static final String ADD_QUALIFICATION_APPLY = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/addUserApply";//新增资质申请

        public static final String UPDATE_QUALIFICATION_APPLY = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/updateUserApply";//修改资质申请

        public static final String GET_QUALIFICATION_APPLY = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/getUserApplyBySkill";//获取资质申请资料

        public static final String GET_APPLY_RANDOM_WORDS = BuildConfig.JAVA_BASE_URL + "/api/assign/skillVoice/randomExample";//随机获取一条文字提示

        public static final String GET_APPLY_RULE_BY_SKILL_ID = BuildConfig.JAVA_BASE_URL + "/api/assign/wordsRemark/getWordsBySkillId";//查询技能资质要求

        public static final String SKILL_FORBID_FOR_USER_UNAUTH = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/updateForbidSomeoneForUser";//接单屏蔽未实名人 0关1开

        public static final String SKILL_FAST_ANSWER = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/updateFastAnswerForUser";//接单闪电应约fastAnswer 0关 1开

        public static final String UPDATE_SKILL_PRICE_OR_SWITCH = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/updatePriceOrSwitch";//修改陪玩价格   或接单开关

        public static final String GET_USER_SKILLS = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/getAppliesForUser";//获取已通过的资质列表

        public static final String GET_ORDER_SWITCH = BuildConfig.JAVA_BASE_URL + "/api/assign/userOrderSwitch/getSwitch";//查询用户接单开关

        public static final String GET_SKILL_PRICE_LIST = BuildConfig.JAVA_BASE_URL + "/api/assign/skillPriceConfig/getPriceBySkillId";//在技能价格修改时价格 显示

        public static final String UPDATE_ORDER_SWITCH = BuildConfig.JAVA_BASE_URL + "/api/assign/userOrderSwitch/updateOrderSwitch";//接单闪电应约fastAnswer 0关 1开

        public static final String GET_SKILL_LIST_BY_USER_ID = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/getUserSkill";//获取用户技能列表

        public static final String GET_ORDER_SKILL_LIST = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/getUserSkillList";//订单技能选择

        public static final String GET_SKILL_INFO = BuildConfig.JAVA_BASE_URL + "/api/assign/userApply/getSkillInfo";//获取技能信息

        public static final String ADD_ORDER = BuildConfig.JAVA_BASE_URL + "/api/assign/order/addOrder";//创建订单

        public static final String ORDER_PAY = BuildConfig.JAVA_BASE_URL + "/api/assign/order/payment";//订单支付

        public static final String VERIFY_ORDER_TIME = BuildConfig.JAVA_BASE_URL + "/api/assign/order/verifyTime";//校验订单时间

        public static final String QUERY_MY_SEND_ORDER = BuildConfig.JAVA_BASE_URL + "/api/assign/order/getMyPlaceAnOrder";//查询我下的单

        public static final String QUERY_MY_RECV_ORDER = BuildConfig.JAVA_BASE_URL + "/api/assign/order/getMyOrders";//查询我接的单

        public static final String GET_LAST_ORDER_MSG = BuildConfig.JAVA_BASE_URL + "/api/assign/order/getUserOrder";//聊天界面查询是否有订单消息
        public static final String GET_ORDER_MSG = BuildConfig.JAVA_BASE_URL + "/api/assign/order/orderMessage";//查询订单消息
        public static final String BOSS_ACCEPT_SERVICE = BuildConfig.JAVA_BASE_URL + "/api/assign/order/bossAcceptService";//老板同意立即服务或拒绝立即服务
        public static final String ACCOMPANY_ACCEPT = BuildConfig.JAVA_BASE_URL + "/api/assign/order/accompanyAccept";//主播接单
        public static final String BOSS_CONFIRM_ORDER = BuildConfig.JAVA_BASE_URL + "/api/assign/order/boosConfirmOrder";//老板点击完成订单
        public static final String BOSS_REFUND_ORDER = BuildConfig.JAVA_BASE_URL + "/api/assign/order/boosRefundOrder";//老板申请退款
        public static final String ACCOMPANY_AGREE_REFUND_ORDER = BuildConfig.JAVA_BASE_URL + "/api/assign/order/agreeRefund";//主播同意退款
        public static final String ACCOMPANY_DISAGREE_REFUND_ORDER = BuildConfig.JAVA_BASE_URL + "/api/assign/order/disagreeRefund";//主播拒绝退款
        public static final String BOSS_AGREE_REFUSE_REFUND_ORDER = BuildConfig.JAVA_BASE_URL + "/api/assign/order/agreeRefuseRefund";//老板同意主播拒绝退款
        public static final String BOSS_APPEALING = BuildConfig.JAVA_BASE_URL + "/api/assign/order/appealing";//老板发起申诉
        public static final String EVALUATION_ACCOMPANY = BuildConfig.JAVA_BASE_URL + "/api/assign/orderEvaluation/evaluateAccompany";//评价陪陪
        public static final String EVALUATION_BOSS = BuildConfig.JAVA_BASE_URL + "/api/assign/orderEvaluation/evaluateBoss";//评价陪陪
        public static final String ACCOMPANY_SERVICE = BuildConfig.JAVA_BASE_URL + "/api/assign/order/accompanyService";//主播接单后立即服务
        public static final String GET_ORDER_DETAIL_BY_ID = BuildConfig.JAVA_BASE_URL + "/api/assign/order/getOrderDetailById";//查询订单详情
        public static final String MESSAGE_FILTER = BuildConfig.JAVA_BASE_URL + "/api/sensitive/sensitive/messageFilter";//验证聊天消息
        public static final String GET_ORDER_EVALUATE_DETAIL = BuildConfig.JAVA_BASE_URL + "/api/assign/order/getOrderEvaluateDetail";//查询订单评价详情（新接口）
        public static final String GET_GAME_LOG = "/api/LuckyBag/recordList";//中奖纪录
    }


    public final class Share {
        public static final int SHARE_WECHAT = 1;
        public static final int SHARE_WECHAT_CIRCLE = 2;
        public static final int SHARE_QQ = 3;
        public static final int SHARE_QQ_ZONE = 4;
    }


    public final class OpenInstall {
        public static final String QQREGISTER = "qqRegister";
        public static final String WXREGISTER = "wxRegister";
        public static final String PHONEREGISTER = "phoneRegister";
        public static final String PHONELOGIN = "phoneLogin";
        public static final String QQLOGIN = "qqLogin";
        public static final String WXLOGIN = "wxLogin";
        public static final String LOGIN = "login";
        public static final String RECHARGEPAGE = "rechargePage";
        public static final String WXRECHARGE = "wxRecharge";
        public static final String ALIRECHARGE = "aliRecharge";
        public static final String TOTALRECHARGE = "totalRecharge";
        public static final String WXRECHARGEFAIL = "wxRechargeFail";
        public static final String ALIRECHARGEFAIL = "aliRechargeFail";
        public static final String RECHARGEFAIL = "RechargeFail";
        public static final String ALIRECHARGETOTAL = "aliRechargeTotal";
        public static final String WXRECHARGETOTAL = "wxRechargeTotal";
        public static final String RECHARGETOTAL = "rechargeTotal";
    }


    public final class Channel {
        public static final String CHANNELCODE = "CHANNELCODE";
        public static final String USERON = "USERON";
        public static final String ROOMID = "ROOMID";
        public static final String ISFIRST = "ISFIRST";
        public static final String VOLUME = "VOLUME";
    }

    public final class Account {
        public static final String BUSINESSID = "ff85e040bb8046e2912117d5fde67058";
    }
}
