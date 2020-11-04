package com.qpyy.libcommon.constant;

import com.qpyy.libcommon.BuildConfig;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.libcommon.constant
 * 创建人 王欧
 * 创建时间 2020/6/29 10:38 AM
 * 描述 describe
 */
public class URLConstants {

    public static final String SHARE = BuildConfig.BASE_URL + "/api/AboutApi/download";

    public static final String CHILDREN_PROTOCOL = BuildConfig.BASE_URL + "/api/article/info/id/20";//黑桃A儿童隐私保护声明

    public static final String QPYY_YSZC = com.qpyy.libcommon.BuildConfig.BASE_URL + "/api/article/info/id/19";//黑桃A隐私政策

    public static final String ARTICLE = BuildConfig.BASE_URL + "/api/articleApi/info/id/";
    public static final String HOME_BANNER = BuildConfig.BASE_URL + "/home/banner/detail?id=";
    public static final String LOGIN = "/Api/PublicApi/login";
    public final static String SEARCH = "/api/index/search";
    public static final String WEEK_STAR = "/api/ranking/star";
    public static final String WEEK_STAR_RICH = "/api/ranking/starRich";
    public static final String WEEK_STAR_CHARM = "/api/ranking/starCharm";
    public static final String WEEK_STAR_ROOM = "/api/ranking/starRoom";
    public static final String LAST_WEEK_STAR = "/api/ranking/starLast";
    public static final String ROOM_CATEGORY = "/api/index/newRoomType";
    public static final String INDEX_BANNERS = "/api/index/banners";
    public static final String ROOM_LIST = "/api/index/roomList";
    public static final String ATTENTION_LIST = "/api/room/attentionList";
    public static final String RECOMMEND_ATTENTION_LIST = "/api/index/recommendAttentionList";
    public static final String GHOST_ATTENTION = "/api/room/ghostAttention";
    public static final String MANAGE_LISTS = "/api/room/manageLists";
    public static final String REMOVE_MANAGE = "/api/room/removeManage";
    public static final String MYFOOT = "/api/room/myFoot";
    public static final String DELFOOT = "/api/room/delFoot";
    public static final String HOT_ROOM_LIST = "/api/index/newHotRoom";
    public static final String RECOMMEND_ROOM_LIST = "/api/index/recommendList";
    public static final String THIRD_PARTY_LOGIN = "/Api/PublicApi/thirdPartyLogin";
    public static final String SEND_CODE = "/Api/PublicApi/smsCode";
    public static final String REMOVE_FAVORITE = "/api/room/removeFavorite";
    public static final String FRIEND_LIST = "/api/user/friendList";
    public static final String FOLLOW_LIST = "/api/user/followList";
    public static final String FANS_LIST = "/api/user/fansList";
    public static final String SEARCH_FRIEND = "/api/user/searchFriend";
    public static final String SEARCH_FANS = "/api/user/searchFans";
    public static final String SEARCH_FOLLOW = "/api/user/searchFollow";
    public static final String REPORT_TYPE = "/api/user/reportType";
    public static final String MY_INFO = "/api/user/myInfo";
    public static final String USER_UPDATE = "/api/user/update";
    public static final String PROFESSION = "/api/index/profession";
    public static final String REGION_LIST = "/api/index/region";
    public static final String USER_HOME_PAGE = "api/user/homepage";
    public static final String SERVICEUSER = "/api/UserCenterApi/serviceUser";
    public static final String CHECKTXT = "/api/index/checkTxt";
    public static final String ADD_USER_ROOM = "/api/room/addRoom";
    public static final String GIFTWALL = "/api/user/giftWall";
    public static final String USER_ROOM = "/api/user/room";
    public static final String PHOTOWALL = "/api/user/photoWall";
    public static final String DELETEPHOTO = "/api/user/deletePhoto";
    public static final String CHECKIMAGE = "/api/index/checkImage";
    public static final String ADDPHOTO = "/api/user/addPhoto";
    public static final String ADD_BLACK_USER = "/Api/UserCenterApi/addBlackUser";//黑名单
    public static final String GET_INFO_BY_EM_CHAT = "/api/user/getInfoByEmchat";//根据环信账号获取用户基本信息
    public static final String CHAT_USER_REPORT = "/api/user/report";//举报用户
    public static final String GIVE_CHAT_GIFT = "/api/user/giveGift";//送礼物
    public static final String SYSTEM_NEWS_LIST = "/Api/UserCenterApi/userNewsList";//系统消息列表
    public static final String NAME_AUTH_RESULT = "/api/user/authResult";//实名认证结果查询
    public static final String NAME_AUTH = "/api/user/auth";//实名认证
    public static final String COMEUSER = "/api/user/visit";
    public static final String UNION = "/api/JavaUnion/getApplyStateByUserId";
    public static final String UPDATEAVATAR = "/api/user/updateAvatar";
    public static final String VERIFY_USER_SEX = "/api/user/verifySex";
    public static final String MY_GUILD_INFO = "/api/Sociaty/getUnionInfoByUserId";//我的公会详情
    public static final String SEARCH_GUILD = "/api/Sociaty/getUnionByUnionNum ";//搜索公会
    public static final String JOIN_GUILD = "/api/Sociaty/apply";//加入公会
    public static final String EXIT_GUILD = "/api/Sociaty/apply";//退出公会

