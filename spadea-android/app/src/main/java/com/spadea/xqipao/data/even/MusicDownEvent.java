package com.spadea.xqipao.data.even;

import com.spadea.xqipao.data.MusicModel;

public class MusicDownEvent {
    private MusicModel musicModel;
    private String path;

    public MusicDownEvent(MusicModel musicModel, String path) {
        this.musicModel = musicModel;
        this.path = path;
    }

    public MusicModel getMusicModel() {
        return musicModel;
    }

    public void setMusicModel(MusicModel musicModel) {
        this.musicModel = musicModel;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
