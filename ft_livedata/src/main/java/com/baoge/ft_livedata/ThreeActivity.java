package com.baoge.ft_livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;

import com.baoge.ft_livedata.databinding.ActivityThreeBinding;
import com.baoge.ft_livedata.databinding.ActivityTwoBinding;
import com.baoge.lib_common.util.LogUtil;

public class ThreeActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityThreeBinding binding;
    private   LiveDataBus2.BusMutableLiveData mutableLiveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThreeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnChange.setOnClickListener(this);

        mutableLiveData= LiveDataBus2.getInstance().with("code", String.class);
        mutableLiveData.observe(this,true, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                LogUtil.i("activity3 onchage: " + s);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change:
                mutableLiveData.setValue("333333");
                break;
        }
    }
}