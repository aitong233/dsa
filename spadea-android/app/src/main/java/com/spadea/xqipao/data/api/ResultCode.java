package com.spadea.xqipao.data.api;

/**
 * 返回状态   每个服务定义一段
 * <p>
 * 1000-1999：  用户服务
 * 2000-2999：  房间服务
 * 3000-3999：  收益服务
 * 4000-4999：  商城服务
 * 5000-5999：
 * 6000-6999：
 * 7000-7999：
 * 8000-8999： 游戏服务
 * 9000-9999：
 * 10001-19999： 授权
 *
 * @author xf
 */
public enum ResultCode {

    /**
     * 通用的返回状态
     */


    DATA_SUCCESS(0, "数据返回成功"),
    OPT_SUCCESS(0, "操作成功"),
    DATA_ERROR(-1, "服务器错误"),
    UNAUTHORIZED(403, "未认证或Token失效"),
    DATA_404(404, "页面不存在"),
    ERROR_400(400, "参数校验失败"),
    ERROR_401(401, "类型转换异常"),
    PASSWORD_ERROR(401, "密码错误，请重新输入"),

    ERROR_405(405, "限流异常  被限流规则阻挡"),
    ERROR_406(406, " 降级异常 被降级规则阻挡"),
    ERROR_407(407, "热点参数异常 被热点参数规则阻挡"),
    ERROR_408(408, "系统规则异常  被系统规则阻挡"),
    ERROR_409(409, "认证异常 被授权规则阻挡"),
    ERROR_410(410, "未知异常"),

    /**
     * 500级别
     */
    ERROR_500(500, "系统错误"),
    ERROR_501(501, "服务器错误"),
    ERROR_550(550, "DB数据刪除异常"),
    ERROR_551(551, "数据转换结果为空"),
    ERROR_552(552, "数据结果集为空"),
    ERROR_553(553, "数据转换list结果为空"),

    /**
     * 响应码1000-2000，用户服务
     */
    USER_NO_EXIST(1001, "用户不存在"),
    CODE_PASSWORD_ERROR(1002, "用户名或密码错误"),
    USER_SEAL_ERROR(1003, "用户被封禁"),
    IP_SEAL_ERROR(1004, "ip被封禁"),
    DEVICE_SEAL_ERROR(1005, "设备被封禁"),
    MOBILE_REG_ERROR(1006, "手机号码格式不正确"),
    MOBILE_USED_ERROR(1007, "您输入的电话号码已被使用"),
    PASSWORD_REG_ERROR(1008, "密码格式不正确"),
    REGISTER_ERROR(1009, "注册失败，请联系管理员"),
    CREATE_EASE_ERROR(1010, "创建环信账号失败"),
    UPDATE_DATE_ERROR(1011, "修改信息失败"),
    ID_CARD_REG_ERROR(1006, "身份证号码格式不正确"),
    BANK_NUM_REG_ERROR(1006, "银行卡号格式不正确"),
    /**
     * 响应码2000-3000,房间服务
     */
    ROOM_PASSWORD_ERROR(2001, "房间密码错误"),
    ROOM_ID_ERROR(2002, "房间id不能为空"),
    ROOM_NAME_ERROR(2003, "房间名称不能为空"),
    ROOM_PIT_TYPE_ERROR(2004, "房间麦序不能为空"),
    ROOM_WELCOME_ERROR(2005, "房间欢迎语不能为空"),
    ROOM_PERMISSION_ERROR(2006, "不能进入房间"),
    ROOM_NO_EXIST(2007, "房间不存在"),
    ROOM_PASSWORD_REG_ERROR(2008, "房间密码格式错误"),
    ROOM_PIT_EMPTY(2009, "房间没有空麦位"),
    ROOM_PIT_ID_EMPTY(2010, "房间麦位id不能为空"),
    ROOM_NO_SUCCESS_TEAM(2011, "没有成功牵手"),
    ROOM_FACE_NO_EXIST(2012, "房间表情不存在"),
    ROOM_BANNED(2013, "该用户被禁言"),
    ROOM_MANAGER_EXIST(2014, "房间管理员已存在"),
    ROOM_LABEL_NO_EXIST(2015, "房间标签不存在"),
    HOT_ROOM_NO_EXIST(2016, "热门房间不存在"),
    ROOM_BLACKLIST_EXIST(2017, "已在黑名单中"),
    CAP_LEVEL_NO_EXIST(2018, "帽子等级不存在"),
    ROOM_SEAL_NO_EXIST(2019, "房间封禁不存在"),
    ROOM_KICK_OUT(2020, "你被踢出过该房间"),
    ROOM_NO_EMPET(2021, "房间编号不能为空"),
    ROOM_LABEL_EXIST(2022, "房间标签已存在"),
    ROOM_USER_NOBILITY_NO_ALLOW(2023, "用户的爵位不允许被踢"),
    ROOM_SEAL(2024, "此房间已被封禁，请联系官方客服"),
    ROOM_PIT_NO_EXIST(2025, "麦位不存在"),
    NO_RIGHT(2026, "权限不足"),
    NO_CHOOSE_PIT(2027, "没有选择麦位"),
    USER_PIT_MATCH_ERROE(2028, "用户和麦位不匹配"),
    WAIT_LIST_EXIST_USER(2029, "已在排麦队列中"),

