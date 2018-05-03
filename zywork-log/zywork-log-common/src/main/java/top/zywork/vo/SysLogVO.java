package top.zywork.vo;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * SysLogVO值对象类<br/>
 *
 * 创建于2018-05-03<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class SysLogVO extends BaseVO {

    private static final long serialVersionUID = -9223372036768413129L;

    /**
	 * 编号
	 */
	private Long id;
	/**
	 * 用户账号
	 */
	@Size(min = 0, max = 100, message = "必须小于100个字符")
	private String userAccount;
	/**
	 * 执行说明
	 */
	@Size(min = 0, max = 500, message = "必须小于500个字符")
	private String description;
	/**
	 * 类名称
	 */
	@Size(min = 0, max = 200, message = "必须小于200个字符")
	private String executeClass;
	/**
	 * 方法名称
	 */
	@Size(min = 0, max = 200, message = "必须小于200个字符")
	private String executeMethod;
	/**
	 * 开始执行时间
	 */
	private Date executeTime;
	/**
	 * 执行耗时(ms)
	 */
	private Long executeCostTime;
	/**
	 * IP地址
	 */
	@Size(min = 0, max = 100, message = "必须小于100个字符")
	private String executeIp;
	
    public SysLogVO() {}

    public SysLogVO(Long id, String userAccount, String description, String executeClass, String executeMethod, Date executeTime, Long executeCostTime, String executeIp) {
        this.id = id;
		this.userAccount = userAccount;
		this.description = description;
		this.executeClass = executeClass;
		this.executeMethod = executeMethod;
		this.executeTime = executeTime;
		this.executeCostTime = executeCostTime;
		this.executeIp = executeIp;
		
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
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

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
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

	
    @Override
    public String toString() {
        return "SysLogDO{" +
                "id = " + id + 
				", userAccount = " + userAccount + 
				", description = " + description + 
				", executeClass = " + executeClass + 
				", executeMethod = " + executeMethod + 
				", executeTime = " + executeTime + 
				", executeCostTime = " + executeCostTime + 
				", executeIp = " + executeIp + 
				"}";
    }

}
