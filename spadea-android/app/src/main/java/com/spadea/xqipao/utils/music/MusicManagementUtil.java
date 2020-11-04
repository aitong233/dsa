package com.spadea.xqipao.utils.music;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;


import com.spadea.xqipao.data.MusicModel;

import java.util.ArrayList;
import java.util.List;

public class MusicManagementUtil {


    //获取手机中的所有音乐
    public static List<MusicModel> getAllMusicFile(Context context) {
        List<MusicModel> list = new ArrayList<>();
        ContentResolver mContentResolver;
        mContentResolver = context.getContentResolver();
        Cursor c = null;
        try {
            c = mContentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                    MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
            MusicModel musicModel = null;
            while (c.moveToNext()) {
                String path = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));// 路径
                int musicId = c.getInt(c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));// 歌曲的id
                String name = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)); // 歌曲名
                String album = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)); // 专辑
                String artist = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)); // 作者
                long size = c.getLong(c.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));// 大小
                int duration = c.getInt(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));// 时长
                musicModel = new MusicModel(name, artist, path, size, album, musicId);
                if (path.contains("mp3")) {
                    list.add(musicModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return list;
    }


}
