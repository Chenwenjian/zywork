package top.zywork.dos;

import java.math.BigDecimal;
import java.util.Date;

/**
 * UserSocialDO数据对象实体类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class UserSocialDO extends BaseDO {

    private static final long serialVersionUID = -9223372035328293902L;

    /**
	 * 编号
	 */
	private Long id;
	/**
	 * 用户编号
	 */
	private Long userId;
	/**
	 * 第三方登录OAuth openid
	 */
	private String openid;
	/**
	 * 第三方登录类型编号
	 */
	private Long socialTypeId;
	/**
	 * 第三方登录绑定时间
	 */
	private Date createTime;
	
    public UserSocialDO () {}

    public UserSocialDO (Long id, Long userId, String openid, Long socialTypeId, Date createTime) {
        this.id = id;
		this.userId = userId;
		this.openid = openid;
		this.socialTypeId = socialTypeId;
		this.createTime = createTime;
		
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Long getSocialTypeId() {
		return socialTypeId;
	}

	public void setSocialTypeId(Long socialTypeId) {
		this.socialTypeId = socialTypeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
    @Override
    public String toString() {
        return "UserSocialDO{" +
                "id = " + id + 
				", userId = " + userId + 
				", openid = " + openid + 
				", socialTypeId = " + socialTypeId + 
				", createTime = " + createTime + 
				"}";
    }

}
