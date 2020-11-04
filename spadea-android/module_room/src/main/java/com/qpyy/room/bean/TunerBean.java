package com.qpyy.room.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 黄强
 * 创建时间 2020/8/14 09:24
 * 描述 describe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TunerBean {
    private String name;
    private int type;
    private int image;
    private boolean isSelect;
}
