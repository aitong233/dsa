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
public enum OrderEndStatusEnum implements IEnum<Integer> {


    /**
     * 待支付 该状态比较特殊 进行特殊处理
     */
    BE_PAYMENT(0, "待支付"),

    /**
     * 流程中
     */
    IN_FLOW(1, "进行中"),

    /**
     * 过期
     */
    EXPIRED(2, "已过期"),

    /**
     * 取消
     */
    CANCEL(3, "已取消"),

    /**
     * 完成
     */
    FINISH(4, "已完成"),

    /**
     * 退款
     */
    REFUND(5, "已退款"),

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
    OrderEndStatusEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public static OrderEndStatusEnum getEnumByValue(Integer value) {
        OrderEndStatusEnum[] enums = values();
        for (OrderEndStatusEnum e : enums) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return BE_PAYMENT;
    }

}

