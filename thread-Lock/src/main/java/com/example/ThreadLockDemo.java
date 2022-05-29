package com.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLockDemo {

    // 非线程安全的
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 通过 ThreadLocal 隔离
     */
    private static ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<>();


    public static DateFormat getDataFormat() {
        DateFormat dateFormat = dateFormatThreadLocal.get();// 从当前线程的范围内获得一个DateFormat
        if (null == dateFormat) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 要在当前线程的范围内设置一个SimpleDateFormat对象
            dateFormatThreadLocal.set(dateFormat);
        }
        return dateFormat;
    }

    public static Date parse(String strDate) throws ParseException {

        return getDataFormat().parse(strDate);
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {

                try {
                    System.out.println(parse("2020-06-30 20:12:10"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }


}
