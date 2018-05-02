package top.zywork.dos;

import java.util.Date;

/**
 * PermissionDO数据对象实体类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class PermissionDO extends BaseDO {

    private static final long serialVersionUID = -9223372036647557771L;

    /**
	 * 编号
	 */
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 权限字符串
	 */
	private String permission;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 所属模块
	 */
	private Long moduleId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否激活
	 */
	private Byte isActive;
	
    public PermissionDO () {}

    public PermissionDO (Long id, String title, String permission, String description, Long moduleId, Date createTime, Date updateTime, Byte isActive) {
        this.id = id;
		this.title = title;
		this.permission = permission;
		this.description = description;
		this.moduleId = moduleId;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isActive = isActive;
		
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
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

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	
    @Override
    public String toString() {
        return "PermissionDO{" +
                "id = " + id + 
				", title = " + title + 
				", permission = " + permission + 
				", description = " + description + 
				", moduleId = " + moduleId + 
				", createTime = " + createTime + 
				", updateTime = " + updateTime + 
				", isActive = " + isActive + 
				"}";
    }

}
