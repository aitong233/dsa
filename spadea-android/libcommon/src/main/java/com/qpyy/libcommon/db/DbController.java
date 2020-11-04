package com.qpyy.libcommon.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.qpyy.libcommon.db.table.MusicTable;

import java.util.List;

public class DbController {
    /**
     * Helper
     */
    private DaoMaster.DevOpenHelper mHelper;//获取Helper对象
    /**
     * 数据库
     */
    private SQLiteDatabase db;
    /**
     * DaoMaster
     */
    private DaoMaster mDaoMaster;
    /**
     * DaoSession
     */
    private DaoSession mDaoSession;
    /**
     * 上下文
     */
    private Context context;
    /**
     * dao
     */
    private MusicTableDao musicTableDao;

    private static DbController mDbController;

    /**
     * 获取单例
     */
    public static DbController getInstance(Context context) {
        if (mDbController == null) {
            synchronized (DbController.class) {
                if (mDbController == null) {
                    mDbController = new DbController(context);
                }
            }
        }
        return mDbController;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public DbController(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context, "yutang.db", null);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        musicTableDao = mDaoSession.getMusicTableDao();
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, "yutang.db", null);
        }
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, "yutang.db", null);
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db;
    }


    /**
     * 会自动判定是插入还是替换
     *
     * @param musicTable
     */
    public void insertOrReplace(MusicTable musicTable) {
        musicTableDao.insertOrReplace(musicTable);
    }

    /**
     * 插入一条记录，表里面要没有与之相同的记录
     *
     * @param musicTable
     */
    public long insert(MusicTable musicTable) {
        return musicTableDao.insert(musicTable);
    }


    public boolean doesItExist(int songid) {
        long count = musicTableDao.queryBuilder().where(MusicTableDao.Properties.Songid.eq(songid)).count();
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }


    public List<MusicTable> queryMusicListAll() {
        List<MusicTable> list = musicTableDao.queryBuilder().orderDesc(MusicTableDao.Properties.Id).list();
        return list;
    }

    public int queryMUsicCount() {
        return (int) musicTableDao.queryBuilder().count();
    }

    public void deleteMusicBy(MusicTable musicTable) {
        musicTableDao.delete(musicTable);
    }


}