    //公会
    public static final String GUILD_SEARCH = "/api/guild/search"; //搜索公会
    public static final String GUILD_JOIN = "/api/guild/join";//加入公会
    public static final String GUILD_INFO = "/api/guild/info";//我的公会
    public static final String GUILD_QUIT = "/api/guild/quit";

    //直播房间apiUrl
    public static final String ROOM_GET_IN = "/api/room/getIn";//获取房间信息
    public static final String MQTT_HEART_BEAT = "/api/room/heartbeat";//mqtt心跳
    public static final String ROOM_ONLINE = "/api/room/onlineUser";//获取房间在线列表
    public static final String CHARM_LIST = "/api/ranking/charm";//获取魅力榜单
    public static final String WEALTH_LIST = "/api/ranking/rich";//获取财富榜单
    public static final String SEARCHMUSIC = "/api/MusicApi/index";//获取音乐搜索
    public static final String SEARCH_USER = "/api/room/search";//获取房间用户搜索
    public static final String ADD_MANAGER = "/api/room/setManager";//设置管理员
    public static final String DELETE_MANAGER = "/api/room/deleteManager";//删除管理员
    public static final String ADD_FORBID = "/api/room/forbid";//添加黑名单
    public static final String DELETE_FORBID = "/api/room/deleteForbid";//删除黑名单
    public static final String ROOM_GET_LIST = "/api/room/getList";//获取房间管理列表(0)和黑名单列表(1)
    public static final String ROOM_EXTRAINFO = "/api/room/extraInfo";//获取房间其他信息
    public static final String EDIT_ROOM = "/api/room/edit";//编辑房间信息
    public static final String AGREEAPPLY = "/api/room/agreeApply";
    public static final String DELETEAPPLY = "/api/room/deleteApply";
    public static final String AGREEAPPLYALL = "/api/room/agreeApplyAll";
    public static final String USER_FILL = "/api/user/fill";
    public static final String APPLYWHEATUSERS = "/api/room/applyWheatUsers";
    public static final String ROOM_BACKGROUND_LIST = "/api/index/roomBackground";//房间背景列表
    public static final String EDIT_ROOM_BG = "/api/room/editBackground";//编辑房间背景
    public static final String UPDATEPASSWORD = "/api/room/updatePassword";//更新房间秘密啊
    public static final String ADD_FAVORITE = "/api/room/addFavorite";//收藏房间
    public static final String ROOM_QUIT = "/api/room/quit";//退出房间
    public static final String BALANCE = "/api/account/balance";
    public static final String GIVEGIFT = "/api/room/giveGift";//送礼物
    public static final String GIVEBACKGIFT = "/api/room/giveBackGift";//回礼
    public static final String PITLIST = "/api/room/pitList";//对阵列表？
    public static final String GIFT_WALL = "/Api/UserCenterApi/giftWall";//礼物图
    public static final String USER_BACKPACK = "/api/user/backpack";//用户背包
    public static final String APPLY_WHEAT = "/api/room/applyWheat";//上麦
    public static final String DOWN_WHEAT = "/api/room/downWheat";//下麦
    public static final String APPLY_WHEAT_WAIT = "/api/room/applyWheatWait";//申请上麦
    public static final String GET_IN_ROOM_INFO = "/api/room/getInRoomInfo";//房间信息
    public static final String APPLY_WHEAT_FM = "/api/room/applyWheatFm"; //电台上麦
    public static final String FM_OPEN_PROTECTED = "/api/room/openProtect"; //开通守护接口
    public static final String GET_PROTECTED_RANKING_LIST = "/api/room/protectRanking";//守护排行榜接口
    public static final String GET_PROTECTED_LIST = "/api/room/getProtectList";//获取守护信息列表
    public static final String GET_ANCHOR_RANKING_LIST = "/api/room/getAnchorRankingList";//主播排行榜
    public static final String CATEGORIES = "/api/mall/categories";//购物类别
    public static final String PRODUCTS = "/api/mall/products";//物品
    public static final String BUY_SHOP = "/api/mall/buy";//购物
    public static final String ROOMUSERINFO = "/api/room/userInfo";//用户信息
    public static final String FOLLOW = "/api/user/follow";
    public static final String DOWN_USER_WHEAT = "/api/room/downUserWheat";
    public static final String KICKOUT = "/api/room/kickOut";//踢出
    public static final String ROOM_USER_SHUTUP = "/api/room/bannedPit";//禁麦
    public static final String SETROOMBANNED = "/api/UserRoomApi/setRoomBanned";//禁言
    public static final String FACE_LIST = "/api/UserRoomApi/faceList";//默认表情
    public static final String FACE_SPECIAL = "/api/user/faceSpecial";//专属表情
    public static final String SIGN_SWITCH = "/api/index/switch";//开关

