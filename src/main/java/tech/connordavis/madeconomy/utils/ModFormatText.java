package tech.connordavis.madeconomy.utils;

public class ModFormatText {
    public static String format(String text) {
        return text.replace("&", "\u00A7");
    }
}
