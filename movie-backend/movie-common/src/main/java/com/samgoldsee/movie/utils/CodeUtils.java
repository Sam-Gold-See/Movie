package com.samgoldsee.movie.utils;

/**
 * 数码相关工具
 */
public class CodeUtils {

    /**
     * 将数码中所有字母大写
     *
     * @param code 原码
     * @return String类目标码
     */
    public static String upperLetters(String code) {
        StringBuilder result = new StringBuilder();
        for (char c : code.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
