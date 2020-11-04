package com.spadea.xqipao.ui.order.status;

/**
 * 逻辑删除。
 * <p>
 * Description：
 * </p>
 *
 * @author mengxiangjun
 * @version v1.0.0
 * @date 2020/5/18 20:38
 */
public enum OrderStatusEnum implements IEnum<Integer> {

    /**
     * 待支付
     */
    BE_PAYMENT(0, "待支付"),

    /**
     * 待主播确认
     */
    BE_CONFIRM(1, "待主播确认"),

    /**
     * 主播确认接单
     */
    BE_SERVE(2, "主播确认接单"),

    /**
     * 主播拒绝接单
     */
    REJECTED(3, "主播拒绝接单"),

    /**
     * 主播是否立即服务
     */
    FIRST_SERVE(4, "主播是否立即服务"),

    /**
     * 老板接受立即服务
     */
    PROCESS(5, "老板接受立即服务"),

    /**
     * 老板拒绝立即服务
     */
    BE_PROCESS(6, "老板拒绝立即服务"),

    /**
     * 老板点击已完成订单
     */
    FINISHED(7, "老板点击已完成订单"),

    /**
     * 老板申请退款
     */
    REFUNDING(8, "老板申请退款"),

    /**
     * 主播同意退款
     */
    REFUND_AGREE(9, "主播同意退款"),

    /**
     * 主播拒绝退款
     */
    REFUND_REJECTED(10, "主播拒绝退款"),

    /**
     * 老板同意主播拒绝退款
     */
    REFUND_REJECTED_AGREE(11, "老板同意主播拒绝退款"),

    /**
     * 老板申诉
     */
    APPEALING(12, "老板申诉"),

    /**
     * 老板申诉成功 由管理台人员操作
     */
    APPEAL_SUCCESS(13, "老板申诉成功"),

    /**
     * 老板申诉失败 由管理台人员操作
     */
    APPEAL_FAIL(14, "老板申诉失败"),

    /**
     * 订单支付超时
     */
    PAYMENT_TIMEOUT(15, "老板未及时确认完成订单系统自动结单之后默认好评"),

    /**
     * 等待接单超时
     */
    CONFIRM_TIMEOUT(16, "等待接单超时"),

    /**
     * 老板未及时确认订单
     */
    FINISH_TIMEOUT(17, "老板未及时确认订单"),

    /**
     * 陪陪未及时操作老板申请退款
     */
    REFUND_TIMEOUT(18, "陪陪未及时操作老板申请退款"),

    /**
     * 陪陪处理老板申请退款超时
     */
    REFUND_REJECTED_TIMEOUT(19, "陪陪处理老板申请退款超时"),

    /**
     * 按照预约时间开始服务
     */
    START_SERVE_WITH_APPOINTMENT(20, "按照预约时间开始服务"),

    /**
     * 按照预约时间开始服务
     */
    START_SERVE_AUTO(21, "按照预约时间开始服务"),
    ;

    /**
     * 状态值
     */
    private Integer value;

    /**
     * 说明
     */
    private String desc;

    /**
     * 构造方法
     *
     * @param value 状态值
     * @param desc  说明
     */
    OrderStatusEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * get方法
     */
    @Override
    public Integer getValue() {
        return this.value;
    }

    /**
     * get方法
     */
    @Override
    public String getDesc() {
        return this.desc;
    }

    public static OrderStatusEnum getEnumByValue(Integer value) {
        OrderStatusEnum[] enums = values();
        for (OrderStatusEnum e : enums) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }

    public static boolean canFinishOrder(Integer value) {
        return PROCESS.value.equals(value) || START_SERVE_AUTO.getValue().equals(value);
    }

    public static boolean canEvaluateOrder(Integer value) {
        return FINISHED.value.equals(value);
    }

}

