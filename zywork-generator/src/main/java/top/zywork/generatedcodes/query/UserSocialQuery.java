package top.zywork.query;

import java.math.BigDecimal;
import java.util.Date;

/**
 * UserSocialQuery查询对象类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class UserSocialQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372036763125480L;

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
	/**
	 * 第三方登录绑定时间(开始)
	 */
	private Date createTimeStart;
	/**
	 * 第三方登录绑定时间(结束)
	 */
	private Date createTimeEnd;
	
    public UserSocialQuery () {}

    public UserSocialQuery (Long id, Long userId, String openid, Long socialTypeId, Date createTime, Date createTimeStart, Date createTimeEnd) {
        this.id = id;
		this.userId = userId;
		this.openid = openid;
		this.socialTypeId = socialTypeId;
		this.createTime = createTime;
		this.createTimeStart = createTimeStart;
		this.createTimeEnd = createTimeEnd;
		
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

	
    @Override
    public String toString() {
        return "UserSocialDO{" +
                "}";
    }

}
