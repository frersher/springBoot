package com.shine.util;
 
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/**
 * @Description: 数字相关工具类
 *
 * @Date: 2021/10/26
 * @Author: jiangXueZhi
 */
public class NumberUtils {
 
    /**
     * 生成随机数字length位数
     *
     * @param length 生成随机数的长度
     * @return 随机数字length位数
     */
    public static String getRandomNickname(int length) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val.append(random.nextInt(10));
        }
        return val.toString();
    }
 
    /**
     * 字符串去重
     *
     * @param str 字符串
     * @return 去重后的字符串
     */
    public static String duplicateRemoval(String str) {
        StringBuilder sb = new StringBuilder(str);
        String rs = sb.reverse().toString().replaceAll("(.)(?=.*\\1)", "");
        StringBuilder out = new StringBuilder(rs);
        return out.reverse().toString();
    }
 
    /**
     * 字符串中的所有数字映射为对应的字母
     *
     * @param str 字符串
     * @return 映射后的字符串
     */
    public static String strMapping(String str) {
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String trim = m.replaceAll("").trim();
        String[] digital = duplicateRemoval(trim).split(""); // 字符串中去重后的所有数字集合
        for (String s : digital) {
            switch (s) {
                case "0":
                    str = str.replace("0", "Q");
                    break;
                case "1":
                    str = str.replace("1", "R");
                    break;
                case "2":
                    str = str.replace("2", "S");
                    break;
                case "3":
                    str = str.replace("3", "T");
                    break;
                case "4":
                    str = str.replace("4", "U");
                    break;
                case "5":
                    str = str.replace("5", "V");
                    break;
                case "6":
                    str = str.replace("6", "W");
                    break;
                case "7":
                    str = str.replace("7", "X");
                    break;
                case "8":
                    str = str.replace("8", "Y");
                    break;
                case "9":
                    str = str.replace("9", "Z");
                    break;
                default:
            }
        }
        return str;
    }
}