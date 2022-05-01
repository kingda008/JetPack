package com.baoge.lib_model.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.baoge.lib_model.BR;


@Entity(tableName = "tb_user")
public class User extends BaseObservable {
    private String account;

    private String passWord;

    private Integer age;

    @PrimaryKey
    private Integer u_id;

    public User(String account, String passWord, Integer age, Integer u_id) {
        this.account = account;
        this.passWord = passWord;
        this.age = age;
        this.u_id = u_id;
    }

    @Bindable
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        //通知绑定类去更新试图
        notifyPropertyChanged(BR.account);
    }

    @Bindable
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
        notifyPropertyChanged(BR.passWord);
    }

    @Bindable
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
        notifyPropertyChanged(BR.u_id);
    }
}
