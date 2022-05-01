package com.baoge.jetpack;



import android.os.Bundle;

import com.baoge.jetpack.databinding.ActivityMainBinding;
import com.baoge.lib_common.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}