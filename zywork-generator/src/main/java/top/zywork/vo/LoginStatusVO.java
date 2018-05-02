package top.zywork.vo;

/**
 * 用户登录状态VO，扩展ControllerStatusVO，增加相应的属性<br/>
 *
 * 创建于2018-01-19
 *
 * @author 王振宇
 * @version 1.0
 */
public class LoginStatusVO extends ControllerStatusVO {

    private static final long serialVersionUID = -7962132058765961457L;

    // 会话id
    private String sessionId;
    // 生成的token
    private String token;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
