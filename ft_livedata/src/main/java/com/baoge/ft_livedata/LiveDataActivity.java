package com.baoge.ft_livedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.baoge.ft_livedata.databinding.ActivityLiveDataBinding;
import com.baoge.lib_common.base.BaseActivity;
import com.baoge.lib_common.util.LogUtil;


public class LiveDataActivity extends BaseActivity implements View.OnClickListener{

    private ActivityLiveDataBinding binding;
    private MutableLiveData<String> liveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityLiveDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);


        liveData = new MutableLiveData<>();
/*        liveData.setValue("zhang");
        liveData.postValue("lisi");*/
        getLifecycle().addObserver(new CustomLifeCycle());

        liveData.observe(LiveDataActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                LogUtil.i("onchage:"+s);

            }
        });


//        liveData.setValue(liveData.getValue()+"22");


        MutableLiveData<String> liveDataBus = LiveDataBus.getInstance().with("code",String.class);


        liveDataBus.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                LogUtil.i("livedataAcitivity ,onchage:"+s);
            }
        });
        liveDataBus.setValue("1234445");





        MutableLiveData<String> liveDataBus2 = LiveDataBus2.getInstance().with("code",String.class);


        liveDataBus2.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                LogUtil.i("livedata2 Acitivity ,onchage:"+s);
            }
        });
        liveDataBus2.setValue("2222");
    }

    @SuppressLint("RestrictedApi")
    private class CustomLifeCycle implements GenericLifecycleObserver {
        @Override
        public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
            LogUtil.i(source.getLifecycle().getCurrentState()+"  ,  "+event);
        }
    }

    @Override
    protected void init() {
        binding.btnChangvalue.setOnClickListener(this);
        binding.btnActivity2.setOnClickListener(this);
        binding.btnActivity3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_changvalue:
                liveData.setValue(liveData.getValue()+"22");

                break;
            case R.id.btn_activity2:
                startActivity(new Intent(this,TwoActivity.class));

                break;
            case R.id.btn_activity3:
                startActivity(new Intent(this,ThreeActivity.class));

                break;
        }

    }
}