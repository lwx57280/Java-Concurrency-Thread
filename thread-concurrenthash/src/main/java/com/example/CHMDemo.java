package com.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class CHMDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer,Integer> chm=new ConcurrentHashMap<>();
        Stream.of(1,2,3,4,5,2,6,3,8).forEach(v->{
            chm.merge(v, 5, Integer::sum);
        });
        System.out.println(chm);
    }
}
