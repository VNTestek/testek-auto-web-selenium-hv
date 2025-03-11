package com.testek.utils;

import java.util.Random;

public class RandomNumberUtils {

    public static long getRandomPrice(int min, int max) {
        Random random = new Random();
        return min + (random.nextInt(Math.abs(max - min)) * 10000L);
    }

    public static long getRandomInt(int min, int max) {
        Random random = new Random();
        return min + (random.nextInt(Math.abs(max - min)));
    }
}
