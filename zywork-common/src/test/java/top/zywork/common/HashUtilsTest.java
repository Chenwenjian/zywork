package top.zywork.common;

import org.junit.Test;
import top.zywork.enums.HashEncodeEnum;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具的测试类
 * 创建于2017-08-15
 *
 * @author 王振宇
 */
public class HashUtilsTest {

    @Test
    public void testMD5() {
        System.out.println(HashUtils.md5("123456", HashEncodeEnum.HEX));
    }

    @Test
    public void testSHA() {
        System.out.println(HashUtils.sha1("123456", HashEncodeEnum.BASE64));
    }

    @Test
    public void testOneWayEncrypt() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(HashUtils.oneWayEncrypt("123456", "salt", "SHA-1", HashEncodeEnum.BASE64));
    }

}
