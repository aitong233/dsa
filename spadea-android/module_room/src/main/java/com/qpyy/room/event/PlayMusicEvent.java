package com.qpyy.room.event;

import com.qpyy.libcommon.db.table.MusicTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.event
 * 创建人 黄强
 * 创建时间 2020/8/13 16:45
 * 描述 describe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayMusicEvent {
    private MusicTable musicTablep;
}
