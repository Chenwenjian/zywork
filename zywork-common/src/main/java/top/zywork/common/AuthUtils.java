package top.zywork.common;

import top.zywork.enums.HashEncodeEnum;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Auth认证相关工具类，可适用于Open API, APP的使用场景<br/>
 *
 * 创建于2018-01-18
 *
 * @author 王振宇
 * @version 1.0
 */
public class AuthUtils {

    public static final String SIGN_TYPE_MD5 = "MD5";
    public static final String SIGN_TYPE_SHA256 = "SHA-256";

    public static final String PARAM_SIGN = "sign";

    /**
     * 根据用户名，获取token的时间，用户加密后的密码获取用户token
     * @param username 用户名
     * @param timestamp 获取token的时间毫秒值
     * @param hashPassword 加密后的密码
     * @return md5摘要后的用户token
     */
    public static String generateToken(String username, Long timestamp, String hashPassword) {
        return HashUtils.md5(username + timestamp + hashPassword, HashEncodeEnum.BASE64).toUpperCase();
    }

    /**
     * 生成签名，签名算法如下：<br/>
     * 1. 除```sign```之外的所有请求参数全部按照字母顺序排列<br/>
     * 2. 除```sign```之外的所有请求参数的参数名和参数值拼接成一个字符串<br/>
     * 3. 对拼接成的字符串进行```MD5```加密后转成全大写字符串，生成```sign```签名值
     * @param data 请求参数所组成的Map
     * @param signType 签名摘要算法
     * @return 指定算法生成的签名数据
     */
    public static String generateSignature(final Map<String, String> data, String signType) {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String key : keyArray) {
            if (PARAM_SIGN.equals(key)) {
                continue;
            }
            sb.append(key).append(data.get(key).trim());
        }
        if (SIGN_TYPE_MD5.equals(signType)) {
            return HashUtils.md5(sb.toString(), HashEncodeEnum.BASE64).toUpperCase();
        } else if (SIGN_TYPE_SHA256.equals(signType)){
            return HashUtils.sha256(sb.toString(), HashEncodeEnum.BASE64).toUpperCase();
        }
        return null;
    }

    /**
     * 使用默认的MD5算法生成签名
     * @param data 请求参数所组成的Map
     * @return MD5算法生成的签名数据
     */
    public static String generateSignature(final Map<String, String> data) {
        return generateSignature(data, SIGN_TYPE_MD5);
    }

    /**
     * 验证请求参数中的签名是否正确
     * @param data 请求参数所组成的Map
     * @param signType 签名摘要算法
     * @return 如果签名正确，则返回true，否则返回false
     */
    public static boolean isSignatureValid(final Map<String, String> data, String signType) {
        return data.containsKey(PARAM_SIGN) && data.get(PARAM_SIGN).equals(generateSignature(data, signType));
    }

    /**
     * 使用默认的MD5算法验证请求参数中的签名是否正确
     * @param data 请求参数所组成的Map
     * @return 如果签名正确，则返回true，否则返回false
     */
    public static boolean isSignatureValid(final Map<String, String> data) {
        return isSignatureValid(data, SIGN_TYPE_MD5);
    }

}
