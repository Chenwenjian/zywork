package top.zywork.enums;

/**
 * 控制器执行结果枚举<br />
 * 创建于2017-08-16
 *
 * @author 王振宇
 * @version 1.0
 */
public enum ControllerStatusEnum {

    OK(101, "成功"),
    ERROR(102, "系统错误"),
    DATA_ERROR(103, "数据错误");

    private Integer code;
    private String message;

    ControllerStatusEnum(Integer code, String message) {
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
