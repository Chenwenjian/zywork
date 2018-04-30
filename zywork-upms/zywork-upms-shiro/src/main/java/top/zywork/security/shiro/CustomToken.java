package top.zywork.security.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义的Shiro Token令牌类，扩展自UsernamePasswordToken，增加了生成token的时间和用户token属性<br/>
 *
 * 创建于2018-01-19
 *
 * @author 王振宇
 * @version 1.0
 */
public class CustomToken extends UsernamePasswordToken {

    private static final long serialVersionUID = -6861967183953259715L;

    // 生成token的时间
    private Long timestamp;
    // 生成的token
    private String token;

    public CustomToken(String username, String hashPassword, Long timestamp, String token) {
        super(username, hashPassword);
        this.timestamp = timestamp;
        this.token = token;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
