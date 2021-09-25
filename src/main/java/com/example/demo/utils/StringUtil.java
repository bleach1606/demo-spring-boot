package com.example.demo.utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private StringUtil() {
    }

    public static String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        if (e == null) return  "";
        for (StackTraceElement element : e.getStackTrace()) {
            if (Objects.isNull(element)) {
                continue;
            }
            sb.append(element.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public static boolean isContainAnySpace(String input) {
        Pattern pattern = Pattern.compile(".*\\s.*");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
