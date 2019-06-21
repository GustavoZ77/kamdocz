package com.rxnoticias.utils;

public class ThreadUtils {

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // suppress
        }
    }

    public static void wait(Object obj) {
        try {
            obj.wait();
        } catch (InterruptedException e) {
            // suppress
        }
    }
}
