package org.juc.readwritelock;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuhui
 * @date 2020/5/24 17:58
 */
public class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 读，取
     */
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取OK");
    }

    /**
     * 写，存
     */
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "写入" + value);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() + "写入OK");
    }
}
