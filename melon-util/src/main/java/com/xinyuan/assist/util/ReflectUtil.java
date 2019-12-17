/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author melonkid
 * @version $Id: ReflectUtil.java, v 0.1 2019年12月05日 13:49 melonkid Exp $
 */
public class ReflectUtil {

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> objectToMap(Object obj)  {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object valObj = null;
            try {
                valObj = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(valObj != null) {
                map.put(fieldName, valObj.toString());
            }
        }
        return map;
    }

}