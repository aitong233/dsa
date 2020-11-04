package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/4/26 11:30 AM
 * 描述 describe
 */
public class RoomInAnimModel<T> {
    public int type; //1爵位，2周星
    public T data;

    public RoomInAnimModel(int type, T data) {
        this.type = type;
        this.data = data;
    }
}
