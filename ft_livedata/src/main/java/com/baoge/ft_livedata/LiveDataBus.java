package com.baoge.ft_livedata;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/*
存储了所有的数据持有类 任何组件去拿都要到我这来拿
 */
public class LiveDataBus {
    private static LiveDataBus liveDataBus = new LiveDataBus();
    //容器
    private Map<String, MutableLiveData<Object>> map;

    private LiveDataBus(){
        map = new HashMap<>();
    }

    public static LiveDataBus getInstance(){
        return liveDataBus;
    }

    /**
     * 创建管道与去管道  为一体
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public<T> MutableLiveData<T> with(String key,Class<T> clazz){
        if(!map.containsKey(key)){
            map.put(key,new MutableLiveData<Object>());
        }
        //在map中已经包含这个key和这个key对应的value
        return (MutableLiveData<T>) map.get(key);
    }
}
