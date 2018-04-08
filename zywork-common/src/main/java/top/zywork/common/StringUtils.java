package top.zywork.common;

/**
 * 自定义的字符串工具类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class StringUtils {

    /**
     * 把指定符号分割的数字字符串转成长整型数字数组
     * @param str 指定符号分割的长整型数字字符串
     * @return 长整型数字数组
     */
    public static Long[] strToLongArray(String str, String separator) {
        String[] strArray = str.split(separator);
        Long[] longArray = new Long[strArray.length];
        for (int i = 0, len = strArray.length; i < len; i++) {
            longArray[i] = Long.valueOf(strArray[i]);
        }
        return longArray;
    }

    /**
     * 把指定符号分割的数字字符串转成整型数字数组
     * @param str 指定符号分割的整型数字字符串
     * @return 整型数字数组
     */
    public static Integer[] strToIntegerArray(String str, String separator) {
        String[] strArray = str.split(separator);
        Integer[] integerArray = new Integer[strArray.length];
        for (int i = 0, len = strArray.length; i < len; i++) {
            integerArray[i] = Integer.valueOf(strArray[i]);
        }
        return integerArray;
    }

    /**
     * 判断一个字符串是否出现在指定的字符串数组中
     * @param strArray 字符串数组
     * @param str 字符串
     * @return 如果字符串出现在指定的字符串数组中，返回true，否则返回false
     */
    public static boolean isInArray(String[] strArray, String str) {
        for (String s : strArray) {
            if (s.equals(str)) {
                return false;
            }
        }
        return true;
    }

}
