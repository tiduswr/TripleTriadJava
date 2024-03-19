package com.tiduswr.game.utils;

public class PrintUtils {
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[32m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void printGreen(String s){
        System.out.print(ANSI_GREEN_BACKGROUND + s + ANSI_RESET);
    }

    public static void printBlue(String s){
        System.out.print(ANSI_BLUE_BACKGROUND + s + ANSI_RESET);
    }

    public static String formatGreen(String s){
        return ANSI_GREEN_BACKGROUND + s + ANSI_RESET;
    }

    public static String formatBlue(String s){
        return ANSI_BLUE_BACKGROUND + s + ANSI_RESET;
    }

}