    /**
     * 响应码3000-4000 用户流水服务
     */
    GOLD_LACK_ERROR(3001, "用户金币不足"),

    GIFT_SEND_ERROR(3002, "送礼物发生错误"),

    GIFT_RECEIVE_ERROR(3003, "收礼物发生错误"),

    GOLD_ADD_ERROR(3004, "金币添加错误"),

    GOLD_SUB_ERROR(3005, "金币扣除错误"),

    TYPE_ALREADY_EXISTS(3006, "类型已存在"),

    AMOUNT_WITHDRAWAL_ERROR(3007, "金额有误"),

    AMOUNT_INSUFFICIENT(3008, "用户余额不足"),

    PROFIT_ASSETS_FREEZE_ERROR(3009, "收益被冻结"),


    /**
     * 响应码 4000-5000商城服务
     */
    LEVEL_OVER_ERROR(4001, "用户当前爵位等级高于购买"),

    NOBILITY_BUY_ERROR(4002, "购买爵位错误"),

    DECORATE_BUY_ERROR(4003, "购买装饰错误"),

    /**
     * 响应码 5000-6000派单服务
     */
    ORDER_FLOW_ERROR(5001, "操作订单流程异常"),
    ORDER_NO_BALANCE(5006, "订单支付余额不足"),

    /**
     * 响应码 6000 - 7000 公会服务
     */
    REJECT_REMARK_ERROR(6001, "驳回理由不能为空"),
    QUIT_UNION_ERROR(6002, "退出指定公会时已不在指定公会"),
    JOIN_UNION_ERROR(6003, "加入指定公会时已在指定公会"),
    PASS_UNION_ERROR(6004, "通过审核发现操作数据时未匹配信息"),
    UNION_ID_NO_EMPTY(6605, "公会id不能为空"),
    UNION_NO_EXIST(6006, "公会不存在"),
    UINON_APPLY_EXIST(6007, "用户已提交申请"),
    UNION_PASSWORD_ERROR(6008, "公会编号或密码错误"),
    UNION_NAME_EXIST(6009, "公会名称重复"),
    UNION_TYPE_REPEAT(6010, "只能创建一个同类型的工会"),
    LABEL_NO_EXIST(6011, "分组不存在"),
    UNION_LABEL_NAME_EXIST(6012, "公会分组名称已存在"),
    UNION_USER_NO_EXIST(6013, "公会成员不存在"),
    UNION_USER_NOT_APPLY(6014, "申请不存在"),
    UNION_DISABLE(6017, "公会被封禁"),
    UNION_DISSOLUTION(6019, "公会已解散"),


    /**
     * 响应码 8000 - 9000 小游戏
     */

    /**
     * 钓鱼
     */
    GAME_INSUFFICIENT_USER_COINS(8001, "用户余额不足"),

    GAME_INSUFFICIENT_PRIZE_POOL_GIFTS(8002, "奖池礼物不足"),

    GAME_HAVE_NOT(8003, "本期礼物已抽完"),

    GAME_TOP_SELECT_USER_DATA_ERROR(8004, "查询用户信息为空 查询异常"),

    GAME_LUCKDRAW_ERROR(8005, "钓鱼错误"),

    GAME_WINNING_LOG_NULL(8006, "暂无记录"),

    /**
     * 短信服务 9001-10000
     */
    SMS_REGISTER_CODE_NO_EMPTY(9001, "注册码不能为空"),

    SMS_REGISTER_CODE_TIMEOUT(9002, "注册码过期"),

    SMS_REGISTER_CODE_ERROR(9003, "注册码错误"),

    /**
     * 10001-19999： 授权
     */
    AUTH_USER_PASSED(10001, "您已认证通过无需任何操作！"),
    AUTH_VERIFIED_NO_OPERATION(10002, "用户已认证通过无需任何操作"),
    AUTH_ADD_ERROR(10003, "添加认证信息时异常"),
    AUTH_UPDATE_ERROR(10004, "修改认证信息时异常"),
    AUTH_DELETE_ERROR(10005, "删除认证信息时异常"),
    YOU_HAVE_NOT_SUBMITTED_CERTIFICATION_INFORMATION(10006, "您还未提交认证信息"),
    AUTH_ID_CODE_ERROR(10007, "身份证号验证错误"),

    ;

    public int code;

    public String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }
}

