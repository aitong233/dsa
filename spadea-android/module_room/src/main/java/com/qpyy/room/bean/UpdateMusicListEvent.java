package com.qpyy.room.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 黄强
 * 创建时间 2020/8/21 14:39
 * 描述 describe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMusicListEvent {
    private int currentMusic;//当前播放的音乐
    private boolean isPlay;//播放状态
}
