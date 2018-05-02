package top.zywork.dto;

/**
 * 用户认证Token DTO，用于存储用户的姓名，生成token的时间，生成的token<br/>
 * 创建于2018-01-19
 *
 * @author 王振宇
 * @version 1.0
 */
public class UserTokenDTO extends BaseDTO {

    private static final long serialVersionUID = -3868698936466154391L;

    // 用户名
    private String username;
    // 生成token的时间
    private Long timestamp;
    // 生成的token
    private String token;

    public UserTokenDTO() {}

    public UserTokenDTO(String username, Long timestamp, String token) {
        this.username = username;
        this.timestamp = timestamp;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
