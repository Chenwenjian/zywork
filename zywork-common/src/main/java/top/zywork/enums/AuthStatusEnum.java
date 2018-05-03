package top.zywork.enums;

/**
 * 认证与授权失败状态码<br/>
 *
 * 创建于2018-05-03<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public enum AuthStatusEnum {

    UNAUTHENTICATED(1000001, "未认证的用户，无法访问！"),
    UNAUTHORIZED(1000002, "未授权的用户，无法访问！");

    private Integer code;
    private String message;

    AuthStatusEnum(Integer code, String message) {
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
