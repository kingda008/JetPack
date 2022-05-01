package com.baoge.ft_livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;

import com.baoge.ft_livedata.databinding.ActivityTwoBinding;
import com.baoge.lib_common.util.LogUtil;

public class TwoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityTwoBinding binding;
    private MutableLiveData<String> mutableLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTwoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnChange.setOnClickListener(this);

        mutableLiveData = LiveDataBus.getInstance().with("code", String.class);
        mutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                LogUtil.i("activity2 onchage: " + s);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change:
                mutableLiveData.setValue("2222333");
                break;
        }
    }
}