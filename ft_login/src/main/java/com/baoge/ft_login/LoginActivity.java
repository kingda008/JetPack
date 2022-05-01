package com.baoge.ft_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.baoge.ft_login.databinding.ActivityLoginBinding;
import com.baoge.lib_common.base.BaseActivity;
import com.baoge.lib_model.model.User;
import com.baoge.lib_model.view_model.LoginActivityViewModel;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private ActivityLoginBinding binding ;
    private LoginActivityViewModel loginActivityViewModel;
    private MutableLiveData<User> user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        //生命周期
        binding.setLifecycleOwner(this);

        //创建对应的viewmodel
        loginActivityViewModel = new LoginActivityViewModel();
        //绑定
        binding.setViewModel(loginActivityViewModel);

        user  = binding.getViewModel().getUser();


        //注册的时候，如果数据不为空的话，根据生命周期会回调一次

        user.observe(LoginActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Log.e("MN---------->",user.getAccount()+user.getPassWord());
            }
        });


        binding.btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                //登录的请求
                loginActivityViewModel.loginRequest();
                break;
        }
    }
}