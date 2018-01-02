package top.zywork.dos;

import java.util.Date;

/**
 * 角色DO类，角色基础信息<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
public class RoleDO extends BaseDO {

    private static final long serialVersionUID = -4615193831961074264L;

    private Long id;
    private String title;
    private String description;
    private Date createTime;
    private Integer isActive;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

}
