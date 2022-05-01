package com.baoge.lib_model.view_model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.baoge.lib_common.util.LogUtil;
import com.baoge.lib_model.dao.UserDao;
import com.baoge.lib_model.database.CacheDataBase;
import com.baoge.lib_model.model.User;

/**
 * 登录窗体对应的viewmodlel
 * 处理数据，保存，从服务器获取等操作都在这里执行
 */
public class LoginActivityViewModel extends ViewModel {
    private MutableLiveData<User> userMutableLiveData;

    /**
     * 这里的泛型一定要写，不然xml中拿不到数据！！！
     * @return
     */
    public MutableLiveData<User> getUser(){
        if(userMutableLiveData==null){
            userMutableLiveData = new MutableLiveData<>();
            //给个默认参数
            userMutableLiveData.setValue(new User("","",0,0));
        }
        return userMutableLiveData;
    }


    public void loginRequest(){
        //请求服务器
        LogUtil.e( userMutableLiveData.getValue().getAccount()+"  "+
                userMutableLiveData.getValue().getPassWord());
        //假设在这里 请求了服务器  得到了结果
        User userBean = new User(userMutableLiveData.getValue().getAccount(),
                userMutableLiveData.getValue().getPassWord(),21,1);
        //保存到数据库
        //1.获取到数据库的操作引用
        CacheDataBase dataBase = CacheDataBase.getDataBase();
        //2.得到你要操作的表所对应的dao类
        UserDao userDao = dataBase.userDao();
        userDao.insertAll(userBean);
        //更新数据源
        userMutableLiveData.setValue(userBean);
    }

}
