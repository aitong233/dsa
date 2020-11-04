package com.qpyy.libcommon.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class MusicTable {

    @Id(autoincrement = true)//设置自增长
    private Long id;
    private int songid;
    private String title;
    private String author;
    private String url;
    @Generated(hash = 1011179913)
    public MusicTable(Long id, int songid, String title, String author,
            String url) {
        this.id = id;
        this.songid = songid;
        this.title = title;
        this.author = author;
        this.url = url;
    }
    @Generated(hash = 1642429583)
    public MusicTable() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getSongid() {
        return this.songid;
    }
    public void setSongid(int songid) {
        this.songid = songid;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }


}
