package top.zywork.dto;

import java.sql.Timestamp;

public class SysLogDTO extends BaseDTO {

    private Long id;
    private Long userId;
    private String description;
    private String executeClass;
    private String executeMethod;
    private Timestamp executeTime;
    private Long executeCostTime;
    private String executeIp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExecuteClass() {
        return executeClass;
    }

    public void setExecuteClass(String executeClass) {
        this.executeClass = executeClass;
    }

    public String getExecuteMethod() {
        return executeMethod;
    }

    public void setExecuteMethod(String executeMethod) {
        this.executeMethod = executeMethod;
    }

    public Timestamp getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Timestamp executeTime) {
        this.executeTime = executeTime;
    }

    public Long getExecuteCostTime() {
        return executeCostTime;
    }

    public void setExecuteCostTime(Long executeCostTime) {
        this.executeCostTime = executeCostTime;
    }

    public String getExecuteIp() {
        return executeIp;
    }

    public void setExecuteIp(String executeIp) {
        this.executeIp = executeIp;
    }
}
