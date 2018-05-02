package top.zywork.enums;

/**
 * 用户登录状态枚举<br/>
 * 创建于2018-01-19
 *
 * @author 王振宇
 * @version 1.0
 */
public enum UserControllerStatusEnum {

    USER_LOGIN_SUCCESS(10001, "登录成功"),
    USER_LOGIN_ERROR(10002, "系统错误，请稍候再试"),
    USER_LOGIN_DATA_ERROR(10003, "请输入正确的用户名或密码"),
    USER_CHECK_LOGIN_ERROR(10004, "用户认证失败"),
    USER_SESSION_UPDATED(10005, "用户已认证，并且用户会话已更新"),
    USER_CHECK_LOGIN_SUCCESS(10006, "用户认证正常"),
    USER_LOGOUT_SUCCESS(10007, "安全退出成功"),
    USER_LOGOUT_ERROR(10008, "安全退出失败，不合法的用户");

    private Integer code;
    private String message;

    UserControllerStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
