package com.spadea.xqipao.utils;

public class RandomUtil {


    public static int random(int min,int max){
        int i = (int)(min+Math.random()*(max-min+1));
        return i;
    }



}
