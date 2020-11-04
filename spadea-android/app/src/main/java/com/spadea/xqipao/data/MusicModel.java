package com.spadea.xqipao.data;

public class MusicModel {


    private String title;
    private String author;
    private String url;
    private long size = 0L;
    private String album;
    private int songid;

    public MusicModel() {

    }

    public MusicModel(String title, String author, String url, long size, String album, int songid) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.size = size;
        this.album = album;
        this.songid = songid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }
}

