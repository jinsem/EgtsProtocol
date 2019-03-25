package ru.azat.utils;

public final class BitUtil {

    private BitUtil() {
    }

    public static boolean check(long number, int index) {
        return (number & (1 << index)) != 0;
    }

    public static int between(int number, int from, int to) {
        return (number >> from) & ((1 << to - from) - 1);
    }

    public static int from(int number, int from) {
        return number >> from;
    }

    public static int to(int number, int to) {
        return between(number, 0, to);
    }

    public static long between(long number, int from, int to) {
        return (number >> from) & ((1L << to - from) - 1L);
    }

    public static long from(long number, int from) {
        return number >> from;
    }

    public static long to(long number, int to) {
        return between(number, 0, to);
    }

}
