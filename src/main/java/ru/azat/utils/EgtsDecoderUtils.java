package ru.azat.utils;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EgtsDecoderUtils {
    public static String[] typesOfByte = new String[]{"PRV", "SKID", "PRF", "HL", "HE", "FDL(1)", "FDL(2)", "PID(1)", "PID(2)", "PT", "PRA(1)", "PRA(2)", "RCA(1)", "RCA(2)", "TTL", "HCS"};


    public static String getCurrentByte(ByteBuf byteBuf){
        return EgtsDecoderUtils.byteListToString(new byte[]{byteBuf.getByte(byteBuf.readerIndex())});
    }

    public static void printCurrentByte(ByteBuf byteBuf){
        System.out.println(getCurrentByte(byteBuf));
    }

    public static String byteListToString(byte[] bytes) {
        List<String> result = new ArrayList<>();
        for (byte b : bytes) {
            result.add(byteToString(b));
        }
        return String.join(" ", result);
    }

    public static String byteToString(byte b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            result.append((b & 1) == 1 ? "1" : "0");
            b >>= 1;
        }
        return result.reverse().toString();
    }

    public static boolean[] byteToBooleanList(byte b) {
        return byteToBooleanList((short) b);
    }

    public static boolean[] byteToBooleanList(short b) {
        boolean[] list = new boolean[8];
        for (int i = 0; i < 8; i++) {
            list[i] = ((b & 1) == 1);
            b >>= 1;
        }
        return list;
    }

    public static void printDebug(ByteBuf buf) {
        byte[] bytes = new byte[buf.readableBytes()];
        int readerIndex = buf.readerIndex();
        buf.getBytes(readerIndex, bytes);
        EgtsDecoderUtils.printDebug(bytes);
    }

    public static void printDebug(byte[] bytes){
        System.out.println(byteListToString(bytes));
        for(int i = 0; i < bytes.length; i ++){
            System.out.print(EgtsDecoderUtils.textToCenter(i + "", 8) + " ");
        }
        System.out.println();
    }

    public static void printTypeOfByte(){
        Arrays.stream(typesOfByte).forEach(type -> System.out.print(textToCenter(type, 8) + " "));
    }

    public static String textToCenter(String string, int length) {
        StringBuilder result = new StringBuilder();
        String subString = string.substring(0, Math.min(string.length(), length));

        int start = (length - subString.length()) / 2;

        int end = subString.length() + start;

        int j = 0;

        for (int i = 0; i < length; i++) {
            if (i < start || i >= end) {
                result.append(' ');
            } else {
                result.append(subString.toCharArray()[j++]);
            }
        }

        return result.toString();
    }


}
