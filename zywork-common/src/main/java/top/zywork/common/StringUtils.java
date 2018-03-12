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

}
