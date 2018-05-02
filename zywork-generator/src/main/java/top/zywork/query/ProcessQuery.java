package top.zywork.query;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ProcessQuery查询对象类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class ProcessQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372035190254235L;

    /**
	 * 编号
	 */
	private Long id;
	/**
	 * 流程名字
	 */
	private String name;
	/**
	 * ZIP文件路径
	 */
	private String filePath;
	/**
	 * 流程描述
	 */
	private String description;
	/**
	 * 上传人编号
	 */
	private Long userId;
	/**
	 * 上传时间
	 */
	private Date createTime;
	/**
	 * 上传时间(开始)
	 */
	private Date createTimeStart;
	/**
	 * 上传时间(结束)
	 */
	private Date createTimeEnd;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新时间(开始)
	 */
	private Date updateTimeStart;
	/**
	 * 更新时间(结束)
	 */
	private Date updateTimeEnd;
	/**
	 * 是否部署
	 */
	private Byte isDeploy;
	/**
	 * 部署时间
	 */
	private Date deployTime;
	/**
	 * 部署时间(开始)
	 */
	private Date deployTimeStart;
	/**
	 * 部署时间(结束)
	 */
	private Date deployTimeEnd;
	/**
	 * 是否激活
	 */
	private Byte isActive;
	
    public ProcessQuery () {}

    public ProcessQuery (Long id, String name, String filePath, String description, Long userId, Date createTime, Date createTimeStart, Date createTimeEnd, Date updateTime, Date updateTimeStart, Date updateTimeEnd, Byte isDeploy, Date deployTime, Date deployTimeStart, Date deployTimeEnd, Byte isActive) {
        this.id = id;
		this.name = name;
		this.filePath = filePath;
		this.description = description;
		this.userId = userId;
		this.createTime = createTime;
		this.createTimeStart = createTimeStart;
		this.createTimeEnd = createTimeEnd;
		this.updateTime = updateTime;
		this.updateTimeStart = updateTimeStart;
		this.updateTimeEnd = updateTimeEnd;
		this.isDeploy = isDeploy;
		this.deployTime = deployTime;
		this.deployTimeStart = deployTimeStart;
		this.deployTimeEnd = deployTimeEnd;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Byte getIsDeploy() {
		return isDeploy;
	}

	public void setIsDeploy(Byte isDeploy) {
		this.isDeploy = isDeploy;
	}

	public Date getDeployTime() {
		return deployTime;
	}

	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}

	public Date getDeployTimeStart() {
		return deployTimeStart;
	}

	public void setDeployTimeStart(Date deployTimeStart) {
		this.deployTimeStart = deployTimeStart;
	}

	public Date getDeployTimeEnd() {
		return deployTimeEnd;
	}

	public void setDeployTimeEnd(Date deployTimeEnd) {
		this.deployTimeEnd = deployTimeEnd;
	}

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	
    @Override
    public String toString() {
        return "ProcessDO{" +
                "}";
    }

}
