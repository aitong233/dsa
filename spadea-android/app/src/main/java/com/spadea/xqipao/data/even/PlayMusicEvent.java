package com.spadea.xqipao.data.even;

import com.qpyy.libcommon.db.table.MusicTable;

public class PlayMusicEvent {


    public MusicTable musicTablep;

    public PlayMusicEvent(MusicTable musicTablep) {
        this.musicTablep = musicTablep;
    }

    public MusicTable getMusicTablep() {
        return musicTablep;
    }

    public void setMusicTablep(MusicTable musicTablep) {
        this.musicTablep = musicTablep;
    }
}