    public static final String RECHARGE = "/api/UserCenterApi/userRechargeMoney";//充值
    public static final String RECHARGE_INFO = "api/UserCenterApi/rechargeInfo"; //充值信息
    public static final String ALIPAYMENT = "/Api/Payment/payment";//支付宝支付
    public static final String WXPAYMENT = "/Api/Wxpay/payment";//微信支付

    public static final String PUT_ON_WHEAT = "/api/room/putOnWheat";//抱麦
    public static final String ROOM_GUIDE = "/api/room/guide";//完成房间引导
    public static final String ROOM_FANS_NOTICE_INFO = "/api/room/fansNoticeInfo";//粉丝通知信息
    public static final String ROOM_FANS_NOTICE = "/api/room/fansNotice";//粉丝通知
    public static final String GIVE_BACK_GIFT_ALL = "/api/room/giveBackGiftAll";//一键赠送背包礼物给某人
    public static final String GIFT_NUMBER_SET = "/api/room/giftNumberSet";//房间赠送礼物的数量配置
    public static final String SEND_FACE = "/api/room/sendFace";//发送表情
    public static final String SOUND_EFFECT = "/api/room/soundEffect";//房间音效列表
    public static final String UPDATE_SOUND_EFFECT = "/api/room/updateSoundEffect";//修改房间音效
    public static final String PIT_COUNT_DOWN = "/api/room/pitCountDown";//麦位倒计时
    public static final String LOG_EMCHAT = "/api/log/emchat";
    public static final String ROOM_PITINFO = "/api/room/pitInfo";
    public static final String SETROOMCARDIAC = "/api/room/setRoomCardiac";
    public static final String CLEARROOMCARDIAC = "/api/room/clearRoomCardiac";
    public static final String CLEAR_CARDIAC = "/api/room/clearCardiac";
    public static final String CLOSEPIT = "/Api/UserRoomApi/closePit";
    public static final String USERNEWS = "/Api/UserCenterApi/userNews";
    public static final String ROOM_ROLL = "/api/room/roll";//抽签
    public static final String RANCARDS = "/api/LotteryCard/lottery";
    public static final String RANTOUZI = "/api/Dice/lottery";
    public static final String BALLSTART = "/api/room/ballStart";
    public static final String BALLTHROW = "/api/room/ballThrow";
    public static final String BALLSHOW = "/api/room/ballShow";
    public static final String MIXER = "/api/room/mixer";
    public static final String SETUSERMIXER = "/api/room/setUserMixer";
    public static final String SWITCHVOICE = "/api/room/switchVoice";//开关麦克风时调用
    public static final String SWITCHPUBLICSCREEN = "/Api/room/setChat";//开关公屏
    public static final String SENDCHATMSG = "/Api/user/sendChatMsg";//发送聊天文字
    public static final String SEND_CHAT_PIC = "/Api/user/sendChatImg";//发送聊天图片
    public static final String SEND_CHAT_AUDIO = "/Api/user/sendChatAudio";//发送聊天语音
    public static final String CLOSE_ALL_PIT = "/api/room/closeAllPit";//一件锁麦
    public static final String GET_FISH_INFO = "/api/LuckyBag/allSprite";//小猫钓鱼信息
    public static final String FISHING = "/Api/LuckyBag/lottery";
    public static final String CATHELP = "/Api/game/help";
    public static final String WINJACKPOT = "/api/LuckyBag/prizelConfig";
    public static final String GET_GAME_LOG = "/api/LuckyBag/recordList";//中奖纪录
    public static final String WINRANKING = "/api/LuckyBag/rankList";
    public static final String LUCKY_RANK = "/api/LuckyBag/luckyRank";
    public static final String LUCKY_RANK_1 = "/api/LuckyBag/rankListRecord";   //手气记录

    public static final String NEXT_BOX_CONTENT = "api/index/boxInfo";//盲盒说明
}
