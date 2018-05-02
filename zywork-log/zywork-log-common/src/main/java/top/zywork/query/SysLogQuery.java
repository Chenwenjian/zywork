package top.zywork.query;

import java.util.Date;

/**
 * SysLogQuery查询对象类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class SysLogQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372036670625824L;

    /**
	 * 编号
	 */
	private Long id;
	/**
	 * 用户编号
	 */
	private Long userId;
	/**
	 * 执行说明
	 */
	private String description;
	/**
	 * 类名称
	 */
	private String executeClass;
	/**
	 * 方法名称
	 */
	private String executeMethod;
	/**
	 * 开始执行时间
	 */
	private Date executeTime;
	/**
	 * 开始执行时间(开始)
	 */
	private Date executeTimeStart;
	/**
	 * 开始执行时间(结束)
	 */
	private Date executeTimeEnd;
	/**
	 * 执行耗时(ms)
	 */
	private Long executeCostTime;
	/**
	 * IP地址
	 */
	private String executeIp;
	
    public SysLogQuery() {}

    public SysLogQuery(Long id, Long userId, String description, String executeClass, String executeMethod, Date executeTime, Date executeTimeStart, Date executeTimeEnd, Long executeCostTime, String executeIp) {
        this.id = id;
		this.userId = userId;
		this.description = description;
		this.executeClass = executeClass;
		this.executeMethod = executeMethod;
		this.executeTime = executeTime;
		this.executeTimeStart = executeTimeStart;
		this.executeTimeEnd = executeTimeEnd;
		this.executeCostTime = executeCostTime;
		this.executeIp = executeIp;
		
    }

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

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public Date getExecuteTimeStart() {
		return executeTimeStart;
	}

	public void setExecuteTimeStart(Date executeTimeStart) {
		this.executeTimeStart = executeTimeStart;
	}

	public Date getExecuteTimeEnd() {
		return executeTimeEnd;
	}

	public void setExecuteTimeEnd(Date executeTimeEnd) {
		this.executeTimeEnd = executeTimeEnd;
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
                "}";
    }

}
