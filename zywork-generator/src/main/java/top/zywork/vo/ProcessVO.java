package top.zywork.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * ProcessVO值对象类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class ProcessVO extends BaseVO {

    private static final long serialVersionUID = -9223372036564851081L;

    /**
	 * 编号
	 */
	private Long id;
	/**
	 * 流程名字
	 */
	@NotBlank(message = "此项是必须项")
	private String name;
	/**
	 * ZIP文件路径
	 */
	@NotBlank(message = "此项是必须项")
	private String filePath;
	/**
	 * 流程描述
	 */
	@Size(min = 0, max = 500, message = "必须小于500个字符")
	private String description;
	/**
	 * 上传人编号
	 */
	@NotNull(message = "此项是必须项")
	private Long userId;
	/**
	 * 上传时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否部署
	 */
	private Byte isDeploy;
	/**
	 * 部署时间
	 */
	private Date deployTime;
	/**
	 * 是否激活
	 */
	private Byte isActive;
	
    public ProcessVO () {}

    public ProcessVO (Long id, String name, String filePath, String description, Long userId, Date createTime, Date updateTime, Byte isDeploy, Date deployTime, Byte isActive) {
        this.id = id;
		this.name = name;
		this.filePath = filePath;
		this.description = description;
		this.userId = userId;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isDeploy = isDeploy;
		this.deployTime = deployTime;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	
    @Override
    public String toString() {
        return "ProcessDO{" +
                "id = " + id + 
				", name = " + name + 
				", filePath = " + filePath + 
				", description = " + description + 
				", userId = " + userId + 
				", createTime = " + createTime + 
				", updateTime = " + updateTime + 
				", isDeploy = " + isDeploy + 
				", deployTime = " + deployTime + 
				", isActive = " + isActive + 
				"}";
    }

}
