package com.xinyuan.assist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WordDao {

    @Autowired
    private DBHelper dbHelper;

    private static String WD_NO = "WD_NO";

    public int generateWdNo(int capacity) {
        Object wdNo = dbHelper.getByK(WD_NO);
        List<String> nums ;
        int num = (int) (Math.random() * capacity);
        if (wdNo == null) {
            nums = new ArrayList<>();
            nums.add(num + "");
            dbHelper.saveKV(WD_NO, nums);
            return num;
        }
        nums = (List<String>) wdNo;
        while (nums.contains(num + "")) {
            num = (int) (Math.random() * capacity);
        }
        nums.add(num + "");
        dbHelper.saveKV(WD_NO, nums);
        return num;
    }
}
