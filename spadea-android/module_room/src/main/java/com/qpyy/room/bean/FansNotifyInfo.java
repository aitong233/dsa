package com.qpyy.room.bean;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 王欧
 * 创建时间 2020/8/15 3:27 PM
 * 描述 describe
 */
public class FansNotifyInfo {

    /**
     * price :
     * times_per_day :
     * is_free :
     * left_count :
     */

    private String price;
    private String times_per_day;
    private String is_free; //1免费0需要收费
    private int left_count;//0表示当日不能再发通知了

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimes_per_day() {
        return times_per_day;
    }

    public void setTimes_per_day(String times_per_day) {
        this.times_per_day = times_per_day;
    }

    public String getIs_free() {
        return is_free;
    }

    public void setIs_free(String is_free) {
        this.is_free = is_free;
    }

    public int getLeft_count() {
        return left_count;
    }

    public void setLeft_count(int left_count) {
        this.left_count = left_count;
    }
}
