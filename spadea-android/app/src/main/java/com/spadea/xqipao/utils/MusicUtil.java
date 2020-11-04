package com.spadea.xqipao.utils;

import com.qpyy.libcommon.db.table.MusicTable;

import java.util.List;

public class MusicUtil {


    public static int getIndex(MusicTable musicTable, List<MusicTable> list) {
        if (musicTable == null) {
            return -1;
        }
        if (list == null || list.size() == 0) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (musicTable.getSongid() == list.get(i).getSongid()) {
                return i;
            }
        }
        return -1;
    }


}
