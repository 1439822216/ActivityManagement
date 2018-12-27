package com.example.administrator.activitymanagement.utils;

import java.util.Random;

public class RandomUtils {
    public static int randomNum(int i){
        Random random = new Random();
        int num = 0;
        num = random.nextInt(i) + 1;
        return num;
    }
}
