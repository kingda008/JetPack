package com.baoge.lib_model.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.baoge.lib_common.Globals;
import com.baoge.lib_model.dao.UserDao;
import com.baoge.lib_model.model.User;

@Database(entities = {User.class},version = 1)
public abstract class CacheDataBase extends RoomDatabase {
    private static final CacheDataBase database;
    static {
        //创建数据库
        database = Room.databaseBuilder(Globals.getsApplication(),CacheDataBase.class,"logindb")
                //允许在主线程中执行查询
        .allowMainThreadQueries()
        .build();
    }

    public static CacheDataBase getDataBase(){
        return database;
    }

    public abstract UserDao userDao();
}
