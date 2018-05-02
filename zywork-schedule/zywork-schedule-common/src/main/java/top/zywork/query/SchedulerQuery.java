package top.zywork.query;

import java.util.Date;

/**
 * SchedulerQuery查询对象类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class SchedulerQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372036515726764L;

    /**
	 * 作业编号
	 */
	private Long id;
	/**
	 * 作业名称
	 */
	private String name;
	/**
	 * 作业完整类名
	 */
	private String className;
	/**
	 * CRON表达式
	 */
	private String cronExpression;
	/**
	 * 作业组名称
	 */
	private String groupName;
	/**
	 * 触发器名称
	 */
	private String triggerName;
	/**
	 * 触发器组
	 */
	private String triggerGroup;
	/**
	 * 作业描述
	 */
	private String description;
	/**
	 * 作业状态
	 */
	private Byte jobStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建时间(开始)
	 */
	private Date createTimeStart;
	/**
	 * 创建时间(结束)
	 */
	private Date createTimeEnd;
	/**
	 * 最近一次更新时间
	 */
	private Date updateTime;
	/**
	 * 最近一次更新时间(开始)
	 */
	private Date updateTimeStart;
	/**
	 * 最近一次更新时间(结束)
	 */
	private Date updateTimeEnd;
	/**
	 * 是否可用
	 */
	private Byte isActive;
	
    public SchedulerQuery() {}

    public SchedulerQuery(Long id, String name, String className, String cronExpression, String groupName, String triggerName, String triggerGroup, String description, Byte jobStatus, Date createTime, Date createTimeStart, Date createTimeEnd, Date updateTime, Date updateTimeStart, Date updateTimeEnd, Byte isActive) {
        this.id = id;
		this.name = name;
		this.className = className;
		this.cronExpression = cronExpression;
		this.groupName = groupName;
		this.triggerName = triggerName;
		this.triggerGroup = triggerGroup;
		this.description = description;
		this.jobStatus = jobStatus;
		this.createTime = createTime;
		this.createTimeStart = createTimeStart;
		this.createTimeEnd = createTimeEnd;
		this.updateTime = updateTime;
		this.updateTimeStart = updateTimeStart;
		this.updateTimeEnd = updateTimeEnd;
		this.isActive = isActive;
		
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Byte jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(Date updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public Date getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	
    @Override
    public String toString() {
        return "SchedulerDO{" +
                "}";
    }

}
