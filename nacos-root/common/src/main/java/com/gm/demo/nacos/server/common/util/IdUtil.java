package com.gm.demo.nacos.server.common.util;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class IdUtil {
    private static SecureRandom random = new SecureRandom();
    private static String[] chars = new String[]{"F", "E", "H", "G", "I", "J", "K", "L", "N", "M", "P", "O", "Q", "R", "S", "T", "2", "1", "4", "3", "5", "7", "6", "9", "8", "A", "B", "C", "D", "V", "U", "W", "Y", "X", "Z"};
    public static AtomicInteger atomicInteger;
    static Runnable runnable;
    static Runnable runnable2;

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String uuidReplace() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    public static int randomInteger(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

    public static String getTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        String time = calendar.getTimeInMillis() / 1000L + "";
        return time.substring(time.length() - 9);
    }

    private static synchronized int getId() {
        if (atomicInteger.get() > 1024) {
            atomicInteger.set(0);
        }

        return atomicInteger.getAndIncrement();
    }

    public static String getAptId() {
        Long idx = 1073741823L & Long.valueOf(getTimeStamp());
        String outChars = "";

        int value;
        for(value = 0; value < 6; ++value) {
            int index = (int)(31L & idx);
            outChars = outChars + chars[index];
            idx = idx >> 5;
        }

        value = getId();
        outChars = outChars + chars[31 & value];
        value >>= 5;
        outChars = outChars + chars[31 & value];
        return outChars;
    }

    private static Runnable runnable() {
        return () -> {
            for(int i = 0; i < 1000; ++i) {
                getAptId();

                try {
                    Thread.sleep(100L);
                } catch (InterruptedException var2) {
                    var2.printStackTrace();
                }
            }

        };
    }

    static {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(14);
        atomicInteger = new AtomicInteger(i);
        runnable = runnable();
        runnable2 = runnable();
    }
}
