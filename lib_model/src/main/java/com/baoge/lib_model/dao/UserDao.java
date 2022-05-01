package com.baoge.lib_model.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import androidx.lifecycle.LiveData;

import com.baoge.lib_model.model.User;

import java.util.List;

//标记我这个接口是操作user这张表的基类
@Dao
public interface UserDao {
    //1.）如果是插入数据,只需要标记上Insert注解，并指明插入数据时如果已存在一条主键一样的数据，执行什么策略
    //REPLACE: 直接替换老数据
    //ABORT:终止操作，并回滚事务，也就是老数据不影响
    //IGNORE:忽略冲突，但是会插入失败
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User... users);

    //2.)常规查询操作，此时还是需要你写sql语句的
    @Query("SELECT * FROM tb_user WHERE u_id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    //3.) 高级查询操作，可以通过livedata 以观察者的形式获取数据库数据，可以避免不必要的npe
    //更重要的是 他可以监听数据库表中的数据的比变化。一旦发生了 insert update delete。
    //room会自动读取表中最新的数据，发送给UI层 刷新页面
    //这一点是我们着重要关注的，看它背后有什么骚操作。
    @Query("SELECT * FROM tb_user")
    LiveData<List<User>> getAll();

    //4.）删除操作非常简单 ，也可以执行sql的删除数据
    @Delete
    void delete(User user);


    @Update
    void update(User user);
}
