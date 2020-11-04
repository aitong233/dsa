package com.qpyy.room.bean;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 黄强
 * 创建时间 2020/8/19 16:22
 * 描述 describe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoom {
    private  Map<String, String> map;
    private String roomId;
}
